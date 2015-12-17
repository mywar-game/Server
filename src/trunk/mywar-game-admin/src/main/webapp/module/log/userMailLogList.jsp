<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userMailLogListListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var erroDescrip = '${erroDescrip}';
			if (erroDescrip != "") {
				alert(erroDescrip);
			}
		</script>
	</head>
	<body>
		<form action="userMailLogList" method="post" >
			<table>
				<tr>
					<td>
						<s:text name="userMailLog.senderName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="searchSenderName" value="${searchSenderName}"/>
					</td>
					<td>
						<s:text name="userMailLog.receiveName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="searchReceiveName" value="${searchReceiveName}"/>
					</td>
					<td>
						<input type="radio" name="systemSend" value="true" <s:if test="systemSend == true">checked</s:if>><s:text name="userMailLogListListJsp.systemSend"></s:text>
					</td>
					<td><input type="submit" value="<s:text name="search"></s:text>" /></td>
				</tr>
			</table>
		</form>
		
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center >
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						<s:text name="userMailLogListListJsp.title"></s:text>
					</center>
				</td>
			</tr>			
			<tr>
				<td>
					<s:text name="userMailLog.userMailLogId"></s:text>
				</td>
				<td>
					<s:text name="userMailLog.senderName"></s:text>
				</td>
				<td>
					<s:text name="userMailLog.receiveName"></s:text>
				</td>
				<td>
					<s:text name="userMailLog.mailType"></s:text>
				</td>
				<td>
					<s:text name="userMailLog.mailTheme"></s:text>
				</td>
				<td>
					<s:text name="userMailLog.mailContent"></s:text>
				</td>
				<td>
					<s:text name="userMailLog.mailAttach"></s:text>
				</td>
				<td>
					<s:text name="userMailLog.sendTime"></s:text>
				</td>
				<td>
					<s:text name="userMailLog.getAttachTime"></s:text>
				</td>
				<td>
					<s:text name="userMailLog.delTime"></s:text>
				</td>
			
			</tr>
				<s:iterator var="userMailLog" value="userMailLogList" status="sta">
				<tr>
					<td>${sta.index+1}</td>
					<td>${userMailLog.senderName}</td>
					<td>${userMailLog.receiveName}</td>
					<td>
						<s:text name="%{'userMailLog.mailType_'+#userMailLog.mailType}"></s:text>
					</td>
					<td>${userMailLog.mailTheme}</td>
					<td>${userMailLog.mailContent}</td>
					<td style="width: 150">
						<s:iterator var="one" value="#userMailLog.mailAttachList">
<!--						金钱或钻石	-->
							<s:if test="#one.attachType == 1">
								<s:text name="%{'mailAttach.attachId_1_'+#one.attachId}">
									<s:param>${one.attachNum}</s:param>
								</s:text>
								<br/>
							</s:if>
<!--						道具	-->
							<s:if test="#one.attachType == 2">
								${treasureIDNameMap[one.attachId]}*${one.attachNum}<br/>
							</s:if>
<!--						装备	-->
							<s:if test="#one.attachType == 3">
								${equipmentIdNameMap[one.attachId]}*${one.attachNum}<br/>
							</s:if>
						</s:iterator>
					</td>
					<td>
						<s:if test="#userMailLog.sendTime != null">
							<s:text name="format.date_time">
								<s:param value="#userMailLog.sendTime"></s:param>
							</s:text>
						</s:if>
					</td>
					<td>
						<s:if test="#userMailLog.getAttachTime != null">
							<s:text name="format.date_time">
								<s:param value="#userMailLog.getAttachTime"></s:param>
							</s:text>
						</s:if>
					</td>
					<td>
						<s:if test="#userMailLog.delTime != null">
							<s:text name="format.date_time">
								<s:param value="#userMailLog.delTime"></s:param>
							</s:text>
						</s:if>
					</td>
				</tr>
				</s:iterator>
				
				<tr>
					<td colspan="100">
						<aldtags:pageTag paraStr="systemSend,${systemSend},searchSenderName,${searchSenderName},searchReceiveName,${searchReceiveName}"/>
					</td>
				</tr>
		</table>
	</body>
</html>