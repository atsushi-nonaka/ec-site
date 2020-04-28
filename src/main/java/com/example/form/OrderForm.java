package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文する際にアップデートするために必要なフォーム.
 * 
 * @author nonaa
 *
 */
@Data
@NoArgsConstructor
public class OrderForm {
	/** UserId */
	private String userId;
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
}
