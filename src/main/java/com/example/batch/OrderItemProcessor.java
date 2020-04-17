package com.example.batch;

import org.springframework.batch.item.ItemProcessor;

import com.example.domain.Order;

public class OrderItemProcessor implements ItemProcessor<Order, Order>{

	@Override
	public Order process(Order order) throws Exception {
		return order;
	}
	
	
}
