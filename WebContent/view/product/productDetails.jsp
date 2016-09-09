<%@ page language="java" import="com.library.bean.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
	function cart(){
		$("#flag").val("cart");
		$("#frm").submit();
	}
</script>
</head>
<body>
<div align="center">
<%
	Product product = (Product)request.getAttribute("product");
%>
<form id="frm" action="orderServlet?mothed=saveOrder" method="post">
	<input type="hidden" name="bookId" value="<%=product.getId()%>" />
	<input type="hidden" id="flag" name="flag"/>
	<img src="<%=product.getCover()%>"><br>
	<label><%=product.getBookName()%></lable><br>
	<label><%=product.getAuthor()%></lable><br>
	<label>¥<%=product.getPrice()%></lable><br>
	<label><%=product.getCount()%>本</lable><br>
	<input type="number" name="count" min="1" value="1" max="<%=product.getCount()%>"><br>
	<input type="submit" value="下单" />
	<button onclick="cart()">加入购物车</button><br>
</form>
</div>
</body>
</html>