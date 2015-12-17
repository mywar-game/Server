<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userFirendLogListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="../../js/ajax.js"></script>
	</head>
	<body>
		<form action="userFirendLogList" method="post" >
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center id="table">
				<tr class="border">
					<td class="td_title" colspan="10" >
						<center><s:text name="userFirendLogListJsp.title"></s:text> &nbsp;</center>
					</td>
				</tr>
				<tr class="border" >
					<td>
						<s:text name="log.userIdSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="userId" name="userId" onblur="value=value.replace(/[^\d]/g,'')" value="${userId}"/>
					</td>
					<td>
						<s:text name="log.userNameSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="userName" name="userName" value="${userName}"/>
					</td>
					<td>
						<s:text name="log.nameSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="name" name="name" value="${name}"/>
					</td>
					
					<td align="center">
						<input type="submit" value="<s:text name="search"></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>


		<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center id="table">
			<tr>
				<td>
					<s:text name="userFirendLog.userFirendLogId"></s:text>
				</td>
				<td>
					<s:text name="userFirendLog.operrationUserId"></s:text>
				</td>
				<td>
					<s:text name="userFirendLog.userName"></s:text>
				</td>
				<td>
					<s:text name="userFirendLog.name"></s:text>
				</td>
				<td>
					<s:text name="userFirendLog.operationType"></s:text>
				</td>
				<td>
					<s:text name="userFirendLog.beInvitedUserId"></s:text>
				</td>
				<td>
					<s:text name="userFirendLog.operationTime"></s:text>
				</td>
			</tr>
			<s:iterator var="userFirendLog" value="userFirendLogList" status="sta">
				<tr >
				
					<td>
							${sta.index+1}
					</td>
					<td>
							${userFirendLog.operrationUserId}
					</td>
					
					<td>
							${userFirendLog.userName}
					</td>
				
					<td>
							${userFirendLog.name}
					</td>
					
					<td>
						<s:text name="%{'userFirendLog.operationType_'+#userFirendLog.operationType}"></s:text>
					</td>
					
					<td>
							${userFirendLog.beInvitedUserId}
					</td>
					
					<td>
						<s:text name="format.date_time">
							<s:param value="#userFirendLog.operationTime"></s:param>
						</s:text>
					</td>
					
				</tr>
			</s:iterator>
				<tr>
				<td colspan="100">
					<aldtags:pageTag paraStr="userId,${userId},userName,${userName},name,${name}"/>
				</td>
			</tr>
		</table>
	</body>
</html>