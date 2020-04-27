package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー情報を格納するためのフォーム.
 * 
 * @author nonaa
 *
 */
@Data
@NoArgsConstructor
public class UserForm {
	/** ユーザー名 */
	@NotBlank(message = "お名前を入力してください")
	private String name;
	/** Eメール */
	@Email(message = "メールの形式が正しくないです")
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;
	/** パスワード */
	@Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)[a-zA-Z\\d]{8,32}$", message="8桁以上32桁以外で設定してください")
	private String password;
	/** 確認用パスワード */
	@NotBlank(message = "確認用パスワードを入力してください")
	private String checkPassword;
	/** 郵便番号 */
	@NotBlank(message = "郵便番号を入力してください")
	private String zipcode;
	/** 住所 */
	@NotBlank(message = "住所を入力してください")
	private String address;
	/** 電話番号 */
	@NotBlank(message = "電話番号を入力してください")
	private String telephone;
}
