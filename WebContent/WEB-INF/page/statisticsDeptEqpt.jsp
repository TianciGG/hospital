<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="base.jsp" />
	<meta charset="utf-8" />
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>各科室设备使用情况统计</title>
	<%@include file="header.jsp"%>
	<script src="heighcharts/js/highcharts.js"></script>
	<script src="heighcharts/js/highcharts-3d.js"></script>
	<script src="heighcharts/js/modules/exporting.js"></script>
	<script type="text/javascript" src="js/logic/statisticsDeptEqpt.js"></script>
</head>
<body>
	<%@include file="headerDiv.jsp"%>
	<div id="main-container" style="height: 600px;">
		<div class="main-container-inner">
			<%@include file="leftPage.jsp"%>
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="glyphicon glyphicon-home"></i> <a
							href="goToMainPage.do">首页</a> <i
							class="glyphicon glyphicon-menu-right"></i>控制台</li>
					</ul>
				</div>
				<div id="containerColumn"
					style="min-width: 300px; height: 400px; margin: 0 auto"></div>
				<div id="containerPie"
					style="min-width: 300px; height: 400px; margin: 0 auto"></div>
				<div class="btn-group"
					style="display: table; width: auto; margin-left: auto; margin-right: auto;"></div>
			</div>
		</div>
	</div>
</body>
</html>

