package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.service.OrderListService;

/**
 * 管理者画面を表示させるコントローラー.
 * 
 * @author nonaa
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminOrderList {
	
	@Autowired
	private OrderListService orderService;
	
	/**
	 * 注文して購入された商品を表示させる.
	 * 
	 * @param model 注文リストを入れるためのリクエストスコープ
	 * @return 注文リストページ
	 */
	@RequestMapping("/order_list")
	public String showOrderList(Model model) {
		List<Order> orderList = orderService.findAllOrder();
		model.addAttribute("orderList", orderList);
		return "order_list";
	}
}
