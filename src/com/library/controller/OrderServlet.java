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
import com.library.service.impl.UserServiceImpl;

public class OrderServlet extends HttpServlet{

	private OrderServiceI orderService = new OrderServiceImpl();
	private UserServiceImpl userService = new UserServiceImpl();
	
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
			}if ("orderList".equals(mothed)) {
				orderList(req, resp);
			}if ("delOrder".equals(mothed)) {
				delOrder(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	private void orderList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		User user = (User)req.getSession().getAttribute("user");
		if (user == null) {
			//����û�Ϊ�գ��������ҳ
			req.getRequestDispatcher("test.jsp").forward(req, resp);
			return;
		}
		req.setAttribute("orders", orderService.orderList(user.getId()));
		req.getRequestDispatcher("view/product/orderList.jsp").forward(req, resp);
	}

	private void shoppingCartSaveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		User user = (User)req.getSession().getAttribute("user");
		if (user == null) {
			//����û�Ϊ�գ��������ҳ
			req.getRequestDispatcher("test.jsp").forward(req, resp);
			return;
		}
		String[] ids = req.getParameterValues("ids");
		if (ids.length == 0) {
			req.setAttribute("msg", "��ѡ������");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
			return;
		}
		boolean b = orderService.shoppingCartSaveOrder(ids, user.getId());
		if (b) {
			req.setAttribute("orders", orderService.orderList(user.getId()));
			req.getRequestDispatcher("view/product/orderList.jsp").forward(req, resp);
		}
	}
 //���ﳵ�б���
	private void shoppingCartList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		User user = (User)req.getSession().getAttribute("user");
		if (user == null) {
			req.getRequestDispatcher("test.jsp").forward(req, resp);
			return;
		}
		List<Map<String, Object>> products = orderService.shoppingCartList(user.getId());
		req.setAttribute("products", products);
		req.getRequestDispatcher("view/product/shoppingCart.jsp").forward(req, resp);
		//req.getRequestDispatcher("view/product/orderList.jsp").forward(req, resp);
	}

	private void saveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		
		//���Ϊture������������빺�ﳵ
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
			req.setAttribute("msg", "�µ��ɹ�");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
		}
	}

	private void cart(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException, SQLException, ServletException, IOException {
		User user = (User)req.getSession().getAttribute("user");
		if (user == null) {
			req.getRequestDispatcher("test.jsp").forward(req, resp);
			return;
		}
		int userId = user.getId();
		String bookId = req.getParameter("bookId");
		String count = req.getParameter("count");
		Boolean b = orderService.cart(user.getId(), bookId, Integer.parseInt(count));
		if (b) {
			req.setAttribute("msg", "�Ѽ��빺�ﳵ");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
		}
	}
	
	//���ﳵ�µ������ҵĶ����б�
	private void cartList(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException, SQLException, ServletException, IOException {
		User user = (User)req.getSession().getAttribute("user");
		if (user == null) {
			req.getRequestDispatcher("test.jsp").forward(req, resp);
			return;
		}
		int userId = user.getId();
		String bookId = req.getParameter("bookId");
		String count = req.getParameter("count");
		Boolean b = orderService.cart(user.getId(), bookId, Integer.parseInt(count));
		if (b) {
			//req.setAttribute("msg", "�Ѽ��빺�ﳵ");
			req.getRequestDispatcher("view/product/orderList.jsp").forward(req, resp);
		}
	}
	//ɾ�������ķ���
	public void delOrder(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException{
		String id = req.getParameter("id");
		if (orderService.delOrder(id)) {
			User user = (User) req.getSession().getAttribute("user");
			req.setAttribute("orders", orderService.orderList(user.getId()));
			req.getRequestDispatcher("view/product/orderList.jsp").forward(req, resp);
		}else{
			req.setAttribute("msg", "����ʧ��");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
		}
	}
	
}
