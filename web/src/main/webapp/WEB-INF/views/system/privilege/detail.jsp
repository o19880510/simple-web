<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>权限组详情</title>
</head>
<body>
<div id="accordion" class="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a href="#addAccordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion">
				<i class="icon-th-large icon-white"></i>
				<span>权限组详情</span>
				<i class="icon-chevron-down icon-white pull-right"></i>
			</a>
		</div>
		<div id="addAccordion" class="accordion-body in">
			<div class="accordion-inner">
				<div class="row-fluid">
					<div class="span2">权限组：</div>
					<div class="span10">${param.group}</div>
				</div>
				
				<div class="row-fluid">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>权限名</th>
								<th>资源URI</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${privilegeList}" var="privilege" varStatus="vs">
								<tr>
									<td>${privilege.name}</td>
									<td>${privilege.uri}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>	
					<a href="${returnURI}" class="btn btn-primary">返回</a>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>