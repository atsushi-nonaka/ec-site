package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.OrderForm;
import com.example.service.BuyOrderService;

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
	
	@RequestMapping("/order_confirm")
	public String toOrderConfirm() {
		return "order_confirm";
	}
	
	@RequestMapping("/order_finish")
	public String orderComplete(@Validated OrderForm form, BindingResult result) {
		if(result.hasErrors()) {
			return toOrderConfirm();
		}
		
		service.orderComplete(form);
		return "order_finished";
	}
}
