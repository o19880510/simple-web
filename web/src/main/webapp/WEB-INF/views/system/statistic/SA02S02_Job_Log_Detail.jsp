<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>任务管理</title>
</head>
<body>
<div id="accordion" class="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a href="#addAccordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion">
				<i class="icon-th-large icon-white"></i>
				<span>任务管理</span>
				<span>SA02S02</span>
				<i class="icon-chevron-down icon-white pull-right"></i>
			</a>
		</div>
		<div id="addAccordion" class="accordion-body in">
			<div class="accordion-inner">
			
				<table class="table table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>任务</th>
							<th>说明</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${scheduleJobList}" var="scheduleJob" varStatus="vs">
							<tr>
								<td>${vs.index + 1}</td>
								<td>${scheduleJob}</td>
								<td>${scheduleJob.desc}</td>
								<td>
									<div style="display: none">
										<table border="0">
											<c:forEach items="${scheduleJob.parameters}" var="parameter">
												<tr>
													<td>${parameter.chiName }</td>
													<td><input name="${parameter.engName }" /></td>
												</tr>
												<tr>
													<td></td>
													<td>${parameter.desc }</td>
												</tr>
											</c:forEach>
										</table>
									</div>
										
									<div class="btn-group">
										<a href="update.do?id=" class="btn">execute</a>
										<a href="change_password.do?id=er.id}" class="btn">params</a>
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

<div id="modal" class="modal hide fade">
	<div class="modal-header">
	  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	  <h3>删除用户</h3>
 	</div>
	<div class="modal-body">
	  <p>真的要删除该用户吗？</p>
	</div>
    <div class="modal-footer">
      <a href="#" class="btn btn-primary" data-dismiss="modal">否</a>
      <a class="btn" href="javascript: remove()">是</a>
    </div>
</div>

<script type="text/javascript">
function remove(id){
	
	if(id){
		$('#modal').modal();
		$('#modal').data('id', id);
	}else{
		$('#modal').modal('hide');
		location.href = 'remove.do?id='+$('#modal').data('id');
	}
}
</script>
</body>
</html>