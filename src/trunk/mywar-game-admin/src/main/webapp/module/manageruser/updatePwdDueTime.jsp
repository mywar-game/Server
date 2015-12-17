<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title><s:text name="updatePwdDueTimeJsp.title"><s:param><font color='red'>${adminUser.adminName}</font></s:param></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		

	</head>
	<body>
		<form action="updatePwdDueTime?isCommit=T&Id=${Id}" method="post">
			<table>
				<tr class="border">
					<td class="td_title" colspan="3">
						<s:text name="updatePwdDueTimeJsp.title"><s:param><font color='red'>${adminUser.adminName}</font></s:param></s:text>
						<font color='red'>${erroDescrip}</font>
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
