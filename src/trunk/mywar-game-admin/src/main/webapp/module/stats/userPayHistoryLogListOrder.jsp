<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userPayHistoryLogListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="../../js/json.js"></script>
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script>
	</head>
	<script type="text/javascript">
		function dateMust(){
				var startDate = document.getElementById("startDate").value;
				var endDate = document.getElementById("endDate").value;
				//alert("startDate "+startDate);
				//alert("endDate "+endDate);
				if(startDate =="" || endDate == ""){
					alert("请选择查询时间段");
					return false;
				}
				return true;
			}
	</script>
	<body>
		<form action="userPayHistoryLogListOrder?isCommit=T" method="post" onsubmit="return dateMust()">
			<table>
				<tr>
					<td>
						<s:text name="log.lodoSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="lodoId" name="lodoId" value="${lodoId}"/>
					</td>
					<td>
						<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<input type="text" id="startDate" name="startDate" value="<s:if test="startDate != null"><s:text name="format.date_time"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){endDate.focus();}})"/>
						<s:text name="timeTo"></s:text>
						<input type="text" id="endDate" name="endDate" value="<s:if test="endDate != null"><s:text name="format.date_time"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}'})"/>
					</td>
					<td>
						<input type="submit" value="<s:text name="search"></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>

		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center >
		<tr class="border">
			<td class="td_title" colspan="10" align="center">
				<center>
					<s:text name="userPayHistoryLogListJsp.title"></s:text>
				</center>
			</td>
		</tr>
			<tr>
				<td>
					<s:text name="userPayHistoryLog.payHistoryId"></s:text>
				</td>
				<td>
					<s:text name="userPayHistoryLog.userId"></s:text>
				</td>
				<td>
					<s:text name="userPayHistoryLog.userName"></s:text>
				</td>
				<td>
					<s:text name="userPayHistoryLog.lodoId"></s:text>
				</td>
				<td>
					<s:text name="userPayHistoryLog.orderId"></s:text>
				</td>
				<td>
					<s:text name="userPayHistoryLog.channel"></s:text>
				</td>
				<td>
					<s:text name="userPayHistoryLog.amount"></s:text>
				</td>
				<td>
					<s:text name="userPayHistoryLog.payTime"></s:text>
				</td>
			</tr>
			<s:iterator var="paymentLog" value="userPayHistoryList" status="sta">
				<tr>
					<td>${sta.index+1}</td>
					<td>${paymentLog.userId}</td>
					<td>${paymentLog.userName}</td>
					<td>${paymentLog.lodoId}</td>
					<td>${paymentLog.orderId}</td>
					<td>${paymentLog.channel}</td>
					<td>${paymentLog.amount}</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#paymentLog.createdTime"></s:param>
						</s:text>
					</td>
				</tr>
			</s:iterator>
			
			<tr>
				<td colspan="100">
					<aldtags:pageTag paraStr="isCommit,T,lodoId,${lodoId}" datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}"/>
				</td>
			</tr>
		</table>
	</body>
</html>