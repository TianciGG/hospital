//数据字典管理页面，页面加载完毕调用的事件
$(document).ready(function() {
	searchTable();
	selectDicType1();
	selectDicType2();
});
// 数据字典管理页面，给数据类型下拉列表赋值
var selectDicType1 = function() {
	$.post("logic/selectDicType.do", {}, function(data) {
		var tmp1 = "<option value=''>全部</option>";
		$.each(data, function(index, item) {
			tmp1 += "<option value='" + item.type + "'>" + item.type
					+ "</option>";
		});
		$("#myshujuTypeId1").html(tmp1);
	});
}
// 数据字典管理页面，添加按钮打开页面和分页操作项的编辑图标打开页面的取消按钮的事件
function clearDisabled() {
	$('#myshujuTypeId2').removeAttr("disabled");
}
// 数据字典管理页面，添加按钮打开添加页面和分页操作项的编辑图标打开页面给数据类型下拉列表赋值
var selectDicType2 = function() {
	$.post("logic/selectDicType.do", {}, function(data) {
		var tmp2 = "";
		$.each(data, function(index, item) {
			tmp2 += "<option value='" + item.type + "'>" + item.type
					+ "</option>";
		});
		$("#myshujuTypeId2").html(tmp2);
	});
}
// 数据字典管理页面，添加按钮打开添加页面的事件
function openAddDicWin() {
	$("#ffAdd")[0].reset();
	$('#addDicWin').modal('show');
}
// 数据字典管理页面，分页显示相关查询数据
function searchTable() {
	$("#tblResult").bootstrapTable('destroy');
	$("#tblResult").bootstrapTable({
		url : 'logic/getAllDic.do',
		method : 'post',
		queryParams : function(params) {
			return {
				name : $("#myshujuxiang").val(),
				type : $("#myshujuTypeId1").val()
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
// 数据字典管理页面，分页选择项的自定义函数
var xuanze = function(value, row, index) {
	return "<label><input type='checkbox' class='acem' /><span class='lbl'></span></label>";
}
// 数据字典管理页面，分页序号项的自定义函数
var xuhao = function(value, row, index) {
	return index + 1;
}
// 数据字典管理页面，分页操作项的自定义函数
var caozuo = function(value, row, index) {
	return "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'><button class='btn btn-xs btn-success' onclick='openUpdateWin(\""
			+ value + "\");'>编辑内容</button>"
			// +"<a class='red' href='javascript:openDeleteConfirmWin(\""
			// + value
			// + "\");'><i class='glyphicon glyphicon-trash'></i></a>"
			+ "</div>";
}
// 数据字典管理页面，分页操作项的编辑图标产生的事件
function openUpdateWin(dic_ic) {
	$.post("logic/getOneDicCode.do", {
		dicId : dic_ic
	}, function(data) {
		$('#hdic_id').val(data.dicId);
		$('#hdic_code_id').val(data.dicCode);
		$('#addShujuxiangid').val(data.name);
		$('#myshujuTypeId2').val(data.type).attr("disabled", "disabled");
	});
	$("#ffAdd")[0].reset();
	$('#addDicWin').modal('show');
}
// 数据字典管理页面，分页操作项的删除图标产生的事件
function openDeleteConfirmWin(dic_id) {
	$('#delDicHiddenID').val(dic_id);
	$('#myModal').modal('show');
}
// 数据字典管理页面，添加按钮和分页操作项的编辑图标打开页面的确定按钮产生的事件
function updateDic() {
	if ($("#addShujuxiangid").val().trim() == "") {
		alert("数据项名称不能为空");
		$("#addShujuxiangid").val("").focus();
		return false;
	} else {
		$.ajax({
			type : "GET",
			url : "logic/addOrUpdate.do",
			data : {
				dicId : $('#hdic_id').val(),
				dicCode : $('#hdic_code_id').val(),
				name : $("#addShujuxiangid").val(),
				type : $("#myshujuTypeId2").val()
			},
			async : false,
			success : function(data) {
				if (data == "allok") {
					$('#addDicWin').modal('hide');
					searchTable();
					alert("更新成功，请在页面中查看！");
				} else {
					$('#addDicWin').modal('hide');
					searchTable();
					alert("更新失败，正在返回！");
				}
			}
		});
	}
}
// 数据字典管理页面，分页操作项的删除图标打开页面的提交按钮产生的事件
function deleteDicCode() {
	$.ajax({
		type : "GET",
		url : "logic/deleteOneDicCode.do",
		data : {
			dicId : $('#delDicHiddenID').val()
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
// 键盘enter建查询的事件
function KeyDown() {
	if (event.keyCode == 13) {
		event.returnValue = false;
		event.cancel = true;
		searchTable();
	}
}
