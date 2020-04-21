package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.form.LoginForm;

/**
 * ユーザー登録機能を操作するコントローラー.
 * 
 * @author nonaa
 *
 */
@Controller
public class LoginController {
	
	@ModelAttribute
	public LoginForm setUpForm() {
		return new LoginForm();
	}
	
	@RequestMapping("/to_login")
	public String toLogin(Model model, @RequestParam(required = false) String error) {
		if(error != null) {
			model.addAttribute("mailAddress", "メールアドレスまたはパスワードが不正です");	
		}
		return "login";
	}
}
