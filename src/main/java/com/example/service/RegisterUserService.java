package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * ユーザー登録するためのサービス.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class RegisterUserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository repository;

	/**
	 * ユーザー登録するためにレポジトリを呼び出す.
	 * 
	 * @param user ユーザー
	 */
	public void registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); 
		repository.insert(user);
	}
	
	/**
	 * メールアドレスからユーザーの有無を確認する.
	 * 
	 * @param email メールアドレス
	 * @return ユーザー情報
	 */
	public User findUserByEmail(String email) {
		User user = repository.findUserByEmail(email);
		return user;
	}
}
