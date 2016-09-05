<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	    <%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改管理员</title>
</head>
<!-- 背景图片平铺效果的代码 -->
<style type="text/css">
body {
	background-image: url(img/2.jpg);
	background-repeat: no-repeat;
	background-size: 100%
} 
</style>
<body>
	<br>
	<br>
	<br>
	<br>
	<center>
		<form action="userServlet?mothed=updateManager" method="post">
			<table width="300" height="200" border="0" cellspacing="0"
				cellpadding="0"
				style="border: 1px solid #ccc; background-color: #a6a3ff">
				<input type="hidden" id="id" name="id" value="<%=request.getParameter("id")%>" />
				<tr>
					<th colspan="2" align="center">修改管理员</th>
				</tr>
				<tr>
					<td align="right">用户名:</td>
					<td><input type="text" name="user_name" value="<%=request.getParameter("user_name")%>"></td>
				</tr>
				<br>
				<tr>
					<td align="right">密码:</td>
					<td><input type="text" id="password" name="password" value="<%=request.getParameter("password")%>"></td>
				</tr>
				<br>
				<tr>
					<td colspan="2" align="center"><input type="submit" class="ti"
						value="修改"></td>
				</tr>
			</table>
	</center>

	</form>
</body>
</html>