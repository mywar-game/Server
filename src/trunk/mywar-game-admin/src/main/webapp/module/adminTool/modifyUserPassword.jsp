<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="modifyUserPasswordJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<form action="modifyUserPassword?isCommit=T" method="post" >
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center >
				<tr class="border">
					<td class="td_title" colspan="100" align="center" >
						<center>
							<s:text name="modifyUserPasswordJsp.title"></s:text>
							<font color="red">${erroDescrip}</font>
						</center>
					</td>
				</tr>
				
				<tr>
					<td>
						<s:text name="user.name"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="name" id="name" size="20" value="${name}">
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="modifyUserPasswordJsp.newPassword"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="newPassword" id="newPassword" size="10" value="${newPassword}">
					</td>	
				</tr>
				<tr>
					<td align="center" colspan="100">
						<input type="submit" value="<s:text name="submit"></s:text>" class="button">
						<input type="reset" value="<s:text name="reset"></s:text>" class="button">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>