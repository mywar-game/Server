<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="server.closeServer"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<base target="_self">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	
	<script src="../../js/jquery/jquery.min.js"></script>
	<script type="text/javascript" >
	
		function subMit() {
			var notice = document.getElementById("content").value;
			if (notice == "") {
				alert("请输入关服通知！");
				return;
			}
			
			window.returnValue = notice;
			window.close();
		}
		
	</script>	
	<body>
		&nbsp;	
		<form >	
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="server.closeServer"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>			
				<tr>
					<td>
						<s:text name="server.serverId"></s:text><s:text name="colon"></s:text>
					</td>
					<td >
						${gameServerStatus.serverId}
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="server.notice"></s:text><s:text name="colon"></s:text>
					</td>
					<td >
						<textarea size="45" id="content" name="content" rows="8" cols="60" />${gameServerStatus.notice}</textarea>
					</td>	
				</tr>
				<tr>
					<td colspan="5" align="center">
						<input type="button" onclick="subMit()" value="<s:text name='submit'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
