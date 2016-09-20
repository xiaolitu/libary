<%@ page language="java" import="java.util.*,com.library.bean.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<style type="text/css">
td {
	padding: 12px;
}
</style>
</head>
<body>
<%
    //从session域中取出指定对象user,用user接收
	User user = (User)request.getSession().getAttribute("user");
	if(user == null){%>
		<div align="right" style="margin-right: 50px"><a href="view/login.jsp">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="view/user/register.jsp">免费注册</a></div>
	<%}else{%>
		<div align="right" style="margin-right: 50px"><label><%=user.getUserName() %></label>&nbsp;&nbsp;<a href="userServlet?mothed=loginOut">退出</a>&nbsp;&nbsp;<a href="orderServlet?mothed=shoppingCartList">我的购物车</a>&nbsp;&nbsp;<a href="orderServlet?mothed=orderList">我的订单</a></div>
	<%}
%>
	<div align="center">
		<table>
			<%
				Map<String, Object> map = (Map<String, Object>) request.getAttribute("map");
				List<Product> books = (List<Product>) map.get("books");
				//总的页数
				int pages = Integer.valueOf(String.valueOf(map.get("pages")));
				//当前页码
				int pageTemp = Integer.valueOf(String.valueOf(map.get("page")));
				for (int i = 0; books != null && i < books.size(); ) {
			%>
			<tr>
				<%
				//设置每一行显示的书本数
					for(int j = 0; j < 3; j++){
						if(i < books.size()){
					%>
				<td align="center"><a href="productServlet?mothed=details&bookId=<%=books.get(i).getId()%>"><img
					src="<%=books.get(i).getCover()%>"></a> <br>
					<label><%=books.get(i).getBookName()%></label> <br> <label><%=books.get(i).getAuthor()%></label>
					<br> <label>¥<%=books.get(i).getPrice()%></label><br> <label>库存<%=books.get(i).getCount()%>本</label></td>
				<%}
						i = i + 1;	
					}
				%>

			</tr>
			<%
				}
			%>

		</table>
	</div>

	<div style="text-align: center; margin-top: 30px">
		<a href="productServlet?mothed=productDisplay&page=1&rows=6">首页</a>

		<%
			if (pageTemp <= 3) {

				for (int i = 2; i < pages && i <= 4; i++) {
		%>
		<a href="productServlet?mothed=productDisplay&page=<%=i%>&rows=6"><%=i%></a>
		<%
			}
			} else if (pages - pageTemp < 3) {
				if (pages - 3 > 1) {
		%>
		<a href="productServlet?mothed=productDisplay&page=<%=pages - 3%>&rows=6"><%=pages - 3%></a>
		<%
			}
		%>
		<a href="productServlet?mothed=productDisplay&page=<%=pages - 2%>&rows=6"><%=pages - 2%></a>
		<a href="productServlet?mothed=productDisplay&page=<%=pages - 1%>&rows=6"><%=pages - 1%></a>

		<%
			} else {
		%>
		<%
			if (pageTemp - 1 > 2) {
		%>
		<a
			href="productServlet?mothed=productDisplay&page=<%=pageTemp - 1%>&rows=6"><%=pageTemp - 1%></a>
		<%
			}
		%>
		<a href="productServlet?mothed=productDisplay&page=<%=pageTemp%>&rows=6"><%=pageTemp%></a>
		<%
			if (pageTemp + 1 < pages) {
		%>
		<a
			href="productServlet?mothed=productDisplay&page=<%=pageTemp + 1%>&rows=6"><%=pageTemp + 1%></a>
		<%
			}
			}
		%>
		<a href="productServlet?mothed=productDisplay&page=<%=pages%>&rows=6">尾页</a>
	</div>
</body>
</html>