package com.library.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.User;
import com.library.service.i.OrderServiceI;
import com.library.service.impl.OrderServiceImpl;

public class OrderServlet extends HttpServlet{

	private OrderServiceI orderService = new OrderServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String mothed = req.getParameter("mothed");
			if ("saveOrder".equals(mothed)) {
				saveOrder(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	private void saveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		
		User user = (User)req.getSession().getAttribute("user");
		if (user == null) {
			req.getRequestDispatcher("test.jsp").forward(req, resp);
			return;
		}
		String userId = String.valueOf(req.getSession().getAttribute("id"));
		String bookId = req.getParameter("bookId");
		String count = req.getParameter("count");
		Boolean b = orderService.saveOrder(user.getId(), bookId, Integer.parseInt(count));
		if (b) {
			req.setAttribute("msg", "下单成功");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
		}
	}
}
