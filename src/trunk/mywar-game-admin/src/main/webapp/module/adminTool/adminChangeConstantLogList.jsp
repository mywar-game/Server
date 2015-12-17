<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="adminChangeConstantLogJsp.title"></s:text></title>

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
				<td class="td_title" colspan="50" align="center">
					<center>
						<s:text name="adminChangeConstantLogJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="adminChangeConstantLog.id"></s:text>
				</td>
				<td>
					<s:text name="adminChangeConstantLog.adminName"></s:text>
				</td>
				<td>
					<s:text name="adminChangeConstantLog.changeTime"></s:text>
				</td>
				<td>
					<s:text name="adminChangeConstantLog.constantName"></s:text>
				</td>
				<td>
					<s:text name="adminChangeConstantLog.changeType"></s:text>
				</td>
				<td>
					<s:text name="adminChangeConstantLog.changeDetail"></s:text>
				</td>
			</tr>
			<s:iterator var="log" value="list" status="sta">
				
				<tr>
					<td>
						${log.id}
					</td>
					<td>
						${log.adminName}
					</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#log.changeTime"></s:param>
						</s:text>
					</td>
					<td>
						<s:text name="%{'adminChangeConstantLog.constantName_'+#log.constantName}"></s:text>
					</td>
					<td>
						<s:text name="%{'adminChangeConstantLog.changeType_'+#log.changeType}"></s:text>
					</td>
					<td>
						${log.changeDetail}
					</td>

				</tr>
			</s:iterator>
			<tr>
				<td colspan="50">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>