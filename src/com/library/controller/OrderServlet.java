package com.library.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.Product;
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
			}if ("shoppingCartList".equals(mothed)) {
				shoppingCartList(req, resp);
			}if ("shoppingCartSaveOrder".equals(mothed)) {
				shoppingCartSaveOrder(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	private void shoppingCartSaveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		User user = (User)req.getSession().getAttribute("user");
		if (user == null) {
			req.getRequestDispatcher("test.jsp").forward(req, resp);
			return;
		}
		String[] ids = req.getParameterValues("ids");
		if (ids.length == 0) {
			req.setAttribute("msg", "请选择数据");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
			return;
		}
		boolean b = orderService.shoppingCartSaveOrder(ids, user.getId());
		if (b) {
			req.setAttribute("msg", "下单成功");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
			return;
		}
	}

	private void shoppingCartList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		User user = (User)req.getSession().getAttribute("user");
		if (user == null) {
			req.getRequestDispatcher("test.jsp").forward(req, resp);
			return;
		}
		List<Map<String, Object>> products = orderService.shoppingCartList(user.getId());
		req.setAttribute("products", products);
		req.getRequestDispatcher("view/product/shoppingCart.jsp").forward(req, resp);
	}

	private void saveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		
		//如果为ture，点代表点击加入购物车
		if ("cart".equals(req.getParameter("flag"))) {
			cart(req, resp);
			return;
		}
		
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

	private void cart(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException, SQLException, ServletException, IOException {
		User user = (User)req.getSession().getAttribute("user");
		if (user == null) {
			req.getRequestDispatcher("test.jsp").forward(req, resp);
			return;
		}
		String userId = String.valueOf(req.getSession().getAttribute("id"));
		String bookId = req.getParameter("bookId");
		String count = req.getParameter("count");
		Boolean b = orderService.cart(user.getId(), bookId, Integer.parseInt(count));
		if (b) {
			req.setAttribute("msg", "已加入购物车");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
		}
	}
}
