package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

/**
 * 注文履歴を表示させるためのサービス.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class OrderHistoryService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * 注文履歴リストを検索する.
	 * 
	 * @param userId ユーザーID
	 * @return 注文履歴リスト
	 */
	public List<Order> findOrderHistory(Integer userId, boolean isJoin){
		List<Order> orderList = orderRepository.findOrderHistory(userId, isJoin);
		return orderList;
	}
}
