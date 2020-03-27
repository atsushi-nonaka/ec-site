package com.example.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * ログイン情報を格納するドメイン.
 * 
 * @author nonaa
 *
 */
public class LoginUser extends org.springframework.security.core.userdetails.User{
	
	private static final long serialVersionUID = 1L;
	/** ユーザー情報 */
	private final User user;
	
	/**
	 * 通常のログイン情報に加え、認可用ロールを設定する.
	 * 
	 * @param login ログイン情報
	 * @param authorityList　権限情報のリスト
	 */
	public LoginUser(User user, Collection<GrantedAuthority>authorityList) {
		super(user.getEmail(), user.getPassword(), authorityList);
		this.user = user;
	}
	
	public User getLogin() {
		return user;
	}
}
