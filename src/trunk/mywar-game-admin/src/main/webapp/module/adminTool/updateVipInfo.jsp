<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
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
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
		<script type="text/javascript">
			function beforeSubmit() {
				var vipLevel = document.getElementById("vipLevel");
				if (vipLevel.value.length == 0) {
					alert("VIP 等级不能为空");
					return false;
				}
				
			}
		</script>
		
		</head>
<body>
<form action="updateVip" method="post" onsubmit="return beforeSubmit(0)">
	<table>
		<input type="hidden" name="userId" value="${user.userId}">
		<tr>
			<td>用户 ID</td>
			<td>${user.userId}</td>
		</tr>
		<tr>	
			<td>用户名</td>
			<td>${user.userName}</td>
		</tr>
		<tr>
			<td>VIP 等级</td>
			<td>${vipLevel}</td>
		</tr>
		<tr>
			<td>新的 VIP 等级</td>
			<td><input type="text" id="vipLevel" name="vipLevel"></td>
		</tr>
		<tr>
			<td><input type="submit" value="提交"></a></td>
		</tr>
	</table>
</form>
</body>

</html>