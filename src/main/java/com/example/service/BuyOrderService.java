package com.example.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
	
	@Autowired
	private MailSender sender;
	
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
		sendMail(order);
	}
	
	/**
	 * String型からTimestamp型に変更する.
	 * 
	 * @param form オーダーフォーム
	 * @return 配達日のタイムスタンプ型
	 */
	public Timestamp stringToTimestamp(OrderForm form) {
		Timestamp stamp = Timestamp.valueOf(localDateAndLocalTimeToLocalTimeDate(stringToLocalDate(form.getDeliveryDate()), stringToLocalTime(form.getDeliveryTime())));
		return stamp;
	}
	
	/**
	 * 配達日をString型からLocalDate型に変換する.
	 * 
	 * @param form 注文フォーム
	 * @return 配達日
	 */
	public LocalDate stringToLocalDate(String deliveryDate) {
		LocalDate date = LocalDate.parse(deliveryDate);
		return date;
	}
	
	/**
	 * 配達時間をString型からLocalTime型に変換する.
	 * 
	 * @param form 注文フォーム
	 * @return 配達時間
	 */
	public LocalTime stringToLocalTime(String deliveryTime) {
		LocalTime time = LocalTime.parse(deliveryTime + ":00:00");
		return time;
	}
	
	/**
	 * LocalDateとLocalTimeからLocalDateTimeに変換する.
	 * 
	 * @param date 配達日
	 * @param time 配達時間
	 * @return 配達日時
	 */
	public LocalDateTime localDateAndLocalTimeToLocalTimeDate(LocalDate date, LocalTime time) {
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		return dateTime;
	}
	
	/**
	 * メールの送信を行う.
	 * 
	 * @param order 注文情報
	 */
	public void sendMail(Order order) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("test@gmail.com");
		msg.setTo(order.getDestinationEmail());
		msg.setSubject("テストメール");
		msg.setText("ご注文ありがとうございます");
		sender.send(msg);
	}
}
