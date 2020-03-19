package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.service.ShowItemDetailService;
import com.example.service.ShowToppingListService;

/**
 * 商品詳細を操作するコントローラー.
 * 
 * @author nonaa
 *
 */
@Controller
@RequestMapping("")
public class ShowItemDetailController {

	@Autowired
	private ShowItemDetailService showItemDetailService;
	
	@Autowired
	private ShowToppingListService showToppingListService;
	
	/**
	 * 商品詳細ページに遷移させる.
	 * 
	 * @param id 商品ID
	 * @param model リクエストスコープ
	 * @return 商品詳細ページ
	 */
	@RequestMapping("show_item_detail")
	public String toItemDetail(Integer id, Model model) {
		Item item = showItemDetailService.showItemDetail(id);
		List<Topping> toppingList = showToppingListService.findAll();
		model.addAttribute("item", item);
		model.addAttribute("toppingList", toppingList);
		System.out.println(toppingList);
		return "item_detail";
	}
}
