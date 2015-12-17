<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userEquipStatsListJsp.title"></s:text></title>
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
			function changeEquipType() {
				var equipType = document.getElementById("equipType").value;
				var startDate = document.getElementById("startDate").value;
				var endDate = document.getElementById("endDate").value;
				//var pageSize = document.getElementById("pageSize").value;
				window.location.href="userEquipStatsList?equipType=" + equipType + "&startDate=" + startDate + "&endDate=" + endDate;
			}
			function qiuzong() {
				var equipType = document.getElementById("equipType").value;
				var startDate = document.getElementById("startDate").value;
				var endDate = document.getElementById("endDate").value;
				//var pageSize = document.getElementById("pageSize").value;
				window.location.href="userEquipStatsList?equipType=" + equipType + "&startDate=" + startDate + "&endDate=" + endDate + "&total=" + 1;
			}
		</script>
	</head>
	<body>
		<form action="userEquipStatsList" method="post" onsubmit="return check()">
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			<s:text name="timeTo"></s:text>
			<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			
			<%-- <s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" id="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')"> --%>
			<select name="equipType" id="equipType" onchange="return changeEquipType();">
				<option value="0" <s:if test="equipType == 0">selected=selected</s:if>>全部</option>
				<option value="1" <s:if test="equipType == 1">selected=selected</s:if>>白色装备</option>
				<option value="2" <s:if test="equipType == 2">selected=selected</s:if>>绿色装备</option>
				<option value="3" <s:if test="equipType == 3">selected=selected</s:if>>蓝色装备</option>
				<option value="4" <s:if test="equipType == 4">selected=selected</s:if>>紫色装备</option>
				<option value="5" <s:if test="equipType == 5">selected=selected</s:if>>橙色装备</option>
			</select>
			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
			<input type="button" value="求总" class="button" onclick="qiuzong();"/>
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="userEquipStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="userEquipStats.time"></s:text>
				</td>

				<td>
					名称 
				</td>
				
				<td>
					数量
				</td>
			</tr>
			
			<s:iterator var="stats" value="statsList">
				<tr>
					<td>
						<s:if test="total == 1">
							<s:if test="startDate != null">求总(<s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text>&nbsp;&nbsp;<s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text>)</s:if>
							<s:else>全部</s:else>
						</s:if>
						<s:else>
							<s:text name="format.date_ymd">
								<s:param value="#stats.time"></s:param>
							</s:text>
						</s:else>	
					</td>
					<td>
						${stats.name}
					</td>
					
					<td>
						${stats.equipNumber}
					</td>
					
				</tr>
			</s:iterator>
			
			<tr>
				<td colspan="22">
					<aldtags:pageTag paraStr="equipType,${equipType}" datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}" para1="pageSize" value1="${pageSize}"/>
				</td>
			</tr>
		</table>
		<br/>
		说明：
		<br/>
		1、统计当天用户拥有的装备数目
		<br/>
		2、默认只显示前一天数据，可以根据日期，装备品质筛选数据
	</body>
</html>