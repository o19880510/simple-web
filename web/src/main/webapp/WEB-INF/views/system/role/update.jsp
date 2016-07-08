<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改角色</title>
</head>
<body>
<div id="accordion" class="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a href="#addAccordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion">
				<i class="icon-th-large icon-white"></i>
				<span>修改角色</span>
				<i class="icon-chevron-down icon-white pull-right"></i>
			</a>
		</div>
		<div id="addAccordion" class="accordion-body in">
			<div class="accordion-inner">
				<form:form action="update.do" method="post" modelAttribute="role" cssClass="form-horizontal">
					<c:set var="nameError"><form:errors path="name"/></c:set>
					<c:set var="descriptionError"><form:errors path="description"/></c:set>			
					<c:set var="errorAssignedPrivilegeIds"><form:errors path="assignedPrivilegeIds"></form:errors></c:set>		
					
					<div class="control-group${empty nameError?'':' error'}">
						<label class="control-label" for="name">角色名</label>
						<div class="controls">
							<form:input path="name" maxlength="20"/>
							<span class="help-inline">${nameError}</span>
						</div>
					</div>
					<div class="control-group${empty descriptionError?'':' error'}">
						<label class="control-label" for="description">描述</label>
						<div class="controls">
							<form:textarea path="description" rows="4" class="span6"/>
							<span class="help-inline">${descriptionError}</span>
						</div>
					</div>
					
					<c:if test="${not empty errorAssignedPrivilegeIds}">
						<div class="row-fluid text-error">${errorAssignedPrivilegeIds}</div>
					</c:if>
					
					<c:forEach items="${groupMap}" var="entry" varStatus="vs">
							<c:if test="${vs.index%4==0}">
								<div class="row-fluid">
							</c:if>
							
							<div id="accordion${vs.index}" class="accordion span3">
								<div class="accordion-group">
									<div class="accordion-heading">
										<a href="#addAccordion${vs.index}" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion${vs.index}">
											<i class="icon-th-large icon-white"></i>
											<span>${entry.key}</span>
											<i class="icon-chevron-down icon-white pull-right"></i>
										</a>
									</div>
									<div id="addAccordion${vs.index}" class="accordion-body in">
										<div class="accordion-inner">
											<ul class="nav">
												<c:forEach items="${entry.value}" var="privilege" varStatus="vs2">
													<li>
														<label class="checkbox">
															<form:checkbox path="assignedPrivilegeIds" value="${privilege.id}"/>
															<span title="${privilege.uri}">${privilege.name}</span>
														</label>
													</li>
												</c:forEach>
												<li>
													<a class="checkAll btn" href="#">全选/返选</a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							
							<c:if test="${(vs.index+1)%4==0}">
								</div>
							</c:if>
							<c:if test="${vs.last}">
								</div>
							</c:if>
					</c:forEach>
					
					<div class="form-actions">
						<form:hidden path="id"/>
						<button type="submit" class="btn btn-primary">提交</button>
						<button type="reset" class="btn">重置</button>
						<a href="${returnURI}" class="btn">返回</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(':checkbox').filter(':checked').each(function(i, ele){
	$(ele).parents('li').addClass('toggleCheck');
});

$(':checkbox').click(function(){
	$(this).parents('li').toggleClass('toggleCheck');
});

$('.checkAll').click(function(){
	$(this).parents('li').siblings('li').find(':checkbox').each(function(i, ele){
		if($(ele).is(':checked')){
			$(ele).removeAttr('checked');
			$(ele).parents('li').removeClass('toggleCheck');
		}else{
			$(ele).parents('li').addClass('toggleCheck');
			$(ele).attr('checked', 'checked');
		}
	});
	return false;
});

$('#role').validate({
	rules:{
		name:'required',
		description:{
			maxlength:200
		}
	},
	messages:{
		name:'<spring:message code="role.name.required"/>',
		description:{
			maxlength:'<spring:message code="role.description.max"/>'
		}
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
</script>
</body>
</html>