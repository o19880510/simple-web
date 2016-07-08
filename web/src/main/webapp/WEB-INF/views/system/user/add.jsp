<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新增用户</title>
</head>
<body>
<div id="accordion" class="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a href="#addAccordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion">
				<i class="icon-th-large icon-white"></i>
				<span>新增用户</span>
				<i class="icon-chevron-down icon-white pull-right"></i>
			</a>
		</div>
		<div id="addAccordion" class="accordion-body in">
			<div class="accordion-inner">
				<form:form id="user" action="add.do" method="post" modelAttribute="user" cssClass="form-horizontal">
					<c:set var="useridError"><form:errors path="userid"></form:errors></c:set>
					<c:set var="passwordError"><form:errors path="password"></form:errors></c:set>
					<c:set var="rolesError"><form:errors path="roles"></form:errors></c:set>
					
					<div class="control-group${empty useridError?'':' error'}">
						<label class="control-label" for="userid">用户ID</label>
						<div class="controls">
							<form:input path="userid" maxlength="20"/>
							<span class="help-inline">${empty useridError?'只能是字母和数字':useridError}</span>
						</div>
					</div>
					<div class="control-group${empty passwordError?'':' error'}">
						<label class="control-label" for="password">密码</label>
						<div class="controls">
							<form:input path="password" maxlength="20"/>
							<span class="help-inline">${empty passwordError?'只能是字母和数字':passwordError}</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="nickname">昵称</label>
						<div class="controls">
							<form:input path="nickname" maxlength="20"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="province">省份</label>
						<div class="controls">
							<form:select path="province" cssClass="province">
								<option></option>
							</form:select>
							<span class="help-inline">只能管理该省业务</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="city">地市</label>
						<div class="controls">
							<form:select path="city" cssClass="city">
								<option></option>
							</form:select>
							<span class="help-inline">只能管理改地市业务</span>
						</div>
					</div>
					<div class="role control-group${empty rolesError?'':' error'}">
						<label class="control-label">角色</label>
						<div class="controls">
							<ul class="nav">
								<c:forEach items="${roles}" var="role" varStatus="vs">
									<li>
										<label class="checkbox">${role.name}
											<form:checkbox path="roles[${vs.index}].id" value="${role.id}"/>
										</label>
									</li>
								</c:forEach>
							</ul>
							<span class="help-block">${rolesError}</span>
							<a id="checkall" class="btn btn-small">全选/返选</a>
						</div>
					</div>
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">提交</button>
						<button type="reset" class="btn">重置</button>
						<a href="${returnURI}" class="btn">返回</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<w:path/>resources/js/pc.js"></script>
<script type="text/javascript">
	jQuery(document).pc({province:'province', city:'city', defaultProvince:'${user.province}', defaultCity:'${user.city}'});
	
	$('#checkall').click(function(){
		$('label.checkbox').find(':checkbox').each(function(i, ele){
			if($(ele).is(':checked')){
				$(ele).removeAttr('checked');
			}else{
				$(ele).attr('checked', 'checked');
			}
		});
	});
	
	jQuery.validator.addMethod('word', function(value, element){
		return /^[a-zA-Z0-9]+$/.test(value);
	});
	$('#user').validate({
		submitHandler: function(form){
			var size = $(':checkbox').filter(':checked').size();
			console.log(size);
			if(size==0){
				$('.role').addClass('error');
				$('.role').find('span').text('<spring:message code="user.roles.min"/>');
				return false;
			}else{
				form.submit();
			}
		},
		rules:{
			userid:"word",
			password:"word"
		},
		messages:{
			userid:'<spring:message code="user.userid.invalid"/>',
			password:'<spring:message code="user.password.invalid"/>'
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