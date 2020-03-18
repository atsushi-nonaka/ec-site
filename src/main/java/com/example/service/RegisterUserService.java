package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.RegisterUserRepository;
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
	private UserRepository repository;

	/**
	 * ユーザー登録するためにレポジトリを呼び出す.
	 * 
	 * @param user ユーザー
	 */
	public void registerUser(User user) {
		repository.insert(user);
	}
	
	/**
	 * メールアドレスからユーザーの有無を確認する.
	 * 
	 * @param email メールアドレス
	 * @return ユーザー情報
	 */
	public List<User> findUserByEmail(String email) {
		List<User> userList = repository.findUserByEmail(email);
		return userList;
	}
}
