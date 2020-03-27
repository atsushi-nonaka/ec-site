package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.OrderItemForm;
import com.example.service.AddItemInCartService;
import com.example.service.ShowCartListService;

/**
 * 商品をカート内に入れるコントローラー.
 * 
 * @author nonaa
 *
 */
@Controller
@RequestMapping("")
public class AddItemInCartController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private AddItemInCartService addItemInCartService;
	
	@Autowired
	private ShowCartListService showCartListService;
	
	/**
	 * カートリストページに遷移する.
	 * 
	 * @return カートリストページ
	 */
	@RequestMapping("/item_into_cart")
	public String itemIntoCart(OrderItemForm form) {
		addItemInCartService.addItemInCart(form);
		return "redirect:/show_cart_list";
	}
	
	@RequestMapping("/show_cart_list")
	public String toCartList(Model model) {
		Order order = null;
		if(session.getAttribute("userId") == null) {
			order = showCartListService.showOrderdItem(session.getId().hashCode());
		}else {
			order = showCartListService.showOrderdItem((Integer)session.getAttribute("userId"));
		}
		model.addAttribute("order", order);
		session.setAttribute("order", order);
		return "cart_list";
	}
}
