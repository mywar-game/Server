<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.system.bo.BlackInfo"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="blackListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
	
		function del(id, gameWebId) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param>id</s:param><s:param>"+id+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delBlackInfo?id=" + id + "&gameWebId=" + gameWebId;
				ajaxobj.callback = function() {
					window.location.href = "blackList";
					//var tr = document.getElementById(id);
					//var table = document.getElementById("table");
					//table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(id, gameWebId) {
			window.location.href = "updateBlackInfo?id=" + id + "&gameWebId=" + gameWebId;
		}
	
		function add(gameWebId) {
			window.location.href = "addBlackInfo?gameWebId=" + gameWebId;
		}
	</script>
	<body>
		<!--
		<input type="submit" value="<s:text name='addBlackInfoJsp.title'></s:text>" class="button" onclick=add(); />
		<input type="button" value='<s:text name=""></s:text>' class="button" onclick="" />
		-->
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="blackListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<s:text name="gameWeb.name"></s:text>
				</td>
				<td >
					<s:text name="blackInfo.value"></s:text>
				</td>
				<td >
					<s:text name="blackInfo.blackType"></s:text>
				</td>
				<td>
					<s:text name="blackInfo.valueType"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<%
				Map<GameWeb, List<BlackInfo>> map = (Map<GameWeb, List<BlackInfo>>) request.getAttribute("infoMap");
				
				// 排序
				ArrayList<Map.Entry<GameWeb, List<BlackInfo>>> mappingList = new ArrayList<Map.Entry<GameWeb, List<BlackInfo>>>(map.entrySet());
				// 通过比较器实现比较排序
				Collections.sort(mappingList, new Comparator<Map.Entry<GameWeb, List<BlackInfo>>>(){
					public int compare(Map.Entry<GameWeb, List<BlackInfo>> mapping1,Map.Entry<GameWeb, List<BlackInfo>> mapping2){
						return mapping1.getKey().getServerId().compareTo(mapping2.getKey().getServerId());
					}
				}); 
			
				for(Map.Entry<GameWeb, List<BlackInfo>> mapping : mappingList) { 
					List<BlackInfo> infoList = mapping.getValue();
					GameWeb gameWeb = mapping.getKey(); 
			
				/* for (GameWeb gameWeb : map.keySet()) {
					List<BlackInfo> infoList = map.get(gameWeb); */
					int rowspan = infoList.size();
					if (infoList.size() == 0) {
						rowspan = 1;
					}	
			%>
			<tr id="<%=gameWeb.getServerId()%>">
				<td rowspan="<%=rowspan%>" title="<%=gameWeb.getServerName()%>">
					<%=gameWeb.getServerName()%> <a href="#" onclick='add("<%=gameWeb.getServerId()%>")'><s:text name="add"></s:text></a>				
				</td>
				<%
					for (BlackInfo info : infoList) {				
				%>
				<td title="<%=info.getValue()%>">
					<%=info.getValue()%>
				</td>
				<td >
					<%
						switch (info.getBlackType()) {
							case 0:
							%><s:text name="blackInfo.blackType_0"></s:text><%
							break;
							case 1:
							%><s:text name="blackInfo.blackType_1"></s:text><%
							break;
							case 2:
							%><s:text name="blackInfo.blackType_2"></s:text><%
							break;
							case 3:
							%><s:text name="blackInfo.blackType_3"></s:text><%
							break;
							case 4:
							%><s:text name="blackInfo.blackType_4"></s:text><%
							break;
						}					
					%>
				</td>
				<td >
					<%
						switch (info.getValueType()) {
							case 1:
							%><s:text name="blackInfo.valueType_1"></s:text><%
							break;
							case 2:
							%><s:text name="blackInfo.valueType_2"></s:text><%
							break;
							case 3:
							%><s:text name="blackInfo.valueType_3"></s:text><%
							break;
						}					
					%>
				</td>				
				<td>
					<a href="#" onclick='del("<%=info.getId()%>", "<%=gameWeb.getServerId()%>")'><s:text name="delete"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='update("<%=info.getId()%>", "<%=gameWeb.getServerId()%>")'><s:text name="update"></s:text></a>
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
 	