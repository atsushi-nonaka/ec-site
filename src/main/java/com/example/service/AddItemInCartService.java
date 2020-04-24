package com.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.form.OrderItemForm;
import com.example.repository.ItemRepository;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;
import com.example.repository.ToppingRepository;

/**
 * カート内に商品を入れるためのサービス.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class AddItemInCartService {

	@Autowired
	private HttpSession session;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	@Autowired
	private OrderToppingRepository orderToppingRepository;

	/**
	 * 商品をカート内に入れる.
	 * 
	 * @param orderItem 注文商品
	 */
	public void addItemInCart(OrderItemForm form) {
		Order order = new Order();
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			userId = session.getId().hashCode();
		}
		
		if (orderRepository.checkByUserIdAndStatus(userId) == null) {						
			order.setUserId(userId);					
			order = orderRepository.insert(order);					
		} else {						
			order = orderRepository.checkByUserIdAndStatus(userId);					
		}						

		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(Integer.parseInt(form.getItemId()));
		orderItem.setQuantity(Integer.parseInt(form.getQuantity()));
		orderItem.setSize(form.getSize());
		orderItem.setOrderId(order.getId());
		orderItem.setItem(itemRepository.findById(orderItem.getItemId()));
		Integer orderItemId = orderItemRepository.insert(orderItem);
		orderItem.setId(orderItemId);

		//ラムダ式に変更
		if (form.getToppingIdList() != null) {
			List<OrderTopping> orderToppingList = new ArrayList<>();
			form.getToppingIdList().stream().forEach(toppingId -> {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setToppingId(Integer.parseInt(toppingId));
				orderTopping.setOrderItemId(orderItem.getId());
				orderTopping.setTopping(toppingRepository.findByToppingId(Integer.parseInt(toppingId)));
				orderToppingRepository.insert(orderTopping);
				orderToppingList.add(orderTopping);
			});
			
//			for (String toppingId : form.getToppingIdList()) {
//				OrderTopping orderTopping = new OrderTopping();
//				orderTopping.setToppingId(Integer.parseInt(toppingId));
//				orderTopping.setOrderItemId(orderItem.getId());
//				orderTopping.setTopping(toppingRepository.findByToppingId(Integer.parseInt(toppingId)));
//				orderToppingRepository.insert(orderTopping);
//				orderToppingList.add(orderTopping);
//			}
			orderItem.setOrderToppingList(orderToppingList);
		}
		
		order = orderRepository.findOrderedItem(userId);		
		order.setTotalPrice(order.getCalcTotalPrice());						
		orderRepository.updateTotalPrice(order);						
								
		if (userId == session.getId().hashCode()) {						
			session.setAttribute("hashedOrder", order);					
		}	

	}
	
}
