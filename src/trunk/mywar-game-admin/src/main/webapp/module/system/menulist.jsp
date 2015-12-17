<%@ include file="../common/taglib.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title><s:text name="menulistJsp.title"></s:text></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <link href="../../css/main.css" rel="stylesheet" type="text/css" />
   
  </head>
  <body> 
  <table cellpadding="3" cellspacing="1" border="0" width="100%"
			align=center>
			<tr >
				<td ><a href="addmenuchild?parentId=0"><s:text name="menulistJsp.addFirstNode"></s:text></a></td>
				</tr>
				</table>
  ${treeStr}
  </body>
</html>
