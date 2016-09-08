package com.library.service.i;

import java.sql.SQLException;

public interface OrderServiceI {

	public boolean saveOrder(Integer userId, String bookId, Integer count) throws SQLException;
	
}
