<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查询内存数据</title>
</head>
<body>
<div id="accordion" class="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a href="#addAccordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion">
				<i class="icon-th-large icon-white"></i>
				<span>查询内存数据</span>
				<i class="icon-chevron-down icon-white pull-right"></i>
			</a>
		</div>
		<div id="addAccordion" class="accordion-body in">
			<div class="accordion-inner">
			
				<div class="btn-group" style="text-align: right; width:90%;">
					<a href="reload_all.do" class="btn">加载所有数据</a>
				</div>
				<br>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>数据名称</th>
							<th>数据描述</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						
						<c:forEach items="${loaderList}" var="loader" varStatus="vs">
							<tr>
								<td>${loader.dataName}</td>
								<td>${loader.dataDesc}</td>
								<td>
									<div class="btn-group">
										<a href="detail.do?dataName=${loader.dataName}" class="btn">查看</a>
										<a href="reload.do?dataName=${loader.dataName}" class="btn">重新加载</a>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

</body>
</html>