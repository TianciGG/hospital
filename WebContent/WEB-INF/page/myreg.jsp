<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="base.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
	<title>注册</title>
	<link href="bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="css/myreg.css" rel="stylesheet" type="text/css" media="all" />
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/logic/myreg.js"></script>
</head>
<body>
	<!-- 顶部 -->
	<div class="top">
		<span>注册页面</span>
		<ul>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>
	<!-- 中间部分 -->
	<div class="login">
		<div class="login-top">
			<h1>
				天津市总医院 <br />医疗器械检修预警系统<br>(用户注册)
			</h1>
			<form>
				<input id="myUserName" type="text" placeholder="用户名"
					class="form-control" aria-describedby="sizing-addon"
					required="required"><br /> <input id="myPwd"
					type="password" placeholder="密码" class="form-control"
					required="required"><br /> <input id="tMobileID"
					type="text" placeholder="手机号" class="form-control"><br />
				<input id="tEmailID" type="text" placeholder="Email"
					class="form-control"><br />
			</form>
		</div>
		<div class="login-bottom">
			<h3>
				<a href="javascript:history.go(-1);">返回登录</a>&nbsp; <a
					href="javascript:myreg();">提交</a>
			</h3>
		</div>
	</div>
	<!-- 底部 -->
	<div class="bottom">版权所有@大软Java2班09组</div>
</body>
</html>