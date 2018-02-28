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
* {
	margin: 0;
	padding: 0;
}
</style>
</head>
<body style="width:100%;">
	<div class="main fly-user-main layui-clear" style="width:100%;">
		<div class="fly-panel fly-panel-user">
			<div class="layui-tab layui-tab-brief" lay-filter="user">
				<ul class="layui-tab-title" id="LAY_mine">
					<li lay-id="info"><a href="${basePath}user.jsp?page=userPage"><i
							class="layui-icon"></i> 我的资料</a>
					</li>
					<li lay-id="avatar"><a
						href="${basePath}user.jsp?page=orderPage"><i
							class="layui-icon">&#xe60a;</i> 我的订单</a>
					</li>
					<li lay-id="pass" class="layui-this"><a
						href="${basePath}user.jsp?page=addressPage"><i
							class="layui-icon">&#xe609;</i> 我的地址</a>
					</li>
				</ul>
				<div class="layui-tab-content" style="padding: 20px 0;width:80%;">
					<div class="layui-form layui-form-pane layui-tab-item layui-show">
						<div class="layui-form-item">
							<c:choose>
								<c:when
									test="${addressList != null && fn:length(addressList) != 0}">
									<p>我的地址列表：</p><br>
									<c:forEach items="${addressList}" var="item1" varStatus="statu">
										<p>${item1.address}</p>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<p>您还没有地址！</p>
								</c:otherwise>
							</c:choose>
						</div><br><br>

						<form action="" class="layui-form">
							<div class="layui-form-item">
								<label class="layui-form-label">新地址</label>
								<div class="layui-input-block">
									<input id="address" type="text" placeholder="请输入正确地址，否则后果自负"
										value="" class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-input-block">
									<button class="layui-btn" type="button"
										onclick="addAddress()">立即提交</button>
									<button type="reset" class="layui-btn layui-btn-primary">重置</button>
								</div>
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
<script src="${basePath}res/layui/layui.js" type="text/javascript"></script>
<script type="text/javascript">
layui.use('element', function(){
  var element = layui.element();
});

function addAddress() {
	var address = $("#address").val();
	
	if("" == address) {
		layer.msg('请将信息填写完整!', {
					icon : 5
		});
		return;
	}
	$.ajax({
			type : "POST",
			dataType : "text",
			async : false,
			url : '${basePath}user.jsp?page=addAddress',
			data : {
				"address" : address
			},
			success : function(msg) {
				if (msg == 'success') {
					layer.msg('添加成功！', {
						icon : 4, time: 500
					});
					window.location.href = '${basePath}user.jsp?page=addressPage';
				} else if (msg == 'exit') {
					layer.msg('存在该地址！', {
						icon : 5
					});
				} else {
					layer.msg('添加失败！', {
						icon : 5
					});
				}
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