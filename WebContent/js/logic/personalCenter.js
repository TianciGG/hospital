//个人中心页面，页面加载完毕调用的事件
$(document).ready(function() {
	getUpdateDropDown('科室', 'mykeshixiala', elPersonDicId);
	$("#mygender").val(elPersonGender);
	var imgURL = elPersonAtturl + "/" + elPersonAttname;
	if (imgURL != "/") {
		$("#myHeadIconID").attr("src", imgURL);
	}
});
// 个人中心页面，用于为科室的下拉列表提供支持的事件
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
// 个人中心页面，选择图片按钮的事件
function choosePic() {
	$("#fileToUpload").click();
}
// 个人中心页面，上传按钮的事件
function ajaxUpload() {
	$.ajaxFileUpload({
		url : 'logic/myUpload.do',
		secureuri : false,
		fileElementId : 'fileToUpload',// file标签的id
		dataType : 'json',// 返回数据的类型
		data : {
			deviceId : elPersonId,
			mytAttid : elAttUpdateID,
			attType : 'headicon'
		},// 一同上传的数据
		success : function(data, status) {
			$("#mypicID").attr("src", data.atturl + data.attname);
			alert('上传成功!');
		}
	});
}
// 个人中心页面，提交按钮的事件
function updatePerson() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "logic/updatePerson.do",
		data : {
			userId : $("#myuserId").val(),
			presonId : $("#mypersonId").val(),
			cname : $("#mytCname").val(),
			mobile : $("#mobilenum").val(),
			email : $("#myemail").val(),
			gender : $("#mygender").val(),
			dicId : $("#mykeshixiala").val(),
		},
		async : false,
		success : function(data) {
			if (data == true) {
				alert('更新成功!');
				window.location.href = "goToPersonalCenterPage.do";
			} else {
				alert('更新失败!');
			}
		}
	});
}
