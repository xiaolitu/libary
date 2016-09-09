package com.library.service.i;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface OrderServiceI {

	public boolean saveOrder(Integer userId, String bookId, Integer count) throws SQLException;

	public Boolean cart(int id, String bookId, int parseInt) throws SQLException;

	public List<Map<String, Object>> shoppingCartList(int userId) throws SQLException;

	public boolean shoppingCartSaveOrder(String[] ids, int userId) throws SQLException;
	
}
