<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title></title>
</head>
<frameset rows="15%,85%" border="0" cellspacing="0" cellpadding="0">
	<frame noresize="noresize" src="view/manager/top.jsp"/>
	<frameset cols="10%,90%">
		<frame src="view/manager/left.jsp"/>
		<frame name="right"/>
	</frameset>
</frameset>
</html>