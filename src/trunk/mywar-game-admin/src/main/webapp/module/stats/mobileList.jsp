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
	
	<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
		<tr class="border">
			<td class="td_title" colspan="22" align="center">
				<center>
					<s:text name="mobile.title"></s:text>
				</center>
			</td>
		</tr>
		<tr>
			<td>
				<s:text name="mobile.name" />
			</td>
			<td> 
				<s:text name="mobile.count" />
			</td>
		</tr>
		<s:iterator value="mobileMap">
			
			<tr>
				<td>${key}</td>
				<td>${value}</td>
			</tr> 
		</s:iterator>
	</table>
	</body>
</html>