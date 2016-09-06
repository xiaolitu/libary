package com.library.service.i;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.library.bean.User;

public interface UserServiceI {

	public boolean register(com.library.bean.User user) throws SQLException;
	
	public User login(com.library.bean.User user) throws SQLException;

	public boolean addManager(User user) throws SQLException;

	public Map<String, Object> managerList(int page, int rows) throws SQLException;

	public boolean delManager(String id) throws SQLException;

	public boolean updateManager(String id, String user_name, String password) throws SQLException;

	public Map<String, Object> userList(int page, int rows) throws SQLException;

	public boolean delUser(String id) throws SQLException;
}
