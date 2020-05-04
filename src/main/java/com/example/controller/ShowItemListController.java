package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.form.ItemForm;
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
	
	@ModelAttribute
	private ItemForm setItemForm() {
		return new ItemForm();
	}
	
	@RequestMapping("")
	public String showItemList(Model model, Integer page) {
		// 全件検索で取得したItemオブジェクト群が入ったリスト（要素数１８）
		List<Item> itemList = service.showItemList();
		// itemオブジェクトを三つずつリストに格納しそれをリストに入れる（要素数６）
		List<List<Item>> itemListList = putThreeItemsListInList(itemList);
		List<List<Item>> bigThreeList = new ArrayList<>();
		// 上記itemListListを二つずつ入れたリストをさらにリストに格納する（要素数３）
		List<List<List<Item>>> superItemList = new ArrayList<>();
		for (int i = 0; i < itemListList.size(); i++) {
			bigThreeList.add(itemListList.get(i));
			if (bigThreeList.size() == 2) {
				superItemList.add(bigThreeList);
				bigThreeList = new ArrayList<>();
			}
		}			
		
		if (bigThreeList.size() != 0) {
			superItemList.add(bigThreeList);
		}
		StringBuilder itemListForAutocomplete = service.getItemListForAutocomplete(itemList);
		model.addAttribute("itemListForAutocomplete", itemListForAutocomplete);

		Integer index = 0;
		if (page != null) {
			index = page - 1;
		}
		model.addAttribute("superItemList", superItemList);
		// 従来のItemオブジェクトを３つずつ格納したリスト
		// model.addAttribute("itemListList", itemListList);
		model.addAttribute("bigThreeList", superItemList.get(index));
		model.addAttribute("pattern1", 1);
		return "item_list_curry";
	}

	/**
	 * Itemオブジェクトを3つずつリストに入れそのリストオブジェクト群を大枠のリスト格納する.
	 * 
	 * @param itemList 検索して取得したItemオブジェクト群
	 * @return リストの中にアイテムオブジェクトを入れたリストを格納し返します
	 */
	private List<List<Item>> putThreeItemsListInList(List<Item> itemList) {
		List<List<Item>> itemListList = new ArrayList<>();
		List<Item> smallitemList = new ArrayList<>();
		for (int i = 1; i <= itemList.size(); i++) {
			smallitemList.add(itemList.get(i - 1));
			if (smallitemList.size() % 3 == 0 || itemList.size() == i) {
				itemListList.add(smallitemList);
				smallitemList = new ArrayList<>();
			}
		}
		return itemListList;
	}
}
