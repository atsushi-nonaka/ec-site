package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 注文する際にアップデートするために必要なフォーム.
 * 
 * @author nonaa
 *
 */
public class OrderForm {
	/** 宛先氏名 */
	@NotBlank(message = "名前が未入力です")
	private String destinationName;
	/** 宛先Eメール */
	@NotBlank(message = "メールアドレスが未入力です")
	@Email(message = "メールの形式が正しくありません")
	private String destinationEmail;
	/** 宛先郵便番号 */
	@NotBlank(message = "郵便番号が未入力です")
	private String destinationZipcode;
	/** 宛先住所 */
	@NotBlank(message = "住所が未入力です")
	private String destinationAddress;
	/** 宛先TEL */
	@NotBlank(message = "電話番号が未入力です")
	private String destinationTel;
	/** 配達日 */
	@NotBlank(message = "配達日が未入力です")
	private String deliveryDate;
	/** 配達時間 */
	@NotBlank(message = "配達時間が未入力です")
	private String deliveryTime;
	/** 支払方法 */
	@NotBlank(message = "支払方法が未入力です")
	private String paymentMethod;

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationTel() {
		return destinationTel;
	}

	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "OrderForm [destinationName=" + destinationName + ", destinationEmail=" + destinationEmail
				+ ", destinationZipcode=" + destinationZipcode + ", destinationAddress=" + destinationAddress
				+ ", destinationTel=" + destinationTel + ", deliveryDate=" + deliveryDate + ", deliveryTime="
				+ deliveryTime + ", paymentMethod=" + paymentMethod + "]";
	}

}
