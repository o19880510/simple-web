<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查询用户</title>
</head>
<body>
<div id="accordion" class="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a href="#addAccordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion">
				<i class="icon-th-large icon-white"></i>
				<span>查询用户</span>
				<i class="icon-chevron-down icon-white pull-right"></i>
			</a>
		</div>
		<div id="addAccordion" class="accordion-body in">
			<div class="accordion-inner">
				<form:form action="list.do" method="post" modelAttribute="user" cssClass="form-inline">
					<label>用户ID/昵称
					<form:input path="name"/>
					</label>
					<label>状态</label>
					<form:select path="status" cssClass="span1">
						<form:option value="">所有</form:option>
						<codetable:option table="User$Status"/>
					</form:select>
					
					<div class="btn-group">
						<form:hidden path="current"/>
						<form:hidden path="pageSize"/>
						<a href="#" onclick="$('#user').submit(); return false;" class="btn">查询</a>
						<a href="add.do" class="btn">新增</a>
					</div>
				</form:form>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>用户ID</th>
							<th>昵称</th>
							<th>拥有角色</th>
							<th>省份</th>
							<th>地市</th>
							<th>上次登录时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pb.dataList}" var="user" varStatus="vs">
							<tr>
								<td>${user.userid}</td>
								<td>${user.nickname}</td>
								<td>
									<c:forEach items="${user.roles}" var="role" varStatus="vs2">
									<c:choose>
										<c:when test="${vs2.last}">${role.name}</c:when>
										<c:otherwise>${role.name},</c:otherwise>
									</c:choose>
									</c:forEach>
								</td>
								<td>
									<codetable:out table="PROVINCE_MAP" selectValue="${user.province }" defaultValue=""/>
								</td>
								<td>
									<codetable:out table="CITY_MAP" selectValue="${user.city}" defaultValue=""/>
								</td>
								<td>
									<joda:format value="${user.lastLoginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									<span class="badge
									${user.status=='ONLINE'?' badge-success':''}
									${user.status=='OFFLINE'?' badge-important':''}
									${user.status=='ENABLE'?' badge-info':''}
									">
										
										<codetable:out table="User$Status" selectValue="${user.status }" defaultValue=""/>
									</span>
								</td>
								<td>
									<div class="btn-group">
										<a href="update.do?id=${user.id}" class="btn">修改</a>
										<a href="change_password.do?id=${user.id}" class="btn">修改密码</a>
										<c:if test="${user.userid!='admin' && user.status=='DISABLE'}">
											<a href="enable.do?id=${user.id}" class="btn">启用</a>
										</c:if>
										
										<c:if test="${user.userid!='admin' && (user.status=='ENABLE' || user.status=='OFFLINE')}">
											<a href="disable.do?id=${user.id}" class="btn btn-danger">禁用</a>
										</c:if>
										
										<c:if test="${user.userid!='admin' && user.status!='ONLINE'}">
											<a href="javascript: remove(${user.id})" class="btn btn-danger">删除</a>
										</c:if>
									</div>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="8" form-id="user" class="paginationPanel">${pb.fullDisplay}</td>
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