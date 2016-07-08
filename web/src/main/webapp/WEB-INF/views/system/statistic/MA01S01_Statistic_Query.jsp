.<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<div id="accordion" class="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a href="#addAccordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion">
				<i class="icon-th-large icon-white"></i>
				<span>统计数据查询</span>
				<span>MA01S01</span>
				<i class="icon-chevron-down icon-white pull-right"></i>
			</a>
		</div>
		<div id="addAccordion" class="accordion-body in">
			<div class="accordion-inner">
				<form:form id="formStatistic" action="query.do" method="post" modelAttribute="statisticReq" cssClass="form-inline">
					
					<label>统计类型</label>
					<form:select path="indicatorType" cssClass="span1">
						<codetable:option table="DataSummaryIndicator$IndicatorType" selectValue="${statisticReq.indicatorType}" />
					</form:select>
					
					<label>开始时间</label>
					<form:input path="startDate" cssClass="input-small Wdate" onclick="WdatePicker()"/>
					
					<label>结束时间</label>
					<form:input path="endDate" cssClass="input-small Wdate" onclick="WdatePicker()"/>
					
					<div class="btn-group">
						<form:hidden path="current"/>
						<form:hidden path="pageSize"/>
						<a href="#" onclick="$('#formStatistic').submit(); return false;" class="btn">查询</a>
						<a href="#" onclick="exportExcel()" class="btn">导出Excel</a>
					</div>
				</form:form>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>日期</th>
							<c:forEach items="${dataSummaryIndicators }" var="indicator">
								<th>${indicator.nameChi }</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${statisticRspPage.dataList }" var="statisticRspItem" varStatus="vs">
							<c:forEach items="${statisticRspItem.dataMap }" var="recordMap">
							<tr>
								<td>${recordMap.key }</td>
								<c:forEach items="${dataSummaryIndicators }" var="indicator">
									<td>${recordMap.value[indicator.nameEng].dataValue }</td>
								</c:forEach>
							</tr>
							</c:forEach>
						</c:forEach>
						
						<tr>
							<td colspan="${fn:length( dataSummaryIndicators) + 1 }" 
							form-id="formStatistic" class="paginationPanel">${statisticRspPage.fullDisplay}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
function exportExcel(){
	
	var oldAction = $('#formStatistic')[0].action;
	
	$('#formStatistic')[0].action = "export.do";
	$('#formStatistic').submit();
	
	$('#formStatistic')[0].action = oldAction;
}
</script>
</body>
</html>