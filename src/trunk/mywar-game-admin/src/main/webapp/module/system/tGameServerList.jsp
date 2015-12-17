<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.system.bo.TGameServer"%>
<%@page import="com.system.domain.ServerNameAndIP"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="tGameServerListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		function reflashServerCache() {
			var ajaxobj = new Ajax();
			ajaxobj.url="reflashServerCache?number="+Math.random();
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');

		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="tGameServerListJsp.reflashServerCache"></s:text></s:param></s:text>');
					//alert("现有可以统计数据的服务器包括(" + responseMsg.servers + ")");
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="tGameServerListJsp.reflashServerCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
	
		function del(serverID) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='serverId'></s:text></s:param><s:param>"+serverID+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delTGameServer?serverId=" + serverID;
				ajaxobj.callback = function() {
					window.location.href = "tGameServerList";
					//alert(ajaxobj.gettext());
					//var tr = document.getElementById(serverID);
					//var table = document.getElementById("table");
					//table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function downloadingApplication(serverID) {
			var ajaxobj = new Ajax();
			ajaxobj.url = "downloadingApplication?serverId=" + serverID;
			ajaxobj.callback = function() {
				window.location.href = "downloadingZip?fileName=application.zip";
			}
			ajaxobj.send();
		}
	
		function update(serverID) {
			window.location.href = "updateTGameServer?serverId=" + serverID;
		}
	
		function add(gameWebServerId) {
			window.location.href = "addTGameServer?gameWebServerId=" + gameWebServerId;
		}
		function cls(serverId) {
			if (window.confirm("<s:text name='clearConfirm'><s:param><s:text name='data.serverId'></s:text></s:param><s:param>" + serverId + "</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url='clearTGameServer?serverId=' + serverId;
				ajaxobj.callback = function() {
					alert('<s:text name="action.success"><s:param><s:text name="tGameServerListJsp.clearServerStats"></s:text></s:param></s:text>');
					window.location.href = "tGameServerList";
				}
				ajaxobj.send();
			}
	   }
	   
	   var Tips = {
	   		mousePos : function(e) {
	   			var x, y;
	   			var e = e || window.event;
	   			return {
	   				x : e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft,
	   				y : e.clientY + document.body.scrollTop + document.documentElement.scrollTop
	   			};
	   		},
	   };
		
	</script>
	<body>
		<!--
		<input type="submit" value="<s:text name='addTGameServerJsp.title'></s:text>" class="button" onclick=add(); />
		-->
		<input type="button" value='<s:text name="tGameServerListJsp.reflashServerCache"></s:text>' class="button" onclick="reflashServerCache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="tGameServerListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<s:text name="gameWeb.name"></s:text>
				</td>
				<td width="10">
					<s:text name="serverId"></s:text>
				</td>
				<td >
					<s:text name="serverName"></s:text>
				</td>
				<td>
					<s:text name="serverAlias"></s:text>
				</td>
				<td>
					<s:text name="serverIp"></s:text>
				</td>
				<td>
					<s:text name="serverPoint"></s:text>
				</td>
				<td>
					<s:text name="webOpenPort"></s:text>
				</td>
				<td>
					<s:text name="webServerPath"></s:text>
				</td>
				<td>
					<s:text name="serverCommunicationKey"></s:text>
				</td>
				<td >
					<s:text name="serverOpernTime"></s:text>
				</td>
				<!--
				<td>
					<s:text name="gameServerUrl"></s:text>
				</td>
				-->
				<td>
					<s:text name="logDbServerCode"></s:text>
				</td>
				<td>
					<s:text name="configDbServerCode"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
				<td width="35">
					<s:text name="clear"></s:text>
				</td>
			</tr>
			
			<%
				Map<Integer, ServerNameAndIP> dbServerNameMap = (Map<Integer, ServerNameAndIP>) request.getAttribute("dbServerNameMap");
				Map<GameWeb, List<TGameServer>> gameWebMap = (Map<GameWeb, List<TGameServer>>) request.getAttribute("gameWebMap");
					
				// 排序
				ArrayList<Map.Entry<GameWeb, List<TGameServer>>> mappingList = new ArrayList<Map.Entry<GameWeb, List<TGameServer>>>(gameWebMap.entrySet());
  				// 通过比较器实现比较排序
  				Collections.sort(mappingList, new Comparator<Map.Entry<GameWeb, List<TGameServer>>>(){
   					public int compare(Map.Entry<GameWeb, List<TGameServer>> mapping1,Map.Entry<GameWeb, List<TGameServer>> mapping2){
   						return mapping1.getKey().getServerId().compareTo(mapping2.getKey().getServerId());
   					}
  				}); 
				
  				for(Map.Entry<GameWeb, List<TGameServer>> mapping : mappingList) { 
  					/* List<VersionManagerApk> apkList = mapping.getValue();
  					GameWeb gameWeb = mapping.getKey();  */
  					List<TGameServer> list = mapping.getValue();
  					GameWeb gameWeb = mapping.getKey();
				
				/* 
				for (GameWeb gameWeb : gameWebMap.keySet()) {
					List<TGameServer>  list = gameWebMap.get(gameWeb); */
					int rowspan = list.size();
					if (list.size() == 0) {
						rowspan = 1;
					}
				
			%>
				<tr id="<%=gameWeb.getServerId()%>">
						<td rowspan="<%=rowspan%>" title="<%=gameWeb.getServerName()%>">
						<%=gameWeb.getServerName()%> <a href="#" onclick='add("<%=gameWeb.getServerId()%>")'><s:text name="add"></s:text></a>
					</td>
					
					<%
						for (TGameServer data : list) {
					%>
					   <td title="<%=data.getServerId()%>">
						<%=data.getServerId()%>
					</td> 
					<td title="<%=data.getServerName()%>">
						<%=data.getServerName()%>
					</td>
					<td title="<%=data.getServerAlias()%>">
						<%=data.getServerAlias()%>
					</td>
					<td title="<%=data.getServerIp()%>">
						<%=data.getServerIp()%>
					</td>
					<td title="<%=data.getServerPoint()%>">
						<%=data.getServerPoint()%>
					</td>
					<td title="<%=data.getWebOpenPort()%>">
						<%=data.getWebOpenPort()%>
					</td>
					<td title="<%=data.getWebServerPath()%>">
						<%=data.getWebServerPath()%>
					</td>
					<td title="<%=data.getServerCommunicationKey()%>">
						******
					</td>
					<!--
					<td title="<%=data.getServerOpernTime()%>">
						<%=data.getServerOpernTime()%>
					
						<s:text name="format.date_ymd">
							<s:param value="#data.serverOpernTime"></s:param>
						</s:text>
					</td>
					-->
					
					<td title="<%=data.getGameServerUrl()%>">
						******
					</td>
					<td title="<%=dbServerNameMap.get(data.getLogDbServerCode()).getServerName()%>">
						<%if (dbServerNameMap.get(data.getLogDbServerCode()) != null) { %>
							<a href="updateDbServer?dbServerId=<%=data.getLogDbServerCode()%>">
							<%=dbServerNameMap.get(data.getLogDbServerCode()).getServerName()%>
							</a>	
						<%}%>						
					</td>
					<td title="<%=dbServerNameMap.get(data.getConfigDbServerCode()).getServerName()%>">
						<%if (dbServerNameMap.get(data.getConfigDbServerCode()) != null) {%>
							<a href="updateDbServer?dbServerId=<%=data.getConfigDbServerCode()%>">
								<%=dbServerNameMap.get(data.getConfigDbServerCode()).getServerName()%>
							</a>
						<%}%>						
					</td>
					 <td>
						<a href="#" onclick='del("<%=data.getServerId()%>")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("<%=data.getServerId()%>")'><s:text name="update"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='cls("<%=data.getServerId()%>")'><s:text name="clear"></s:text></a> 
					</td>
					</tr>
					
					<tr>	
					<%}%>
				</tr>					
			<%}%>
			
			<tr>
				<td colspan="22">
					<aldtags:pageTag paraStr="isCommit,T,pageSize,${pageSize}"/>
				</td>
			</tr>
		</table>
		<!-- 添加说明 -->
		<s:text name="tGameServerListJsp.note"></s:text><br>
		<s:generator separator="," val="%{getText('tGameServerListJsp.note_value')}">
			<s:iterator var="str">
				<s:text name="%{'tGameServerListJsp.note'+#str}"></s:text><br>
			</s:iterator>
		</s:generator>
		<br/>
		2、<font color="red">添加服务器后，按“刷新服务器信息”按钮。</font>
	</body>
</html>
