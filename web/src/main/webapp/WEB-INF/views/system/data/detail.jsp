<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看数据</title>
</head>
<body>
<div id="accordion" class="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a href="#addAccordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion">
				<i class="icon-th-large icon-white"></i>
				<span>查看数据</span>
				<i class="icon-chevron-down icon-white pull-right"></i>
			</a>
		</div>
		<div id="addAccordion" class="accordion-body in">
			<div class="accordion-inner">
			<div class="row-fluid">
					<div class="span2">参数名：</div>
					<div class="span10">${param.dataName }</div>
				</div>
				<div class="row-fluid">
					<div class="span2">数据：</div>
					<div class="span10">${memData }</div>
				</div>
				
				<br>
				<div class="row-fluid">
					<div class="span2"></div>
					<div class="span10">
						<a href="reload.do?dataName=${param.dataName }" class="btn btn-primary">重新加载</a>
						<a href="list.do" class="btn">返回</a>
					</div>
				</div>
				
				
			</div>
		</div>
	</div>
</div>

</body>
</html>