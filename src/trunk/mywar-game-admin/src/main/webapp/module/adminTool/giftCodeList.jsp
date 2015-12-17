<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.adminTool.bo.GiftCode"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="giftCodeListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
	
		function del(code, gameWebId) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='giftCode.code'></s:text></s:param><s:param>"+code+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delGiftCode?code=" + code + "&gameWebId=" + gameWebId;
				ajaxobj.callback = function() {
					window.location.href = "giftCodeList";
					//var tr = document.getElementById(id);
					//var table = document.getElementById("table");
					//table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function create(gameWebId) {
			window.location.href = "createGiftCode?gameWebId=" + gameWebId;
		}
	</script>
	<body>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="giftCodeListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<s:text name="gameWeb.name"></s:text>
				</td>
				<td >
					<s:text name="giftCode.code"></s:text>
				</td>
				<td >
					<s:text name="giftCode.name"></s:text>
				</td>
				<!--
				<td >
					<s:text name="giftCode.giftBagType"></s:text>
				</td>
				<td >
					<s:text name="giftCode.subType"></s:text>
				</td>
				-->
				<td >
					<s:text name="giftCode.effectiveTime"></s:text>
				</td>
				<td >
					<s:text name="giftCode.loseTime"></s:text>
				</td>
				<td>
					<s:text name="giftCode.createdTime"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
			</tr>
			<%
				Map<GameWeb, List<GiftCode>> map = (Map<GameWeb, List<GiftCode>>) request.getAttribute("giftcodeMap");
				Map<GameWeb, Map<Integer, String>> typeMap = (Map<GameWeb, Map<Integer, String>>) request.getAttribute("typeAndNameMap");
				for (GameWeb gameWeb : map.keySet()) {
					List<GiftCode> giftList = map.get(gameWeb);
					Map<Integer, String> typeAndNameMap = typeMap.get(gameWeb);
					int rowspan = giftList.size();
					if (giftList.size() == 0) {
						rowspan = 1;
					}	
			%>
			<tr id="<%=gameWeb.getServerId()%>">
				<td rowspan="<%=rowspan%>" title="<%=gameWeb.getServerName()%>">
					<%=gameWeb.getServerName()%> <a href="#" onclick='create("<%=gameWeb.getServerId()%>")'><s:text name="giftCode.createGiftCode"></s:text></a>				
				</td>
				<%
					for (GiftCode gift : giftList) {				
				%>
				<td title="<%=gift.getCode()%>">
					<%=gift.getCode()%>
				</td>
				<td>
					<% if (typeAndNameMap.containsKey(gift.getGiftBagType())) {%>
						<%=typeAndNameMap.get(gift.getGiftBagType())%>
					<%}%>
				</td>
				<!--
				<td title="<%=gift.getGiftBagType()%>">
					<%=gift.getGiftBagType()%>
				</td>
				<td title="<%=gift.getSubType()%>">
					<%=gift.getSubType()%>
				</td>
				-->
				<td title="<%=gift.getEffectiveTime()%>">
					<%=gift.getEffectiveTime()%>
				</td>
				<td title="<%=gift.getLoseTime()%>">
					<%=gift.getLoseTime()%>
				</td>
				<td title="<%=gift.getCreatedTime()%>">
					<%=gift.getCreatedTime()%>
				</td>
				<td>
					<a href="#" onclick='del("<%=gift.getCode()%>", "<%=gameWeb.getServerId()%>")'><s:text name="delete"></s:text></a>
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