package com.library.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.bean.User;
import com.library.dao.i.DaoI;
import com.library.dao.impl.DaoImpl;
import com.library.service.i.UserServiceI;

public class UserServiceImpl implements UserServiceI{

	private DaoI dao = new DaoImpl();
	
	/**
	 * 用户注册
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

	@Override
	public boolean addManager(User user) throws SQLException {
		return register(user);
	}

	@Override
	public Map<String, Object> managerList(int page, int rows) throws SQLException {
		//得到总条数
		List<Map<String, Object>> countList = dao.query("select count(1) as count from t_user where type=1");
		//计算总页数
		int count = Integer.valueOf(String.valueOf(countList.get(0).get("count")));
		int pages = count / rows + (count % rows == 0 ? 0 : 1);
		//分页
		List<Map<String, Object>> result = dao.query("SELECT * FROM t_user where type=1 limit ?, ?", ((page-1) * rows), rows);
		//遍历集合 封装数据
		List<User> users = new ArrayList<>();
		for (int i = 0; i < result.size(); i++) {
			User tempUser = new User();
			tempUser.setId(Integer.parseInt(String.valueOf(result.get(i).get("id"))));
			tempUser.setUserName(String.valueOf(result.get(i).get("user_name")));
			tempUser.setPassword(String.valueOf(result.get(i).get("password")));
			tempUser.setType(String.valueOf(result.get(i).get("type")));
			tempUser.setCreateTime(String.valueOf(result.get(i).get("create_time")));
			users.add(tempUser);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("pages", pages);
		map.put("users", users);
		return map;
	}

}
