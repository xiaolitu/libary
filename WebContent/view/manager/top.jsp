<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<title>top</title>
<script src="js/jquery-3.1.0.min.js"></script>

</head>
<!-- 背景图片平铺效果的代码 -->
<body style="text-align: center;background-color:#1E90FF">
<h1>欢迎登陆后台管理系统</h1>

</body>
</html>