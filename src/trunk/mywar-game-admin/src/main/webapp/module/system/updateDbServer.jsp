<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="dbServer.update"></s:text><s:text name="colon"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<script src="../../js/jquery/jquery.min.js"></script>
	<body>
		&nbsp;
		<form action="updateDbServer?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="dbServer.update"></s:text><s:text name="colon"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="dbServerId" value="${dbServer.dbServerId}"/>
						<s:text name="dbServer.serverType"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="hidden" name="serverType" value="${dbServer.serverType}">
						<s:text name="%{'dbServer.serverType_'+dbServer.serverType}"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dbServer.serverName"></s:text><s:text name="colon"></s:text>
					</td>
					<td id="serverNameTd">
						<input type="hidden" name="serverName" value="${dbServer.serverName}"/>
						${dbServer.serverName}
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dbServer.serverIp"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="serverIp" value="${dbServer.serverIp}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dbServer.serverPort"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="serverPort" id="serverPort" value="${dbServer.serverPort}"/>
					</td>
				</tr>
				<s:if test="dbServer.serverType != 3">
					<tr>
						<td>
							<s:text name="dbServer.dbName"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<input type="text" name="dbName" value="${dbServer.dbName}" />
						</td>
					</tr>
					<tr>
						<td>
							<s:text name="dbServer.userName"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<input type="text" name="userName" value="${dbServer.userName}" />
						</td>
					</tr>
					<tr>
						<td>
							<s:text name="dbServer.password"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<input type="text" name="password" value="${dbServer.password}" />
						</td>
					</tr>
				</s:if>
				<s:else>
					<input type="hidden" name="dbName" value="${dbServer.dbName}"/>
					<input type="hidden" name="userName" value="${dbServer.userName}"/>
					<input type="hidden" name="password" value="${dbServer.password}"/>
				</s:else>
				
				<tr>
					<td colspan="5" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
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
					serverIp:{
						required: true,
						maxlength:64
					},
					serverPort:{
						required: true,
						digits:true
					},
					dbName:{
						required:true,
						maxlength:32
					},
					userName:{
						required: true,
						maxlength:32
					},
					password:{
						required: true,
						maxlength:32
					}
				}
			})
		});
	</script>
</html>