package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.service.OrderListService;

@Controller
@RequestMapping("/admin")
public class AdminOrderList {
	
	@Autowired
	private OrderListService orderService;
	
	@RequestMapping("/order_list")
	public String showOrderList(Model model) {
		List<Order> orderList = orderService.findAllOrder();
		model.addAttribute("orderList", orderList);
		return "order_list";
	}
}
