<%@ page language="java" import="woo.study.web.common.common.business.base.functions.actionlog2.config.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/codetable" prefix="codetable" %>

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
	Hello World!
	
	<codetable:checkbox name="AAA" table="PROVINCE_MAP"/>
	<codetable:json table="User$Status" type="Array"/>
	<codetable:json table="User$Status" />
	
	<script type="text/javascript">
	</script>
</body>
</html>
