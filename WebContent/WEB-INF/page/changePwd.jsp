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
	<title>密码修改</title>
	<%@include file="header.jsp"%>
	<script type="text/javascript" src="js/logic/changePwd.js"></script>
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
							class="glyphicon glyphicon-menu-right"></i> 控制台</li>
					</ul>
				</div>
				<div class="page-content" style="overflow-x: hidden;">
					<div class="row">
						<div class="col-xs-12">
							<div class="table-header">密码重置</div>
							<div class="hr hr-24"></div>
							<div class="space-24"></div>
							<form class="form-horizontal" role="form"
								style="margin-top: -20px; margin-left: 200px;">
								<div class="space-4"></div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-2"> 原密码</label> <input type="hidden"
										id="myuserid" value="${myuser.userId}" /></input>
									<div class="col-sm-9">
										<input type="password" id="oldpwd" placeholder="Password"
											class="col-xs-10 col-sm-5" onblur="checkNullOrNot();" /> <span
											class="help-inline col-xs-12 col-sm-7"> <span
											class="middle" id="oldpwdmsg" style="color: red;"></span>
										</span>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-2"> 新密码</label>
									<div class="col-sm-9">
										<input type="password" id="newpwd1" placeholder="Password"
											class="col-xs-10 col-sm-5" onblur="checkNullOrNot2();" /> <span
											class="help-inline col-xs-12 col-sm-7"> <span
											class="middle" id="newpwd1msg" style="color: red;"></span>
										</span>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-2"> 再次输入新密码</label>
									<div class="col-sm-9">
										<input type="password" id="newpwd2" placeholder="Password"
											class="col-xs-10 col-sm-5" onblur="checkNullOrNot3();" /> <span
											class="help-inline col-xs-12 col-sm-7"> <span
											class="middle" id="newpwd2msg" style="color: red;"></span>
										</span>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="clearfix form-actions" style="margin-left: -200px;">
									<div class="col-md-offset-3 col-md-9"
										style="margin-left: 410px;">
										<button class="btn btn-info" type="button" id="mysubbtn"
											onclick="mysubUpdate();" disabled="disabled">
											<i class="glyphicon glyphicon-ok"></i> 提交
										</button>
										&emsp; &emsp;&emsp;
										<button class="btn" type="reset" onclick="addDisabled();">
											<i class="glyphicon glyphicon-refresh"></i> 重置
										</button>
									</div>
								</div>
								<div class="hr hr-24" style="margin-left: -200px;"></div>
								<div class="space-24"></div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改密码</h4>
				</div>
				<div class="modal-body">确定要更新密码么？</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary"
						onclick="updatePwd();">提交</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

