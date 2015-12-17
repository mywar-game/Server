<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="updateSGlobalParamJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<body>
		<form action="updateSGlobalParam?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5" align="center">
						<s:text name="updateSGlobalParamJsp.title"></s:text> &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				
				<tr>
					<td>
						<s:text name="globalParam.globalKey"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							${globalParam.globalKey}
							<input type="hidden" name="globalKey" value="${globalParam.globalKey}" class="maxLife" size="20" maxlength="50" readonly/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="globalParam.globalValue"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="globalValue" value="${globalParam.globalValue}" class="maxLife" size="150" maxlength="1000" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="globalParam.golobalTransportPat"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							${globalParam.golobalTransportPat}
							<input type="hidden" name="golobalTransportPat" value="${globalParam.golobalTransportPat}" class="maxLife" size="100" maxlength="1000" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="globalParam.globalDesc"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							${globalParam.globalDesc}
							<input type="hidden" name="globalDesc" value="${globalParam.globalDesc}" class="maxLife" size="100" maxlength="1000" />
					</td>
				</tr>
				
				<tr>
					<td colspan="4" align="left">
						<input type="submit" value="<s:text name="submit"></s:text>" class="button" />
						<input type="reset" value="<s:text name="reset"></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>