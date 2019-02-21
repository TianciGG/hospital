<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="base.jsp" />
	<meta charset="utf-8" />
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>首页</title>
	<%@include file="header.jsp"%>
	<script type="text/javascript" src="js/logic/main.js"></script>
	<style type="text/css">
		a:link {
			color: #400040;
		} /* 未被访问的链接 */
		a:visited {
			color: #BA55D3;
		} /* 已被访问的链接 */
		a:hover {
			color: #0080ff;
		} /* 鼠标指针移动到链接上 */
	</style>
</head>
<body>
	<%@include file="headerDiv.jsp"%>
	<div class="" id="main-container" style="height: 600px;">
		<div class="main-container-inner">
			<%@include file="leftPage.jsp"%>
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="glyphicon glyphicon-home"></i> <a
							href="goToMainPage.do">首页</a></li>
					</ul>
				</div>
				<div class="mainindex">
					<div class="welinfo">
						<span> <img src="images/sun.png" />
						</span> <b> 您好，欢迎您使用天津医科大学总医院医疗设备检修预警系统！ </b>
					</div>
					<div class="welinfo">
						<span> <img src="images/time.png" />
						</span> <b> 您本次登录的时间： <font color="#000000" id="currentTime">
						</font>
						</b>
					</div>
					<div class="xline"></div>
					<ul class="iconlist">
						<li><a href="goToMainPage.do"><img src="images/ico01.png" />
								<p>首页</p> </a></li>
						<c:if test="${myuser.role=='维护人员'}">
							<li><a href="goToEquipManagePage.do"><img
									src="images/d01.png" />
									<p>设备管理</p></a></li>
							<li><a href="goToEquipLogManagePage.do"><img
									src="images/d04.png" />
									<p>设备日志管理</p></a></li>
						</c:if>
						<c:if test="${myuser.role=='管理员'}">
							<li><a href="goToEquipManagePage.do"><img
									src="images/d01.png" />
									<p>设备管理</p></a></li>
							<li><a href="goToEquipLogManagePage.do"><img
									src="images/d04.png" />
									<p>设备日志管理</p></a></li>
							<li><a href="goToSupplierManagePage.do"><img
									src="images/ico02.png" />
									<p>供应商管理</p></a></li>
							<li><a href="goToDicListPage.do"><img
									src="images/d03.png" />
									<p>数字字典管理</p></a></li>
							<li><a href="goToUserManagePage.do"><img
									src="images/d07.png" />
									<p>用户管理</p></a></li>
						</c:if>
						<li><a href="goToStatisticsDeptEqptPage.do"><img
								src="images/d02.png" />
								<p>各科室设备统计</p></a></li>
					</ul>
					<div class="ibox">
						<a class="ibtn"> <img src="images/iadd.png" /> 添加新的快捷键
						</a>
					</div>
					<div class="xline"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

