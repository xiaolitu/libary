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
<title>库存列表</title>
<style type="text/css">
td {
	padding: 12px;
}
</style>
</head>
<body>
	<div style="text-align: center;">
		<h1>库存列表</h1>
		<table align="center" border="1px" cellspacing="0">
			<tr>
				<td>ID</td>
				<td>书名</td>
				<td>价格</td>
				<td>数量</td>
				<td>作者</td>
				<td>封面</td>
				<td>创建时间</td>
				<td>操作</td>
			</tr>
			<%
			Map<String, Object> map = (Map<String, Object>)request.getAttribute("map");
			List<Product> books = (List<Product>)map.get("books");
			//总的页数
			int pages = Integer.valueOf(String.valueOf(map.get("pages")));
			//当前页码
			int pageTemp = Integer.valueOf(String.valueOf(map.get("page")));
			for(int i = 0; books!= null && i < books.size(); i++){%>
			<tr>
				<td><%=books.get(i).getId() %></td>
				<td><%=books.get(i).getBookName() %></td>
				<td><%=books.get(i).getPrice() %></td>
				<td><%=books.get(i).getCount() %></td>
				<td><%=books.get(i).getAuthor() %></td>
				<td><%=books.get(i).getCover() %></td>
				<td><%=books.get(i).getCreateTime() %></td>
				<td><a href="productServlet?mothed=delProduct&id=<%=books.get(i).getId() %>">删除</a>&nbsp;<a href="productServlet?mothed=updateProductJump&id=<%=books.get(i).getId() %>">修改</a></td>
			</tr>
			<%}
			%>

		</table>
	</div>
	<div style="text-align: center; margin-top: 30px">
		<a href="userServlet?mothed=productList&page=1&rows=3">首页</a>

		<%
			if(pageTemp <= 3){
				
				for(int i = 2; i < pages && i <= 4; i++){%>
		<a href="userServlet?mothed=productList&page=<%=i %>&rows=3"><%=i %></a>
		<%}
			}else if(pages - pageTemp < 3){
				if(pages-3 > 1){%>
					<a href="userServlet?mothed=productList&page=<%=pages-3 %>&rows=3"><%=pages-3 %></a>
				<%}
				%>
					<a href="userServlet?mothed=productList&page=<%=pages-2 %>&rows=3"><%=pages-2 %></a>
					<a href="userServlet?mothed=productList&page=<%=pages-1 %>&rows=3"><%=pages-1 %></a>
					
					<%
			}else{
		%>
		<%
			if(pageTemp - 1 > 2){%>
		<a
			href="userServlet?mothed=productList&page=<%=pageTemp - 1 %>&rows=3"><%=pageTemp - 1 %></a>
		<%}
		%>
		<a href="userServlet?mothed=productList&page=<%=pageTemp %>&rows=3"><%=pageTemp %></a>
		<%
			if(pageTemp + 1 < pages){%>
		<a
			href="userServlet?mothed=productList&page=<%=pageTemp + 1 %>&rows=3"><%=pageTemp + 1 %></a>
		<%}
			}
		%>
		<a href="userServlet?mothed=productList&page=<%=pages %>&rows=3">尾页</a>
	</div>
</body>
</html>