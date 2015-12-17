<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="adminIssueLogListJsp.title"></s:text></title>

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
						<s:text name="adminIssueLogListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="adminIssueLog.id"></s:text>
				</td>
				<td>
					<s:text name="adminIssueLog.adminName"></s:text>
				</td>
				<td>
					<s:text name="adminIssueLog.issueTime"></s:text>
				</td>
				<td>
					<s:text name="adminIssueLog.receiveUser"></s:text>
				</td>
				<td>
					<s:text name="adminIssueLog.receiveUserType"></s:text>
				</td>
				<td>
					<s:text name="adminIssueLog.failUser"></s:text>
				</td>
				<td>
					<s:text name="mailAttach.attachType_1"></s:text>
				</td>
				<td>
					<s:text name="mailAttach.attachType_2"></s:text>
				</td>
				<td>
					<s:text name="adminIssueLog.issueReason"></s:text>
				</td>

			</tr>
			<s:iterator var="adminIssueLog" value="list" status="sta">
				
				<tr>
					<td>
						${adminIssueLog.id}
					</td>
					<td>
						${adminIssueLog.adminName}
					</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#adminIssueLog.issueTime"></s:param>
						</s:text>
					</td>
					<td>
						<s:generator separator="," val="#adminIssueLog.receiveUser">
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
						<s:if test="#adminIssueLog.receiveUserType == 3">
							<font color="red">
								<s:text name="%{'adminIssueLog.receiveUserType_'+#adminIssueLog.receiveUserType}"></s:text>
							</font>
						</s:if>
						<s:else>
							<s:text name="%{'adminIssueLog.receiveUserType_'+#adminIssueLog.receiveUserType}"></s:text>
						</s:else>
					</td>
					<td>
						${adminIssueLog.failUser}
					</td>
					<td>
						<div style="width: 90px">
							<s:iterator var="issueMap" value="allIssueMap[#adminIssueLog.id]">
								<s:if test="#issueMap.attachType == 1">
									${treasureIdNameMap[issueMap.attachId]}x${issueMap.attachNum}<br>
									<!-- 
									<a href="../dataconfig/tTreasureConstantList?id=${issueMap.attachId}">${treasureIdNameMap[issueMap.attachId]}</a>x${issueMap.attachNum}<br>
									 -->
								</s:if>
							</s:iterator>
						</div>
					</td>
					<td>
						<div style="width: 150px">
							<s:iterator var="issueMap" value="allIssueMap[#adminIssueLog.id]">
								<s:if test="#issueMap.attachType == 2">
									${equipmentIdNameMap[issueMap.attachId]}x${issueMap.attachNum}<br>
									<!-- 
									<a href="../dataconfig/eEquipmentConstantList?id=${issueMap.attachId}">${equipmentIdNameMap[issueMap.attachId]}</a>x${issueMap.attachNum}<br>
									 -->
								</s:if>
							</s:iterator>
						</div>
					</td>
					<td>
						${adminIssueLog.issueReason}
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