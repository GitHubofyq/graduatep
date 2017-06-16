<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="http://localhost:8080/mango/">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.jpg" alt="网上商城">
			</a>
		</div>
	</div>
	
	<div class="span9">
		<div class="headerAd">
			<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/vip.jpg" width="320" height="50" alt="正品保障" title="正品保障">
		</div>	
	</div>
	
	<%@ include file="navbar.jsp" %>
	
</div>	

<div class="container productList">
		<!-- 左边分类列表菜单 -->
		<div class="span6">
			<div class="hotProductCategory">
				<s:iterator var="category" value="#session.cList">
					<dl>
						<dt>
							<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="#category.cid"/>&currentPage=1"><s:property value="#category.cname"/></a>
						</dt>
						<s:iterator var="cs" value="#category.categorySeconds">
						<dd>
							<a href="${ pageContext.request.contextPath }/product_findByCsid.action?csid=<s:property value="#cs.csid"/>&currentPage=1"><s:property value="#cs.csname"/></a>
						</dd>
						</s:iterator>
					</dl>	
				</s:iterator>	
			</div>
		</div>
		
		<!-- 右边展示分页结果的商品 -->
		<div class="span18 last">
			<form id="productForm" action="${pageContext.request.contextPath}/image/蔬菜 - Powered By Mango Team.htm" method="get">
					
				<div id="result" class="result table clearfix">
					<ul>
						<s:iterator var="p" value="pageHelper.listPage">
							<li>
								<a href="${pageContext.request.contextPath}/product_findByPid.action?pid=<s:property value="#p.pid"/>">
									<img src="${pageContext.request.contextPath}/<s:property value="#p.image"/>" width="170" height="170"  style="display: inline-block;">
									   
									<span style='color:green'>
										<s:property value="#p.pname"/>
									</span>
									 
									<span class="price">
										商城价： ￥<s:property value="#p.eshop_price"/>/件
									</span>	 
								</a>
							</li>
						</s:iterator>
					</ul>
				</div>
				
				<!-- 分页按钮---值栈里有所有pageHelper的属性值  -->
				<div class="pagination">
					<s:if test="cid != null">
						<s:if test="pageHelper.currentPage != 1">
						<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&currentPage=1" class="firstPage">&nbsp;</a>
						<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&currentPage=<s:property value="pageHelper.currentPage-1"/>" class="previousPage">&nbsp;</a>
						</s:if>
						
						<!-- 显示当前页 -->
						<span>第&nbsp;<s:property value="pageHelper.currentPage" />&nbsp;页</span>
						
						<s:if test="pageHelper.currentPage != pageHelper.totalPages">
						<a class="nextPage" href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&currentPage=<s:property value="pageHelper.currentPage+1"/>">&nbsp;</a>
						<a class="lastPage" href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&currentPage=<s:property value="pageHelper.totalPages"/>">&nbsp;</a>
						</s:if>
						<span>共计&nbsp;<s:property value="pageHelper.totalPages"/>&nbsp;页</span>
					</s:if>
					
					<s:if test="csid != null">
						<s:if test="pageHelper.currentPage != 1">
						<a href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&currentPage=1" class="firstPage">&nbsp;</a>
						<a href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&currentPage=<s:property value="pageHelper.currentPage-1"/>" class="previousPage">&nbsp;</a>
						</s:if>
						
						<!-- 显示当前页 -->
						<span>第&nbsp;<s:property value="pageHelper.currentPage" />&nbsp;页</span>
						
						<s:if test="pageHelper.currentPage != 1">
						<a class="nextPage" href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&currentPage=<s:property value="pageHelper.currentPage+1"/>">&nbsp;</a>
						<a class="lastPage" href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&currentPage=<s:property value="pageHelper.totalPages"/>">&nbsp;</a>
						</s:if>
						<span>共计&nbsp;<s:property value="pageHelper.totalPages"/>&nbsp;页</span>
					</s:if>
				</div>
			</form>
		</div>
</div>
	
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
			<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
		</div>	
	</div>

	<div class="span24">
		<ul class="bottomNav">
			<li><a >关于我们</a>|</li>
			<li><a>联系我们</a>|</li>
			<li><a >诚聘英才</a>|</li>
			<li><a >法律声明</a>|</li>
			<li><a>友情链接</a>|</li>
			<li><a target="_blank">支付方式</a>|</li>
			<li><a target="_blank">配送方式</a>|</li>
			<li><a >官网</a>|</li>
			<li><a >论坛</a></li>
		</ul>
	</div>
	
	<div class="span24">
		<div class="copyright">Copyright©2013-2017 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>