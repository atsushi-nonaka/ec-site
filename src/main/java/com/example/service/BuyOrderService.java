package com.example.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.repository.OrderRepository;

/**
 * 購入時に呼び出されるサービス.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class BuyOrderService {
	
	@Autowired
	private OrderRepository repository;
	
	/**
	 * 注文完了するためにアップデートする.
	 * 
	 * @param form オーダーフォーム
	 */
	public void orderComplete(OrderForm form) {
		Order order = new Order();
		BeanUtils.copyProperties(form, order);
		order.setOrderDate(Date.valueOf(LocalDate.now()));
		order.setDeliveryTime(stringToTimestamp(form));
		if(form.getPaymentMethod().equals("cash")) {
			order.setStatus(1);
			order.setPaymentMethod(1);
		}else {
			order.setStatus(2);
			order.setPaymentMethod(2);
		}
		repository.update(order);
	}
	
	/**
	 * String型からTimestamp型に変更する.
	 * 
	 * @param form オーダーフォーム
	 * @return 配達日のタイムスタンプ型
	 */
	public Timestamp stringToTimestamp(OrderForm form) {
		System.out.println(form);
		String deliveryTime = form.getDeliveryTime();
		String deliveryDate = form.getDeliveryDate();
		
		LocalDate date = LocalDate.parse(deliveryDate);
		LocalTime time = LocalTime.parse(deliveryTime + ":00:00");
		
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		
		Timestamp stamp = Timestamp.valueOf(dateTime);
		return stamp;
	}
}
