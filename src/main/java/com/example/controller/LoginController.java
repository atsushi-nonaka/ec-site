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
	
//	@RequestMapping("/login")
//	public String login(@Validated LoginForm form, BindingResult result) {
//		User user = service.findUserByEmailAndPassword(form.getMailAddress(), form.getPassword());
//		if(form.getMailAddress() != null && form.getPassword() != null &&  user == null) {
//			result.rejectValue("mailAddress", null, "メールアドレスまたはパスワードが一致しません");
//		}
//		
//		if(result.hasErrors()) {
//			return toLogin();
//		}
//		
//		session.setAttribute("userId", user.getId());
//		return "forward:/";
//	}
}
