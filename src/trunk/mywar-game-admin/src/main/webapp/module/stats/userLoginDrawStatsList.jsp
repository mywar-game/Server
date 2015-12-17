<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
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
		<div>
			<form action="userLoginDrawStatsList" method="post">
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
							大富翁统计（每天凌晨统计）
						</center>
					</td>
				</tr>
				<tr>
					<td>
						日期
					</td>
					<td>
						消耗钻石人数
					</td>
					<td>
						总参与人数
					</td>
					<td>
						消耗钻石次数
					</td>
					<td>
						总次数
					</td>
				</tr>


				<s:iterator var="stats" value="userLoginDrawStatsList">
					<tr>
						<td>
							<s:text name="format.date_ymd">
									<s:param value="#stats.time"></s:param>
							</s:text>
						</td>
						<td>
							${stats.diamondUsePeopleCount}
						</td>
						<td>
							${stats.totalPeopleCount}
						</td>
						<td>
							${stats.diamondUseCount}
						</td>
						
						<td>
							${stats.totalCount}
						</td>
					</tr>
				</s:iterator>
				
				<tr>
					<td colspan="22">
						<aldtags:pageTag datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}" para1="pageSize" value1="${pageSize}"/>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>