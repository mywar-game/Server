<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title><s:text name="updatepasswordJsp.title"><s:param><font color='red'>${adminUser.adminName}</font></s:param></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript">
		function check(){
			var pwd = document.getElementById("password").value;
			var adminUser = "${adminUser.adminName}";
			if(pwd==""){
				alert("密码不能为空!");
				return false;
			}
			if(pwd.length < 6){
				alert("密码的长度必须大于等于6个字符!");
				return false;
			}
			if(pwd==adminUser){
				alert("用户名和密码不能相同!");
				return false;
			}
			if (!/[a-z]/.test(pwd) && !/[A-Z]/.test(pwd) && !/[0-9]/.test(pwd)){
				alert("请输入至少6位,带数字、字母大小写字符、且不要和用户名相同!");
				return false;
			}
		}	
	</script>
	<body>
		<form action="updatepassword?isCommit=T&Id=${Id}" method="post" onsubmit="return check()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="3">
						<s:text name="updatepasswordJsp.title"><s:param><font color='red'>${adminUser.adminName}</font></s:param></s:text>
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="updatepasswordJsp.newPassword"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input id="password" type="password" name="newPassword" value="${newPassword}" class="textbox" size="22" maxlength="32" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
