<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>

<link href="${ pageContext.request.contextPath }/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${ pageContext.request.contextPath }/css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="./网上商城/index.htm">
				<img src="${ pageContext.request.contextPath }/image/r___________renleipic_01/logo.jpg" alt="传智播客"/>
			</a>
		</div>
	</div>
	
	<div class="span9">
		<div class="headerAd">
			<img src="${ pageContext.request.contextPath }/image/r___________renleipic_01/vip.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
		</div>	
	</div>
	
	<%@ include file="navbar.jsp" %>
	
</div>	

<div class="container cart">

		<div class="span24">
		
			<div class="step step1">
				<ul>
					
					<li  class="current"></li>
					<li  >我的订单</li>
				</ul>
			</div>
			
			<table>
				<tbody>
				<s:iterator var="order" value="pageHelper.listPage">
					<tr>
						<th colspan="5">订单编号：<s:property value="#order.oid"/>&nbsp;&nbsp;&nbsp;&nbsp;
							合计：￥<font color="red"><s:property value="#order.total"/></font>
							&nbsp;&nbsp;&nbsp;&nbsp;
							订单状态：
							<s:if test="#order.state == 1">
								<a href="${ pageContext.request.contextPath }/order_findByOid.action?oid=<s:property value="#order.oid"/>"><font color="red">付款</font></a>
							</s:if>
							<s:if test="#order.state == 2">
								用户已付款
							</s:if>
							<s:if test="#order.state == 3">
								<a href="${ pageContext.request.contextPath }/order_updateState.action?oid=<s:property value="#order.oid"/>"><font color="red">确定收货</font></a>
							</s:if>
							<s:if test="#order.state == 4">
								交易完成
							</s:if>
						</th>
					</tr>
					
						<tr>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
					<s:iterator var="orderItem" value="#order.orderItems">
						<tr>
							<td width="60">
								<input type="hidden" name="id" value="22"/>
								<img src="${ pageContext.request.contextPath }/<s:property value="#orderItem.product.image"/>"/>
							</td>
							<td>
								<a target="_blank"><s:property value="#orderItem.product.pname"/></a>
							</td>
							<td>
								￥<font color="red"><s:property value="#orderItem.product.eshop_price"/></font>
							</td>
							<td class="quantity" width="60">
								<s:property value="#orderItem.count"/>
							</td>
							<td width="140">
								<span class="subtotal">￥<font color="red"><s:property value="#orderItem.subtotal"/></font></span>
							</td>
						</tr>
					</s:iterator>
				</s:iterator>
				<!-- 分页信息-按钮 -->
				<tr>
					<td colspan="5">
						<div class="pagination">
							<s:if test="pageHelper.currentPage != 1">
							<a href="${pageContext.request.contextPath}/order_findByUid.action?page=1" class="firstPage">&nbsp;</a>
							<a href="${pageContext.request.contextPath}/order_findByUid.action?page=<s:property value="pageHelper.currentPage"/>-1" class="previousPage">&nbsp;</a>
							</s:if>
							
							<!-- 显示当前页 -->
							<span>第&nbsp;<s:property value="pageHelper.currentPage"/>&nbsp;页</span>
							
							<s:if test="pageHelper.currentPage != 1">
							<a class="nextPage" href="${pageContext.request.contextPath}/order_findByUid.action?page=<s:property value="pageHelper.currentPage"/>+1">&nbsp;</a>
							<a class="lastPage" href="${pageContext.request.contextPath}/order_findByUid.action?page=<s:property value="pageHelper.totalPages"/>">&nbsp;</a>
							</s:if>
							<span>共计&nbsp;<s:property value="pageHelper.totalPages"/>&nbsp;页</span>
						</div>
					</td>
				</tr>
				</tbody>
			</table>
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
					<li>
						<a href="#">关于我们</a>
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