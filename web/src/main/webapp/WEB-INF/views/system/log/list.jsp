<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查询日志</title>
</head>
<body>
<div id="accordion" class="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a href="#logAccordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion">
				<i class="icon-th-large icon-white"></i>
				<span>日志列表</span>
				<i class="icon-chevron-down icon-white pull-right"></i>
			</a>
		</div>
		<div id="logAccordion" class="accordion-body in">
			<div class="accordion-inner">
				<form:form action="list.do" method="post" modelAttribute="log" cssClass="form-inline">
					<label for="actor">操作者
						<form:input path="actor" cssClass="input-small"/>
					</label>
					<label for="content">操作描述
						<form:input path="content"/>
					</label>
					<label for="from">日期
						<form:input path="from" cssClass="input-small Wdate" onclick="WdatePicker()"/>
					</label>
					<label for="to">至
						<form:input path="to" cssClass="input-small Wdate" onclick="WdatePicker()"/>
					</label>
					
					<form:hidden path="current"/>
					<form:hidden path="pageSize"/>
					<div class="btn-group">
						<form:hidden path="type"/>
						<form:hidden path="typeCN"/>
						<a class="btn" href="#" id="submit" onclick="$('#log').submit()">${log.typeCN}</button>
		                <a href="#" data-toggle="dropdown" class="btn dropdown-toggle"><span class="caret"></span></a>
		                <ul class="dropdown-menu">
		                  <c:forEach items="${mdata:get('ENUM_PARAMTER_MAP')['ActionResult$Type']}" var="parameter">
		                  	 <li><a href="#" onclick="doSearch('${parameter.key}','${parameter.value}'); return false">${parameter.value}</a></li>
		                  </c:forEach>
		                   <li><a href="#" onclick="doSearch('','所有'); return false">所有</a></li>
		                </ul>
	                </div>
				</form:form>
				
				<table class="table table-hover table-condensed">
					<thead>
						<tr>
							<th>操作者</th>
							<th>操作描述</th>
							<th>操作时间</th>
							<th width="10%">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pb.dataList}" var="log">
							<tr>
								<td>${log.actor}</td>
								<td>${log.content}</td>
								<td><joda:format value="${log.addDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<a href="javascript:;" class="btn" onclick="detail(${log.id})">详细</a>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="4" form-id="log" class="paginationPanel">${pb.fullDisplay}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div id="modal" class="modal hide fade">
	<div class="modal-header">
	  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	  <h3>详细日志</h3>
 	</div>
	<div class="modal-body">
	  <table class="table table-bordered">
	  		<tr>
	  			<td width="80">操作者</td>
	  			<td class="actor">加载中...</td>
	  		</tr>
	  		<tr>
	  			<td width="80">操作时间</td>
	  			<td class="addDate">加载中...</td>
	  		</tr>
	  		<tr>
	  			<td width="80">操作描述</td>
	  			<td class="content">加载中...</td>
	  		</tr>
	  		<tr>
	  			<td width="80">请求地址</td>
	  			<td class="uri">加载中...</td>
	  		</tr>
	  		<tr>
	  			<td width="80">请求参数</td>
	  			<td class="param">加载中...</td>
	  		</tr>
	  		<tr>
	  			<td width="80">异常信息</td>
	  			<td class="exception">加载中...</td>
	  		</tr>
	  	</table>
	</div>
    <div class="modal-footer">
      <a href="#" class="btn btn-primary" data-dismiss="modal">关闭</a>
    </div>
</div>

<script type="text/javascript">
function doSearch(en,cn){
	$('#type').val(en);
	$('#typeCN').val(cn);
	$('#log').submit();
}

function detail(id){
	$.ajax({type:'get', url:id+'.do', success:function(data){
		$('#modal .actor').text(data.actor);
		$('#modal .addDate').text(new Date(data.addDate).toLocaleString());
		$('#modal .content').text(data.content);
		$('#modal .uri').text(data.uri);
		$('#modal .param').html(data.param);
		if(data.detail){
			$('#modal .exception').text(data.detail.content);
		}else{
			$('#modal .exception').text('');
		}
	}});
	$('#modal')
	.css('margin-top','-250px')
	.css('margin-left','-40%')
	.css('width','80%')
	.modal({backdrop:false});
	return false;
};
</script>
</body>
</html>