<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title><s:text name="updatepowerJsp.title"><s:param><font color='red'>${adminUser.adminName}</font></s:param></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript">
			function checkall(id) {
				var names = document.getElementsByName(id);
				var len = names.length;
				if (len > 0) {
					var i = 0;
					for (i = 0; i < len; i++)
						names[i].checked = true;
		
				}
			}
			function uncheckall(id) {
				var names = document.getElementsByName(id);
				var len = names.length;
				if (len > 0) {
					var i = 0;
					for (i = 0; i < len; i++)
						names[i].checked = false;
				}
			}
		
			function checkallmodule(id) {
				var names = document.getElementById(id).getElementsByTagName("input");
				var len = names.length;
				if (len > 0) {
					var i = 0;
					for (i = 0; i < len; i++)
						names[i].checked = true;
		
				}
			}
			function uncheckallmodule(id) {
				var names = document.getElementById(id).getElementsByTagName("input");
				var len = names.length;
				if (len > 0) {
					var i = 0;
					for (i = 0; i < len; i++)
						names[i].checked = false;
				}
			}
	</script>
	</head>
	<body>
		<form action="updatepower?isCommit=T&Id=${Id}" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="6">
						<s:text name="updatepowerJsp.title"><s:param><font color='red'>${adminUser.adminName}</font></s:param></s:text>&nbsp;&nbsp;
						<a href="javascript:checkall('powerList');"><s:text name="updatepowerJsp.checkallmodule"></s:text></a>&nbsp;&nbsp;
						<a href="javascript:uncheckall('powerList');"><s:text name="updatepowerJsp.uncheckallmodule"></s:text></a>
					</td>
				</tr>
				<s:iterator value="map" var="entry">

					<tr>
						<td colspan="6">
							<STRONG>
								${entry.key}
							</STRONG>&nbsp;&nbsp;
							<a href="javascript:checkallmodule('${entry.key}');"><s:text name="updatepowerJsp.checkallmodule"></s:text></a>&nbsp;&nbsp;
							<a href="javascript:uncheckallmodule('${entry.key}');"><s:text name="updatepowerJsp.uncheckallmodule"></s:text></a>
						</td>
					</tr>
					<s:set var="i" value="1" />
					<s:set var="module" value="#entry.key" />

					<%
						out.print("<span id='");
					%>${entry.key}<%
						out.print("'>");
					%>
					<tr>
						<s:iterator var="data" value="#entry.value">
							<TD>
								<input name="powerList" type="checkbox" class="checkbox" value="${data.powerId}" id="${entry.key}" <s:if test="adminUser.powerString.substring(#data.powerId-1,#data.powerId) == 1">checked</s:if>>
								${data.name}
							</td>
							<s:if test="#i%6==0"><%="</tr><tr>"%></s:if>
							<s:set var="i" value="#i+1" />
						</s:iterator>
						<s:set var="i" value="#i-1" />
						<s:if test="#i%6!=0">
							<td colspan="${6-i%6}"></td>
						</s:if>
					</tr>

					<%
						out.print("</span>");
					%>
				</s:iterator>
				<tr>
					<td colspan="6" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>"
							class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>"
							class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
