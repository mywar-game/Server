<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.adminTool.bo.SystemGiftbag"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="systemGiftbaglistJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<link href="../../css/loading.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script language="javascript" src="../../js/mask.js"></script>
	<script type="text/javascript">
	
		function del(giftbagId, gameWebId) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param>id</s:param><s:param>"+giftbagId+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delSystemGiftbag?giftbagId=" + giftbagId + "&gameWebId=" + gameWebId;
				ajaxobj.callback = function() {
					window.location.href = "systemGiftbagList";
					//var tr = document.getElementById(id);
					//var table = document.getElementById("table");
					//table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(giftbagId, gameWebId) {
			window.location.href = "updateSystemGiftbag?giftbagId=" + giftbagId + "&gameWebId=" + gameWebId;
		}
	
		function add(gameWebId) {
			window.location.href = "addSystemGiftbag?gameWebId=" + gameWebId;
		}
		
		// 礼包掉落配置
		function config(giftbagId, gameWebId) {
			window.location.href = "giftbagDropToolList?giftbagId=" + giftbagId + "&gameWebId=" + gameWebId;
		}
		
		// 礼包领取次数配置
		function limit(giftBagType, gameWebId) {
			window.location.href = "giftbagTypeLimitConfig?giftBagType=" + giftBagType + "&gameWebId=" + gameWebId;
		}
		
		// 生成礼包码
		function createCode(gameWebId, giftbagId) {
			window.location.href = "createGiftCode?gameWebId=" + gameWebId + "&giftbagId=" + giftbagId;
		}
		
		// 导出礼包码
		function exportCode(giftBagId, gameWebId, lim) {
			var ajaxobj = new Ajax();
			ajaxobj.url = "exportGiftCode?giftBagId=" + giftBagId + "&gameWebId=" + gameWebId;
			
			ajaxobj.callback = function() {
				removeCoverDiv(); // 去除遮挡层
				var responseMsg = eval('(' + ajaxobj.gettext() + ')');
				if (responseMsg.erroCodeNum == 0) {
					var tempLim = Math.ceil(lim / 500000);
					var fileName = giftBagId + "_" + 1 + ".xlsx";
						
					window.location.href = "../system/action/downloadingZip?fileName=" + fileName;
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="giftbag.exportError"></s:text></s:param></s:text>');
				}				
			}
			popCoverDiv(); // 添加遮挡层
			ajaxobj.send();
		}
		
	</script>
	<body>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="systemGiftbaglistJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<s:text name="gameWeb.name"></s:text>
				</td>
				<td >
					<s:text name="giftbag.type"></s:text>
				</td>
				<td >
					<s:text name="giftbag.name"></s:text>
				</td>
				<td>
					<s:text name="giftbag.description"></s:text>
				</td>
				<td>
					<s:text name="giftbag.codeNum"></s:text>
				</td>
				<!--
				<td>
					<s:text name="giftbag.imgId"></s:text>
				</td>
				-->
				<td width="50">
					<s:text name="giftbag.config"></s:text>
				</td>
				<td width="50">
					<s:text name="giftbag.limit"></s:text>
				</td>
				<td width="60">
					<s:text name="giftCode.createGiftCode"></s:text>
				</td>
				<td width="60">
					<s:text name="giftbag.exportCode"></s:text>
				</td>
				<td width="30">
					<s:text name="delete"></s:text>
				</td>
				<td width="30">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<%
				Map<GameWeb, List<SystemGiftbag>> map = (Map<GameWeb, List<SystemGiftbag>>) request.getAttribute("giftbagMap");
				// 排序
				ArrayList<Map.Entry<GameWeb, List<SystemGiftbag>>> mappingList = new ArrayList<Map.Entry<GameWeb, List<SystemGiftbag>>>(map.entrySet());
				// 通过比较器实现比较排序
				Collections.sort(mappingList, new Comparator<Map.Entry<GameWeb, List<SystemGiftbag>>>(){
					public int compare(Map.Entry<GameWeb, List<SystemGiftbag>> mapping1, Map.Entry<GameWeb, List<SystemGiftbag>> mapping2){
						return mapping1.getKey().getServerId().compareTo(mapping2.getKey().getServerId());
					}
				}); 
			
				for(Map.Entry<GameWeb, List<SystemGiftbag>> mapping : mappingList) { 
					List<SystemGiftbag> giftList = mapping.getValue();
					GameWeb gameWeb = mapping.getKey(); 
			
					if (gameWeb.getServerId() != 1) {
							continue; // 只显示越狱苹果区
					}
				/* for (GameWeb gameWeb : map.keySet()) {
					List<SystemGiftbag> giftList = map.get(gameWeb); */
					int rowspan = giftList.size();
					if (giftList.size() == 0) {
						rowspan = 1;
					}	
			%>
			<tr id="<%=gameWeb.getServerId()%>">
				<td rowspan="<%=rowspan%>" title="<%=gameWeb.getServerName()%>">
					全渠道&nbsp; <a href="#" onclick='add("<%=gameWeb.getServerId()%>")'><s:text name="add"></s:text></a>				
				</td>
				<%
					for (SystemGiftbag gift : giftList) {				
				%>
				<td title="<%=gift.getType()%>">
					<%=gift.getType()%>
				</td>
				<td title="<%=gift.getName()%>">
					<%=gift.getName()%>
				</td>
				<td title="<%=gift.getDescription()%>">
					<%=gift.getDescription()%>
				</td>
				<td title="<%=gift.getCodeNum()%>">
					<%=gift.getCodeNum()%>
				</td>
				<!--
				<td title="<%=gift.getImgId()%>">
					<%=gift.getImgId()%>
				</td>
				-->
				<td>
					<a href="#" onclick='config("<%=gift.getGiftbagId()%>", "<%=gameWeb.getServerId()%>")'><s:text name="giftbag.config"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='limit("<%=gift.getType()%>", "<%=gameWeb.getServerId()%>")'><s:text name="giftbag.limit"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='createCode("<%=gameWeb.getServerId()%>", "<%=gift.getGiftbagId()%>")' /><s:text name="giftCode.createGiftCode"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='exportCode("<%=gift.getGiftbagId()%>", "<%=gameWeb.getServerId()%>", "<%=gift.getCodeNum()%>")' /><s:text name="giftbag.exportCode"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='del("<%=gift.getGiftbagId()%>", "<%=gameWeb.getServerId()%>")'><s:text name="delete"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='update("<%=gift.getGiftbagId()%>", "<%=gameWeb.getServerId()%>")'><s:text name="update"></s:text></a>
				</td>				
				</tr>
				<tr>
				<%}%> 
			</tr>
			<%}%>
			<tr>
				<td colspan="22">
					<aldtags:pageTag paraStr="isCommit,T,pageSize,${pageSize}" />
				</td>
			</tr>
		</table>
	</body>
</html>
 	