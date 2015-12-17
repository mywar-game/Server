<%@ include file="../common/taglib.jsp" %>
<%@ page language="java" import="java.util.*,com.framework.common.SystemCode" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title><s:text name="delmenulistJsp.title"></s:text></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <link href="../../css/main.css" rel="stylesheet" type="text/css" />
   
  </head>
  <body> 
 <%
    Integer errorCode = (Integer)request.getAttribute("erroCodeNum");
  if(errorCode!=null&&errorCode!=SystemCode.SYS_SUCESS){
   out.println("<script>alert('"+request.getAttribute("erroDescrip")+"');location.replace('menulist')</script>");
  }
   %>
  </body>
</html>
