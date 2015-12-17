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
			var dueTime = document.getElementById("dueTime");
			if (dueTime.value.length == 0) {
				alert("封号到期时间不能为空");
				return false;
			}
		}
		</script>
		
		</head>
<body>
<form action="userBan" method="post" onsubmit="return beforeSubmit()">
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
			<td>状态</td>
			<td>
				<s:if test="isBanned==0"><s:text name="未封号"></s:text></s:if>
				<s:else><s:text name="已封号"></s:text></s:else>
			</td>
		</tr>
		<tr>
			<td>输入封号到期时间(解封设置成当天时间)</td>
			<td>
				<input type="text" id="dueTime" name="dueTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" style="width: 100px">
			</td>	
		</tr>
		<tr>
			<s:if test="isBanned==0">
				<td> 
					<input type="submit" value="封号"></a>
				</td>
			</s:if>
			<s:else>
				<td> 
					<input type="submit" value="解封"></a>
				</td>
			</s:else>
		</tr>
	</table>
</form>
</body>

</html>