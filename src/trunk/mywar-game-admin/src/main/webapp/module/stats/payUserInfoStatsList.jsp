<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="payUserInfoStatsListJsp.title"></s:text></title>
		<style type="text/css">
		td {
			text-align: center;
		}
		</style>		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="payUserInfoStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="payUserInfoStats.name"></s:text>
				</td>
				<td>
					<s:text name="payUserInfoStats.userName"></s:text>
				</td>
				<td>
					<s:text name="payUserInfoStats.regTime"></s:text>
				</td>
				<td>
					<s:text name="payUserInfoStats.lastLoginTime"></s:text>
				</td>
				<td>
					<s:text name="payUserInfoStats.logCondition"></s:text>
				</td>
				<td>
					<s:text name="payUserInfoStats.totalOnlineTime"></s:text>
				</td>
				<td>
					<s:text name="payUserInfoStats.averageOnlineTime"></s:text>
				</td>
				<td>
					<s:text name="payUserInfoStats.averagePayPeriod"></s:text>
				</td>
				<td>
					<s:text name="payUserInfoStats.guildName"></s:text>
				</td>
			</tr>
			<s:iterator var="stats" value="statsList">
				<tr>
					<td>
						${stats.name}
					</td>
					<td>
						${stats.userName}
					</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#stats.regTime"></s:param>
						</s:text>
					</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#stats.lastLoginTime"></s:param>
						</s:text>
					</td>
					<td>
						<s:text name="payUserInfoStats.logCondition_show">
							<s:param>${stats.logCondition}</s:param>
						</s:text>
					</td>
					<td>
						<s:text name="payUserInfoStatsListJsp.dhm">
							<s:param><s:property value="#stats.totalOnlineTime/(24*60)"/></s:param>
							<s:param><s:property value="#stats.totalOnlineTime%(24*60)/60"/></s:param>
							<s:param><s:property value="#stats.totalOnlineTime%(24*60)%60"/></s:param>
						</s:text>
					</td>
					<td>
						<s:text name="payUserInfoStatsListJsp.dhm">
							<s:param><s:property value="#stats.averageOnlineTime/(24*60)"/></s:param>
							<s:param><s:property value="#stats.averageOnlineTime%(24*60)/60"/></s:param>
							<s:param><s:property value="#stats.averageOnlineTime%(24*60)%60"/></s:param>
						</s:text>
					</td>
					<td>
						<s:text name="payUserInfoStatsListJsp.dhm">
							<s:param><s:property value="#stats.averagePayPeriod/(24*60)"/></s:param>
							<s:param><s:property value="#stats.averagePayPeriod%(24*60)/60"/></s:param>
							<s:param><s:property value="#stats.averagePayPeriod%(24*60)%60"/></s:param>
						</s:text>
					</td>
					<td>
						${stats.guildName}
					</td>
				</tr>
			</s:iterator>
		</table>
		<td colspan="100">
				<aldtags:pageTag paraStr="isCommit,T,pageSize,${pageSize}" />
		</td>
	</body>
</html>