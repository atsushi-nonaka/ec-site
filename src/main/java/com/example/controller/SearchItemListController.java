package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.SearchItemService;
import com.example.service.ShowItemListService;

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
	
	@Autowired
	private ShowItemListService showItemListService;
	
	@Autowired
	private HttpSession session;
	
	private final static int VIEW_SIZE = 9;
	
	@RequestMapping("/search")
	public String findItemListByName(String price, Integer page, String itemName, Model model) {
		List<Item> itemList = new ArrayList<>();
		
		if(itemList.isEmpty()) {
			itemList = service.findItemListByName(itemName, model);
		}
		
		if(session.getAttribute("itemName") != null) {
			itemName = (String) session.getAttribute("itemName");
			itemList = service.findItemListByName(itemName, model);
		}
		
		if (page == null) {
			page = 1;
		}
		model.addAttribute("page", page);
		session.setAttribute("itemName", itemName);
		
		Page<Item> itemPage = showItemListService.showListPaging(page, VIEW_SIZE, itemList);
		model.addAttribute("itemPage", itemPage);
		List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
		model.addAttribute("pageNumbers", pageNumbers);
		
		StringBuilder itemListForAutocomplete = showItemListService.getItemListForAutocomplete(itemList);
		model.addAttribute("itemListForAutocomplete", itemListForAutocomplete);
				
		return "item_list_curry";
	}
	
	/**
	 * ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
	 * 
	 * @param model        モデル
	 * @param employeePage ページング情報
	 */
	private List<Integer> calcPageNumbers(Model model, Page<Item> itemPage) {
		int totalPages = itemPage.getTotalPages();
		List<Integer> pageNumbers = null;
		if (totalPages > 0) {
			pageNumbers = new ArrayList<Integer>();
			for (int i = 1; i <= totalPages; i++) {
				pageNumbers.add(i);
			}
		}
		return pageNumbers;
	}
	
}
