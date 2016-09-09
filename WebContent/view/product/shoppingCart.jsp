<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>购物车列表</title>
<style type="text/css">
td {
	width: 150px;
}
</style>
</head>
<body>
	<form action="orderServlet?mothed=shoppingCartSaveOrder" method="post">
	<%
		List<Map<String, Object>> products = (List<Map<String, Object>>)request.getAttribute("products");
	%>
		<table>
			<%
				for(int i = 0; i < products.size(); i++){%>
					<tr>
				<td><input type="checkbox" name="ids" value="<%=products.get(i).get("id") %>"></td>
				<td><%=products.get(i).get("book_name") %></td>
				<td>单价:<%=products.get(i).get("price") %></td>
				<td>数量:<%=products.get(i).get("count") %></td>
			</tr>
				<%}
			%>
		</table>
		<input type="submit" value="下单" />
	</form>
</body>
</html>