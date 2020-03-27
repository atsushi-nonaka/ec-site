package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

/**
 * カートリストの中身を表示させる.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class ShowCartListService {
	
	@Autowired
	private OrderRepository repository;
	
	/**
	 * カートの中身を表示させる.
	 * 
	 * @return 注文リスト
	 */
	public Order showOrderdItem(Integer userId){
		Order order = repository.findOrderedItem(userId);
		return order;
	}
}
