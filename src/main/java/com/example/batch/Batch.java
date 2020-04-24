package com.example.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.domain.Item;
import com.example.domain.Order;

@Configuration
@EnableBatchProcessing
public class Batch {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public DataSource dataSource;
	
	
	//ItemReader
	public FlatFileItemReader<Item> itemReader(){
		FlatFileItemReader<Item> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("item-data.csv"));
		reader.setLineMapper(new DefaultLineMapper<Item>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setNames(new String[] { "name", "description", "priceM", "priceL", "imagePath", "deleted"});
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper<Item>() {{
				setTargetType(Item.class);
			}});
		}});
		return reader;
	}
	
	//OrderReader
	@Bean
	public JdbcCursorItemReader<Order> orderReader(){
		JdbcCursorItemReader<Order> cursorItemReader = new JdbcCursorItemReader<>();
		cursorItemReader.setDataSource(dataSource);
		cursorItemReader.setSql(orderSql());
		cursorItemReader.setRowMapper(new OrderRowMapper());
		return cursorItemReader;
	}
	
	//ItemProcessor
	@Bean
	public CurryItemProcessor itemProcessor() {
		return new CurryItemProcessor();
	}
	
	//OrderProcessor
	@Bean
	public OrderItemProcessor orderProcessor() {
		return new OrderItemProcessor();
	}
	
	//ItemWriter
	@Bean
	public JdbcBatchItemWriter<Item> itemWriter(){
		JdbcBatchItemWriter<Item> writer = new JdbcBatchItemWriter<>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Item>());
		writer.setSql(itemSql());
		writer.setDataSource(dataSource);
		return writer;
	}
	
	//OrderWriter
	@Bean
	public FlatFileItemWriter<Order> orderWriter(){
		FlatFileItemWriter<Order> writer = new FlatFileItemWriter<Order>();
		writer.setResource(new ClassPathResource("order.csv"));
		
		DelimitedLineAggregator<Order> lineAggregator = new DelimitedLineAggregator<>();
		lineAggregator.setDelimiter(",");
		
		BeanWrapperFieldExtractor<Order> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(setNames());
		lineAggregator.setFieldExtractor(fieldExtractor);
		
		writer.setLineAggregator(lineAggregator);
		return writer;
	}
	
	@Bean
	public JobExecutionListener listener() {
		return new JobStartEndListener(new JdbcTemplate(dataSource));
	}
	
	/**
	 * ステップの実行を構築するメソッドです.
	 * @return
	 */
	@Bean
	public Step truncateStep() {
		return stepBuilderFactory.get("truncateStep")
				.tasklet(truncateTasklet()).build();
	}

	/**
	 *
	 * @return
	 */
	@Bean
	public MethodInvokingTaskletAdapter truncateTasklet() {
		MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
		adapter.setTargetObject(truncateService());
		adapter.setTargetMethod("execute");
		return adapter;
	}

	/**
	 * TruncateStepの実行処理とそれが完了したことを通知する処理を返すメソッドです
	 * @return
	 */
	@Bean
	public TruncateService truncateService() {
		return new TruncateServiceImpl();
	}

	
	//ステップ1(item情報をDBに)
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Item, Item> chunk(10)
				.reader(itemReader())
				.processor(itemProcessor())
				.writer(itemWriter())
				.build();
	}
	
	//ステップ2(Order情報をcsvに)
	@Bean
	public Step step2() {	
		return stepBuilderFactory.get("step2")
				.<Order, Order> chunk(10)
				.reader(orderReader())
				.processor(orderProcessor())
				.writer(orderWriter())
				.build();
	}
	
	//Job
	@Bean
	public Job importItemAndExportOrderJob() {
		return jobBuilderFactory.get("importItemAndExportOrderJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.flow(truncateStep())
				.next(step1())
				.next(step2())
				.end()
				.build();
	}
	
	/**
	 * 商品情報をCSV→DBに登録するためのSQL文.
	 * 
	 * @return 商品挿入SQL
	 */
	public String itemSql() {
		String sql = "INSERT INTO items (name, description, price_m, price_l, image_path, deleted) VALUES (:name, :description, :priceM, :priceL, :imagePath, :deleted )";
		return sql;
	}
	
	/**
	 * 注文情報をDB→CSVに書くためのSQL文.
	 * 
	 * @return 注文情報を表すSQL
	 */
	public String orderSql() {
		String sql = "SELECT o.id o_id, o.user_id o_user_id, o.status o_status, o.total_price o_total_price, o.order_date o_order_date, "
					+ "o.destination_name o_destination_name, o.destination_email o_destination_email, o.destination_zipcode o_destination_zipcode, "
					+ "o.destination_address o_destination_address, o.destination_tel o_destination_tel, o.delivery_time o_delivery_time, o.payment_method o_payment_method, "
					+ "u.id u_id, u.name u_name, u.email u_email, u.password u_password, u.zipcode u_zipcode, u.address u_address, u.telephone u_telephone  FROM orders o "
					+ "LEFT OUTER JOIN users u ON o.user_id = u.id";
		return sql;
	}
	
	public String[] setNames() {
		String[] names = {"id", "userId" , "status", "totalPrice", "orderDate", "destinationName", "destinationEmail", "destinationZipcode", "destinationAddress", 
							"destinationTel", "deliveryTime", "paymentMethod", "user"};
		return names;
	}	
	
}