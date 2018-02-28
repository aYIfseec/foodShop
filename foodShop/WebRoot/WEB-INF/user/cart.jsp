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
<title>购物车</title>
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="${basePath}res/css/bootstrap.css" type="text/css"
	rel="stylesheet" media="all">
<link href="${basePath}res/css/style.css" type="text/css"
	rel="stylesheet" media="all">
<!-- js -->
<script src="${basePath}res/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${basePath}res/js/bootstrap-3.1.1.min.js"></script>
<!-- //js -->
<!-- cart -->
<script src="${basePath}res/js/simpleCart.min.js"></script>
</head>
<body>
	<!--cart-items-->
	<div class="cart-items">
		<div class="container">
			<h2 style="font-size:2.2em;">
				<a href="${basePath}cart.jsp?page=cartPage">我的购物车</a>
			</h2>
			<form action="${basePath}buy.jsp" method="post" id="cart-form">
			<c:if test="${goodsList != null && fn:length(goodsList) != 0}">
				<c:forEach items="${goodsList}" var="item" varStatus="status">
					<script>
						$(document).ready(function(c) {
							$('#close${item.goodsId}').on('click', function(c) {
								$('#cart-header${item.goodsId}').fadeOut('fast', function(c) {
									removeGoodsFromCart('${item.goodsId}');
									$('#cart-header${item.goodsId}').remove();
								});
							});
						});
					</script>
					<div class="cart-header" id="cart-header${item.goodsId}">
						<div class="close1" id="close${item.goodsId}"></div>
						<div class="cart-sec simpleCart_shelfItem">
							<div class="cart-item cyc">
								<img src="${imgUrl}/goods/${item.goodsImg}"
									class="img-responsive" alt="">
							</div>
							<div class="cart-item-info">
								<h3>
									<a
										href="${basePath}home.jsp?page=goodsInfoPage&goodsId=${item.goodsId}">
										${item.goodsName} </a> <span>${item.goodsExplain}</span>
								</h3>
								<ul class="size dl-horizontal">
									<li><h3>价格：</h3></li>
									<li><h4 id="price${item.goodsId}">${item.goodsPrice*(empty
											item.goodsDiscount ? 1:item.goodsDiscount)}</h4>
									</li>
								</ul>
								<div class="delivery">
								<ul class="size dl-horizontal">
									<li><h3>规格：</h3>
									</li>
									<li><h4>${item.goodsSize}</h4>
									</li>
									<span><li><h3>选择：</h3></li>
										<li><input type="checkbox" name="cart-goods" value="${item.goodsId}" id="checkbox${item.goodsId}" onclick="onClickHander(this)"></li>
									</span>
								</ul>
								</div>
								<div class="delivery">
									<ul class="size dl-horizontal">
									<li><h3>数量：</h3></li>
									<button onclick="changerNum(1,'${item.goodsId}')" type="button">-</button>
									
									<input value="${item.buyNum}" readOnly="readOnly"
										id="buyNum${item.goodsId}" name="buyNum" style="width:25px;">
									<!-- ajax传商品id与数量到后台，判断数量 -->
									<input value="${item.goodsId}" type="hidden" name="goodsId">
									
									<button onclick="changerNum(2,'${item.goodsId}')" type="button">+</button>
									<span><li><h3>小计：</h3></li>
										<li><h4 id="sum${item.goodsId}">${item.goodsPrice*item.buyNum}</h4></li>
									</span>
									</ul>
									<div class="clearfix"></div>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>

				</c:forEach>
			</c:if>
			
			
			<!-- 结算 -->
			<div class="delivery">
			<!-- 
			<ul><li>
				<div class="col-lg-3">
      				<input type="text" class="form-control" placeholder="请输入正确地址，否则后果自负">
   			    </div>
   			    <button type="button" onclick="getAddress()" class="btn btn-primary">从我的地址库中选择</button>
				</li>
			</ul>
			 -->
			
			<ul class="size dl-horizontal">
			    <span><li><h3>总计：</h3></li>
				<li><h3 id="priceSum">0.00</h3></li>
				<button type="button" onclick="submitForm()" class="btn btn-success" id="submit-button">去 结 算</button>
				</span>
			<!-- 付款生成订单 -->
			</ul>
			</div>
			
			
			<!-- 地址 -->
            <div class="col-lg-6">
                <div class="input-group">
                    <input type="text" class="form-control" id="address-input" placeholder="请输入正确地址，否则后果自负" name="address">
                    <div class="input-group-btn">
                        <button type="button" class="btn btn-default 
                        dropdown-toggle" data-toggle="dropdown">我的地址库
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu pull-right">
                        	<c:choose>
                        		<c:when test="${addressList != null && fn:length(addressList) != 0}" >
                        			<c:forEach items="${addressList}" var="item1" varStatus="statu">
                        			<li>
                                		<a href="#" onclick="getAddress(this)">${item1.address}</a>
                            		</li>
                            		</c:forEach>
                        		</c:when>
                        		<c:otherwise>
                        			<li>
                                		<p>您还没有地址，直接输入一个吧！</p>
                            		</li>
                        		</c:otherwise>
                        	</c:choose>
                        </ul>
                    </div>
                </div>
            </div>
			
			
			</form>
			
			
            
		</div>
	</div>
