<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <style>
	body
	{
		SCROLLBAR-ARROW-COLOR: #ffffff;  SCROLLBAR-BASE-COLOR: #dee3f7;
	}
   </style>
</head>
  
<frameset rows="18%,*">
	<frame src="${pageContext.request.contextPath}/admin/top.jsp" name="top"></frame>
	
	<frameset cols="15%,*">
		<frame src="${pageContext.request.contextPath}/admin/left.jsp" name="left"></frame>
		<frame src="${pageContext.request.contextPath}/admin/welcome.jsp" name="main"></frame>
	</frameset>
	
	<frame src="${pageContext.request.contextPath}/admin/bottom.jsp" name="top"></frame>
		
</frameset>

</html>
