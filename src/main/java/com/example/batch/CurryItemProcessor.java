package com.example.batch;

import org.springframework.batch.item.ItemProcessor;

import com.example.domain.Item;

public class CurryItemProcessor implements ItemProcessor<Item, Item>{
	
	@Override
	public Item process(Item item) throws Exception{
		String name = item.getName().toUpperCase();
		String description = item.getDescription().toUpperCase();
		Integer priceL = item.getPriceL();
		Integer priceM = item.getPriceM();
		String imagePath = item.getImagePath();
		Boolean deleted = item.getDeleted();
		
		Item transformColumns = new Item(name, description, priceM, priceL, imagePath, deleted);
		return transformColumns;
	}
}
