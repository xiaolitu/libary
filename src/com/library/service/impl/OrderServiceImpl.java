package com.library.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.library.bean.Product;
import com.library.dao.i.DaoI;
import com.library.dao.impl.DaoImpl;
import com.library.service.i.OrderServiceI;
import com.library.service.i.ProductServiceI;

public class OrderServiceImpl implements OrderServiceI{

	private DaoI dao = new DaoImpl();
	private ProductServiceI productService = new ProductServiceImpl();
	
	@Override
	public boolean saveOrder(Integer userId, String bookId, Integer count) throws SQLException {
		
		Product product = productService.findProductById(bookId);
		dao.exec("insert into t_order(user_id,price_total) values(?,?)", userId, count * product.getPrice());
		List<Map<String, Object>> orderId = dao.query("select last_insert_id() as id");
		dao.exec("update t_book set count=? where id=?", product.getCount() - count, bookId);
		dao.exec("insert into t_goods(order_id,book_id,count) values(?,?,?)", orderId.get(0).get("id"), bookId, count);
		return true;
	}

	@Override
	public Boolean cart(int userId, String bookId, int count) throws SQLException {

		Product product = productService.findProductById(bookId);
		dao.exec("insert into t_shopping_cart(user_id,book_id,count) values(?,?,?)", userId, bookId, count);
		dao.exec("update t_book set count=? where id=?", product.getCount() - count, bookId);
		return true;
	}

	@Override
	public List<Map<String, Object>> shoppingCartList(int userId) throws SQLException {
		
		return dao.query("SELECT t_shopping_cart.id,book_name,price,t_shopping_cart.count FROM t_shopping_cart,t_book where t_book.id=t_shopping_cart.book_id and user_id=?", userId);
		
	}

	@Override
	public boolean shoppingCartSaveOrder(String[] ids, int userId) throws SQLException {
		
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < ids.length; i++) {
			sBuffer.append(ids[i]).append(",");
		}
		String temp = sBuffer.substring(0, sBuffer.length()-1);
		String sql = "select sum(price) as sum from(SELECT t_shopping_cart.count*t_book.price as price FROM t_shopping_cart,t_book where t_shopping_cart.book_id=t_book.id and user_id="+userId+" and t_shopping_cart.id in ("+temp+")) t";
		List<Map<String, Object>> sum = dao.query(sql);
		
		dao.exec("insert into t_order(user_id,price_total) values("+userId+","+sum.get(0).get("sum")+")");
		List<Map<String, Object>> orderId = dao.query("select last_insert_id() as id");
		
		for (int i = 0; i < ids.length; i++) {
			List<Map<String, Object>> shopping_cart = dao.query("SELECT * FROM t_shopping_cart where id=?", ids[i]);
			dao.exec("insert into t_goods(order_id,book_id,count) values(?,?,?)", orderId.get(0).get("id"), shopping_cart.get(0).get("book_id"), shopping_cart.get(0).get("count"));
			dao.exec("delete from t_shopping_cart where id="+ids[i]);
		}
		
		return true;
		
	}

	@Override
	public List<Map<String, Object>> orderList(int id) throws SQLException {
	
		List<Map<String, Object>> orders = dao.query("select * from t_order where user_id = ?", id);
		for (int i = 0; i < orders.size(); i++) {
			List<Map<String, Object>> goods = dao.query("SELECT book_name,price,t_goods.count FROM t_goods,t_book where t_goods.book_id=t_book.id and order_id = ?", orders.get(i).get("id"));
			orders.get(i).put("goods", goods);
		}
		return orders;
	}

	@Override
	public boolean delOrder(String id) throws SQLException {
		
		dao.exec("delete from t_goods where order_id=?", id);
		dao.exec("delete from t_order where id=?", id);
		
		return true;
	}

}
