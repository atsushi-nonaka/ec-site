package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	
	private final static int VIEW_SIZE = 9;
	
	@RequestMapping("")
	public String showItemList(Model model, Integer page) {
		List<Item> itemList = service.showItemList();
		model.addAttribute("itemList", itemList);
		
		// ページング機能追加
		if (page == null) {
			// ページ数の指定が無い場合は1ページ目を表示させる
			page = 1;
		}
		
		// 表示させたいページ数、ページサイズ、従業員リストを渡し１ページに表示させる従業員リストを絞り込み
		Page<Item> itemPage = service.showListPaging(page, VIEW_SIZE, itemList);
		model.addAttribute("itemPage", itemPage);
		// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
		List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
		model.addAttribute("pageNumbers", pageNumbers);
		
		// オートコンプリート用にJavaScriptの配列の中身を文字列で作ってスコープへ格納
		StringBuilder itemListForAutocomplete = service.getItemListForAutocomplete(itemList);
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
