package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemListService;

/**
 * 商品一覧を表示させるためのコントローラー.
 * 
 * @author nonaa
 *
 */
@Controller
@RequestMapping("")
public class ShowItemListController {
	
	@Autowired
	private ShowItemListService service;
	
	@RequestMapping("")
	public String showItemList(Model model) {
		List<Item> itemList = service.showItemList();
		model.addAttribute("itemList", itemList);
		return "item_list_curry";
	}
}
