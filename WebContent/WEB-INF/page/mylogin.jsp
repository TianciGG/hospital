<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
	<jsp:include page="base.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>登录</title>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<link href="css/loginstyle.css" rel="stylesheet" type="text/css" media="all" />
	<script type="text/javascript" src="js/logic/login.js"></script>
</head>
<body onload="createCode()">
	<div class="logintop">
		<span>欢迎登录</span>
		<ul>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>
	<div class="login">
		<div class="login-top">
			<h1>天津市总医院</h1>
			<h1>医疗器械检修预警系统</h1>
			<form>
				<input id="myUserName" type="text" placeholder="用户名"
					required="required" onkeydown="KeyDown()"> <input
					id="myPwd" type="password" placeholder="密码" required="required"
					onkeydown="KeyDown()"> <input
					style="float: left; width: 100px;" placeholder="验证码" type="text"
					  id="inputCode" onkeydown="KeyDown()" /> <span class="code"
					id="checkCode" onclick="createCode()"></span><a class="yza"
					href="#" onclick="createCode()">看不清换一张</a>
			</form>
		</div>
		<div class="login-bottom">
			<h3>
				<a href="javascript:mylogin();">登录</a>&nbsp;
				<!-- <a
					href="javascript:myreg();">注册</a> -->
			</h3>
		</div>
	</div>
	<div class="loginbm">版权所有@大软Java2班09组</div>
</body>
</html>