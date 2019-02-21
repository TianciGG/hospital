//系统用户管理页面，页面加载完毕调用的事件
$(document).ready(function() {
	searchTable();
});
// 系统用户管理页面，查询的相关数据分页显示在页面上
function searchTable() {
	$("#tblResult").bootstrapTable('destroy');
	$("#tblResult").bootstrapTable({
		url : 'getAllSysUser.do',
		method : 'post',
		queryParams : function(params) {
			return {
				userName : $("#searchUserNameId").val(),
				status:$("#iStatus").val()
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
//系统用户管理页面，添加按钮的事件
function openAddUserWin(){
	$("#addComWin").modal("show");
	getUpdateDropDown('权限', 'iquanxian');
}
//系统用户管理页面，添加按钮打开页面用户名文本框，输入内容不能为空的事件
function checkNullOrNot() {
	if ($("#iuserName").val().trim() == "") {
		$("#tishiUserName").html("用户名不能为空！");
		$("#iuserName").val("").focus();
		$("#inserUserBtn").attr("disabled", true);
	}else{
		$("#tishiUserName").html("");
//		$("#inserUserBtn").attr("disabled", true);
		$("#inserUserBtn").removeAttr("disabled");
	}
}
//系统用户管理页面，添加按钮打开页面密码文本框，输入内容不能为空的事件
function checkNullOrNot2() {
	if ($("#iPwd").val().trim() == "") {
		$("#tishiPwd").html("密码不能为空！");
		$("#iPwd").val("");
		$("#inserUserBtn").attr("disabled", true);
//	} else {
//		$("#tishiPwd").html("");
//		$("#inserUserBtn").attr("disabled", true);
	}
}
//系统用户管理页面，添加按钮打开页面再次输入密码文本框，输入内容不能为空的事件
function checkNullOrNot3() {
	if ($("#iRePwd").val().trim() == "") {
		$("#tishiRePwd").html("重复新密码不能为空！");
		$("#iRePwd").val("");
		$("#inserUserBtn").attr("disabled", true);
	} else if ($("#iPwd").val() != $("#iRePwd").val()) {
		$("#tishiRePwd").html("两次新密码输入不一致！");
		$("#inserUserBtn").attr("disabled", true);
	} else {
		$("#tishiRePwd").html("");
		$("#inserUserBtn").removeAttr("disabled");
	}
}
//系统用户管理页面，添加按钮打开页面确定按钮的事件
function subUserForm(){
	$.ajax({
		cache : true,
		type : "POST",
		url : "addSysUser.do",
		data : {
			userName : $("#iuserName").val(),
			role : $("#iquanxian").val(),
//			userPwd: $("#iRePwd").val()
			userPwd: $("#iPwd").val()
		},
		async : true,
		success : function(data) {
			if (data == "allok") {
				$("#addComWin").modal("hide");
				searchTable();
				alert("添加成功，请在页面审核！")
			} else {
				$("#addComWin").modal("hide");
				searchTable();
				alert("添加失败，正在返回！");
			}
		}
	});
}
// 系统用户管理页面，分页选择项的自定义函数
var xuanze = function(value, row, index) {
	return "<label><input type='checkbox' class='acem' /><span class='lbl'></span></label>";
}
// 系统用户管理页面，分页操作项的自定义函数
var caozuo = function(value, row, index) {
	if (row.status == -1) {
		var shenhe = "<button class='btn btn-xs btn-warning' onclick='updateUserStatus(\""
				+ row.userId
				+ "\""
				+ ","
				+ "\""
				+ row.status
				+ "\");'>已屏蔽</button>";
	} else {
		var shenhe = "<button class='btn btn-xs btn-success' onclick='updateUserStatus(\""
				+ row.userId
				+ "\""
				+ ","
				+ "\""
				+ row.status
				+ "\");'>已激活</button>";
	}
	return "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>"
			+ shenhe
			+ "<button class='btn btn-xs btn-info' onclick='openQuanxianWin(\""
			+ row.userId
			+ "\",\""
			+ row.roleDicId
			+ "\",\""
			+ row.cname
			+ "\")'>权限分配</button>"
			+ "<button class='btn btn-xs btn-danger' onclick='pwdReset(\""
			+ row.userId + "\");'>密码重置	</button>"
			// + "<a class='red' href='javascript:openDeleteConfirmWin(\""
			// + row.userId
			// + "\");'><i class='glyphicon glyphicon-trash'></i></a>"
			+ "</div>"
}
// 系统用户管理页面，分页操作项的变更用户的审核状态按钮事件
function updateUserStatus(myuserid, mystatus) {
	$.ajax({
		type : "GET",
		url : "updateSysUser.do",
		data : {
			userId : myuserid,
			status : mystatus
		},
		async : false,
		success : function(data) {
			if (data == true) {
				searchTable();
				alert("变更成功，请在页面中查看！");
			} else {
				searchTable();
				alert("变更失败，正在返回！");
			}
		}
	});
}
// 系统用户管理页面，分页操作项的权限分配按钮打开权限分配界面
function openQuanxianWin(myuserId, dicCode, realname) {
	$('#addQuanxian').modal('show');
	$("#roleUserId").val(myuserId);
	getUpdateDropDown('权限', 'myquanxian', dicCode);
	$('#quanxianRealName').val(realname);
}
// 系统用户管理页面，权限分配页面获取当前活动行的相关值
function getUpdateDropDown(dicType, htmlObjId, pDicCode) {
	var htmlObj = document.getElementById(htmlObjId);
	var selectStr = "";
	$.post("logic/getDropDown.do", {
		type : dicType
	}, function(data) {
		$.each(data, function(index, item) {
			if (item.dicCode == pDicCode) {
				selectStr += '<option selected="selected" value="'
						+ item.dicCode + '">' + item.name + '</option>';
			} else {
				selectStr += '<option value="' + item.dicCode + '">'
						+ item.name + '</option>';
			}
		});
		htmlObj.innerHTML = selectStr;
	});
}
// 系统用户管理页面，权限分配页面的的确定按钮事件
function updateUserRole() {
	$.ajax({
		type : "GET",
		url : "updateSysUser.do",
		data : {
			userId : $("#roleUserId").val(),
			role : $("#myquanxian").val()
		},
		async : false,
		success : function(data) {
			if (data == true) {
				$('#addQuanxian').modal('hide');
				searchTable();
				alert("变更成功，请在页面中查看！");
			} else {
				$('#addQuanxian').modal('hide');
				searchTable();
				alert("变更失败，正在返回！");
			}
		}
	});
}
// 系统用户管理页面，分页操作项的密码重置按钮的事件
function pwdReset(myuserid) {
	$.ajax({
		type : "GET",
		url : "updateSysUser.do",
		data : {
			userId : myuserid,
			userPwd : '666666'
		},
		async : false,
		success : function(data) {
			if (data == true) {
				searchTable();
				alert("密码重置成功,请用新密码登陆！");
			} else {
				searchTable();
				alert("密码重置失败，正在返回！");
			}
		}
	});
}
// 系统用户管理页面，分页操作项的删除图标打开删除页面
function openDeleteConfirmWin(userid) {
	$('#delUserId').val(userid);
	$('#myModalDelete').modal('show');
}
// 系统用户管理页面，删除页面的提交按钮事件
function deleteUser() {
	var myuserid = $('#delUserId').val();
	$.ajax({
		type : "GET",
		url : "deleteSysUser.do",
		data : {
			userId : myuserid,
		},
		async : false,
		success : function(data) {
			if (data == 1) {
				$('#myModalDelete').modal('hide');
				searchTable();
				alert("删除成功，请在页面中查看！");
			} else {
				$('#myModalDelete').modal('hide');
				searchTable();
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