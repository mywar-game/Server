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
<script type="text/javascript">
		function add(gameWebId) {
			window.location.href = "addCMD";
		}
		function detail(serverId) {
			window.location.href = "commandDetails?serverId=" + serverId;
		}
	</script>
<body>
	<input type="submit" value="<s:text name="cmd.add"></s:text>" class="button" onclick=add(); />
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
			<td><s:text name="cmd.currentCmd"></s:text></td>
			<td><s:text name="cmd.currentCmdStatus"></s:text></td>
			<td><s:text name="cmd.execInfo"></s:text></td>
			<td><s:text name="cmd.execTime"></s:text></td>
			<td width="35"><s:text name="cmd.details"></s:text></td>
		</tr>
		<s:iterator var="cmd" value="cmdList">
			<tr>
				<td>${map[cmd.gameWebId].serverName}</td>
				<td>${cmd.serverId}</td>
				<td><s:text name="%{'cmd.cmdType_'+#cmd.currentCmd}"></s:text></td>
				<td><s:text name="%{'cmd.status_'+#cmd.currentCmdStatus}"></s:text></td>
				<td>${cmd.execInfo}</td>
				<td><fmt:formatDate value="${cmd.createdTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><a href="#" onclick='detail("${cmd.serverId}")'><s:text name="cmd.detail"></s:text></a></td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>
