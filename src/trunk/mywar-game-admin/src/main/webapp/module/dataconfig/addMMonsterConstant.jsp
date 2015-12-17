<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title><s:text name="addMMonsterConstantJsp.title"></s:text></title>
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
	</head>

	<body>
		<form action="addMMonsterConstant?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="4">
						<s:text name="addMMonsterConstantJsp.title"></s:text> &nbsp;
					</td>
				</tr>
				
				<tr>
					<td>
						<s:text name="monsterConstant.monsterId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="monsterId" value="${monsterConstant.monsterId}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="monsterConstant.monsterName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="monsterName" value="${monsterConstant.monsterName}" class="maxLife" size="30" maxlength="32" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="monsterConstant.type"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="type" class="select">
							<s:generator separator="," val="%{getText('monsterConstant.type_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="monsterConstant.type == #str">selected=selected</s:if>>
										<s:text name="%{'monsterConstant.type_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>			
						</select>
					</td>
					<td>
						<s:text name="monsterConstant.attribute"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="attribute" class="select">
							<s:generator separator="," val="%{getText('monsterConstant.attribute_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="monsterConstant.attribute == #str">selected=selected</s:if>>
										<s:text name="%{'monsterConstant.attribute_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="monsterConstant.quality"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="quality" class="select">
							<s:generator separator="," val="%{getText('monsterConstant.quality_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="monsterConstant.quality == #str">selected=selected</s:if>>
										<s:text name="%{'monsterConstant.quality_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
					<td>
						<s:text name="monsterConstant.monsterLevel"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="monsterLevel" value="${monsterConstant.monsterLevel}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="monsterConstant.basicLife"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="basicLife" value="${monsterConstant.basicLife}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="monsterConstant.basicArmor"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="basicArmor" value="${monsterConstant.basicArmor}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="monsterConstant.basicHighAttack"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="basicHighAttack" value="${monsterConstant.basicHighAttack}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="monsterConstant.baseicLowerAttack"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="baseicLowerAttack" value="${monsterConstant.baseicLowerAttack}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="monsterConstant.lifeAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="lifeAdd" value="${monsterConstant.lifeAdd}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="monsterConstant.armorAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="armorAdd" value="${monsterConstant.armorAdd}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />&nbsp;&nbsp;
							<font color="green"><s:text name="monsterConstantJsp.armorAdd_note"></s:text></font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="monsterConstant.attackAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="attackAdd" value="${monsterConstant.attackAdd}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />&nbsp;&nbsp;
							<font color="green"><s:text name="monsterConstantJsp.attackAdd_note"></s:text></font>
					</td>
					<td>
						<s:text name="monsterConstant.attackSpeed"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="attackSpeed" value="${monsterConstant.attackSpeed}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="monsterConstant.baseSkill"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="baseSkill" value="${monsterConstant.baseSkill}" class="maxLife" size="30" maxlength="64" />&nbsp;&nbsp;
						<font color="green"><s:text name="monsterConstantJsp.baseSkill_note"></s:text></font>
					</td>
					<td>
						<s:text name="monsterConstant.skillCooling"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="skillCooling" value="${monsterConstant.skillCooling}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="monsterConstant.critRate"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="critRate" value="${monsterConstant.critRate}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="monsterConstant.dodgeRate"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="dodgeRate" value="${monsterConstant.dodgeRate}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="monsterConstant.hitRate"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="hitRate" value="${monsterConstant.hitRate}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="monsterConstant.moveSpeed"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="moveSpeed" value="${monsterConstant.moveSpeed}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="monsterConstant.attackScope"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="attackScope" value="${monsterConstant.attackScope}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="monsterConstant.activeAttackScope"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="activeAttackScope" value="${monsterConstant.activeAttackScope}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="monsterConstant.modelId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
							<input type="text" name="modleId" value="${monsterConstant.modleId}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="monsterConstant.color"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="color" class="select">
							<s:generator separator="," val="%{getText('monsterConstant.color_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="monsterConstant.color == #str">selected=selected</s:if>>
										<s:text name="%{'monsterConstant.color_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="monsterConstant.arenaId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="arenaId" value="${monsterConstant.arenaId}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="monsterConstant.battleModel"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="battleModel" class="select">
							<s:generator separator="," val="%{getText('monsterConstant.battleModel_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="monsterConstant.battleModel == #str">selected=selected</s:if>>
										<s:text name="%{'monsterConstant.battleModel_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="4" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
