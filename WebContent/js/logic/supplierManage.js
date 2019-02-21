//供应商管理页面，页面加载完毕调用的事件
$(document).ready(function() {
	searchTable();
});
var agencyID = "";
var attID = "";
// 供应商管理页面，查询的相关数据分页显示在页面上
function searchTable() {
	$("#tblResult").bootstrapTable('destroy');
	$("#tblResult").bootstrapTable({
		url : 'logic/getAllAgency.do',
		method : 'post',
		queryParams : function(params) {
			return {
				agencyName : $("#searchSupportNameID").val()
			}
		},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		pagination : true, // 开启分页
		pageNumber : 1,// 默认加载页
		pageSize : 6,// 每页数据
		pageList : [ 3, 6, 9 ]
	// 可选的每页数据
	});
}
// 供应商管理页面，添加按钮触发的事件
function openAddWin(agencyIDPara) {
	$("#mySupportFormID")[0].reset();
	agencyID = agencyIDPara;
	$('#addComWin').modal('show');
}
// 供应商管理页面，添加按钮、分页操作项的编辑图标打开的添加页面的确定按钮事件
function updateDic() {
	if ($("#tAgencyName").val().trim() == "") {
		alert("供应商名称不能为空");
		$("#tAgencyName").val("").focus();
		return false;
	} else if ($("#tPermitNo").val().trim() == "") {
		alert("营业执照编号不能为空");
		$("#tPermitNo").val("").focus();
		return false;
	} else if ($("#tLicense").val().trim() == "") {
		alert("仪器经营许可证不能为空");
		$("#tLicense").val("").focus();
		return false;
	} else {
		$.ajax({
			type : "GET",
			url : "logic/addOrUpdateAgency.do",
			data : {
				agencyId : agencyID,
				agencyName : $("#tAgencyName").val(),
				permitNo : $('#tPermitNo').val(),
				license : $("#tLicense").val()
			},
			async : false,
			success : function(data) {
				if (data == "allok") {
					$('#addComWin').modal('hide');
					searchTable();
					alert("更新成功，请在页面中查看！");
				} else {
					$('#addComWin').modal('hide');
					searchTable();
					alert("更新失败，正在返回！");
				}
			}
		});
	}
}
// 供应商管理页面，分页选择项的自定义函数
var xuanze = function(value, row, index) {
	return "<label><input type='checkbox' class='acem' /><span class='lbl'></span></label>";
}
// 供应商管理页面，分页序号项的自定义函数
var xuhao = function(value, row, index) {
	return index + 1;
}
// 供应商管理页面，分页操作项的自定义函数
var caozuo = function(value, row, index) {
	return "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'><button class='btn btn-xs btn-success' onclick='openUpdateWin(\""
			+ value
			+ "\");'>编辑内容</button>"
			// +"<a class='red' href='javascript:openDeleteConfirmWin(\""
			// + value
			// + "\");'><i class='glyphicon glyphicon-trash'></i></a> "
			+ "<button class='btn btn-xs btn-info' onclick='openAttWin(\""
			+ value + "\");'>附件列表 </button>" + "</div>";
}
// 供应商管理页面，分页操作项的编辑图标的事件
function openUpdateWin(dic_ic) {
	$.post("logic/getOneAgency.do", {
		agencyId : dic_ic
	}, function(data) {
		agencyID = data.agencyId;
		$('#tPermitNo').val(data.permitNo);
		$('#tLicense').val(data.license);
		$('#tAgencyName').val(data.agencyName);
	});
	$('#addComWin').modal('show');
}
// 供应商管理页面，分页操作项的删除图标的事件
function openDeleteConfirmWin(dic_id) {
	agencyID = dic_id;
	$('#myModal').modal('show');
}
// 供应商管理页面，分页操作项的删除图标打开页面的提交按钮的事件
function deleteAgency() {
	$.ajax({
		type : "GET",
		url : "logic/deleteOneAgency.do",
		data : {
			agencyId : agencyID
		},
		async : false,
		success : function(data) {
			if (data == "allok") {
				$('#myModal').modal('hide');
				searchTable();
				alert("删除成功，请在页面中查看！");
			} else {
				$('#myModal').modal('hide');
				searchTable();
				alert("删除失败，正在返回！");
			}
		}
	});
}
// 供应商管理页面，分页操作项的附件列表图标的事件
function openAttWin(agency_Id) {
	agencyID = agency_Id;
	loadAttPage(agencyID);
	$('#addAttWin').modal('show');
}
// 供应商管理页面，分页操作项的附件列表图标打开页面的事件
function loadAttPage(myagencyId) {
	var mycontent = document.getElementById("myattShowID");
	var htmls = "";
	mycontent.innerHTML = "";
	$.get("logic/getAllAtt.do",{
		agencyId : myagencyId
		},function(data) {
			$.each(data,function(index, item) {
				var no = index + 1;
				htmls += "<div class='col-md-12'><div class='form-group'><div class='control-label col-md-3'></div><div class='col-md-9'>"
						+ "<a href='logic/myDownLoadFile.do?attname="
						+ item.attname
						+ "'>"
						+ no
						+ "."
						+ item.attname
						+ "</a>"
						+ "<a class='red' href='javascript:openDeleteConfirmATTWin(\""
						+ item.attid
						+ "\");'><i class='glyphicon glyphicon-trash'></i></a></div></div></div>";
			});
		mycontent.innerHTML = htmls;
	});
}
// 供应商管理页面，分页操作项的附件列表图标打开页面上传按钮的事件
function myajaxUpload() {
	$.ajaxFileUpload({
		url : 'logic/myUploadFile.do',
		secureuri : false,
		fileElementId : 'myFujianID',// file标签的id
		dataType : 'text',// 返回数据的类型
		data : {
			myObjectID : agencyID,
			attType : 'fujian'
		},// 一同上传的数据
		success : function(data, status) {
			loadAttPage(agencyID);
			alert("上传成功，请在页面中查看！");
		}
	});
}
// 供应商管理页面，分页操作项的附件列表图标打开页面删除图标的事件
function openDeleteConfirmATTWin(attid) {
	attID = attid;
	$('#myAttDeleteConfirm').modal('show');
}
// 供应商管理页面，分页操作项的附件列表图标打开页面删除图标打开页面的提交按钮的事件
function deleteAttByAttId() {
	$.ajax({
		type : "GET",
		url : "logic/deleteOneAtt.do",
		data : {
			attid : attID
		},
		async : false,
		success : function(data) {
			if (data == "allok") {
				$('#myAttDeleteConfirm').modal('hide');
				loadAttPage(agencyID);
				alert("删除成功，请在页面中查看！");
			} else {
				$('#myAttDeleteConfirm').modal('hide');
				loadAttPage(agencyID);
				alert("删除失败，正在返回！");
			}
		}
	});
}
// 键盘enter建查询的事件
function KeyDown() {
	if (event.keyCode == 13) {
		event.returnValue = false;
		event.cancel = true;
		searchTable();
	}
}