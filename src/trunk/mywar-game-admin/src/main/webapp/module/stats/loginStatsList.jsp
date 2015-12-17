<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="loginStatsListJsp.title"></s:text></title>
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
						<s:text name="loginStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="channel"></s:text>
				</td>
				<td>
					<s:text name="login.loginNum"></s:text>
				</td>
				<td>
					<s:text name="login.loginIpNum"></s:text>
				</td>
				<td>
					<s:text name="login.newReg"></s:text>
				</td>
				<td>
					<s:text name="login.newUser"></s:text>
				</td>
				<td>
					<s:text name="login.oldLoginNum"></s:text>
				</td>
				<td>
					<s:text name="channelDetail"></s:text>
				</td>
			</tr>
			<s:iterator value="statsMap">
				<tr>
					<s:iterator>
						<td>
							${key}
						</td>
						<td>
							<s:property value="value.split(',')[0]"/>
						</td>
						<td>
							<s:property value="value.split(',')[1]"/>
						</td>
						<td>
							<s:property value="value.split(',')[2]"/>
						</td>
						<td>
							<s:property value="value.split(',')[3]"/>
						</td>
						<td>
							<s:property value="value.split(',')[4]"/>
						</td>
						<td>
							<a href="#" ><s:text name="detail"></s:text></a>
						</td>
					</s:iterator>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>