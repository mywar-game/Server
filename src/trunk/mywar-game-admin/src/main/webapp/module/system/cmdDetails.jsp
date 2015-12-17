<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.system.bo.Notice"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><s:text name="cmdListJsp.title"></s:text></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="../../css/main.css" rel="stylesheet" type="text/css" />

</head>
<script type="text/javascript" src="../../js/ajax.js"></script>
<body>
	<table id="table" cellpadding="3" cellspacing="1" border="0"
		width="100%" align=center>
		<tr class="border">
			<td class="td_title" colspan="22" align="center">
				<center>
					<s:text name="cmdListJsp.title"></s:text>
				</center>
			</td>
		</tr>
		<tr>
			<td><s:text name="gameWeb.name"></s:text></td>
			<td><s:text name="cmd.serverId"></s:text></td>
			<td><s:text name="cmd.cmd"></s:text></td>
			<td><s:text name="cmd.cmdStatus"></s:text></td>
			<td><s:text name="cmd.info"></s:text></td>
			<td><s:text name="cmd.execTime"></s:text></td>
		</tr>
		<s:iterator var="cmd" value="cmdList">
			<tr>
				<td>${map[cmd.gameWebId].serverName}</td>
				<td>${cmd.serverId}</td>
				<td><s:text name="%{'cmd.cmdType_'+#cmd.currentCmd}"></s:text></td>
				<td><s:text name="%{'cmd.status_'+#cmd.currentCmdStatus}"></s:text></td>
				<td>${cmd.execInfo}</td>
				<td><fmt:formatDate value="${cmd.createdTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>
