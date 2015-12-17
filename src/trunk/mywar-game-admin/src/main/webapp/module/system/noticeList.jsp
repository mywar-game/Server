<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.system.bo.Notice"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="noticeListJsp.title"></s:text></title>

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
			if (window.confirm("<s:text name='deleteConfirm'><s:param>id</s:param><s:param>"+serverId+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delNotice?serverId=" + serverId + "&gameWebId=" + gameWebId;
				ajaxobj.callback = function() {
					window.location.href = "noticeList";
					//var tr = document.getElementById(id);
					//var table = document.getElementById("table");
					//table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(serverId, gameWebId) {
			window.location.href = "updateNotice?serverId=" + serverId + "&gameWebId=" + gameWebId;
		}
	
		function add(gameWebId) {
			window.location.href = "addNotice?gameWebId=" + gameWebId;
		}
	</script>
	<body>
		<!--
		<input type="submit" value="<s:text name='addNoticeJsp.title'></s:text>" class="button" onclick=add(); />
		<input type="button" value='<s:text name=""></s:text>' class="button" onclick="" />
		-->
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="noticeListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<s:text name="gameWeb.name"></s:text>
				</td>
				<td >
					<s:text name="notice.id"></s:text>
				</td>
				<td >
					<s:text name="notice.title"></s:text>
				</td>
				<td >
					<s:text name="notice.content"></s:text>
				</td>
				<td>
					<s:text name="notice.isEnable"></s:text>
				</td>
				<td>
					<s:text name="notice.createdTime"></s:text>
				</td>
				<td>
					<s:text name="notice.updatedTime"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<%
				Map<GameWeb, List<Notice>> map = (Map<GameWeb, List<Notice>>) request.getAttribute("noticeMap");
				
				// 排序
				ArrayList<Map.Entry<GameWeb, List<Notice>>> mappingList = new ArrayList<Map.Entry<GameWeb, List<Notice>>>(map.entrySet());
				// 通过比较器实现比较排序
				Collections.sort(mappingList, new Comparator<Map.Entry<GameWeb, List<Notice>>>(){
					public int compare(Map.Entry<GameWeb, List<Notice>> mapping1,Map.Entry<GameWeb, List<Notice>> mapping2){
						return mapping1.getKey().getServerId().compareTo(mapping2.getKey().getServerId());
					}
				}); 
			
				for(Map.Entry<GameWeb, List<Notice>> mapping : mappingList) { 
					List<Notice> noticeList = mapping.getValue();
					GameWeb gameWeb = mapping.getKey(); 
				
				/* for (GameWeb gameWeb : map.keySet()) {
					List<Notice> noticeList = map.get(gameWeb); */
					int rowspan = noticeList.size();
					if (noticeList.size() == 0) {
						rowspan = 1;
					}	
			%>
			<tr id="<%=gameWeb.getServerId()%>">
				<td rowspan="<%=rowspan%>" title="<%=gameWeb.getServerName()%>">
					<%=gameWeb.getServerName()%> <a href="#" onclick='add("<%=gameWeb.getServerId()%>")'><s:text name="add"></s:text></a>				
				</td>
				<%
					for (Notice notice : noticeList) {				
				%>
				<td id="<%=notice.getServerId()%>" title="<%=notice.getServerId()%>">
					<%=notice.getServerId()%>
				</td>
				<td title="<%=notice.getTitle()%>">
					<%=notice.getTitle()%>
				</td>
				<td title="<%=notice.getContent()%>">
					<%=notice.getContent2()%>
				</td>
				<td title="<%=notice.getIsEnable()%>">
					<%
					switch (notice.getIsEnable()) {
						case 0:
						%><s:text name="notice.isEnable_0"></s:text><% 
						break;
						case 1:
						%><s:text name="notice.isEnable_1"></s:text><% 
						break;
						default:
						break;
					}
					%>
				</td>
				<td title="<%=notice.getCreatedTime()%>">
					<%=notice.getCreatedTime()%>
				</td>
				<td title="<%=notice.getUpdatedTime()%>">
					<%if (notice.getUpdatedTime() != null) {%>
						<%=notice.getUpdatedTime()%>
					<%}%>
				</td>
				<td>
					<a href="#" onclick='del("<%=notice.getServerId()%>", "<%=gameWeb.getServerId()%>")'><s:text name="delete"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='update("<%=notice.getServerId()%>", "<%=gameWeb.getServerId()%>")'><s:text name="update"></s:text></a>
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
 	