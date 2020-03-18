package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<Item> findItemListByName(String itemName){
		List<Item> itemList = repository.findByName(itemName);
		if(itemList.size() == 0) {
			itemList = repository.findAll();
			return  itemList;
		}
		return itemList;
	}
}
