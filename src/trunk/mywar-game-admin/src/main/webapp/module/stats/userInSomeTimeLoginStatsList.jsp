<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>玩家某段时间内登录统计</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script>  
	</head>
	<body>
		<div>
			<form action="userInSomeTimeLoginStatsList" method="post" >
				<table>
					<tr>
						<td>
							<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<input type="text" id="startDate" name="startDate" value="<s:if test="startDate != null"><s:text name="format.date_time"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){endDate.focus();}})"/>
						</td>
						<td>
							<s:text name="timeTo"></s:text>
						</td>
						<td>
							<input type="text" id="endDate" name="endDate" value="<s:if test="endDate != null"><s:text name="format.date_time"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>				
						</td>
						<td>
							<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						</td>
					</tr>
				</table>
			</form>
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="22" align="center">
						<center>
							玩家某段时间内登录统计
						</center>
					</td>
				</tr>
				<tr>
					<td>
						登录次数
					</td>
					<td>
						人数（有多少人，在指定的时间范围内，有这么多的登录次数）
					</td>
				</tr>


				<s:iterator var="stats" value="statsMap">
					<tr>
						<td>
							${key}
						</td>
						<td>
							${value}
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</body>
</html>