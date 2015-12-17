<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="generateRegionalMapJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript">
		var erroDescrip = '${erroDescrip}';
		if (erroDescrip != "") {
			alert(erroDescrip);
		}
		function check(){
			var generateNum = document.getElementById("generateNum").value;
			if(generateNum<=0){
				alert("<s:text name="generateRegionalMapJsp.num"></s:text>");			
				return false;
			}
		}
	</script>
	<body>
		<form action="generateRegionalMap?isCommit=T" method="post" onsubmit="return check()">
			<table>
				<tr>
					<td>
						<s:text name="generateRegionalMapJsp.numMustGreateThanTwo"></s:text><s:text name="colon"></s:text><input type="text" id="generateNum" name="generateNum" size="10" onblur="value=value.replace(/[^\d]/g,'')">
					</td>
					<td>
						<input type="submit" value="<s:text name="submit"></s:text>" class="button">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>