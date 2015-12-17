<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
		<title><s:text name="getUserMissionJsp.title"><s:param>${user.userName}</s:param><s:param>${user.name}</s:param></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
  </script>
  </head>
 <body>
		<form action="updateHeroLevel" method="post" >
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center">
						<center>
							<s:text name="updateHeroLevel.tile"></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<s:text name="updateHeroLevel.userId"></s:text><s:text name="colon"></s:text><input type="text" id="userId" name="userId" size="10">
					</td>
					<td colspan="4">
						<s:text name="updateHeroLevel.userHeroId"></s:text><s:text name="colon"></s:text><input type="text" id="userHeroId" name="userHeroId" size="10">
					</td>
					<td colspan="4">
						<s:text name="updateHeroLevel.heroLevel"></s:text><s:text name="colon"></s:text><input type="text" id="heroLevel" name="heroLevel" size="10">
					</td>
					<td colspan="50">
						<input type="button" value="<s:text name="update"></s:text>" class="button" onclick="submit()">
					</td>
				</tr>
		
			</table>
		</form>
	</body>
</html>
