package com.example.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;

/**
 * 注文商品テーブルを操作するレポジトリ.
 * 
 * @author nonaa
 *
 */
@Repository
public class OrderItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private SimpleJdbcInsert insert;
	
	/**
	 * idカラムが自動採番されることをDIコンテナに教える.
	 * init()...インスタンスされるときに1度だけ実行されるメソッド.
	 */
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("order_items");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
	/**
	 * 自動採番されたIDを取得する.
	 * 
	 * @param order 注文情報
	 * @return 注文情報
	 */
	public Integer insert(OrderItem orderItem) {
		String sql = "INSERT INTO order_items(item_id, order_id, quantity, size) VALUES(:itemId, :orderId, :quantity, :size)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		
		Number key = insert.executeAndReturnKey(param);
		return key.intValue();
	}
	
	/**
	 * カート内に商品を追加する.
	 * 
	 * @param orderItem
	 */
	public Integer addItemInCart(OrderItem orderItem) {
		String sql = "INSERT INTO order_items(item_id, order_id, quantity, size) "
					+ "VALUES(:itemId, :orderId, :quantity, :size)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		template.update(sql, param);
		
		Number key = insert.executeAndReturnKey(param);
		return key.intValue();
	}
	
	/**
	 * 注文商品を更新する.
	 * 
	 * @param hashedId
	 * @param loginUsersOrderId
	 */
	public void updateOrderIdByOrderId(Integer hashedId, Integer loginUsersOrderId) {
		String sql = "UPDATE order_items SET order_id = :loginUsersOrderId WHERE order_id = :hashedId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("hashedId", hashedId).addValue("loginUsersOrderId", loginUsersOrderId);
		template.update(sql, param);
	}
	
	/**
	 * カート内の商品を削除します.
	 * 
	 * @param id 注文商品のID
	 */
	public void delete(Integer orderItemId) {
		String sql = "DELETE FROM order_items WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", orderItemId);
		template.update(sql, param);
	}
}
