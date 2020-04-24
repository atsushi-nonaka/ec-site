package com.example.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品情報を表すエンティティ.
 * 
 * @author nonaa
 *
 */
@Data
@NoArgsConstructor
public class Item {
	/** 商品ID */
	private Integer id;
	/** 商品名 */
	private String name;
	/** 商品説明 */
	private String description;
	/** Mサイズの価格 */
	private Integer priceM;
	/** Mサイズの価格 */
	private Integer priceL;
	/** 商品画像パス */
	private String imagePath;
	/** 削除フラグ */
	private Boolean deleted;
	/** トッピングリスト */
	private List<Topping> toppingList;

	/**
	 * @param name
	 * @param description
	 * @param priceM
	 * @param priceL
	 * @param imagePath
	 * @param deleted
	 * @param toppingList
	 */
	public Item(String name, String description, Integer priceM, Integer priceL, String imagePath, Boolean deleted) {
		this.name = name;
		this.description = description;
		this.priceM = priceM;
		this.priceL = priceL;
		this.imagePath = imagePath;
		this.deleted = deleted;
	}

}
