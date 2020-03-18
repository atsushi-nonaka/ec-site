package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.LoginForm;
import com.example.service.LoginService;

/**
 * ユーザー登録機能を操作するコントローラー.
 * 
 * @author nonaa
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public LoginForm setUpForm() {
		return new LoginForm();
	}
	
	@RequestMapping("/to_login")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(@Validated LoginForm form, BindingResult result) {
		User user = service.findUserByEmailAndPassword(form.getMailAddress(), form.getPassword());
		if(form.getMailAddress() != null && form.getPassword() != null &&  user == null) {
			result.rejectValue("mailAddress", null, "メールアドレスまたはパスワードが一致しません");
		}
		
		if(result.hasErrors()) {
			return toLogin();
		}
		
		session.setAttribute("user", user);
		return "item_list_curry";
	}
}
