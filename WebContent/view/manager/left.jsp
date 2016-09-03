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
<script src="js/jquery-3.1.0.min.js"></script>
	<script type="text/javascript">
		
		$(function(){
			$("#userHead").click(function(){
				if ($("#user").css("display") == 'none') {
					$("#user").css("display", "block");
				}else{
					$("#user").css("display", "none");
				}
			})
			$("#productHead").click(function(){
				if ($("#product").css("display") == 'none') {
					$("#product").css("display", "block");
				}else{
					$("#product").css("display", "none");
				}
			})
			$("#managerHead").click(function(){
				if ($("#manager").css("display") == 'none') {
					$("#manager").css("display", "block");
				}else{
					$("#manager").css("display", "none");
				}
			})
		})

	</script>
	
	<style type="text/css">
		[name='head']{
			background-color: #C1C1C1;
			width: 100%;
			height: 20px;
			margin-top: 2px;
		}

	</style>
</head>
<body style="text-align: center;">
<div id="userHead" name="head">用户管理</div>
<div id="user" style="display: none">
<a href="#">查询用户</a>
</div>
<div id="productHead" name="head">库存管理</div>
<div id="product" style="display: none">
<a href="#">添加库存</a><br>
<a href="#">查询库存</a>
</div>
<div id="managerHead" name="head">管理员管理</div>
<div id="manager" style="display: none">
<a href="view/manager/add.jsp" target="right">添加管理员</a><br>
<a href="userServlet?mothed=managerList&page=1&rows=3" target="right">查询管理员</a>
</div>
</body>
</html>