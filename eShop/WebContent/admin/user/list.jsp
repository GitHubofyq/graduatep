<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户列表</title>
	<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		//创建异步对象的方法
		function createXmlHttp(){
			   var xmlHttp;
			   try{ // Firefox, Opera 8.0+, Safari
			        xmlHttp=new XMLHttpRequest();
			    }
			    catch (e){
				   try{// Internet Explorer
				         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
				      }
				    catch (e){
				      try{
				         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
				      }
				      catch (e){}
				      }
			    }
				return xmlHttp;
		}
		//显示订单详情
		function orderDetail(oid){
			// 1.创建异步交互对象
			var xhr = createXmlHttp();
			// 2.设置监听
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						var div1 = document.getElementById("div"+oid);
						div1.innerHTML = xhr.responseText;
					}
				}
			}
			// 3.打开连接
			xhr.open("GET","${pageContext.request.contextPath}/adminOrder_showOrderItem.action?time="+new Date().getTime()+"&oid="+oid,true);
			// 4.发送
			xhr.send(null);
		}
	</script>
</HEAD>

<body>
	<br>
	<form id="Form1" name="Form1"  method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
						<strong>用户 列 表</strong>
					</td>
				</tr>
				
				<tr><!-- 展示用户列表  -->
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
	
								<td align="center" width="10%">
									序号
								</td>
								<td align="center" width="10%">
									用户ID
								</td>
								<td width="10%" align="center">
									用户名
								</td>
								<td align="center" width="10%">
									密码
								</td>
								<td width="10%" align="center">
									用户联系方式
								</td>
								<td width="10%" align="center">
									收货人
								</td>
								<td width="*" align="center">
									地址
								</td>
								
							</tr>
							<!-- 迭代出所有用户的分页结果  -->
							<s:iterator var="user" value="pageHelper.listPage" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="14%">
										<s:property value="#status.count"/>
									</td>
									
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="14%">
										<s:property value="#user.uid"/>
									</td>
									
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="14%">
										<s:property value="#user.username"/>
									</td>
									
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="14%">
										<s:property value="#user.password"/>
									</td>
									
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="14%">
										<s:property value="#user.phone"/>
									</td>
									
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="14%">
										<s:property value="#user.name"/>
									</td>
									
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%">
										<s:property value="#user.addr"/>
									</td>
									
								</tr>
							</s:iterator>	
						</table>
					</td>
				</tr>
				
				<!-- 显示分页数据 -->
				<tr align="center">
					<td class="ta_01" align="center" bgColor="#afd1f3">
						第<s:property value="pageHelper.currentPage"/>|<s:property value="pageHelper.totalPages"/>页&nbsp;&nbsp;&nbsp;&nbsp;
						<s:if test="pageHelper.currentPage != 1">
							<a href="${ pageContext.request.contextPath }/adminUser_findAll.action?page=1">首页</a>
							<a href="${ pageContext.request.contextPath }/adminUser_findAll.action?page=<s:property value="pageHelper.currentPage-1"/>">上一页</a>
						</s:if>
						<s:if test="pageHelper.currentPage != pageHelper.totalPages">
							<a href="${ pageContext.request.contextPath }/adminUser_findAll.action?page=<s:property value="pageHelper.currentPage+1"/>">下一页</a>
							<a href="${ pageContext.request.contextPath }/adminUser_findAll.action?page=<s:property value="pageHelper.totalPages"/>">末页</a>
						</s:if>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>