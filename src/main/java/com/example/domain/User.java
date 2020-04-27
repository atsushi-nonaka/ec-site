package com.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー情報を表すエンティティ.
 * 
 * @author nonaa
 *
 */
@Data
@NoArgsConstructor
public class User {
	/** UserID */
	private Integer id;
	/** ユーザー名 */
	private String name;
	/** Eメール */
	private String email;
	/** パスワード */
	private String password;
	/** 郵便番号 */
	private String zipcode;
	/** 住所 */
	private String address;
	/** 電話番号 */
	private String telephone;
	/** 権利者権限 */
	private Boolean admin; 

}
