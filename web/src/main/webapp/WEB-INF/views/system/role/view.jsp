<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看角色</title>
</head>
<body>
<div id="accordion" class="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a href="#addAccordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion">
				<i class="icon-th-large icon-white"></i>
				<span>查看角色</span>
				<i class="icon-chevron-down icon-white pull-right"></i>
			</a>
		</div>
		<div id="addAccordion" class="accordion-body in">
			<div class="accordion-inner">
				<form:form method="post" modelAttribute="role" cssClass="form-horizontal">
					<c:set var="nameError"><form:errors path="name"/></c:set>
					<c:set var="descriptionError"><form:errors path="description"/></c:set>			
					<c:set var="errorAssignedPrivilegeIds"><form:errors path="assignedPrivilegeIds"></form:errors></c:set>		
					
					<div class="control-group${empty nameError?'':' error'}">
						<label class="control-label" for="name">角色名</label>
						<div class="controls">
							<form:input path="name" maxlength="20" readonly="true"/>
							<span class="help-inline">${nameError}</span>
						</div>
					</div>
					<div class="control-group${empty descriptionError?'':' error'}">
						<label class="control-label" for="description">描述</label>
						<div class="controls">
							<form:textarea path="description" rows="4" class="span6" readonly="true"/>
							<span class="help-inline">${descriptionError}</span>
						</div>
					</div>
					
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
															<form:checkbox disabled="true" path="assignedPrivilegeIds" value="${privilege.id}"/>
															<span title="${privilege.uri}">${privilege.name}</span>
														</label>
													</li>
												</c:forEach>
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
						<a href="${returnURI}" class="btn btn-primary">返回</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	$(':checkbox').filter(':checked').each(function(i, ele){
		$(ele).parents('li').addClass('privilege_checked');
	});
});
</script>
</body>
</html>