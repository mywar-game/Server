<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title><s:text name="addTGameServerJsp.title"></s:text></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
		<script src="../../js/jquery/jquery.min.js"></script>
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
	</head>

	<body>
		<form action="addTGameServer?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border" >
					<td class="td_title" colspan="20">
						<s:text name="addTGameServerJsp.title"></s:text> &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				
				<tr>
					<td>
						<s:text name="serverId"></s:text> <s:text name="colon"></s:text>
					</td>
					<td id="serverId">
						<input type="hidden" name="gameWebServerId" value="${gameWebServerId}" />
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
									<option value="${str}" <s:if test="official == #str">selected=selected</s:if>>
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
						<input type="text" name="serverPoint" value="8080"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<s:text name="serverPoint_note"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="webOpenPort"></s:text> <s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="webOpenPort" value="8080"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<s:text name="webOpenPort_note"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="webServerPath"></s:text> <s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="webServerPath" value="gameAdminApi"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<s:text name="webServerPath_note"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="serverCommunicationKey"></s:text> <s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="serverCommunicationKey" value="jjdadsfoo@11349$^%$113"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<s:text name="serverCommunicationKey_note"></s:text>
					</td>
				</tr>
				<!--
				<tr>
					<td>
						<s:text name="serverOpernTime"></s:text> <s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="serverOpernTime" name="serverOpernTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="tgameServer.serverOpernTime != null"><s:text name="format.date_ymd"><s:param value="tgameServer.serverOpernTime"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
					</td>
				</tr>
				
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
					</td>
					<td>
					<select name="logDbServerCode" >
							<s:iterator var="data" value="tdbServersForLog">
								<option value="${data.dbServerId}">${data.serverName}(${data.serverIp})
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
							<s:iterator var="data" value="tdbServersForConfig">
								<option value="${data.dbServerId}">${data.serverName}(${data.serverIp})
								</option>
							</s:iterator>
						</select>
					</td>
					</tr>
				<tr>
				<tr>
					<td>
						<s:text name="userDbServerCode"></s:text> <s:text name="colon"></s:text>
					</td>
					<td>
						<select name="userDbServerCode" >
							<s:iterator var="data" value="tdbServersForUser">
								<option value="${data.dbServerId}">${data.serverName}(${data.serverIp})
								</option>
							</s:iterator>
						</select>
					</td>
					</tr>
				<tr>
				<tr>
					<td align="center" colspan="2">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button"  />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
						<input type="hidden" name="loginEncryption" value="${tgameServer.loginEncryption}">
						<input type="hidden" name="timeZoneId" value="Asia/Shanghai">
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
