<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎页面</title>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<%
	String userName = (String)request.getAttribute("userName");
	if(userName == null){%>
		<div align="right" style="margin-right: 50px"><a href="view/login.jsp">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="view/user/register.jsp">免费注册</a></div>
	<%}else{%>
		<div align="right" style="margin-right: 50px"><label><%=userName %></label>&nbsp;&nbsp;&nbsp;&nbsp;<a href="index.jsp	">退出</a></div>
	<%}
%>

<div align="center">
	<table>
		<tr>
			<td align="center">
				<img src="http://img3x0.ddimg.cn/62/6/24012710-1_l_5.jpg">
				<br><label>人类历史</label>
				<br><label>张大人</label>
				<br><label>¥19.5</label>
			</td>
			<td align="center">
				<img src="http://img3x0.ddimg.cn/62/6/24012710-1_l_5.jpg">
				<br><label>人类历史</label>
				<br><label>张大人</label>
				<br><label>¥19.5</label>
			</td>
			<td align="center">
				<img src="http://img3x0.ddimg.cn/62/6/24012710-1_l_5.jpg">
				<br><label>人类历史</label>
				<br><label>张大人</label>
				<br><label>¥19.5</label>
			</td>
		</tr>
		
		<tr>
			<td align="center">
				<img src="http://img3x0.ddimg.cn/62/6/24012710-1_l_5.jpg">
				<br><label>人类历史</label>
				<br><label>张大人</label>
				<br><label>¥19.5</label>
			</td>
			<td align="center">
				<img src="http://img3x0.ddimg.cn/62/6/24012710-1_l_5.jpg">
				<br><label>人类历史</label>
				<br><label>张大人</label>
				<br><label>¥19.5</label>
			</td>
			<td align="center">
				<img src="http://img3x0.ddimg.cn/62/6/24012710-1_l_5.jpg">
				<br><label>人类历史</label>
				<br><label>张大人</label>
				<br><label>¥19.5</label>
			</td>
		</tr>
	</table>
</div>
</body>
</html>