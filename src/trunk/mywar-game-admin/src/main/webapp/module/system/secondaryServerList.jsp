<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="secondaryServer.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		function del(serverId,serverName) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="secondaryServer.serverName"></s:text></s:param><s:param>'+serverName+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delSecondaryServer?serverId=" + serverId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(serverId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(serverId) {
			window.location.href = "updateSecondaryServer?serverId=" + serverId;
		}
	
		function add() {
			window.location.href = "addSecondaryServer";
		}
	</script>
	<body>
		<input type="submit" value="<s:text name="secondaryServer.add"></s:text>" class="button" onclick=add(); />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						<s:text name="secondaryServer"></s:text>
					</center>
				</td>
			</tr>
			<tr>
					<td>
					<s:text name="secondaryServer.serverName"></s:text>
				</td>
				<td>
					<s:text name="secondaryServer.serverIp"></s:text>
				</td>
				<td>
					<s:text name="secondaryServer.serverPort"></s:text>
				</td>
				<td>
					<s:text name="secondaryServer.logDbServerCode"></s:text>
				</td>
				<td>
					<s:text name="secondaryServer.configDbServerCode"></s:text>
				</td>
				<s:if test="secondaryServer.isBattleServer != 1">
					<td>
						<s:text name="secondaryServer.cacheDbServerCode"></s:text>
					</td>
				</s:if>

				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="secondaryServer" value="secondaryServerList">
				<tr id="${secondaryServer.serverId}">
					<td>
						${secondaryServer.serverName}
					</td>
					<td>
						${secondaryServer.serverIp}
					</td>
					<td>
						${secondaryServer.serverPort}
					</td>
					<td title="${idAndDbServerMap[secondaryServer.logDbServerCode].serverIp} ${idAndDbServerMap[secondaryServer.logDbServerCode].dbName}">
						<a href="updateDbServer?dbServerId=${secondaryServer.logDbServerCode}">${idAndDbServerMap[secondaryServer.logDbServerCode].serverName}</a>
					</td>
					<td title="${idAndDbServerMap[secondaryServer.configDbServerCode].serverIp} ${idAndDbServerMap[secondaryServer.configDbServerCode].dbName}">
						<a href="updateDbServer?dbServerId=${secondaryServer.configDbServerCode}">${idAndDbServerMap[secondaryServer.configDbServerCode].serverName}</a>
					</td>
					<s:if test="#secondaryServer.isBattleServer != 1">
						<td title="${idAndDbServerMap[secondaryServer.cacheDbServerCode].serverIp}">
							<a href="updateDbServer?dbServerId=${secondaryServer.cacheDbServerCode}">${idAndDbServerMap[secondaryServer.cacheDbServerCode].serverName}</a>
						</td>
					</s:if>
					<s:if test="#secondaryServer.isBattleServer  == 1">
						<td>
							<s:text name="dbServerList.none"></s:text>
						</td>
					</s:if>
					<td>
						<a href="#" onclick='del(${secondaryServer.serverId},"${secondaryServer.serverName}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${secondaryServer.serverId}")'><s:text name="update"></s:text></a>
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