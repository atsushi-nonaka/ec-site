package com.example.domain;

import java.util.List;

/**
 * 注文商品情報を表すエンティティ.
 * 
 * @author nonaa
 *
 */
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}
	
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
