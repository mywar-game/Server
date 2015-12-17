<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script type="text/javascript" src="../../js/json2.js"></script>
		
		<script src="../../js/jquery/ui/jquery.ui.core.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.widget.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.position.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.autocomplete.js"></script>
		
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
		
		<script>
			function changeSelect(gameWebSelect){
				var gameWebSid = gameWebSelect.value;
				var obj = document.getElementById('servergroup');
				obj.options.length = 0;				
				
				if (gameWebSid == 0) {
					obj.add(new Option('--请选择--', 0));
					return;
				}
				
				obj.add(new Option('--请选择--', 0));
				var objArr = document.getElementById('serverArr');
				for (var i = 0; i < objArr.options.length; i++) {
					var sid = objArr[i].value;
					if (document.getElementById(sid).value == gameWebSid) {
						obj.add(new Option(objArr[i].text, objArr[i].value));
					}
				}				
			}
			
			function submitSearch() {
				var lodoId = document.getElementById("lodoId").value;
				var systemMailId = document.getElementById("systemMailId");
				
				if (!lodoId) {
					alert("请输入用户ID");
					return false;
				} else {
					return true;
				}	
			}
		
		</script>
</head>
<body>

	<form name="searchMail" action="searchMail?isCommit=T" method="post" onsubmit="return submitSearch();">
		<table>
			<tr>
					<td><s:text name="log.lodoSearch"></td>
					<td colspan="6">
						</s:text><s:text name="colon"></s:text><input type="text" name="lodoId" id="lodoId" size="10" value="${lodoId}">
					</td>
					<td>后台邮件ID:</td>
					<td colspan="6">
						<input type="text" name="systemMailId" name="systemMailId" size="10" value="${systemMailId}">
					</td>
					<td colspan="50">
						<input type="submit" value="查询" class="button">
					</td>
					<font color="#FF0000">
						<%-- <%
							AdminUser adminUser = (AdminUser)request.getSession().getAttribute("aldadmin");
										  	
							GameWebService gameWebService = ServiceCacheFactory.getServiceCache().getService(GameWebService.class);
							List<GameWeb> gameWebList = gameWebService.findGameWebList();
										  	
							TGameServerService gameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
							List<TGameServer> list = gameServerService.findTGameServerList();
										  	
							String systemNum = null;
							if (adminUser != null) {
								systemNum = adminUser.getExp1();
							}
						%> --%>
					</font>
				<%-- <td background="images/bg2.gif" style="text-align: left; color: #135294;">
					选择服务器:
					<select name="gameweb" id="gameweb" onchange="changeSelect(this)" class="select">
							<option value="0">--请选择--</s:text></option>
							<%
								int gameWebId = 0;
								for (TGameServer gameServer : list) {
									if (systemNum != null && systemNum.equals(gameServer.getServerId() + "")) {
										gameWebId = gameServer.getGameWebServerId();
										break;
									}
								}
											
								for (int i = 0; i < gameWebList.size(); i++) {
									GameWeb gameweb = gameWebList.get(i);
							%>
									<option value="<%=gameweb.getServerId()%>"
							<%
									if (gameWebId == gameweb.getServerId()) {
							%>
										selected=selected
							<%	
									} 
							%>
									>
									<%=gameweb.getServerName()%>
									</option>
							<%
							}
							%>
						</select>
									
						<select name="servergroup" id="servergroup" onchange="ChangeServer(this.value)" class="select">
							<%
							for (int i = 0; i < list.size(); i++) {
							TGameServer gameServer = list.get(i);
							if (gameWebId == gameServer.getGameWebServerId()) {
							%>
							<option value="<%=gameServer.getServerId()%>" 
							<%
														
							if (systemNum != null && systemNum.equals(gameServer.getServerId() + "")) {										
							%>	
										selected=selected
							<%}%>									
							><%=gameServer.getServerAlias()%>
							</option>
							<%}%>
							<%}%>						
										
							<option value=""><--请选择服务器--></s:text></option>				
						</select>
									
						<select name="serverArr" id="serverArr" style="display:none;" class="select">
							<%
								for (int i = 0; i < list.size(); i++) {
									TGameServer gameServer = list.get(i);
							%>
									<option value="<%=gameServer.getServerId()%>" >
									<%=gameServer.getServerAlias()%>
									</option>
							<%
							}
							%>
							</select>
									
							<%
								for (TGameServer gameServer : list) {
							%>
							<input type="hidden" id="<%=gameServer.getServerId()%>" name="<%=gameServer.getServerId()%>" value="<%=gameServer.getGameWebServerId()%>"/>
						<%}%>
					</td> --%>
			</tr>
		</table>
	</form>
	<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
		<tr>
			<td class="td_title" colspan="7" align="center">
				<s:text name="searchMailJsp.title"></s:text>
			</td>
		</tr>
		<tr>
			<td><s:text name="searchMailJsp.ID"></s:text></td>
			<td><s:text name="searchMailJsp.mailTitle"></s:text></td>
			<td><s:text name="searchMailJsp.tool"></s:text></td>
			<td><s:text name="searchMailJsp.systemMailId"></s:text></td>
			<td><s:text name="searchMailJsp.createTime"></s:text></td>
			<td><s:text name="searchMailJsp.receiveStats"></s:text></td>
			<td><s:text name="searchMailJsp.status"></s:text></td>
		</tr>
		<s:iterator var="data" value="searchMailClassList">
			<tr>
				<td>${data.ID}</td>
				<td>${data.title}</td>
				<td>${data.tool}</td>
				<td>${data.systemMailId}</td>
				<td>${data.createTime}</td>
				<td>
					${data.receiveStatusName}
				</td>
				<td>${data.statusName}</td>
			</tr>
		</s:iterator>
	</table>
	<br/>
	<s:text name="searchMailJsp.note"></s:text><br>
	<s:generator separator="," val="%{getText('searchMailJsp.note_value')}">
			<s:iterator var="str">
				<s:text name="%{'searchMailJsp.note'+#str}"></s:text><br>
			</s:iterator>
		</s:generator>
</body>
</html>
