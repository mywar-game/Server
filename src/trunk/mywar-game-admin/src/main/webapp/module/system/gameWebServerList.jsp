<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.system.bo.GameWebServer"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="gameWebServerListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
	
		function del(serverId, gameWebId) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='gameWebServer.serverId'></s:text></s:param><s:param>"+serverId+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delGameWebServer?serverId=" + serverId + "&gameWebId=" + gameWebId;
				ajaxobj.callback = function() {
					window.location.href = "gameWebServerList";
					//var tr = document.getElementById(gameWebId);
					//var table = document.getElementById("table");
					//table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
		
		function update(serverId, gameWebId) {
			window.location.href = "updateGameWebServer?serverId=" + serverId + "&gameWebId=" + gameWebId;
		}
	
		function add(gameWebId) {
			window.location.href = "addGameWebServer?gameWebId=" + gameWebId;
		}
		
		function operate(status, serverId, gameWebId) {
			// 开启   (状态：0 关闭  1 开启 )
			if (status == 0) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "setGameServerStatus?isCommit=T&status=" + status + "&serverId=" + serverId + "&gameWebId=" + gameWebId;		
				ajaxobj.callback = function(){
					window.location.href = "gameWebServerList";
				}
				ajaxobj.send();
			
				return;
			}
		
			var hight = (screen.height - 350) / 2.8;
			var width = (screen.width - 680) / 2;
			
			var notice = window.showModalDialog("setGameServerStatus?serverId=" + serverId + "&gameWebId=" + gameWebId, "", "dialogLeft=" + width
				+ "; dialogTop=" + hight
				+ "; dialogWidth=680px; dialogHeight=350px; location=no");
				
			
			if (notice == null || notice == '') {
				return;
			}
			
			var ajaxobj = new Ajax();
			ajaxobj.url = "setGameServerStatus?isCommit=T&status=" + status + "&serverId=" + serverId + "&gameWebId=" + gameWebId + "&notice=" + encodeURI(encodeURI(notice));
			ajaxobj.callback = function() {
				window.location.href = "gameWebServerList";
			}
			ajaxobj.send();			
		}
		
		// 区服务器状态开启、关闭
		function operateStatus(gameWebId, serverName, status) {
			// 开启   (状态：0 关闭  1 开启 )
			if (status == 0) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "setLoginServerStatus?isCommit=T&status=" + status + "&gameWebId=" + gameWebId;		
				ajaxobj.callback = function(){
					window.location.href = "gameWebServerList";
				}
				ajaxobj.send();
			
				return;
			}
		
			var hight = (screen.height - 350) / 2.8;
			var width = (screen.width - 680) / 2;
			
			var notice = window.showModalDialog("setLoginServerStatus?serverName=" + encodeURI(encodeURI(serverName)) + "&gameWebId=" + gameWebId, "", "dialogLeft=" + width
				+ "; dialogTop=" + hight
				+ "; dialogWidth=680px; dialogHeight=350px; location=no");				
			
			if (notice == null || notice == '') {
				return;
			}
			
			var ajaxobj = new Ajax();
			ajaxobj.url = "setLoginServerStatus?isCommit=T&status=" + status + "&gameWebId=" + gameWebId + "&notice=" + encodeURI(encodeURI(notice));
			ajaxobj.callback = function() {
				window.location.href = "gameWebServerList";
			}
			ajaxobj.send();		
		}
		
		function reflash(gameWebId) {
			var ajaxobj = new Ajax();
			ajaxobj.url = "reflashServerList?number=" + Math.random() + "&gameWebId=" + gameWebId;
			ajaxobj.callback=function(){
				var responseMsg = eval('(' + ajaxobj.gettext() + ')');
				if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="serverList.reflash"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="serverList.reflash"></s:text></s:param></s:text>');
				}
			}
			ajaxobj.send();	
		}
		
		var clearServerStats = function(serverId) {
		
		}
	</script>
	<body>
		<!--
		<input type="submit" value="<s:text name='addGameWebServerJsp.title'></s:text>" class="button" onclick=add(); />		
		<input type="button" value='<s:text name="activityListJsp.reflashActivity"></s:text>' class="button" onclick="" />
		-->
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="gameWebServerListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td width="265">
					<s:text name="gameWeb.name"></s:text>
				</td>
				<td >
					<s:text name="gameWebServer.serverId"></s:text>
				</td>
				<td >
					<s:text name="gameWebServer.serverName"></s:text>
				</td>
				<td >
					<s:text name="gameWebServer.serverUrl"></s:text>
				</td>
				<td>
					<s:text name="gameWebServer.serverPort"></s:text>
				</td>
				<td>
					<s:text name="gameWebServer.apiPort"></s:text>
				</td>
				<td>
					<s:text name="gameWebServer.status"></s:text>
				</td>
				<td>
					<s:text name="gameWebServer.openTime"></s:text>
				</td>
				<td>
					<s:text name="gameWebServer.createTime"></s:text>
				</td>
				<td width="75">
					<s:text name="operate"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<%
				List<GameWeb> list = (List<GameWeb>) request.getAttribute("list");
				Map<GameWeb, List<GameWebServer>> gameWebServerMap = (Map<GameWeb, List<GameWebServer>>) request.getAttribute("gameWebServerMap");
  				
				// 排序
				Collections.sort(list, new Comparator<GameWeb>() {
					public int compare(GameWeb arg0, GameWeb arg1) {
						return arg0.getServerId().compareTo(arg1.getServerId());
					}
				});
				
				for (GameWeb gameWeb : list) {
					List<GameWebServer> serverList = gameWebServerMap.get(gameWeb);
					int rowspan = serverList.size();
					if (serverList.size() == 0) {
						rowspan = 1;
					}
			%>
				<tr id="<%=gameWeb.getServerId()%>">
					<td rowspan="<%=rowspan%>" title="<%=gameWeb.getServerName()%>">
						<%=gameWeb.getServerName()%> <a href="#" onclick='add("<%=gameWeb.getServerId()%>")'><s:text name="add"></s:text></a>
						<a href="#" onclick='reflash("<%=gameWeb.getServerId()%>")'><s:text name="updateGameWebServerJsp.reflash"></s:text></a>
						<a href="#" onclick="operateStatus('<%=gameWeb.getServerId()%>', '<%=gameWeb.getServerName()%>', '<%=gameWeb.getOpenStatus()%>')">
							<% if (gameWeb.getOpenStatus() == 0) { %>
								<font color="#ff0000"><s:text name="server.openStatus_1"></s:text></font>
							<% } else {%>
								<s:text name="server.openStatus_0"></s:text>
							<%}%>
						</a>
					</td>
					<%
					for (GameWebServer gameWebServer : serverList) {					 		
					%>		
					<td title="<%=gameWebServer.getServerId()%>">
						<%=gameWebServer.getServerId()%>
					</td>
					<td title="<%=gameWebServer.getServerName()%>">
						<%=gameWebServer.getServerName()%>
					</td>
					<td title="<%=gameWebServer.getServerUrl()%>">
						<%=gameWebServer.getServerUrl()%>
					</td>
					<td title="<%=gameWebServer.getServerPort()%>">
						<%=gameWebServer.getServerPort()%>
					</td>
					<td title="<%=gameWebServer.getApiPort()%>">
						<%=gameWebServer.getApiPort()%>
					</td>
					<td title="<%=gameWebServer.getStatus()%>">
					
					<%
					switch (gameWebServer.getStatus()) {
						case 1:
						%><s:text name="server.status_1"></s:text><% 
						break;
						case 2:
						%><s:text name="server.status_2"></s:text><% 
						break;
						case 3:
						%><s:text name="server.status_3"></s:text><% 
						break;
						case 4:
						%><s:text name="server.status_4"></s:text><% 
						break;
						case 100:
						%><s:text name="server.status_100"></s:text><% 
						break;
						default:
						break;
					}
					%>
					
					
					</td>
					<td title="<%=gameWebServer.getOpenTime()%>">
						<%=gameWebServer.getOpenTime()%>
					</td>
					<td title="<%=gameWebServer.getCreateTime()%>">
						<%=gameWebServer.getCreateTime()%>
					</td>
					<td>
						<a href="#" onclick='operate("<%=gameWebServer.getOpenStatus()%>", "<%=gameWebServer.getServerId()%>", "<%=gameWeb.getServerId()%>")'>
						<% if (gameWebServer.getOpenStatus() == 0) { %>
							<font color="#ff0000"><s:text name="server.openStatus_1"></s:text></font>
						<%} else {%>
							<s:text name="server.openStatus_0"></s:text>
						<%}%>
						</a>
					</td>
					<td>
						<a href="#" onclick='del("<%=gameWebServer.getServerId()%>", "<%=gameWeb.getServerId()%>")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("<%=gameWebServer.getServerId()%>", "<%=gameWeb.getServerId()%>")'><s:text name="update"></s:text></a>
					</td>
					</tr>
					<tr>
					<%}%>			
				</tr>
			
			<%}%>
			<td colspan="100">
				<aldtags:pageTag paraStr="isCommit,T,pageSize,${pageSize}" />
			</td>
		</table>
	</body>
</html>
 	