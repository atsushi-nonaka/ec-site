package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.service.AddItemInCartService;
import com.example.service.DeleteOrderItemService;

/**
 * 注文を削除するためのコントローラー.
 * 
 * @author nonaa
 *
 */
@Controller
@RequestMapping("")
public class DeleteOrderItemController {

	@Autowired
	private DeleteOrderItemService deleteOrderItemService;
	
	@Autowired
	private AddItemInCartService addItemInCartService;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 注文を削除する.
	 * 
	 * @param orderItemId 注文商品ID
	 * @return 
	 */
	@RequestMapping("/delete_order_item")
	public String deleteOrderItem(Integer orderItemId) {
		Order order = (Order)session.getAttribute("order");
		OrderItem orderItem = null;
		for(OrderItem oi:order.getOrderItemList()) {
			if(oi.getId().equals(orderItemId)) {
				orderItem = oi;
			}
		}
		Integer subtotal = orderItem.getSubTotal();
		
		order.setTotalPrice(order.getCalcTotalPrice()-(int)(subtotal + subtotal * 0.1));
		System.out.println(order.getTotalPrice());
		deleteOrderItemService.deleteOrderTopping(orderItemId);
		deleteOrderItemService.deleteOrderItem(orderItemId);
		deleteOrderItemService.updateTotalPrice(order);
		if(order.getTotalPrice() == 0) {
			deleteOrderItemService.deleteOrder(order.getUserId());
		}
		return "redirect:/show_cart_list";
	}
}
