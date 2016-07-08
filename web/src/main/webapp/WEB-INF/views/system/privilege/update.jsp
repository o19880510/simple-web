<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改权限</title>
</head>
<body>
<div id="accordion" class="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a href="#addAccordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion">
				<i class="icon-th-large icon-white"></i>
				<span>修改权限</span>
				<i class="icon-chevron-down icon-white pull-right"></i>
			</a>
		</div>
		<div id="addAccordion" class="accordion-body in">
			<div class="accordion-inner">
				<form:form action="update.do" modelAttribute="privilegesAddReq" method="post" cssClass="form-horizontal">
					<c:set var="groupError"><form:errors path="group"></form:errors></c:set>
					
					<div class="control-group${not empty groupError?' error':''}">
						<label class="control-label" for="group">权限组名</label>
						<div class="controls">
							<form:input path="group" maxlength="20"/>
							<form:input type="hidden" path="oldGroup"/>
							<span class="help-inline">${empty groupError?'用于对权限进行分类':groupError}</span>
						</div>
					</div>
					
					<div class="control-group">
						<div class="controls">
							<table class="table table-hover">
								<thead>
									<tr>
										<th style="width:30%">权限名</th>
										<th style="width:50%">资源URI</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${privilegesAddReq.privileges }" var="privilege" varStatus="vs">
										<tr>
											<td>
												<form:input path="privileges[${vs.index }].name"/>
												<span class="help-block${not empty groupError?' error':''}"><form:errors path="privileges[${vs.index }].name"/></span>
											</td>
											<td>
												<form:input path="privileges[${vs.index }].uri" cssStyle="width:80%"/>
												<span class="help-block"><form:errors path="privileges[${vs.index }].uri"/></span>
											</td>
											<td>
												<a href="#" class="add btn">添加</a>
												<a href="#" class="remove btn">删除</a>
											</td>
											<form:input type="hidden" path="privileges[${vs.index }].id"/>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="form-actions">
						<button class="btn btn-primary" type="submit">提交</button>
						<button class="btn" type="reset">重置</button>
						<a href="${returnURI}" class="btn">返回</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

$('#privilegesAddReq').validate({
	rules:{
		group: 'required'
	},
	messages:{
		group:'<spring:message code="privilege.group.required"/>'
	},
	onfocusout: function(element) {
        $(element).valid();
    },
    errorElement: 'span',
    errorClass: 'help-inline',
   	highlight: function(element, errorClass) {
    	$(element).parents('.control-group').addClass('error');
  	},
  	unhighlight: function(element, errorClass, validClass) {
    	$(element).parents('.control-group').removeClass('error');
  	}
});

function addTr($target){
	var size = $('tbody tr').size();
	var trTemp = '<tr> \
					<td> \
					<input type="text" name="privileges['+size+'].name"/> \
				</td> \
				<td> \
					<input type="text" name="privileges['+size+'].uri" style="width:80%"/> \
				</td> \
				<td> \
					<a href="#" class="add btn">添加</a> \
					<a href="#" class="remove btn">删除</a> \
				</td> \
				</tr>';
				
	$target.parents('tr').after(trTemp);
	indexReset();
}

function removeTr($target){
	$target.parents("tr").remove();
	indexReset();
}

function indexReset(){
	$('tbody tr').each(function(i, ele){
		var $name = $(ele).find('input:eq(0)');
		var $value = $(ele).find('input:eq(1)');
		$name.attr('name', 'privileges['+i+'].name');
		$value.attr('name', 'privileges['+i+'].uri');
	});
}

$(function(){
	$("table").on("click", function(event){
		var $target = $(event.target);
		if($target.hasClass("add")){
			addTr($target);
		} else if($target.hasClass("remove")){
			removeTr($target);
		}
	});
});
</script>
</body>
</html>