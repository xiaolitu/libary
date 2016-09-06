package com.library.service.i;

import java.sql.SQLException;
import java.util.Map;

import com.library.bean.Product;

public interface ProductServiceI {

	public boolean addProduct(Product product) throws SQLException;

	public Map<String, Object> productList(int page, int rows) throws SQLException;

	public boolean delManager(String id) throws SQLException;

	public Product updateProductJump(String parameter) throws SQLException;

	public boolean updateProduct(Product product) throws SQLException;
	
}
