<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="generateTestAccountJsp.title"></s:text></title>
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
		function check(){
			var generateNum = document.getElementById("generateNum").value;
			var areaId = document.getElementById("areaId").value;
			if(generateNum<=0){
				alert("<s:text name="generateTestAccountJsp.note_generateNumMustGreaterThan0"></s:text>");			
				return false;
			}
			if(areaId<=0 || areaId>10){
				alert("<s:text name="generateTestAccountJsp.note_areaIdMustBetween1And10"></s:text>");			
				return false;
			}
		}
	</script>
  <body>
    <form action="generateTestAccount?isCommit=T" method="post" onsubmit="return check()">
		<table>
			<tr>
				<td>
					<s:text name="generateTestAccountJsp.generateNum"></s:text><s:text name="colon"></s:text>
					<input type="text" id="generateNum" name="generateNum" size="10" onblur="value=value.replace(/[^\d]/g,'')">
				</td>
				<td>
					<s:text name="generateTestAccountJsp.areaId"></s:text><s:text name="colon"></s:text>
					<input type="text" id="areaId" name="areaId" size="10" onblur="value=value.replace(/[^\d]/g,'')">
				</td>
				<td>
					<input type="submit" value="<s:text name="submit"></s:text>" class="button">
				</td>
			</tr>
		</table>
	</form>
	<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
	    	<tr class="border">
				<td class="td_title" colspan="100" align="center">
					<center>
						<s:text name="generateTestAccountJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="user.userId"></s:text>
				</td>
				<td>
					<s:text name="user.userName"></s:text>
				</td>
				<td>
					<s:text name="user.password"></s:text>
				</td>
				<td>
					<s:text name="userAccountInfo.createTime"></s:text>
				</td>
			</tr>

			<s:iterator id="userAccountInfo" value="userAccountList">
				<tr>
				    <td>
						${userAccountInfo.userId}
					</td>
					<td>
						${userAccountInfo.userName}
					</td>
					<td>
						${userAccountInfo.password}
					</td>
					<td>
						${userAccountInfo.createTime}
					</td>
				</tr>	
			</s:iterator>
		</table>
  </body>
</html>
