<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.system.service.GameWebService"%>
<%@page import="com.framework.servicemanager.ServiceCacheFactory"%>
<%@page import="com.system.bo.GameWeb"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="sParamConfigListJsp.title"></s:text></title>
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
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script>  
		<script type="text/javascript" src="../../js/ajax.js"></script> 
		<script>
			function check() {
				var date = document.getElementById("date").value;
				var day = document.getElementById("day").value;
				var channel = document.getElementById("pid").value;
				var zone = document.getElementById("zone").value;
				
				if (!date || !day || ((channel == 0) && (zone == -1))) {
					alert("请填写完整信息");
					return false;
				}
				if (day <= 0) {
					alert("天数要大于0");
					return false;
				}
				return true;
			}
			
			function calDay() {
				var date = document.getElementById("date").value;
				var date2 = document.getElementById("endDate1").value;
				if (!date || !date2) {
					return;
				}
				var a = new Date(date).getTime();
				var b= new Date(date2).getTime();
				var c = 60 * 60 * 24 * 1000;
				var res = parseInt((b - a) / c) + 1;
				document.getElementById("day").value = parseInt(res);
			}
		</script>
	</head>
	<body>
		<form action="chouyangList" method="post" onsubmit="return check()">
		<%
			GameWebService gameWebService = ServiceCacheFactory.getServiceCache().getService(GameWebService.class);
			List<GameWeb> gameWebList = gameWebService.findGameWebList();
										  
		%>
		<select name="zone" id="zone" onchange="" class="select">
			<option value="-1"><s:text name="pleaseSelect"></s:text></option>
				<%
					for (int i = 0; i < gameWebList.size(); i++) {
						GameWeb gameweb = gameWebList.get(i);
				%>
			<option value="<%=gameweb.getServerId()%>" >
				<%=gameweb.getServerName()%>
			</option>
				<%}%>
		</select>
			<s:text name="searchPartner"></s:text><s:text name="colon"></s:text>
			<s:select name="channel" id="pid" list="partnerList" listKey="pid" listValue="PName" headerKey="0" headerValue="%{getText('pleaseSelect')}" />
			统计日期<s:text name="colon"></s:text>
			<input type="text" id="date" name="date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${date}" class="Wdate" style="width:100px" onblur="calDay()" />
			截止日期<s:text name="colon"></s:text>
			<input type="text" id="endDate1" name="endDate1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${endDate1}" class="Wdate" style="width:100px" onblur="calDay()" />
			
			天数<s:text name="colon"></s:text>
			<input type="text" id="day" name="day" value="${day}" class="day" style="width:100px" onFocus="this.blur()" />
			<input id="bt1" type="submit" value="<s:text name='submit'></s:text>" class="button" />
			<br/><br/>
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr>
					<td>渠道</td>
					<td>统计日期</td>
					<td>截止日期</td>
					<td>天数</td>
					<td>创建角色数量</td>
					<td>付费总数</td>
					<td>ltv</td>
				</tr>
				<s:if test='list1.size > 0'>
				<s:iterator var="stats" value="list1">
				<tr>
					<td>${channelName}</td>
					<td>${date}</td>
					<td>${stats.endRegDateStr}</td>
					<td>${stats.day}</td>
					<td>${stats.totalCreateUser}</td>
					<td>${stats.totalConsume}</td>
					<td>${stats.ltv}</td>
				</tr>
				</s:iterator>
				</s:if>
			</table>
		</form>
		说明:<br/>
		1、创建角色数目是统计日期当天的数据
		<br/>
		2、付费总数是统计日期那天创建的用户在这个时间范围内（统计日期，截止日期）付费总数
	</body>
</html>