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

}
