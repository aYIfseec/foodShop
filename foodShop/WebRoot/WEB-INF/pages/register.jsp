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
<title>登录</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="">
<meta name="description" content="">
<link rel="stylesheet" href="${basePath}res/layui/css/layui.css">
<link rel="stylesheet" href="${basePath}res/css/global.css">
<link rel="stylesheet" href="${basePath}res/css/login.css">
<style type="text/css">
body{
	background:url(${basePath}res/images/login-bg.jpg) top left;
}
</style>
</head>
<body>
	<div class="main layui-clear"  style="width:400px;margin:10% auto;">
		<div class="fly-panel fly-panel-user" style="background-color: rgba(255, 255, 255, 0.8);" pad20>
			<div class="layui-tab layui-tab-brief">
				<ul class="layui-tab-title">
					<li><a href="${basePath}login.jsp?page=loginPage">登录</a></li>
					<li class="layui-this">注册</li>
				</ul>
				<div class="layui-form layui-tab-content" id="LAY_ucm"
					style="padding: 20px 0;">
					<div class="layui-tab-item layui-show">
						<div class="layui-form layui-form-pane">
							<form method="post">
								<div class="layui-form-item">
									<label for="L_email" class="layui-form-label">手机号</label>
									<div class="layui-input-inline">
										<input type="text" id="userId" name="userId" required placeholder="作为您唯一的登录名"
											lay-verify="email" autocomplete="off" class="layui-input" onblur="confirmTel(this)">
									</div>
								</div>
								<div class="layui-form-item">
									<label for="userName" class="layui-form-label">姓名</label>
									<div class="layui-input-inline">
										<input type="text" id="userName" name="username" required placeholder="请填写真实姓名"
											lay-verify="required" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label for="password" class="layui-form-label">密码</label>
									<div class="layui-input-inline">
										<input type="password" id="password" name="password" required placeholder=""
											lay-verify="required" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label for="repass" class="layui-form-label">确认密码</label>
									<div class="layui-input-inline">
										<input type="password" id="repass" name="repass" required placeholder=""
											lay-verify="required" autocomplete="off" class="layui-input">
									</div>
								</div>
								
								<!-- 
								<div class="layui-form-item">
									<label for="vercode" class="layui-form-label">验证</label>
									<div class="layui-input-inline">
										<input type="text" id="vercode" name="vercode" required
											lay-verify="required" placeholder="请"
											autocomplete="off" class="layui-input">
									</div>
									<div class="layui-form-mid">
										<span style="color: #c00;">test</span>
									</div>
								</div>
								 -->
								
								<div class="layui-form-item">
									<button onclick="doRegister()" id="registerSubmit" class="layui-btn" type="button" lay-filter="*" lay-submit style="margin-left:80px;">立即注册</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="${basePath}res/js/jquery-1.10.2.min.js"
		type="text/javascript"></script>

	<script src="${basePath}res/layer/layer.js" type="text/javascript"></script>
</body>
<script type="text/javascript">
		function confirmTel(obj) {
			var val = obj.value;
            if(/^(13|15|18)\d{9}$/i.test(val)) {
            } else {
            	layer.msg('请输入有效的手机号!', {
					icon : 2,
					time : 700
				});
				obj.value = "";
            }
            return;
		}
	
		//监听密码input回车事件
		$('#repass').bind('keypress', function(event) {  
        	if (event.keyCode == "13") {              
            	event.preventDefault();
            	$('#registerSubmit').click();  
        	}
    	});
		
		function doRegister() {
			var user = $("#userId").val();
			var name = $("#userName").val();
			var pswd = $("#password").val();
			var repass = $("#repass").val();
			if("" == user || "" == pswd) {
			    layer.msg('请填写手机号、密码!', {
					icon : 2,
					time : 700
				});
				return;
			}
			if (pswd != repass) {
				layer.msg('两次输入的密码不匹配!', {
					icon : 2,
					time : 700
				});
				return;
			}
			//
			
			$.ajax({
				type : "POST",
				dataType : "text",
				async : false,
				url : '${basePath}register.jsp',
				data : {"userId":user,"userName":name,
				"password":pswd},
				success : function(msg) {
						if (msg == 'success') {
							layer.msg('注册成功！', {
								icon : 4
							});
							document.getElementById("userId").value = "";
							document.getElementById("userName").value = "";
						} else if (msg == 'exist'){
							layer.msg('注册失败，该号码已被注册！', {
								icon : 5
							});
							document.getElementById("userId").value = "";
						} else {
							layer.msg('注册失败！', {
								icon : 5
							});
						}
						document.getElementById("password").value = "";
						document.getElementById("repass").value = "";
						document.getElementById("vercode").value = "";
					},
					error : function() {
						layer.msg('服务器响应失败！', {
							icon : 5
						});
					}
				});
		return false;
	}
</script>
</html>