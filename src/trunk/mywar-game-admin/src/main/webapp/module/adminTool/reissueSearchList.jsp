<%@page import="com.system.bo.GameWeb"%>
<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" pageEncoding="Utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>Insert title here</title>
	
	<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script>
			var searchReceipt = function() {
				var gameWebId = document.getElementById("gameWebId").value;
				var lodoId = document.getElementById("lodoId").value;
				if (gameWebId == -1) {
					alert("请选择服务区");
					return false;
				} else {
					if (!lodoId) {
						alert("请输入用户Id");
						return false;
					}
					return true;
				}
			}
		</script>
</head>
<body>

<form name="receipt" action="reissueSearchById?isCommit=T" method="post" onsubmit="return searchReceipt()">
	<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
	<s:select list="gameWebList" name="gameWebId" id="gameWebId" headerKey="-1" headerValue="--请选择服务区--" listKey="serverId" listValue="serverName" onchange=""/>
	<tr><td></td>
	
	<td>请输入用户ID</td>
	</tr>
	
	<tr><td></td><td><input type="text" name="lodoId" id="lodoId"></td></tr>
	<tr><td></td><td><input type="submit" value="查询"></td></tr>
	</table>
</form>

</body>

</html>