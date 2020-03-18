package com.example.form;

<<<<<<< HEAD
=======
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

>>>>>>> feature/register
/**
 * ユーザー情報を格納するためのフォーム.
 * 
 * @author nonaa
 *
 */
public class UserForm {
	/** ユーザー名 */
<<<<<<< HEAD
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
=======
	@NotBlank(message = "お名前を入力してください")
	private String name;
	/** Eメール */
	@Email(message = "メールの形式が正しくないです")
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;
	/** パスワード */
	@NotBlank(message = "パスワードを入力してください")
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
>>>>>>> feature/register
	private String telephone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

<<<<<<< HEAD
	@Override
	public String toString() {
		return "UserForm [name=" + name + ", email=" + email + ", password=" + password + ", zipcode=" + zipcode
				+ ", address=" + address + ", telephone=" + telephone + "]";
	}

}
=======
	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	@Override
	public String toString() {
		return "UserForm [name=" + name + ", email=" + email + ", password=" + password + ", checkPassword="
				+ checkPassword + ", zipcode=" + zipcode + ", address=" + address + ", telephone=" + telephone + "]";
	}

}
>>>>>>> feature/register
