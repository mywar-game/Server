<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="physicslistJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<body>
		<form action="updatepower?isCommit=T&Id=${Id}" method="post">
			<table cellpadding="6" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="6">
						<s:text name="physicslistJsp.title"></s:text>&nbsp;
						<a href="addpysics"><s:text name="addpysicsJsp.title"></s:text></a>
					</td>
				</tr>
				<s:iterator value="map" var="entry">
					<tr>
						<td colspan="6">
							<STRONG>${entry.key}</STRONG>
						</td>
					</tr>
					<s:set var="i" value="1" />
					<tr>
						<s:iterator var="data" value="#entry.value">
							<td>
								<SPAN title="${data.actionName}">
										${data.name}
								</SPAN>&nbsp;
								<a href="javascript:if(confirm('<s:text name="addpysicsJsp.delete_note"><s:param>${data.name}</s:param></s:text>'))location.replace('delpysics?id=${data.id}');">
									<s:text name="addpysicsJsp.delete"></s:text>
								</a>
								&nbsp;
								<a href="editpysics?id=${data.id}">
									<s:text name="addpysicsJsp.update"></s:text>
								</a>
							</td>
							<s:if test="#i%6==0"><%="</tr><tr>"%></s:if>
							<s:set var="i" value="#i+1" />
						</s:iterator>
						<s:set var="i" value="#i-1" />
						<s:if test="#i%6!=0">
							<td colspan="${6-i%6}"></td>
						</s:if>
					</tr>
				</s:iterator>
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
