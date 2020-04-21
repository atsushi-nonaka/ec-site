package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * ログイン情報を操作するサービス.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class LoginService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * ユーザー情報の有無を検索する.
	 * 
	 * @param email メールアドレス
	 * @param password パスワード
	 * @return ユーザーリスト
	 */
	public User findUserByEmailAndPassword(String email, String password){
		User user = null;
		if(repository.findUserByEmail(email) == null) {
			return null;
		}
		
		String hashedPassowrd = repository.findUserByEmail(email).getPassword();
		
		if(passwordEncoder.matches(password, hashedPassowrd)) {
			user = repository.findUserByEmailAndPassword(email, hashedPassowrd);			
		}
		return user;
	}
}
