<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改密码</title>
</head>
<body>
<div id="accordion" class="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a href="#addAccordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion">
				<i class="icon-th-large icon-white"></i>
				<span>修改密码</span>
				<i class="icon-chevron-down icon-white pull-right"></i>
			</a>
		</div>
		<div id="addAccordion" class="accordion-body in">
			<div class="accordion-inner">
				<form:form action="change_self_password.do" modelAttribute="user" method="post" cssClass="form-horizontal">
					<c:set var="oldPasswordError"><form:errors path="oldPassword"></form:errors></c:set>
					<div class="control-group${empty oldPasswordError?'':' error'}">
						<label class="control-label" for="oldPassword">旧密码</label>
						<div class="controls">
							<form:password path="oldPassword" maxlength="20"/>
							<span class="help-inline">${oldPasswordError}</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="newPassword">新密码</label>
						<div class="controls">
							<form:password path="newPassword" maxlength="20"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="newPassword2">新密码确认</label>
						<div class="controls">
							<form:password path="newPassword2" maxlength="20"/>
						</div>
					</div>
					<div class="form-actions">
						<form:hidden path="userid"/>
						<a href="#" onclick="$('#user').submit()" class="btn btn-primary">提交</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$('#user').validate({
	rules:{
		oldPassword:'required',
		newPassword:'required',
		newPassword2:{
			required: true,
			equalTo: '#newPassword'
		}
	},
	messages:{
		oldPassword:'<spring:message code="user.oldpassword.required"/>',
		newPassword:'<spring:message code="user.newpassword.required"/>',
		newPassword2:{
			required: '<spring:message code="user.newpassword2.required"/>',
			equalTo: '<spring:message code="user.newpassword2.mismatch"/>'
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