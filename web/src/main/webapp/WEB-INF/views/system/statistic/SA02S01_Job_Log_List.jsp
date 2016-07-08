<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/common_tags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>任务日志</title>
</head>
<body>
	<div id="accordion" class="accordion">
		<div class="accordion-group">
			<div class="accordion-heading">
				<a href="#addAccordion" class="accordion-toggle"
					data-toggle="collapse" data-parent="#accordion"> <i
					class="icon-th-large icon-white"></i> <span>任务日志</span> <span>SA02S01</span>
					<i class="icon-chevron-down icon-white pull-right"></i>
				</a>
			</div>
			<div id="addAccordion" class="accordion-body in">
				<div class="accordion-inner">
					<form:form id="log" action="list.do" method="post"
						modelAttribute="jobLogReq" cssClass="form-inline">
						<label>任务类 <form:input path="jobClass" />
						</label>
						<label>状态</label>
						<form:select path="status" cssClass="span1">
							<form:option value="">所有</form:option>
							<codetable:option table="ScheduleJob$Status" selectValue="${jobLogReq.status }"/>
						</form:select>

						<label>日期 <form:input path="startDate"
								cssClass="input-small Wdate" onclick="WdatePicker()" />
						</label>
						<label>至 <form:input path="endDate"
								cssClass="input-small Wdate" onclick="WdatePicker()" />
						</label>

						<div class="btn-group">
							<form:hidden path="current" />
							<form:hidden path="pageSize" />
							<a href="#" onclick="$('#log').submit(); return false;" class="btn">查询</a>
						</div>
					</form:form>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>任务</th>
								<th>状态</th>
								<th>执行时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${logPageBean.dataList}" var="log"
								varStatus="vs">
								<tr>
									<td>${vs.index + 1}</td>
									<td>${log.jobClass}</td>
									<td><codetable:out table="ScheduleJob$Status"
											selectValue="${log.status}" /></td>
									<td><joda:format value="${log.runDate}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>
										<div class="btn-group">
											<permission:verify uri="manage/statistic/job/log/detail.do">
												<a href="javascript:detail('${log.id}')" class="btn">详情</a>
											</permission:verify>
										</div>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="5" form-id="log" class="paginationPanel">${logPageBean.fullDisplay}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div id="modal" class="modal hide fade" style="width: 900px;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h3>详细日志</h3>
		</div>
		<div class="modal-body">
			<table class="table table-bordered">
				<tr>
					<td width="80">日志ID</td>
					<td class="logId">加载中...</td>
				</tr>
				<tr>
					<td width="80">日志类</td>
					<td class="jobClass">加载中...</td>
				</tr>
				<tr>
					<td width="80">执行状态</td>
					<td class="status">加载中...</td>
				</tr>
				<tr>
					<td width="80">异常</td>
					<td class="exception">加载中...</td>
				</tr>
				<tr>
					<td width="80">执行时间</td>
					<td class="runDate">加载中...</td>
				</tr>
			</table>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn btn-primary" data-dismiss="modal">关闭</a>
		</div>
	</div>

	<script type="text/javascript">
		
		function detail(logId){
			if(logId){
				$.get("detail.do",{logId:logId},
						function(data){
							data = JSON.parse(data);
							$('#modal .logId').text(data.id);
							$('#modal .jobClass').text(data.jobClass);
							$('#modal .status').text(data.statusDesc);
							$('#modal .runDate').text(new Date(data.runDate).toLocaleString());
							if(data.errorMsg){
								$('#modal .exception').text(data.errorMsg);
							}else{
								$('#modal .exception').text("");
							}
						}
				);
				
				$('#modal').modal();
				return false;
			}else{
				$('#modal').modal('hide');
			}
		}
	</script>
</body>
</html>