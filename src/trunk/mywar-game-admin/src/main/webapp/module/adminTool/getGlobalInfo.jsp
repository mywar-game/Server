<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="getGlobalInfoJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="../../js/json.js"></script>
	</head>
	<body>
		<form action="getGlobalInfo" method="post" onsubmit="">
				<table id="table" cellpadding="3" cellspacing="1" border="0" width="40%" align="left" >
						<tr class="border">
							<td class="td_title" colspan="5" align="center">
								<center>
									<s:text name="getGlobalInfoJsp.title"></s:text>
								</center>
							</td>
						</tr>
						<tr>
							<td width = "30%">
								<s:text name="statisticsInfo.totalRegisterNum"></s:text>
							</td>
							
							<td  width = "70%">
								${statisticsInfo.totalRegisterNum}
							</td>
							
						</tr>
						<tr>
							<td>
								<s:text name="statisticsInfo.totalPayNum"></s:text>
							</td>
							<td>
								${statisticsInfo.totalPayNum}
							</td>
						</tr>
						
						<tr>
							<td>
								<s:text name="statisticsInfo.totalGoldenNum"></s:text>
							</td>
		
							<td>
								${statisticsInfo.totalGoldenNum}
							</td>
						</tr>
						<tr>
							<td>
								<s:text name="statisticsInfo.totalOnlineTime"></s:text>
							</td>
							<td>
								<s:text name="getGlobalInfoJsp.onlineTimeShow">
									<s:param>${statisticsInfo.totalOnlineTime}</s:param>
									<s:param><fmt:formatNumber type="number" maxFractionDigits="0" value="${statisticsInfo.totalOnlineTime/60}"></fmt:formatNumber></s:param>
									<s:param><fmt:formatNumber type="number" maxFractionDigits="0" value="${statisticsInfo.totalOnlineTime/60/24}"></fmt:formatNumber></s:param>
								</s:text>
								
							</td>
						</tr>
						<tr>
							<td>
								<s:text name="statisticsInfo.averageOnlineTime"></s:text>
							</td>
							<td>
								<s:text name="getGlobalInfoJsp.onlineTimeShow">
									<s:param>${statisticsInfo.averageOnlineTime}</s:param>
									<s:param><fmt:formatNumber type="number" maxFractionDigits="0" value="${statisticsInfo.averageOnlineTime/60}"></fmt:formatNumber></s:param>
									<s:param><fmt:formatNumber type="number" maxFractionDigits="0" value="${statisticsInfo.averageOnlineTime/60/24}"></fmt:formatNumber></s:param>
								</s:text>
							</td>
						</tr>
				</table>
				
				<table id="table1" cellpadding="3" cellspacing="1" border="0" width="60%" align=left >
						<tr class="border">
							<td width="25%"><s:text name="globalInfo.level"></s:text></td>
							<td width="25%"><s:text name="globalInfo.population"></s:text></td>
							<td width="25%"><s:text name="globalInfo.activePopulation"></s:text></td>
							<td width="25%"><s:text name="globalInfo.payPopulation"></s:text></td>
						</tr>
						<c:if test="${list != null}">
							<s:iterator var="globalInfo" value="list">
								<tr class="border">
									<td>
										<s:text name="%{'globalInfo.level_'+#globalInfo.level}"></s:text>
									</td>
									<td>${globalInfo.population}</td>
									<td>${globalInfo.activePopulation}</td>
									<td>${globalInfo.payPopulation}</td>
								</tr>
							</s:iterator>
						</c:if>
				</table>
		</form>
	</body>
</html>