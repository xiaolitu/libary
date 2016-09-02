package com.library.controller;

import java.io.IOException;
import java.sql.SQLException;

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
			String mothed = req.getParameter("mothed");
			if ("register".equals(mothed)) {
				register(req, resp);
			}else if("login".equals(mothed)){
				login(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 登录
	 * @param req
	 * @param resp
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException{
		String user_name = req.getParameter("user_name");
		String password = req.getParameter("password");
		User user = new User();
		user.setUserName(user_name);
		user.setPassword(password);
		user = userService.login(user);
		if(user != null){
			req.setAttribute("userName", user.getUserName());
			if ("1".equals(user.getType())) {
				req.getRequestDispatcher("view/manager/manager.jsp").forward(req, resp);
			}else{
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}
		}else{
			req.setAttribute("msg", "用户名或密码错误");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
		};
	}
	
	/**
	 * 注册
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException{
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
	}
	
}
