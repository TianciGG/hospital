//各科室设备使用情况统计页面，页面加载完毕调用的事件
$(document).ready(function() {
	getCharColAlarmNum();
	getChartPieDeviceNum();
});
// 各科室设备使用情况统计页面，取柱状图数据的事件
function getCharColAlarmNum() {
	$.get("logic/getCharColAlarmNum.do", function(data) {
		dataArray = data.split('|');
		initColumnChart(eval(dataArray[0]), eval(dataArray[1]));
	});
}
// 各科室设备使用情况统计页面，取饼状图数据的事件
function getChartPieDeviceNum() {
	$.get("logic/getChartPieDeviceNum.do", function(data) {
		initPieChart(eval(data));
	});
}
// 各科室设备使用情况统计页面，初始化柱状图的事件
function initColumnChart(myJsonArray, jsonType) {
	$('#containerColumn').highcharts({
		chart : {
			type : 'column',
			options3d : {
				enabled : true,
				alpha : 15,
				beta : 15,
				viewDistance : 25,
				depth : 40
			},
			marginTop : 80,
			marginRight : 40
		},
		title : {
			text : '各科室正常使用设备和待检测设备分布情况'
		},
		xAxis : {
			categories : jsonType
		},
		yAxis : {
			allowDecimals : false,
			min : 0,
			title : {
				text : '设备数量'
			}
		},
		tooltip : {
			headerFormat : '<b>{point.key}</b><br>',
			pointFormat : '<span style="color:{series.color}">\u25CF</span> {series.name}: {point.y} / {point.stackTotal}'
		},
		plotOptions : {
			column : {
				stacking : 'normal',
				depth : 40
			}
		},
		series : myJsonArray
	});
}
// 各科室设备使用情况统计页面，初始饼状图的事件
function initPieChart(pieJSON) {
	$('#containerPie').highcharts({
		chart : {
			type : 'pie',
			options3d : {
				enabled : true,
				alpha : 45
			}
		},
		title : {
			text : '各科室设备数量'
		},
		subtitle : {
			text : '分布图'
		},
		plotOptions : {
			pie : {
				innerSize : 100,
				depth : 45
			}
		},
		series : pieJSON
	});
}