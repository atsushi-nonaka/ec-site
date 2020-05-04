package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Order;
import com.example.service.OrderListService;

/**
 * 売り上げに関するグラフを表示させるAPI.
 * 
 * @author nonaa
 *
 */
@RestController
@RequestMapping(value="")
public class ChartJsApi {
	
	@Autowired
	private OrderListService service;
	
	@RequestMapping(value="/check_sales",method = RequestMethod.POST)
	public Map<String, List<Order>> checkCreditNumber(){
		Map<String, List<Order>> map = new HashMap<>();
		List<Order> orderList = service.findAllOrder();
		map.put("orderList", orderList);
		return map;
	}
}
