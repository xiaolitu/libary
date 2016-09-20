<%@ page language="java" import="java.util.*"
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
<title>购物车列表</title>
<style type="text/css">
td {
	width: 150px;
}
</style>
</head>
<body>
	<div align="right" style="margin-right: 50px">
		<a href="#" align:right>回到首页</a>
	</div>
	<form action="orderServlet?mothed=shoppingCartSaveOrder" method="post">
		<%
			List<Map<String, Object>> products = (List<Map<String, Object>>) request.getAttribute("products");
		%>
		<table align="center"
			style="border: 1px solid #ccc; background-color: #ECFFFF">
			<tr>
				<td colspan="4" align="center"><big>我的购物车</big></td>

			</tr>

			<tr>
				<td></td>
				<td>书名</td>
				<td>单价</td>
				<td>数量</td>
			</tr>
			<%
				for (int i = 0; i < products.size(); i++) {
			%>

			<tr>
				<td><input type="checkbox" name="ids"
					value="<%=products.get(i).get("id")%>"></td>
				<td>《<%=products.get(i).get("book_name")%>》
				</td>
				<td>¥<%=products.get(i).get("price")%></td>
				<td><%=products.get(i).get("count")%></td>
			</tr>
			<%
				}
			%>
		</table>
		<center>
			<input type="submit" value="下单" />
		</center>

	</form>
</body>
</html>