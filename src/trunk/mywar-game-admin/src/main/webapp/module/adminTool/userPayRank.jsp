<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>充值排行</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<form action="userPayRank" method="post">
		<table>
			<tr>
				<td>
					<s:text name="pageSize"></s:text><s:text name="colon"></s:text>
				</td>
				<td>
					<input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
				</td>
			</tr>
		</table>
	</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						充值排行
					</center>
				</td>
			</tr>
			<tr>
				<td>
					用户名
				</td>
				<td>
					用户名
				</td>
				<td>
					角色名
				</td>
				<td>
					总充值数
				</td>
				<td>
					当前钻石数
				</td>
				<td>
					当前等级
				</td>
				<td>
					当前关卡
				</td>
			</tr>
			<s:iterator var="info" value="list">
				<tr>
					<td>
						${info.userId}
					</td>
					<td>
						${info.userName}
					</td>
					<td>
						${info.name}
					</td>
					<td>
						${info.payAmount}
					</td>
					<td>
						${info.golden}
					</td>
					<td>
						${info.level}
					</td>
					<td>
						<a href="../dataconfig/baPveConstantList?bigId=<s:property value='#info.unlockPosition.split("_")[0]' />&smallId=<s:property value='#info.unlockPosition.split("_")[1]' />">
							<s:property value="baPveIdNamesMap[#info.unlockPosition.replace('_',',')]"/>
						</a>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="10">
					<aldtags:pageTag paraStr="pageSize,${pageSize}"/>
				</td>
			</tr>
		</table>
	</body>
</html>