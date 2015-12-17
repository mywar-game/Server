<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.system.bo.SpecialNotice"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="specialNoticeListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
	
		function del(serverId, gameWebId, partnerId, partnerName) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param>id</s:param><s:param>"+serverId+"-"+partnerName+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delSpecialNotice?serverId=" + serverId + "&gameWebId=" + gameWebId + "&partnerId=" + partnerId;
				ajaxobj.callback = function() {
					window.location.href = "specialNoticeList";
				}
				ajaxobj.send();
			}
	
		}
	
		function update(serverId, gameWebId, partnerId) {
			window.location.href = "updateSpecialNotice?serverId=" + serverId + "&gameWebId=" + gameWebId+"&partnerId="+partnerId;
		}
	
		function add(gameWebId) {
			window.location.href = "addSpecialNotice?gameWebId=" + gameWebId;
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
						<s:text name="specialNoticeListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<s:text name="gameWeb.name"></s:text>
				</td>
				
				<td >
					<s:text name="specialNotice.id"></s:text>
				</td>
				<td >
					<s:text name="specialNotice.partnerId"></s:text>
				</td>
				<td >
					<s:text name="specialNotice.title"></s:text>
				</td>
				<td >
					<s:text name="specialNotice.content"></s:text>
				</td>
				<td>
					<s:text name="specialNotice.isEnable"></s:text>
				</td>
				<td>
					<s:text name="specialNotice.createdTime"></s:text>
				</td>
				<td>
					<s:text name="specialNotice.updatedTime"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<%
				Map<GameWeb, List<SpecialNotice>> map = (Map<GameWeb, List<SpecialNotice>>) request.getAttribute("specialNoticeMap");
				
				// 排序
				ArrayList<Map.Entry<GameWeb, List<SpecialNotice>>> mappingList = new ArrayList<Map.Entry<GameWeb, List<SpecialNotice>>>(map.entrySet());
				// 通过比较器实现比较排序
				Collections.sort(mappingList, new Comparator<Map.Entry<GameWeb, List<SpecialNotice>>>(){
					public int compare(Map.Entry<GameWeb, List<SpecialNotice>> mapping1,Map.Entry<GameWeb, List<SpecialNotice>> mapping2){
						return mapping1.getKey().getServerId().compareTo(mapping2.getKey().getServerId());
					}
				}); 
			
				for(Map.Entry<GameWeb, List<SpecialNotice>> mapping : mappingList) { 
					List<SpecialNotice> specialNoticeList = mapping.getValue();
					GameWeb gameWeb = mapping.getKey(); 
				
				/* for (GameWeb gameWeb : map.keySet()) {
					List<Notice> noticeList = map.get(gameWeb); */
					int rowspan = specialNoticeList.size();
					if (specialNoticeList.size() == 0) {
						rowspan = 1;
					}	
			%>
			<tr id="<%=gameWeb.getServerId()%>">
				<td rowspan="<%=rowspan%>" title="<%=gameWeb.getServerName()%>">
					<%=gameWeb.getServerName()%> <a href="#" onclick='add("<%=gameWeb.getServerId()%>")'><s:text name="add"></s:text></a>				
				</td>
				<%
					for (SpecialNotice specialNotice : specialNoticeList) {				
				%>
				<td id="<%=specialNotice.getServerId()%>" title="<%=specialNotice.getServerId()%>">
					<%=specialNotice.getServerId()%>
				</td>
				
				<td id="<%=specialNotice.getPartnerName()%>" title="<%=specialNotice.getPartnerName()%>">
					<%=specialNotice.getPartnerName()%>
				</td>
				<td title="<%=specialNotice.getTitle()%>">
					<%=specialNotice.getTitle()%>
				</td>
				<td title="<%=specialNotice.getContent()%>">
					<%=specialNotice.getContent2()%>
				</td>
				<td title="<%=specialNotice.getIsEnable()%>">
					<%
					switch (specialNotice.getIsEnable()) {
						case 0:
						%><s:text name="specialNotice.isEnable_0"></s:text><% 
						break;
						case 1:
						%><s:text name="specialNotice.isEnable_1"></s:text><% 
						break;
						default:
						break;
					}
					%>
				</td>
				<td title="<%=specialNotice.getCreatedTime()%>">
					<%=specialNotice.getCreatedTime()%>
				</td>
				<td title="<%=specialNotice.getUpdatedTime()%>">
					<%if (specialNotice.getUpdatedTime() != null) {%>
						<%=specialNotice.getUpdatedTime()%>
					<%}%>
				</td>
				<td>
					<a href="#" onclick='del("<%=specialNotice.getServerId()%>", "<%=gameWeb.getServerId()%>", "<%=specialNotice.getPartnerId()%>", "<%=specialNotice.getPartnerName()%>")'><s:text name="delete"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='update("<%=specialNotice.getServerId()%>", "<%=gameWeb.getServerId()%>", "<%=specialNotice.getPartnerId()%>")'><s:text name="update"></s:text></a>
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
 	