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
    <div align="right" style="margin-right: 50px"><a href="#" align:right>回到首页</a></div>
	<center><big>我的订单</big></center>
	
	<%
		List<Map<String, Object>> orders = (List<Map<String, Object>>) request.getAttribute("orders");

		for (int i = 0; i < orders.size(); i++) {
	%>

	<table align="center" style="border:1px solid #ccc; background-color:#ECFFFF">
	<tr>
			<td>书名</td>
			<td>单价</td>
			<td>数量</td>
			<td>创建时间：</td>
			<td>总价</td>
			<td></td>
		</tr> 
		<%
			List<Map<String, Object>> goods = (List<Map<String, Object>>) orders.get(i).get("goods");
				for (int j = 0; j < goods.size(); j++) {
		%>
		<tr>
			<td><%=goods.get(j).get("book_name")%></td>
			<td><%=goods.get(j).get("price")%></td>
			<td><%=goods.get(j).get("count")%></td>
			<td><%=orders.get(i).get("create_time")%></td>
			<td>¥<%=orders.get(i).get("price_total")%></td>
			<td><a href="orderServlet?mothed=delOrder&id=<%=orders.get(i).get("id") %>">删除</a></td>
		</tr>
		<%
			}
			}
		%>

	</table>
	<center><input type="submit" value="去结算" /></center>
</body>
</html>