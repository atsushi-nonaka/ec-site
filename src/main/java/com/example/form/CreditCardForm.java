package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * クレジットカード情報が入るフォーム.
 * 
 * @author nonaa
 *
 */
@Data
@NoArgsConstructor
public class CreditCardForm {
	/** 利用者ID */
	private String userId;
	/** 注文NO */
	private String orderNumber;
	/** 決済金額 */
	private String amount;
	/** クレジットカード番号 */
	@CreditCardNumber(message = "クレジットカード番号が正しくありません")
	private String cardNumber;
	/** カード有効期限（年） */
	private String cardExpYear;
	/** カード有効期限（月） */
	private String cardExpMonth;
	/** カード名義人 */
	@NotBlank(message = "名前を入力してください")
	@Pattern(regexp = "^[A-Z]$", message = "名前は大文字で入力してください")
	private String cardName;
	/** セキュリティーコード */
	@Size(min = 3, max = 4, message = "セキュリティーコードはAmex以外は3桁(Amexは4桁です)")
	private String cardCvv;

}
