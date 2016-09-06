package com.library.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.library.bean.Product;
import com.library.dao.i.DaoI;
import com.library.dao.impl.DaoImpl;
import com.library.service.i.ProductServiceI;
import com.library.util.Contant;

public class ProductServiceImpl implements ProductServiceI{

	private DaoI dao = new DaoImpl();

	@Override
	public boolean addProduct(Product product) throws SQLException {

		return dao.exec("insert into t_book(book_name,price,count,author,cover) "
				+ "values(?,?,?,?,?)", 
				product.getBookName(),
				product.getPrice(),
				product.getCount(),
				product.getAuthor(),
				product.getCover());
	}

	@Override
	public Map<String, Object> productList(int page, int rows) throws SQLException {
		//得到总条数
		List<Map<String, Object>> countList = dao.query("select count(1) as count from t_book");
		//计算总页数
		int count = Integer.valueOf(String.valueOf(countList.get(0).get("count")));
		int pages = count / rows + (count % rows == 0 ? 0 : 1);
		//分页
		List<Map<String, Object>> result = dao.query("SELECT * FROM t_book limit ?, ?", ((page-1) * rows), rows);
		//遍历集合 封装数据
		List<Product> books = new ArrayList<>();
		for (int i = 0; i < result.size(); i++) {
			Product product = new Product();
			product.setId(Integer.parseInt(String.valueOf(result.get(i).get("id"))));
			product.setBookName(String.valueOf(result.get(i).get("book_name")));
			product.setAuthor(String.valueOf(result.get(i).get("author")));
			product.setCount(Integer.parseInt(String.valueOf(result.get(i).get("count"))));
			product.setCover(String.valueOf(result.get(i).get("cover")));
			product.setPrice(Float.parseFloat(String.valueOf(result.get(i).get("price"))));
			product.setCreateTime(String.valueOf(result.get(i).get("create_time")));
			books.add(product);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("pages", pages);
		map.put("books", books);
		return map;
	}

	@Override
	public boolean delManager(String id) throws SQLException {
		List<Map<String, Object>> cover = dao.query("select cover from t_book where id=?", id);
		java.io.File file = new java.io.File(Contant.PATH ,String.valueOf(cover.get(0).get("cover")).replace(",", ""));
		if (file.exists()) {
			file.delete();
		}
		return dao.exec("delete from t_book where id = ?", id);
	}

	@Override
	public Product updateProductJump(String parameter) throws SQLException {
		List<Map<String, Object>> result = dao.query("SELECT * FROM t_book where id = ?", parameter);
		Product product = new Product();
		product.setId(Integer.parseInt(String.valueOf(result.get(0).get("id"))));
		product.setBookName(String.valueOf(result.get(0).get("book_name")));
		product.setAuthor(String.valueOf(result.get(0).get("author")));
		product.setCount(Integer.parseInt(String.valueOf(result.get(0).get("count"))));
		product.setCover(String.valueOf(result.get(0).get("cover")));
		product.setPrice(Float.parseFloat(String.valueOf(result.get(0).get("price"))));
		product.setCreateTime(String.valueOf(result.get(0).get("create_time")));
		return product;
	}

	@Override
	public boolean updateProduct(Product product) throws SQLException {
		if (product.getCover() == null || product.getCover().trim().length() == 0) {
			return dao.exec("update t_book set book_name=?,author=?,count=?,price=? where id=?", product.getBookName(),
					product.getAuthor(),
					product.getCount(),
					product.getPrice(),
					product.getId());
		}else{
			
			List<Map<String, Object>> cover = dao.query("select cover from t_book where id=?", product.getId());
			java.io.File file = new java.io.File(Contant.PATH ,String.valueOf(cover.get(0).get("cover")).replace(",", ""));
			if (file.exists()) {
				file.delete();
			}
			return dao.exec("update t_book set book_name=?,author=?,count=?,price=?,cover=? where id=?", product.getBookName(),
					product.getAuthor(),
					product.getCount(),
					product.getPrice(),
					product.getCover(),
					product.getId());
		}
		
	}

}
