<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="woo.study.web.system.constant.AppConstant"%>
<%@page import="woo.study.web.system.model.entity.Menu"%>
<%@page import="java.util.List"%>
<%@page isELIgnored="false"%>
<%@ include file="../common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>
		iTV天翼卖场管理平台-
		<decorator:title></decorator:title>
	</title>
	<link rel="stylesheet" type="text/css" href="<w:path/>resources/css/bootstrap.min.css">
	<style type="text/css">
		body {
			padding-top: 80px;
		}	
	</style>
	<link rel="stylesheet" type="text/css" href="<w:path/>resources/css/bootstrap-responsive.min.css">
	<link id="skinUri" rel="stylesheet" type="text/css" href="<w:path/>resources/css/${ cookie['skin'] != null ? cookie['skin'].value:'main'}.css">
	<script type="text/javascript" src="<w:path/>resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="<w:path/>resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<w:path/>resources/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<w:path/>resources/js/pagination.js"></script>
	<script type="text/javascript" src="<w:path/>resources/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<w:path/>resources/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="<w:path/>resources/js/lutong/javascript-enhance-0.1.js"></script>
	<!--[if lt IE 9]>
    	<script type="text/javascript" src="<w:path/>resources/js/html5shiv.js"></script>
    <![endif]-->
</head>
<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
			
				<a class="brand" href="#">
					<img alt="" src="<w:path/>resources/img/imusic_logo.png" border="0">
				</a>
				<div class="nav-collapse">
					<ul class="nav">
						<c:forEach var="menu1" items="${USER_MENU_TREE.children}">
							<c:if test="${menu1.enable}">
								<li rel="${menu1.id}" class="menu1${menu1.id==mid1?' active':''}" ><a href="<w:path/>system/index.do?mid1=${menu1.id}">${menu1.name}</a></li>
							</c:if>
						</c:forEach>
					</ul>
					
					<div class="btn-group pull-right">
						<a class="btn"><i class="icon-user"></i>${USER_INFO.nickname}</a>
						<a href="<w:path/>system/user/change_self_password.do" class="btn"><i class="icon-cog"></i>修改密码</a>
						<a href="<w:path/>system/logout.do" class="btn"><i class="icon-off"></i>退出系统</a>
					</div>
					
					<div class="btn-group pull-right">
		        <button data-toggle="dropdown" class="btn dropdown-toggle">皮肤<span class="caret"></span></button>
		          <div class="dropdown-menu">
		          <div class="btnSkin" rel="blue" style="width:100%; height:30px; background:#458FCE; text-align:center; color:#FFFFFF; line-height:30px; cursor:pointer">蓝色</div>
		          <div class="btnSkin" rel="main" style="width:100%; height:30px; background:#7bb72c; text-align:center; color:#FFFFFF; line-height:30px; cursor:pointer">绿色</div>
		        </div>
		      </div>
          
          <div class="btn-group pull-right">
            <button data-toggle="dropdown" class="btn dropdown-toggle">版本<span class="caret"></span></button>
              <div class="dropdown-menu">
              <div class="btnSkin" rel="blue" style="width:100%; height:30px; background:#458FCE; text-align:center; color:#FFFFFF; line-height:30px; cursor:pointer">蓝色</div>
              <div class="btnSkin" rel="main" style="width:100%; height:30px; background:#7bb72c; text-align:center; color:#FFFFFF; line-height:30px; cursor:pointer">绿色</div>
            </div>
          </div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container-fluid">
		<div class="row-fluid">
			<div id="menu-left" class="span2">
			<%
			String mid1 = (String)session.getAttribute("mid1");
			String mid2 = (String)session.getAttribute("mid2");
			String mid3 = (String)session.getAttribute("mid3");
			List<Menu> menu1List = ((Menu)session.getAttribute(AppConstant.USER_MENU_TREE)).getChildren();
			for(Menu menu1: menu1List){
			%>
			<ul class="topnav nav nav-list nav<%=menu1.getId() %> <%=String.valueOf(menu1.getId()).equals(mid1)?"":"hide"%>">
			<%	
				for(Menu menu2: menu1.getChildren()){
				if(menu2.isEnable()){
							
					String uri2 = menu2.getUri();
					uri2 = uri2==null?"":uri2;
					String target2 = "_self";
					String style2 = menu2.getStyle();
					if(uri2.startsWith("http://") || uri2.startsWith("https://")){
						target2 = "_blank";
					}else{
						if(uri2.indexOf("?")>0){
							uri2 = application.getContextPath() + "/" + uri2 + "&mid1="+menu1.getId()+"&mid2="+menu2.getId();
						}else{
							uri2 = application.getContextPath() + "/" + uri2 + "?mid1="+menu1.getId()+"&mid2="+menu2.getId();
						}
					}
					
					if(menu2.getChildren().size()==0){
			%>
				<li>
					<a data-title="<%=menu2.getName() %>" rel="shortmenu" href="<%=uri2%>" target="<%=target2%>" <%=menu2.getId().toString().equals(mid2)?"class=\"current\"":"" %>>
						<i class="icon-th-large<%=menu2.getId().toString().equals(mid2)?" icon-white":"" %>"></i>
						<span><%=menu2.getName() %></span>
					</a>
				</li>
			<%			
					}else{
			%>
				<li class="accordion-menu">
					<a href="#accordion<%=menu2.getId() %>" class="accordion-toggle <%=style2.equals("open")?"":"collapsed" %>" data-toggle="collapse">
						<i class="icon-th-large"></i>
						<span><%=menu2.getName() %></span>
					</a>
					<div id="accordion<%=menu2.getId() %>" class="<%=style2.equals("open")?"in":"collapse" %> accordion-body">
						<div class="accordion-inner">
							<ul class="nav nav-list">
			<%
								int i = 0;
								for(Menu menu3: menu2.getChildren()){
									if(menu3.isEnable()){
											
										
										String uri3 = menu3.getUri();
										uri3 = uri3==null?"":uri3;
										String target3 = "_self";
										if(uri3.startsWith("http://") || uri3.startsWith("https://")){
											target3 = "_blank";
										}else{
											if(uri3.indexOf("?")>0){
												uri3 = application.getContextPath() + "/" + uri3 + "&mid1="+menu1.getId()+"&mid2="+menu2.getId()+"&mid3="+menu3.getId();
											}else{
												uri3 = application.getContextPath() + "/" + uri3 + "?mid1="+menu1.getId()+"&mid2="+menu2.getId()+"&mid3="+menu3.getId();
											}
										}
			%>
										<li>
											<a href="<%=uri3 %>" target="<%=target3 %>" 
												class="<%=menu3.getId().toString().equals(mid3)?"current":"" %> <%=(i++)==0?"first":"" %>">
												<%=menu3.getName() %>
											</a>
										</li>
			<%						}		
								}					
			%>
							</ul>
						</div>
					</div>
				</li>
			<%			
					}
				}
				}
			%>
			</ul>
			<%
			}
			%>		
				<div class="toggle-menu">
					<a href="#"><i class="icon-circle-arrow-left icon-white"></i></a>
				</div>
			</div>
			<div id="content" class="span10">
				<c:if test="${not empty actionResult}">
					<div class="alert alert-${fn:toLowerCase(actionResult.type)}">
						<button class="close" type="button" data-dismiss="alert">X</button>
						<spring:message code="${actionResult.message}"></spring:message>
					</div>
				</c:if>
				<decorator:body></decorator:body>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
		$('.menu1').click(function(){
			var $this = $(this);
			$('.menu1').removeClass('active');;
			$this.addClass('active');
			$('.topnav').addClass('hide');
			$('.nav'+$this.attr('rel')).removeClass('hide');
		});
		
		
		$('.toggle-menu').click(function(){
			var i = $.cookie('toggleMenuStatus') || 0;
			$.cookie('toggleMenuStatus', ++i, { path: '<w:path/>' });
			
			$('#menu-left').toggleClass('span1');
			$('#menu-left').toggleClass('span2');
			$('#content').toggleClass('span10');
			$('#content').toggleClass('span11');
			$('#menu-left').find('span').toggle();
			if($('#menu-left').hasClass('span1')){
				$('#menu-left').wrap('<center></center>');
			}else{
				$('#menu-left').unwrap();
			}
			$('.toggle-menu').find('i').toggleClass('icon-circle-arrow-right');
			$('.toggle-menu').find('i').toggleClass('icon-circle-arrow-left');
			$(".span1 a[rel=shortmenu]").tooltip({
				placement:'right'
			});
		});
		
		if($.cookie('toggleMenuStatus')%2==1){
			$('#menu-left').toggleClass('span1');
			$('#menu-left').toggleClass('span2');
			$('#content').toggleClass('span10');
			$('#content').toggleClass('span11');
			$('#menu-left').find('span').toggle();
			if($('#menu-left').hasClass('span1')){
				$('#menu-left').wrap('<center></center>');
			}else{
				$('#menu-left').unwrap();
			}
			$('.toggle-menu').find('i').toggleClass('icon-circle-arrow-right');
			$('.toggle-menu').find('i').toggleClass('icon-circle-arrow-left');
			$(".span1 a[rel=shortmenu]").tooltip({
				placement:'right'
			});
		}
    
		$('.btnSkin').click(function(){
			var skin = $(this).attr('rel');
			$.cookie('skin', skin, {path: '<w:path/>'});
			$('#skinUri').attr('href', '<w:path/>resources/css/'+skin+'.css');
		});
	});
	</script>
</body>
</html>
