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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="${basePath}">
<title>主页</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="">
<meta name="description" content="">
<link rel="stylesheet" href="${basePath}res/layui/css/layui.css">
<link rel="stylesheet" href="${basePath}res/css/global.css">
<style type="text/css">

.search-view {
    text-align: left;
    border: 1px solid #695D69;
    background:#fff;
    border-radius: 15px;
    margin-right:20px;
    position:fixed;
    width:230px;
    height: 30px;
    top:-5px; left:110;
}
.search-view input{
	width:175px;
    height: 30px;
    border:0;
    margin-left:5px;
    border-top-left-radius: 15px;
    border-bottom-left-radius: 15px;
    background:#fff;
}
.search-view button{
	border:4px solid #ff7b11;
	width:46px;
    height: 30px;
    border-top-right-radius: 13px;
    border-bottom-right-radius: 13px;
    background:#ff7b11;
    color:#fff;
}
body .open-class .layui-layer-title{
	/*background:#ff7b11; color:#fff; border: none;*/
}
body .open-class .layui-layer-content{
	top:40px;
	left:25px;
}
</style>
</head>
<body>
	<div class="header">
		<div class="main">
			<a class="logo" href="${basePath}home.jsp" title="">websop</a>
			
			<div class="nav">
			<!-- layui tab显示分类 ? -->
			<!-- 
				<a class="" href="${basePath}home.jsp?page=showAll">
					全部</a>
			 -->
				<a class="nav-this" href="${basePath}home.jsp">
					首页</a>
				<a class="" href="${basePath}home.jsp?page=showGoods&type=cai"  target="content"><!-- nav-this亮字 -->
					美味菜肴</a>
				<a href="${basePath}home.jsp?page=showGoods&type=tianpin" target="content">
					精美甜品</a>
				<a href="${basePath}home.jsp?page=showGoods&type=shuiguo"  target="content">
					新鲜水果</a>
			</div>
			<div class="nav-user" style="top:5px;">
				<div class="nav search-view" >
					<input type="text" class="text" value="" placeholder="搜索 商品名 类型" id="search-input">
				 	<button type="button" id="search-button" onclick="search()">搜 索</button>
				</div>
			</div>
			<div class="nav-user">
				<c:choose>
					<c:when test="${user == null}">
						<div class="nav">
						  <a href="${basePath}login.jsp?page=loginPage" target="content" id="login-a">登录</a>
						  <a href="${basePath}register.jsp?page=registerPage" target="content">注册</a>
						</div>
					</c:when>
					<%-- 登录后的状态  --%>
					<c:otherwise>
						<div class="nav">
							<a  href="${basePath}user.jsp?page=userPage" target="content"> <img
							src="${basePath}res/images/avatar/${user.userHeadImg}">${user.userName}</a>
						</div>
						<div class="nav">
							<a href="${basePath}login.jsp?page=userLogout">退出</a>
						</div>
					</c:otherwise>
				</c:choose>
				<div class="nav">
					<a onclick="isLogin(this)" target="content" id="cart-a">
					    <img src="${basePath}res/images/pccart.png"></a>
				</div>
			</div>
		</div>
	</div>
	
	<iframe id="content" src="${basePath}home.jsp?page=mainPage" style="height:100%;width:101%;" name="content" frameborder="no" border="0" marginwidth="0" marginheight="0" ></iframe>
	
</body>

<script src="${basePath}res/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${basePath}res/layer/layer.js" type="text/javascript"></script>
<script src="${basePath}res/layui/layui.js" type="text/javascript"></script>
<script type="text/javascript">
layui.use('util', function(){
  var util = layui.util;
  
  //使用内部工具组件
  util.fixbar({
  bar1: '&#xe60f;',
  bar2: false,
  bgcolor: 'rgba(60, 60, 60,0.611)',//#ff7b11
  css: {top: 100, left: 10, width: 10, height:10},
  click: function(type){
    if(type === 'bar1'){
      layer.open({
		id: 'menu',
	 	type:1,
	 	anim: 3,
	 	title: '',
	 	content: "<a href='${basePath}home.jsp'>首页</a><br>"+
	 	"<a href='${basePath}home.jsp?page=showGoods&type=cai'  target='content'>美味菜肴 </a><br>"+
		"<a href='${basePath}home.jsp?page=showGoods&type=cai' target='content'>精美甜品 </a><br>"+
		"<a href='${basePath}home.jsp?page=showGoods&type=cai'  target='content'>新鲜水果</a>",
	 	skin: 'open-class',
	    area: ['120px','180px'],
	    offset: ['100px', '55px'],
     	btnAlign: 'c',
	 	shade: 0.3,//背景颜色透明度
	 	shadeClose: true,//是否点击背景关闭, shade不为0
	 	resize: false
	  });
    }
  }
});
});

function isLogin(obj) {
	if ('${user}' != null && '${user}' != '') {
		obj.href='${basePath}cart.jsp?page=cartPage';
	} else {
		layer.tips('您还没有登录！','#login-a');
	}
}

function search(){
	var value = $('#search-input').val();
	if(value != null && value != '') {
		///alert('ajax搜索');
		$.ajax({
				type : "GET",
				dataType : "text",
				async : false,
				url : '${basePath}home.jsp?page=searchGoods',
				data : {"condition":value},
				success : function(msg) {
						if (msg == 'success') {
							//windows.parent.location.open('result');
							window.content.location.href='${basePath}home.jsp?page=result';
						} else {
							layer.msg('没有找到您要的商品！', {icon:5});
						}
				},
				error : function () {
					layer.msg('服务器响应失败！', {icon: 5});
				}
			});
	} else {
		layer.tips('请输入您要找的商品！','#search-input');
	}
}

//监听搜索input回车事件
		$('#search-input').bind('keypress', function(event) {  
        	if (event.keyCode == "13") {
            	event.preventDefault();
            	$('#search-button').click();  
        	}
    	});

</script>
</html>
