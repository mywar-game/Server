<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="economicAnalysisJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<%-- <form action="economicAnalysis?isSearch=T" method="post" >
			<table width="30%" cellspacing="1" cellpadding="3" border="0" align="center">
				<tr>
					<td><s:text name="log.nameSearch"></s:text><s:text name="colon"></s:text></td>
					<td><input type="text" id="user.name" name="user.name" /></td>
					<td><input type="submit" value="<s:text name="search"></s:text>" class="button"/></td>
				</tr>
			</table>
		</form> --%>
		<%-- <s:if test="userInfoMap != null">
			<table id="table1" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="10" align="center">
						<center>
							<s:text name="economicAnalysisJsp.userInfoMap"></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="economicAnalysisJsp.userInfoMap.level"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						${userInfoMap.level}
					</td>
					<td>
						<s:text name="economicAnalysisJsp.userInfoMap.totalLiveHours"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<s:text name="economicAnalysisJsp.userInfoMap.totalLiveHours_show"><s:param>${userInfoMap.totalLiveHours}</s:param></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="economicAnalysisJsp.userInfoMap.money"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<fmt:formatNumber type="number" value="${userInfoMap.money}" pattern="#,###" />
					</td>
					<td>
						<s:text name="economicAnalysisJsp.userInfoMap.payAmount"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<fmt:formatNumber type="number" value="${userInfoMap.payAmount}" pattern="#,###" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="economicAnalysisJsp.userInfoMap.diamond"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<fmt:formatNumber type="number" value="${userInfoMap.diamond}" pattern="#,###" />
					</td>
					<td>
						<s:text name="economicAnalysisJsp.userInfoMap.getDiamondAmountInGame"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<fmt:formatNumber type="number" value="${userInfoMap.getDiamondAmountInGame}" pattern="#,###" />
					</td>
				</tr>
			</table>
		</s:if> --%>
		
		
		<form>
			<s:set var="total" value="0"></s:set>
			<%--
			<table id="table2" cellpadding="3" cellspacing="1" border="0" width="25%" align="left">
				<tr class="border">
					<td class="td_title" colspan="5" align="center">
						<center>
							<s:text name="economicAnalysisJsp.moneyOutputMap"></s:text>
						</center>
					</td>
				</tr>
				<s:if test="moneyOutputMap != null && moneyOutputMap.size() > 0">
					<tr>
						<td>
							<s:text name="economicAnalysisJsp.type"></s:text>
						</td>
						<td>
							<s:text name="economicAnalysisJsp.num"></s:text>
						</td>
						<td>
							百分比
						</td>
					</tr>
<!--				先求一下总值	-->
					<s:iterator value="moneyOutputMap">
						<s:set name="total" value="#total+value"></s:set>
					</s:iterator>
					<s:iterator value="moneyOutputMap">
						<tr>
							<td>
								<s:text name="%{'userResourceLog.operation_'+key}"></s:text>
							</td>
							<td>
								<fmt:formatNumber type="number" value="${value}" pattern="#,###" />
							</td>
							<td>
								<fmt:formatNumber type="percent" value="${value/total}" pattern="#.##%" />
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td><s:text name="economicAnalysisJsp.total"></s:text></td>
						<td colspan="2">
							<fmt:formatNumber type="number" value="${total}" pattern="#,###" />
						</td>
					</tr>
				</s:if>
			</table> --%>
			<%-- <table id="table3" cellpadding="3" cellspacing="1" border="0" width="25%" align="left">
				<tr class="border">
					<td class="td_title" colspan="5" align="center">
						<center>
							<s:text name="economicAnalysisJsp.moneyConsumeMap"></s:text>
						</center>
					</td>
				</tr>
				<s:if test="moneyConsumeMap != null && moneyConsumeMap.size() > 0">
					<tr>
						<td>
							<s:text name="economicAnalysisJsp.type"></s:text>
						</td>
						<td>
							<s:text name="economicAnalysisJsp.num"></s:text>
						</td>
						<td>
							百分比
						</td>
					</tr>
