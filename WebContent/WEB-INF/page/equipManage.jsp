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
	<title>设备预警管理</title>
	<%@include file="header.jsp"%>
	<style type="text/css">
		.devicePic {
			width: 200px;
			height: 200px;
			border: 1px dotted;
		}
		.devicePic img {
			width: 100%;
			height: 100%;
		}
		.detailPicDiv {
			width: 400px;
			height: 300px;
			border: 1px dotted;
		}
		.detailPicDiv img {
			width: 100%;
			height: 100%;
		}
	</style>
	<script type="text/javascript">
		var elPersonCname="${myperson.cname}"
		var elPersonId="${myperson.presonId}"
	</script>
	<script type="text/javascript" src="js/logic/equipManage.js"></script>
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
							<div class="table-header">各科室仪器设备</div>
							<div class="widget-box">
								<div class="widget-body">
									<div class="widget-main">
										<form class="form-inline">
											科室：<select id="mykeshixiala" class="selectpicker"
												data-style="btn-primary" style="width: 100px;"
												onchange="searchTable()"></select> 仪器名称：<input type="text"
												id="deviceNameSearchID" class="input-small"
												placeholder="仪器名称" style="width: 300px;"
												onkeydown="KeyDown()" />
											<button type="button" class="btn btn-purple btn-sm"
												onclick="searchTable()">
												<i class="glyphicon glyphicon-search"></i> 查询
											</button>
											<button type="button" class="btn btn-sm btn-success"
												onclick="openAddWin();">
												<i class="glyphicon glyphicon-ok"></i> 添加
											</button>
											<button type="button" class="btn btn-info btn-sm"
												onclick="exportExcel();">
												导出Excel<a id="myhref"></a>
											</button>
											<button type="button" class="btn btn-info btn-sm"
												onclick="openExcelImportDialog();">导入Excel</button>
											<a href="uploadFile/设备导入模板.xlsx">导入模板下载</a>
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
											<th class="center" data-field="dicCode">科室</th>
											<th class="center" data-field="deviceName">仪器名称</th>
											<th class="center" data-field="buyDate">仪器购买日期</th>
											<th class="center" data-field="checkDate">上次年检时间</th>
											<th class="center" data-field="checkCircle">年检周期(天)</th>
											<th class="center" data-field="sid" data-formatter="juli">距离下次检测(天)</th>
											<th class="center" data-formatter="tiqian">提前预警(天)</th>
											<th class="center" data-formatter="caozuo">操作</th>
											<th class="center" data-field="equipId"
												data-formatter="xiangqing">仪器详细信息</th>
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

	<!--------------------------添加设备信息---------------------------->
	<div id="addWin" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<i class="glyphicon glyphicon-pencil"></i><span id="lblAddTitle"
							style="font-weight: bold">添加设备信息</span>
					</h4>
				</div>
				<form class="form-horizontal form-bordered form-row-strippe"
					id="addDeviceMyForm">
					<input type="hidden" id="deviceHiddenID" name="tEquipId" /></input>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2">科室</label>
									<div class="col-md-2">
										<select id="myAddkeshixiala" name="tDicCode"
											class="selectpicker" data-style="btn-primary"
											style="width: 100px;"></select>
									</div>

									<label class="control-label col-md-2">供应商</label>

									<div class="col-md-6">
										<select id="myAddGongYingshangxiala" name="tAgencyId"
											class="selectpicker" data-style="btn-primary"
											style="width: 260px;"></select>

									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2">仪器名称</label>
									<div class="col-md-10">
										<input id="tDeviceNameID" name="tDeviceName" type="text"
											class="form-control" placeholder="名称..." />
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2">购买日期</label>
									<div class="col-md-10">
										<div id="mydatepicker"
											class="input-group date form_date col-md-10"
											data-date-format="yyyy-MM-dd " data-link-field="dtp_input2"
											data-link-format="yyyy-mm-dd">
											<input id="myupdateBuyDateId" name="tBuyDate"
												class="form-control" size="16" type="text" > <span
												class="input-group-addon"><span
												class="glyphicon glyphicon-calendar"></span></span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2">提前预警</label>
									<div class="col-md-4">
										<input id="tiqianYujingID" name="tDnumber" type="number"
											class="form-control" placeholder="单位：天" />
									</div>

									<label class="control-label col-md-2">检修周期</label>
									<div class="col-md-4">
										<input id="tCheckCircleID" name="tCheckCircle" type="number"
											class="form-control" placeholder="单位：天" />
									</div>
								</div>
							</div>

						</div>
					</div>
					<div class="modal-footer bg-info">
						<input type="hidden" id="ID" name="ID" />
						<button type="button" onclick="subForm();" class="btn blue">确定</button>
						<button type="button" class="btn green" data-dismiss="modal">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="updateWin" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<i class="glyphicon glyphicon-pencil"></i> <span id="lblAddTitle"
							style="font-weight: bold">编辑设备信息</span>
					</h4>
				</div>
				<form class="form-horizontal form-bordered form-row-strippe"
					id="updateDeviceMyForm">
					<input type="hidden" id="udeviceHiddenID" name="tEquipId" /></input>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2">科室</label>
									<div class="col-md-2">
										<select id="umyAddkeshixiala" name="tDicCode"
											class="selectpicker" data-style="btn-primary"
											style="width: 100px;"></select>
									</div>

									<label class="control-label col-md-2">供应商</label>
									<div class="col-md-6">
										<select id="GongYingshangxialaUpdate" name="tAgencyId"
											class="selectpicker" data-style="btn-primary"
											style="width: 260px;"></select>

									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2">仪器名称</label>
									<div class="col-md-10">
										<input id="uDeviceNameID" name="tDeviceName" type="text"
											class="form-control" placeholder="名称..." />
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2">购买日期</label>
									<div class="col-md-10">
										<div id="umydatepicker"
											class="input-group date form_date col-md-10"
											data-date-format="yyyy-MM-dd " data-link-field="dtp_input2"
											data-link-format="yyyy-mm-dd">
											<input id="umyupdateBuyDateId" name="tBuyDate"
												class="form-control" size="16" type="text" >
											<span class="input-group-addon"><span
												class="glyphicon glyphicon-calendar"></span></span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2">提前预警</label>
									<div class="col-md-4">
										<input id="utiqianYujingID" name="tDnumber" type="number"
											class="form-control" placeholder="单位：天" />
									</div>

									<label class="control-label col-md-2">检修周期</label>
									<div class="col-md-4">
										<input id="utCheckCircleID" name="tCheckCircle" type="number"
											class="form-control" placeholder="单位：天" />
									</div>
								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2">设备图片</label>
									<div class="col-md-10" style="width: 300px;">
										<div class="devicePic">
											<input id="fileToUpload" style="display: none" type="file"
												name="devicePicName"> <img id="mypicID" src=""
												alt="无图片" /> <input type="button" value="选择图片"
												onclick="choosePic()"> <input type="button"
												value="上传" onclick="ajaxUpload()">
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
					<div class="modal-footer bg-info">
						<input type="hidden" id="ID" name="ID" />
						<button type="button" onclick="updateSubForm();" class="btn blue">确定</button>
						<button type="button" class="btn green" data-dismiss="modal">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 设备详细情况 -->
	<div id="addComWin" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<i class="glyphicon glyphicon-pencil"></i> <span id="lblAddTitle"
							style="font-weight: bold">设备详细情况</span>
					</h4>
				</div>
				<form class="form-horizontal form-bordered form-row-strippe"
					id="addSupport" data-toggle="validator">
					<input type="hidden" id="deviceHiddenID" name="tEquipId" /></input>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<div class="control-label col-md-3">供应商名称</div>
									<div class="col-md-9">
										<input id="tAgencyNameID" name="tCheckCircle" type="text"
											class="form-control" disabled="disabled"/>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">营业执照编号</label>
									<div class="col-md-9">
										<input id="tPermitNoID" name="tCheckCircle" type="text"
											class="form-control" disabled="disabled"/>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">仪器经营许可证</label>
									<div class="col-md-9">
										<input id="tLicenseID" name="tCheckCircle" type="text"
											class="form-control" disabled="disabled"/>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">仪器图片</label>
									<div class="col-md-9">
										<div class="detailPicDiv">
											<img id="myDetailPicID" src="" alt="无图片" />
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer bg-info">
						<input type="hidden" id="ID" name="ID" />
						<button type="button" id="supportBtnID" onclick="subForm();"
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
					<h4 class="modal-title" id="myModalLabel">报废设备</h4>
				</div>
				<div class="modal-body">确定要报废设备么？</div>
				<div class="modal-footer">
					<input type="hidden" id="mydeleteDeviceHiddenID">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" onclick="UpdateWin()">
						确定</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 检修完成确认 -->
	<div class="modal fade" id="myFixModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">设备检修消息</h4>
				</div>
				<div class="modal-body">设备已经检修完成？</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary">提交</button>
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
							style="font-weight: bold">确认检修设备</span>
					</h4>
				</div>
				<form class="form-horizontal form-bordered form-row-strippe"
					id="addLog" data-toggle="validator">
					<input type="hidden" id="deviceLogHiddenID" name="tId" /></input>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<div class="control-label col-md-3">设备名称</div>
									<div class="col-md-9">
										<input id="fixequipNameID" name="tCheckCircle" type="text"
											class="form-control" disabled />
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">维修人员</label>
									<div class="col-md-9">
										<input id="fixpersonNameId" name="tCheckCircle" type="text"
											class="form-control" disabled />
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">维修日期</label>
									<div class="col-md-9">
										<div id="mydatepickerLog"
											class="input-group date form_date col-md-10"
											data-date-format="yyyy-MM-dd " data-link-field="dtp_input2"
											data-link-format="yyyy-mm-dd">
											<input name="tBuyDate" id="equipLogDateId"
												class="form-control" size="16" type="text" >
											<span class="input-group-addon"><span
												class="glyphicon glyphicon-calendar"></span></span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">维修日志记录</label>
									<div class="col-md-9">
										<textarea class="form-control" rows="10" id="fixcontentId"></textarea>
									</div>
								</div>
							</div>

						</div>
					</div>
					<div class="modal-footer bg-info">
						<input type="hidden" id="elogID" name="ID" />
						<button type="button" id="myupdateSubID" onclick="submitLog();"
							class="btn blue">确定</button>
						<button type="button" class="btn green" data-dismiss="modal">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="openExcelImport" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<i class="glyphicon glyphicon-pencil"></i> <span id="lblAddTitle"
							style="font-weight: bold">Excel文件导入</span>
					</h4>
				</div>
				<form class="form-horizontal form-bordered form-row-strippe"
					id="excelidform" data-toggle="validator">
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">Excel文件导入</label>

									<div class="col-md-9">
										<input id="excelToUpload" name="myExcelFile" type="file"
											class="form-control" />
									</div>

								</div>
							</div>

						</div>
					</div>
					<div class="modal-footer bg-info">
						<button type="button" id="myupdateSubID"
							onclick="ajaxUploadExcel();" class="btn blue">导入</button>
						<button type="button" class="btn green" data-dismiss="modal">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

