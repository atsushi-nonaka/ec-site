package com.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文トッピングの情報を表すエンティティ.
 * 
 * @author nonaa
 *
 */
@Data
@NoArgsConstructor
public class OrderTopping {
	/** 注文トッピングID */
	private Integer id;
	/** トッピングID */
	private Integer toppingId;
	/** 注文商品ID */
	private Integer orderItemId;
	/** トッピング */
	private Topping topping;

}

