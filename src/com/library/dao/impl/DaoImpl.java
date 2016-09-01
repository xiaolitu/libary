package com.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.dao.i.DaoI;

public class DaoImpl implements DaoI{

	static Connection conn = null;

	static{
		try {
			Class.forName("com.mysql.jdbc.Driver") ; 
			//获取链接，
			String url = "jdbc:mysql://127.0.0.1:3306/library"
					+ "?useUnicode=true&characterEncoding=UTF-8";
			String userName = "root";//数据库默认的用户名
			String password = ""; //无密码
			conn = java.sql.DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 增加，修改，删除
	 * sql 
	 * obj是一个数组，对应sql里的“？”
	 */
	@Override
	public boolean exec(String sql, Object ... obj) throws SQLException {
		PreparedStatement prepareStatement = null;
		try {
			//创建预处理对象
			prepareStatement = conn.prepareStatement(sql);
			//设置sql里的？
			for (int i = 0; i < obj.length; i++) {
				prepareStatement.setObject((i+1), obj[i]);
			}
			//执sql,返回影响表的行数(比如:insert into 返回1  --> ssize=1)
			int size = prepareStatement.executeUpdate();
			if (size == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw e;
		} finally {
			prepareStatement.close();
		}
	}

	/**
	 * 查询
	 * sql查询语句
	 * obj是一个数组，对应sql里的“？”
	 */
	@Override
	public List<Map<String, Object>> query(String sql, Object ... obj) throws SQLException {
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				prepareStatement.setObject((i+1), obj[i]);
			}
			//执行sql
			ResultSet result = prepareStatement.executeQuery();
			//取得查出的表的字段名
			ResultSetMetaData metaData = result.getMetaData();
			//把取出来的字段存到column里
			List<String> column = new ArrayList<>();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				column.add(metaData.getColumnName(i));
			}
			//list保存sql查询出表的结果
			List<Map<String, Object>> list = new ArrayList<>();
			Map<String, Object> map = null;
			while(result.next()){
				//每行单元格的值
				map = new HashMap<>();
				for (int i = 0; i < column.size(); i++) {
					//     key=表的字段名     value=单元格的值
					map.put(column.get(i), result.getObject((i+1)));
				}
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			throw e;
		}finally {
			prepareStatement.close();
		}
	}

}
