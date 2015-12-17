<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加宠物常量</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
		var erroDescrip = '${erroDescrip}';
		if (erroDescrip != "") {
			alert(erroDescrip);
		}
	</script>
	<body>
		&nbsp;
		<form action="addPetConstant?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							添加宠物常量 &nbsp;
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="petConstant.petId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="petId" value="${petConstant.petId}" class="maxLife" size="11" maxlength="11" />
					</td>
					<td>
						<s:text name="petConstant.name"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="name" value="${petConstant.name}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="petConstant.description"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<input type="text" name="description" value="${petConstant.description}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="petConstant.quality"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="quality" class="select">
							<s:generator separator="," val="%{getText('petConstant.quality_values')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="petConstant.quality == #str">selected=selected</s:if>>
										<s:text name="%{'petConstant.quality_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
					<td>
						<s:text name="petConstant.stamina"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="stamina" value="${petConstant.stamina}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="petConstant.attackSpeed"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="attackSpeed" value="${petConstant.attackSpeed}" class="maxLife" size="11" maxlength="11" />
					</td>
					<td>
						<s:text name="petConstant.attackSpeedAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="attackSpeedAdd" value="${petConstant.attackSpeedAdd}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="petConstant.lowAttack"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="lowAttack" value="${petConstant.lowAttack}" class="maxLife" size="11" maxlength="11" />
					</td>
					<td>
						<s:text name="petConstant.lowAttackAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="lowAttackAdd" value="${petConstant.lowAttackAdd}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="petConstant.highAttack"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="highAttack" value="${petConstant.highAttack}" class="maxLife" size="11" maxlength="11" />
					</td>
					<td>
						<s:text name="petConstant.highAttackAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="highAttackAdd" value="${petConstant.highAttackAdd}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="petConstant.cirtRate"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="cirtRate" value="${petConstant.cirtRate}" class="maxLife" size="11" maxlength="11" />
					</td>
					<td>
						<s:text name="petConstant.cirtRateAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="cirtRateAdd" value="${petConstant.cirtRateAdd}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="petConstant.hitRate"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="hitRate" value="${petConstant.hitRate}" class="maxLife" size="11" maxlength="11" />
					</td>
					<td>
						<s:text name="petConstant.hitRateAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="hitRateAdd" value="${petConstant.hitRateAdd}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="petConstant.moveSpeed"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="moveSpeed" value="${petConstant.moveSpeed}" class="maxLife" size="11" maxlength="11" />
					</td>
					<td>
						<s:text name="petConstant.attackScope"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="attackScope" value="${petConstant.attackScope}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="petConstant.activeAttackScope"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="activeAttackScope" value="${petConstant.activeAttackScope}" class="maxLife" size="11" maxlength="11" />
					</td>
					<td>
						<s:text name="petConstant.life"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="life" value="${petConstant.life}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="petConstant.buyType"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="buyType" class="select">
							<s:generator separator="," val="%{getText('petConstant.buyType_values')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="petConstant.buyType == #str">selected=selected</s:if>>
										<s:text name="%{'petConstant.buyType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
					<td>
						<s:text name="petConstant.price"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="price" value="${petConstant.price}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="petConstant.unlockLevel"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="unlockLevel" value="${petConstant.unlockLevel}" class="maxLife" size="11" maxlength="11" />
					</td>
					<td>
						<s:text name="petConstant.unlockGolden"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="unlockGolden" value="${petConstant.unlockGolden}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="petConstant.type"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<select name="type" class="select">
							<s:generator separator="," val="%{getText('petConstant.type_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="petConstant.type == #str">selected=selected</s:if>>
										<s:text name="%{'petConstant.type_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
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