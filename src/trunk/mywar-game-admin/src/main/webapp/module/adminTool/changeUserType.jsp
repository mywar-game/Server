<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="changeUserTypeJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
		var erroDescrip = '${erroDescrip}';
		if (erroDescrip != "") {
			alert(erroDescrip);
		}
			
		function isSubmit(){
			if(window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
		}
	</script>
	<body>
		&nbsp;
		<form action="changeUserType?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="6">
						<center>
							<s:text name="changeUserTypeJsp.title"></s:text> &nbsp;
						</center>
					</td>
				</tr>
				<tr>
					<td><s:text name="log.userIdSearch"></s:text><s:text name="colon"></s:text></td>
					<td><input type="text" id="user.userId" name="user.userId" onblur="value=value.replace(/[^\d]/g,'')" /></td>
					<td><s:text name="log.userNameSearch"></s:text><s:text name="colon"></s:text></td>
					<td><input type="text" id="user.userName" name="user.userName" /></td>
					<td><s:text name="log.nameSearch"></s:text><s:text name="colon"></s:text></td>
					<td><input type="text" id="user.name" name="user.name" /></td>
				</tr>
				<tr>
					<td>
						<s:text name="user.type"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="5">
						<select name="type" class="select">
							<s:generator separator="," val="%{getText('user.type_value')}">
								<s:iterator var="str">
									<option value="${str}">
										<s:text name="%{'user.type_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="6" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>