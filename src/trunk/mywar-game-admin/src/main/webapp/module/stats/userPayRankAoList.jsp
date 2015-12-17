<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="payRankAoList.title"></s:text></title>
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
			<form action="userPayRankAoList" method="post" onsubmit="return check()">
				<input type="hidden" name="channel" id="channel" value="${channel}"/>
				<input type="hidden" name="sysNum" id="sysNum" value="${sysNum}"/>
				<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
				<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
				<s:text name="timeTo"></s:text>
				<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
				<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
			</form>
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="22" align="center">
						<center>
							<s:text name="payRankAoList.title"></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="payRank.rank"></s:text>
					</td>
					<td>
						<s:text name="payRank.userId"></s:text>
					</td>
					<td>
						<s:text name="payRank.userName"></s:text>
					</td>
					<td>
						<s:text name="payRank.server"></s:text>
					</td>
					<td>
						<s:text name="payRank.userLevel"></s:text>
					</td>
					<td>
						<s:text name="payRank.amount"></s:text>
					</td>
					<td>
						<s:text name="payRank.payTimes"></s:text>
					</td>
					<td>
						<s:text name="payRank.lastLoginTime"></s:text>
					</td>
				</tr>
				<s:iterator var="payRank" value="rankList">
					<tr>
						<td>
							${payRank.rank}
						</td>
						<td>
							${payRank.userId}
						</td>
						<td>
							${payRank.userName}
						</td>
						<td>
							${payRank.sysNum}
						</td>
						<td>
							${payRank.userLevel}
						</td>
						<td>
							${payRank.amount}
						</td>
						<td>
							${payRank.payTimes}
						</td>
						<td>
							${payRank.lastLoginTime}
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="22">
						<aldtags:pageTag para1="channel" value1="${channel}" para2="sysNum" value2="${sysNum}" datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}"/>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>