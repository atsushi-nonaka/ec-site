package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.form.ItemForm;
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
	
	@ModelAttribute
	private ItemForm setUpForm() {
		return new ItemForm();
	}

	@RequestMapping("/search")
	public String findItemListByName(Integer page, ItemForm itemForm, Model model) {
		List<Item> itemList = new ArrayList<>();
		if (itemForm.getHighLow().equals("1")) {
			itemList = service.findItemListByLowPrice(itemForm.getName());
		} else if (itemForm.getHighLow().equals("2")) {
			itemList = service.findItemListByHighPrice(itemForm.getName());
		}
		if (itemList.isEmpty()) {
			itemList = showItemListService.showItemList();
			model.addAttribute("message", "該当する商品がございません");
		}
		//条件検索したItemオブジェクトが3つずつ格納されたリスト
		List<List<Item>> itemListList = putThreeItemsListInList(itemList);
		List<List<Item>> bigThreeList = new ArrayList<>();
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
		StringBuilder itemListForAutocomplete = showItemListService.getItemListForAutocomplete(itemList);
		model.addAttribute("itemListForAutocomplete", itemListForAutocomplete);
		Integer index = 0;
		if(page != null) {
			index = page -1;
		}
		model.addAttribute("superItemList", superItemList);
		model.addAttribute("bigThreeList", superItemList.get(index));
		model.addAttribute("name", itemForm.getName());
		model.addAttribute("pattern2", 1);
		model.addAttribute("highLow", itemForm.getHighLow());
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
