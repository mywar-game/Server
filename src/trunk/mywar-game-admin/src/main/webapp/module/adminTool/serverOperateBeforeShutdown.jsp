<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>关服前操作</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript">
		function setValue(x){
			document.getElementById("operateNum").value=x;
		}
		var erroDescrip = '${erroDescrip}';
		if (erroDescrip != "") {
			alert(erroDescrip);
		}
	</script>
	<body>
		<form action="serverOperateBeforeShutdown" method="post">
			<table>
				<tr>
					<td>
						<input id="operateNum" type="hidden" name="operateNum">
						<input type="submit" value="踢出在线玩家" class="button" onclick="setValue(1)">
					</td>
					<td>
						<input type="submit" value="同步数据" class="button" onclick="setValue(2)">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>