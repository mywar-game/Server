<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="addadminuserJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function check(){
			var pwd = document.getElementById("password").value;
			var adminUser = document.getElementById("adminUser").value;
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
		<form action="addadminuser?isCommit=T" method="post" onsubmit="return check()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%"
				align=center>
				<tr class="border">
					<td class="td_title" colspan="3">
						<s:text name="addadminuserJsp.title"></s:text> &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminUser.adminName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input id="adminUser" type="text" name="newName" value="${newName}" class="textbox" size="20" maxlength="20" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminUser.password"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input id="password" type="text" name="newPwd" value="${newPwd}" class="textbox" size="22" maxlength="32" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminUser.description"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="newDesc" value="${newDesc}" class="textbox" size="20" maxlength="512" />
					</td>
				</tr>
				<tr>
					<td>
						角色<s:text name="colon"></s:text>
					</td>
					<td>
						<s:select list="roleIdAndNameMap" id="newRoleId" name="newRoleId" cssClass="select" theme="simple" headerKey="" headerValue="%{getText('pleaseSelect')}" value="newRoleId" ></s:select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminUser.dueTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select id="dueTime" name="dueTime" class="select">
							<s:generator separator="," val="%{getText('adminUser.dueTime_values')}">
								<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
								<s:iterator var="str">
									<option value="${str}" <s:if test="dueTime == #str">selected=selected</s:if>>
										<s:text name="%{'adminUser.dueTime_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
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
