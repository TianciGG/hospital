<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div class="navbar navbar-default" id="navbar">
	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand"> <small> <i
					class="glyphicon glyphicon-leaf"></i> 天津市总医院医疗器械检修预警系统
			</small>
			</a>
		</div>
		<div class="navbar-header pull-right" role="navigation">
			<ul class="nav navbar-nav">
				<li>
					<div style="padding: 5px;" class="headImgDiv">
						<img id="myHeadIconID" class="nav-user-photo"
							src="${myperson.atturl}/${myperson.attname}" alt="无头像" />
					</div>
				</li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><span class="user-info">${myuser.userName}</span>
						<i class="glyphicon glyphicon-triangle-bottom"></i><span
						class="user-info">${myuser.role}</span> </a>
					<ul class="dropdown-menu" style="min-width: 50%; align: center">
						<li><a href="goToPersonalCenterPage.do">个人中心</a></li>
						<li><a href="goToChangePwdPage.do">密码修改</a></li>
						<li><a href="myQuit.do">退出系统</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		var imgURL = "${myperson.atturl}/${myperson.attname}";
		if (imgURL != "/") {
			$("#myHeadIconID").attr("src", imgURL);
		}
	});
</script>