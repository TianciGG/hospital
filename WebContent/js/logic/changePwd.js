//密码重置页面，原密码文本框，输入内容不能为空的事件
function checkNullOrNot() {
	if ($("#oldpwd").val().trim() == "") {
		$("#oldpwdmsg").html("原始密码不能为空！");
		$("#oldpwd").val("").focus();
		return false;
	}
	checkOldPwd();
}
// 密码重置页面，原密码文本框，验证输入内容是否正确的事件
function checkOldPwd() {
	$.ajax({
		cache : true,
		type : "GET",
		url : "checkOriginalPwd.do",
		data : {
			userId : $("#myuserid").val(),
			userPwd : $("#oldpwd").val()
		},
		async : false,
		success : function(data) {
			if (data == "allok") {
				$("#oldpwdmsg").html("原始密码正确！");
				$("#mysubbtn").attr("disabled", true);
			} else {
				$("#mysubbtn").attr("disabled", true);
				$("#oldpwdmsg").html("原始密码不正确！");
				$("#oldpwd").val("").focus();
				$("#newpwd1").val("");
				$("#newpwd2").val("");
			}
		}
	});
}
// 密码重置页面，新密码文本框，输入内容不能为空的事件
function checkNullOrNot2() {
	if ($("#newpwd1").val().trim() == "") {
		$("#newpwd1msg").html("新密码不能为空！");
		$("#newpwd1").val("");
		$("#mysubbtn").attr("disabled", true);
	} else {
		$("#newpwd1msg").html("");
		$("#mysubbtn").attr("disabled", true);
	}
}
// 密码重置页面，再次输入新密码文本框，输入内容不能为空的事件
function checkNullOrNot3() {
	if ($("#newpwd2").val().trim() == "") {
		$("#newpwd2msg").html("重复新密码不能为空！");
		$("#newpwd2").val("");
		$("#mysubbtn").attr("disabled", true);
	} else if ($("#newpwd1").val() != $("#newpwd2").val()) {
		$("#newpwd2msg").html("两次新密码输入不一致！");
		$("#mysubbtn").attr("disabled", true);
	} else {
		$("#newpwd2msg").html("");
		$("#mysubbtn").removeAttr("disabled");
	}
}
// 密码重置页面，提交按钮的事件
function mysubUpdate() {
		$('#myModal').modal('show');
}
// 密码重置页面，提交按钮打开页面的提交按钮的事件
function updatePwd() {
	$.ajax({
		type : "GET",
		url : "updateSysUser.do",
		data : {
			userId : $("#myuserid").val(),
			userPwd : $("#newpwd2").val()
		},
		async : false,
		success : function(data) {
			if (data == true) {
				alert("更新成功,请用新密码登录！");
				$('#myModal').modal('hide');
			} else {
				alert("更新失败，正在返回！");
				$('#myModal').modal('hide');
			}
		}
	});
}
//密码重置页面，重置按钮的事件
function addDisabled(){
	$("#mysubbtn").attr("disabled", true);
	$("#oldpwdmsg").html("原始密码不能为空！");
	$("#oldpwd").val("").focus();
}