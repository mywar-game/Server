<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="addSSkillConstantJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
		function setColSpan(){
			//最大列数
			//var cols = 0;
			var skillEffectTr = document.getElementById("skillEffectTr");
			var skillEffectNum = skillEffectTr.cells.length;
			var numStringTr = document.getElementById("numStringTr");
			var numStringCount = numStringTr.cells.length;
			//alert("numStringCount "+numStringCount);
			//if(skillEffectNum > numStringCount){
			//	cols = skillEffectNum;
			//} else{
			//	cols = numStringCount;
			//}
			//alert("cols "+cols);
			//前几列的colspan都为1，最后一列的colspan为10-(列数-1）
			for(var i=0; i<skillEffectNum-1; i++){
				skillEffectTr.cells[i].colSpan = 1;
			}
			skillEffectTr.cells[skillEffectNum-1].colSpan = 10-(skillEffectNum-1);
			
			for(var i=0; i<numStringCount-1; i++){
				numStringTr.cells[i].colSpan = 1;
			}
			numStringTr.cells[numStringCount-1].colSpan = 10-(numStringCount-1);
		}
	
		function isSubmit(){
			var skillEffectIds = document.getElementsByName("skillEffectId");
			//alert("skillEffectIds"+skillEffectIds);
			//alert("skillEffectIds.length"+skillEffectIds.length);
			var str = "";
			for(var i=0; i<skillEffectIds.length; i++){
				//alert(skillEffectIds[i].value);
				str += skillEffectIds[i].value;
				if(i < (skillEffectIds.length-1)){
					str += ",";
				}
			}
			//alert(str);
			var skillEffectInput = document.getElementById("skillEffect");
			skillEffectInput.value = str;
			
			str = "";
			var numStringInputs = document.getElementsByName("numStringInput");
			for(var i=0; i<numStringInputs.length; i++){
				str += numStringInputs[i].value;
				if(i < (numStringInputs.length-1)){
					str += ",";
				}
			}
			var numString = document.getElementById("numString");
			numString.value = str;
			
			if(window.confirm("<s:text name='addConfirm'></s:text>"))
				return true;
			return false;
		}
		
		//删除某个技能效果
		function delSkillEffectId(delButton){
			var td = delButton.parentNode;
			//alert("td "+td);
			//alert("td.cellIndex "+td.cellIndex);
			var tr = td.parentNode;
			//alert("tr "+tr);
			tr.deleteCell(td.cellIndex);
			setColSpan();
		}
		
		//添加一个技能效果
		function addSkillEffecId(addButton){
			//添加的select所在行
			var addTr = addButton.parentNode.parentNode;
			//alert("addTr "+ addTr);
			//alert("addTr.innerHTML "+addTr.innerHTML);
			//添加的select所在的td
			var addTd = addTr.cells[1];
			//alert("addTd.innerHTML "+addTd.innerHTML);
			var childNodes = addTd.childNodes;
			//alert(childNodes);
			var select;
			for(var i=0; i<childNodes.length; i++){
				//alert(childNodes[i].nodeType);
				//alert(childNodes[i].nodeName);
				if(childNodes[i].nodeType==1 && childNodes[i].nodeName=="SELECT"){
					//克隆添加的select标签
					select = childNodes[i].cloneNode(true);
					select.value = childNodes[i].value;
					select.name = "skillEffectId";
				}
			}
			
			var tr = document.getElementById("table").rows[addTr.rowIndex-1];
			//alert("tr.innerHTML "+tr.innerHTML);
			var newTd = tr.insertCell(tr.cells.length);
			//alert("newTd "+newTd);
			newTd.appendChild(select);
			var input = document.createElement("INPUT");
			input.type = "button";
			input.setAttribute("onclick","delSkillEffectId(this)");
			input.value = "<s:text name="delete"></s:text>";
			input.setAttribute("class","button");
			newTd.appendChild(input);
			setColSpan();
		}
		
		//删除某个数字组合
		function delNumString(delButton){
			var td = delButton.parentNode;
			//alert("td "+td);
			//alert("td.cellIndex "+td.cellIndex);
			var tr = td.parentNode;
			//alert("tr "+tr);
			tr.deleteCell(td.cellIndex);
			setColSpan();
		}
		
		//添加一个数字组合
		function addNumString(addButton){
			//添加的select所在行
			var addTr = addButton.parentNode.parentNode;
			//alert("addTr "+ addTr);
			//alert("addTr.innerHTML "+addTr.innerHTML);
			//添加的select所在的td
			var addTd = addTr.cells[1];
			//alert("addTd.innerHTML "+addTd.innerHTML);
			//数字组合所在tr
			var tr = document.getElementById("numStringTr");
			//alert("tr.innerHTML "+tr.innerHTML);
			var newTd = tr.insertCell(tr.cells.length);
			var html = '<input type="text" name="numStringInput" value="" class="maxLife" size="10" maxlength="10" />';
			html += '<input type="button" value="<s:text name="delete"></s:text>" onclick="delNumString(this)" class="button" />';
			newTd.innerHTML = html;
			setColSpan();
		}
	</script>
	<body onload="setColSpan()">
		&nbsp;
		<form action="addSSkillConstant?isCommit=T" method="post" onsubmit="return isSubmit()">

			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="10">
						<center><s:text name="addSSkillConstantJsp.title"></s:text> &nbsp;</center>
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="skillConstant.skillConstantId"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="9">
						<input type="text" name="skillConstantId" value="${sskillConstant.skillConstantId}" class="maxLife" size="10" maxlength="11" readonly="readonly" />
						<font color="green"><s:text name="autoGeneration"></s:text></font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="skillConstant.skillId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="skillId" value="${sskillConstant.skillId}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="skillConstant.skillName"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="7">
						<input type="text" name="skillName" value="${sskillConstant.skillName}" class="maxLife" size="30" maxlength="32" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="skillConstant.skillDesc"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="9">
						<input type="text" name="skillDesc" value="${sskillConstant.skillDesc}" class="maxLife" size="180" maxlength="1024" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="skillConstant.level"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="level" value="${sskillConstant.level}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="skillConstant.skillType"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="7">
						<select name="skillType" class="select">
							<s:generator separator="," val="%{getText('skillType_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="sskillConstant.skillType == #str">selected=selected</s:if>>
										<s:text name="%{'skillType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>							
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="skillConstant.target"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="target" class="select">
							<s:generator separator="," val="%{getText('target_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="sskillConstant.target == #str">selected=selected</s:if>>
										<s:text name="%{'target_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>						
						</select>
					</td>
					<td>
						<s:text name="skillConstant.rangeCenter"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="7">
						<select name="rangeCenter" class="select">
							<s:generator separator="," val="%{getText('rangeCenter_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="sskillConstant.rangeCenter == #str">selected=selected</s:if>>
										<s:text name="%{'rangeCenter_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>						
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="skillConstant.skillRange"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="skillRange" value="${sskillConstant.skillRange}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="skillConstant.castRange"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="7">
						<input type="text" name="castRange" value="${sskillConstant.castRange}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="skillConstant.coolingTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="coolingTime" value="${sskillConstant.coolingTime}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="skillConstant.isSpecial"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="7">
						<s:generator separator="," val="%{getText('isSpecial_value')}">
							<s:iterator var="str">
								<input type="radio" name="isSpecial" id="isSpecial${str}" <s:if test="sskillConstant.isSpecial == #str">checked="checked"</s:if> value="${str}" /><label for="isSpecial${str}"><s:text name="%{'isSpecial_'+#str}"></s:text></label>
							</s:iterator>
						</s:generator>						
					</td>
				</tr>
				<tr id="skillEffectTr">
					<td>
						<s:text name="skillConstant.skillEffect"></s:text><s:text name="colon"></s:text>
						<input type="text" name="skillEffect" id="skillEffect" value="0" readonly="readonly"/>
