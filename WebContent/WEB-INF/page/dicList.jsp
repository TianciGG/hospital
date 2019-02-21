<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="base.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width,initial-scale=1.0" />
	<title>数据字典</title>
	<%@include file="header.jsp"%>
	<link href="css/dicList.css" rel="stylesheet" type="text/css" media="all" />
	<script type="text/javascript" src="js/logic/dicList.js"></script>
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
							<div class="table-header">数据字典列表</div>
							<div class="widget-box">
								<div class="widget-body">
									<div class="widget-main">
										<form class="form-inline">
											数据项名称：<input type="text" id="myshujuxiang"
												class="input-large" placeholder="数据项名称"
												onkeydown="KeyDown()" /> 数据类型：<select id="myshujuTypeId1"
												class="selectpicker" data-style="btn-primary"
												onChange="searchTable();">
											</select>
											<button type="button" class="btn btn-purple btn-sm"
												onclick="searchTable();">
												<i class="glyphicon glyphicon-search"></i>查询
											</button>
											<button type="button" class="btn btn-sm btn-success"
												onclick="openAddDicWin();">
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
											<th class="center" data-formatter="xuhao">序号</th>
											<th class="center" data-field="name">数据项名称</th>
											<th class="center" data-field="type">数据类型</th>
											<th class="center" data-field="dicId" data-formatter="caozuo">操作</th>
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
	<div id="addDicWin" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLable" aria-hidden="true">
		<input type="hidden" id="hdic_id" /> <input type="hidden"
			id="hdic_code_id" />
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<i class="glyphicon glyphicon-pencil"></i> <span id="lblAddTitle">数据字典</span>
					</h4>
				</div>
				<form class="form-horizontal form-bordered form-row-strippe"
					id="ffAdd" action="" data-toggle="validator"
					enctype="multipart/form-data">
					<input type="hidden" id="deviceHiddenID" name="tId" />
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<div class="control-label col-md-3">数据项名称</div>
									<div class="col-md-9">
										<input id="addShujuxiangid" name="tCheckCircle" type="text"
											class="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">数据项类型</label>
									<div class="col-md-9">
										<select id="myshujuTypeId2" class="selectpicker"
											data-style="btn-primary">
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer bg-info">
						<input type="hidden" id="ID" name="ID" />
						<button type="button" onclick="updateDic();" class="btn blue">确定</button>
						<button type="button" onclick="clearDisabled();" class="btn green"
							data-dismiss="modal">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 删除确认 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLable" aria-hidden="true">
		<input type="hidden" id="delDicHiddenID" />
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">删除消息</h4>
				</div>
				<div class="modal-body">确认要删除数据吗？</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="deleteDicCode()">提交</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>