package com.example.form;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 検索結果の情報を格納するフォーム.
 * 
 * @author nonaa
 *
 */
@Data
@NoArgsConstructor
public class ItemForm {
	private String name;
	private String highLow;
}
