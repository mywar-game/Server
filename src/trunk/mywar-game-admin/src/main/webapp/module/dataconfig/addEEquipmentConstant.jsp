<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
		<title><s:text name="addEEquipmentConstantJsp.title"></s:text></title>
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
    	<form action="addEEquipmentConstant?isCommit=T" method="post">
    		<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
    			<tr class="border">
					<td class="td_title" colspan="4">
						<CENTER><s:text name="addEEquipmentConstantJsp.title"></s:text></CENTER>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="equipmentId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="equipmentId" value="${eequipmentConstant.equipmentId}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="equipmentName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="equipmentName" value="${eequipmentConstant.equipmentName}" class="maxLife" size="30" maxlength="32" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="eequipmentConstant.equipmentDescription"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<input type="text" name="equipmentDescription" value="${eequipmentConstant.equipmentDescription}" class="maxLife" size="150" maxlength="2048" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="equipmentLevel"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="equipmentLevel" value="${eequipmentConstant.equipmentLevel}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="equipmentPrice"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="equipmentPrice" value="${eequipmentConstant.equipmentPrice}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="part"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="part">
							<s:generator separator="," val="%{getText('eequipmentConstant.part_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="eequipmentConstant.part == #str">selected=selected</s:if>>
										<s:text name="%{'eequipmentConstant.part_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
					<td>
						<s:text name="material"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="material">
							<s:generator separator="," val="%{getText('eequipmentConstant.material_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="eequipmentConstant.material == #str">selected=selected</s:if>>
										<s:text name="%{'eequipmentConstant.material_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="exclusiveHeroId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="exclusiveHeroId">
							<option value="-1" <s:if test="eequipmentConstant.exclusiveHeroId == -1">selected="selected"</s:if>><s:text name="eequipmentConstant.exclusiveHeroId_-1"></s:text></option>
							<s:iterator value="heroIdNameMap">
								<option value="${key}" <s:if test="eequipmentConstant.exclusiveHeroId == key">selected="selected"</s:if>>${value}</option>
							</s:iterator>
						</select>
					</td>
					<td>
						<s:text name="highAttack"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="highAttack" value="${eequipmentConstant.highAttack}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="lowerAttack"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="lowerAttack" value="${eequipmentConstant.lowerAttack}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="critRate"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="critRate" value="${eequipmentConstant.critRate}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="life"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="life" value="${eequipmentConstant.life}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="armor"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="armor" value="${eequipmentConstant.armor}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="moveSpeed"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="moveSpeed" value="${eequipmentConstant.moveSpeed}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="attackSpeed"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="attackSpeed" value="${eequipmentConstant.attackSpeed}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dodgeRate"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="dodgeRate" value="${eequipmentConstant.dodgeRate}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="hitRate"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="hitRate" value="${eequipmentConstant.hitRate}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="skillCooling"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="skillCooling" value="${eequipmentConstant.skillCooling}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="skillPoints"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="skillPoints" value="${eequipmentConstant.skillPoints}" class="maxLife" size="30" maxlength="32" />
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
