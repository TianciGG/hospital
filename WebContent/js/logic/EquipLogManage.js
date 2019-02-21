//设备日志管理页面，页面加载完毕调用的事件
$(document).ready(function() {
	searchTable();
	if (elUserRole=="维护人员") {
		$('#fixdateID').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
	}
});
// 设备日志管理页面，分页显示相关查询数据
function searchTable() {
	$("#tblResult").bootstrapTable('destroy');
	$("#tblResult").bootstrapTable({
		url : 'logic/getAllLog.do',
		method : 'post',
		queryParams : function(params) {
			return {
				equipName : $("#myEquipNameId").val(),
				presonId : $("#myEquipFixPersonId").val()
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
// 设备日志管理页面，分页选择项的自定义函数
var xuanze = function(value, row, index) {
	return "<label><input type='checkbox' class='acem' /><span class='lbl'></span></label>";
}
// 设备日志管理页面，分页序号项的自定义函数
var xuhao = function(value, row, index) {
	return index + 1;
}
// 设备日志管理页面，分页操作项的自定义函数
var caozuo = function(value, row, index) {
	if (elUserRole=="管理员") {
		return "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>"
				+ "<button class='btn btn-xs btn-info' onclick='openAttWin(\""
				+ value + "\");'>详细情况 </button>" 
//				+"<a class='red' href='javascript:openDeleteConfirmWin(\""
//				+ value
//				+ "\");'><i class='glyphicon glyphicon-trash'></i></a>"
				+"</div>";
	} else if (elUserRole=="维护人员") {
		return "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" 
				+"<button class='btn btn-xs btn-success' onclick='openAddWin(\""
				+ value + "\");'>编辑内容</button>" 
//				+"<a class='red' href='javascript:openDeleteConfirmWin(\""
//				+ value
//				+ "\");'><i class='glyphicon glyphicon-trash'></i></a>"
				+"</div>";
	} else {
		return "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>"
				+ "<button class='btn btn-xs btn-info' onclick='openAttWin(\""
				+ value + "\");'>详细情况 </button>"
//				+"<a class='red' href='javascript:openDeleteConfirmWin(\""
//				+ value
//				+ "\");'><i class='glyphicon glyphicon-trash'></i></a>"
				+"</div>";
	}
}
// 设备日志管理页面，分页操作项的编辑内容按钮的事件
function openAddWin(logid) {
	$('#equipLogDateId').removeAttr("disabled");
	$('#fixcontentId').removeAttr("disabled");
	$.get("logic/getOneLog.do", {
		logId : logid
	}, function(data) {
		$('#elogID').val(data.logId);
		$('#equipNameID').val(data.equipName);
		$('#epersonName').val(data.presonId);
		$('#equipLogDateId').val(data.logAddtime);
		$('#fixcontentId').val(data.fixContent);
		$("#deviceHiddenID").val(data.equipId);
	});
	$('#addLogWin').modal('show');
}
// 设备日志管理页面，分页操作项的编辑内容按钮打开页面的确定按钮的事件
function updateLog() {
	if ($("#fixcontentId").val().trim() == "") {
		alert("维修日志记录不能为空");
		$("#fixcontentId").val("").focus();
		return false;
	} else {
		$.ajax({
			type : "POST",
			url : "logic/addOrUpdateLog.do",
			data : {
				logId : $('#elogID').val(),
				logAddtime : $('#equipLogDateId').val(),
				fixContent : $('#fixcontentId').val(),
				equipId : $('#deviceHiddenID').val()
			},
			async : false,
			success : function(data) {
				if (data == "allok") {
					$('#addLogWin').modal('hide');
					searchTable();
					alert("更新成功！");
				} else {
					$('#addLogWin').modal('hide');
					searchTable();
					alert("更新失败！");
				}
			}
		});
	}
}
// 设备日志管理页面，分页操作项的详细情况按钮的事件
function openAttWin(logid) {
	openAddWin(logid);
	$('#equipNameID').attr("disabled", true);
	$('#epersonName').attr("disabled", true);
	$('#equipLogDateId').attr("disabled", true);
	$('#fixcontentId').attr("disabled", true);
}
// 设备日志管理页面，分页操作项的删除图标的事件
function openDeleteConfirmWin(logid) {
	$('#deleteLogID').val(logid);
	$('#myModal').modal('show');
}
// 设备日志管理页面，分页操作项的删除图标打开页面的提交按钮的事件
function deleteLog() {
	$.ajax({
		type : "GET",
		url : "logic/deleteOneLog.do",
		data : {
			logId : $('#deleteLogID').val()
		},
		async : false,
		success : function(data) {
			if (data == "allok") {
				$('#myModal').modal('hide');
				searchTable();
				alert("删除成功！");
			} else {
				$('#myModal').modal('hide');
				searchTable();
				alert("删除失败！");
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
