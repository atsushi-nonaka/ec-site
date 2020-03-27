package com.example.form;

import java.util.List;

/**
 * 注文情報を格納するフォーム.
 * 
 * @author nonaa
 *
 */
public class OrderItemForm {
	/** 商品ID */
	private String itemId;
	/** 数量 */
	private String quantity;
	/** サイズ */
	private String size;
	/** トッピングリスト */
	private List<String> toppingIdList;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<String> getToppingIdList() {
		return toppingIdList;
	}

	public void setToppingIdList(List<String> toppingIdList) {
		this.toppingIdList = toppingIdList;
	}

	@Override
	public String toString() {
		return "OrderItemForm [itemId=" + itemId + ", quantity=" + quantity + ", size=" + size + ", toppingIdList="
				+ toppingIdList + "]";
	}

}
