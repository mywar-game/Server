<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="pveStatsListJsp.title"></s:text></title>
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
			function pveCheck() {
				var startDate = document.getElementById("startDate").value;
				var endDate = document.getElementById("endDate").value;
				var guanQia = document.getElementById("guanQia").value;
				window.location.href="pveStatsList?startDate=" + startDate + "&endDate=" + endDate + "&guanQia=" + guanQia;
			}
		</script>
	</head>
	<body>
		<div>
			<form action="pveStatsList" method="post" onsubmit="return check()">
				<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
				<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
				<s:text name="timeTo"></s:text>
				<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
				<select name="guanQia" id="guanQia" onchange="return pveCheck();"> 
					
					<option value="0" <s:if test="guanQia == 0">selected=selected</s:if>>
						全部
					</option>
					<option value="1" <s:if test="guanQia == 1">selected=selected</s:if>>
						mark篇
					</option>
					<option value="2" <s:if test="guanQia == 2">selected=selected</s:if>>
						索隆篇
					</option>
					
					<option value="3" <s:if test="guanQia == 3">selected=selected</s:if>>
						盖伦篇
					</option>
					<option value="4" <s:if test="guanQia == 4">selected=selected</s:if>>
						阿隆篇
					</option>
					<option value="5" <s:if test="guanQia == 5">selected=selected</s:if>>
						精英副本
					</option>	
					<option value="6" <s:if test="guanQia == 6">selected=selected</s:if>>
						活动副本
					</option>	
					<option value="7" <s:if test="guanQia == 7">selected=selected</s:if>>
						试炼塔
					</option>				
				</select>
				<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
			</form>
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="22" align="center">
						<center>
							<s:text name="pveStatsListJsp.title"></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="pveStatsListJsp.sceneName"></s:text>
					</td>
					<td>
						<s:text name="pveStatsListJsp.forcesName"></s:text>
					</td>
					<td>
						<s:text name="pveStatsListJsp.atkUserNum"></s:text>
					</td>
					<td>
						<s:text name="pveStatsListJsp.atkNum"></s:text>
					</td>
					<td>
						<s:text name="pveStatsListJsp.winNum"></s:text>
					</td>
					<td>
						<s:text name="pveStatsListJsp.failNum"></s:text>
					</td>
					<td>
						<s:text name="pveStatsListJsp.drawNum"></s:text>
					</td>
				</tr>
				<s:iterator var="scene" value="sceneList">
					<s:set var="forcesList" value="map[#scene.sceneId]"></s:set>
					<tr>
						<td rowspan="${fn:length(forcesList)+1}">
							${scene.sceneName}
						</td>
						<s:iterator var="forces" value="forcesList">
							<tr>
								<td>
									${forces.forcesName}
								</td>
								<s:if test="statsMap[#forces.forcesId]==null">
									<td>
										0
									</td>
									<td>
										0
									</td>
									<td>
										0
									</td>
									<td>
										0
									</td>
									<td>
										0
									</td>
								</s:if>
								<s:else>
									<td>
										${statsMap[forces.forcesId].atkUserNum}
									</td>
									<td>
										${statsMap[forces.forcesId].atkNum}
									</td>
									<td>
										${statsMap[forces.forcesId].winNum}
									</td>
									<td>
										${statsMap[forces.forcesId].failNum}
									</td>
									<td>
										${statsMap[forces.forcesId].drawNum}
									</td>
								</s:else>
							</tr>
						</s:iterator>	
					</tr>
				</s:iterator>
				<tr>
					<td colspan="22">
						<aldtags:pageTag para1="startDate" value1="${startDate}" para2="endDate" value2="${endDate}" para3="pageSize" value3="${pageSize}"/>
					</td>
				</tr>
<!--				<tr>-->
<!--					<td colspan="22">-->
<!--						通关成功率（=首次通关次数/首次闯关次数）-->
<!--					</td>-->
<!--				</tr>-->
			</table>
		</div>
	</body>
</html>