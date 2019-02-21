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
	<title>个人中心</title>
	<%@include file="header.jsp"%>
	<link href="css/personalCenter.css" rel="stylesheet" type="text/css" media="all" />
	<script type="text/javascript" charset="UTF-8">
		var elPersonDicId = "${myperson.dicId}";
		var elPersonGender = "${myperson.gender}";
		var elPersonId = "${myperson.presonId}";
		var elAttUpdateID = "${myperson.attid}";
		var elPersonAtturl = "${myperson.atturl}";
		var elPersonAttname = "${myperson.attname}";
	</script>
	<script type="text/javascript" src="js/logic/personalCenter.js" charset="UTF-8"></script>
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
							<div class="table-header">个人中心</div>
							<div class="hr hr-24"></div>
							<div class="space-24"></div>
							<form id="personForm" class="form-horizontal"
								style="margin-top: -20px; margin-left: 180px;">
								<input type="hidden" id="myuserId" value="${myuser.userId}" />
								<input type="hidden" id="mypersonId"
									value="${myperson.presonId}" />
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> 用户姓名:</label>
									<div class="col-sm-9">
										<input type="text" id="mytCname" placeholder="中文姓名"
											class="col-xs-10 col-sm-5" value="${myperson.cname}" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1">性别:</label>
									<div class="col-sm-9">
										<select class="selectpicker" id="mygender"
											data-style="btn-primary">
											<option value="男">男</option>
											<option value="女">女</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1">角色:</label>
									<div class="col-sm-9">
										<input type="text" id="juese" class="col-xs-10 col-sm-5"
											value="${myuser.role}" disabled />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> 手机号:</label>
									<div class="col-sm-9">
										<input type="text" id="mobilenum" name="person.tMobile"
											placeholder="请输入个人手机号" class="col-xs-10 col-sm-5"
											value="${myperson.mobile}" />
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3" style="left: 15px">科室:</label>
									<div class="col-md-9">
										<select id="mykeshixiala" class="selectpicker"
											data-style="btn-primary">
										</select>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-2"> 电子邮件:</label>
									<div class="col-sm-9">
										<input type="text" id="myemail" name="person.tEmail"
											placeholder="电子邮件地址" class="col-xs-10 col-sm-5"
											value="${myperson.email}" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="control-label col-md-3" style="left: 15px">个人头像:</label>
									<div class="col-md-9" style="width: 300px;">
										<div class="devicePic">
											<input id="fileToUpload" type="file" name="devicePicName" />
											<img id="mypicID"
												src="${myperson.atturl}/${myperson.attname}" alt="无头像" /> <input
												type="button" value="选择图片" onclick="choosePic()" /> <input
												type="button" value="上传" onclick="ajaxUpload()" />
										</div>
									</div>
								</div>
								<div class="clearfix form-actions"
									style="margin-top: 50px; margin-left: -180px;">
									<div class="col-md-offset-3 col-md-9"
										style="margin-left: 375px;">
										<button class="btn btn-info" id="mysub"
											onclick="updatePerson();">
											<i class="glyphicon glyphicon-ok"></i> 提交
										</button>
										&emsp; &emsp;&emsp;
										<button class="btn" type="reset">
											<i class="glyphicon glyphicon-refresh"></i> 重置
										</button>
									</div>
								</div>
								<div class="hr hr-24" style="margin-left: -180px;"></div>
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
					<h4 class="modal-title" id="myModalLabel">删除消息</h4>
				</div>
				<div class="modal-body">确定要删除数据么？</div>
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