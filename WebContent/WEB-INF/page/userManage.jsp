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
	<title>用户管理</title>
	<%@include file="header.jsp"%>
	<script type="text/javascript" src="js/logic/userManage.js"></script>
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
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="table-header">系统用户列表</div>
							<div class="widget-box">
								<div class="widget-body">
									<div class="widget-main">
										<form class="form-inline">
											用户名：<input type="text" id="searchUserNameId"
												class="input-large" placeholder="用户名" onkeydown="KeyDown()" />
											激活状态：<select id="iStatus"
												class="selectpicker" data-style="btn-primary"
												onChange="searchTable();">
													<option value="">全部</option>
													<option value="1">激活</option>
													<option value="-1">屏蔽</option>
												</select>
											<button type="button" class="btn btn-purple btn-sm"
												onclick="searchTable()">
												<i class="glyphicon glyphicon-search"></i> 查询
											</button>
											<button type="button" class="btn btn-sm btn-success"
												onclick="openAddUserWin();">
												<i class="glyphicon glyphicon-ok"></i>添加
											</button>
										</form>
									</div>
								</div>
							</div>
							<div id="result" class="table-responsive ">
								<table id="tblResult"
									class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center" data-formatter="xuanze"><label>
													<input type="checkbox" class="acem" /> <span class="lbl"></span>
											</label></th>
											<th class="center" data-field="userName">用户名</th>
											<th class="center" data-field="cname">真实姓名</th>
											<th class="center" data-field="role">权限角色</th>
											<th class="center" data-field="gender">性别</th>
											<th class="center" data-field="dicId">科室</th>
											<th class="center" data-field="mobile">手机号</th>
											<th class="center" data-field="email">Email</th>
											<th class="center" data-formatter="caozuo">操作</th>
										</tr>
									</thead>
									<tbody id="tableResult"></tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="addComWin" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<i class="glyphicon glyphicon-pencil"></i> <span id="lblAddTitle"
							style="font-weight: bold">用户信息</span>
					</h4>
				</div>
				<form class="form-horizontal form-bordered form-row-strippe"
					id="ffAdd" action="" data-toggle="validator"
					enctype="multipart/form-data">
					<input type="hidden" id="deviceHiddenID" name="tId" /></input>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<div class="control-label col-md-3">用户名</div>
									<div class="col-md-9">
										<input id="iuserName" name="iUserName" type="text"
											placeholder="用户名" class="col-xs-10 col-sm-5" onblur="checkNullOrNot();"/><span
											class="help-inline col-xs-12 col-sm-7"> <span
											class="middle" id="tishiUserName" style="color: red;"></span>
										</span>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">权限</label>
									<div class="col-md-9">
										<select id="iquanxian" class="selectpicker" data-style="btn-primary"
											style="width: 100px;">
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">密码</label>
									<div class="col-md-9">
										<input id="iPwd" name="Pwd" type="password" value="666666" disabled="disabled"
											placeholder="Password" class="col-xs-10 col-sm-5" onblur="checkNullOrNot2();"/>
										<span class="help-inline col-xs-12 col-sm-7"> <span
											class="middle" id="tishiPwd" style="color: red;"></span>
										</span>
									</div>
								</div>
							</div>
							<!--<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">再次输入密码</label>
									<div class="col-md-9">
										<input id="iRePwd" name="RePwd" type="password"
											placeholder="Password" class="col-xs-10 col-sm-5" onblur="checkNullOrNot3();"/>
										<span class="help-inline col-xs-12 col-sm-7"> <span
											class="middle" id="tishiRePwd" style="color: red;"></span>
										</span>
									</div>
								</div>
							</div> -->
						</div>
					</div>
					<div class="modal-footer bg-info">
						<input type="hidden" id="ID" name="ID" />
						<button id="inserUserBtn" type="button" onclick="subUserForm();" class="btn blue" disabled="disabled">确定</button>
						<button type="button" class="btn green" data-dismiss="modal">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="addQuanxian" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<i class="glyphicon glyphicon-pencil"></i> <span id="lblAddTitle"
							style="font-weight: bold">用户信息</span>
					</h4>
				</div>
				<form class="form-horizontal form-bordered form-row-strippe"
					id="ffAdd" action="" data-toggle="validator"
					enctype="multipart/form-data">
					<input type="hidden" id="deviceHiddenID" name="tId" /></input>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<div class="control-label col-md-3">用户姓名</div>
									<div class="col-md-9">
										<input id="quanxianRealName" name="tCheckCircle" type="text" readonly="readonly"/>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<div class="control-label col-md-3">权限</div>
									<div class="col-md-9">
										<select id="myquanxian" class="selectpicker"
											data-style="btn-primary" style="width: 100px;">
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer bg-info">
						<input type="hidden" id="roleUserId">
						<button type="button" onclick="updateUserRole();" class="btn blue">确定</button>
						<button type="button" class="btn green" data-dismiss="modal">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModalDelete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">删除消息</h4>
				</div>
				<div class="modal-body">确定要删除用户么？</div>
				<div class="modal-footer">
					<input type="hidden" id="delUserId">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary"
						onclick="deleteUser();">提交</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="myPwdReset" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">密码重置</h4>
				</div>
				<div class="modal-body">确定要重置密码么？</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary">提交</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

