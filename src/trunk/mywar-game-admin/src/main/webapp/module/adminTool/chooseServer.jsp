<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.system.bo.TGameServer"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>选择服务器</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<base target="_self">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script src="../../js/jquery/jquery.min.js"></script>
	<script>
		function checkAll(obj) {
			var index = obj.value;
			var isChecked = obj.checked;
			var gameWeb = document.getElementById("gameMap_" + index).value;
			var servers = gameWeb.split("_");
			for (var i = 0; i < servers.length - 1; i++) {
				var server = servers[i];
				document.getElementById(server).checked = isChecked;
			}
		}
		function add() {
			var servers = '';
			var flag = 'false';
			var server = document.getElementsByTagName('input');
			for (var i = 0; i < server.length; i++) {
				if (server[i].type == 'checkbox') {
					if (server[i].name == 'serverIds') {
						if (server[i].checked) {
							if (flag != 'false') {
								servers += ',';
							}
							servers += server[i].value;	
							flag = 'true';		
						}
					}
				}
			}
			window.returnValue = servers;
			window.close();
		}
	</script>
<body>
	<table cellpadding="3" cellspacing="2" border="0" width="100%" id="table"
			align=center>
		<tr class="border">
			<td class="td_title" colspan="22" align="center">
				<center>
					选择需要同步的服务器
				</center>
			</td>
		</tr>
	<%
		Map<GameWeb, List<TGameServer>> gameWebMap = (Map<GameWeb, List<TGameServer>>) request.getAttribute("gameWebMap");
		// 排序
		ArrayList<Map.Entry<GameWeb, List<TGameServer>>> mappingList = new ArrayList<Map.Entry<GameWeb, List<TGameServer>>>(gameWebMap.entrySet());
		// 通过比较器实现比较排序
		Collections.sort(mappingList, new Comparator<Map.Entry<GameWeb, List<TGameServer>>>(){
			public int compare(Map.Entry<GameWeb, List<TGameServer>> mapping1,Map.Entry<GameWeb, List<TGameServer>> mapping2){
				return mapping1.getKey().getServerId().compareTo(mapping2.getKey().getServerId());
			}
		}); 
	%>
			
	<%
	for (Map.Entry<GameWeb, List<TGameServer>> mapping : mappingList) { 
  		List<TGameServer> list = mapping.getValue();
  		GameWeb gameWeb = mapping.getKey();
  	%>
  	<tr>
		<td size="2">
			选择服务器	
		</td>
		<td>
			<input type="checkbox" name="gameWeb" value="<%=gameWeb.getServerId()%>" onclick="checkAll(this);"/>
					<%=gameWeb.getServerName()%>(全选)
  			<%
  					String str = "";
					int rowspan = list.size();
					if (list.size() == 0) {
						rowspan = 1;
					}
			
			%>

					&nbsp;(
					<%
						for (TGameServer data : list) {
					%>
							<input type="checkbox" name="serverIds" id="<%=data.getServerAlias()%><%=data.getServerId()%>" value="<%=data.getServerId()%>" />
							<%=data.getServerAlias()%>
					<%
							str += data.getServerAlias();
							str += data.getServerId();
							str += "_";
						}
					%>
					)
				</td>
				<input type="hidden" id="gameMap_<%=gameWeb.getServerId()%>" value="<%=str%>"/>
			</tr>
			<%
				}
			%>
		<tr>
			<td colspan="5" align="center">
				<input type="submit" onclick="add()" value="提交" class="button" />
			</td>
		</tr>
	</table>
</body>
</html>