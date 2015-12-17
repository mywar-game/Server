<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="发送邮件"></s:text></title>
	</head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="../../js/json.js"></script>
	<script language="javascript" src="../../js/ajax.js"></script>

<body>
<form   >
	<table cellpadding="3" cellspacing="2" border="0" width="100%" align=center>
		<tr>
			<td>主题</td>
			<td>${adminMail.title}
		</tr>
		<tr>
			<td>邮件内容</td>
			<td>${adminMail.content}</td>
		</tr>
		<tr>
			<td>服务器</td>
			<td>${adminMail.serverIds }</td>
		</tr>
		<tr>
			<td>收件人</td>
			<td>
			<s:if test="adminMail.target == 1">
				<s:if test="adminMail.lodoIds != ''">
					注册时间在 <b>${adminMail.lodoIds}</b> 至 <b>${adminMail.date}</b> 之间的用户
				</s:if>
				<s:else>
					注册时间在 <b>${adminMail.date}</b> 之前的用户
				</s:else>						
			</s:if>
			<s:if test="adminMail.target == 2">发送给 lodoId 是 ${adminMail.lodoIds } 的用户</s:if>
			<s:if test="adminMail.target == 3">发送给在 ${adminMail.date } 这一天登录的用户</s:if>
			<s:if test="adminMail.target == 4">发送给在 ${adminMail.date } 这一天充值过的用户</s:if>
			<s:if test="adminMail.target == 5">发送给渠道商是 ${adminMail.partner } 的用户</s:if>
		</tr>
		<tr>
			<td>道具发放列表</td>
			<td>${adminMail.toolList }</td>
		</tr>
		<tr>
			<td colspan="10" align="center">
				<a href="mailAudit?isAudited=1&isCommit=T&adminMailId=${adminMail.adminMailId}"><input type="button" value="<s:text name='同意'></s:text>" /></a>
				<a href="mailAudit?isAudited=-1&isCommit=T&adminMailId=${adminMail.adminMailId}"><input type="button" value="<s:text name='拒绝'></s:text>"/></a>
				<a href="mailAuditList"><input type="button" value="<s:text name='返回'></s:text>"/></a>
			</td>
		
		
	</table>
</form>
</body>
</html>
