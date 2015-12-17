<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>首页记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   <link href="../../css/main.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
   <table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
  <tr class="border">
    <td class="td_title" colspan="7">首页记录&nbsp;&nbsp;<a href="updatePage.jsp">添加</a></td>
  </tr>
  <tr>
    <td>编号</td>
      <td>信息</td>
      <td>日期</td>
      <td>备注</td>
      <td>操作</td>
  </tr>
  <s:iterator value="hpages" var="h">
    <tr>
    <td>${h.id }</td>
      <td>${h.info }</td>
      <td><fmt:formatDate value="${h.ddate}" pattern="yyyy-MM-dd"/></td>
      <td>${h.exp }</td>
      <td>【<a href="showPageById?id=${h.id }">修改</a>】【<a href="javascript:if(confirm('将删除网站首页显示记录，是否继续？'))
     location.replace('deleteHomePageById?id=${h.id }');">删除</a>】</td>
  </tr>
  </s:iterator>
  <tr ><td colspan="6"><aldtags:pageTag/></td></tr>
</table>
   
  </body>
</html>
