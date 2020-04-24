package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.service.OrderListService;

/**
 * 管理者画面で全ての注文情報を操作するクラス.
 * 
 * @author nonaa
 *
 */
@Controller
@RequestMapping("")
public class OrderListController {
	
	@Autowired
	private OrderListService orderListService;
	
	/**
	 * 注文一覧情報のページに遷移する.
	 * 
	 * @param model リクエストスコープ
	 * @return 注文リスト
	 */
	@RequestMapping("/show_order_list")
	public String showOrderList(Model model) {
		List<Order> orderList = orderListService.findAllOrder();
		model.addAttribute("orderList", orderList);
		return "order_list";
	}
}
