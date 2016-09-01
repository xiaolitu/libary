package com.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.User;
import com.library.service.i.UserServiceI;
import com.library.service.impl.UserServiceImpl;
import com.library.util.StringUtil;

/**
 * 用户模块
 * @author Administrator
 *
 */
public class UserServlet extends HttpServlet{

	private UserServiceI userService = new UserServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
			String user_name = req.getParameter("user_name");
			String password = req.getParameter("password");
			String password_again = req.getParameter("password_again");
			if (StringUtil.isEmpty(user_name) || StringUtil.isEmpty(password)) {
				req.setAttribute("msg", "用户名或密码不能为空");
				req.getRequestDispatcher("view/error.jsp").forward(req, resp);
				return;
			}
			if (!password.equals(password_again)) {
				req.setAttribute("msg", "密码不一致");
				req.getRequestDispatcher("view/error.jsp").forward(req, resp);
				return;
			}
			User user = new User();
			user.setUserName(user_name);
			user.setPassword(password);
			user.setType("0");
			boolean b = userService.register(user);
			if (b) {
				req.setAttribute("userName", user.getUserName());
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}else{
				req.setAttribute("msg", "用户名已存在");
				req.getRequestDispatcher("view/error.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
