package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * ユーザー情報を格納するローマッパー.
	 */
	private final static RowMapper<User>USER_ROW_MAPPER = (rs, i) ->{
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setAddress(rs.getString("address"));
		user.setPassword(rs.getString("password"));
		user.setTelephone(rs.getString("telephone"));
		user.setZipcode(rs.getString("zipcode"));
		return user;
	};

	/**
	 * ユーザー情報の登録.
	 * 
	 * @param user ユーザー
	 */
	public void insert(User user) {
		String sql = "INSERT INTO users(name, email, password, zipcode, address, telephone) " 
				    + "VALUES(:name, :email, :password, :zipcode, :address, :telephone)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		template.update(sql, param);
	}
	
	/**
	 * メールアドレスからユーザー情報を検索する.
	 * 
	 * @param email メールアドレス
	 * @return ユーザーリスト or null
	 */
	public User findUserByEmail(String email) {
		String sql = "SELECT id, name, email,password, zipcode, address, telephone "
				    + "FROM users WHERE email = :email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if(userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}
	
	/**
	 * メールアドレスとパスワードからユーザー情報を検索する.
	 * 
	 * @param email メールアドレス
	 * @return ユーザーリスト or null
	 */
	public User findUserByEmailAndPassword(String email, String password) {
		String sql = "SELECT id, name, email,password, zipcode, address, telephone "
				    + "FROM users WHERE email = :email AND password = :password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email).addValue("password", password);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if(userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}
	
}