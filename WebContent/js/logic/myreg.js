//注册页面，提交按钮产生的事件
function myreg() {
	if ($("#myUserName").val().trim() == "") {
		alert("用户名不能为空");
	} else if ($("#myPwd").val().trim() == "") {
		alert("密码不能为空");
	} else {
		$.ajax({
			cache : true,
			type : "POST",
			url : "addSysUser.do",
			data : {
				userName : $("#myUserName").val(),
				userPwd : $("#myPwd").val(),
				mobile : $("#tMobileID").val(),
				email : $("#tEmailID").val()
			},
			async : true,
			success : function(data) {
				if (data == "allok") {
					alert("注册成功，请等待审核！")
				} else {
					alert("注册失败");
				}
			}
		});
	}
}