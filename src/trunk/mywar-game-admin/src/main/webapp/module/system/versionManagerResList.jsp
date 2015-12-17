<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.adminTool.bo.Partner"%>
<%@page import="com.system.bo.VersionManagerRes"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="versionManagerResListJsp.title"></s:text></title>

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
				ajaxobj.url = "delVersionManagerRes?id=" + id + "&gameWebId=" + gameWebId;
				ajaxobj.callback = function() {
					window.location.href = "versionManagerResList";
				}
				ajaxobj.send();
			}
	
		}
	
		function update(id, gameWebId) {
			window.location.href = "updateVersionManagerRes?id=" + id + "&gameWebId=" + gameWebId;
		}
	
		function add(gameWebId) {
			window.location.href = "addVersionManagerRes?gameWebId=" + gameWebId;
		}
	</script>
	<body>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="versionManagerResListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<s:text name="gameWeb.name"></s:text>
				</td>
				<td >
					<s:text name="versionManagerApk.version"></s:text>
				</td>
				<td >
					<s:text name="versionManagerApk.beUpdateVersion"></s:text>
				</td>
				<td >
					<s:text name="versionManagerApk.partnerId"></s:text>
				</td>
				<td>
					<s:text name="versionManagerApk.url"></s:text>
				</td>
				<td>
					<s:text name="versionManagerApk.isTest"></s:text>
				</td>
				<td>
					<s:text name="versionManagerApk.description"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<%
				Map<GameWeb, List<VersionManagerRes>> resMap = (Map<GameWeb, List<VersionManagerRes>>) request.getAttribute("resMap");
				Map<String, Partner> partnerMap = (Map<String, Partner>) request.getAttribute("partnerMap");
				
				// 排序
				ArrayList<Map.Entry<GameWeb, List<VersionManagerRes>>> mappingList = new ArrayList<Map.Entry<GameWeb, List<VersionManagerRes>>>(resMap.entrySet());
  				// 通过比较器实现比较排序
  				Collections.sort(mappingList, new Comparator<Map.Entry<GameWeb, List<VersionManagerRes>>>(){
   					public int compare(Map.Entry<GameWeb, List<VersionManagerRes>> mapping1,Map.Entry<GameWeb, List<VersionManagerRes>> mapping2){
    					return mapping1.getKey().getServerId().compareTo(mapping2.getKey().getServerId());
   					}
  				}); 
				
  				for(Map.Entry<GameWeb, List<VersionManagerRes>> mapping : mappingList) { 
  					List<VersionManagerRes> resList = mapping.getValue();
  					GameWeb gameWeb = mapping.getKey(); 
  				/*  }
				for (GameWeb gameWeb : resMap.keySet()) {
					List<VersionManagerRes> resList = resMap.get(gameWeb);  */
					int rowspan = resList.size();
					if (resList.size() == 0) {
						rowspan = 1;
					}
			%>
			<tr id="<%=gameWeb.getServerId()%>">
				<td rowspan="<%=rowspan%>" title=<%=gameWeb.getServerName()%>>
					<%=gameWeb.getServerName()%> <a href="#" onclick='add("<%=gameWeb.getServerId()%>")'><s:text name="add"></s:text></a>				
				</td>
				<%
					for (VersionManagerRes res : resList) {
						int isTest = res.getIsTest();// 1是测试
				%>
				<td title="<%=res.getResBigVersion()%>.<%=res.getResSmallVersion()%>">
					<% if (isTest == 1) { %>
						<font color="#ff0000"><%=res.getResBigVersion()%>.<%=res.getResSmallVersion()%></font>
					<% } else { %>
						<%=res.getResBigVersion()%>.<%=res.getResSmallVersion()%>
					<% } %>
				</td>
				<td title="<%=res.getBeUpdateBigVersion()%>.<%=res.getBeUpdateSmallVersion()%>">
					<% if (isTest == 1) { %>
						<font color="#ff0000"><%=res.getBeUpdateBigVersion()%>.<%=res.getBeUpdateSmallVersion()%></font>
					<% } else { %>
						<%=res.getBeUpdateBigVersion()%>.<%=res.getBeUpdateSmallVersion()%>
					<% } %>
				</td>
				<td title="<%=res.getPartnerId()%>">
					<% if (isTest == 1) { %>
						<font color="#ff0000">
						<% if (partnerMap.containsKey(res.getPartnerId())) {%>
							<%=partnerMap.get(res.getPartnerId()).getPName()%>
						<%} else {%>
							<s:text name="versionManagerApk.allPartner"></s:text>
						<%}%>
						</font>
					<% } else { %>
						<% if (partnerMap.containsKey(res.getPartnerId())) {%>
							<%=partnerMap.get(res.getPartnerId()).getPName()%>
						<%} else {%>
							<s:text name="versionManagerApk.allPartner"></s:text>
						<%}%>
					<% } %>
				</td>
				<td title="<%=res.getUrl()%>">
					<% if (isTest == 1) { %>
						<font color="#ff0000"><%=res.getUrl()%></font>
					<%} else {%>
						<%=res.getUrl()%>
					<%}%>
				</td>
				<td title="<%=res.getIsTest()%>">
					<%
					switch (res.getIsTest()) {
						case 0:
						%><s:text name="versionManagerApk.isTest_0"></s:text><% 
						break;
						case 1:
						%><font color="#FF0000"><s:text name="versionManagerApk.isTest_1"></s:text></font><% 
						break;
						default:
						break;
					}
					%>
				</td>				
				<td title="<%=res.getDescription()%>">
					<% if (isTest == 1) { %>
						<font color="#FF0000"><%=res.getDescription()%></font>
					<%} else {%>
						<%=res.getDescription()%>
					<%}%>
				</td>				
				<td>
					<a href="#" onclick='del("<%=res.getId()%>", "<%=gameWeb.getServerId()%>")'><s:text name="delete"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='update("<%=res.getId()%>", "<%=gameWeb.getServerId()%>")'><s:text name="update"></s:text></a>
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
 	