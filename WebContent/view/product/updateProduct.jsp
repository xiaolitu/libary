<%@ page language="java" import="com.library.bean.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<%
	Product product = (Product)request.getAttribute("product");
	%>
	<center>
		<form action="productServlet?mothed=updateProduct" method="post"  enctype="multipart/form-data">
			<input type="hidden" id="id" name="id" value="<%=product.getId() %>" />
			<table width="300" height="200" border="0" cellspacing="0"
				cellpadding="0"
				style="border: 1px solid #ccc; background-color: #a6a3ff">
				<tr>
					<th colspan="2" align="center">修改库存</th>
				</tr>
				<tr>
					<td align="right">书名:</td>
					<td><input type="text" name="book_name" value="<%=product.getBookName() %>"></td>
				</tr>
				<tr>
					<td align="right">价格:</td>
					<td><input type="text" id="price" name="price" value="<%=product.getPrice() %>"></td>
				</tr>
				<tr>
					<td align="right">数量:</td>
					<td><input type="text" id="count" name="count" value="<%=product.getCount() %>"></td>
				</tr>
				<tr>
					<td align="right">作者:</td>
					<td><input type="text" id="author" name="author" value="<%=product.getAuthor() %>"></td>
				</tr>
				<tr>
					<td align="right">封面:</td>
					<td><input type="file" id="cover" name="cover" accept=".jpg,.png,.gif" multiple="multiple"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" class="ti"
						value="添加"></td>
				</tr>
			</table>
	</center>

	</form>
</body>
</html>