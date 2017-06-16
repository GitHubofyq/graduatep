<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>网上商城管理中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<style type="text/css">
	*{
		margin:0;
		padding:0;
	}

	html,body{
		width:100%;
		height:100%;
	}
	body {
	  		background:url(../images/bg.png) no-repeat;
	  		backgrond-size:cover;
	  }
	 
	 .loginBox{
	 	width:100%;
	 	height:100%;
	 	overflow:hidden;
	 	display:flex;
	 	display:-webkit-flex;
	 }
	 
	 .loginBox .login{
	 	margin:auto;
	 }
	 
	 .loginBox .login input{
	 	width:200px;
	 	height:30px;
	 	border-radius:5px;
	 	margin-bottom:2px;
	 	outline:none;
	 	border:1px solid #ccc;
	 	padding:2px;
	 }
	 
	 .loginBox .login input[type="submit"]{
	 	cursor:pointer;
	 	background:-webkit-linear-gradient(left,lightgreen,cyan);
	 	color:#fff;
	 	font-weight:800;
	 	font-size:14px;
	 	border:none;
	 	width:205px;
	 	margin-top:5px;
	 }
	 
	 .loginBox .login input[type="submit"]:hover{
	 	background:lightgreen;
	 	color:#fff;
	 }
	 
	 .loginBox .login label{
	 	color:darkblue;
	 	font-weight:800;
	 }
	  
</style>
</head>

<body>
<%-- <center><s:actionerror /></center> --%>
	<div class="loginBox">
	<form class="login" method="post" action="${pageContext.request.contextPath }/admin_login.action" target="_parent" name='theForm'>
	  <table cellspacing="0" cellpadding="0" align="center">
		  <tr>
		    <td style="padding-left: 50px">
		      <table>
			      <tr>
			        <td><label for="adminname">管理员姓名：</label></td>
			        <td><input type="text" name="adminname" /></td>
			      </tr>
			      
			      <tr>
			        <td><label for="password">管理员密码：</label></td>
			        <td><input type="password" name="password" /></td>
			      </tr>
			      
			      <tr>
				      <td>&nbsp;</td>
				      <td><input type="submit" value="进入管理员中心" class="button"/></td>
			      </tr>
		      </table>
		    </td>
		  </tr>
	  </table>
	  
	  <!-- <input type="hidden" name="act" value="signin" /> -->
	</form>
	</div>
</body>
</html>