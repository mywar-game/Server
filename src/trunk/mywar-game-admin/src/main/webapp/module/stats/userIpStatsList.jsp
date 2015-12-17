<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>IP统计</title>
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
	</head>
	<body>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						IP统计
					</center>
				</td>
			</tr>
			<tr>
				<td colspan="10">最后登陆IP相同的玩家角色统计，前100个IP。排序方式为：角色数降序，最近登陆时间降序。更新时间：每天晚上12点</td>
			</tr>
			<tr>
				<td>
					IP
				</td>
				<td>
					角色数量
				</td>
				<td>
					最早
				</td>
				<td>
					最晚
				</td>
			</tr>
			<s:iterator var="stats" value="statsList">
				<tr>
					<td>
						${stats.ip}
					</td>
					<td>
						${stats.roleNum}
					</td>
					<td>
						<s:text name="format.date_ymd">
							<s:param value="#stats.firstDate"></s:param>
						</s:text>
					</td>
					<td>
						<s:text name="format.date_ymd">
							<s:param value="#stats.lastDate"></s:param>
						</s:text>
					</td>
				</tr>
			</s:iterator>
			
		</table>
	</body>
</html>