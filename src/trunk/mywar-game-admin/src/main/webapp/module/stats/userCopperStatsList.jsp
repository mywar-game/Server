<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userDiamondStatsListJsp.title"></s:text></title>
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
		<script>
			function changeSelect(obj) {
				var sType = document.getElementById("sType").value;
				if (sType == "T") {
					var sName = 1;
					window.location.href="userCopperStatsList?isCommit=T";
				} else if (sType == "F") {
					var sName = 2;
					window.location.href="userCopperStatsList?isCommit=F";
				}
			}
		</script>
	</head>
	<body>
		<form action="userCopperStatsList" method="post" onsubmit="return check()">
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			<s:text name="timeTo"></s:text>
			<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			
			<s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
			<select name="isCommit" id="sType" onchange="changeSelect(this)" class="select">
				<option value="TF"><s:text name="pleaseSelect"></s:text></option>
				<option value="T" <s:if test='isCommit=="T"'>selected=selected</s:if>>产出</option>
				<option value="F"  <s:if test='isCommit=="F"'>selected=selected</s:if>>消耗</option>
			</select>
			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
		</form>
		<s:if test='isCommit=="T"'>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="61" align="center">
					<center>
						金币统计（每天凌晨统计）
					</center>
				</td>
			</tr>
			<tr>
				<td>
					时间
				</td>
				
				<td>
					<s:text name="userResourceLog.operation_1001"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1002"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1003"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1004"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1005"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1006"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1009"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1010"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1012"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1013"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1014"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1015"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1016"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1017"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1018"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1019"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1020"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1021"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1022"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1023"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1024"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1025"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1026"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1027"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_1028"></s:text>
				</td>				
				<td>
					产出总计
				</td>
			</tr>
			
			<%
				int i = 0;
			%>
			<s:iterator var="stats" value="statsList">
				<tr>
					<td>
						<s:text name="format.date_ymd">
							<s:param value="#stats.time"></s:param>
						</s:text>
					</td>					
					<td>
						<fmt:formatNumber type="number" value="${stats.addTask}" pattern="#,###" />
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addCreatRole}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addAttackMonster}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addMoneyPay}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addMoneyReturn}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addMoneyDouble}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addOpenGiftBox}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addPrestigeRewards}" pattern="#,###" />						
					</td>
					<!-- 			
					<td>
						<fmt:formatNumber type="number" value="${stats.addPackgeExtendReturn}" pattern="#,###" />						
					</td>
					 -->
					<td>
						<fmt:formatNumber type="number" value="${stats.addStudySkill}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addUpgradeSkillBook}" pattern="#,###" />
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addByAdmin}" pattern="#,###" />
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addByReceiveGiftcode}" pattern="#,###" />					
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addPrestigeInvite}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addExploreIntegralExchange}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addByExplore}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addByEmail}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addBuyFromPawnshop}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addSellToPawnshop}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addByLifeSkill}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.battleLuckyEvent}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addByCollect}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addByCollectAndAttackMonster}" pattern="#,###" />						
					</td>		
					<td>
						<fmt:formatNumber type="number" value="${stats.addBuyinMall}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addBySell}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addByBuyback}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.addTask + stats.addCreatRole + stats.addAttackMonster + stats.addMoneyPay
						+ stats.addMoneyReturn + stats.addMoneyDouble + stats.addOpenGiftBox + stats.addPrestigeRewards + stats.addPackgeExtendReturn
						+ stats.addStudySkill + stats.addUpgradeSkillBook + stats.addByAdmin + stats.addByReceiveGiftcode + stats.addPrestigeInvite 
						+ stats.addExploreIntegralExchange + stats.addByExplore + stats.addByEmail + stats.addBuyFromPawnshop + stats.addSellToPawnshop 
						+ stats.addByLifeSkill + stats.battleLuckyEvent + stats.addByCollect + stats.addByCollectAndAttackMonster + stats.addBuyinMall 
						+ stats.addBySell + stats.addByBuyback}" pattern="#,###" />
					</td>
				</tr>
			</s:iterator>			
			<tr>
				<td colspan="61">
					<aldtags:pageTag paraStr="isCommit,T" datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}" para1="pageSize" value1="${pageSize}"/>
				</td>
			</tr>
		</table>
		</s:if>
		
		<s:if test='isCommit=="F"'>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="61" align="center">
					<center>
						金币统计（每天凌晨统计）
					</center>
				</td>
			</tr>
			<tr>
				<td>
					时间
				</td>				
				<td>
					<s:text name="userResourceLog.operation_2002"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_2003"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_2004"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_2005"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_2006"></s:text>
				</td>		
				<td>
					<s:text name="userResourceLog.operation_2007"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_2008"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_2009"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_2010"></s:text>
				</td>				
				<td>
					<s:text name="userResourceLog.operation_2011"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_2012"></s:text>
				</td>				
				<td>
					<s:text name="userResourceLog.operation_2013"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_2014"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_2015"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_2016"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_2017"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation_2018"></s:text>
				</td>
				<td>
					消耗总计
				</td>
				
			</tr>
				<%
					int i = 0;
				%>
				<s:iterator var="stats" value="statsList">
				<tr>
					<td>
						<s:text name="format.date_ymd">
							<s:param value="#stats.time"></s:param>
						</s:text>
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.reduceOpenGiftBox}" pattern="#,###" />
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.reducePackgeExtend}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.reduceStudySkill}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.reduceUpgradeLeaderSkill}" pattern="#,###" />
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.reducePrestigeInvite}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.reduceExploreRefresh}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.reduceExploreAutoRefresh}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.reducePawnshop}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.reduceSellPawnshop}" pattern="#,###" />						
					</td>					
					<td>
						<fmt:formatNumber type="number" value="${stats.reduceCreateLifeBuilder}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.reduceOneclickRefreshTask}" pattern="#,###" />						
					</td>					
					<td>
						<fmt:formatNumber type="number" value="${stats.reduceRefreshFiveTask}" pattern="#,###" />						
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.reduceForcesRelive}" pattern="#,###" />
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.reduceBuyinMall}" pattern="#,###" />
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.reduceBySell}" pattern="#,###" />
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.reduceByBuyback}" pattern="#,###" />
					</td>
					<td>
						<fmt:formatNumber type="number" value="${stats.reduceRecallHero}" pattern="#,###" />
					</td>					
					<td>
						<fmt:formatNumber type="number" value="${stats.reduceOpenGiftBox + stats.reducePackgeExtend + stats.reduceStudySkill + stats.reduceUpgradeLeaderSkill 
						+ stats.reducePrestigeInvite + stats.reduceExploreRefresh + stats.reduceExploreAutoRefresh + stats.reducePawnshop + stats.reduceSellPawnshop 
						+ stats.reduceCreateLifeBuilder + stats.reduceOneclickRefreshTask + stats.reduceRefreshFiveTask + stats.reduceForcesRelive 
						+ stats.reduceBuyinMall + stats.reduceBySell + stats.reduceByBuyback + stats.reduceRecallHero}" pattern="#,###" />
					</td>					
			</tr>
			</s:iterator>
			<tr>
				<td colspan="61">
					<aldtags:pageTag paraStr="isCommit,F" datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}" para1="pageSize" value1="${pageSize}"/>
				</td>
			</tr>
			</table>
		</s:if>
	</body>
</html>