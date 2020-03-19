package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Topping;

/**
 * トッピングを操作するレポジトリ.
 * 
 * @author nonaa
 *
 */
@Repository
public class ToppingRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * トッピング情報を格納するローマッパー.
	 */
	private final static RowMapper<Topping>TOPPING_ROW_MAPPER = (rs, i) ->{
		Topping topping = new Topping();
		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceL(rs.getInt("price_l"));
		topping.setPriceM(rs.getInt("price_m"));
		return topping;
	};
	
	/**
	 * トッピングの全件検索を行う.
	 * 
	 * @return トッピングリスト
	 */
	public List<Topping> findAll(){
		String sql = "SELECT id, name, price_l, price_m FROM toppings ORDER BY name";
		List<Topping> toppingList = template.query(sql, TOPPING_ROW_MAPPER);
		return toppingList;
	}
}
