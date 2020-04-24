package com.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * クレジットカードAPIからの返答を格納するドメイン.
 * 
 * @author nonaa
 *
 */
@Data
@NoArgsConstructor
public class CreditCard {
	/** 成功or失敗 */
	private String status;
	/** 有効期限の有無 */
	private String message;
	/** エラーの種類 */
	private String error_code;

}
