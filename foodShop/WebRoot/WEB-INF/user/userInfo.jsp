<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>帐号设置</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="">
<meta name="description" content="">
<link rel="stylesheet" href="${basePath}res/layui/css/layui.css">
<link rel="stylesheet" href="${basePath}res/css/global.css">
<style type="text/css">
*{
	margin:0;
	padding:0;
}
</style>
</head>
<body  style="width:100%;">
	<div class="main fly-user-main layui-clear"  style="width:100%;">
		<div class="fly-panel fly-panel-user">
			<div class="layui-tab layui-tab-brief" lay-filter="user">
				<ul class="layui-tab-title" id="LAY_mine">
					<li lay-id="info"  class="layui-this"><a href="${basePath}user.jsp?page=userPage"><i class="layui-icon">&#xe612;</i> 我的资料</a></li>
					<li lay-id="avatar"><a href="${basePath}user.jsp?page=orderPage"><i class="layui-icon">&#xe60a;</i> 我的订单</a></li>
					<li lay-id="pass"><a href="${basePath}user.jsp?page=addressPage"><i class="layui-icon">&#xe609;</i> 我的地址</a></li>
				</ul>
				<div class="layui-tab-content"
					style="padding: 20px 0;width:80%;">
					<div class="layui-form layui-form-pane layui-tab-item layui-show">
						<form method="post">
							<div class="layui-form-item">
								<label for="username" class="layui-form-label">账号</label>
								<div class="layui-input-inline">
									<input type="text" id="userId" readonly="readonly"
										name="userId" lay-verify="required" autocomplete="off"
										value="${user.userId}" class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label for="username" class="layui-form-label">姓名</label>
								<div class="layui-input-inline">
									<input type="text" id="username" name="username" required
										lay-verify="required" autocomplete="off"
										value="${user.userName}" class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label for="L_nowpass" class="layui-form-label">当前密码</label>
								<div class="layui-input-inline">
									<input type="password" id="nowpass" name="nowpass" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label for="L_pass" class="layui-form-label">新密码</label>
								<div class="layui-input-inline">
									<input type="password" id="newpass" name="newpass" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label for="L_repass" class="layui-form-label">确认密码</label>
								<div class="layui-input-inline">
									<input type="password" id="renewpass" name="renewpass" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<button class="layui-btn" type="button"
									onclick="updateUserInfo()" 
									>确认修改</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>


<script src="${basePath}res/js/jquery-1.10.2.min.js"
		type="text/javascript"></script>
		
<script src="${basePath}res/layer/layer.js" type="text/javascript"></script>
	<!--  
	
	<script src="${basePath}res/layui/layui.js" type="text/javascript"></script>
	-->
<script type="text/javascript">

function updateUserInfo() {
	var user = $("#userId").val();
	var username = $("#username").val();
	var nowpswd = $("#nowpass").val();
	var newpass = $("#newpass").val();
	var renewpass = $("#renewpass").val();
	if("" == username || "" == nowpswd || "" == newpass) {
	    layer.msg('请将信息填写完整!', {
			icon : 5
		});
		return;
	}
	if (newpass != renewpass) {
		layer.msg('两次输入的密码不匹配!', {
			icon : 2,
			time : 700
		});
		return;
	}
	$.ajax({
		type : "POST",
		dataType : "text",
		async : false,
		url : '${basePath}user.jsp',
		data : {
			"userId" : user,
			"userName" : username,
			"oldPass" : nowpswd,
			"password" : renewpass
		},
		success : function(msg) {
			if (msg == 'success') {
				layer.msg('修改成功！', {
					icon : 4
				});
			} else if (msg == 'wrong') {
				layer.msg('修改失败，密码错误！', {
					icon : 5
				});
			} else {
				layer.msg('修改失败！', {
					icon : 5
				});
			}
			document.getElementById("nowpass").value = "";
			document.getElementById("newpass").value = "";
			document.getElementById("renewpass").value = "";
		},
		error : function() {
			layer.msg('服务器响应失败！', {
				icon : 5
			});
		}
	});
}
</script>
</html>