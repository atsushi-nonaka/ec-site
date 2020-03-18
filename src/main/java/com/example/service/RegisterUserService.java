package com.example.service;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> feature/register
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
<<<<<<< HEAD
import com.example.repository.RegisterUserRepository;
=======
import com.example.repository.UserRepository;
>>>>>>> feature/register

/**
 * ユーザー登録するためのサービス.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class RegisterUserService {
<<<<<<< HEAD
	
	@Autowired
	private RegisterUserRepository repository;
	
=======

	@Autowired
	private UserRepository repository;

>>>>>>> feature/register
	/**
	 * ユーザー登録するためにレポジトリを呼び出す.
	 * 
	 * @param user ユーザー
	 */
	public void registerUser(User user) {
		repository.insert(user);
	}
<<<<<<< HEAD
}
=======
	
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
>>>>>>> feature/register
