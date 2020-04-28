package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.service.OrderHistoryService;

/**
 * 注文履歴の取得・ページ遷移を行うコントローラ.
 * 
 * @author nonaa
 *
 */
@Controller
@RequestMapping("")
public class OrderHistoryController {
	
	@Autowired
	private OrderHistoryService service;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 注文履歴を表示させる.
	 * 
	 * @return 注文履歴ページ
	 */
	@RequestMapping("order_history")
	public String showOrderHistory(Model model) {
		List<Order> orderList = service.findOrderHistory((Integer)session.getAttribute("userId"), true);
		model.addAttribute("orderList", orderList);
		return "order_history";
	}
}
