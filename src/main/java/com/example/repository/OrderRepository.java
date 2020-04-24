package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;
import com.example.domain.User;

/**
 * 注文データベースを操作する.
 * 
 * @author nonaa
 *
 */
@Repository
public class OrderRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private SimpleJdbcInsert insert;
	
	public static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setOrder_number(rs.getString("order_number"));
		order.setUserId(rs.getInt("user_id"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setOrderDate(rs.getDate("order_date"));
		order.setDestinationName(rs.getString("destination_name"));
		order.setDestinationEmail(rs.getString("destination_email"));
		order.setDestinationZipcode(rs.getString("destination_zipcode"));
		order.setDestinationAddress(rs.getString("destination_address"));
		order.setDestinationTel(rs.getString("destination_tel"));
		order.setDeliveryTime(rs.getTimestamp("delivery_time"));
		order.setPaymentMethod(rs.getInt("payment_method"));
		return order;
	};
	
	private final static ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTOR = (rs) ->{
		List<Order> orderList = new ArrayList<>();
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;
		List<Topping> toppingList = null;
		int beforeOrderId = 0;
		int beforeOrderItemId = 0;
		while(rs.next()) {
			int nowOrderId = rs.getInt("o_id");
			int nowOrderItemId = rs.getInt("oi_id");

			if(beforeOrderId != nowOrderId) {
				Order order = new Order();
				order.setId(rs.getInt("o_id"));
				order.setOrder_number(rs.getString("o_order_number"));
				order.setUserId(rs.getInt("o_user_id"));
				order.setStatus(rs.getInt("o_status"));
				order.setTotalPrice(rs.getInt("o_total_price"));
				order.setOrderDate(rs.getDate("o_order_date"));
				order.setDestinationName(rs.getString("o_destination_name"));
				order.setDestinationEmail(rs.getString("o_destination_email"));
				order.setDestinationZipcode(rs.getString("o_destination_zipcode"));
     			order.setDestinationTel(rs.getString("o_destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("o_delivery_time"));
				order.setPaymentMethod(rs.getInt("o_payment_method"));
				orderItemList = new ArrayList<>();
			
				User user = new User();
				user.setId(rs.getInt("u_id"));
				user.setName(rs.getString("u_name"));
				user.setEmail(rs.getString("u_email"));
				user.setPassword(rs.getString("u_password"));
				user.setZipcode(rs.getString("u_zipcode"));
				user.setAddress(rs.getString("u_address"));
				user.setTelephone(rs.getString("u_telephone"));
				
				order.setUser(user);
				order.setOrderItemList(orderItemList);
				orderList.add(order);
			}
			
			if(beforeOrderItemId != nowOrderItemId) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("oi_id"));
				orderItem.setItemId(rs.getInt("oi_item_id"));
				orderItem.setOrderId(rs.getInt("oi_order_id"));
				orderItem.setQuantity(rs.getInt("oi_quantity"));
				orderItem.setSize(rs.getString("oi_size"));
				orderToppingList = new ArrayList<>();
				
				Item item = new Item();
				item.setId(rs.getInt("i_id"));
     			item.setName(rs.getString("i_name"));
				item.setDescription(rs.getString("i_description"));
				item.setPriceM(rs.getInt("i_price_m"));
				item.setPriceL(rs.getInt("i_price_l"));
				item.setImagePath(rs.getString("i_image_path"));
				item.setDeleted(rs.getBoolean("i_deleted"));
				
				toppingList = new ArrayList<>();
				item.setToppingList(toppingList);
				orderItem.setItem(item);
				orderItem.setOrderToppingList(orderToppingList);
				orderItemList.add(orderItem);
			}
			
			if(rs.getInt("ot_id") != 0) {
				Topping topping = new Topping();
				topping.setId(rs.getInt("t_id"));
				topping.setName(rs.getString("t_name"));
				topping.setPriceL(rs.getInt("t_price_l"));
				topping.setPriceM(rs.getInt("t_price_m"));
				toppingList.add(topping);
				
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("ot_id"));
				orderTopping.setToppingId(rs.getInt("ot_topping_id"));
				orderTopping.setOrderItemId(rs.getInt("ot_order_item_id"));
				orderTopping.setTopping(topping);		
				orderToppingList.add(orderTopping);				
			}
				
			beforeOrderId = nowOrderId;
			beforeOrderItemId = nowOrderItemId;
		}
		return orderList;
	};
	
	/**
	 * idカラムが自動採番されることをDIコンテナに教える.
	 * init()...インスタンスされるときに1度だけ実行されるメソッド.
	 */
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("orders");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
	/**
	 * 自動採番されたIDを取得する.
	 * 
	 * @param order 注文情報
	 * @return 注文情報
	 */
	public Order insert(Order order) {
		String sql = "INSERT INTO orders(user_id, status, total_price) VALUES(:userId, :status, :totalPrice)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", order.getUserId()).addValue("status", 0).addValue("totalPrice", 0);
		
		Number key = insert.executeAndReturnKey(param);
		order.setId(key.intValue());
		return order;
	}
	
	/**
	 * 注文テーブルの更新をする.
	 * 
	 * @param order 注文情報
	 */
	public void update(Order order) {
		String sql = "UPDATE orders SET status = :status, order_date = :orderDate, destination_name = :destinationName, destination_email = :destinationEmail, "
				    + "destination_zipcode = :destinationZipcode, destination_address = :destinationAddress, "
				    + "destination_tel = :destinationTel, delivery_time = :deliveryTime, payment_method = :paymentMethod";
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		template.update(sql, param);
	}
	
	/**
	 * ユーザーIDとステータスから注文情報を取得する.
	 * 
	 * @param userId ユーザーID
	 * @return 注文情報
	 */
	public Order checkByUserIdAndStatus(Integer userId) {
		try {
			String sql = "select id, order_number, user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method from orders where user_id = :userId and status = 0";
			SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", 0);
			Order order = template.queryForObject(sql, param, ORDER_ROW_MAPPER);
			return order;
		} catch (Exception e) {
			return null;	
		}
	}
	
	/**
	 * ショッピングカートの中身を表示させる.
	 * 
	 * @param userId ユーザーID
	 * @return 注文情報
	 */
	public Order findOrderedItem(Integer userId){
		String sql = "SELECT o.id o_id, o.order_number o_order_number, o.user_id o_user_id, o.status o_status, o.total_price o_total_price, o.order_date o_order_date, "
					+ "o.destination_name o_destination_name, o.destination_email o_destination_email, o.destination_zipcode o_destination_zipcode, "
					+ "o.destination_address o_destination_address, o.destination_tel o_destination_tel, o.delivery_time o_delivery_time, o.payment_method o_payment_method, "
					+ "u.id u_id, u.name u_name, u.email u_email, u.password u_password, u.zipcode u_zipcode, u.address u_address, u.telephone u_telephone, "
					+ "oi.id oi_id, oi.item_id oi_item_id, oi.order_id oi_order_id, oi.quantity oi_quantity, oi.size oi_size, "
					+ "i.id i_id, i.name i_name, i.description i_description, i.price_m i_price_m, i.price_l i_price_l, i.image_path i_image_path, i.deleted i_deleted, "
					+ "ot.id ot_id, ot.topping_id ot_topping_id, ot.order_item_id ot_order_item_id, "
					+ "t.id t_id, t.name t_name, t.price_m t_price_m, t.price_l t_price_l "
					+ "FROM orders o LEFT OUTER JOIN users u ON o.user_id = u.id "
					+ "LEFT OUTER JOIN order_items oi ON o.id = oi.order_id "
					+ "LEFT OUTER JOIN items i ON oi.item_id = i.id "
					+ "LEFT OUTER JOIN order_toppings ot ON ot.order_item_id = oi.id "
					+ "LEFT OUTER JOIN toppings t ON t.id = ot.topping_id "
					+ "WHERE o.user_id = :userId AND status = :status ORDER BY i.price_m";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", 0);
		
		List<Order> orderList = template.query(sql, param, ORDER_RESULT_SET_EXTRACTOR);
		if (orderList.size() == 0) {
			return null;
		}
		System.out.println(orderList.get(0));
		return orderList.get(0);
		
	}
	
	/**
	 * 注文履歴を表示させる.
	 * 
	 * @param userId ユーザーID
	 * @return 注文情報
	 */
	public List<Order> findOrderHistory(Integer userId){
		String sql = "SELECT o.id o_id, o.order_number o_order_number, o.user_id o_user_id, o.status o_status, o.total_price o_total_price, o.order_date o_order_date, "
				+ "o.destination_name o_destination_name, o.destination_email o_destination_email, o.destination_zipcode o_destination_zipcode, "
				+ "o.destination_address o_destination_address, o.destination_tel o_destination_tel, o.delivery_time o_delivery_time, o.payment_method o_payment_method, "
				+ "u.id u_id, u.name u_name, u.email u_email, u.password u_password, u.zipcode u_zipcode, u.address u_address, u.telephone u_telephone, "
				+ "oi.id oi_id, oi.item_id oi_item_id, oi.order_id oi_order_id, oi.quantity oi_quantity, oi.size oi_size, "
				+ "i.id i_id, i.name i_name, i.description i_description, i.price_m i_price_m, i.price_l i_price_l, i.image_path i_image_path, i.deleted i_deleted, "
				+ "ot.id ot_id, ot.topping_id ot_topping_id, ot.order_item_id ot_order_item_id, "
				+ "t.id t_id, t.name t_name, t.price_m t_price_m, t.price_l t_price_l "
				+ "FROM orders o LEFT OUTER JOIN users u ON o.user_id = u.id "
				+ "LEFT OUTER JOIN order_items oi ON o.id = oi.order_id "
				+ "LEFT OUTER JOIN items i ON oi.item_id = i.id "
				+ "LEFT OUTER JOIN order_toppings ot ON ot.order_item_id = oi.id "
				+ "LEFT OUTER JOIN toppings t ON t.id = ot.topping_id "
				+ "WHERE o.user_id = :userId AND NOT status = :status ORDER BY o.order_date DESC";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", 0);
		
		List<Order> orderList = template.query(sql, param, ORDER_RESULT_SET_EXTRACTOR);
		if (orderList.size() == 0) {
			return null;
		}
		return orderList;
		
	}
	
	
	/**
	 * 注文情報の全件検索.
	 * 
	 * @return 全件入りの注文リスト
	 */
	public List<Order> findAllOrder(){
		String sql = "SELECT id, order_number, user_id,status,total_price,order_date,destination_name,destination_email, "
					+ "destination_zipcode,destination_address,destination_tel,delivery_time,payment_method FROM orders";
		List<Order> orderList = template.query(sql, ORDER_ROW_MAPPER);
		return orderList;
	}
	
	/**
	 * 合計金額を注文テーブルに挿入する.
	 * 
	 * @param order 注文情報
	 */
	public void updateTotalPrice(Order order) {
		String sql = "UPDATE orders SET total_price = :totalPrice WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("totalPrice", order.getTotalPrice()).addValue("id", order.getId());
		template.update(sql, param);
	}
	
	/**
	 * 注文情報を消去する.
	 * 
	 * @param userId
	 */
	public void delete(Integer userId) {
		String sql = "DELETE FROM orders WHERE user_id = :userId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		template.update(sql, param);
	}
	
}
