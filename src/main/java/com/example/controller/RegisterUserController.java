package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.service.RegisterUserService;

/**
 * ユーザー登録機能を操作するコントローラー.
 * 
 * @author nonaa
 *
 */
@Controller
@RequestMapping("")
public class RegisterUserController {
	
	@Autowired
	private RegisterUserService service;
	
	@ModelAttribute
	public UserForm setUserForm() {
		return new UserForm();
	}
	
	/**
	 * ユーザー登録画面へ遷移する.
	 * 
	 * @return ユーザー登録画面ページ
	 */
	@RequestMapping("/to_register_user")
	public String toRegister() {
		return "register_user";
	}
	
	/**
	 * ユーザー登録を行う.
	 * 
	 * @param form ユーザーフォーム
	 * @return ログインページ
	 */
	@RequestMapping("/register_user")
	public String registerUser(@Validated UserForm form, BindingResult result) {
		String password = form.getPassword();
		String checkPassword = form.getCheckPassword();
		
		if(!(password.isEmpty()) && !(checkPassword.isEmpty()) && !(password.equals(checkPassword))) {
			result.rejectValue("checkPassword", null, "確認用パスワードが一致していません");
		}
		
		if(service.findUserByEmail(form.getEmail()) != null) {
			result.rejectValue("email", null, "既にそのメールアドレスは存在しています");
		}
		
		if(result.hasErrors()) {
			return toRegister();
		}
		User user = new User();
		BeanUtils.copyProperties(form, user);
		service.registerUser(user);
		return "redirect:/to_login";
	}
}
