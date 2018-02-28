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
<!-- 
<link href="${basePath}res/css/bootstrap.css" type="text/css"
	rel="stylesheet" media="all">
 -->
</head>
<body style="width:100%;">
	<div class="main fly-user-main layui-clear" style="width:100%;">
		<div class="fly-panel fly-panel-user">
			<div class="layui-tab layui-tab-brief" lay-filter="user">
				<ul class="layui-tab-title" id="LAY_mine">
					<li lay-id="info"><a href="${basePath}user.jsp?page=userPage"><i
							class="layui-icon">&#xe612;</i> 我的资料</a>
					</li>
					<li lay-id="avatar" class="layui-this"><a
						href="${basePath}user.jsp?page=orderPage"><i
							class="layui-icon">&#xe60a;</i> 我的订单</a>
					</li>
					<li lay-id="pass"><a
						href="${basePath}user.jsp?page=addressPage"><i
							class="layui-icon">&#xe609;</i> 我的地址</a>
					</li>
				</ul>
				<div class="layui-tab-content" style="padding: 20px 0;width:80%;">
					<div class="layui-form layui-form-pane layui-tab-item layui-show ">
					
						<ul class="list-unstyled" style="margin:30px; 20px;">
						<c:choose>
							<c:when
								test="${orderList != null && fn:length(orderList) != 0}">
								<c:forEach items="${orderList}" var="item1" varStatus="statu">
									<li>
									<p class="text-success">订单编号：${item1.orderId}</p>
									<p>用户：${item1.userName}</p>
									<!-- 评论用 -->
									<input type="hidden" value="${item1.goodsId}" id="input${item1.goodsId}">
									<p>商品名：${item1.goodsName}</p>
									<p>单价：${item1.goodsPrice}</p>
									<p>购买数量：${item1.buyNum}</p>
									<p>收货电话：${item1.userId}</p>
									<p>收货地址：${item1.address}</p>
									<small>订单生成时间：${item1.orderTime}</small>
									<br>
									<button onclick="comment('${item1.goodsId}')" class="layui-btn layui-btn-small layui-btn-radius layui-btn-normal">评价该商品</button>
									</li>
									<br><br>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<div class="layui-form-item">
									<p>暂无订单</p>
								</div>
							</c:otherwise>
						</c:choose>
						</ul>
					</div>
				</div>


			</div>
		</div>
	</div>
</body>
<script src="${basePath}res/js/jquery-1.10.2.min.js"
	type="text/javascript"></script>

<script src="${basePath}res/layer/layer.js" type="text/javascript"></script>
<script type="text/javascript">
function comment(goodsId) {
	layer.open({
		id: 'addComment',
	 	type: 2,
	 	title: '评论商品',
	 	content: ['${basePath}user.jsp?page=commentPage&goodsId='+goodsId, 'no'],	//contentHTML
	 	//skin: 'demo-class',
	 	area: ['450px','400px'],
	 	shade: 0.1,//背景颜色透明度
	 	shadeClose: true,//是否点击背景关闭, shade不为0
	 	resize: false,
	 	//content: '传入任意的文本或html'
	});
}
</script>
</html>