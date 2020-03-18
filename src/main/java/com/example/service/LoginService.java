package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	/**
	 * ユーザー情報の有無を検索する.
	 * 
	 * @param email メールアドレス
	 * @param password パスワード
	 * @return ユーザーリスト
	 */
	public User findUserByEmailAndPassword(String email, String password){
		User user = repository.findUserByEmailAndPassword(email, password);
		return user;
	}
}
