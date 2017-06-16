<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>网上商城</title>
<link href="${ pageContext.request.contextPath }/css/common.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/css/product.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
	function saveCart(){
		document.getElementById("cartForm").submit();
	}
</script>

</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a>
				<img src="${ pageContext.request.contextPath }/image/r___________renleipic_01/logo.jpg" alt="网上商城">
			</a>
		</div>
	</div>
	
	<div class="span9">
		<div class="headerAd">
			<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/vip.jpg" alt="正品保障" title="正品保障" height="50" width="320">
		</div>	
	</div>
	
	<%@ include file="navbar.jsp" %>
	
</div>

<div class="container productContent">
	<!-- 左边分类列表菜单 -->
	<div class="span6">
		<div class="hotProductCategory">
			<s:iterator var="c" value="#session.cList">
				<dl>
					<dt>
						<a href="${ pageContext.request.contextPath }/product_findByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/></a>
					</dt>
					<s:iterator var="cs" value="#c.categorySeconds">
						<dd>
							<a href="${ pageContext.request.contextPath }/product_findByCsid.action?csid=<s:property value="#cs.csid"/>&cspage=1"><s:property value="#cs.csname"/></a>
						</dd>
					</s:iterator>	
				</dl>
			</s:iterator>
		</div>
	</div>
	
	
	<!-- 商品详情 -->
	<div class="span18 last">
		<!--商品图片  -->
		<div class="productImage">
			<a title="" style="outline-style: none; text-decoration: none;" 
				id="zoom" 
				href="http://image/r___________renleipic_01/bigPic1ea8f1c9-8b8e-4262-8ca9-690912434692.jpg" rel="gallery">
				<div class="zoomPad">
				<img style="opacity: 1;" title="" class="medium" 
					src="${ pageContext.request.contextPath }/<s:property value="model.image"/>">
					
				</div>
			</a>
		</div>
		
		<div class="name"><s:property value="model.pname"/></div>
		<div class="sn">
			<div>编号：<s:property value="model.pid"/></div>
		</div>
		
		<!-- 商品信息 -->
		<div class="info">
			<dl>
				<dt>商城价:</dt>
				<dd>
					<strong>￥：<s:property value="model.eshop_price"/>元/件</strong>
						参 考 价：
						<del>￥<s:property value="model.market_price"/>元/件</del>
				</dd>
			</dl>
			<dl>
				<dt>促销:</dt>
				<dd>
					<a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
				</dd>
			</dl>
			<dl>
				<dt>    </dt>
				<dd>
					<span>    </span>
				</dd>
			</dl>
		</div>
		
		<!-- 商品添加到购物车  -->
		<form id="cartForm" action="${ pageContext.request.contextPath }/cart_addCart.action" method="post">
			<!-- 隐藏字段，商品ID -->
			<input type="hidden" name="pid" value="<s:property value="model.pid"/>"/>
			<div class="action">
				<dl class="quantity">
					<dt>购买数量:</dt>
					<dd>
						<!-- 输入商品数量 -->
						<input id="count" name="count" value="1" maxlength="4" onpaste="return false;" type="text"/>
					</dd>
					<dd>
						件
					</dd>
				</dl>
					
				<div class="buy">
					<input id="addCart" class="addCart" value="加入购物车" type="button" onclick="saveCart()"/>
				</div>
			</div>
		</form>
		
		<!-- 商品信息条 -->
		<div id="bar" class="bar">
			<ul>
				<li id="introductionTab">
					<a href="#introduction">商品介绍</a>
				</li>
			</ul>
		</div>
		
		<!-- 显示商品介绍 -->	
		<div id="introduction" name="introduction" class="introduction">
			<div class="title">
				<strong><s:property value="model.pdesc"/></strong>
			</div>
			
			<div>
				<img src="${pageContext.request.contextPath }/<s:property value="model.image"/>">
			</div>
		</div>		
	</div>
</div>
	
	

	
	

<div class="container footer">
	<div class="span24">
		<div class="footerAd">
			<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
		</div>
	</div>
	
	<div class="span24">
		<ul class="bottomNav">
					<li><a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a>SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2013-2017 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>