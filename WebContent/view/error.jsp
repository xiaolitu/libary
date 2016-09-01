<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>错误页面</title>
</head>
<body style="text-align: center;">
<h1 style="color: red"><%=request.getAttribute("msg") %></h1>
</body>
</html>