<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.adminTool.bo.Partner"%>
<%@page import="com.system.bo.GameWebServer"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="addCMDJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script src="../../js/jquery/jquery.min.js"></script>
	
	<body>
		&nbsp;
		<form name="f" action="addCMD?isCommit=T" method="post" onsubmit="return check()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="addCMDJsp.title"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="cmd.serverId"></s:text><s:text name="colon"></s:text>
					</td>
					<%
						List<GameWebServer> serverList = (ArrayList<GameWebServer>) request.getAttribute("serverList");						
						Integer size = serverList.size();
						int colspan = size % 4;
						
						if (colspan == 0) {
							colspan = 1;
						} else {
							colspan = (4 - colspan) + 1;
						}
						
						for (int i = 0; i < serverList.size(); i++) {
							GameWebServer server = serverList.get(i);
							int index = 0;
							if (i == (size - 1)) {
					%>
						<td colspan="<%=colspan%>">
							<%} else {%>
						<td >
						
						<%}%>
							<input type="checkbox" name="sid" value="<%=server.getServerId()%>" />
							<%=server.getServerId()%>
						</td>	
						<% if (i > 0 && (i + 1) % 4 == 0 && (i+1) != size) {%>
							
						</tr>
						<tr>
								<td></td>
						<%}%>
						
					<%}%>
				</tr>				
				<tr>
					<td>
						<s:text name="cmd.cmdType"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<select id="cmdType" name="cmdType" class="select">
							<s:generator separator="," val="%{getText('cmd.cmdType_value')}">
								<s:iterator var="str">
									<option value="${str}">
										<s:text name="%{'cmd.cmdType_' + #str}"></s:text> 
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="cmd.version"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input id="version" type="text" name="version" value="" /><font color='red'>更新游戏服和更新数据库时必填</font>
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
<script type="text/javascript">
	$(document).ready(function(){
		$("form").validate({
			rules:{
				serverId:{
					required:true
				}
			}		
		});	
	});
	
	function check(){
		var cmdType = document.getElementById("cmdType").value;
		var version = document.getElementById("version").value;
		if(cmdType==0){
			alert("请选择一个命令");
			return false;
		}
		if(cmdType==1 || cmdType==8){
			if(version==""){
				alert("请选择版本号");
				return false;
			}
		}
		return true;
	}
</script>
