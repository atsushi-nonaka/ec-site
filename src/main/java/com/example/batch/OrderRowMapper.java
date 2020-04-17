package com.example.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.domain.Order;
import com.example.domain.User;

public class OrderRowMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setId(rs.getInt("o_id"));
		order.setUserId(rs.getInt("o_user_id"));
		order.setStatus(rs.getInt("o_status"));
		order.setTotalPrice(rs.getInt("o_total_price"));
		order.setOrderDate(rs.getDate("o_order_date"));
		order.setDestinationName(rs.getString("o_destination_name"));
		order.setDestinationEmail(rs.getString("o_destination_email"));
		order.setDestinationZipcode(rs.getString("o_destination_zipcode"));
		order.setDestinationAddress(rs.getString("o_destination_address"));
		order.setDestinationTel(rs.getString("o_destination_tel"));
		order.setDeliveryTime(rs.getTimestamp("o_delivery_time"));
		order.setPaymentMethod(rs.getInt("o_payment_method"));
		
		User user = new User();
		user.setId(rs.getInt("u_id"));
		user.setName(rs.getString("u_name"));
		user.setEmail(rs.getString("u_email"));
		user.setPassword(rs.getString("u_password"));
		user.setZipcode(rs.getString("u_zipcode"));
		user.setAddress(rs.getString("u_address"));
		user.setTelephone(rs.getString("u_telephone"));
		
		order.setUser(user);
		return order;
	}

}
