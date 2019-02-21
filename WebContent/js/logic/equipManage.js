//设备预警管理页面，页面加载完毕调用的事件
$(document).ready(function() {
	searchTable();
	selectDropDownCommon1('科室', 'mykeshixiala');
	selectDropDownCommon2('myAddGongYingshangxiala');
	$('#mydatepicker').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0
	});
	$('#mydatepickerLog').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0
	});
	selectDropDownCommon3('科室', 'umyAddkeshixiala');
	selectDropDownCommon2('GongYingshangxialaUpdate');
	$('#umydatepicker').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0
	});
});
var deviceidUpload = "";
var attUpdateID = "";
// 设备预警管理页面，用于为科室的下拉列表提供支持的事件
function selectDropDownCommon1(dicType, htmlObjId) {
	var htmlObj = document.getElementById(htmlObjId);
	var selectStr = "";
	$.post("logic/getDropDown.do", {
		type : dicType
	}, function(data) {
		selectStr += '<option value="myall">全部</option>';
		$.each(data, function(index, item) {
			selectStr += '<option value="' + item.dicCode + '">' + item.name
					+ '</option>';
		});
		htmlObj.innerHTML = selectStr;
	});
}
// 设备预警管理页面，用于为供应商下拉列表提供支持的事件
function selectDropDownCommon2(htmlObjId) {
	var htmlObj = document.getElementById(htmlObjId);
	var selectStr = "";
	$.post("logic/getAgencyDropDown.do", {}, function(data) {
		$.each(data, function(index, item) {
			selectStr += '<option value="' + item.agencyId + '">'
					+ item.agencyName + '</option>';
		});
		htmlObj.innerHTML = selectStr;
	});
}
// 设备预警管理页面，用于为添加按钮打开页面科室的下拉列表提供支持的事件
function selectDropDownCommon3(dicType, htmlObjId) {
	var htmlObj = document.getElementById(htmlObjId);
	var selectStr = "";
	$.post("logic/getDropDown.do", {
		type : dicType
	}, function(data) {
		$.each(data, function(index, item) {
			selectStr += '<option value="' + item.dicCode + '">' + item.name
					+ '</option>';
		});
		htmlObj.innerHTML = selectStr;
	});
}
// 设备预警管理页面，分页显示相关查询数据
function searchTable() {
	$("#tblResult").bootstrapTable('destroy');
	$("#tblResult").bootstrapTable({
		url : 'logic/getAllDevices.do',
		method : 'post',
		queryParams : function(params) {
			return {
				deviceName : $('#deviceNameSearchID').val(),
				dicCode : $('#mykeshixiala').val()
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
// 设备预警管理页面，分页选择项的自定义函数
var xuanze = function(value, row, index) {
	return "<label><input type='checkbox' class='acem' /><span class='lbl'></span></label>";
}
// 设备预警管理页面，分页序号项的自定义函数
var xuhao = function(value, row, index) {
	return index + 1;
}
// 设备预警管理页面，分页距离下次检测（天）项的自定义函数
var juli = function(value, row, index) {
	if (value == '-1') {
		return '<font style="color:red">' + row.leftDay + '</font>';
	} else {
		return '<font style="color:blue">' + row.leftDay + '</font>';
	}
}
// 设备预警管理页面，提前预警（天）项的自定义函数
var tiqian = function(value, row, index) {
	return '<font style="color:blue" >' + row.dnumber + '</font>';
}
// 设备预警管理页面，分页操作项的自定义函数
var caozuo = function(value, row, index) {
	return "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>"
			+ "<button class='btn btn-xs btn-info' onclick='openAddLogWin(\""
			+ row.equipId
			+ "\",\""
			+ row.deviceName
			+ "\")'>"
			+ "检修</button>"
			+ "<button class='btn btn-xs btn-success' onclick='updateForm(\""
			+ row.equipId + "\")'>" + "编辑</button>"
			+ "<button class='btn btn-xs btn-danger' onclick='discardEquip(\""
			+ row.equipId
			+ "\");'>报废</button>"
//			+ "<a class='red' href='javascript:openDeleteConfirmWin(\""
//			+ row.equipId
//			+ "\");'><i class='glyphicon glyphicon-trash'></i></a>"
			+ "</div>";
}
// 设备预警管理页面，分页仪器详细信息项的自定义函数
var xiangqing = function(value, row, index) {
	return "<button class='btn btn-xs btn-info' onclick='openConpanyWin(\""
			+ value + "\");'>详细情况 </button>";
}
// 设备预警管理页面,添加按钮的事件
function openAddWin() {
	$("#addDeviceMyForm")[0].reset();
	selectDropDownCommon3('科室', 'myAddkeshixiala');
	$('#addWin').modal('show');
}
// 设备预警管理页面,添加按钮打开页面确定按钮的事件
function subForm() {
	if ($("#tDeviceNameID").val().trim() == "") {
		alert("仪器名称不能为空");
		$("#tDeviceNameID").val("").focus();
		return false;
	} else if ($("#myupdateBuyDateId").val().trim() == "") {
		alert("购买日期不能为空");
		$("#myupdateBuyDateId").val("").focus();
		return false;
	} else if ($("#tiqianYujingID").val().trim() == "") {
		alert("提前预警不能为空");
		$("#tiqianYujingID").val("").focus();
		return false;
	} else if ($("#tCheckCircleID").val().trim() == "") {
		alert("检测周期不能为空");
		$("#tCheckCircleID").val("").focus();
		return false;
	} else {
		$.post("logic/addDevice.do", {
			dicCode : $("#myAddkeshixiala").val(),
			agencyId : $("#myAddGongYingshangxiala").val(),
			deviceName : $("#tDeviceNameID").val(),
			buyDate : $("#myupdateBuyDateId").val(),
			dnumber : $("#tiqianYujingID").val(),
			checkCircle : $("#tCheckCircleID").val(),
			dtype:1
		}, function(data) {
			if (data > 0) {
				$('#addWin').modal('hide');
				searchTable();
				alert("添加成功，请在页面中查看！");
			} else {
				$('#addWin').modal('hide');
				searchTable();
				alert("添加失败，正在返回！");
			}
		});
	}
}
// 设备预警管理页面,导出Excel按钮的事件
function exportExcel() {
	var deviceName = $('#deviceNameSearchID').val();
	var mydicCode = $('#mykeshixiala').val();
	var exportExcelURL = 'logic/getExcelDevice.do?&tDicCode=' + mydicCode
			+ '&tDeviceName' + deviceName;
	$("#myhref").attr("href", exportExcelURL);
	$("#myhref")[0].click();
}
// 设备预警管理页面,导入Excel按钮的事件
function openExcelImportDialog() {
	$('#openExcelImport').modal('show');
}
// 设备预警管理页面,导入Excel按钮打开页面导入按钮的事件
function ajaxUploadExcel() {
	$.ajaxFileUpload({
		url : 'logic/importExcelDevice.do',
		secureuri : false,
		fileElementId : 'excelToUpload',// file标签的id
		// dataType: 'text',//返回数据的类型
		data : {},// 一同上传的数据
		success : function(data, status) {
			$('#openExcelImport').modal('hide');
			searchTable();
			alert("导入成功，请在页面中查看！");
		}
	});
}
// 设备预警管理页面，分页操作项确认检修按钮的事件
function openAddLogWin(equipId, equipName) {
	$('#deviceLogHiddenID').val(equipId);
	$('#fixequipNameID').val(equipName);
	$('#fixpersonNameId').val(elPersonCname);
	$('#addLogWin').modal('show');
}
// 设备预警管理页面，分页操作项确认检修按钮打开页面确定按钮的事件
function submitLog() {
	var addurl = 'logic/addOrUpdateLog.do';
	if ($('#equipLogDateId').val().trim() == "") {
		alert("维修日期不能为空");
		$('#equipLogDateId').val("").focus();
		return false;
	} else if ($('#fixcontentId').val().trim() == "") {
		alert("维修日志记录不能为空");
		$('#fixcontentId').val("").focus();
		return false;
	} else {
		$.ajax({
			cache : false,
			type : "post",
			url : addurl,
			data : {
				equipId : $('#deviceLogHiddenID').val(),
				presonId : elPersonId,
				logAddtime : $('#equipLogDateId').val(),
				fixContent : $('#fixcontentId').val()
			},
			async : false,
			success : function(data) {
				if (data == "allok") {
					$('#addLogWin').modal('hide');
					searchTable();
					alert("添加成功，请在日志管理页面中查看！");
				} else {
					$('#addLogWin').modal('hide');
					searchTable();
					alert("添加失败，正在返回！");
				}
			}
		});
	}
}
// 设备预警管理页面，分页操作项编辑内容按钮的事件
function updateForm(deviceid) {
	deviceidUpload = deviceid;
	$('#updateWin').modal('show');
	$.post("logic/getOneDevice.do", {
		equipId : deviceidUpload
	}, function(data) {
		attUpdateID = data.attid;
		$("#udeviceHiddenID").val(data.equipId);
		$("#uDeviceNameID").val(data.deviceName);
		$("#umyupdateBuyDateId").val(data.buyDate);
		$("#utCheckCircleID").val(data.checkCircle);
		$("#utiqianYujingID").val(data.dnumber);
		$("#umyAddkeshixiala").val(data.keshiCode);
		$("#GongYingshangxialaUpdate").val(data.supportCode);
		$("#mypicID").attr("src", data.atturl + data.attname);
	});
}
// 设备预警管理页面，分页操作项编辑内容按钮打开页面选择图片按钮的事件
function choosePic() {
	$("#fileToUpload").click();
}
// 设备预警管理页面，分页操作项编辑内容按钮打开页面上传按钮的事件
function ajaxUpload() {
	$.ajaxFileUpload({
		url : 'logic/myUpload.do',
		secureuri : false,
		fileElementId : 'fileToUpload',// file标签的id
		dataType : 'json',// 返回数据的类型
		data : {
			deviceId : deviceidUpload,
			mytAttid : attUpdateID,
			attType : 'shebei'
		},// 一同上传的数据
		success : function(data, status) {
			$("#mypicID").attr("src", data.atturl + data.attname);
			updateForm(deviceidUpload);
			alert('上传成功!');
		}
	});
}
// 设备预警管理页面，分页操作项编辑内容按钮打开页面确定按钮的事件
function updateSubForm() {
	if ($("#uDeviceNameID").val().trim() == "") {
		alert("仪器名称不能为空");
		$("#uDeviceNameID").val("").focus();
		return false;
	} else if ($("#utiqianYujingID").val().trim() == "") {
		alert("提前预警不能为空");
		$("#utiqianYujingID").val("").focus();
		return false;
	} else if ($("#utCheckCircleID").val().trim() == "") {
		alert("检修周期不能为空");
		$("#utCheckCircleID").val("").focus();
		return false;
	} else {
		$.post("logic/addDevice.do", {
			equipId : $("#udeviceHiddenID").val(),
			dicCode : $("#umyAddkeshixiala").val(),
			agencyId : $("#GongYingshangxialaUpdate").val(),
			deviceName : $("#uDeviceNameID").val(),
			buyDate : $("#umyupdateBuyDateId").val(),
			dnumber : $("#utiqianYujingID").val(),
			checkCircle : $("#utCheckCircleID").val(),
			dtype:1
		}, function(data) {
			if (data > 0) {
				$('#updateWin').modal('hide');
				searchTable();
				alert("更新成功，请在页面中查看！");
			} else {
				$('#updateWin').modal('hide');
				searchTable();
				alert("更新失败，正在返回！");
			}
		});
	}
}
//设备预警管理页面，分页操作项报废设备按钮的事件
function discardEquip(deviceId)
{
	$('#mydeleteDeviceHiddenID').val(deviceId);
	$('#myModal').modal('show');   
}
//设备预警管理页面，分页操作项报废设备按钮打开页面提交按钮的事件
function UpdateWin(){
	$.post("logic/updateDevice.do",{
			equipId:$('#mydeleteDeviceHiddenID').val(),
			dtype:0
			},function(data){
				 if(data>0){
		            	$('#myModal').modal('hide');   
		            	searchTable();
		            	alert('报废成功，请在页面中查看！');
		            }else{
		            	$('#myModal').modal('hide');   
		            	searchTable();
		            	alert('报废失败，正在返回！');
		            }
			});
}
//设备预警管理页面，分页操作项删除图标的事件
function openDeleteConfirmWin(deviceId)
{
	$('#mydeleteDeviceHiddenID').val(deviceId);
	$('#myModal').modal('show');   
}
//设备预警管理页面，分页操作项删除图标打开页面提交按钮的事件
function DeleteWin()
{
	 var addurl='logic/deleteDevice.do';
		$.ajax({
	        cache: false,
	        type: "post",
	        url:addurl,
	        data:{
	        	equipId:$('#mydeleteDeviceHiddenID').val()
	        	},
	        async: false,
	        error: function(request) {
	            alert("连接错误！");
	        },
	        success: function(data) {
	            if(data>0){
	            	$('#myModal').modal('hide');   
	            	searchTable();
	            	alert('删除成功，请在页面中查看！');
	            }else{
	            	$('#myModal').modal('hide');   
	            	searchTable();
	            	alert('删除失败，正在返回！');
	            }
	        }
	    });
}
// 设备预警管理页面，分页操作项详细情况按钮的事件
function openConpanyWin(deviceID) {
	$.post("logic/getDeviceDetail.do", {
		equipId : deviceID
	}, function(data) {
		$("#tAgencyNameID").val(data.agencyName);
		$("#tPermitNoID").val(data.permitNo);
		$("#tLicenseID").val(data.license);
		$("#myDetailPicID").attr("src", data.atturl + data.attname);
	});
	$('#supportBtnID').attr({
		"disabled" : "disabled"
	});
	$('#addComWin').modal('show');
}
// 键盘enter建查询的事件
function KeyDown() {
	if (event.keyCode == 13) {
		event.returnValue = false;
		event.cancel = true;
		searchTable();
	}
}
