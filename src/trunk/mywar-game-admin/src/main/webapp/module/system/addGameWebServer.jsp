<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="addGameWebServerJsp.title"></s:text></title>
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
	<body>
		&nbsp;
		<form action="addGameWebServer?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="addGameWebServerJsp.title"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="gameWebId" value="${gameWebId}" />
						<s:text name="server.serverId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="serverId" value="${gameWebServer.serverId}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="gameWebServer.serverName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="serverName" value="${gameWebServer.serverName}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="gameWebServer.serverUrl"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="serverUrl" size="30" value="${gameWebServer.serverUrl}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="gameWebServer.serverPort"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="serverPort" value="${gameWebServer.serverPort}"/>
					</td>
				</tr>
				
				<tr>
					<td>
						<s:text name="gameWebServer.apiPort"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="apiPort" name="apiPort" value="${gameWebServer.apiPort}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="gameWebServer.status"></s:text><s:text name="colon"></s:text>
					</td>
					<td >
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
					<td>
						<s:text name="gameWebServer.openTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td >
						<input type="text" id="openTime" name="openTime" class="Wdate" style="width:160px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
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
					maxlength:10
				},
				serverName:{
					required: true,
					maxlength: 64
				},
				serverUrl:{
					required: true,
					maxlength:64
				},
				serverPort:{
					required: true,
					digits: true,
					maxlength: 11
				},
				apiPort:{
					required: true,
					digits: true,
					maxlength: 11
				},
				status:{
					required: true
				},
				openTime:{
					required:true
				}
			}		
		});	
	});
</script>