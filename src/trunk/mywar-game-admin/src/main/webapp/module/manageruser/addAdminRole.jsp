<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加角色</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<body>
		&nbsp;
		<form action="addAdminRole?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							添加角色&nbsp;
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						角色名<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="roleName" value="${adminRole.roleName}" class="maxLife" size="128"/>
					</td>
				</tr>
				<tr>
					<td>
						描述<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="description" value="${adminRole.description}" class="maxLife" size="128"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="5" align="center">
						<input type="submit" value="下一步" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>