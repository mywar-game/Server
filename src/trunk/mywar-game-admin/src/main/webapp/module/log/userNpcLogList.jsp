<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userNpcLogListJsp.title"></s:text><s:text name=""></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="../../js/json.js"></script>
	</head>
	<body>
		<form action="userNpcLogList" method="post" >
			<table>
				<tr>
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
					<td><input type="submit" value="<s:text name="search"></s:text>" /></td>
				</tr>
			</table>
		</form>

		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center >
			<tr class="border">
				<td class="td_title" colspan="12" align="center">
					<center>
						<s:text name="userNpcLogListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="userNpcLog.userNpcLogId"></s:text>
				</td>
				<td>
					<s:text name="userNpcLog.userId"></s:text>
				</td>
				<td>
					<s:text name="userNpcLog.userName"></s:text>
				</td>
				<td>
					<s:text name="userNpcLog.Name"></s:text>
				</td>
<!--					<td>-->
<!--						<s:text name="userNpcLog.npcId"></s:text>-->
<!--					</td>-->
				<td>
					<s:text name="userNpcLog.npcName"></s:text>
				</td>
<!--					<td>-->
<!--						<s:text name="userNpcLog.buildingId"></s:text>-->
<!--					</td>-->
				<td>
					<s:text name="userNpcLog.buildingName"></s:text>
				</td>
<!--					<td>-->
<!--						<s:text name="userNpcLog.userBuildingId"></s:text>-->
<!--					</td>-->
				<td>
					<s:text name="userNpcLog.position"></s:text>
				</td>
				<td>
					<s:text name="userNpcLog.operation"></s:text>
				</td>
				<td>
					<s:text name="userNpcLog.createTime"></s:text>
				</td>
			</tr>
			<s:iterator var="userNpcLog" value="userNpcLogList" status="sta">
					<tr>
						<td>${sta.index+1}</td>
						<td>${userNpcLog.userId}</td>
						<td>${userNpcLog.userName}</td>
						<td>${userNpcLog.name}</td>
<!--							<td>${userNpcLog.npcId}</td>-->
						<td>${userNpcLog.npcName}</td>
<!--							<td>${userNpcLog.buildingId}</td>-->
						<td>${userNpcLog.buildingName}</td>
<!--							<td>${userNpcLog.userBuildingId}</td>-->
						<td><s:text name="%{'userNpcLog.position_'+#userNpcLog.position}"></s:text></td>
						<td><s:text name="%{'userNpcLog.operation_'+#userNpcLog.operation}"></s:text></td>
						<td>
							<s:text name="format.date_time">
								<s:param value="#userNpcLog.createTime"></s:param>
							</s:text>
						</td>
					</tr>
			</s:iterator>
			
			<tr>
				<td colspan="12">
					<aldtags:pageTag paraStr="userId,${userId},userName,${userName},name,${name}"/>
				</td>
			</tr>
		</table>
	</body>
</html>