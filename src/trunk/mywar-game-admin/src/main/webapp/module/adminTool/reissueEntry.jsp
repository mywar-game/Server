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
</head>
<body>
	<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
		<tr>
			<td>用户 ID</td>
			<td>用户名</td>
			<td>金额</td>
			<td>状态</td>
			<td>提交时间</td>
		</tr>
		<tr>
			<td>${paymentOrder.userId}</td>
			<td>${paymentOrder.userName }</td>
			<td>${paymentOrder.amount }</td>
			<td><s:if test="paymentOrder.status == 0">未充值</s:if><s:else>已补发</s:else></td>
			<td>${paymentOrder.createdTime }</td>
		</tr>
		
	</table>

</body>
</html>