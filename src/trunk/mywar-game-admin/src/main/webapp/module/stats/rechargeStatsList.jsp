<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="rechargeStatsListJsp.title"></s:text></title>
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
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/stats_WdatePicker.js"></script>
	</head>
	<body>
		<form action="rechargeStatsList" method="post" onsubmit="return check()">
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" onfocus="WdatePicker({onpicked:function(){endDate.focus();}})" class="Wdate" id="startDate" name="startDate" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" style="width:100px"/>
			<s:text name="timeTo"></s:text>
			<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" class="Wdate" id="endDate" name="endDate" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" style="width:100px"/>
			
			<s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="rechargeStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="rechargeStats.time"></s:text>
				</td>
				<td>
					<s:text name="rechargeStats.channel"></s:text>
				</td>
				<td>
					<s:text name="rechargeStats.server"></s:text>
				</td>
				<td>
					<s:text name="rechargeStats.payUserNum"></s:text>
				</td>
				<td>
					<s:text name="rechargeStats.dayActive"></s:text>
				</td>
				<td>
					<s:text name="rechargeStats.oldRegPayNum"></s:text>
				</td>
				<td>
					<s:text name="rechargeStats.newRegPayNum"></s:text>
				</td>
				<td>
					<s:text name="rechargeStats.payTotalAmount"></s:text>
				</td>
				<td>
					<s:text name="rechargeStats.oldRegPayTotalAmount"></s:text>
				</td>
				<td>
					<s:text name="rechargeStats.newRegPayTotalAmount"></s:text>
				</td>
				<td>
					<s:text name="rechargeStats.newReg"></s:text>
				</td>
				<td>
					<s:text name="rechargeStats.agvNewRegPayValue"></s:text>
				</td>
				<td>
					<s:text name="rechargeStats.penetrateRate"></s:text>
				</td>
				<td>
					<s:text name="rechargeStats.payRate"></s:text>
				</td>
				<td>
					<s:text name="rechargeStats.arppu"></s:text>
				</td>
				<td>
					<s:text name="rechargeStats.arpu"></s:text>
				</td>
			</tr>
			<s:iterator var="list" value="mappingList">
				<tr>
					<td rowspan="${fn:length(value)+1}">
						${key}
					</td>
					<s:iterator var="stats" value="value">
						<tr>
							<td>
								${partnerMap[stats.partnerId].PName}
							</td>
							<td>
								<%-- ${stats.sysNum} --%>
								${stats.serverName}
							</td>
							<td>
								${stats.payUserNum}
							</td>
							<td>
								${stats.dayActive}
							</td>
							<td>
								${stats.oldRegPayNum}
							</td>
							<td>
								${stats.newRegPayNum}
							</td>
							<td>
								${stats.payTotalAmount}
							</td>
							<td>
								${stats.oldRegPayTotalAmount}
							</td>
							<td>
								${stats.newRegPayTotalAmount}
							</td>
							<td>
								${stats.newReg}
							</td>
							<td>
								${stats.agvNewRegPayValue}
							</td>
							<td>
								${stats.penetrateRate}%
							</td>
							<td>
								${stats.payRate}%
							</td>
							<td>
								${stats.arppu}
							</td>
							<td>
								${stats.arpu}
							</td>
						</tr>	
					</s:iterator>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="22">
					<aldtags:pageTag datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}" para1="pageSize" value1="${pageSize}"/>
				</td>
			</tr>
		</table>
	</body>
</html>