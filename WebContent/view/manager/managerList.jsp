<%@ page language="java" import="java.util.*,com.library.bean.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员列表</title>
<style type="text/css">
td {
	padding: 12px;
}
</style>
</head>
<body>
	<div style="text-align: center;">
		<h1>管理员列表</h1>
		<table align="center" border="1px" cellspacing="0">
			<tr>
				<td>ID</td>
				<td>用户名</td>
				<td>创建时间</td>
				<td>操作</td>
			</tr>
			<%
			Map<String, Object> map = (Map<String, Object>)request.getAttribute("map");
			List<User> users = (List<User>)map.get("users");
			//总的页数
			int pages = Integer.valueOf(String.valueOf(map.get("pages")));
			//当前页码
			int pageTemp = Integer.valueOf(String.valueOf(map.get("page")));
			for(int i = 0; users!= null && i < users.size(); i++){%>
			<tr>
				<td><%=users.get(i).getId() %></td>
				<td><%=users.get(i).getUserName() %></td>
				<td><%=users.get(i).getCreateTime() %></td>
				<td><a href="#">删除</a>&nbsp;<a href="#">修改</a></td>
			</tr>
			<%}
			%>

		</table>
	</div>
	<div style="text-align: center; margin-top: 30px">
		<a href="userServlet?mothed=managerList&page=1&rows=3">首页</a>

		<%
			if(pageTemp <= 3){
				
				for(int i = 2; i < pages && i <= 4; i++){%>
		<a href="userServlet?mothed=managerList&page=<%=i %>&rows=3"><%=i %></a>
		<%}
			}else if(pages - pageTemp <= 3){
				%>
					<a href="userServlet?mothed=managerList&page=<%=pageTemp-1 %>&rows=3"><%=pageTemp-1 %></a>
					<a href="userServlet?mothed=managerList&page=<%=pageTemp %>&rows=3"><%=pageTemp %></a>
					<%
						if(pageTemp < pages){%>
							<a href="userServlet?mothed=managerList&page=<%=pageTemp+1 %>&rows=3"><%=pageTemp+1 %></a>
						<%}
					%>
					
					<%
			}else{
		%>

		<%
			if(pageTemp - 1 > 2){%>
		<a
			href="userServlet?mothed=managerList&page=<%=pageTemp - 1 %>&rows=3"><%=pageTemp - 1 %></a>
		<%}
		%>
		<a href="userServlet?mothed=managerList&page=<%=pageTemp %>&rows=3"><%=pageTemp %></a>
		<%
			if(pageTemp + 1 < pages){%>
		<a
			href="userServlet?mothed=managerList&page=<%=pageTemp + 1 %>&rows=3"><%=pageTemp + 1 %></a>
		<%}
			}
		%>
		<a href="userServlet?mothed=managerList&page=<%=pages %>&rows=3">尾页</a>
	</div>
</body>
</html>