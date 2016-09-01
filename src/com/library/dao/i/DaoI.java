package com.library.dao.i;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DaoI {

	/**
	 * ���ӣ��޸ģ�ɾ��
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public boolean exec(String sql, Object ... obj) throws SQLException;
	
	/**
	 * ��ѯ
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public List<Map<String, Object>> query(String sql, Object ... obj) throws SQLException;
	
}
