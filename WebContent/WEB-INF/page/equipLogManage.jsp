<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="base.jsp" />
	<meta charset="utf-8" />
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>设备日志管理</title>
	<%@include file="header.jsp"%>
	<script type="text/javascript">
		var elUserRole="${myuser.role}";
	</script>
	<script type="text/javascript" src="js/logic/EquipLogManage.js"></script>
</head>
<body>
	<%@include file="headerDiv.jsp"%>
	<div class="main-container" style="height: 600px;">
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
							<div class="table-header">设备维护日志</div>
							<div class="widget-box">

								<div class="widget-body">
									<div class="widget-main">
										<form class="form-inline">
											设备名称：<input type="text" id="myEquipNameId"
												class="input-large" placeholder="设备名称" onkeydown="KeyDown()" />
											维修人员：<input type="text" id="myEquipFixPersonId"
												class="input-large" placeholder="维修人员姓名"
												onkeydown="KeyDown()" />

											<button type="button" class="btn btn-purple btn-sm"
												onclick="searchTable()">
												<i class="glyphicon glyphicon-search"></i> 查询
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
											<th class="center" data-formatter="xuhao">序号</th>
											<th class="center" data-field="equipName">设备名称</th>
											<th class="center" data-field="presonId">维护人员</th>
											<th class="center" data-field="logAddtime">维护日期</th>
											<th class="center" data-field="logId" data-formatter="caozuo">操作</th>
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


	<div id="addLogWin" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<i class="glyphicon glyphicon-pencil"></i> <span id="lblAddTitle"
							style="font-weight: bold">设备维修日志信息</span>
					</h4>
				</div>
				<form class="form-horizontal form-bordered form-row-strippe"
					id="ffAdd" action="" data-toggle="validator"
					enctype="multipart/form-data">
					<input type="hidden" id="deviceHiddenID" /></input>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<div class="control-label col-md-3">设备名称</div>
									<div class="col-md-9">
										<input id="equipNameID" name="tCheckCircle" type="text"
											class="form-control" disabled="disabled" />
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">维修人员</label>
									<div class="col-md-9">
										<input id="epersonName" name="tCheckCircle" type="text"
											class="form-control" disabled="disabled" />
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">维修日期</label>
									<div class="col-md-9">
										<div id="fixdateID"
											class="input-group date form_date col-md-10"
											data-date-format="yyyy-MM-dd " data-link-field="dtp_input2"
											data-link-format="yyyy-mm-dd">
											<input name="tBuyDate" id="equipLogDateId"
												class="form-control" size="16" type="text"
												disabled="disabled"> <span class="input-group-addon"><span
												class="glyphicon glyphicon-calendar"></span></span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">维修日志记录</label>
									<div class="col-md-9">
										<textarea class="form-control" rows="10" id="fixcontentId" disabled="disabled"></textarea>
									</div>
								</div>
							</div>

						</div>
					</div>
					<div class="modal-footer bg-info">
						<input type="hidden" id="elogID" name="ID" />
						<button type="button" id="myupdateSubID" onclick="updateLog();"
							class="btn blue">确定</button>
						<button type="button" class="btn green" data-dismiss="modal">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 删除确认 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">删除消息</h4>
				</div>
				<div class="modal-body">确定要删除数据么？</div>
				<div class="modal-footer">
					<input type="hidden" id="deleteLogID" />
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" onclick="deleteLog()">
						提交</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

