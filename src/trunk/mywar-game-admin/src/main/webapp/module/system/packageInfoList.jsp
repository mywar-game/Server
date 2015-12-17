<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.adminTool.bo.Partner"%>
<%@page import="com.system.bo.PackageInfo"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="packageInfoListJsp.title"></s:text></title>

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
				ajaxobj.url = "delPackageInfo?id=" + id + "&gameWebId=" + gameWebId;
				ajaxobj.callback = function() {
					window.location.href = "packageInfoList";
					//var tr = document.getElementById(id);
					//var table = document.getElementById("table");
					//table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(version, gameWebId) {
			window.location.href = "updatePackageInfo?version=" + version + "&gameWebId=" + gameWebId;
		}
	
		function add(gameWebId) {
			window.location.href = "addPackageInfo?gameWebId=" + gameWebId;
		}
	</script>
	<body>
		<!--
		<input type="submit" value="<s:text name='addBlackImeiJsp.title'></s:text>" class="button" onclick=add(); />
		<input type="button" value='<s:text name=""></s:text>' class="button" onclick="" />
		-->
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="packageInfoListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<s:text name="gameWeb.name"></s:text>
				</td>
				<td >
					<s:text name="packageInfo.version"></s:text>
				</td>
				<td >
					<s:text name="packageInfo.versions"></s:text>
				</td>
				<td >
					<s:text name="packageInfo.fullUrl"></s:text>
				</td>
				<td>
					<s:text name="packageInfo.upgradeUrl"></s:text>
				</td>
				<td>
					<s:text name="packageInfo.createdTime"></s:text>
				</td>
				<td>
					<s:text name="packageInfo.pkgType"></s:text>
				</td>
				<td>
					<s:text name="packageInfo.description"></s:text>
				</td>
				<td>
					<s:text name="packageInfo.isTest"></s:text>
				</td>
				<td>
					<s:text name="packageInfo.partnerId"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<%
				Map<GameWeb, List<PackageInfo>> infoMap = (Map<GameWeb, List<PackageInfo>>) request.getAttribute("infoMap");
				Map<String, Partner> partnerMap = (Map<String, Partner>) request.getAttribute("partnerMap");
				for (GameWeb gameWeb : infoMap.keySet()) {
					List<PackageInfo> infoList = infoMap.get(gameWeb);
					int rowspan = infoList.size();
					if (infoList.size() == 0) {
						rowspan = 1;
					}
			%>
			<tr id="<%=gameWeb.getServerId()%>">
				<td rowspan="<%=rowspan%>" title=<%=gameWeb.getServerName()%>>
					<%=gameWeb.getServerName()%> <a href="#" onclick='add("<%=gameWeb.getServerId()%>")'><s:text name="add"></s:text></a>				
				</td>
				<%
					for (PackageInfo packageInfo : infoList) {
				%>
				<td title="<%=packageInfo.getVersion()%>">
					<%=packageInfo.getVersion()%>
				</td>
				<td title="<%=packageInfo.getVersions()%>">
					<%=packageInfo.getVersions()%>
				</td>
				<td title="<%=packageInfo.getFullUrl()%>">
					<%=packageInfo.getFullUrl()%>
				</td>
				<td title="<%=packageInfo.getUpgradeUrl()%>">
					<%=packageInfo.getUpgradeUrl()%>
				</td>
				<td title="<%=packageInfo.getCreatedTime()%>">
					<%=packageInfo.getCreatedTime()%>
				</td>
				<td title="<%=packageInfo.getPkgType()%>">
					<%
					switch (packageInfo.getPkgType()) {
						case 0:
						%><s:text name="packageInfo.pkgType_0"></s:text><% 
						break;
						case 1:
						%><s:text name="packageInfo.pkgType_1"></s:text><% 
						break;
						default:
						break;
					}
					%>
				</td>
				<td title="<%=packageInfo.getDescription()%>">
					<%=packageInfo.getDescription()%>
				</td>
				<td title="<%=packageInfo.getIsTest()%>">
					<%
					switch (packageInfo.getIsTest()) {
						case 0:
						%><s:text name="packageInfo.isTest_0"></s:text><% 
						break;
						case 1:
						%><s:text name="packageInfo.isTest_1"></s:text><% 
						break;
						default:
						break;
					}
					%>
				</td>
				<td title="<%=packageInfo.getPartnerId()%>">
					<%=partnerMap.get(packageInfo.getPartnerId()).getPName()%>
				</td>
				<td>
					<a href="#" onclick='del("<%=packageInfo.getId()%>", "<%=gameWeb.getServerId()%>")'><s:text name="delete"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='update("<%=packageInfo.getVersion()%>", "<%=gameWeb.getServerId()%>")'><s:text name="update"></s:text></a>
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
 	