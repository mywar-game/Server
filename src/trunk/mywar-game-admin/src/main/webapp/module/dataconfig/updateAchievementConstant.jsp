<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateAchievementConstantJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
		function changeConditionType(conditionTypeSelect){
			alert("conditionType=="+conditionTypeSelect.value);
			//如果条件类型包含参数{0}说明完成数量可变，则后面的conditionNum的值可变
			var conditionTypeStr = conditionTypeSelect.options[conditionTypeSelect.value].text;
			var conditionNumInput = document.getElementById("conditionNum");
			if (conditionTypeStr.indexOf("{0}") == -1) {
				//alert("不可变");
				conditionNumInput.readOnly = true;
				switch (parseInt(conditionTypeSelect.value)) {
					case 0:
						//alert("97");
						conditionNumInput.value = 97;
						break;
					case 32:
						//alert("8");
						conditionNumInput.value = 8;
						break;
					case 8:
					case 9:
					case 10:
					case 11:
					case 12:
					case 62:
						//alert("7");
						conditionNumInput.value = 7;
						break;
					default:
						//alert("default 1");
						conditionNumInput.value = 1;
						break;
				}
				var conditionDescInput = document.getElementById("conditionDesc");
				conditionDescInput.value = conditionTypeStr;
			} else {
				conditionNumInput.value = 1;
				conditionNumInput.readOnly = false;
				changeConditionNum(conditionNumInput);
			}
			//32 8
			//62 7
			//0 97
			//8-12 7
		}
		
		function changeConditionNum(conditionNumInput){
			//alert("changeConditionNum");
			var conditionTypeSelect = document.getElementById("conditionType");
			var conditionTypeStr = conditionTypeSelect.options[conditionTypeSelect.value].text;
			//条件类型中完成数量可变
			if (conditionTypeStr.indexOf("{0}") != -1) {
				var conditionDescInput = document.getElementById("conditionDesc");
				conditionDescInput.value = conditionTypeStr.replace(/\{0\}/g, conditionNumInput.value);
			}
		}	
		
		function isSubmit(){
			var inputs = document.getElementsByTagName("INPUT");
			for ( var i = 0; i < inputs.length; i++) {
				var input = inputs[i];
				if (input.type == "text" && (input.value == "" || input.value == null)) {
					alert('<s:text name="addAchievementConstantJsp.cannotNull"><s:param>'+input.parentNode.parentNode.cells[0].innerHTML.replace(/\t/g, "")+'</s:param></s:text>');
					return false;
				}
			}
			if(window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
		}
	</script>
	<body>
		&nbsp;
		<form action="updateAchievementConstant?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="updateAchievementConstantJsp.title"></s:text> &nbsp;
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				
				
				<tr>
					<td>
						<s:text name="achievementConstant.achievementId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="achievementId" value="${achievementConstant.achievementId}" class="maxLife" size="11" maxlength="11" readonly="readonly"/>
						<s:text name="cannotEdit"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="achievementConstant.achievementName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="achievementName" value="${achievementConstant.achievementName}" class="maxLife" size="30" maxlength="32" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="achievementConstant.effectDesc"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="effectDesc" value="${achievementConstant.effectDesc}" class="maxLife" size="100" maxlength="128" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="achievementConstant.achievementType"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="achievementType" class="select">
							<s:generator separator="," val="%{getText('achievementConstant.achievementType_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="achievementConstant.achievementType == #str">selected=selected</s:if>>
										<s:text name="%{'achievementConstant.achievementType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="achievementConstant.isSendNotice"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<s:generator separator="," val="%{getText('achievementConstant.isSendNotice_value')}">
							<s:iterator var="str">
								<input type="radio" name="isSendNotice" id="isSendNotice${str}" <s:if test="achievementConstant.isSendNotice == #str">checked="checked"</s:if> value="${str}" />
								<label for="isSendNotice${str}"><s:text name="%{'achievementConstant.isSendNotice_'+#str}"></s:text></label>
							</s:iterator>
						</s:generator>	
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="achievementConstant.conditionType"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="conditionType" id="conditionType" class="select" onchange="changeConditionType(this)">
							<s:generator separator="," val="%{getText('achievementConstant.conditionType_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="achievementConstant.conditionType == #str">selected=selected</s:if>>
										<s:text name="%{'achievementConstant.conditionType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="achievementConstant.conditionNum"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="conditionNum" id="conditionNum" value="${achievementConstant.conditionNum}" class="maxLife" size="11" maxlength="11" onblur="value=value.replace(/[^\d]/g,'');changeConditionNum(this)"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="achievementConstant.conditionDesc"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="conditionDesc" id="conditionDesc" value="${achievementConstant.conditionDesc}" class="maxLife" size="100" maxlength="128" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="achievementConstant.valueType"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<s:generator separator="," val="%{getText('achievementConstant.valueType_value')}">
							<s:iterator var="str">
								<input type="radio" name="valueType" id="valueType${str}" <s:if test="achievementConstant.valueType == #str">checked="checked"</s:if> value="${str}" />
								<label for="valueType${str}"><s:text name="%{'achievementConstant.valueType_'+#str}"></s:text></label>
							</s:iterator>
						</s:generator>	
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="achievementConstant.attackAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="attackAdd" value="${achievementConstant.attackAdd}" class="maxLife" size="11" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="achievementConstant.lifeAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="lifeAdd" value="${achievementConstant.lifeAdd}" class="maxLife" size="11" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="achievementConstant.armorAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="armorAdd" value="${achievementConstant.armorAdd}" class="maxLife" size="11" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="achievementConstant.critAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="critAdd" value="${achievementConstant.critAdd}" class="maxLife" size="11" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="achievementConstant.dodgeAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="dodgeAdd" value="${achievementConstant.dodgeAdd}" class="maxLife" size="11" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="achievementConstant.speedAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="speedAdd" value="${achievementConstant.speedAdd}" class="maxLife" size="11" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="achievementConstant.hitAdd"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="hitAdd" value="${achievementConstant.hitAdd}" class="maxLife" size="11" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')"/>
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