package com.library.service.i;

import java.sql.SQLException;

public interface UserServiceI {

	public boolean register(com.library.bean.User user) throws SQLException;
}
