package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderTopping;

/**
 * 注文トッピングに関わるレポジトリ.
 * 
 * @author nonaa
 *
 */
@Repository
public class OrderToppingRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 注文トッピング情報を登録する.
	 * 
	 * @param orderTopping 注文トッピング
	 */
	public void insert(OrderTopping orderTopping) {
		String sql = "INSERT INTO order_toppings(topping_id, order_item_id) VALUES(:toppingId, :orderItemId)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("toppingId", orderTopping.getToppingId()).addValue("orderItemId", orderTopping.getOrderItemId());
		template.update(sql, param);
	}
	
	/**
	 * 注文商品のIDからトッピング情報を消去する.
	 * 
	 * @param orderItemId
	 */
	public void delete(Integer orderItemId) {
		String sql = "DELETE FROM order_toppings WHERE order_item_id = :orderItemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
		template.update(sql, param);
	}
}
