<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
</HEAD>
	
<body>
	<!-- 4.提交编辑后的表单 -->
	<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/adminCategory_update.action" method="post" >
		<!-- 1.获取隐藏字段cid -->
		<input type="hidden" name="cid" value="<s:property value="model.cid"/>"/>
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26">
					<STRONG>编辑一级分类</STRONG>
				</td>
			</tr>

			<!-- 2.输入一级分类的名称 -->
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					一级分类名称：
				</td>
				<td class="ta_01" bgColor="#ffffff" colspan="3">
					<input type="text" name="cname" value="<s:property value="model.cname"/>" id="userAction_save_do_logonName" class="bg"/>
				</td>
			</tr>
		
			<tr> <!-- 3.点击 -确定/重置/返回 按钮- -->
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">
					<button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
						&#30830;&#23450;
					</button>

					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
					<span id="Label1"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</HTML>