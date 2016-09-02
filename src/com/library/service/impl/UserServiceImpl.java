package com.library.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.library.bean.User;
import com.library.dao.i.DaoI;
import com.library.dao.impl.DaoImpl;
import com.library.service.i.UserServiceI;

public class UserServiceImpl implements UserServiceI{

	private DaoI dao = new DaoImpl();
	
	/**
	 * ÓÃ»§×¢²á
	 * @throws SQLException 
	 */
	@Override
	public boolean register(User user) throws SQLException {
		
		List<Map<String, Object>> result = dao.query("select count(1)"
				+ " as count from t_user where user_name=?", 
				user.getUserName());
		if ("0".equals(String.valueOf(result.get(0).get("count")))) {
			return dao.exec("insert into t_user(user_name,password,type) values(?,?,?)", 
					user.getUserName(),user.getPassword(),user.getType());
		}else{
			return false;
		}
	}

	@Override
	public User login(User user) throws SQLException {
		List<Map<String, Object>> result = dao.query("select * from t_user where user_name=? and password=?", user.getUserName(), user.getPassword());
		if (result.size() > 0) {
			User tempUser = new User();
			tempUser.setId(Integer.parseInt(String.valueOf(result.get(0).get("id"))));
			tempUser.setUserName(String.valueOf(result.get(0).get("user_name")));
			tempUser.setPassword(String.valueOf(result.get(0).get("password")));
			tempUser.setType(String.valueOf(result.get(0).get("type")));
			tempUser.setCreateTime(String.valueOf(result.get(0).get("create_time")));
			return tempUser;
		}
		return null;
	}

}
