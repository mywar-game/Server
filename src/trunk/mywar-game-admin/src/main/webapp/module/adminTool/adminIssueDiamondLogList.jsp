<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="adminIssueDiamondLogJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<body>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="50" align="center">
					<center>
						<s:text name="adminIssueDiamondLogJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="adminIssueDiamondLog.id"></s:text>
				</td>
				<td>
					<s:text name="adminIssueDiamondLog.adminName"></s:text>
				</td>
				<td>
					<s:text name="adminIssueDiamondLog.issueTime"></s:text>
				</td>
				<td>
					<s:text name="adminIssueDiamondLog.receiveUser"></s:text>
				</td>
				<td>
					<s:text name="adminIssueDiamondLog.failUser"></s:text>
				</td>
				<td>
					<s:text name="adminIssueDiamondLog.num"></s:text>
				</td>
				<td>
					<s:text name="adminIssueDiamondLog.issueReason"></s:text>
				</td>

			</tr>
			<s:iterator var="adminIssueDiamondLog" value="list" status="sta">
				
				<tr>
					<td>
						${adminIssueDiamondLog.id}
					</td>
					<td>
						${adminIssueDiamondLog.adminName}
					</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#adminIssueDiamondLog.issueTime"></s:param>
						</s:text>
					</td>
					<td>
						<s:generator separator="," val="#adminIssueDiamondLog.receiveUser">
							<s:iterator var="oneUser">
								<s:generator separator="_" val="oneUser">
									<s:iterator var="str" status="sta">
										<s:if test="#sta.index == 0">
											<a href="../adminTool/getUserInfo?userID=${str}">
										</s:if>
										<s:if test="#sta.index == 1">
											${str}
											</a>
										</s:if>
									</s:iterator>
								</s:generator>
							</s:iterator>
						</s:generator>
					</td>
					<td>
						${adminIssueDiamondLog.failUser}
					</td>
					<td>
						${adminIssueDiamondLog.num}
					</td>
					<td>
						${adminIssueDiamondLog.issueReason}
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