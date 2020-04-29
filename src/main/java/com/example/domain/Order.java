package com.example.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文情報を表すエンティティ.
 * 
 * @author nonaa
 *
 */
@Data
@NoArgsConstructor
public class Order {
	/** 注文番号 */
	private Long orderNumber;
	/** UserID */
	private Integer userId;
	/**
	 * 状態( 0:注文前 1:未入金 2:入金済 3:発送済 4:発送完了 9:キャンセル )
	 */
	private Integer status;
	/** 合計金額 */
	private Integer totalPrice;
	/** 注文日 */
	private Date orderDate;
	/** 宛先氏名 */
	private String destinationName;
	/** 宛先Eメール */
	private String destinationEmail;
	/** 宛先郵便番号 */
	private String destinationZipcode;
	/** 宛先住所 */
	private String destinationAddress;
	/** 宛先TEL */
	private String destinationTel;
	/** 配達時間 */
	private Timestamp deliveryTime;
	/** 支払方法 */
	private Integer paymentMethod;
	/** ユーザー */
	private User user;
	/** 注文商品リスト */
	private List<OrderItem> orderItemList;

	/**
	 * 商品合計の税金の合計を計算する.
	 * 
	 * @return 消費税の合計金額
	 */
	public int getTax() {
//		int totalTax = 0;
		List<OrderItem> orderItemList = getOrderItemList();
//		for (OrderItem orderItem : orderItemList) {
//			totalTax += (int) (orderItem.getSubTotal() * 0.1);
//		}
		int totalTax = orderItemList.stream()
											.mapToInt(oi -> (int)(oi.getSubTotal() * 0.1))
											.sum();
		return totalTax;
	}

	/**
	 * 商品合計の金額を計算する.
	 * 
	 * @return 商品の合計金額
	 */
	public int getCalcTotalPrice() {
//		int subTotalPrice = 0;
		List<OrderItem> orderItemList = getOrderItemList();
//		for (OrderItem orderItem : orderItemList) {
//			subTotalPrice += orderItem.getSubTotal();
//		}
		int subTotalPrice = orderItemList.stream()
												.mapToInt(oi -> oi.getSubTotal())
												.sum();
		return subTotalPrice + getTax();
	}
}
