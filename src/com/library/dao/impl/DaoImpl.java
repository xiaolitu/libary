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
			//��ȡ���ӣ�
			String url = "jdbc:mysql://127.0.0.1:3306/library"
					+ "?useUnicode=true&characterEncoding=UTF-8";
			String userName = "root";//���ݿ�Ĭ�ϵ��û���
			String password = ""; //������
			conn = java.sql.DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���ӣ��޸ģ�ɾ��
	 * sql 
	 * obj��һ�����飬��Ӧsql��ġ�����
	 */
	@Override
	public boolean exec(String sql, Object ... obj) throws SQLException {
		PreparedStatement prepareStatement = null;
		try {
			//����Ԥ�������
			prepareStatement = conn.prepareStatement(sql);
			//����sql��ģ�
			for (int i = 0; i < obj.length; i++) {
				prepareStatement.setObject((i+1), obj[i]);
			}
			//ִsql,����Ӱ��������(����:insert into ����1  --> ssize=1)
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
	 * ��ѯ
	 * sql��ѯ���
	 * obj��һ�����飬��Ӧsql��ġ�����
	 */
	@Override
	public List<Map<String, Object>> query(String sql, Object ... obj) throws SQLException {
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				prepareStatement.setObject((i+1), obj[i]);
			}
			//ִ��sql
			ResultSet result = prepareStatement.executeQuery();
			//ȡ�ò���ı���ֶ���
			ResultSetMetaData metaData = result.getMetaData();
			//��ȡ�������ֶδ浽column��
			List<String> column = new ArrayList<>();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				column.add(metaData.getColumnName(i));
			}
			//list����sql��ѯ����Ľ��
			List<Map<String, Object>> list = new ArrayList<>();
			Map<String, Object> map = null;
			while(result.next()){
				//ÿ�е�Ԫ���ֵ
				map = new HashMap<>();
				for (int i = 0; i < column.size(); i++) {
					//     key=����ֶ���     value=��Ԫ���ֵ
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
