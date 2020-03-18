package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.SearchItemService;

/**
 * 商品一覧を表示させるためのコントローラー.
 * 
 * @author nonaa
 *
 */
@Controller
@RequestMapping("")
public class SearchItemListController {
	
	@Autowired
	private SearchItemService service;
	
	@RequestMapping("/search")
	public String findItemListByName(String itemName, Model model) {
		List<Item> itemList = service.findItemListByName(itemName);
		model.addAttribute("itemList", itemList);
		return "item_list_curry";
	}
}
