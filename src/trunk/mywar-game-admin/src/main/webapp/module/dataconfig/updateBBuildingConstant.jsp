<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateBBuildingConstantJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
		function isSubmit(){
			if(window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
		}
	</script>
	<body>
		&nbsp;
		<form action="updateBBuildingConstant?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<s:text name="updateBBuildingConstantJsp.title"></s:text> &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="buildingConstant.buildingId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="buildingId" value="${buildingConstant.buildingId}" class="maxLife" size="4" maxlength="11" readonly="readonly"/>
						<s:text name="cannotEdit"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="buildingConstant.buildingName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="buildingName" value="${buildingConstant.buildingName}" class="maxLife" size="10" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="buildingConstant.buildingDesc"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="buildingDesc" value="${buildingConstant.buildingDesc}" class="maxLife" size="100" maxlength="200"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="buildingConstant.maxLevel"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="maxLevel" value="${buildingConstant.maxLevel}" class="maxLife" size="4" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="buildingConstant.displayLevel"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="displayLevel" value="${buildingConstant.displayLevel}" class="maxLife" size="4" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="5" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>