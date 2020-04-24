package com.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * トッピング情報を表すエンティティ.
 * 
 * @author nonaa
 *
 */
@Data
@NoArgsConstructor
public class Topping {
	/** トッピングID */
	private Integer id;
	/** トッピング名 */
	private String name;
	/** Mサイズの価格 */
	private Integer priceM;
	/** Lサイズの価格 */
	private Integer priceL;

}
