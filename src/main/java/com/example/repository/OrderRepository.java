package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Order;

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
}
