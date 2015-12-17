<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="payUserInfoStatsListJsp.title"></s:text></title>
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
		<form action="userLoginSearchList" method="post" onsubmit="return true;">
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" id="beginDate" name="beginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			<s:text name="timeTo"></s:text>
			<input type="text" id="enDate" name="enDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="userLoginSearchListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="userLoginSearchListJsp.date"></s:text>
				</td>
				<td>
					<s:text name="userLoginSearchListJsp.totalTimes"></s:text>
				</td>
				<td>
					<s:text name="userLoginSearchListJsp.userNumber"></s:text>
				</td>
				<td>
					<s:text name="userLoginSearchListJsp.ipNumber"></s:text>
				</td>
			</tr>
			
			<tr>
				<td>
					${beginDate} 至 ${enDate}
				</td>
				<td>
					${userLoginStats.totalTimes}
				</td>
				<td>
					${userLoginStats.userNumber}
				</td>
				<td>
					${userLoginStats.ipNumber}
				</td>
			</tr>
		</table>
	</body>
</html>