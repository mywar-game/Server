<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="addHHeroConstantJsp.title"></s:text></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
		function check(){
			//检查英雄描述的长度是否超过最大值
			var heroDesc = document.getElementById("heroDesc");
			//alert("heroDesc "+heroDesc.value.length);
			if (heroDesc.value.length > 1024) {
				alert("<s:text name="heroDescTooLong"></s:text>");
				return false;
			}
			//检查英雄名字集合的长度是否超过最大值
			var generateHeroName = document.getElementById("generateHeroName");
			//alert("heroDesc "+heroDesc.value.length);
			if (generateHeroName.value.length > 2048) {
				alert("<s:text name="generateHeroNameTooLong"></s:text>");
				return false;
			}
			return true;
		}
	</script>
  </head>

	<body>
    	<form action="addHHeroConstant?isCommit=T" method="post" onsubmit="return check()">
	    	<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
	    		<tr class="border">
					<td class="td_title" colspan="4">
						<s:text name="addHHeroConstantJsp.title"></s:text>&nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="heroId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="heroId" value="${hheroConstant.heroId}" class="maxLife" size="10" maxlength="11" readonly="readonly" />
					</td>
					<td>
						<s:text name="heroName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="heroName" value="${hheroConstant.heroName}" class="maxLife" size="32" maxlength="32" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="heroDesc"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<textarea rows="1" cols="100" id="heroDesc" name="heroDesc" class="textarea">${hheroConstant.heroDesc}</textarea>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="basicLife"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="basicLife" value="${hheroConstant.basicLife}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="basicArmor"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="basicArmor" value="${hheroConstant.basicArmor}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="heroConstant.basicHighAttack"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="basicHighAttack" value="${hheroConstant.basicHighAttack}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="heroConstant.basicLowerAttack"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="basicLowerAttack" value="${hheroConstant.basicLowerAttack}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="heroConstant.lifeAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="lifeAdd" value="${hheroConstant.lifeAdd}" class="maxLife" size="10" maxlength="11" />
					</td>
					<td>
						<s:text name="heroConstant.armorAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="armorAdd" value="${hheroConstant.armorAdd}" class="maxLife" size="10" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="heroConstant.highAttackAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="highAttackAdd" value="${hheroConstant.highAttackAdd}" class="maxLife" size="10" maxlength="11" />
					</td>
					<td>
						<s:text name="heroConstant.lowerAttackAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="lowerAttackAdd" value="${hheroConstant.lowerAttackAdd}" class="maxLife" size="10" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="attackSpeed"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="attackSpeed" value="${hheroConstant.attackSpeed}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="moveSpeed"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="moveSpeed" value="${hheroConstant.moveSpeed}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="critRate"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="critRate" value="${hheroConstant.critRate}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="dodgeRate"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="dodgeRate" value="${hheroConstant.dodgeRate}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="hitRate"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="hitRate" value="${hheroConstant.hitRate}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="attackScope"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="attackScope" value="${hheroConstant.attackScope}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activeAttackScope"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="activeAttackScope" value="${hheroConstant.activeAttackScope}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="hero.type"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="type" class="maxLife">
							<s:generator separator="," val="%{getText('heroConstant.type_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="hheroConstant.type == #str">selected=selected</s:if>>
										<s:text name="%{'heroConstant.type_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="quality"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="quality" class="maxLife">
							<s:generator separator="," val="%{getText('heroConstant.quality_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="hheroConstant.quality == #str">selected=selected</s:if>>
										<s:text name="%{'heroConstant.quality_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
					<td>
						<s:text name="skillResist"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="skillResist" value="${hheroConstant.skillResist}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="baseSkill"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="baseSkill" value="${hheroConstant.baseSkill}" class="maxLife" size="30" maxlength="32" />
					</td>
					<td>
						<s:text name="skillCooling"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="skillCooling" value="${hheroConstant.skillCooling}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="heroConstant.generateHeroName"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<textarea rows="2" cols="100" id="generateHeroName" name="generateHeroName" class="textarea">${hheroConstant.generateHeroName}</textarea>
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
