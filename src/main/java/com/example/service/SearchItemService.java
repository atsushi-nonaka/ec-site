package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品検索の操作を行うサービス.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class SearchItemService {
	
	@Autowired
	private ItemRepository repository;
	
	/**
	 * 商品名から商品の検索を行う.
	 * 
	 * @param itemName 商品名
	 * @return 商品リスト
	 */
	public List<Item> findItemListByName(String itemName, Model model){
		List<Item> itemList = repository.findByName(itemName);
		if(itemList.size() == 0) {
			itemList = repository.findAllByLowPrice();
		}
		return itemList;
	}
	
	
	/**
	 * 	価格が低い順に表示させる.
	 * 
	 * @return 商品リスト（価格が低い順）
	 */
	public List<Item> findItemListByLowPrice(){
		List<Item> itemList = repository.findAllByLowPrice();
		return itemList;
	}
	
	/**
	 * 	価格が高い順に表示させる.
	 * 
	 * @return 商品リスト（価格が高い順）
	 */
	public List<Item> findItemListByHighPrice(){
		List<Item> itemList = repository.findAllByHighPrice();
		return itemList;
	}

}
