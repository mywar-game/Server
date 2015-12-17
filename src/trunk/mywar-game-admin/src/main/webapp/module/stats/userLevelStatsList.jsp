<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userLevelStatsListJsp.title"></s:text></title>
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
		<form action="userLevelStatsList" method="post" onsubmit="return check()">
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			<s:text name="timeTo"></s:text>
			<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			
			<s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="userLevelStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="userLevelStats.time"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index0"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index1"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index2"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index3"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index4"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index5"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index6"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index7"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index8"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index9"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index10"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index11"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index12"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index13"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index14"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index15"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index16"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index17"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index18"></s:text>
				</td>
				<td>
					<s:text name="userLevelStats.index19"></s:text>
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
						${stats.index0}
					</td>
					<td>
						${stats.index1}
					</td>
					<td>
						${stats.index2}
					</td>
					<td>
						${stats.index3}
					</td>
					<td>
						${stats.index4}
					</td>
					<td>
						${stats.index5}
					</td>
					<td>
						${stats.index6}
					</td>
					<td>
						${stats.index7}
					</td>
					<td>
						${stats.index8}
					</td>
					<td>
						${stats.index9}
					</td>
					<td>
						${stats.index10}
					</td>
					<td>
						${stats.index11}
					</td>
					<td>
						${stats.index12}
					</td>
					<td>
						${stats.index13}
					</td>
					<td>
						${stats.index14}
					</td>
					<td>
						${stats.index15}
					</td>
					<td>
						${stats.index16}
					</td>
					<td>
						${stats.index17}
					</td>
					<td>
						${stats.index18}
					</td>
					<td>
						${stats.index19}
					</td>
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