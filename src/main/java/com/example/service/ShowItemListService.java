package com.example.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品一覧を表示させるサービス.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class ShowItemListService {

	@Autowired
	private ItemRepository repository;

	public List<Item> showItemList() {
		List<Item> itemList = repository.findAll();
		return itemList;
	}
	
	/**
	 * ページング用メソッド.
	 * 
	 * @param page         表示させたいページ数
	 * @param size         １ページに表示させる従業員数
	 * @param employeeList 絞り込み対象リスト
	 * @return １ページに表示されるサイズ分の従業員一覧情報
	 */
	public Page<Item> showListPaging(int page, int size, List<Item> itemList) {
		// 表示させたいページ数を-1しなければうまく動かない
		page--;
		// どの商品から表示させるかと言うカウント値
		int startItemCount = page * size;
		// 絞り込んだ後の商品リストが入る変数
		List<Item> list;

		if (itemList.size() < startItemCount) {
			// (ありえないが)もし表示させたい商品カウントがサイズよりも大きい場合は空のリストを返す
			list = Collections.emptyList();
		} else {
			// 該当ページに表示させる商品一覧を作成
			int toIndex = Math.min(startItemCount + size, itemList.size());
			list = itemList.subList(startItemCount, toIndex);
		}

		// 上記で作成した該当ページに表示させる商品一覧をページングできる形に変換して返す
		Page<Item> itemPage = new PageImpl<Item>(list, PageRequest.of(page, size), itemList.size());
		return itemPage;
	}

	/**
	 * オートコンプリート用にJavaScriptの配列の中身を文字列で作ります.
	 * 
	 * @param employeeList 従業員一覧
	 * @return オートコンプリート用JavaScriptの配列の文字列
	 */
	public StringBuilder getItemListForAutocomplete(List<Item> itemList) {
		StringBuilder itemListForAutocomplete = new StringBuilder();
		for (int i = 0; i < itemList.size(); i++) {
			if (i != 0) {
				itemListForAutocomplete.append(",");
			}
			Item item = itemList.get(i);
			itemListForAutocomplete.append("\"");
			itemListForAutocomplete.append(item.getName());
			itemListForAutocomplete.append("\"");
		}
		return itemListForAutocomplete;
	}
}