<!--				先求一下总值	-->
					<s:set name="total" value="0"></s:set>
					<s:iterator value="moneyConsumeMap">
						<s:set name="total" value="#total+value"></s:set>
					</s:iterator>
					<s:iterator value="moneyConsumeMap">
						<tr>
							<td>
								<s:text name="%{'userResourceLog.operation_'+key}"></s:text>
							</td>
							<td>
								<fmt:formatNumber type="number" value="${value}" pattern="#,###" />
							</td>
							<td>
								<fmt:formatNumber type="percent" value="${value/total}" pattern="#.##%" />
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td><s:text name="economicAnalysisJsp.total"></s:text></td>
						<td colspan="2">
							<fmt:formatNumber type="number" value="${total}" pattern="#,###" />
						</td>
					</tr>
				</s:if>
			</table> --%>
			
			<table id="table4" cellpadding="3" cellspacing="1" border="0" width="25%" align="left">
				<tr class="border">
					<td class="td_title" colspan="5" align="center">
						<center>
							<s:text name="economicAnalysisJsp.diamondReceiveMap"></s:text>
							<s:text name="economicAnalysisJsp.tips"></s:text>
						</center>
					</td>
				</tr>
				<s:if test="diamondReceiveMap != null && diamondReceiveMap.size() > 0">
					<tr>
						<td>
							<s:text name="economicAnalysisJsp.type"></s:text>
						</td>
						<td>
							<s:text name="economicAnalysisJsp.num"></s:text>
						</td>
						<td>
							百分比
						</td>
					</tr>
<!--				先求一下总值	-->
					<s:set name="total" value="0"></s:set>
					<s:iterator value="diamondReceiveMap">
						<s:set name="total" value="#total+value.split('_')[0]*1"></s:set>
					</s:iterator>
					<s:iterator value="diamondReceiveMap">
						<tr>
							<td>
								<s:text name="%{'userResourceLog.operation_'+key}"></s:text>
							</td>
							<td>
								<s:set var="num" value="value.split('_')[0]"/>
								<fmt:formatNumber type="number" value="${num}" pattern="#,###" />
							</td>
							<td>
								<fmt:formatNumber type="percent" value="${num/total}" pattern="#.##%" />
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td><s:text name="economicAnalysisJsp.total"></s:text></td>
						<td colspan="2">
							<fmt:formatNumber type="number" value="${total}" pattern="#,###" />
						</td>
					</tr>
				</s:if>
			</table>
			<table id="table4" cellpadding="3" cellspacing="1" border="0" width="25%" align="left">
				<tr class="border">
					<td class="td_title" colspan="5" align="center">
						<center>
							<s:text name="economicAnalysisJsp.diamondConsumeList"></s:text>
							<s:text name="economicAnalysisJsp.tips"></s:text>
						</center>
					</td>
				</tr>
				<s:if test="diamondConsumeMap != null && diamondConsumeMap.size() > 0">
					<tr>
						<td>
							<s:text name="economicAnalysisJsp.type"></s:text>
						</td>
						<td>
							<s:text name="economicAnalysisJsp.num"></s:text>
						</td>
						<td>
							百分比
						</td>
					</tr>
<!--				先求一下总值	-->
					<s:set name="total" value="0"></s:set>
					<s:iterator value="diamondConsumeMap">
						<s:set name="total" value="#total+value.split('_')[0]*1"></s:set>
					</s:iterator>
					<s:iterator value="diamondConsumeMap">
						<tr>
							<td>
								<s:text name="%{'userResourceLog.operation_'+key}"></s:text>
							</td>
							<td>
								<s:set var="num" value="value.split('_')[0]"/>
								<fmt:formatNumber type="number" value="${num}" pattern="#,###" />
							</td>
							<td>
								<fmt:formatNumber type="percent" value="${num/total}" pattern="#.##%" />
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td><s:text name="economicAnalysisJsp.total"></s:text></td>
						<td colspan="2">
							<fmt:formatNumber type="number" value="${total}" pattern="#,###" />
						</td>
					</tr>
				</s:if>
			</table>
		</form>
		<div style="float:none">
			
		</div>
	</body>
</html>