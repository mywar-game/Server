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
		<script type="text/javascript">
	
			function add() {
				window.location.href = "userPayHistoryLogListOrder";
			}
		</script>
	</head>
	
	<body>
		<input type="submit" value="<s:text name='用户充值明细'></s:text>" class="button" onclick=add(); />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center >
			<tr class="border">
				<td class="td_title" colspan="20" align="center">
					<center>
						充值用户数据综合查询
					</center>
				</td>
			</tr>
			<tr>
				<td>
				   合作商ID
				</td>
				<td>
					合作商
				</td>
				<td>
					服务器ID
				</td>
				<td>
					用户ID
				</td>
				<td>
					用户名
				</td>
				<td>
					充值总金额
				</td>
				
				<td>
					最后充值时间
				</td>
			</tr>
			
			<s:iterator var="paymentLog" value="userPayHistoryListOrder" status="sta">
				<tr>

				    <td>${paymentLog.partnerId}</td>
			        <td>${paymentLog.partnerName}</td>
			        <td>${paymentLog.serverId}</td>
					<td>${paymentLog.userId}</td>
					<td>${paymentLog.userName}</td>
					<td>${paymentLog.totalAmount}</td>
			   
					<td>
						<s:text name="format.date_time">
							<s:param value="#paymentLog.lastCreateTime"></s:param>
						</s:text>
					</td> 
				</tr>
			</s:iterator>
			
			<tr>
				<td colspan="22">
					<aldtags:pageTag datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}" para1="pageSize" value1="${pageSize}" />
				</td>
			</tr>
		</table>
	</body>
</html>