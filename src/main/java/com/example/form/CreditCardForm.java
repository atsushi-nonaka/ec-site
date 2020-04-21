package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

/**
 * クレジットカード情報が入るフォーム.
 * 
 * @author nonaa
 *
 */
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardExpYear() {
		return cardExpYear;
	}

	public void setCardExpYear(String cardExpYear) {
		this.cardExpYear = cardExpYear;
	}

	public String getCardExpMonth() {
		return cardExpMonth;
	}

	public void setCardExpMonth(String cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardCvv() {
		return cardCvv;
	}

	public void setCardCvv(String cardCvv) {
		this.cardCvv = cardCvv;
	}

	@Override
	public String toString() {
		return "CreditCardForm [userId=" + userId + ", orderNumber=" + orderNumber + ", amount=" + amount
				+ ", cardNumber=" + cardNumber + ", cardExpYear=" + cardExpYear + ", cardExpMonth=" + cardExpMonth
				+ ", cardName=" + cardName + ", cardCvv=" + cardCvv + "]";
	}

}
