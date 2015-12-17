<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>玩家每等级区间的各在线时长区间的人数和消费统计</title>
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
<!--		<form action="userLevelIntervalConsumeStatsList" method="post" onsubmit="return check()">-->
<!--			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>-->
<!--			<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>-->
<!--			<s:text name="timeTo"></s:text>-->
<!--			<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>-->
			
<!--			<s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">-->
<!--			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />-->
<!--		</form>-->
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						玩家不同等级区间的各活跃天数区间的消费统计
					</center>
				</td>
			</tr>
			<tr>
				<td rowspan="2">
					等级区间
				</td>
				<td colspan="2">
					<center>
						小户
					</center>
				</td>
				<td colspan="2">
					<center>
						中户
					</center>
				</td>
				<td colspan="2">
					<center>
						大户
					</center>
				</td>
				<td rowspan="2">
					等级区间总元宝消耗
				</td>
			</tr>
			<tr>
				<td>
					人数（消耗活跃天数）
				</td>
				<td>
					平均消耗元宝（预计消耗）
				</td>
				<td>
					人数（消耗活跃天数）
				</td>
				<td>
					平均消耗元宝（预计消耗）
				</td>
				<td>
					人数（消耗活跃天数）
				</td>
				<td>
					平均消耗元宝（预计消耗）
				</td>
			</tr>
<!--			${statsMap}<br/>-->
			<s:bean name="org.apache.struts2.util.Counter" id="counter">
				<s:param name="first" value="0" />
				<s:param name="last" value="19" />
				<s:iterator>
					<s:set var="levelIntervalIndex" value="current-1"></s:set>
					<tr>
						<td>
							<s:text name="%{'userLevelIntervalActiveDaysAndConsumStatsListJsp.userLevelInterval_'+#levelIntervalIndex}"></s:text>
						</td>
						<s:set var="levelIntervalConsumeAmount" value="0"></s:set>
						<s:generator separator="," val="%{getText('userLevelIntervalActiveDaysAndConsumStatsListJsp.userConsumeType_value')}">
							<s:iterator var="userConsumeType">
<!--								<s:property value="#levelInterval+'_'+#liveTimeIntervalIndex"/>-->
								<s:set var="info" value="statsMap[#levelIntervalIndex+'-'+#userConsumeType]"/>
								<s:set var="testInfo" value="testStatsMap[#levelIntervalIndex+'-'+#userConsumeType]"/>
								<s:if test="#info != null && !''.equals(#info)">
									<s:set var="population" value="#info.split('-')[0]"></s:set>
									<s:set var="consumeAmount" value="#info.split('-')[1]"></s:set>
									<s:set var="levelIntervalConsumeAmount" value="#levelIntervalConsumeAmount + #consumeAmount*1"></s:set>
									<s:if test="#testInfo != null && !''.equals(#testInfo)">
										<s:set var="testPopulation" value="#testInfo.split('-')[0]"></s:set>
										<s:set var="testConsumeAmount" value="#testInfo.split('-')[1]"></s:set>
									</s:if>
									<td>
										${population}
										<s:if test="#testPopulation != null">
											<font color="grey">(${testPopulation})</font>
										</s:if>
										<s:if test="#userConsumeType == 0">
											（>=${activeDaysInterval[levelIntervalIndex][0]}）
										</s:if>
										<s:if test="#userConsumeType == 1">
											（>=${activeDaysInterval[levelIntervalIndex][1]}）
										</s:if>
										<s:if test="#userConsumeType == 2">
											（<${activeDaysInterval[levelIntervalIndex][1]}）
										</s:if>
									</td>
									<td>
										<s:set var="avgConsumeAmount" value="#consumeAmount/#population"></s:set>
										<s:set var="estimateIntervalAvgConsumeStr" value="%{getText('userLevelIntervalActiveDaysAndConsumStatsListJsp.estimateIntervalAvgConsume_'+#levelIntervalIndex+'_'+#userConsumeType)}"></s:set>
										<s:if test="#avgConsumeAmount < #estimateIntervalAvgConsumeStr.split('-')[0] || #avgConsumeAmount > #estimateIntervalAvgConsumeStr.split('-')[1]">
											<font color="red">
												<fmt:formatNumber type="number" value="${avgConsumeAmount}" pattern="#,###" />
											</font>
										</s:if>
										<s:else>
											<fmt:formatNumber type="number" value="${avgConsumeAmount}" pattern="#,###" />
										</s:else>
										(${estimateIntervalAvgConsumeStr})
									</td>
								</s:if>
								<s:else>
									<td></td>
									<td></td>
								</s:else>
							</s:iterator>
						</s:generator>
						<td>
							<fmt:formatNumber type="number" value="${levelIntervalConsumeAmount}" pattern="#,###" />
						</td>	
					</tr>
				</s:iterator>
			</s:bean>
		</table>
	</body>
</html>