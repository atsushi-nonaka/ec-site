package com.example.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文商品情報を表すエンティティ.
 * 
 * @author nonaa
 *
 */
@Data
@NoArgsConstructor
public class OrderItem {
	/** 注文情報ID */
	private Integer id;
	/** 商品ID */
	private Integer itemId;
	/** 注文ID */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/** サイズ */
	private String size;
	/** 商品 */
	private Item item;
	/** 注文トッピングリスト */
	private List<OrderTopping> orderToppingList;
	
	/**
	 * 商品の小計を計算する.
	 * 
	 * @return 商品小計
	 */
	public int getSubTotal() {
		int sizePrice = 0;
		int toppingPrice = 0;
		int subTotal = 0;
		int quantity = getQuantity();
		
		if(getSize().equals("M")) {
			sizePrice = getItem().getPriceM();
			if(getOrderToppingList().size() != 0) {
				toppingPrice = getOrderToppingList().size() * 200;
			}
		}else {
			sizePrice = getItem().getPriceL();
			if(getOrderToppingList().size() != 0) {
				toppingPrice = getOrderToppingList().size() * 300;
			}
		}
		subTotal = quantity * (sizePrice + toppingPrice);
		return subTotal;
	}
}
