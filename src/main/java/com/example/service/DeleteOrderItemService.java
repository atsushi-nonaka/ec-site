package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;

/**
 * 注文商品を削除するためのサービスクラス.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class DeleteOrderItemService {
	
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * 注文した商品を削除する.
	 * 
	 * @param id
	 */
	public void deleteOrderItem(Integer orderItemId) {
		orderItemRepository.delete(orderItemId);
	}
	
	/**
	 * 注文した商品のトッピング情報を削除する.
	 * 
	 * @param orderItemId
	 */
	public void deleteOrderTopping(Integer orderItemId) {
		orderToppingRepository.delete(orderItemId);
	}
	
	/**
	 * オーダー情報を消去する.
	 * 
	 * @param userId
	 */
	public void deleteOrder(Integer userId) {
		orderRepository.delete(userId);
	}
	
	/**
	 * 合計金額を変更する.
	 * 
	 * @param order
	 */
	public void updateTotalPrice(Order order) {
		orderRepository.updateTotalPrice(order);
	}
}
