package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Topping;
import com.example.repository.ToppingRepository;

/**
 * トッピング情報を操作するサービス.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class ShowToppingListService {
	
	@Autowired
	private ToppingRepository repository;
	
	/**
	 * トッピングの全件リストを操作する.
	 * 
	 * @return
	 */
	public List<Topping> findAll(){
		List<Topping> toppingList = repository.findAll();
		return toppingList;
	}
}
