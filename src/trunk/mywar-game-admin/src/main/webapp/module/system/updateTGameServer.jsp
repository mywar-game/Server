<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@page import="java.util.*" language="java"%>
<%@page import="com.system.bo.TSecondaryServer" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateTGameServerJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
	<script src="../../js/jquery/jquery.min.js"></script>
	<script type="text/javascript">
		function isSubmit(){
			if(window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
		}
	</script>
 <body>
		&nbsp;
		<form action="updateTGameServer?isCommit=T" method="post" onsubmit="return isSubmit()">

			<table cellpadding="3" cellspacing="1" border="0" width="100%"
				align=center>
				<tr class="border">
					<td class="td_title" colspan="10">
						<s:text name="updateTGameServerJsp.title"></s:text> &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="serverId"></s:text> <s:text name="colon"></s:text>
					</td>
					<td id="serverId">
						<input type="hidden" name="serverId" value="${tgameServer.serverId}"/>
						${tgameServer.serverId}
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="serverName"></s:text> <s:text name="colon"></s:text>
					</td>
					<td >
						<input type="hidden" name="serverName" value="${dbServer.serverName}"/>
						${tgameServer.serverName}
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="serverAlias"></s:text> <s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="serverAlias" value="${tgameServer.serverAlias}"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<s:text name="serverAlias_note"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="official"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select id="official" name="official" class="select">
							<s:generator separator="," val="%{getText('official.values')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="tgameServer.official == #str">selected=selected</s:if>>
										<s:text name="%{'official_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="serverIp"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="serverIp" value="${tgameServer.serverIp}"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<s:text name="serverIp_note"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="serverPoint"></s:text> <s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="serverPoint" value="${tgameServer.serverPoint}"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<s:text name="serverPoint_note"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="webOpenPort"></s:text> <s:text name="colon"></s:text>
					</td>
					<td>
						${tgameServer.webOpenPort}
						<input type="hidden" name="webOpenPort" value="${tgameServer.webOpenPort}"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<s:text name="webOpenPort_note"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="webServerPath"></s:text> <s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="webServerPath" value="${tgameServer.webServerPath}"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<s:text name="webServerPath_note"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="serverCommunicationKey"></s:text> <s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="serverCommunicationKey" value="${tgameServer.serverCommunicationKey}" size="32"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<s:text name="serverCommunicationKey_note"></s:text>
					</td>
					</tr>
				<tr>
					<td>
						<s:text name="serverOpernTime"></s:text> <s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="serverOpernTime" name="serverOpernTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="tgameServer.serverOpernTime != null"><s:text name="format.date_ymd"><s:param value="tgameServer.serverOpernTime"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
					</td>
				</tr>
				<!--
				<tr>
					<td>
						<s:text name="gameServerUrl"></s:text> <s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="gameServerUrl" value="${tgameServer.gameServerUrl}"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<s:text name="gameServerUrl_note"></s:text>
					</td>
				</tr>
				-->
				<tr>
					<td>
						<s:text name="logDbServerCode"></s:text> <s:text name="colon"></s:text>
						<input type="hidden" name="gameServerUrl" value="${tgameServer.gameServerUrl}"/>
					</td>
					<td>
					<select name="logDbServerCode" >
							<s:iterator var="data" value="tdbServersForLog">
								<option value="${data.dbServerId}" <s:if test="tgameServer.logDbServerCode == #data.dbServerId">selected=selected</s:if>>${data.serverName}(${data.serverIp})
								</option>
							</s:iterator>
						</select>
					</td>
					</tr>
				<tr>
					<td>
						<s:text name="configDbServerCode"></s:text> <s:text name="colon"></s:text>
					</td>
					<td>
						<select name="configDbServerCode" >
							<s:iterator var="data" value="tdbServersForConfig" >
								<option value="${data.dbServerId}" <s:if test="tgameServer.configDbServerCode == #data.dbServerId">selected=selected</s:if>>${data.serverName}(${data.serverIp})
								</option>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="userDbServerCode"></s:text> <s:text name="colon"></s:text>
					</td>
					<td>
						<select name="userDbServerCode" >
							<s:iterator var="data" value="tdbServersForUser">
								<option value="${data.dbServerId}" <s:if test="tgameServer.userDbServerCode == #data.dbServerId">selected=selected</s:if>>${data.serverName}(${data.serverIp})
								</option>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="10" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
						<input type="hidden" name="loginEncryption" value="${tgameServer.loginEncryption}">
						<input type="hidden" name="timeZoneId" value="${tgameServer.timeZoneId}">
						<input type="hidden" name="gameWebServerId" value="${tgameServer.gameWebServerId}">
					</td>
				</tr>
			</table>
		</form>
	</body>
	<script src="../../js/jquery/jquery.validate.js"></script>
	<script type="text/javascript" >
		$(document).ready(function(){
			$("form").validate({
				rules:{
					serverAlias:{
						required: true,
						maxlength:64
					},
					serverIp:{
						required: true,
						maxlength:32
					},
					serverPoint:{
						required: true,
						digits:true
					},
					webOpenPort:{
						required: true,
						digits:true
					},
					webServerPath:{
						required: true,
						maxlength:64
					},
					serverCommunicationKey:{
						required: true,
						maxlength:32
					},
					serverOpernTime:{
						required: true,
					},
					gameServerUrl:{
						required: true,
						maxlength:256
					},
					loginEncryption:{
						required: true,
						maxlength:64
					}
				}
			})
		});
	</script>
</html>