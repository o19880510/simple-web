<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看参数</title>
</head>
<body>
<div id="accordion" class="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a href="#addAccordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion">
				<i class="icon-th-large icon-white"></i>
				<span>查看参数</span>
				<i class="icon-chevron-down icon-white pull-right"></i>
			</a>
		</div>
		<div id="addAccordion" class="accordion-body in">
			<div class="accordion-inner">
				<div class="row-fluid">
					<div class="span2">参数名：</div>
					<div class="span10">${PARAM.name}</div>
				</div>
				<div class="row-fluid">
					<div class="span2">参数说明：</div>
					<div class="span10">${PARAM.description}</div>
				</div>
				
				<div class="row-fluid">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>键</th>
								<th>值</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${PARAM.items}" var="item" varStatus="vs">
								<tr>
									<td>${item.name}</td>
									<td>${item.value}</td>
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