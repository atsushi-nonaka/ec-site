package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

/**
 * ユーザーを登録するためのレポジトリ.
 * 
 * @author nonaa
 *
 */
@Repository
public class RegisterUserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ユーザー情報の登録.
	 * 
	 * @param user ユーザー
	 */
	public void insert(User user) {
		String sql = "INSERT INTO users(id, name, email, password, zipcode, address, telephone) " 
				    + "VALUES(name = :name, email = :email, password = :password, zipcode = :zipcode, address = :address, telephone = :telephone)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		template.update(sql, param);
	}
}