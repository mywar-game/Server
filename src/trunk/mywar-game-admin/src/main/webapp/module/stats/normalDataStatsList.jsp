<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="normalDataStatsListJsp.title"></s:text></title>
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
		<form action="normalDataStatsList" method="post" onsubmit="return check()">
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
						<s:text name="normalDataStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="normalDataStats.time"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.server"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.userTotal"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.newReg"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.dayActive"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.activeRate"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.maxOnline"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.maxOnlineTime"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.agvOnline"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.agvTime"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.agvLoginNum"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.dayIncome"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.channelIncomeInfo"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.payNum"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.activePayRate"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.arpu"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.arppu"></s:text>
				</td>
				<td>
					<s:text name="normalDataStats.regArpu"></s:text>
				</td>
			</tr>
			<s:iterator var="stats" value="statsList">
				<tr>
					<td>
						<s:text name="format.date_ymd">
							<s:param value="#stats.time"></s:param>
						</s:text>
					</td>
					<td>
						${stats.serverName}
					</td>
					<td>
						${stats.userTotal}
					</td>
					<td>
						${stats.newReg}
					</td>
					<td>
						${stats.dayActive}
					</td>
					<td>
						${stats.activeRate}
					</td>
					<td>
						${stats.maxOnline}
					</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#stats.maxOnlineTime"></s:param>
						</s:text>
					</td>
					<td>
						${stats.agvOnline}
					</td>
					<td>
						${stats.agvTimeStr}
					</td>
					<td>
						${stats.agvLoginNum}
					</td>
					<td>
						${stats.dayIncome}
					</td>
					<td>
						${stats.channelIncomeInfo}
					</td>
					<td>
						${stats.payNum}
					</td>
					<td>
						<s:if test="#stats.activePayRate==0">
							0
						</s:if>
						<s:else>
							<fmt:formatNumber type="percent" value="${stats.activePayRate}" pattern=".#%" />
						</s:else>
					</td>
					<td>
						${stats.arpu}
					</td>
					<td>
						${stats.arppu}
					</td>
					<td>
						${stats.regArpu}
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="22">
					<aldtags:pageTag datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}" para1="pageSize" value1="${pageSize}"/>
				</td>
			</tr>
		</table>
		<br/>
		说明:
		<br/>
		1、平均时长2014年-6月-30日规则改动
		<br/>
	</body>
</html>