var code;
// 登录页面，用于产生验证码的是随机数
function createCode() {
	code = "";
	var codelength = 6;// 验证码长度
	var checkCode = document.getElementById("checkCode");
	var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'a', 'b', 'c', 'd',
			'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
	for (var i = 0; i < codelength; i++) {
		var charNum = Math.floor(Math.random() * 52);
		code += codeChars[charNum];
	}
	if (checkCode) {
		checkCode.className = "code";
		checkCode.innerHTML = code;
	}
}
// 登录页面，对用户名、密码、验证码的约束，只有都不能为空时才允许进行登录操作
function validateCode() {
	var inputCode = document.getElementById("inputCode").value;
	if ($("#myUserName").val().trim() == "") {
		alert("用户名不能为空");
		$("#myUserName").val("").focus();
		return false;
	} else if ($("#myPwd").val().trim() == "") {
		alert("密码不能为空");
		$("#myPwd").val("").focus();
		return false;
	} else if ($("#inputCode").val().trim() == "") {
		alert('验证码不能为空！');
		$("#inputCode").val("").focus();
		return false;
	} else if ($("#inputCode").val().toUpperCase() != code.toUpperCase()) {
		alert('验证码输入错误！');
		createCode();
		$("#inputCode").val("").focus();
		return false;
	} else {
		return true;
	}
}
// 登录页面，登录按钮事件
function mylogin() {
	if (validateCode()) {
		$.ajax({
			cache : true,
			type : "GET",
			url : "mylogin.do",
			data : {
				userName : $("#myUserName").val(),
				userPwd : $("#myPwd").val()
			},
			async : true,
			success : function(data) {
				if (data == "allok") {
					window.location.href = "goToMainPage.do";
				} else {
					alert("登陆失败");
				}
			}
		});
	}
}
// 登录页面，注册按钮事件
function myreg() {
	window.location.href = "goToMyRegPage.do"
}
// 键盘enter建登录的事件
function KeyDown() {
	if (event.keyCode == 13) {
		event.returnValue = false;
		event.cancel = true;
		mylogin();
	}
}
