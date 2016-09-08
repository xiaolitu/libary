package com.library.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.User;
import com.library.service.i.ProductServiceI;
import com.library.service.i.UserServiceI;
import com.library.service.impl.ProductServiceImpl;
import com.library.service.impl.UserServiceImpl;
import com.library.util.StringUtil;

/**
 * 用户模块
 * @author Administrator
 *
 */
public class UserServlet extends HttpServlet{
	
	private ProductServiceI productService = new ProductServiceImpl();
	private UserServiceI userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
			String mothed = req.getParameter("mothed");
			if ("register".equals(mothed)) {
				register(req, resp);
			}else if("userList".equals(mothed)){
				userList(req, resp);
			}else if("delUser".equals(mothed)){
				delUser(req, resp);
			}else if("login".equals(mothed)){
				login(req, resp);
			}else if("addManager".equals(mothed)){
				addManager(req, resp);
			}else if("managerList".equals(mothed)){
				managerList(req, resp);
			}else if("delManager".equals(mothed)){
				delManager(req, resp);
			}else if("updateManager".equals(mothed)){
				updateManager(req, resp);
			}else if("loginOut".equals(mothed)){
				loginOut(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loginOut(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		req.getSession().removeAttribute("userName");
		req.getRequestDispatcher("test.jsp").forward(req, resp);
	}


	private void delUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		String id = req.getParameter("id");
		if (userService.delUser(id)) {
			Map<String, Object> map = userService.userList(1, 3);
			map.put("page", 1);
			req.setAttribute("map", map);
			req.getRequestDispatcher("view/user/userList.jsp").forward(req, resp);
		}else{
			req.setAttribute("msg", "操作失败");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
		}
		
	}


	private void userList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		int page = Integer.valueOf(req.getParameter("page"));
		int rows = Integer.valueOf(req.getParameter("rows"));
		if (page < 1) {
			page = 1;
		}
		if (rows < 1) {
			rows = 3;
		}
		Map<String, Object> map = userService.userList(page, rows);
		map.put("page", page);
		req.setAttribute("map", map);
		req.getRequestDispatcher("view/user/userList.jsp").forward(req, resp);
	}


	public void updateManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException{
		String id = req.getParameter("id");
		String user_name = req.getParameter("user_name");
		String password = req.getParameter("password");
		if (id == null) {
			req.setAttribute("msg", "参数错误");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
		}
		if (userService.updateManager(id, user_name, password)) {
			Map<String, Object> map = userService.managerList(1, 3);
			map.put("page", 1);
			req.setAttribute("map", map);
			req.getRequestDispatcher("view/manager/managerList.jsp").forward(req, resp);
		}else{
			req.setAttribute("msg", "操作失败,可能是已存此用户");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
		}
		
	}
	
	public void delManager(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException{
		String id = req.getParameter("id");
		if (userService.delManager(id)) {
			Map<String, Object> map = userService.managerList(1, 3);
			map.put("page", 1);
			req.setAttribute("map", map);
			req.getRequestDispatcher("view/manager/managerList.jsp").forward(req, resp);
		}else{
			req.setAttribute("msg", "操作失败");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
		}
	}
	
	/**
	 * 管理员列表
	 * @param req
	 * @param resp
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void managerList(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException{
		int page = Integer.valueOf(req.getParameter("page"));
		int rows = Integer.valueOf(req.getParameter("rows"));
		if (page < 1) {
			page = 1;
		}
		if (rows < 1) {
			rows = 3;
		}
		Map<String, Object> map = userService.managerList(page, rows);
		map.put("page", page);
		req.setAttribute("map", map);
		req.getRequestDispatcher("view/manager/managerList.jsp").forward(req, resp);
	}
	
	/**
	 * 添加管理员
	 * @param req
	 * @param resp
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addManager(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException{
		String user_name = req.getParameter("user_name");
		String password = req.getParameter("password");
		User user = new User();
		user.setUserName(user_name);
		user.setPassword(password);
		user.setType("1");
		if (user.getUserName() == null || user.getUserName().trim().length() == 0) {
			req.setAttribute("msg", "参数有误");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
			return;
		}
		if (userService.addManager(user)) {
			req.setAttribute("msg", "操作成功");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
		}else{
			req.setAttribute("msg", "添加失败");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
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
			req.getSession().setAttribute("user", user);
			if ("1".equals(user.getType())) {
				req.getRequestDispatcher("view/manager/manager.jsp").forward(req, resp);
			}else{
				Map<String, Object> map = productService.productList(1, 6);
				map.put("page", 1);
				req.setAttribute("map", map);
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
			req.getSession().setAttribute("user", user);
			if ("1".equals(user.getType())) {
				req.getRequestDispatcher("view/manager/manager.jsp").forward(req, resp);
			}else{
				Map<String, Object> map = productService.productList(1, 6);
				map.put("page", 1);
				req.setAttribute("map", map);
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}
		}else{
			req.setAttribute("msg", "用户名已存在");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
		}
	}
	
}
