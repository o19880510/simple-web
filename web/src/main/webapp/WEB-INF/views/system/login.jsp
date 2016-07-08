<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>iTV天翼卖场管理平台</title>
<link rel="stylesheet" type="text/css" href="<w:path/>resources/css/bootstrap.min.css">
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	background: #190a23;
}	
#wrapper{
	position: relative;
	left: 50%;
	margin-left: -512px;
	width: 1024px;
	height: 600px;
	background: url(<w:path/>resources/img/login.jpg) no-repeat;
}
#userid{
	position: absolute;
	width: 125px; 
	height: 24px;
	left: 512px;
	top: 306px;
}

#password{
	position: absolute;
	width: 125px; 
	height: 24px;
	left: 512px;
	top: 346px;
}

#verifyCode{
	position: absolute;
	width: 120px; 
	height: 24px;
	left: 512px;
	top: 402px;
}

#verifyCodeIMG{
	position: absolute;
	width: 85px; 
	height: 39px;
	left: 338px;
	top: 402px;
}
#btnLogin{
	background: none repeat scroll 0 0 transparent;
    border: none;
    cursor: pointer;
    height: 66px;
    left: 655px;
    position: absolute;
    top: 307px;
    width: 66px;
}
.loginError{
	color: #FFFFFF;
	position: absolute;
	left:800px; 
	top:172px;
	font-size: 16px;
}
.browserRecommend{
	color: #FFFFFF;
	position: absolute;
	width: 1024px;
	text-align: center;
	top: 500px;
}
.browserRecommend a{
	color: #ffffff;
}
</style>
<link rel="stylesheet" type="text/css" href="<w:path/>resources/css/bootstrap-responsive.min.css">
<script type="text/javascript" src="<w:path/>resources/js/jquery.min.js"></script>
<script type="text/javascript" src="<w:path/>resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
	
	$(document).keypress(function(e){  
		var keyCode = e.which;
		if(keyCode == 13){
	        	$('#login').submit();
	    }
	});

	if (self != top) {
		top.location = self.location;
	}
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
	
	
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
	<div id="wrapper">
		<form:form id="login" action="login.do" method="POST" modelAttribute="login">
			<form:errors path="*" cssClass="loginError"></form:errors>
			<c:if test="${not empty info}">
				<span class="loginError">${info}</span>
				<c:remove var="info" scope="session"/>
			</c:if>
			<form:input path="userid" maxlength="20"/>
			<form:password path="password"/>
			<form:input path="verifyCode" maxlength="4"/>
			<img src="verifyCode.do" id="verifyCodeIMG"/>
			<a onclick="$('#login').submit()" id="btnLogin">&nbsp;</a>
		</form:form>
		
		<div class="browserRecommend">
			推荐使用
			<a href="http://firefox.com.cn/" target="_blank">火狐</a>、
			<a href="http://www.google.cn/intl/zh-CN/chrome/browser/" target="_blank">谷歌</a>、
			<a href="http://ie.sogou.com/index.html" target="_blank">搜狗</a>、
			<a href="http://se.360.cn/" target="_blank">360</a>浏览器
			浏览本系统
		</div>
	</div>
	
</body>
</html>