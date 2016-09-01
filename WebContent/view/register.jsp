<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>免费注册</title>
</head>
<!-- 背景图片平铺效果的代码 -->
<style type="text/css">
body {
	background-image: url(../img/2.jpg);
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
		<form action="../userServlet?mothed=register" method="post">
			<table width="300" height="200" border="0" cellspacing="0"
				cellpadding="0"
				style="border: 1px solid #ccc; background-color: #a6a3ff">
				<tr>
					<th colspan="2" align="center">用户注册</th>
				</tr>
				<tr>
					<td align="right">用户名:</td>
					<td><input type="text" name="user_name"></td>
				</tr>
				<br>
				<tr>
					<td align="right">密码:</td>
					<td><input type="password" id="password" name="password"></td>
				</tr>
				<br>
				<tr>
					<td align="right">确定密码:</td>
					<td><input type="password" id="password_again"
						name="password_again"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" class="ti"
						value="注册"></td>
				</tr>
			</table>
	</center>

	</form>
</body>
</html>