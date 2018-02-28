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
<link rel="stylesheet" href="${basePath}resources/layui/css/layui.css">
<link rel="stylesheet" href="${basePath}resources/css/global.css">

<link rel="stylesheet" href="${basePath}resources/css/login.css">
<style type="text/css">
body{
	background:url(${basePath}res/images/login-bg.jpg) top left;
}
</style>

</head>
<body style="">
	<div class="main layui-clear" style="width:400px;margin:10% auto;">
		<div class="fly-panel fly-panel-user" style="background-color: rgba(255, 255, 255, 0.8);" pad20>
			<div class="layui-tab layui-tab-brief">
				<ul class="layui-tab-title">
					<li class="layui-this">登录</li>
					<li><a href="${basePath}register.jsp?page=registerPage">注册</a>
					</li>
				</ul>
				<div class="layui-form layui-tab-content" id="LAY_ucm"
					style="padding: 20px 0;">
					<div class="layui-tab-item layui-show">
						<div class="layui-form layui-form-pane">
							<form action="" method="post">
								<div class="layui-form-item">
									<label for="L_email" class="layui-form-label">账 号</label>
									<div class="layui-input-inline">
										<input type="text" id="userId" name="userId" required placeholder="您注册时的手机号"
										lay-verify="required" autocomplete="off" class="layui-input" onblur="confirmTel(this)">
									</div>
								</div>
								<div class="layui-form-item">
									<label for="L_pass" class="layui-form-label">密 码</label>
									<div class="layui-input-inline">
										<input type="password" id="password" name="password" required placeholder="请输入密码"
											lay-verify="required" autocomplete="off" class="layui-input">
									</div>
								</div>
								<!-- 
								<div class="layui-form-item">
									<label for="vercode" class="layui-form-label">验 证</label>
									<div class="layui-input-inline">
										<input type="text" id="vercode" name="vercode" required
											lay-verify="required" placeholder="请输入验证码"
											autocomplete="off" class="layui-input">
									</div>
									<div class="layui-form-mid" style="">
										<span style="color: #c00;">1+1=?</span>
									</div>
								</div>
								 -->
								
								
								<div class="layui-form-item">
									<span style="padding-left:20px;"> <a href="mailto:754912298@qq.com">忘记密码？</a>
									</span>
									<button onclick="doLogin()" type="button" style="margin-left:30px;" class="layui-btn" id="loginSubmit" >立即登录</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${basePath}res/js/jquery-1.10.2.min.js" type="text/javascript"></script>

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
		$('#password').bind('keypress', function(event) {  
        	if (event.keyCode == "13") {
            	event.preventDefault();
            	$('#loginSubmit').click();  
        	}
    	});
		
		//提交
		function doLogin() {
			var user = $("#userId").val();
			var pswd = $("#password").val();
			if("" == user || "" == pswd) {
			    layer.msg('请填写账号密码!', {
					icon : 2,
					time : 700
				});
				return;
			}
			
			//ajax提交表单数据
			$.ajax({
				type : "POST",
				dataType : "text",
				async : false,
				url : '${basePath}login.jsp',
				data : {"userId":user,
				"password":pswd},
				success : function(msg) {
						if (msg == 'success') {
							window.parent.location.href = '${basePath}home.jsp';
						} else {
							layer.msg('用户名或密码错误！', {icon: 5});
						}
						document.getElementById("password").value = "";
				},
				error : function () {
					layer.msg('服务器响应失败！', {icon: 5});
				}
			});
			return false;
		}
	</script>
</html>