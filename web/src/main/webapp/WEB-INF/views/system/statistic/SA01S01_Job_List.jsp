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
				<span>SA01S01</span>
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
									<div id="scheduleJob${vs.index + 1}" style="display: none">
										<form action="run.do" method="post">
											<input type="hidden" name="jobClass" value="${scheduleJob}"/>
											<table border="0">
												<c:forEach items="${scheduleJob.parameters}" var="parameter">
													<tr>
														<td>${parameter.chiName }</td>
														<td><input name="${parameter.engName }" /></td>
													</tr>
													<tr>
														<td></td>
														<td>${parameter.desc}</td>
													</tr>
												</c:forEach>
											</table>
										</form>
									</div>
									<div class="btn-group">
										<a onclick="javascript:$('#scheduleJob${vs.index + 1} form').submit();" class="btn btn-primary">执行</a>
										<a onclick="javascript:$('#scheduleJob${vs.index + 1}').show();" class="btn btn-danger">参数</a>
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

<div id="addMenuModal" class="modal hide fade">
	<div class="modal-header">
	  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	  <h3>添加菜单</h3>
 	</div>
	<div class="modal-body">
	  <form action="run.do" id="jobRunForm" class="form-horizontal">
	  	<div class="control-group">
	  		<label class="control-label">菜单名</label>
	  		<div class="controls">
	  			<input type="text" name="name" maxlength="20"/>
	  		</div>
	  	</div>
	  	<div class="control-group">
	  		<label class="control-label">链接地址</label>
	  		<div class="controls">
	  			<input type="text" name="uri" class="w90" maxlength="256"/>
	  			<input type="hidden" name="parent.id" id="parentId"/>
	  		</div>
	  	</div>
	  </form>
	</div>
    <div class="modal-footer">
    	<permission:verify uri="manage/statistic/job/run.do"></permission:verify>
      <a href="#" class="btn btn-primary" id="btnAddMenu2" onclick="$('#jobRunForm').submit()">执行</a>
      <a href="#" class="btn" data-dismiss="modal">取消</a>
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