<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="addTEquipmentSynthesisConstantJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		function update(equipmentId) {
			window.location.href = "addTEquipmentSynthesisConstant?equipmentId="+equipmentId;
		}
		
		function beforeSubmit(){
			var treasureIds = document.getElementsByName("treasureId");
			var nums = document.getElementsByName("num");
			//alert(treasureIds);
			//alert(nums);
			for ( var i = 0; i < treasureIds.length; i++) {
				var treasureId = document.getElementById("buildMeterials"+(i+1));
				treasureId.value = treasureIds[i].value+":"+nums[i].value
			}
			return true;
		}
	</script>
	<body>
	<form action="addTEquipmentSynthesisConstant?isCommit=T" method="post" onsubmit="return beforeSubmit()">
		<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="7" align="center">
						<center><s:text name="addTEquipmentSynthesisConstantJsp.title"></s:text> &nbsp;</center>
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				
				<tr id="${tequipmentSynthesisConstant.equipmentId}">
					<td>
						<s:text name="equipmentSynthesisConstant.equipmentName"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<select name="equipmentId">
							<s:iterator var="map" value="equipmentIDNameMap">
								<option value="${map.key}">
									${map.key}-${map.value}
								</option>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<s:generator separator=":" val="tequipmentSynthesisConstant.buildMeterials1" var="args">
						<s:iterator var="arg" status="sta">
							<s:if test="#sta.index == 0">
								<s:set var="treasureId1" value="arg"></s:set>
							</s:if>
							<s:if test="#sta.index == 1">
								<s:set var="num1" value="arg"></s:set>
							</s:if>
						</s:iterator>
					</s:generator>
					<td>
						<s:text name="equipmentSynthesisConstant.buildMeterials1Name"></s:text><s:text name="colon"></s:text>
						<input type="hidden" name="buildMeterials1" id="buildMeterials1"/>
					</td>
					<td>
						<select name="treasureId">
							<s:iterator var="map" value="synthesisMaterialIdNameMap">
								<option value="${map.key}" <s:if test="#map.key == #treasureId1">selected="selected"</s:if>>
									${map.value}
								</option>
							</s:iterator>
						</select>
					</td>
					<td>
						<s:text name="equipmentSynthesisConstant.buildMeterials1Num"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="num" value="1" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<s:generator separator=":" val="tequipmentSynthesisConstant.buildMeterials2" var="args">
						<s:iterator var="arg" status="sta">
							<s:if test="#sta.index == 0">
								<s:set var="treasureId2" value="arg"></s:set>
							</s:if>
							<s:if test="#sta.index == 1">
								<s:set var="num2" value="arg"></s:set>
							</s:if>
						</s:iterator>
					</s:generator>
					<td>
						<s:text name="equipmentSynthesisConstant.buildMeterials2Name"></s:text><s:text name="colon"></s:text>
						<input type="hidden" name="buildMeterials2" id="buildMeterials2"/>
					</td>
					<td>
						<select name="treasureId">
							<s:iterator var="map" value="synthesisMaterialIdNameMap">
								<option value="${map.key}" <s:if test="#map.key == #treasureId2">selected="selected"</s:if>>
									${map.value}
								</option>
							</s:iterator>
						</select>
					</td>
					<td>
						<s:text name="equipmentSynthesisConstant.buildMeterials2Num"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="num" value="1" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<s:generator separator=":" val="tequipmentSynthesisConstant.buildMeterials3" var="args">
						<s:iterator var="arg" status="sta">
							<s:if test="#sta.index == 0">
								<s:set var="treasureId3" value="arg"></s:set>
							</s:if>
							<s:if test="#sta.index == 1">
								<s:set var="num3" value="arg"></s:set>
							</s:if>
						</s:iterator>
					</s:generator>
					<td>
						<s:text name="equipmentSynthesisConstant.buildMeterials3Name"></s:text><s:text name="colon"></s:text>
						<input type="hidden" name="buildMeterials3" id="buildMeterials3"/>
					</td>
					<td>
						<select name="treasureId">
							<s:iterator var="map" value="synthesisMaterialIdNameMap">
								<option value="${map.key}" <s:if test="#map.key == #treasureId3">selected="selected"</s:if>>
									${map.value}
								</option>
							</s:iterator>
						</select>
					</td>
					<td>
						<s:text name="equipmentSynthesisConstant.buildMeterials3Num"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="num" value="1" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
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