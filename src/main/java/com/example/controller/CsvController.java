package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.service.OrderListService;

@Controller
@RequestMapping("")
public class CsvController {

	@Autowired
	private OrderListService service;
	
	@RequestMapping("/download/csv")
	public void csvDown(HttpServletResponse response) {

		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"test.csv\"");
		List<Order> orderList = service.findCsvData();

		try (PrintWriter pw = response.getWriter()) {
			for (Order order : orderList) {
				for (OrderItem orderItem : order.getOrderItemList()) {
					String status = "未入金";
					if (order.getStatus() == 2) {
						status = "入金済";
					}
					pw.print(order.getOrderNumber() + "," + order.getOrderDate() + "," + orderItem.getItem().getName()
							+ "," + order.getUser().getName() + "," + status + "," + orderItem.getSubTotal() + ","
							+ "\r\n");
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}