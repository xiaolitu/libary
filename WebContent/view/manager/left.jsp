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
<title></title>
<style type="text/css">

	a{
	}
</style>
</head>
<body style="text-align: center;">
<div>用户管理</div>
<div>
<a href="#">添加用户</a><br>
<a href="#">查询用户</a>
</div>

<div>库存管理</div>
<div>
<a href="#">添加库存</a><br>
<a href="#">查询库存</a>
</div>

</body>
</html>