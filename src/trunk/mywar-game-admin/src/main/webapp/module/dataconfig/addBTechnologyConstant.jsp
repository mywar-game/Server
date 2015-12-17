<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="addBTechnologyConstantJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<body>
		&nbsp;
		<form action="addBTechnologyConstant?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<s:text name="addBTechnologyConstantJsp.title"></s:text> &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="technologyConstant.technologyConstantId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="technologyConstantId" value="${technologyConstant.technologyConstantId}" class="maxLife" size="10" maxlength="11" readonly="readonly"/>
						<s:text name="autoGeneration"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="technologyConstant.technologyId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="technologyId" value="${technologyConstant.technologyId}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="technologyConstant.technologyName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="technologyName" value="${technologyConstant.technologyName}" class="maxLife" size="60" maxlength="64" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="technologyConstant.technologyDesc"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="technologyDesc" value="${technologyConstant.technologyDesc}" class="maxLife" size="150" maxlength="1024" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="technologyConstant.effectType"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="effectType">
							<s:generator separator="," val="%{getText('effectType_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="technologyConstant.effectType == #str">selected=selected</s:if>>
										<s:text name="%{'effectType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="technologyConstant.level"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="level" value="${technologyConstant.level}" class="maxLife" size="10" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="technologyConstant.valueType"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="valueType">
							<s:generator separator="," val="%{getText('technologyConstant.valueType_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="technologyConstant.valueType == #str">selected=selected</s:if>>
										<s:text name="%{'technologyConstant.valueType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="technologyConstant.value"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="value" value="${technologyConstant.value}" class="maxLife" size="4" maxlength="4" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="technologyConstant.needGold"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="needGold" value="${technologyConstant.needGold}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="technologyConstant.coolingTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="coolingTime" value="${technologyConstant.coolingTime}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
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