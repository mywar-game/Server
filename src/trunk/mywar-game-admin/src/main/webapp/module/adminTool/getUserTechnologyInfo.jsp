<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="getUserTechnologyInfoJsp.title"><s:param>${user.userName}</s:param><s:param>${user.name}</s:param></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript">
		var erroDescrip = '${erroDescrip}';
		if (erroDescrip != "") {
			alert(erroDescrip);
		}
	</script>
	<body>
		<form action="getUserTechnologyInfo" method="post">
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center">
						<center>
							<s:text name="getUserTechnologyInfoJsp.title"><s:param>${user.userName}</s:param><s:param>${user.name}</s:param></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="user.name"></s:text><s:text name="colon"></s:text>
						<input type="text" id="name" name="name" size="10">
					</td>
					<td colspan="50">
						<input type="submit" value="<s:text name="search"></s:text>" class="button">
					</td>
				</tr>
		
				<tr>
					<td><s:text name="userTechnologyInfo.technologyName"></s:text></td>
					<td><s:text name="userTechnologyInfo.level"></s:text></td>
					<td><s:text name="userTechnologyInfo.state"></s:text></td>
					<td><s:text name="userTechnologyInfo.startTime"></s:text></td>
					<td><s:text name="userTechnologyInfo.endTime"></s:text></td>
				</tr>
				<s:iterator var="userTechnologyInfo" value="userTechnologyInfoList">
					<tr>
						<td>
							${userTechnologyInfo.technologyName}
						</td>
						<td>
							${userTechnologyInfo.level}
						</td>
						<td>
							<s:text name="%{'userTechnologyInfo.state_'+#userTechnologyInfo.state}"></s:text>
						</td>
						<td>
							${userTechnologyInfo.startTime}
						</td>
						<td>
							${userTechnologyInfo.endTime}
						</td>
					</tr>
				</s:iterator>
			</table>
		</form>
	</body>
</html>