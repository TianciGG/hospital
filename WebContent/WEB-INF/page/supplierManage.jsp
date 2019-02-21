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
	<title>供应商管理</title>
	<%@include file="header.jsp"%>
	<script type="text/javascript" src="js/logic/supplierManage.js"></script>
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
							<div class="table-header">供应商列表</div>
							<div class="widget-box">
								<div class="widget-body">
									<div class="widget-main">
										<form class="form-inline">
											供应商：<input type="text" id="searchSupportNameID"
												class="input-large" placeholder="供应商名称"
												onkeydown="KeyDown()" />
											<button type="button" class="btn btn-purple btn-sm"
												onclick="searchTable();">
												<i class="glyphicon glyphicon-search"></i> 查询
											</button>
											<button type="button" class="btn btn-sm btn-success"
												onclick="openAddWin();">
												<i class="glyphicon glyphicon-ok"></i> 添加
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
											<th class="center" data-field="agencyName">经销商名称</th>
											<th class="center" data-field="permitNo">营业执照编号</th>
											<th class="center" data-field="license">医疗器械经营许可证编号</th>
											<th class="center" data-field="agencyId"
												data-formatter="caozuo">操作</th>
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
	<!-- 附件列表 -->
	<div id="addAttWin" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<i class="glyphicon glyphicon-pencil"></i> <span id="lblAddTitle"
							style="font-weight: bold">供应商附件列表</span>
					</h4>
				</div>
				<form class="form-horizontal form-bordered form-row-strippe"
					id="ffAdd" action="" data-toggle="validator"
					enctype="multipart/form-data">
					<input type="hidden" id="deviceHiddenID" name="tId" /></input>
					<div class="modal-body">
						<div class="row">
							<div id="myattShowID"></div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">附件上传</label>
									<div class="col-md-9">
										<input id="myFujianID" name="myFileName" type="file"
											class="form-control" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer bg-info">
						<button type="button" onclick="myajaxUpload();" class="btn blue">上传</button>
						<button type="button" class="btn green" data-dismiss="modal">取消</button>
					</div>
				</form>
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
						<i class="glyphicon glyphicon-tag"></i> <span id="lblAddTitle"
							style="font-weight: bold">供应商信息</span>
					</h4>
				</div>
				<form class="form-horizontal form-bordered form-row-strippe"
					id="mySupportFormID" data-toggle="validator"
					enctype="multipart/form-data">
					<input type="hidden" id="deviceHiddenID" name="tId" /></input>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<div class="control-label col-md-3">供应商名称</div>
									<div class="col-md-9">
										<input id="tAgencyName" name="tAgencyName" type="text"
											class="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">营业执照编号</label>
									<div class="col-md-9">
										<input id="tPermitNo" name="tPermitNo" type="text"
											class="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">仪器经营许可证</label>
									<div class="col-md-9">
										<input id="tLicense" name="tLicense" type="text"
											class="form-control" />
									</div>
								</div>
							</div>

						</div>
					</div>
					<div class="modal-footer bg-info">
						<input type="hidden" id="ID" name="ID" />
						<button type="button" onclick="updateDic();" class="btn blue">确定</button>
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
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary"
						onclick="deleteAgency()">提交</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="myAttDeleteConfirm" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">删除消息</h4>
				</div>
				<div class="modal-body">确定要删除数据么？</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary"
						onclick="deleteAttByAttId()">提交</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

