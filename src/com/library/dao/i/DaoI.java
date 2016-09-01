package com.library.dao.i;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DaoI {

	/**
	 * 增加，修改，删除
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public boolean exec(String sql, Object ... obj) throws SQLException;
	
	/**
	 * 查询
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public List<Map<String, Object>> query(String sql, Object ... obj) throws SQLException;
	
}
