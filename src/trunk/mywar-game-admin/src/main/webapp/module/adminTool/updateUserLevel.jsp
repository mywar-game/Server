<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
		<title><s:text name="updateUserLevelJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		
		function updateUserLevel() {
			var lodoId = document.getElementById('lodoId').value;
			var level = document.getElementById('level').value;
			
			var ajaxobj = new Ajax();
			ajaxobj.url = "updateUserLevel?isCommit=T&lodoId=" + lodoId + "&level=" + level;
			ajaxobj.callback=function(){
				var responseMsg = eval('(' + ajaxobj.gettext() + ')');
				if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="updateUserLevelJsp.title"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="updateUserLevelJsp.title"></s:text></s:param></s:text>');
				}
			}
			ajaxobj.send();
			
		}
		
	</script>
	<body>
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center" >
						<center>
							<s:text name="updateUserLevelJsp.title"></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<s:text name="log.lodoSearch"></s:text><s:text name="colon"></s:text><input type="text" id="lodoId" name="lodoId">
					</td>
					<td colspan="6">
						<s:text name="updateUserLevel.level"></s:text><s:text name="colon"></s:text><input type="text" id="level" name="level">
					</td>
					<td colspan="50">
						<input type="button" onclick="updateUserLevel()" value="<s:text name="update"></s:text>" class="button">
					</td>
				</tr>
			</table>
	</body>
</html>