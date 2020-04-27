package com.example.form;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文情報を格納するフォーム.
 * 
 * @author nonaa
 *
 */
@Data
@NoArgsConstructor
public class OrderItemForm {
	/** 商品ID */
	private String itemId;
	/** 数量 */
	private String quantity;
	/** 注文番号 */
	private Integer orderNumber;
	/** サイズ */
	private String size;
	/** トッピングリスト */
	private List<String> toppingIdList;
}
