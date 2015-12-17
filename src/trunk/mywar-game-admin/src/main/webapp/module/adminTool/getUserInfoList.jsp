<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
		<title><s:text name="getUserInfoJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<form action="getUserInfoList?isCommit=T" method="post">
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center" >
						<center>
							<s:text name="getUserInfoJsp.title"></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<s:text name="log.lodoSearch"></s:text><s:text name="colon"></s:text>
						<textarea name="lodoId" id="lodoId" rows="8" cols="80">${lodoId}</textarea>
					</td>
					
					<td>
						<input type="submit" value="<s:text name="search"></s:text>" class="button">
					</td>
				</tr>
			
				<tr>
					<td colspan="5">
						查询总数:${all} &nbsp;&nbsp;成功查询数目:${success} &nbsp;&nbsp;<font color="red">失败查询数目:${fail}</font>
					</td>
					
				</tr>
				<tr>
					<td>
						<s:text name="user.regName"></s:text>
					</td>
					<td>
						<s:text name="user.userName"></s:text>
					</td>
					<td>
						<s:text name="user.channel"></s:text>
					</td>
					<td>
						<s:text name="user.lodoId"></s:text>
					</td>
				</tr>
				<s:iterator var="userSomeInfo" value="infoList">
					<tr>
						<td>
							${userSomeInfo.regZhanghao}
							</td>
						<td>
							${userSomeInfo.userName}
						</td>
						<td>
							${userSomeInfo.channel}
						</td>
						<td>
							${userSomeInfo.userId}
						</td>
					</tr>
				</s:iterator>
			</table>
			<br/><br/>
			<table>
				<font color="red">失败查询用户ID: ${failUserIds}</font>
			</table>
			
		</form>
		<br/>
		说明:<br/>
		1、格式:(100001,100002)
	</body>
</html>