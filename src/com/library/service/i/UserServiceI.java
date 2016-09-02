package com.library.service.i;

import java.sql.SQLException;

import com.library.bean.User;

public interface UserServiceI {

	public boolean register(com.library.bean.User user) throws SQLException;
	
	public User login(com.library.bean.User user) throws SQLException;
}
