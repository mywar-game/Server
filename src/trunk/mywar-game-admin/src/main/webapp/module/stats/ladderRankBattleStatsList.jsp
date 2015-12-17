<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="ladderRankBattleStatsListJsp.title"></s:text></title>
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
		<form action="ladderRankBattleStatsList" method="post" onsubmit="return check()">
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			<s:text name="timeTo"></s:text>
			<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			
			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="ladderRankBattleStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="ladderRankBattleStatsListJsp.attackNumInterval"></s:text>
				</td>
				<td>
					<s:text name="ladderRankBattleStatsListJsp.userNum"></s:text>
				</td>
				<td>
					<s:text name="ladderRankBattleStatsListJsp.consumeAmount"></s:text>
				</td>
			</tr>
			<s:set var="totalConsumeAmount" value="0"></s:set>
			<s:set var="totalReceiveAmount" value="0"></s:set>
			<s:iterator value="statsMap">
				<tr>
					<td>
						${key*5}-${(key+1)*5}
					</td>
					<td>
						<s:property value="value.split('_')[0]"/>
					</td>
					<td>
						<s:property value="value.split('_')[1]"/>
					</td>
					<s:set name="totalConsumeAmount" value="#totalConsumeAmount+value.split('_')[1]*1"></s:set>
					<s:set name="totalReceiveAmount" value="#totalReceiveAmount+value.split('_')[2]*1"></s:set>
				</tr>
			</s:iterator>
<!--			<tr>-->
<!--				<td colspan="22">-->
<!--					<aldtags:pageTag datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}"/>-->
<!--				</td>-->
<!--			</tr>-->
		</table>
		<s:text name="ladderRankBattleStatsListJsp.note_1">
			<s:param>${totalConsumeAmount}</s:param>
			<s:param>${totalReceiveAmount}</s:param>
			<s:param><fmt:formatNumber type="number" value="${totalConsumeAmount-totalReceiveAmount}" pattern="#,###" /></s:param>
		</s:text>
	</body>
</html>