<!--						<input type="hidden" name="skillEffect" id="skillEffect" value="${sskillConstant.skillEffect}" />-->
					</td>
<!--					<s:generator separator="," val="sskillConstant.skillEffect">-->
<!--						<s:iterator var="skillEffectId" status="sta">-->
<!--							<td>-->
<!--								<select name="skillEffectId">-->
<!--									<s:iterator var="effectConstant" value="seffectConstantList">-->
<!--											<option value="${effectConstant.effectId}" <c:if test="${skillEffectId == effectConstant.effectId}">selected="selected"</c:if>>-->
<!--												${effectConstant.effectDesc}-->
<!--											</option>-->
<!--									</s:iterator>-->
<!--								</select>-->
<!--								<input type="button" value="<s:text name='delete'></s:text>" onclick="delSkillEffectId(this)" class="button" />-->
<!--							</td>-->
<!--						</s:iterator>-->
<!--					</s:generator>-->
<!--				</tr>-->
<!--				<tr>-->
<!--					<td>-->
<!--						<input type="button" value="添加技能效果" onclick="addSkillEffecId(this)" class="button" />-->
<!--					</td>-->
<!--					<td colspan="9">-->
<!--						<select>-->
<!--							<s:iterator var="effectConstant" value="seffectConstantList">-->
<!--								<option value="${effectConstant.effectId}">${effectConstant.effectDesc}</option>-->
<!--							</s:iterator>-->
<!--						</select>-->
<!--						<font color="green">（选择技能效果，点击添加按钮）</font>-->
<!--					</td>-->
				</tr>
				<tr id="numStringTr">
					<td>
						<s:text name="skillConstant.numString"></s:text><s:text name="colon"></s:text>
						<input type="hidden" id="numString" name="numString" value="${sskillConstant.numString}"/>
					</td>
					<s:generator separator="," val="sskillConstant.numString">
						<s:iterator var="num">
							<td>
								<input type="text" name="numStringInput" value="<s:property value='num'/>" class="maxLife" size="10" maxlength="10" />
								<input type="button" value="<s:text name="delete"></s:text>" onclick="delNumString(this)" class="button" />
							</td>
						</s:iterator>
					</s:generator>
				</tr>	
				<tr>
					<td colspan="10">
						<input type="button" value="<s:text name="skillConstantJsp.addNumString"></s:text>" onclick="addNumString(this)" class="button" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="skillConstant.skillTiggerCondition"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="skillTiggerCondition" value="${sskillConstant.skillTiggerCondition}" class="maxLife" size="30" maxlength="32" />
					</td>
					<td colspan="7">
						<s:text name="skillConstant.skillEndCondition"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="skillEndCondition" value="${sskillConstant.skillEndCondition}" class="maxLife" size="30" maxlength="32" />
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