<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.TGameServer"%>
<%@page import="com.adminTool.bo.GiftbagTypeLimit"%>
<%@page import="com.system.bo.GameWebServer"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.system.bo.TGameServer"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="giftbagTypeLimit.limit"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	<script src="../../js/jquery/jquery.min.js"></script>
	<script type="text/javascript">
		
		function checkAll(check) {
			var isCheck = check.checked;
			var sidArr = document.getElementsByName('sid');
			for (var i = 0; i < sidArr.length; i++) {
				sidArr[i].checked = isCheck;
			}
			
			if (isCheck) {
				check.value = 1;
			} else {
				check.value = 2;
			}
		}
		
		function checkOne(check) {
			var isCheck = check.checked;
			if (!isCheck) {
				var allServer = document.getElementById('allServer');
				allServer.checked = isCheck;
				allServer.value = 2;
				return;
			}
			
			var sidArr = document.getElementsByName('sid');
			for (var i = 0; i < sidArr.length; i++) {
				if (!sidArr[i].checked) {
					return;
				}
			}
			
			document.getElementById('allServer').checked = isCheck;
			document.getElementById('allServer').value = 1;
		}
		
		function checkAll1(obj) {
			var index = obj.value;
			var isChecked = obj.checked;
			var gameWeb = document.getElementById("gameMap_" + index).value;
			var servers = gameWeb.split("_");
			for (var i = 0; i < servers.length - 1; i++) {
				var server = servers[i];
				document.getElementById(server).checked = isChecked;
				
			}
		}
		
	</script>
	
	<body>
		&nbsp;
		<form action="giftbagTypeLimitConfig?isCommit=T" method="post" >
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="giftbag.limit"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td width="150">
						<s:text name="giftbagTypeLimit.giftbagName"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						${giftBagName}
					</td>
				</tr>
				<tr>
					<td width="150">
						<s:text name="giftbagTypeLimit.giftbagType"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="hidden" name="gameWebId" value="${gameWebId}" />
						<input type="hidden" name="giftBagType" value="${giftBagType}" />
						${giftBagType}
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="giftbagTypeLimit.limit"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<input type="text"  name="limitTimes" value="${giftbagTypeLimit.limitTimes}" />
					</td>	
				</tr>
				
					<%
						Integer allServer = (Integer) request.getAttribute("allServer");
						Map<String, String> serverMap = (HashMap<String, String>) request.getAttribute("serverMap");
						Map<GameWeb, List<GameWebServer>> gameWebMap = (Map<GameWeb, List<GameWebServer>>) request.getAttribute("gameWebMap");
						// 排序
						ArrayList<Map.Entry<GameWeb, List<GameWebServer>>> mappingList = new ArrayList<Map.Entry<GameWeb, List<GameWebServer>>>(gameWebMap.entrySet());
						// 通过比较器实现比较排序
						Collections.sort(mappingList, new Comparator<Map.Entry<GameWeb, List<GameWebServer>>>(){
							public int compare(Map.Entry<GameWeb, List<GameWebServer>> mapping1,Map.Entry<GameWeb, List<GameWebServer>> mapping2){
								return mapping1.getKey().getServerId().compareTo(mapping2.getKey().getServerId());
							}
						}); 
					%>
			
					<%
						for (Map.Entry<GameWeb, List<GameWebServer>> mapping : mappingList) { 
  							List<GameWebServer> list = mapping.getValue();
  							GameWeb gameWeb = mapping.getKey();
  							
  					%>
  						<tr>
							<td size="2">
								选择服务器
							</td>
							<td>
								<input type="checkbox" name="gameWeb" value="<%=gameWeb.getServerId()%>" onclick="checkAll1(this);"/>
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
									for (GameWebServer data : list) {
								%>
								<input type="checkbox" name="sid" id="<%=data.getServerName()%><%=data.getServerId()%>" value="<%=data.getServerId()%>" 
									<% if (allServer == 1 || serverMap.containsKey(data.getServerId())) { %>
										checked=true
									<% } %> />
								<%=data.getServerId()%>(<%=data.getServerName()%>)
								<%
									str += data.getServerName();
									str += data.getServerId();
								%>
							
								<%
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
					<!-- 修改规则 -->	
					<%-- <td>
						<s:text name="giftbagTypeLimit.serverList"></s:text><s:text name="colon"></s:text>
						<input type="checkbox" value="${allServer}" name="allServer"
						<% 
							Integer allServer = (Integer) request.getAttribute("allServer");
							if (allServer == 1) {
						%> checked=true
						<%}%>
						 onclick="checkAll(this)" id="allServer"/><s:text name="giftbagTypeLimit.allServer"></s:text>
					</td>	
										
					<%
						//List<TGameServer> serverList = (ArrayList<TGameServer>) request.getAttribute("serverList");						
						List<GameWebServer> gameWebServerList = (ArrayList<GameWebServer>) request.getAttribute("gameWebServerList");
						Map<String, String> serverMap = (HashMap<String, String>) request.getAttribute("serverMap");
						//Integer size = serverList.size();
						Integer size = gameWebServerList.size();
						int colspan = size % 4;
						
						if (colspan == 0) {
							colspan = 1;
						} else {
							colspan = (4 - colspan) + 1;
						}
						
						/* for (int i = 0; i < serverList.size(); i++) { */
						for (int i = 0; i < gameWebServerList.size(); i++) {
							//TGameServer server = serverList.get(i);
							GameWebServer server = gameWebServerList.get(i);
							if (i == (size - 1)) {
					%>
						<td colspan="<%=colspan%>">
							<%} else {%>
						<td >
						<%}%>
							<input type="checkbox" name="sid" onclick="checkOne(this)" value="<%=server.getServerId()%>"
							<% if (allServer == 1 || serverMap.containsKey(server.getServerId().toString())) { %>
								checked=true
							<% } %> />
							<%=server.getServerId()%>-<%=server.getServerAlias()%>
							<%=server.getServerId()%>(<%=server.getServerName()%>)
						</td>	
						<% if (i > 0 && (i + 1) % 4 == 0 && (i+1) != size) {%>
						</tr>
						<tr>
								<td></td>
						<%}%>
					<%}%> --%>	
				
				<tr>
					<td>
						<s:text name="giftbagTypeLimit.minLevel"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<input type="text"  name="minLevel" value="${giftbagTypeLimit.minLevel}" />
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="giftbagTypeLimit.minVipLevel"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<input type="text"  name="minVipLevel" value="${giftbagTypeLimit.minVipLevel}" />
					</td>	
				</tr>
				
				<tr>
					<td colspan="5" align="center">
						<input type="submit"  value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
<script src="../../js/jquery/jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("form").validate({
			rules:{
				limitTimes:{
					required:true,
					digits:true
				}
			}		
		});	
	});
</script>