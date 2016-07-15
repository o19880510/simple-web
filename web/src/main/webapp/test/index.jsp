<%@ page language="java" import="woo.study.web.common.common.business.base.functions.actionlog2.config.*" pageEncoding="UTF-8"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

UalBusinessHelper.setBusinessCode(0L);

UalBusinessHelper.setException(new Exception("woo test!"));
%>

<html>
<head>
	<base href="<%=basePath%>">
	<title>My JSP 'index.jsp' starting page</title>
</head>
<body>
	hello world!
</body>
</html>
