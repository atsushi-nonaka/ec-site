package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品の詳細リストを表示させるサービス.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class ShowItemDetailService {
	
	@Autowired
	private ItemRepository repository;
	
	/**
	 * 商品の詳細を表す.
	 * 
	 * @param id 商品ID
	 * @return 商品詳細
	 */
	public Item showItemDetail(Integer id) {
		Item item = repository.findById(id);
		return item;
	}
}
