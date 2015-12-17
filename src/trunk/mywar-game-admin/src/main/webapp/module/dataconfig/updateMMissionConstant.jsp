<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateMMissionConstantJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script language="javascript" src="../../js/json.js"></script>
	<script type="text/javascript">
		var erroDescrip = '${erroDescrip}';
		if (erroDescrip != "") {
			alert(erroDescrip);
		}
		
		//设置任务触发编号的显示格式
		function setNextIdTrStyle(){
			//触发编号所在行
			var tr = document.getElementById("nextMissionIdsTr");
			//行中除最后一个外colspan都设为1
			for(var i=0; i<tr.cells.length-1; i++){
				tr.cells[i].colSpan = 1;
			}
			//设置最后一个列的colspan
			tr.cells[tr.cells.length-1].colSpan = 6-tr.cells.length+1;
		}
		      		
		//当targetType所选的值有变化时，targetNum的所有选项要跟着变动
		function changeTargetNum(targetTypeSelect){
			//alert("targetTypeSelect=="+targetTypeSelect);
			//当前targetType的值
			var targetType = targetTypeSelect.value;
			var targetTypeTd = targetTypeSelect.parentNode;
			var missionTr = targetTypeTd.parentNode;
			//删除原来的targetNum的td
			missionTr.deleteCell(targetTypeTd.cellIndex+1);
			var newTargetNumTd = missionTr.insertCell(targetTypeTd.cellIndex+1);
			var html = '';
			if(targetType==1 || targetType==4){
				html += '<s:text name="missionJsp.pveId"></s:text><s:text name="colon"></s:text><input name="missionCondition.targetNum" onblur="value=value.replace(/[^\\d]/g,\'\')"/>';
			} else if(targetType==-1 || targetType==2 || targetType>200){
				html += '<s:text name="monsterConstant.monsterId"></s:text><s:text name="colon"></s:text><input name="missionCondition.targetNum" onblur="value=value.replace(/[^\\d]/g,\'\')"/>';
			} else if(targetType==3){
				html += '<s:text name="treasureId"></s:text><s:text name="colon"></s:text><input name="missionCondition.targetNum" onblur="value=value.replace(/[^\\d]/g,\'\')"/>';
			} else if(targetType==20){
				html += '<s:text name="buildingConstant.buildingName"></s:text><s:text name="colon"></s:text>';
				html += '<select name="missionCondition.targetNum" class="select">';
					<s:iterator value="buildingIDNameMap" status="sta">
						html += "<option value='${key}'>${value}</option>";
					</s:iterator>
				html += '</select>';
			} else if(targetType==38){
				html += '<s:text name="technologyConstant.technologyName"></s:text><s:text name="colon"></s:text>';
				html += '<select name="missionCondition.targetNum" class="select">';
					<s:iterator value="technologyIdAndNameMap" status="sta">
						html += "<option value='${key}'>${value}</option>";
					</s:iterator>
				html += '</select>';
			} else {
				html += '<s:text name="missionJsp.default"></s:text><input name="missionCondition.targetNum" value="0" readonly="readonly" size="2"/>';
			}
			newTargetNumTd.innerHTML = html;
		}
		
		//任务奖励的类型改变后，具体奖励的显示的调整
		function changeTargetIdShow(categorySelect){
			//alert(categorySelect.parentNode.cellIndex);
			var tr = categorySelect.parentNode.parentNode;
			var td = tr.cells[categorySelect.parentNode.cellIndex+1];
			//alert(td.innerHTML);
			var html = '';
			if (categorySelect.value == 1) {
				html += '<s:text name="missionPrize.targetId"></s:text><s:text name="colon"></s:text>';
				html += '<select name="missionPrize.targetId" class="select">';
				<s:generator separator="," val="%{getText('missionPrize.targetId_1_value')}">
					<s:iterator var="str">
						html += '<option value="${str}">';
						html += '<s:text name="%{'missionPrize.targetId_1_'+#str}"></s:text>';
						html += '</option>';
					</s:iterator>
				</s:generator>
				html += '</select>';
			} else if(categorySelect.value == 2){
				html += '<s:text name="equipmentId"></s:text><s:text name="colon"></s:text>';
				html += '<input type="text" name="missionPrize.targetId" size="10" maxlength="11" onblur="value=value.replace(/[^\\d]/g,\'\')" />';
			} else if(categorySelect.value == 3){
				html += '<s:text name="treasureId"></s:text><s:text name="colon"></s:text>';
				html += '<input type="text" name="missionPrize.targetId" size="10" maxlength="11" onblur="value=value.replace(/[^\\d]/g,\'\')" />';
			} else if(categorySelect.value == 4 || categorySelect.value == 5){
				html += '<s:text name="missionJsp.default"></s:text><input type="text" name="missionPrize.targetId" value="0" readonly>';
			}
			td.innerHTML = html;
		}
		
		//添加任务完成条件
		function addMissionCondition(addButton) {
			var conditionTr = addButton.parentNode.parentNode;
			//alert("in function addMissionCondition()");
			var tr = table.insertRow(conditionTr.rowIndex);
			var td1 = tr.insertCell(tr.cells.length);
			td1.innerHTML = '<s:text name="missionCondition.id_show"><s:param>'+(conditionDescs.length+1)+'</s:param></s:text><s:text name="colon"></s:text>';
			td1.rowSpan = 2;
			var td2 = tr.insertCell(tr.cells.length);
			td2.innerHTML = '<s:text name="missionCondition.conditionDesc"></s:text><s:text name="colon"></s:text><input type="text" name="missionCondition.conditionDesc" class="maxLife" size="100" maxlength="512" />';
			td2.colSpan = 5;
			
			tr = table.insertRow(conditionTr.rowIndex);
			var td1 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="missionCondition.operatorNum"></s:text><s:text name="colon"></s:text>';
			html += '<select name="missionCondition.operatorNum" class="select">';
			<s:generator separator="," val="%{getText('missionCondition.operatorNum_value')}">
				<s:iterator var="str">
			html += '<option value="${str}">';
			html += '<s:text name="%{'missionCondition.operatorNum_'+#str}"></s:text>';
			html += '</option>';
				</s:iterator>
			</s:generator>
			html += '</select>';
			td1.innerHTML = html;
			var td2 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="missionCondition.targetType"></s:text><s:text name="colon"></s:text>';
			html += '<select name="missionCondition.targetType" class="select" onchange="changeTargetNum(this)">';
			<s:generator separator="," val="%{getText('missionCondition.targetType_value')}">
				<s:iterator var="str" status="sta">
			html += '<option value="${str}" <s:if test="#sta.index == 4">selected</s:if>>';
			html += '<s:text name="%{'missionCondition.targetType_'+#str}"></s:text>';
			html += '</option>';
				</s:iterator>
			html += '<option value="-1" >';
			html += '<s:text name="missionCondition.targetType_others"></s:text>';
			html += '</option>';
			</s:generator>
			html += '</select>';
			td2.innerHTML = html;
			
			var td3 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="missionCondition.targetNum"></s:text><s:text name="colon"></s:text>';
			html += '<s:text name="missionJsp.default"></s:text><input name="missionCondition.targetNum" value="0" readonly="readonly" size="2"/>';
			
			td3.innerHTML = html;
			tr.insertCell(tr.cells.length).innerHTML = '<s:text name="missionCondition.num"></s:text><s:text name="colon"></s:text><input type="text" name="missionCondition.num" value="1" size="10" maxlength="11" onblur="value=value.replace(/[^\\d]/g,\'\')" />';
			tr.insertCell(tr.cells.length).innerHTML = '<input type="button" value="<s:text name="delete"></s:text>" onclick="delMissionCondition(this)" class="button"/>';
		}
		
		//删除任务完成条件
		function delMissionCondition(delButton){
			//要删除的完成条件所在tr
			var tr = delButton.parentNode.parentNode;
			//删除行
			table.deleteRow(tr.rowIndex-1);
			table.deleteRow(tr.rowIndex);
			//重现编号
			for ( var i = 0; i < conditionDescs.length; i++) {
				//alert(categorys[i].parentNode.parentNode.cells[0].innerHTML);
				conditionDescs[i].parentNode.parentNode.cells[0].innerHTML = '<s:text name="missionCondition.id_show"><s:param>'+(i+1)+'</s:param></s:text><s:text name="colon"></s:text>';
			}
		}
		
		//添加任务完成奖励
		function addMissionPrize(button){
			//alert(button.parentNode.parentNode.rowIndex);
			var tr = table.insertRow(button.parentNode.parentNode.rowIndex);
			var categorys = document.getElementsByName("missionPrize.category");
			tr.insertCell(tr.cells.length).innerHTML = '<s:text name="missionPrize.id_show"><s:param>'+(categorys.length+1)+'</s:param></s:text><s:text name="colon"></s:text>';
			var td1 = tr.insertCell(tr.cells.length);
			var html = '';
			html += "<s:text name="missionPrize.category"></s:text><s:text name="colon"></s:text>";
			html += '<select name="missionPrize.category" class="select" onchange="changeTargetIdShow(this)">';
			<s:generator separator="," val="%{getText('missionPrize.category_value')}">
				<s:iterator var="str" status="sta">
					html += '<option value="${str}">';
					html +=	'<s:text name="%{'missionPrize.category_'+#str}"></s:text>';
					html += '</option>';
				</s:iterator>
			</s:generator>
			html += '</select>';
			td1.innerHTML = html;
			var td2 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="missionPrize.targetId"></s:text><s:text name="colon"></s:text>';
			html += '<select name="missionPrize.targetId" class="select">';
			<s:generator separator="," val="%{getText('missionPrize.targetId_1_value')}">
				<s:iterator var="str">
					html += '<option value="${str}">';
					html += '<s:text name="%{'missionPrize.targetId_1_'+#str}"></s:text>';
					html += '</option>';
				</s:iterator>
			</s:generator>
			html += '</select>';
			td2.innerHTML = html;
			var td3 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="missionPrize.num"></s:text><s:text name="colon"></s:text>';
			html += '<input name="missionPrize.num" value="1" type="text" size="10" maxlength="11" onblur="value=value.replace(/[^\\d]/g,\'\')" >';
			td3.innerHTML = html;
			var td4 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="missionPrize.type"></s:text><s:text name="colon"></s:text>';
			html += '<select name="missionPrize.type" class="select">';
			<s:generator separator="," val="%{getText('missionPrize.type_value')}">
				<s:iterator var="str">
					html += '<option value="${str}">';
					html +=	'<s:text name="%{'missionPrize.type_'+#str}"></s:text>';
					html += '</option>';
				</s:iterator>
			</s:generator>
			html += '</select>';
			td4.innerHTML = html;
			tr.insertCell(tr.cells.length).innerHTML = '<input type="button" value="<s:text name="delete"></s:text>" onclick="delMissionPrize(this)" class="button" />';
		}
		
		//删除任务完成奖励
		function delMissionPrize(button){
			var tr = button.parentNode.parentNode;
			//删除当前奖励所在行
			table.deleteRow(tr.rowIndex);
			//将奖励重新编号
			var categorys = document.getElementsByName("missionPrize.category");
			for ( var i = 0; i < categorys.length; i++) {
				//alert(categorys[i].parentNode.parentNode.cells[0].innerHTML);
				categorys[i].parentNode.parentNode.cells[0].innerHTML = '<s:text name="missionPrize.id_show"><s:param>'+(i+1)+'</s:param></s:text><s:text name="colon"></s:text>';
			}
		}
		
		//添加触发任务编号
		function addNextMissionId(){
			//触发编号所在行
			var tr = document.getElementById("nextMissionIdsTr");
			//行的元素个数
			var trSize = tr.cells.length;
			//alert(trSize);
			//在行的最后插入一个列
			var newTd = tr.insertCell(trSize);
			//新插入的所在列的索引应该是 行的元素个数减掉一个说明的列
			var index = trSize-1;
			//alert(index);
			//为列插入代码
			var html = '<input type="text" name="nextMissionId" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\\d]/g,\'\')" />';
			html += '<input type="button" value="<s:text name="delete"></s:text>" onclick="delNextMissionId(this)">';
			newTd.innerHTML = html;
			setNextIdTrStyle();
		}
		
		//删除触发任务编号
		function delNextMissionId(delButton){
			var tr = document.getElementById("nextMissionIdsTr");
			var td = delButton.parentNode;
			//alert(td.cellIndex);
			tr.deleteCell(td.cellIndex);
			setNextIdTrStyle();
		}
		
		//将任务完成条件整理成json字符串
		function formatMissionConditon(){
			var operatorNums = document.getElementsByName("missionCondition.operatorNum");
			var targetTypes = document.getElementsByName("missionCondition.targetType");
			var targetNums = document.getElementsByName("missionCondition.targetNum");
			var nums = document.getElementsByName("missionCondition.num");
			var conditionArray = new Array();
			for ( var i = 0; i < conditionDescs.length; i++) {
				var condition ="{}";
				condition = JSON.parse(condition);
				condition.id = i+1;
				condition.conditionDesc = conditionDescs[i].value;
				condition.operatorNum = parseInt(operatorNums[i].value);
				condition.targetType = parseInt(targetTypes[i].value);
				condition.targetNum = parseInt(targetNums[i].value);
				condition.num = parseInt(nums[i].value);
				conditionArray.push(condition);
			}
			//alert("conditionArray.toJSONString()"+conditionArray.toJSONString());
			var missionCondition =  document.getElementById("missionCondition");
			missionCondition.value = conditionArray.toJSONString();
		}
		
		//将任务完成奖励整理成json字符串
		function formatMissionPrize(){
			var categorys = document.getElementsByName("missionPrize.category");
			var targetIds = document.getElementsByName("missionPrize.targetId");
			var types = document.getElementsByName("missionPrize.type");
			var nums = document.getElementsByName("missionPrize.num");
			//alert(categorys.length);
			//alert(targetIds.length);
			//alert(types.length);
			//alert(nums.length);
			var prizeArray = new Array();
			for ( var i = 0; i < categorys.length; i++) {
				//alert("i "+i);
				var prize ="{}";
				prize = JSON.parse(prize);
				prize.id = i+1;
				prize.category = parseInt(categorys[i].value);
				prize.targetId = parseInt(targetIds[i].value);
				prize.type = parseInt(types[i].value);
				prize.num = parseInt(nums[i].value);
				prizeArray.push(prize);
			}
			//alert(prizeArray.toJSONString());
			var missionPrize = document.getElementById("missionPrize");
			missionPrize.value = prizeArray.toJSONString();
		}
		
		//整理触发任务编号
		function formatNextMissionIds(){
			//获得所有触发任务编号所在input
			var inputs = document.getElementsByName("nextMissionId");
			//整理成字符用逗号分隔的字符串
			var str = "";
			for(var i=0; i<inputs.length; i++){
				str += inputs[i].value;
				//最后一个不加逗号
				if (i < inputs.length-1) {
					str += ",";
				}
			}
			//将str设置成任务的触发任务编号
			var nextMissionIds = document.getElementById("nextMissionIds");
			nextMissionIds.value=str;
		}
		
		//提交确认
		function isSubmit(){
			formatMissionConditon();
			formatMissionPrize();
			formatNextMissionIds();
			if(window.confirm("<s:text name='updateConfirm'></s:text>")){
				return true;
			}
			return false;
		}
	</script>
	<body onload="setNextIdTrStyle()">
		&nbsp;

		<form action="updateMMissionConstant?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="6">
						<s:text name="updateMMissionConstantJsp.title"></s:text> &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="missionConstant.missionId"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="5">
						<input type="text" name="missionId" value="${mmissionConstant.missionId}" class="maxLife" size="10" maxlength="11" readonly="readonly"/>
						<s:text name="cannotEdit"></s:text>
					</td>
				</tr>
				<tr>				
					<td>
						<s:text name="missionConstant.missionName"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="5">
						<input type="text" name="missionName" value="${mmissionConstant.missionName}" class="maxLife" size="20" maxlength="32"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="missionConstant.missionDesc"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="5">
						<input type="text" name="missionDesc" value="${mmissionConstant.missionDesc}" class="maxLife" size="110" maxlength="512" />
					</td>
				</tr>
				
				<s:iterator id="condition" value="mmissionConditionList" status="sta">
					<tr>
						<td rowspan="2">
							<s:text name="missionCondition.id_show"><s:param>${sta.index+1}</s:param></s:text><s:text name="colon"></s:text>
						</td>
						<td colspan="5">
							<s:text name="missionCondition.conditionDesc"></s:text><s:text name="colon"></s:text>
							<input type="text" name="missionCondition.conditionDesc" value="${condition.conditionDesc}" class="maxLife" size="100" maxlength="512" />
						</td>
					</tr>
					<tr>
						<td>
							<s:text name="missionCondition.operatorNum"></s:text><s:text name="colon"></s:text>
							<select name="missionCondition.operatorNum" class="select">
								<s:generator separator="," val="%{getText('missionCondition.operatorNum_value')}">
									<s:iterator var="str">
										<option value="${str}" <s:if test="#condition.operatorNum == #str">selected=selected</s:if>>
											<s:text name="%{'missionCondition.operatorNum_'+#str}"></s:text>
										</option>
									</s:iterator>
								</s:generator>
							</select>
						</td>
						<td>
							<s:text name="missionCondition.targetType"></s:text><s:text name="colon"></s:text>
							<select name="missionCondition.targetType" class="select" onchange="changeTargetNum(this)">
								<s:generator separator="," val="%{getText('missionCondition.targetType_value')}">
									<s:iterator var="str">
										<option value="${str}" <s:if test="#condition.targetType == #str">selected=selected</s:if>>
											<s:text name="%{'missionCondition.targetType_'+#str}"></s:text>
										</option>
									</s:iterator>
								</s:generator>
								<s:if test="#condition.targetType > 200">
									<option  value="${condition.targetType}" selected=selected>
										<s:text name="missionCondition.targetType_others"></s:text>
									</option>
								</s:if>
								<s:else>
									<option  value="-1" >
										<s:text name="missionCondition.targetType_others"></s:text>
									</option>
								</s:else>
							</select>
						</td>
						<td>
							<s:text name="missionCondition.targetNum"></s:text><s:text name="colon"></s:text>
						    <s:if test="#condition.targetType == 1 || #condition.targetType == 4">
						     	<s:text name="missionJsp.pveId"></s:text><s:text name="colon"></s:text>
						     	<input name="missionCondition.targetNum" value="${condition.targetNum}" size="4"/>
							</s:if>
							<s:elseif test="#condition.targetType == 2 || #condition.targetType > 200">
								<s:text name="monsterConstant.monsterId"></s:text><s:text name="colon"></s:text>
								<input name="missionCondition.targetNum" value="${condition.targetNum}" />
							</s:elseif>
							<s:elseif test="#condition.targetType == 3">
								<s:text name="treasureId"></s:text><s:text name="colon"></s:text>
								<input name="missionCondition.targetNum" value="${condition.targetNum}" />
							</s:elseif>
							<s:elseif test="#condition.targetType == 20">
								<s:text name="buildingConstant.buildingName"></s:text><s:text name="colon"></s:text>
								<select name="missionCondition.targetNum" class="select">
									<s:iterator value="buildingIDNameMap" status="sta">
										<option value="${key}" <s:if test="#condition.targetNum == key">selected</s:if>>${value}</option>
									</s:iterator>
								</select>
							</s:elseif>
							<s:elseif test="#condition.targetType == 38">
								<s:text name="buildingConstant.buildingName"></s:text><s:text name="colon"></s:text>
								<select name="missionCondition.targetNum" class="select">
									<s:iterator value="technologyIdAndNameMap" status="sta">
										<option value="${key}" <s:if test="#condition.targetNum == key">selected</s:if>>${value}</option>
									</s:iterator>
								</select>
							</s:elseif>
							<s:else>
								<s:text name="missionJsp.default"></s:text><input name="missionCondition.targetNum" value="${condition.targetNum}" readonly="readonly" size="2"/>
							</s:else>
						</td>
						<td>
							<s:text name="missionCondition.num"></s:text><s:text name="colon"></s:text>
							<input type="text" name="missionCondition.num" value="${condition.num}" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
						</td>
						<td>
							<input type="button" value="<s:text name="delete"></s:text>" onclick="delMissionCondition(this)" class="button"/>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="6">
						<input type="button" value="<s:text name="missionJsp.addMissionCondition"></s:text>" onclick="addMissionCondition(this)" class="button" />
						<input type="hidden" name="missionCondition" id="missionCondition">
					</td>
				</tr>
				
				<s:iterator var="prize" value="missionPrizeList" status="sta">
					<tr>
						<td>
							<s:text name="missionPrize.id_show"><s:param>${prize.id}</s:param></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<s:text name="missionPrize.category"></s:text><s:text name="colon"></s:text>
							<select name="missionPrize.category" class="select" onchange="changeTargetIdShow(this)">
								<s:generator separator="," val="%{getText('missionPrize.category_value')}">
									<s:iterator var="str">
										<option value="${str}" <s:if test="#prize.category == #str">selected=selected</s:if>>
											<s:text name="%{'missionPrize.category_'+#str}"></s:text>
										</option>
									</s:iterator>
								</s:generator>
							</select>
						</td>
						<td>
							<s:text name="missionPrize.targetId"></s:text><s:text name="colon"></s:text>
							<s:if test="#prize.category == 1">
								<select name="missionPrize.targetId" class="select">
									<s:generator separator="," val="%{getText('missionPrize.targetId_1_value')}">
										<s:iterator var="str">
											<option value="${str}" <s:if test="#prize.targetId == #str">selected=selected</s:if>>
												<s:text name="%{'missionPrize.targetId_1_'+#str}"></s:text>
											</option>
										</s:iterator>
									</s:generator>
								</select>
							</s:if>
							<s:elseif  test="#prize.category == 2">
								<input type="text" name="missionPrize.targetId" value="${prize.targetId}" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" /> (${equipmentIDNameMap[prize.targetId]})
							</s:elseif>
							<s:elseif  test="#prize.category == 3">
								<input type="text" name="missionPrize.targetId" value="${prize.targetId}"  size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" /> (${treasureIDNameMap[prize.targetId]})
							</s:elseif>
							<s:elseif  test="#prize.category == 4 || #prize.category == 5">
								<input type="text" name="missionPrize.targetId" value="${prize.targetId}"  size="10" maxlength="11" readonly="readonly"/>
							</s:elseif>
							<s:else>
								<s:text name="undefine"></s:text>
							</s:else>
						</td>
						<td>
							<s:text name="missionPrize.targetId"></s:text><s:text name="colon"></s:text>
							<input type="text" name="missionPrize.num" value="${prize.num}" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
						</td>
						<td>
							<s:text name="missionPrize.type"></s:text><s:text name="colon"></s:text>
							<select name="missionPrize.type" class="select">
								<s:generator separator="," val="%{getText('missionPrize.type_value')}">
									<s:iterator var="str">
										<option value="${str}" <s:if test="#prize.type == #str">selected=selected</s:if>>
											<s:text name="%{'missionPrize.type_'+#str}"></s:text>
										</option>
									</s:iterator>
								</s:generator>
							</select>
						</td>
						<td>
							<input type="button" value="<s:text name="delete"></s:text>" onclick="delMissionPrize(this)" class="button" />
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="6">
						<input type="hidden" name="missionPrize" id="missionPrize">
						<input type="button" value="<s:text name="missionJsp.addMissionPrize"></s:text>" onclick="addMissionPrize(this)" class="button" />
					</td>
				</tr>
				
				<tr>
					<td>
						<s:text name="missionConstant.type"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="5">
						<select name="type" class="select">
							<s:generator separator="," val="%{getText('missionConstant.type_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="#mmissionConstant.type == #str">selected=selected</s:if>>
										<s:text name="%{'missionConstant.type_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
				</tr>
				<tr id="nextMissionIdsTr">
					<td>
						<s:text name="missionConstant.nextMissionIds"></s:text><s:text name="colon"></s:text>
						<input type="hidden" id="nextMissionIds" name="nextMissionIds" value=""  />  
					</td>
					<s:generator separator="," val="mmissionConstant.nextMissionIds">
						<s:iterator status="sta">
							<td>
								<input type="text" name="nextMissionId" value="<s:property/>" class="maxLife" size="10" maxlength="11"  onblur="value=value.replace(/[^\d]/g,'')"/>
								<input type="button" value="<s:text name="delete"></s:text>" onclick="delNextMissionId(this)" class="button" />
							</td>
						</s:iterator>
					</s:generator>
				</tr>
				<tr>
					<td colspan="6">
						<input type="button" value="<s:text name="missionJsp.addNextMissionId"></s:text>" onclick="addNextMissionId()" class="button" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="missionConstant.isTheFirst"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="5">
						<input type="radio" name="isTheFirst" id="isTheFirst1" <c:if test="${mmissionConstant.isTheFirst == 1}"> checked="checked" </c:if> value="1"/>是
						<input type="radio" name="isTheFirst" id="isTheFirst0" <c:if test="${mmissionConstant.isTheFirst == 0}"> checked="checked" </c:if> value="0"/>否
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="missionConstant.missionAreaLimit"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="5">
						<select name="missionAreaLimit" class="select">
							<s:iterator value="userLevelIntervalAreaNamesMap">
								<option value="${key}" <s:if test="mmissionConstant.missionAreaLimit == key">selected="selected"</s:if>>${value}</option>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="missionConstant.needTaskTreasure"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="5">
						<input type="text" name="needTaskTreasure" value="${mmissionConstant.needTaskTreasure}" class="maxLife" size="60" maxlength="64" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="missionConstant.pveIds"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="5">
						<input type="text" name="pveIds" value="${mmissionConstant.pveIds}" class="maxLife" size="60" maxlength="64" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="missionConstant.openFunctionId"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="5">
						<input type="text" name="openFunctionId" value="${mmissionConstant.openFunctionId}" class="maxLife" size="4" maxlength="4" />
					</td>
				</tr>
				
				<tr>
					<td align="center" colspan="6">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
						<input type="hidden" name="npcId" value="${mmissionConstant.npcId}" />
						<input type="hidden" name="storyIds" value="${mmissionConstant.storyIds}" />
					</td>
				</tr>
			</table>
		</form>
		
		<table>
			<tr>
				<td>
					<s:text name="missionJsp.equipmentIdSearch"></s:text><s:text name="colon"></s:text>
				</td>
				<td>
					<s:text name="equipmentName"></s:text><s:text name="colon"></s:text>
				</td>
				<td>
					<input type="text" id="equipmentInput">
				</td>
				<td>
					<input type="button" value="<s:text name="search"></s:text>" onclick="searchEquipment()" class="button">
				</td>
				<td>
					<s:text name="missionJsp.equipmentList"></s:text><s:text name="colon"></s:text>
				</td>
				<td>
					<select id="equipmentSelect" onchange="changeEquipment(this)">
						<option><s:text name="missionJsp.pleaseSearchFirst"></s:text></option>
					</select>
				</td>
				<td>
					<s:text name="equipmentId"></s:text><s:text name="colon"></s:text>
				</td>
				<td>
					<input type="text" id="equipmentId">
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="missionJsp.treasureIdSearch"></s:text><s:text name="colon"></s:text>
				</td>
				<td>
					<s:text name="treasureName"></s:text><s:text name="colon"></s:text>
				</td>
				<td>
					<input type="text" id="treasureInput">
				</td>
				<td>
					<input type="button" value="<s:text name="search"></s:text>" onclick="searchTreasure()" class="button">
				</td>
				<td>
					<s:text name="missionJsp.treasureList"></s:text><s:text name="colon"></s:text>
				</td>
				<td>
					<select id="treasureSelect" onchange="changeTreasure(this)">
						<option><s:text name="missionJsp.pleaseSearchFirst"></s:text></option>
					</select>
				</td>
				<td>
					<s:text name="treasureId"></s:text><s:text name="colon"></s:text>
				</td>
				<td>
					<input type="text" id="treasureId">
				</td>
			</tr>
		</table>
		<s:text name="note"></s:text><s:text name="colon"></s:text><br/>
		<s:text name="missionJsp.note1"></s:text><br/>
		<s:text name="missionJsp.note2"></s:text><br/>
		<s:text name="missionJsp.note3"></s:text><br/>
	</body>
	<script type="text/javascript">
		var equipmentArray = new Array();
		<s:iterator value="equipmentIDNameMap" status="sta">
			var equipmentIDName = new Object();
			equipmentIDName.id = ${key};
			equipmentIDName.name = "${value}";
			equipmentArray.push(equipmentIDName);
		</s:iterator>
		//alert(equipmentArray.length);
		var treasureArray = new Array();
		<s:iterator value="treasureIDNameMap" status="sta">
			var treasureIDName = new Object();
			treasureIDName.id = ${key};
			treasureIDName.name = "${value}";
			treasureArray.push(treasureIDName);
		</s:iterator>
		
		function searchEquipment(){
			var equipmentInput = document.getElementById("equipmentInput").value;
			var equipmentSelect = document.getElementById("equipmentSelect");
			for (i=equipmentSelect.options.length-1;i>=0;i--){
				equipmentSelect.options[i]=null;
			}
			//alert(equipmentInput);
			//if(equipmentInput == ""){
			//	alert("空的，填点啥。。。");
			//	return;
			//}
			var tempArray = new Array();
			for(var i=0; i<equipmentArray.length; i++){
				if(equipmentArray[i].name.indexOf(equipmentInput) != -1){
					//alert("equipmentArray["+i+"].id  "+equipmentArray[i].id);
					//alert("equipmentArray["+i+"].name  "+equipmentArray[i].name);
					
					var equipmentIDName = new Object();
					equipmentIDName.id = equipmentArray[i].id;
					equipmentIDName.name = equipmentArray[i].name;
					tempArray.push(equipmentIDName);
				}
			}
			//alert(tempArray.length);
			if(tempArray.length == 0){
				alert("<s:text name="missionJsp.noSearchResult"></s:text>");
				return;
			}
			for(var i=0; i<tempArray.length; i++){
				equipmentSelect.options[i] = new Option(tempArray[i].name, tempArray[i].id);
			}
			//alert(equipmentSelect.value);
			//	equipmentSelect.options[0].selected=true;
			changeEquipment(equipmentSelect);
		}
		
		function changeEquipment(equipmentSelect){
			document.getElementById("equipmentId").value = equipmentSelect.value;
		}
		
		function searchTreasure(){
			var treasureInput = document.getElementById("treasureInput").value;
			var treasureSelect = document.getElementById("treasureSelect");
			for (i=treasureSelect.options.length-1;i>=0;i--){
				treasureSelect.options[i]=null;
			}
			//alert(treasureInput);
			//if(treasureInput == ""){
			//	alert("空的，填点啥。。。");
			//	return;
			//}
			var tempArray = new Array();
			for(var i=0; i<treasureArray.length; i++){
				if(treasureArray[i].name.indexOf(treasureInput) != -1){
					//alert("treasureArray["+i+"].id  "+treasureArray[i].id);
					//alert("treasureArray["+i+"].name  "+treasureArray[i].name);
					
					var treasureIDName = new Object();
					treasureIDName.id = treasureArray[i].id;
					treasureIDName.name = treasureArray[i].name;
					tempArray.push(treasureIDName);
				}
			}
			//alert(tempArray.length);
			if(tempArray.length == 0){
				alert("<s:text name="missionJsp.noSearchResult"></s:text>");
				return;
			}
			for(var i=0; i<tempArray.length; i++){
				treasureSelect.options[i] = new Option(tempArray[i].name, tempArray[i].id);
			}
			//alert(treasureSelect.value);
			//	treasureSelect.options[0].selected=true;
			changeTreasure(treasureSelect);
		}
		
		function changeTreasure(treasureSelect){
			document.getElementById("treasureId").value = treasureSelect.value;
		}
		
		var table = document.getElementById("table");  
		var conditionDescs = document.getElementsByName("missionCondition.conditionDesc");
	</script>
</html>