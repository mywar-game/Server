<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateBBuildingconstraintConstantJsp.title"></s:text></title>
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
		<form action="updateBBuildingconstraintConstant?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<s:text name="updateBBuildingconstraintConstantJsp.title"></s:text> &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="buildingconstraintConstant.buildingConstraintId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="buildingConstraintId" value="${buildingconstraintConstant.buildingConstraintId}" class="maxLife" size="10" maxlength="11" readonly="readonly" />
						<s:text name="cannotEdit"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="buildingconstraintConstant.buildingId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="buildingId" class="select">
							<s:iterator value="buildingIDNameMap">
								<option value="${key}" <s:if test="buildingconstraintConstant.buildingId == key">selected=selected</s:if>>${value}</option>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="buildingconstraintConstant.buildingLevel"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="buildingLevel" value="${buildingconstraintConstant.buildingLevel}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="buildingconstraintConstant.prebuildings"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="prebuildings" value="${buildingconstraintConstant.prebuildings}" class="maxLife" size="10" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="buildingconstraintConstant.description"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="description" value="${buildingconstraintConstant.description}" class="maxLife" size="150" maxlength="255" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="buildingconstraintConstant.costGrain"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="costGrain" value="${buildingconstraintConstant.costGrain}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="buildingconstraintConstant.costMineral"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="costMineral" value="${buildingconstraintConstant.costMineral}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="buildingconstraintConstant.costMoney"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="costMoney" value="${buildingconstraintConstant.costMoney}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="buildingconstraintConstant.coolTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="coolTime" value="${buildingconstraintConstant.coolTime}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
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