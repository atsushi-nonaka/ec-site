package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.RegisterUserRepository;

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
	private RegisterUserRepository repository;

	/**
	 * ユーザー登録するためにレポジトリを呼び出す.
	 * 
	 * @param user ユーザー
	 */
	public void registerUser(User user) {
		repository.insert(user);
	}
}