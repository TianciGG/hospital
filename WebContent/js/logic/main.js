//首页，页面加载完毕调用的事件
$(document).ready(function() {
	presentTime();
});
// 首页，用于显示本次登录时间的事件
var presentTime = function() {
	document.getElementById('currentTime').innerHTML = new Date()
			.toLocaleString()
			+ ' 星期' + '日一二三四五六'.charAt(new Date().getDay());
	// setInterval("document.getElementById('currentTime').innerHTML=new
	// Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new
	// Date().getDay());",1000);
}