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
</style>
</head>
<body>
<div style="width:70%;margin:-20px 0 0 10px;">
	<c:choose>
		<c:when test="${comment != null}">
			<p>您已对该商品评价过</p>
		</c:when>
		<c:otherwise>
		
			<form action="" class="layui-form">
				<div class="layui-form-item">
					<label class="layui-form-label">评分</label>
					<div class="layui-input-block">
						<input id="score" type="text" placeholder="请输入评分1-5" value=""
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">评论</label>
					<div class="layui-input-block">
						<textarea name="desc" placeholder="请输入内容" class="layui-textarea"
							id="comment"></textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" type="button"
							onclick="addComment('${goodsId}')">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</c:otherwise>
	</c:choose>
	</div>
</body>
<script src="${basePath}res/js/jquery-1.10.2.min.js"
	type="text/javascript"></script>

<script src="${basePath}res/layer/layer.js" type="text/javascript"></script>
<script type="text/javascript">
	function addComment(goodsId) {
		var comment = $('#comment').val();
		var score = $('#score').val();
		score = parseInt(score);
		if (isNaN(score) || score == "" || score > 5 || score < 1) {
			layer.msg('输入错误，评分为1-5');
			return;
		}
		if (comment == "" || comment == null) {
			layer.msg('请输入评论内容');
			return;
		}
		$.ajax({
			type : "POST",
			dataType : "text",
			async : false,
			url : '${basePath}user.jsp?page=userComment',
			data : {
				"goodsId" : goodsId,
				"score" : score,
				"comment" : comment
			},
			success : function(msg) {
				if (msg == 'success') {
					layer.msg('评论成功！', {
						icon : 4, time : 1000
					});
				} else {
					layer.msg('评论失败，请重试！', {
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
		//setTimeout(function(){},1000);
		window.parent.location.href='${basePath}user.jsp?page=orderPage';
	}
</script>
</html>