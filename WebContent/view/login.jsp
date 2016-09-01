<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
</head>
<!-- 背景图片平铺效果的代码 -->
<style type="text/css">
body{
background-image:url(../img/2.jpg);
background-repeat:no-repeat;
background-size:100%
}
</style>

<body>
<center>
<br>
<br>
<br>
<br>
<form action="login.jsp" method="post">
	<table width="300" height="200" border="0" cellspacing="0" cellpadding="0" style="border:1px solid #ccc; background-color:#00caca">
		<tr>
			<th colspan="2" align="center" >用户登录</th>
		</tr>
		<tr>
			<td align="right">用户名:</td>
			<td><input type="text" name="user_name"></td>
		</tr>
		<br>
		<tr>
			<td align="right">密码:</td>
			<td><input type="password" name="password"></td>
		</tr>
		<br>
		<tr>
			<td align="right">验证码:</td>
			<td><input type="text"  name="yanzheng" size="4"><img src=" " style='cursor:hand;width:100px;height:20px' border='0' align='absmiddle' onClick="this.src=code.php" alt='请点击刷新验证码' /></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
			<input type="submit" name="sub" class="ti" value="登录">
			</td>
		</tr>
	</table>

</center>
  
</form>

</body>
</html>