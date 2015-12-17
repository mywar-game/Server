<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="dbServer.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		function del(dbServerId,serverName) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="dbServer.serverName"></s:text></s:param><s:param>'+serverName+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delDbServer?dbServerId=" + dbServerId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(dbServerId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(dbServerId) {
			window.location.href = "updateDbServer?dbServerId=" + dbServerId;
		}
	
		function add() {
			window.location.href = "addDbServer";
		}
	</script>
	<body>
		<input type="submit" value="<s:text name="dbServer.add"></s:text>" class="button" onclick=add(); />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						<s:text name="dbServer.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="dbServer.serverType"></s:text>
				</td>
				<td>
					<s:text name="dbServer.serverName"></s:text>
				</td>
				<td>
					<s:text name="dbServer.serverIp"></s:text>
				</td>
				<td>
					<s:text name="dbServer.serverPort"></s:text>
				</td>
				<td>
					<s:text name="dbServer.dbName"></s:text>
				</td>
				<td>
					<s:text name="dbServer.userName"></s:text>
				</td>
				<td>
					<s:text name="dbServer.password"></s:text>
				</td>

				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="dbServer" value="dbServerList">
				<tr id="${dbServer.dbServerId}">
					<td>
						<s:text name="%{'dbServer.serverType_'+#dbServer.serverType}"></s:text>
					</td>
					<td>
						${dbServer.serverName}
					</td>
					<td>
						${dbServer.serverIp}
					</td>
					<td>
						${dbServer.serverPort}
					</td>
					<td>
						${dbServer.dbName}
					</td>
					<td>
						${dbServer.userName}
					</td>
					<td>
						${dbServer.password}
					</td>

					<td>
						<a href="#" onclick='del(${dbServer.dbServerId},"${dbServer.serverName}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${dbServer.dbServerId}")'><s:text name="update"></s:text></a>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="10">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>