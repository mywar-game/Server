<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.system.bo.AmendNotice"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="amendNoticeListJsp.title"></s:text></title>

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
				ajaxobj.url = "delAmendNotice?serverId=" + serverId + "&gameWebId=" + gameWebId + "&partnerId=" + partnerId;
				ajaxobj.callback = function() {
					window.location.href = "amendNoticeList";
				}
				ajaxobj.send();
			}
	
		}
	
		function update(serverId, gameWebId, partnerId) {
			window.location.href = "updateAmendNotice?serverId=" + serverId + "&gameWebId=" + gameWebId+"&partnerId="+partnerId;
		}
	
		function add(gameWebId) {
			window.location.href = "addAmendNotice?gameWebId=" + gameWebId;
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
						<s:text name="amendNoticeListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<s:text name="gameWeb.name"></s:text>
				</td>
				
				<td >
					<s:text name="amendNotice.id"></s:text>
				</td>
				<td >
					<s:text name="amendNotice.partnerId"></s:text>
				</td>
				<td >
					<s:text name="amendNotice.title"></s:text>
				</td>
				<td >
					<s:text name="amendNotice.content"></s:text>
				</td>
				<td>
					<s:text name="amendNotice.isEnable"></s:text>
				</td>
				<td>
					<s:text name="amendNotice.createdTime"></s:text>
				</td>
				<td>
					<s:text name="amendNotice.updatedTime"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<%
				Map<GameWeb, List<AmendNotice>> map = (Map<GameWeb, List<AmendNotice>>) request.getAttribute("amendNoticeMap");
				
				// 排序
				ArrayList<Map.Entry<GameWeb, List<AmendNotice>>> mappingList = new ArrayList<Map.Entry<GameWeb, List<AmendNotice>>>(map.entrySet());
				// 通过比较器实现比较排序
				Collections.sort(mappingList, new Comparator<Map.Entry<GameWeb, List<AmendNotice>>>(){
					public int compare(Map.Entry<GameWeb, List<AmendNotice>> mapping1,Map.Entry<GameWeb, List<AmendNotice>> mapping2){
						return mapping1.getKey().getServerId().compareTo(mapping2.getKey().getServerId());
					}
				}); 
			
				for(Map.Entry<GameWeb, List<AmendNotice>> mapping : mappingList) { 
					List<AmendNotice> amendNoticeList = mapping.getValue();
					GameWeb gameWeb = mapping.getKey(); 
				
				/* for (GameWeb gameWeb : map.keySet()) {
					List<Notice> noticeList = map.get(gameWeb); */
					int rowspan = amendNoticeList.size();
					if (amendNoticeList.size() == 0) {
						rowspan = 1;
					}	
			%>
			<tr id="<%=gameWeb.getServerId()%>">
				<td rowspan="<%=rowspan%>" title="<%=gameWeb.getServerName()%>">
					<%=gameWeb.getServerName()%> <a href="#" onclick='add("<%=gameWeb.getServerId()%>")'><s:text name="add"></s:text></a>				
				</td>
				<%
					for (AmendNotice amendNotice : amendNoticeList) {				
				%>
				<td id="<%=amendNotice.getServerId()%>" title="<%=amendNotice.getServerId()%>">
					<%=amendNotice.getServerId()%>
				</td>
				
				<td id="<%=amendNotice.getPartnerName()%>" title="<%=amendNotice.getPartnerName()%>">
					<%=amendNotice.getPartnerName()%>
				</td>
				<td title="<%=amendNotice.getTitle()%>">
					<%=amendNotice.getTitle()%>
				</td>
				<td title="<%=amendNotice.getContent()%>">
					<%=amendNotice.getContent2()%>
				</td>
				<td title="<%=amendNotice.getIsEnable()%>">
					<%
					switch (amendNotice.getIsEnable()) {
						case 0:
						%><s:text name="amendNotice.isEnable_0"></s:text><% 
						break;
						case 1:
						%><s:text name="amendNotice.isEnable_1"></s:text><% 
						break;
						default:
						break;
					}
					%>
				</td>
				<td title="<%=amendNotice.getCreatedTime()%>">
					<%=amendNotice.getCreatedTime()%>
				</td>
				<td title="<%=amendNotice.getUpdatedTime()%>">
					<%if (amendNotice.getUpdatedTime() != null) {%>
						<%=amendNotice.getUpdatedTime()%>
					<%}%>
				</td>
				<td>
					<a href="#" onclick='del("<%=amendNotice.getServerId()%>", "<%=gameWeb.getServerId()%>", "<%=amendNotice.getPartnerId()%>", "<%=amendNotice.getPartnerName()%>")'><s:text name="delete"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='update("<%=amendNotice.getServerId()%>", "<%=gameWeb.getServerId()%>", "<%=amendNotice.getPartnerId()%>")'><s:text name="update"></s:text></a>
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
 	