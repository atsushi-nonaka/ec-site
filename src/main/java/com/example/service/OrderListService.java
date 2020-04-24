package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

/**
 * 全件の注文リスト情報を表すサービス.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class OrderListService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * 注文を全件検索する.
	 * 
	 * @return 注文リスト
	 */
	@Autowired
	public List<Order> findAllOrder(){
		List<Order> orderList = orderRepository.findAllOrder();
		return orderList;
	}
	
}
