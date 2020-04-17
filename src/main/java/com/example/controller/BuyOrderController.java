package com.example.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.service.BuyOrderService;
import com.example.service.ShowCartListService;

/**
 * 注文時に呼ばれるコントローラー.
 * 
 * @author nonaa
 *
 */
@Controller
@RequestMapping("")
public class BuyOrderController {
	
	@Autowired
	private BuyOrderService service;
	
	@Autowired
	private ShowCartListService showCartListService;
	
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public OrderForm setUpForm() {
		return new OrderForm();
	}
	
	/**
	 * 注文確認画面に遷移する.
	 * 
	 * @return 注文確認画面
	 */
	@RequestMapping("/order_confirm")
	public String toOrderConfirm(Model model) {
		Order order = showCartListService.showOrderdItem((Integer)session.getAttribute("userId"));
		model.addAttribute("order", order);
		return "order_confirm";
	}
	
	/**
	 * 注文を行い、完了画面に遷移する.
	 * 
	 * @param form 注文フォーム
	 * @param result 入力チェック
	 * @return 注文完了画面
	 */
	@RequestMapping("/order_finish")
	public String orderComplete(@Validated OrderForm form, BindingResult result, Model model) {
	
		if(!(form.getDeliveryDate().equals("")) && service.localDateAndLocalTimeToLocalTimeDate(service.stringToLocalDate(form.getDeliveryDate()), service.stringToLocalTime(form.getDeliveryTime())).isBefore(LocalDateTime.now())) {
			result.rejectValue("deliveryDate", null, "配達日が以前に日時になっています");
		}
		
		if(result.hasErrors()) {
			return toOrderConfirm(model);
		}
		
		
		service.orderComplete(form);
		return "order_finished";
	}
}
