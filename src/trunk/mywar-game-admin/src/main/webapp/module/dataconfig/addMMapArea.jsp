<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="addMMapAreaJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<body>
		&nbsp;
		<form action="addMMapArea?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="10">
						<s:text name="addMMapAreaJsp.title"></s:text> &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="mapArea.areaId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="areaId" value="${mapArea.areaId}" class="maxLife" size="10" maxlength="11" readonly="readonly" />
						<s:text name="autoGeneration"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="mapArea.areaName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="areaName" value="${mapArea.areaName}" class="maxLife" size="30" maxlength="32" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="mapArea.areaDesc"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="areaDesc" value="${mapArea.areaDesc}" class="maxLife" size="150" maxlength="1024" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="mapArea.minLevel"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="minLevel" value="${mapArea.minLevel}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="mapArea.maxLevel"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="maxLevel" value="${mapArea.maxLevel}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="mapArea.ppveIds"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="ppveIds" value="${mapArea.ppveIds}" class="maxLife" size="150" maxlength="256" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="mapArea.bigPveIds"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="bigPveIds" value="${mapArea.bigPveIds}" class="maxLife" size="150" maxlength="256" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="mapArea.trimIds"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="trimIds" value="${mapArea.trimIds}" class="maxLife" size="100" maxlength="128" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="mapArea.pveIds"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="pveIds" value="${mapArea.pveIds}" class="maxLife" size="100" maxlength="128" />
					</td>
				</tr>
				
				<tr>
					<td colspan="10" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>