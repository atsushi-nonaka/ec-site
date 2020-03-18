package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<Item> showItemList(){
		List<Item> itemList = repository.findAll();
		return itemList;
	}
}
