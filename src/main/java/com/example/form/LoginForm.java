package com.example.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ログインするために必要なフォーム.
 * 
 * @author nonaa
 *
 */
@Data
@NoArgsConstructor
public class LoginForm {
	/** メールアドレス */
	@NotBlank(message="メールアドレスを入力してください")
	private String mailAddress;
	/** パスワード */
	@NotBlank(message="パスワードを入力してください")
	private String password;
}
