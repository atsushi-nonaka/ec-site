package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

/**
 * アイテム商品を操作するレポジトリ.
 * 
 * @author nonaa
 *
 */
@Repository
public class ItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 商品の情報を格納するためのローマッパー.
	 */
	private final static RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) ->{
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDeleted(rs.getBoolean("deleted"));
		return item;
	};
	
	/**
	 * 商品一覧をすべて表示させる.
	 * 
	 * @return 商品一覧リスト
	 */
	public List<Item> findAll(){
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted "
					+ "FROM items ORDER BY name";
		List<Item>itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
}