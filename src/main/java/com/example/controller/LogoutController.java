package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ログアウトに必要なコントローラー.
 * 
 * @author nonaa
 *
 */
@Controller
@RequestMapping("")
public class LogoutController {
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "forward:/to_login";
	}
}
