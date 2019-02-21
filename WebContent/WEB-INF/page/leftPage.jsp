<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>左侧导航</title>
</head>
<body>
	<div class="sidebar" id="sidebar" style="height: 600px;">
		<ul class="nav nav-list">
			<li class="active"><a> <i
					class="glyphicon glyphicon-dashboard"></i> <span class="menu-text">
						控制台 </span>
			</a></li>
			<c:if test="${myuser.role=='维护人员'}">
				<li><a href="goToEquipManagePage.do"> <i
						class="glyphicon glyphicon-blackboard"></i> <span
						class="menu-text"> 设备管理 </span>
				</a></li>
				<li><a href="goToEquipLogManagePage.do"> <i
						class="glyphicon glyphicon-calendar"></i> <span class="menu-text">
							设备日志管理 </span>
				</a></li>
			</c:if>
			<c:if test="${myuser.role=='管理员'}">

				<li><a href="goToEquipManagePage.do"> <i
						class="glyphicon glyphicon-blackboard"></i> <span
						class="menu-text"> 设备管理 </span>
				</a></li>
				<li><a href="goToEquipLogManagePage.do"> <i
						class="glyphicon glyphicon-calendar"></i> <span class="menu-text">
							设备日志管理 </span>
				</a></li>
				<li><a href="goToSupplierManagePage.do"> <i
						class="glyphicon glyphicon-list-alt"></i> <span class="menu-text">
							供应商管理 </span>
				</a></li>
				<li><a href="goToDicListPage.do"> <i
						class="glyphicon glyphicon-book"></i> <span class="menu-text">
							数据字典管理 </span>
				</a></li>
				<li><a href="goToUserManagePage.do"> <i
						class="glyphicon glyphicon-tags"></i> <span class="menu-text">
							用户管理 </span>
				</a></li>
			</c:if>
			<li><a href="goToStatisticsDeptEqptPage.do"> <i
					class="glyphicon glyphicon-text-width"></i> <span class="menu-text">
						各科室设备统计 </span>
			</a></li>
		</ul>
	</div>
</body>
</html>