</body>
<script src="${basePath}res/layer/layer.js" type="text/javascript"></script>
<script type="text/javascript">
function getAddress(obj) {
	var val = obj.innerHTML;
	//alert(val);
	$('#address-input').val(val);
}
//结算
function submitForm() {
	$('#submit-button').attr({"disabled":"disabled"});
	var isChoose = $("input[type='checkbox']").is(':checked');
	if (isChoose) {
		if ($('#address-input').val() == "" || $('#address-input').val() == null) {
			layer.msg('请输入地址！');
			$('#submit-button').removeAttr("disabled");
			return;
		}
		var arr = document.getElementsByName("cart-goods");
		for (var i = 0; i < arr.length; i++) {
			var id = arr[i].value;
			//alert(id);
			if (!$("#checkbox"+id).get(0).checked) {
    			arr[i].value = 0;
			}
			//alert(arr[i].value);
		}
		//alert("trer");
		$('#cart-form').submit();
	} else {
		layer.msg('您未勾选商品！');
		$('#submit-button').removeAttr("disabled");
	}
}

//从购物车中删除
function removeGoodsFromCart(goodsId) {
	if ($('#checkbox'+goodsId).is(':checked')) {
		var price = document.getElementById("sum"+goodsId).innerHTML;
		price = parseFloat(price);
		changerSum(price, 1);
	}
	
	$.ajax({
		type : "POST",
		dataType : "text",
		async : false,
		url : '${basePath}cart.jsp',
		data : {"userId":'${user.userId}',
		"goodsId":goodsId},
		success : function(msg) {
			if (msg == 'success') {
				//layer.msg('数量已修改');
			} else {
				//alert('失败！');
			}
		},
		error : function () {
			//alert('服务器未响应！');
		}
	});
}

//勾选商品，改变总计价格
function onClickHander(obj){
	var priceSum = document.getElementById("priceSum").innerHTML;
	var price = document.getElementById("sum"+obj.value).innerHTML;
	//alert(price);
	
	priceSum = parseFloat(priceSum);
	price = parseFloat(price);
            if(obj.checked){
                priceSum += price;
            }else{
            	priceSum -= price;
            }
            //alert(priceSum);
     priceSum = returnFloat(priceSum);
     document.getElementById("priceSum").innerHTML = priceSum;
  }

//改变总计
function changerSum(price, type) {
	var priceSum = document.getElementById("priceSum").innerHTML;
	priceSum = parseFloat(priceSum);
	if (type == 1) {
		priceSum -= price;
	} else {
		priceSum += price;
	}
	priceSum = returnFloat(priceSum);
    document.getElementById("priceSum").innerHTML = priceSum;
}

//改变数量，改变小计，该项被勾选时同时改变总计
function changerNum(type, goodsId) {
	var num = $('#buyNum'+goodsId).val();
	var price = parseFloat($('#price'+goodsId).html());
	if (type == 1) {
		if (num > 1) {
			num --;
			if ($('#checkbox'+goodsId).is(':checked')) {
				changerSum(price, 1);
			}
		} else {//为1时不能再减
			return;
		}
	} else {
		num ++;
		if ($('#checkbox'+goodsId).is(':checked')) {
			changerSum(price, 2);
		}
	}
	$('#buyNum'+goodsId).val(num);
	$('#sum'+goodsId).html(returnFloat(num*(price)));
	
	//ajax数据库中改buyNum
	$.ajax({
		type : "POST",
		dataType : "text",
		async : false,
		url : '${basePath}cart.jsp',
		data : {
				"userId" : '${user.userId}',
				"buyNum" : num,
				"goodsId" : goodsId
		},
		success : function(msg) {
			if (msg == 'success') {
				//alert('失败！');
				//layer.msg('数量已修改');
			} else {
				//alert('失败！');
			}
		},
		error : function() {
			alert('服务器未响应！');
		}
		});
}
//返回两位小数的数字
	function returnFloat(num) {
		var value = Math.round(parseFloat(num) * 100) / 100;
		var xsd = value.toString().split(".");
		if (xsd.length == 1) {
			value = value.toString() + ".00";
			return value;
		}
		if (xsd.length > 1) {
			if (xsd[1].length < 2) {
				value = value.toString() + "0";
			}
			return value;
		}
	}
</script>

</html>