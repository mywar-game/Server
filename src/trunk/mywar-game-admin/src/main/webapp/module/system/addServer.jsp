<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.adminTool.bo.Partner"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="addServerJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">	
	<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script src="../../js/jquery/jquery.min.js"></script>
	
	<script type="text/javascript" >
		
		function load() {
			var errorCode = document.getElementById("erroCodeNum");
			if (errorCode != 0) {				
				alert(document.getElementById("erroDescrip"));
			}
		}	
		
		function selectServer(serverSelect) {
			var serverName = serverSelect.value;			
			document.getElementById('aliaServerId').value = serverName;
		}
	</script>
	
	<body>
		<%
			Integer erroCodeNum = (Integer) request.getAttribute("erroCodeNum");
			String erroDescrip = (String) request.getAttribute("String erroDescrip");
			
			List<Partner> partnerList = (ArrayList<Partner>) request.getAttribute("partnerList");
			Integer size = partnerList.size();
			Integer cSpan = 4;
			Integer rSpan = 1;
			
			if (size <= 4) {
				cSpan = size;
			} else {
				rSpan = size / 4;
				if (size % 4 != 0) {
					rSpan = rSpan + 1;
				}				
			}
		%>
	
		&nbsp;
		<input type="hidden" id="erroCodeNum" name="erroCodeNum" value="<%=erroCodeNum%>" />
		<input type="hidden" id="erroDescrip" name="erroDescrip" value="<%=erroDescrip%>" />
		<form onload="load()" action="addServer?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="addServerJsp.title"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="gameWebId" value=${gameWebId}>
						<s:text name="gameWebServer.serverId"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="<%=cSpan%>">
						<select name="serverId" id="serverId" onchange="selectServer(this)" class="select">
							<option value="" ><s:text name="pleaseSelect"></s:text></option>
							<s:iterator var="sid" value="serverIdList">
								<option value="${sid}">
									${sid}
								</option>
							</s:iterator> 
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="server.serverName"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="<%=cSpan%>">
						<input type="text" name="serverName" value="" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="server.status"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="<%=cSpan%>">
						<select name="status" class="select">
							<s:generator separator="," val="%{getText('server.status_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="status == #str">selected=selected</s:if>>
										<s:text name="%{'server.status_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
				</tr>
				<tr>
					<td rowspan="<%=rSpan%>">
						<s:text name="server.partenerId"></s:text><s:text name="colon"></s:text>
					</td>
					<%
						int colspan = size % 4;						
						if (colspan == 0) {
							colspan = 1;
						} else {
							colspan = (4 - colspan) + 1;
						}
						
						for (int i = 0; i < partnerList.size(); i++) {
							Partner partner = partnerList.get(i);
							if (i == (size - 1)) {
					%>
							<td colspan="<%=colspan%>">
							<%} else {%> 
							<td>
							<%}%>
								<input type="checkbox" name="pid" value="<%=partner.getPid()%>" />
								<%=partner.getPName()%>
							</td>
							<%
								if (i > 0 && (i + 1) % 4 == 0 && (i+1) != size) {
							%>
							</tr>
							<tr>
							<%}%>
					<%}%>
				</tr>
				<tr>
					<td>
						<s:text name="server.openTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="<%=cSpan%>">
						<input type="text" id="openTime" name="openTime" class="Wdate" style="width:160px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="server.aliaServerId"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="<%=cSpan%>">
						<input type="text" id="aliaServerId" name="aliaServerId" value="" />
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
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
				serverId:{
					required: true,
					maxlength: 10
				},
				serverName:{
					required: true,
					maxlength: 64
				},
				status:{
					required: true
				},
				pid:{
					required: true
				},
				openTime:{
					required: true
				},
				aliaServerId:{
					required: true,
					maxlength: 10
				}
			}		
		});	
	});
</script>