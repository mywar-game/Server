<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script type="text/javascript" src="../../js/json2.js"></script>
		
		<script src="../../js/jquery/ui/jquery.ui.core.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.widget.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.position.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.autocomplete.js"></script>
		<script>
			var success = '${flag}';
			if (success && success == "true") {
				alert("跳过成功！");		
			} else if (success && success == "false") {
			
			}
			function submitStep() {
				
				var lodoId = document.getElementById("lodoId").value;
				var userName = document.getElementById("userName").value;
				
				if (!lodoId && !userName) {
					alert("请输入查询条件");
					return false;
				} else {
					return true;
				}
			}
		</script>
	</head>
	<body>
		<form name="recordUserStep" action="recordUserStep?isCommit=T" method="post" onsubmit="return submitStep();">
		<table>
			<tr>
					<td><s:text name="log.lodoSearch"></td>
					<td colspan="6">
						</s:text><s:text name="colon"></s:text><input type="text" name="lodoId" id="lodoId" size="10" value="${lodoId}">
					</td>
					<td>玩家昵称：</td>
					<td colspan="6">
						<input type="text" name="userName" id="userName" size="10" value="${userName}">
					</td>
					<td colspan="50">
						<input type="submit" value="修改" class="button">
					</td>
			</tr>
		</table>
	</body>
</html>