<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common_tags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>iTV天翼卖场管理平台</title>
<link rel="stylesheet" type="text/css"
	href="<w:path/>resources/css/bootstrap.min.css">
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	background: #190a23;
}

#wrapper {
	position: relative;
	left: 50%;
	margin-left: -512px;
	width: 1024px;
	height: 600px;
	background: url(<w:path/>resources/img/118100.jpg) no-repeat;
}

#userid {
	position: absolute;
	width: 125px;
	height: 24px;
	left: 512px;
	top: 306px;
}

#password {
	position: absolute;
	width: 125px;
	height: 24px;
	left: 512px;
	top: 346px;
}

#verifyCode {
	position: absolute;
	width: 120px;
	height: 24px;
	left: 512px;
	top: 402px;
}

#verifyCodeIMG {
	position: absolute;
	width: 85px;
	height: 39px;
	left: 338px;
	top: 402px;
}

#btnLogin {
	background: none repeat scroll 0 0 transparent;
	border: none;
	cursor: pointer;
	height: 66px;
	left: 655px;
	position: absolute;
	top: 307px;
	width: 66px;
}

.loginError {
	color: #FFFFFF;
	position: absolute;
	left: 800px;
	top: 172px;
	font-size: 16px;
}
</style>
<link rel="stylesheet" type="text/css"
	href="<w:path/>resources/css/bootstrap-responsive.min.css">
<script type="text/javascript" src="<w:path/>resources/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<w:path/>resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
	if (self != top) {
		top.location = self.location;
	}
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
	<div id="wrapper">
		<form action="login.do" id="login" method="post">
			<input type="text" id="userid" name="userid" value="${userid}" /> <input
				type="password" id="password" name="password" value="${password}" />
			<input type="text" id="verifyCode" name="verifyCode"
				value="${VERIFY_CODE}" /> <input type="hidden" name="forceLogin"
				value="true" />
		</form>
	</div>
	<script type="text/javascript">
		if (confirm('用户已经在别处登录，是否强行登录')) {
			$('#login').submit();
		} else {
			window.location.href = 'login_index.do';
		}
	</script>
</body>
</html>