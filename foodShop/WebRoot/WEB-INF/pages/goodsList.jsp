<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="${basePath}">

<title>My JSP 'main.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="${basePath}res/css/bootstrap.css" type="text/css"
	rel="stylesheet" media="all">
<link href="${basePath}res/css/style.css" type="text/css"
	rel="stylesheet" media="all">

<script src="${basePath}res/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${basePath}res/js/bootstrap-3.1.1.min.js"></script>
</head>

<body>
	<div class="products">
		<div class="container">
			<c:choose>
				<c:when test="${type != null && type != ''}">
					<h2>${type}</h2>
				</c:when>
				<c:otherwise>
					<h2>搜索结果</h2>
				</c:otherwise>
			</c:choose>
			<!-- 商品列表 -->
			<div class="col-md-11 product-model-sec" style="">
				<c:if test="${goodsListBySearch != null && fn:length(goodsListBySearch) != 0}">
					<c:forEach items="${goodsListBySearch}" var="item" varStatus="status">
						<div class="product-grid">
							<a href="${basePath}home.jsp?page=goodsInfoPage&goodsId=${item.goodsId}">
								<div class="more-product">
									<span> </span>
								</div>
								<div class="product-img b-link-stripe b-animate-go  thickbox">
									<img src="${imgUrl}/goods/${item.goodsImg}"
										class="img-responsive" alt="" />
									<div class="b-wrapper">
										<h4 class="b-animate b-from-left  b-delay03">
											<button>详 情</button>
										</h4>
									</div>
								</div> </a>
							<div class="product-info simpleCart_shelfItem">
								<div class="product-info-cust prt_name">
									<h4>${item.goodsExplain}</h4>
									<span class="item_price">${item.goodsName}</span>
									<div class="ofr">
										<p class="pric1">
											<del>￥${item.goodsPrice}</del>
										</p>
										<p class="disc">￥<fmt:formatNumber type="number" maxFractionDigits="2"
										value="${item.goodsPrice*(empty item.goodsDiscount ? 1:item.goodsDiscount)}" 
										pattern="0.00"/>
										</p>
									</div>
									<input type="button" class="item_add items" onclick="addGoodsToCart('${item.goodsId}')" value="加入购物车">
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
</body>
<script src="${basePath}res/layer/layer.js" type="text/javascript"></script>
<script type="text/javascript">
	function addGoodsToCart(goodsId) {
		if ('${user}' != null && '${user}' != '') {
			//alert('ajax加入购物车');
			$.ajax({
				type : "POST",
				dataType : "text",
				async : false,
				url : '${basePath}cart.jsp',
				data : {
					"goodsId" : goodsId,
					"buyNum" : 1,
					"oper" : 1
				},
				//不是从详情页面添加商品，默认添加一件
				success : function(msg) {
					if (msg == 'success') {
						layer.msg('已加入您的购物车！', {
							icon : 6
						});
					} else {
						layer.msg('操作失败，请重试！', {
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
		} else {
			//alert('您还没有登录');
			layer.msg('您还没有登录！', {
				icon : 5
			});
		}
	}
</script>
</html>
