package com.library.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.library.dao.impl.DaoImpl;

public class Test {

	public static void main(String[] args) throws SQLException {
		DaoImpl daoImpl = new DaoImpl();
		List<Map<String, Object>> re = daoImpl.query("select * from t_user");
		re.get(0).get("user_name");
	}
	
}
