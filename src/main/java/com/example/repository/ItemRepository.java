package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
	
	/**
	 * 商品を名前から検索して表示させる.
	 * 
	 * @param itemName アイテム名
	 * @return アイテムリスト
	 */
	public List<Item> findByName(String itemName){
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted "
					+ "FROM items WHERE name ILIKE :itemName ORDER BY name";
		SqlParameterSource param = new MapSqlParameterSource().addValue("itemName", "%" + itemName + "%");
		List<Item>itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * idごとの商品を表示させる..
	 * 
	 * @return 商品詳細
	 */
	public Item findById(Integer id){
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted "
					+ "FROM items WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}
}
