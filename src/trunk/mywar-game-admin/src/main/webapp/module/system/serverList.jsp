<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.system.bo.Server"%>
<%@page import="com.adminTool.bo.Partner"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="serverListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
	
		function del(serverId, gameWebId, partnerId, aliaServerId) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='server.serverId'></s:text></s:param><s:param>"+serverId+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delServer?serverId=" + serverId + "&gameWebId=" + gameWebId
					+ "&partnerId=" + partnerId + "&aliaServerId=" + aliaServerId;
				ajaxobj.callback = function() {
					window.location.href = "serverList";
					//var tr = document.getElementById(serverId);
					//var table = document.getElementById("table");
					//table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(serverId, gameWebId, partnerId, aliaServerId) {
			window.location.href = "updateServer?serverId=" + serverId + "&gameWebId=" + gameWebId 
				+ "&partenerId=" + partnerId + "&aliaServerId=" + aliaServerId;
		}
	
		function add(gameWebId) {
			window.location.href = "addServer?gameWebId=" + gameWebId;
		}
		
		function updateConfig(gameWebId) {
			var ajaxobj = new Ajax();
			ajaxobj.url = "updateConfigs?number=" + Math.random() + "&gameWebId=" + gameWebId;
			ajaxobj.callback=function(){
				var responseMsg = eval('(' + ajaxobj.gettext() + ')');
				if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="serverList.updateconfigs"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="serverList.updateconfigs"></s:text></s:param></s:text>');
				}
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
		
	</script>
	<body>		
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="serverListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="gameWeb.name"></s:text>
				</td>
				<td>
					<s:text name="server.partner"></s:text>
				</td>
				<td>
					<s:text name="server.partenerId"></s:text>
				</td>
				<td >
					<s:text name="server.serverId"></s:text>
				</td>
				<td >
					<s:text name="server.serverName"></s:text>
				</td>
				<td>
					<s:text name="server.status"></s:text>
				</td>			
				<td>
					<s:text name="server.openTime"></s:text>
				</td>
				<td>
					<s:text name="server.aliaServerId"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<%
				Map<GameWeb, Integer> sizeMap = (Map<GameWeb, Integer>) request.getAttribute("sizeMap");
				Map<GameWeb, Map<String, List<Server>>> gameWebMap = (Map<GameWeb, Map<String, List<Server>>>) 
					request.getAttribute("gameWebMap");
				Map<String, Partner> partnerMap = (Map<String, Partner>) request.getAttribute("partnerMap");			
						
				// 排序
				ArrayList<Map.Entry<GameWeb, Map<String, List<Server>>>> mappingList = new ArrayList<Map.Entry<GameWeb, Map<String, List<Server>>>>(gameWebMap.entrySet());
  				// 通过比较器实现比较排序
  				Collections.sort(mappingList, new Comparator<Map.Entry<GameWeb, Map<String, List<Server>>>>(){
   					public int compare(Map.Entry<GameWeb, Map<String, List<Server>>> mapping1,Map.Entry<GameWeb, Map<String, List<Server>>> mapping2){
   						return mapping1.getKey().getServerId().compareTo(mapping2.getKey().getServerId());
   					}
  				}); 
				
  				for(Map.Entry<GameWeb, Map<String, List<Server>>> mapping : mappingList) { 
  					/* List<VersionManagerApk> apkList = mapping.getValue();
  					GameWeb gameWeb = mapping.getKey();  */
  					Map<String, List<Server>> serverMap = mapping.getValue();
  					GameWeb gameWeb = mapping.getKey();
  					
				/* for (GameWeb gameWeb :gameWebMap.keySet()) {
					Map<String, List<Server>> serverMap = gameWebMap.get(gameWeb);	 */
					Integer rowspan = sizeMap.get(gameWeb);
					if (rowspan == 0) {
						rowspan = 1;
					}			
			%>
			<tr id="<%=gameWeb.getServerId()%>">
				<td rowspan="<%=rowspan%>" title="<%=gameWeb.getServerName()%>">
					<%=gameWeb.getServerName()%>  <a href="#" onclick='add("<%=gameWeb.getServerId()%>")'><s:text name="add"></s:text></a>
					<a href="#" onclick='updateConfig("<%=gameWeb.getServerId()%>")'><s:text name="serverList.updateconfigs"></s:text></a>
					<a href="#" onclick='reflash("<%=gameWeb.getServerId()%>")'><s:text name="serverList.reflash"></s:text></a>
				</td>
				<%
					for (String pid : serverMap.keySet()) {
						List<Server> serverList = serverMap.get(pid);	
				%>
				<td rowspan=<%=serverList.size()%> title="<%=partnerMap.get(pid).getPName()%>">
					<%=partnerMap.get(pid).getPName()%>
				</td>
				<td rowspan=<%=serverList.size()%> title="<%=pid%>">
					<%=pid%>
				</td>
				
				<%
					for (Server server : serverList) {
				%>
				
				<td title="<%=server.getServerId()%>">
					<%=server.getServerId()%>
				</td>
				<td title="<%=server.getServerName()%>">
					<%=server.getServerName()%>
				</td>
				<td title="<%=server.getStatus()%>">
				<%
					switch (server.getStatus()) {
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
						%>
						<font color="#FF0000">
							<s:text name="server.status_4"></s:text>
						</font>
						<% 
						break;
						case 100:
						%><s:text name="server.status_100"></s:text><% 
						break;
						default:
						break;
					}
				%>
				</td>
				<td title="<%=server.getOpenTime()%>">
					<%=server.getOpenTime()%>				
				</td>
				<td title="<%=server.getAliaServerId()%>">
					<%=server.getAliaServerId()%>
				</td>
				<td>
					<a href="#" onclick='del("<%=server.getServerId()%>", "<%=gameWeb.getServerId()%>", 
						"<%=server.getPartenerId()%>", "<%=server.getAliaServerId()%>")'><s:text name="delete"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='update("<%=server.getServerId()%>", "<%=gameWeb.getServerId()%>", 
						"<%=server.getPartenerId()%>", "<%=server.getAliaServerId()%>")'><s:text name="update"></s:text></a>
				</td>
				
				</tr>
				<tr>
				<%}%>
				<%}%>				
			</tr>
			<%}%>
			
			<td colspan="100">
				<aldtags:pageTag paraStr="isCommit,T,pageSize,${pageSize}" />
			</td>
		</table>
	</body>
</html>
 	