<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.adminTool.bo.Partner"%>
<%@page import="com.system.bo.VersionManagerApk"%>
<%@page import="com.adminTool.bo.PartnerQn"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="versionManagerApkListJsp.title"></s:text></title>

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
				ajaxobj.url = "delVersionManagerApk?id=" + id + "&gameWebId=" + gameWebId;
				ajaxobj.callback = function() {
					window.location.href = "versionManagerApkList";
				}
				ajaxobj.send();
			}
	
		}
	
		function update(id, gameWebId) {
			window.location.href = "updateVersionManagerApk?id=" + id + "&gameWebId=" + gameWebId;
		}
	
		function add(gameWebId) {
			window.location.href = "addVersionManagerApk?gameWebId=" + gameWebId;
		}
	</script>
	<body>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="versionManagerApkListJsp.title"></s:text>
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
					<s:text name="versionManagerApk.partnerId"></s:text>
				</td>
				<td>
					<s:text name="versionManagerApk.url"></s:text>
				</td>
				<td>
					<s:text name="versionManagerApk.isTest"></s:text>
				</td>
				<td>
					qn
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
				Map<GameWeb, List<VersionManagerApk>> apkMap = (Map<GameWeb, List<VersionManagerApk>>) request.getAttribute("apkMap");
				Map<String, Partner> partnerMap = (Map<String, Partner>) request.getAttribute("partnerMap");
				Map<String, PartnerQn> partnerQnMap = (Map<String, PartnerQn>) request.getAttribute("partnerQnMap");
				
				// 排序
				ArrayList<Map.Entry<GameWeb, List<VersionManagerApk>>> mappingList = new ArrayList<Map.Entry<GameWeb, List<VersionManagerApk>>>(apkMap.entrySet());
  				// 通过比较器实现比较排序
  				Collections.sort(mappingList, new Comparator<Map.Entry<GameWeb, List<VersionManagerApk>>>(){
   					public int compare(Map.Entry<GameWeb, List<VersionManagerApk>> mapping1,Map.Entry<GameWeb, List<VersionManagerApk>> mapping2){
    					return mapping1.getKey().getServerId().compareTo(mapping2.getKey().getServerId());
   					}
  				}); 
				
  				for(Map.Entry<GameWeb, List<VersionManagerApk>> mapping : mappingList) { 
  					List<VersionManagerApk> apkList = mapping.getValue();
  					GameWeb gameWeb = mapping.getKey(); 
				/* 
				for (GameWeb gameWeb : apkMap.keySet()) { 
					List<VersionManagerApk> apkList = apkMap.get(gameWeb);*/
					int rowspan = apkList.size();
					if (apkList.size() == 0) {
						rowspan = 1;
					}
			%>
			<tr id="<%=gameWeb.getServerId()%>">
				<td rowspan="<%=rowspan%>" title=<%=gameWeb.getServerName()%>>
					<%=gameWeb.getServerName()%> <a href="#" onclick='add("<%=gameWeb.getServerId()%>")'><s:text name="add"></s:text></a>				
				</td>
				<%
					for (VersionManagerApk apk : apkList) {
				%>
				<td title="<%=apk.getApkBigVersion()%>.<%=apk.getApkSmallVersion()%>">
					<%=apk.getApkBigVersion()%>.<%=apk.getApkSmallVersion()%>
				</td>
				<td title="<%=apk.getPartnerId()%>">
					<% if (partnerMap.containsKey(apk.getPartnerId())) {%>
						<%=partnerMap.get(apk.getPartnerId()).getPName()%>
					<%}%>					
				</td>
				<td title="<%=apk.getUrl()%>">
					<%=apk.getUrl()%>
				</td>
				<td title="<%=apk.getIsTest()%>">
					<%
					switch (apk.getIsTest()) {
						case 0:
						%><s:text name="versionManagerApk.isTest_0"></s:text><% 
						break;
						case 1:
						%><s:text name="versionManagerApk.isTest_1"></s:text><% 
						break;
						default:
						break;
					}
					%>
				</td>	
				<td title="<%=apk.getQn()%>">
					<% if (partnerQnMap.containsKey(apk.getQn())&&(apk.getPartnerId().equals("1001")||!apk.getQn().equals("0"))) { %>						
						<%=partnerQnMap.get(apk.getQn()).getName()%>
					<%}%>
				</td>			
				<td title="<%=apk.getDescription()%>">
					<%=apk.getDescription()%>
				</td>				
				<td>
					<a href="#" onclick='del("<%=apk.getId()%>", "<%=gameWeb.getServerId()%>")'><s:text name="delete"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='update("<%=apk.getId()%>", "<%=gameWeb.getServerId()%>")'><s:text name="update"></s:text></a>
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
 	