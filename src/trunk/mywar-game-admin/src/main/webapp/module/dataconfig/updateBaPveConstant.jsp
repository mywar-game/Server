<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateBaPveConstantJsp.title"></s:text> </title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	
	<script language="javascript" src="../../js/jquery/jquery.min.js"></script>
	<script language="javascript" src="../../js/jquery/ui/jquery.ui.core.js"></script>
	<script language="javascript" src="../../js/jquery/ui/jquery.ui.widget.js"></script>
	<script language="javascript" src="../../js/jquery/ui/jquery.ui.position.js"></script>
	<script language="javascript" src="../../js/jquery/ui/jquery.ui.autocomplete.js"></script>
		
	<script type="text/javascript">
		var erroDescrip = '${erroDescrip}';
		if (erroDescrip != "") {
			alert(erroDescrip);
		}
		//添加一波怪
		function addMonsterInfo(addButton){
			var addTr = addButton.parentNode.parentNode;
			var monsterInfo_appearTypeSelects = document.getElementsByName("monsterInfo_appearTypeSelect");
			//怪物波数
			var waveNum = monsterInfo_appearTypeSelects.length;
			var tr = table.insertRow(addTr.rowIndex);
			var td1 = tr.insertCell(tr.cells.length);
			td1.rowSpan = 3;
			var html = '';
			html += '<s:text name="monsterInfo_num_show"><s:param>'+(waveNum+1)+'</s:param></s:text><s:text name="colon"></s:text>';
			td1.innerHTML = html;
			var td2 = tr.insertCell(tr.cells.length);
			td2.rowSpan = 2;
			html = '';
			html += '<s:text name="monsterInfo"></s:text><s:text name="colon"></s:text>';
			td2.innerHTML = html;
			var td3 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<s:text name="monsterInfo_appearType"></s:text><s:text name="colon"></s:text>';
			td3.innerHTML = html;
			var td4 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<select  name="monsterInfo_appearTypeSelect">';
			<s:generator separator="," val="%{getText('monsterInfo_appearType_value')}">
				<s:iterator var="str">
					html += '<option value="${str}" >';
					html += '<s:text name="%{'monsterInfo_appearType_'+#str}"></s:text>';
					html += '</option>';
				</s:iterator>
			</s:generator>
			html += '</select>';
			td4.innerHTML = html;
			var td5 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<s:text name="monsterInfo_time"></s:text><s:text name="colon"></s:text>';
			td5.innerHTML = html;
			var td6 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<input type="text" name="monsterInfo_timeInput" value="10" class="maxLife" size="4" maxlength="4" onblur="value=value.replace(/[^\\d]/g,\'\')" /><s:text name="monsterInfo_time_note"></s:text>';
			td6.innerHTML = html;
			var td7 = tr.insertCell(tr.cells.length);
			td7.rowSpan = 3;
			html = '';
			html += '<input value=';
			html += '<s:text name="delete"></s:text><s:text name="monsterInfo_num_show"><s:param>'+(waveNum+1)+'</s:param></s:text>';
			html += ' onclick="delMonsterInfo(this)" class="button" type="button">';
			td7.innerHTML = html;
			
			var tr = table.insertRow(addTr.rowIndex);
			var td1 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<s:text name="monsterAppearType_addr"></s:text><s:text name="colon"></s:text>';
			td1.innerHTML = html;
			var td2 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<select name="monsterAppearType_addrSelect">';
			<s:generator separator="," val="%{getText('monsterAppearType_addr_value')}">
				<s:iterator var="str">
					html += '<option value="${str}" >';
					html += '<s:text name="%{'monsterAppearType_addr_'+#str}"></s:text>';
					html += '</option>';
				</s:iterator>
			</s:generator>
			html += '</select>';
			td2.innerHTML = html;
			var td3 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<s:text name="monsterAppearType_time"></s:text><s:text name="colon"></s:text>';
			td3.innerHTML = html;
			var td4 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<input type="text" name="monsterAppearType_timeInput" value="100" class="maxLife" size="10" maxlength="20" onblur="value=value.replace(/[^\\d]/g,\'\')" />';
			td4.innerHTML = html;
			
			var tr = table.insertRow(addTr.rowIndex);
			var td1 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<s:text name="monsterInfo_monster"></s:text><s:text name="colon"></s:text>';
			html += '<br>';
			html += '<input type="button" value=<s:text name="addbaPveConstantJsp.addOneMonster"></s:text> onclick="addMonster(this)" class="button" />';
			td1.innerHTML = html;
			var td2 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<s:text name="monsterInfo_monster_level"></s:text><s:text name="colon"></s:text>';
			html += '<input type="text" name="monsterInfo_'+waveNum+'_monster_levelInput" value="1" class="maxLife" size="8" maxlength="20" onblur="value=value.replace(/[^\\d]/g,\'\')" />';
			td2.innerHTML = html;
			var td3 = tr.insertCell(tr.cells.length);
			html = '';
			html += '怪物名：<input type="text" name="monsterName" />';
			td3.innerHTML = html;
			var td4 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<s:text name="monsterInfo_monster_monsterId"></s:text><s:text name="colon"></s:text>';
			html += '<input type="text" name="monsterInfo_'+waveNum+'_monster_monsterIdInput" class="maxLife" size="10" maxlength="20" onblur="value=value.replace(/[^\\d]/g,\'\')" />';
			td4.innerHTML = html;
			var td5 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<input type="button" value=<s:text name="delete"></s:text> onclick="delMonster('+waveNum+',this)" class="button" />';
			td5.innerHTML = html;
		}
			
		//删除一波怪(delButton删除按钮)
		function delMonsterInfo(delButton){
			//本波第一行
			var tr = delButton.parentNode.parentNode;
			//alert(tr.innerHTML);
			//本波所占行数
			var rows = tr.cells[0].rowSpan;
			//alert(tr.cells[0].rowSpan);
			//删除本波的所有行
			for(var rowIndex=tr.rowIndex+rows-1;rowIndex>tr.rowIndex; rowIndex--){
				//alert("tr.rowIndex=="+tr.rowIndex);
				//alert(rowIndex);
				table.deleteRow(rowIndex);
			}
			table.deleteRow(tr.rowIndex);
			//重新排列各波怪的顺序
			var monsterInfo_appearTypeSelects = document.getElementsByName("monsterInfo_appearTypeSelect");
			for ( var i = 0; i < monsterInfo_appearTypeSelects.length; i++) {
				//alert("monsterInfo_monster_levelInputs.length=="+monsterInfo_monster_levelInputs.length);
				var monsterInfoTr = monsterInfo_appearTypeSelects[i].parentNode.parentNode;
				monsterInfoTr.cells[0].innerHTML = '<s:text name="monsterInfo_num_show"><s:param>'+(i+1)+'</s:param></s:text><s:text name="colon"></s:text>';
				monsterInfoTr.cells[monsterInfoTr.cells.length-1].innerHTML = '<input value="<s:text name="delete"></s:text><s:text name="monsterInfo_num_show"><s:param>'+(i+1)+'</s:param></s:text>" onclick="delMonsterInfo(this)" class="button" type="button">';
				var monsterInfo_monster_levelInputs = document.getElementsByName("monsterInfo_"+i+"_monster_levelInput");
				if(monsterInfo_monster_levelInputs.length == 0){
					var j = i;
				}
			}
			for (; j < monsterInfo_appearTypeSelects.length+1; j++) {
				//alert("j=="+j);
				monsterInfo_monster_levelInputs = document.getElementsByName("monsterInfo_"+(j+1)+"_monster_levelInput");
				for ( var x=monsterInfo_monster_levelInputs.length-1; x>=0; x--) {
					var tr = monsterInfo_monster_levelInputs[x].parentNode.parentNode;
					var html = '';
					html += '<input type="button" value=';
					html += '<s:text name="delete"></s:text>';
					html += ' onclick="delMonster('+j+',this)" class="button" />';
					tr.cells[tr.cells.length-1].innerHTML = html;
					monsterInfo_monster_levelInputs[x].name = "monsterInfo_"+j+"_monster_levelInput";
				}
				var monsterInfo_monster_monsterIdInputs = document.getElementsByName("monsterInfo_"+(j+1)+"_monster_monsterIdInput");
				for ( var x=monsterInfo_monster_monsterIdInputs.length-1; x>=0; x--) {
					monsterInfo_monster_monsterIdInputs[x].name = "monsterInfo_"+j+"_monster_monsterIdInput";
				}
			}
		}
				
		//添加一个怪（addButton添加按钮）
		function addMonster(addButton){
			//第一个怪物所在行
			var monsterTr =addButton.parentNode.parentNode;
			//本波怪物数量
			var monsertNum = monsterTr.cells[0].rowSpan;
			//alert("monsertNum=="+monsertNum);
			//怪物波数
			var childNodes = monsterTr.cells[1].childNodes;
			for ( var i = 0; i < childNodes.length; i++) {
				if (childNodes[i].nodeType==1 && childNodes[i].nodeName=="INPUT") {
					var waveNum =  childNodes[i].name.replace("monsterInfo_","").replace("_monster_levelInput","");
				}
			}
			//alert("waveNum=="+waveNum);
			var tr = table.insertRow(monsterTr.rowIndex+monsertNum);
			var td1 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="monsterInfo_monster_level"></s:text><s:text name="colon"></s:text>';
			html += '<input type="text" name="monsterInfo_'+waveNum+'_monster_levelInput" value="1" class="maxLife" size="3" maxlength="4" onblur="value=value.replace(/[^\\d]/g,\'\')" />';
			td1.innerHTML = html;
			var td2 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '怪物名：<input type="text" name="monsterName" />';
			td2.innerHTML = html;
			var td3 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="monsterInfo_monster_monsterId"></s:text><s:text name="colon"></s:text>';
			html += '<input type="text" name="monsterInfo_'+waveNum+'_monster_monsterIdInput" class="maxLife" size="10" maxlength="20" onblur="value=value.replace(/[^\\d]/g,\'\')" />';
			td3.innerHTML = html;
			var td4 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<input type="button" value=';
			html += '<s:text name="delete"></s:text>';
			html += ' onclick="delMonster('+waveNum+',this)" class="button" />';
			td4.innerHTML = html;
			
			//本波第一行
			var monsterInfo_appearTypeSelects = document.getElementsByName("monsterInfo_appearTypeSelect");
			var monsertInfoTr = monsterInfo_appearTypeSelects[waveNum].parentNode.parentNode;
			//第几波怪：的td加长 
			monsertInfoTr.cells[0].rowSpan++;
			//删除第几波怪：的td加长  
			monsertInfoTr.cells[monsertInfoTr.cells.length-1].rowSpan++;
			//本波所有怪物：的td加长 
			monsterTr.cells[0].rowSpan++;
			
		}
	
		//删除第index+1波的一个怪
		function delMonster(index,delButton){
			//本怪所在行
			var tr = delButton.parentNode.parentNode;
			var monsterInfo_monster_levelInputs = document.getElementsByName("monsterInfo_"+index+"_monster_levelInput");
			if (monsterInfo_monster_levelInputs.length == 1) {
				alert("<s:text name="addbaPveConstantJsp.onlyOneMonsterCannotDel"></s:text>");
				return;
			}
			//第一个怪物所在行
			var firstTr = monsterInfo_monster_levelInputs[0].parentNode.parentNode;
			//如果是删第一个怪
			var isFirst = false;
			//alert("firstTr=="+firstTr.innerHTML);
			if (firstTr.rowIndex == tr.rowIndex) {
				isFirst = true;
			} else {
				//本波所有怪物：的td缩短 
				firstTr.cells[0].rowSpan--;
			}
			table.deleteRow(tr.rowIndex);
			//本波第一行
			var monsterInfo_appearTypeSelects = document.getElementsByName("monsterInfo_appearTypeSelect");
			var monsertInfoTr = monsterInfo_appearTypeSelects[index].parentNode.parentNode;
			//第几波怪：的td缩短 
			monsertInfoTr.cells[0].rowSpan--;
			//删除第几波怪：的td缩短 
			monsertInfoTr.cells[monsertInfoTr.cells.length-1].rowSpan--;
			for ( var i = 0; i < monsterInfo_monster_levelInputs.length; i++) {
				var monsterTr = monsterInfo_monster_levelInputs[i].parentNode.parentNode;
				//alert("i=="+i);
				//alert("monsterTr.innerHTML=="+monsterTr.innerHTML);
				//if (i==0 && isFirst == false) {
					//重新编号
					//monsterTr.cells[1].innerHTML = '<s:text name="baPveConstantListJsp.monsterNum"><s:param>'+(i+1)+'</s:param></s:text><s:text name="colon"></s:text>';
				//} else {
				//	monsterTr.cells[0].innerHTML = '<s:text name="baPveConstantListJsp.monsterNum"><s:param>'+(i+1)+'</s:param></s:text><s:text name="colon"></s:text>';
				//} 
				var html = '';
				html +=  '<input type="button" value=';
				html += '<s:text name="delete"></s:text>';
				html += ' onclick="delMonster('+index+',this)" class="button" />'
				monsterTr.cells[monsterTr.cells.length-1].innerHTML = html;
			}
			if (isFirst == true) {
				firstTr = monsterInfo_monster_levelInputs[0].parentNode.parentNode;
				//firstTr = monsterInfo_monster_levelInputs[0].parentNode.parentNode;
				var html = '';
				html += '<s:text name="monsterInfo_monster"></s:text><s:text name="colon"></s:text>';
				html += '<br>';
				html += '<input type="button" value=<s:text name="addbaPveConstantJsp.addOneMonster"></s:text> onclick="addMonster(this)" class="button" />';
				var newTd = firstTr.insertCell(0);
				newTd.innerHTML = html;
				newTd.rowSpan = monsterInfo_monster_levelInputs.length;
			}
			
		}

		//奖励的类型改变后，具体奖励的显示的调整
		function changeTargetIdShow(rewardTypeSelect){
			//alert(categorySelect.parentNode.cellIndex);
			var tr = rewardTypeSelect.parentNode.parentNode;
			var td = tr.cells[rewardTypeSelect.parentNode.cellIndex+1];
			//alert(td.innerHTML);
			//reward_rewardType_1=装备
			//reward_rewardType_2=道具
			//reward_rewardType_3=金钱
			//reward_rewardType_4=钻石
			//reward_targetId_3_3=金钱
			var html = '';
			html += '<s:text name="reward_targetId"></s:text><s:text name="colon"></s:text>';
			if (rewardTypeSelect.value == 1 || rewardTypeSelect.value == 2) {
				html += '<input name="targetName" value="${prize.targetName}">';
				html += '<input type="hidden" name="reward_targetId" value="${prize.targetId}">';
			} else if(rewardTypeSelect.value == 3) {
				html += '<s:text name="reward_rewardType_3"></s:text>';
			} else if(rewardTypeSelect.value == 4) {
				html += '<s:text name="reward_rewardType_4"></s:text>';
			} else {
				html += '<span style="color:red"><s:text name="undefine"></s:text></span>';
			}
			td.innerHTML = html;
		}

		//VIP奖励的类型改变后，具体奖励的显示的调整
		function changeVipRewardTargetIdShow(vipRewardTypeSelect){
			//alert(categorySelect.parentNode.cellIndex);
			var tr = vipRewardTypeSelect.parentNode.parentNode;
			var td = tr.cells[vipRewardTypeSelect.parentNode.cellIndex+1];
			//alert(td.innerHTML);
			var html = '';
			html += '<s:text name="vipReward_targetId"></s:text><s:text name="colon"></s:text>';
			if (vipRewardTypeSelect.value == 1 || vipRewardTypeSelect.value == 2) {
				html += '<input name="targetName" value="${prize.targetName}">';
				html += '<input type="hidden" name="vipReward_targetId" value="${prize.targetId}">';
			} else if(vipRewardTypeSelect.value == 3) {
				html += '<s:text name="vipReward_rewardType_3"></s:text>';
			} else if(vipRewardTypeSelect.value == 4) {
				html += '<s:text name="vipReward_rewardType_4"></s:text>';
			} else {
				html += '<span style="color:red"><s:text name="undefine"></s:text></span>';
			}
			td.innerHTML = html;
		}
		
		//添加一个奖励信息	
		function addReward(addRewardButton){
			var reward_rewardTypeSelects = document.getElementsByName("reward_rewardTypeSelect");
			var addRewardTr = addRewardButton.parentNode.parentNode;
			var tr = table.insertRow(addRewardTr.rowIndex);
			var td1 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="reward"></s:text>'+(reward_rewardTypeSelects.length+1)+'<s:text name="colon"></s:text>';
			td1.innerHTML = html;
			var td2 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="reward_rewardType"></s:text><s:text name="colon"></s:text>';
			html += '<select name="reward_rewardTypeSelect" onchange="changeTargetIdShow(this)">';
			<s:generator separator="," val="%{getText('reward_rewardType_value')}">
				<s:iterator var="str">
					html += '<option value="${str}" <s:if test="#str == 1">selected=selected</s:if>>';
					html += '<s:text name="%{'reward_rewardType_'+#str}"></s:text>';
					html += '</option>';
				</s:iterator>
			</s:generator>			
			html += '</select>';
			td2.innerHTML = html;
			var td3 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="reward_targetId"></s:text><s:text name="colon"></s:text>';
			html += '<input name="targetName" value="${prize.targetName}">';
			html += '<input type="hidden" name="reward_targetId" value="${prize.targetId}">';
			td3.innerHTML = html;
			var td4 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="reward_num"></s:text><s:text name="colon"></s:text>';
			html += '<input type="text" name="reward_numInput" value="100" class="maxLife" size="10" maxlength="20" onblur="value=value.replace(/[^\\d]/g,\'\')"/>';
			td4.innerHTML = html;
			var td5 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="reward_percent"></s:text><s:text name="colon"></s:text>';
			html += '<input type="text" name="reward_percentInput" value="500" class="maxLife" size="10" maxlength="20" onblur="value=value.replace(/[^\\d]/g,\'\')"/>';
			td5.innerHTML = html;
			var td6 = tr.insertCell(tr.cells.length);
			td6.colSpan = 2;
			var html = '';
			html += '<input type="button" value="<s:text name="delete"></s:text><s:text name="reward"></s:text>'+(reward_rewardTypeSelects.length)+'" onclick="delReward(this)" class="button"/>';
			td6.innerHTML = html;
		}	
		
		//删除一个奖励信息
		function delReward(delRewardButton){
			var tr = delRewardButton.parentNode.parentNode;
			table.deleteRow(tr.rowIndex);
			
			var reward_rewardTypeSelects = document.getElementsByName("reward_rewardTypeSelect");
			for ( var i = 0; i < reward_rewardTypeSelects.length; i++) {
				var tempTr = reward_rewardTypeSelects[i].parentNode.parentNode;
				tempTr.cells[0].innerHTML = '<s:text name="reward"></s:text>'+(i+1)+'<s:text name="colon"></s:text>';
				tempTr.cells[tempTr.cells.length-1].innerHTML = '<input type="button" value="<s:text name="delete"></s:text><s:text name="reward"></s:text>'+(i+1)+'" onclick="delReward(this)" class="button"/>';
			}
		}
		
		//添加一个vipReward
		function addVipReward(addVipRewardButton){
			var vipReward_rewardTypeSelects = document.getElementsByName("vipReward_rewardTypeSelect");
			var addRewardTr = addVipRewardButton.parentNode.parentNode;
			var tr = table.insertRow(addRewardTr.rowIndex);
			var td1 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="vipReward"></s:text>'+(vipReward_rewardTypeSelects.length+1)+'<s:text name="colon"></s:text>';
			td1.innerHTML = html;
			var td2 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="vipReward_rewardType"></s:text><s:text name="colon"></s:text>';
			html += '<select name="vipReward_rewardTypeSelect" onchange="changeTargetIdShow(this)">';
			<s:generator separator="," val="%{getText('vipReward_rewardType_value')}">
				<s:iterator var="str">
					html += '<option value="${str}" <s:if test="#str == 1">selected=selected</s:if>>';
					html += '<s:text name="%{'vipReward_rewardType_'+#str}"></s:text>';
					html += '</option>';
				</s:iterator>
			</s:generator>			
			html += '</select>';
			td2.innerHTML = html;
			var td3 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="vipReward_targetId"></s:text><s:text name="colon"></s:text>';
			html += '<input name="targetName" value="${prize.targetName}">';
			html += '<input type="hidden" name="vipReward_targetId" value="${prize.targetId}">';
			td3.innerHTML = html;
			var td4 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="vipReward_num"></s:text><s:text name="colon"></s:text>';
			html += '<input type="text" name="vipReward_numInput" value="100" class="maxLife" size="10" maxlength="20" onblur="value=value.replace(/[^\\d]/g,\'\')"/>';
			td4.innerHTML = html;
			var td5 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="vipReward_percent"></s:text><s:text name="colon"></s:text>';
			html += '<input type="text" name="vipReward_percentInput" value="500" class="maxLife" size="10" maxlength="20" onblur="value=value.replace(/[^\\d]/g,\'\')"/>';
			td5.innerHTML = html;
			var td6 = tr.insertCell(tr.cells.length);
			td6.colSpan = 2;
			var html = '';
			html += '<input type="button" value="<s:text name="delete"></s:text><s:text name="vipReward"></s:text>'+(vipReward_rewardTypeSelects.length)+'" onclick="delVipReward(this)" class="button"/>';
			td6.innerHTML = html;
		}
		
		//删除一个vipReward
		function delVipReward(delVipRewardButton){
			var tr = delVipRewardButton.parentNode.parentNode;
			table.deleteRow(tr.rowIndex);
			
			var vipReward_rewardTypeSelects = document.getElementsByName("vipReward_rewardTypeSelect");
			for ( var i = 0; i < vipReward_rewardTypeSelects.length; i++) {
				var tempTr = vipReward_rewardTypeSelects[i].parentNode.parentNode;
				tempTr.cells[0].innerHTML = '<s:text name="vipReward"></s:text>'+(i+1)+'<s:text name="colon"></s:text>';
				tempTr.cells[tempTr.cells.length-1].innerHTML = '<input type="button" value="<s:text name="delete"></s:text><s:text name="vipReward"></s:text>'+(i+1)+'" onclick="delVipReward(this)" class="button"/>';
			}
		}
		
		function beforeSubmit(){
			//var dropTaskTreasureInfoInput = document.getElementById("dropTaskTreasureInfo");
			var monsterInfoInput = document.getElementById("monsterInfo");
			var monsterAppearTypeInput = document.getElementById("monsterAppearType");
			var rewardInput = document.getElementById("reward");
			var vipRewardInput = document.getElementById("vipReward");
			
			//var dropTaskTreasureInfoArray = new Array();
			//var dropTaskTreasureInfo =new Object();
			//var dropTreasureId = document.getElementById("dropTreasureId").value;
			//var dropTaskId = document.getElementById("dropTaskId").value;
			//var dropMinNum = document.getElementById("dropMinNum").value;
			//var dropMaxNum = document.getElementById("dropMaxNum").value;
			//var dropPercent = document.getElementById("dropPercent").value;
			//都没填，掉落为空
			//if (dropTreasureId == "" && dropTaskId == "" && dropMinNum == "" && dropMaxNum == "" && dropPercent == "") {
			//	dropTaskTreasureInfoInput.value = "";
			//都填了
			//} else if(dropTreasureId != "" && dropTaskId != "" && dropMinNum != "" && dropMaxNum != "" && dropPercent != "") {
			//	dropTaskTreasureInfo.treasureId = parseInt(dropTreasureId);
			//	dropTaskTreasureInfo.taskId = parseInt(dropTaskId);
			//	dropTaskTreasureInfo.minNum = parseInt(dropMinNum);
			//	dropTaskTreasureInfo.maxNum = parseInt(dropMaxNum);
			//	dropTaskTreasureInfo.percent = parseInt(dropPercent);
			//	dropTaskTreasureInfoArray.push(dropTaskTreasureInfo);
			//	dropTaskTreasureInfoInput.value = JSON.stringify(dropTaskTreasureInfoArray);
			//漏填了
			//} else {
			//	alert("<s:text name="addbaPveConstantJsp.dropTaskTreasureInfo"></s:text>");
			//	return false;
			//}
			
			var monsterInfo_appearTypeSelects = document.getElementsByName("monsterInfo_appearTypeSelect");
			//var monsterInfo_numInputs = document.getElementsByName("monsterInfo_numInput");
			var monsterInfo_timeInputs = document.getElementsByName("monsterInfo_timeInput");
			var monsterInfoArray = new Array();
			//alert("monsterInfo_appearTypeSelects.length "+monsterInfo_appearTypeSelects.length);
			//alert("monsterInfo_numInputs.length "+monsterInfo_numInputs.length);
			//alert("monsterInfo_timeInputs.length "+monsterInfo_timeInputs.length);
			
			for(var i=0; i<monsterInfo_appearTypeSelects.length; i++){
				//alert("i "+i);
				var monsterInfo = new Object();
				monsterInfo.appearType = parseInt(monsterInfo_appearTypeSelects[i].value);
				monsterInfo.num = i+1;
				var monsterInfo_monster_levelInputs = document.getElementsByName("monsterInfo_"+i+"_monster_levelInput");
				var monsterInfo_monster_monsterIdInputs = document.getElementsByName("monsterInfo_"+i+"_monster_monsterIdInput");
				//alert("monsterInfo_monster_levelInputs=="+monsterInfo_monster_levelInputs);
				//alert(monsterInfo_monster_levelInputs.length);
				//如果某波没有怪物，提示不行
				if (monsterInfo_monster_levelInputs.length == 0) {
					alert("<s:text name="addbaPveConstantJsp.XWaveNoMonster"></s:text>");
					return false;
				}
				var monsterInfo_monsterArray = new Array();
				for(var j=0; j<monsterInfo_monster_levelInputs.length; j++){
					//alert("j "+j);
					var monster = new Object();
					monster.level = parseInt(monsterInfo_monster_levelInputs[j].value);
					if (monsterInfo_monster_monsterIdInputs[j].value == "") {
						alert("第"+(i+1)+"波的第"+(j+1)+"个怪物为空，请重新配置！");
						return false;
					}
					monster.monsterId = parseInt(monsterInfo_monster_monsterIdInputs[j].value);
					monsterInfo_monsterArray.push(monster);
				}
				monsterInfo.monster = monsterInfo_monsterArray;

				monsterInfo.time = parseInt(monsterInfo_timeInputs[i].value);
				monsterInfoArray.push(monsterInfo);
			}
			monsterInfoInput.value = JSON.stringify(monsterInfoArray);
			
			//var monsterAppearType_numInputs = document.getElementsByName("monsterAppearType_numInput");
			var monsterAppearType_addrSelects = document.getElementsByName("monsterAppearType_addrSelect");
			var monsterAppearType_timeInputs = document.getElementsByName("monsterAppearType_timeInput");
			var monsterAppearTypeArray = new Array();
			for(var i=0; i<monsterAppearType_addrSelects.length; i++){
				var monsterAppearType = new Object();
				monsterAppearType.addr = parseInt(monsterAppearType_addrSelects[i].value);
				//monsterAppearType.num = parseInt(monsterAppearType_numInputs[i].value);
				monsterAppearType.num = i+1;
				monsterAppearType.time = parseInt(monsterAppearType_timeInputs[i].value);
				
				monsterAppearTypeArray.push(monsterAppearType);
			}
			monsterAppearTypeInput.value = JSON.stringify(monsterAppearTypeArray);
			
			var reward_numInputs = document.getElementsByName("reward_numInput");
			var reward_percentInputs = document.getElementsByName("reward_percentInput");
			var reward_rewardTypeSelects = document.getElementsByName("reward_rewardTypeSelect");
			var reward_targetIds = document.getElementsByName("reward_targetId");
			var rewardArray = new Array();
			for(var i=0; i<reward_numInputs.length; i++){
				var reward = new Object();
				reward.num = parseInt(reward_numInputs[i].value); 
				reward.percent = parseInt(reward_percentInputs[i].value); 
				reward.rewardType = parseInt(reward_rewardTypeSelects[i].value);
				if (reward_targetIds[i].value == "") {
					alert("第"+(i+1)+"个普通奖励的具体奖励为空，请重新配置！");
					return false;
				}
				reward.targetId = parseInt(reward_targetIds[i].value); 
				rewardArray.push(reward);
			}			
			rewardInput.value = JSON.stringify(rewardArray);
			
			var vipReward_numInputs = document.getElementsByName("vipReward_numInput");
			var vipReward_percentInputs = document.getElementsByName("vipReward_percentInput");
			var vipReward_rewardTypeSelects = document.getElementsByName("vipReward_rewardTypeSelect");
			var vipReward_targetIds = document.getElementsByName("vipReward_targetId");
			var vipRewardArray = new Array();
			for(var i=0; i<vipReward_numInputs.length; i++){
				var vipReward = new Object();
				vipReward.num = parseInt(vipReward_numInputs[i].value); 
				vipReward.percent = parseInt(vipReward_percentInputs[i].value); 
				vipReward.rewardType = parseInt(vipReward_rewardTypeSelects[i].value); 
				if (vipReward_targetIds[i].value == "") {
					alert("第"+(i+1)+"个VIP奖励的具体奖励为空，请重新配置！");
					return false;
				}
				vipReward.targetId = parseInt(vipReward_targetIds[i].value);
				vipRewardArray.push(vipReward);
			}			
			vipRewardInput.value = JSON.stringify(vipRewardArray);
			return true;
		}
		
		function isSubmit(){
			//alert(beforeSubmit());
			//alert("xxxxxxxxx");
			if(beforeSubmit() && window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
		}
		
		//怪物名自动完成
		//初始化时
		$(function() {
			//因为输入框可能动态添加，所以要再次绑定
			$("input[name='monsterName']").live('focus', function() { 
				$("input[name='monsterName']").autocomplete({
					minLength: 0,
					search: function() {
						var level = $(this).parent().parent().find(":input[name$=monster_levelInput]")[0].value;
						if(level == ""){
							alert("请先填写等级");
							return false;
						}
					},
					source: function(request, response) {
						var level = $(this.element).parent().parent().find(":input[name$=monster_levelInput]")[0].value;
						$.ajax({
							url: "../dataconfig/mMonsterConstantListfindMonsterIdNameMapByCondition",
							data: {name:encodeURI(request.term), level:level},
							success: function(data) {
								response($.map( data, function(name,id) {
									return {
										id: id,
										label: name,
										value: name
									}
								}));
							}
						});
					},
					select: function( event, ui ) {
						//$(this).parent().parent().find("input")[3].value = ui.item.id;
						$(this).parent().parent().find(":input[name$=monster_monsterIdInput]")[0].value = ui.item.id;;
						
					},
					open: function() {
						$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
					},
					close: function() {
						$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
					}
				});
			});
		});
		
		//奖励自动完成
		//初始化时
		$(function() {
			//因为输入框可能动态添加，所以要再次绑定
			$("input[name='targetName']").live('focus', function() { 
				$("input[name='targetName']").autocomplete({
					minLength: 0,
					source: function(request, response) {
						var rewardType = $(this.element).parent().parent().find("select[name$=_rewardTypeSelect]").val();
						var action = "";
						if(rewardType == 1){
							action = "eEquipmentConstantListfindEquipmentIdNameMapByCondition";
						}
						if(rewardType == 2){
							action = "tTreasureConstantListfindTreasureIdNameMapByCondition";
						}
						$.ajax({
							url: action,
							data: {name:encodeURI(request.term)},
							success: function(data) {
								response($.map( data, function(name,id) {
									return {
										id: id,
										label: name,
										value: name
									}
								}));
							}
						});
					},
					select: function( event, ui ) {
						//$(this).parent().parent().find("input")[3].value = ui.item.id;
						$(this).parent().parent().find(":input[name$=_targetId]")[0].value = ui.item.id;;
						
					},
					open: function() {
						$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
					},
					close: function() {
						$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
					}
				});
			});
		});
	</script>
	<body>
		&nbsp;
		<form action="updateBaPveConstant?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="7">
						<center><s:text name="updateBaPveConstantJsp.title"></s:text></center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="pveBigId"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="baPveConstant.id.pveBigId" value="${baPveConstant.id.pveBigId}" class="maxLife" size="5" maxlength="11" readonly="readonly"/>
						<s:text name="cannotEdit"></s:text>
						
					</td>
					<td>
						<s:text name="pveBigName"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="text" name="pveBigName" value="${baPveConstant.pveBigName}" class="maxLife" size="60" maxlength="64" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="pveSmallId"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="baPveConstant.id.pveSmallId" value="${baPveConstant.id.pveSmallId}" class="maxLife" size="5" maxlength="11" readonly="readonly"/>
						<s:text name="cannotEdit"></s:text>
					</td>
					<td>
						<s:text name="pveSmallName"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="text" name="pveSmallName" value="${baPveConstant.pveSmallName}" class="maxLife" size="60" maxlength="64" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="baPveConstant.pveDesc"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td colspan="6">
						<input type="text" name="pveDesc" value="${baPveConstant.pveDesc}" class="maxLife" size="153" maxlength="256" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="enterLevel"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="enterLevel" value="${baPveConstant.enterLevel}" class="maxLife" size="5" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="baPveConstant.maxNum"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="maxNum" value="${baPveConstant.maxNum}" class="maxLife" size="5" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="baPveConstant.minNum"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td colspan="2">
						<input type="text" name="minNum" value="${baPveConstant.minNum}" class="maxLife" size="5" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					
				</tr>
				<tr>
					<td>
						<s:text name="maxRewardNum"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="maxRewardNum" value="${baPveConstant.maxRewardNum}" class="maxLife" size="5" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="minRewardNum"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="minRewardNum" value="${baPveConstant.minRewardNum}" class="maxLife" size="5" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="vipRewardAdd"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td colspan="2">
						<input type="text" name="vipRewardAdd" value="${baPveConstant.vipRewardAdd}" class="maxLife" size="5" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="waveNum"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="waveNum" value="${baPveConstant.waveNum}" class="maxLife" size="5" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="addExp"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="addExp" value="${baPveConstant.addExp}" class="maxLife" size="5" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="addRenow"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td colspan="2">
						<input type="text" name="addRenow" value="${baPveConstant.addRenow}" class="maxLife" size="5" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="baPveConstant.checkPointType"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td>
						<select  name="checkPointType">
							<s:generator separator="," val="%{getText('baPveConstant.checkPointType_value')}">
								<s:iterator var="str">
										<option value="${str}" <s:if test="baPveConstant.checkPointType == #str">selected="selected"</s:if>>
											<s:text name="%{'baPveConstant.checkPointType_'+#str}"></s:text>
										</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
					<td>
						<s:text name="baPveConstant.failNum"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="failNum" value="${baPveConstant.failNum}" class="maxLife" size="5" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<s:text name="baPveConstant.arenaId"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td colspan="2">
						<s:select  list="mapAreaIdNameMap" name="arenaId" theme="simple" headerKey="0" headerValue="%{getText('pleaseSelect')}" value="baPveConstant.arenaId" ></s:select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="monsterConstant.modelId"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="modelId" value="${baPveConstant.modelId}" class="maxLife" size="15" maxlength="32"/>
					</td>
					<td>
						<s:text name="baPveConstant.bossType"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td>
						<select  name="bossType">
							<s:generator separator="," val="%{getText('baPveConstant.bossType_value')}">
								<s:iterator var="str">
										<option value="${str}" <s:if test="baPveConstant.bossType == #str">selected="selected"</s:if>>
											<s:text name="%{'baPveConstant.bossType_'+#str}"></s:text>
										</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
					<td>
						<s:text name="baPveConstant.startMaxTime"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td colspan="2">
						<input type="text" name="startMaxTime" value="${baPveConstant.startMaxTime}" class="maxLife" size="5" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />秒
					</td>
				</tr>
				
				<s:iterator var="info" value="monsterInfoList" status="sta">
					<s:set var="appearType" value="monsterAppearTypeList[#sta.index]"></s:set>
					<tr>
						<td rowspan="${fn:length(info.monsterMapList)+2}">
							<s:text name="monsterInfo_num_show"><s:param>${info.num}</s:param></s:text><s:text name="colon"></s:text>
						</td>
						<td rowspan="2">
							<s:text name="monsterInfo"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<s:text name="monsterInfo_appearType"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<select  name="monsterInfo_appearTypeSelect">
								<s:generator separator="," val="%{getText('monsterInfo_appearType_value')}">
									<s:iterator var="str">
											<option value="${str}" <s:if test="#info.appearType == #str">selected="selected"</s:if>>
												<s:text name="%{'monsterInfo_appearType_'+#str}"></s:text>
											</option>
									</s:iterator>
								</s:generator>
							</select>
						</td>
						<td>
							<s:text name="monsterInfo_time"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<input type="text" name="monsterInfo_timeInput" value="${info.time}" class="maxLife" size="4" maxlength="4" onblur="value=value.replace(/[^\d]/g,'')" />秒
						</td>
						<td rowspan="${fn:length(info.monsterMapList)+2}">
							<input value="<s:text name="delete"></s:text><s:text name="monsterInfo_num_show"><s:param>${info.num}</s:param></s:text>" onclick="delMonsterInfo(this)" class="button" type="button">
						</td>
					</tr>
					<tr>
						<td>
							<s:text name="monsterAppearType_addr"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<select name="monsterAppearType_addrSelect">
								<s:generator separator="," val="%{getText('monsterAppearType_addr_value')}">
									<s:iterator var="str">
											<option value="${str}" <s:if test="#appearType.addr == #str">selected="selected"</s:if>>
												<s:text name="%{'monsterAppearType_addr_'+#str}"></s:text>
											</option>
									</s:iterator>
								</s:generator>
							</select>
						</td>
						<td>
							<s:text name="monsterAppearType_time"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<input type="text" name="monsterAppearType_timeInput" value="${appearType.time}" class="maxLife" size="10" maxlength="20" onblur="value=value.replace(/[^\d]/g,'')" />
						</td>
					</tr>
					<s:iterator var="monster" value="#info.monsterMapList" status="stat">
						<tr>
							<s:if test="#stat.index == 0">
								<td rowspan="${fn:length(info.monsterMapList)}">
									<s:text name="monsterInfo_monster"></s:text><s:text name="colon"></s:text>
									<br>
									<input type="button" value="<s:text name="addbaPveConstantJsp.addOneMonster"></s:text>" onclick="addMonster(this)" class="button" />
								</td>
							</s:if>
							<td>
								<s:text name="monsterInfo_monster_level"></s:text><s:text name="colon"></s:text>
								<input type="text" name="monsterInfo_${sta.index}_monster_levelInput" value="${monster.level}" class="maxLife" size="3" maxlength="4" onblur="value=value.replace(/[^\d]/g,'')" />
							</td>
							<td>
								怪物名：
								<input type="text" name="monsterName" value="${monster.monsterName}"/>
							</td>
							<td>
								<s:text name="monsterInfo_monster_monsterId"></s:text><s:text name="colon"></s:text>
								<input type="text" name="monsterInfo_${sta.index}_monster_monsterIdInput" value="${monster.monsterId}" class="maxLife" size="10" maxlength="20" onblur="value=value.replace(/[^\d]/g,'')" />
							</td>
							<td>
								<input type="button" value="<s:text name="delete"></s:text>" onclick="delMonster(${sta.index},this)" class="button" />
							</td>
						</tr>
					</s:iterator>
				</s:iterator>
				<tr>
					<td colspan="7">
						<input value="<s:text name="addbaPveConstantJsp.addOneWaveMonster"></s:text>" id="addMonsterInfoButton" onclick="addMonsterInfo(this)" class="button" type="button">
					</td>
				</tr>
				
				<s:iterator var="prize" value="rewardList" status="sta">
					<tr>
						<td>
							<s:text name="reward"></s:text>${sta.index+1}<s:text name="colon"></s:text>
						</td>
						<td>
							<s:text name="reward_rewardType"></s:text><s:text name="colon"></s:text>
							<select name="reward_rewardTypeSelect" onchange="changeTargetIdShow(this)">
								<s:generator separator="," val="%{getText('reward_rewardType_value')}">
									<s:iterator var="str">
										<option value="${str}" <s:if test="#prize.rewardType == #str">selected=selected</s:if>>
											<s:text name="%{'reward_rewardType_'+#str}"></s:text>
										</option>
									</s:iterator>
								</s:generator>
							</select>
						</td>
						<td>
							<s:text name="reward_targetId"></s:text><s:text name="colon"></s:text>
							<input name="targetName" value="${prize.targetName}">
							<input type="hidden" name="reward_targetId" value="${prize.targetId}">
						</td>
						<td>
							<s:text name="reward_num"></s:text><s:text name="colon"></s:text>
							<input type="text" name="reward_numInput" value="${prize.num}" class="maxLife" size="10" maxlength="20" onblur="value=value.replace(/[^\d]/g,'')" />
						</td>
						<td>
							<s:text name="reward_percent"></s:text><s:text name="colon"></s:text>
							<input type="text" name="reward_percentInput" value="${prize.percent}" class="maxLife" size="5" maxlength="20" onblur="value=value.replace(/[^\d]/g,'')" /><s:text name="reward_percent_suffix"></s:text>
						</td>
						<td colspan="2">
							<input type="button" value="<s:text name="delete"></s:text><s:text name="reward"></s:text>${sta.index+1}" onclick="delReward(this)" class="button"/>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="7">
						<input type="button" value="<s:text name='addbaPveConstantJsp.addOneReward'></s:text>" onclick="addReward(this)" class="button" />
					</td>
				</tr>
				
				<s:iterator var="vipPrize" value="vipRewardList" status="sta">
					<tr>
						<td>
							<s:text name="vipReward"></s:text>${sta.index+1}<s:text name="colon"></s:text>
						</td>
						<td>
							<s:text name="vipReward_rewardType"></s:text><s:text name="colon"></s:text>
							<select name="vipReward_rewardTypeSelect"  onchange="changeVipRewardTargetIdShow(this)" class="select">
								<s:generator separator="," val="%{getText('vipReward_rewardType_value')}">
									<s:iterator var="str">
										<option value="${str}" <s:if test="#vipPrize.rewardType == #str">selected=selected</s:if>>
											<s:text name="%{'vipReward_rewardType_'+#str}"></s:text>
										</option>
									</s:iterator>
								</s:generator>
							</select>
						</td>
						<td>
							<s:text name="vipReward_targetId"></s:text><s:text name="colon"></s:text>
							<input type="text" name="targetName" value="${vipPrize.targetName}">
							<input type="hidden" name="vipReward_targetId" value="${vipPrize.targetId}">
						</td>
						<td>
							<s:text name="vipReward_num"></s:text><s:text name="colon"></s:text>
							<input type="text" name="vipReward_numInput" value="${vipPrize.num}" class="maxLife" size="10" maxlength="20" onblur="value=value.replace(/[^\d]/g,'')" />
						</td>
						<td>
							<s:text name="vipReward_percent"></s:text><s:text name="colon"></s:text>
							<input type="text" name="vipReward_percentInput" value="${vipPrize.percent}" class="maxLife" size="5" maxlength="20" onblur="value=value.replace(/[^\d]/g,'')" /><s:text name="vipReward_percent_suffix"></s:text>
						</td>
						<td colspan="2">
							<input type="button" value="<s:text name="delete"></s:text><s:text name="vipReward"></s:text>${sta.index+1}" onclick="delVipReward(this)" class="button"/>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="7">
						<input type="button" value="<s:text name='addbaPveConstantJsp.addOneVipReward'></s:text>" onclick="addVipReward(this)" class="button" />
					</td>
				</tr>
				
				<s:iterator var="dropTaskTreasureInfo" value="dropTaskTreasureInfoList" status="sta">
					<tr>
						<td>
							<s:text name="baPveConstant.dropTaskTreasureInfo"></s:text>${sta.index+1}<s:text name="colon"></s:text>
							<input type="hidden" id="dropTaskTreasureInfo" name="dropTaskTreasureInfo" value="${baPveConstant.dropTaskTreasureInfo}" />
						</td>
						<td>
							<s:text name="dropTaskTreasureInfo.rewardType"></s:text><s:text name="colon"></s:text>
							<select name="rewardType" class="select">
								<s:generator separator="," val="%{getText('dropTaskTreasureInfo.rewardType_value')}">
									<s:iterator var="str">
										<option value="${str}" <s:if test="#dropTaskTreasureInfo.rewardType == #str">selected=selected</s:if>>
											<s:text name="%{'dropTaskTreasureInfo.rewardType_'+#str}"></s:text>
										</option>
									</s:iterator>
								</s:generator>	
							</select>
						</td>
						<td>
							<s:text name="dropTaskTreasureInfo.rewardId"></s:text><s:text name="colon"></s:text>
							<s:if test="#dropTaskTreasureInfo.rewardType == 3">
								<select name="rewardId" class="select">
									<s:generator separator="," val="%{getText('dropTaskTreasureInfo.rewardId_3_value')}">
										<s:iterator var="str">
											<option value="${str}" <s:if test="#dropTaskTreasureInfo.rewardId == #str">selected=selected</s:if>>
												<s:text name="%{'dropTaskTreasureInfo.rewardId_3_'+#str}"></s:text>
											</option>
										</s:iterator>
									</s:generator>	
								</select>
							</s:if>
							<s:else>
								<input type="text" value="<s:property value='treasureIDNameMap[#dropTaskTreasureInfo.rewardId]'/>">
							</s:else>
						</td>
						<td>
							<s:text name="dropTaskTreasureInfo.rewardNum"></s:text><s:text name="colon"></s:text>
							<input type="text" name="rewardNum" value="${dropTaskTreasureInfo.rewardNum}">
						</td>
						<td colspan=3">
							<input type="button" value="删除通关奖励${sta.index+1}" class="button">
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="7">
						<input type="button" value="添加通关奖励" onclick="" class="button" />
					</td>
				</tr>
				
				<s:iterator var="firstReward" value="firstRewardList" status="sta">
					<tr>
						<td>
							<s:text name="baPveConstant.firstReward"></s:text>${sta.index+1}<s:text name="colon"></s:text>
						</td>
						<td>
							<s:text name="vipReward_rewardType"></s:text><s:text name="colon"></s:text>
							<select name="vipReward_rewardTypeSelect"  onchange="changeVipRewardTargetIdShow(this)" class="select">
								<s:generator separator="," val="%{getText('vipReward_rewardType_value')}">
									<s:iterator var="str">
										<option value="${str}" <s:if test="#vipPrize.rewardType == #str">selected=selected</s:if>>
											<s:text name="%{'vipReward_rewardType_'+#str}"></s:text>
										</option>
									</s:iterator>
								</s:generator>
							</select>
						</td>
						<td>
							<s:text name="vipReward_targetId"></s:text><s:text name="colon"></s:text>
							<input type="text" name="targetName" value="${vipPrize.targetName}">
							<input type="hidden" name="vipReward_targetId" value="${vipPrize.targetId}">
						</td>
						<td>
							<s:text name="vipReward_num"></s:text><s:text name="colon"></s:text>
							<input type="text" name="vipReward_numInput" value="${vipPrize.num}" class="maxLife" size="10" maxlength="20" onblur="value=value.replace(/[^\d]/g,'')" />
						</td>
						<td>
							<s:text name="vipReward_percent"></s:text><s:text name="colon"></s:text>
							<input type="text" name="vipReward_percentInput" value="${vipPrize.percent}" class="maxLife" size="5" maxlength="20" onblur="value=value.replace(/[^\d]/g,'')" /><s:text name="vipReward_percent_suffix"></s:text>
						</td>
						<td colspan="2">
							<input type="button" value="<s:text name="delete"></s:text><s:text name="baPveConstant.firstReward"></s:text>${sta.index+1}" onclick="" class="button"/>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="7">
						<input type="button" value="添加第一次必掉" onclick="" class="button" />
					</td>
				</tr>
				
				<s:generator separator="," val="baPveConstant.rewardShow">
					<s:iterator var="one" status="sta">
						<tr>
							<td>
								<s:text name="baPveConstant.rewardShow"></s:text>${sta.index+1}<s:text name="colon"></s:text>
							</td>
							<td>
								<s:set var="type" value="#one.split(':')[0]"></s:set>
								<s:set var="num" value="#one.split(':')[1]"></s:set>
								
								<s:text name="baPveConstant.rewardShow_type"></s:text><s:text name="colon"></s:text>
								<select name="baPveConstant.rewardShow_type" class="select">
									<s:generator separator="," val="%{getText('baPveConstant.rewardShow_type_value')}">
										<s:iterator var="str">
											<option value="${str}" <s:if test="#type == #str">selected=selected</s:if>>
												<s:text name="%{'baPveConstant.rewardShow_type_'+#str}"></s:text>
											</option>
										</s:iterator>
									</s:generator>	
								</select>
								
							</td>
							<td>
								<s:if test="#type == 1">
									<s:text name="eequipmentConstant.quantity"></s:text><s:text name="colon"></s:text>
									<select name="baPveConstant.rewardShow_num" class="select">
										<s:generator separator="," val="%{getText('eequipmentConstant.quantity_value')}">
											<s:iterator var="str">
												<option value="${str}" <s:if test="#num == #str">selected=selected</s:if>>
													<s:text name="%{'eequipmentConstant.quantity_'+#str}"></s:text>
												</option>
											</s:iterator>
										</s:generator>	
									</select>
								</s:if>
								<s:else>
									<s:text name="baPveConstant.rewardShow_num"></s:text><s:text name="colon"></s:text>
									<input type="text" name="baPveConstant.rewardShow_num" value="${num}"/>
								</s:else>
							</td>
							<td colspan="2">
								<input type="button" value="<s:text name="delete"></s:text><s:text name="baPveConstant.rewardShow"></s:text>${sta.index+1}" onclick="" class="button"/>
							</td>
						</tr>
					</s:iterator>
				</s:generator>
				<tr>
					<td colspan="7">
						<input type="button" value="添加奖励展示" onclick="" class="button" />
					</td>
				</tr>
				
				<tr id="submitTr">
					<td colspan="7" align="center">
						<input type="hidden" name="monsterInfo" id="monsterInfo" value="" />
						<input type="hidden" name="monsterAppearType" id="monsterAppearType" value="" />
						<input type="hidden" name="reward" id="reward" value="" />
						<input type="hidden" name="vipReward" id="vipReward" value="" />
						
						<input type="submit" value="<s:text name="submit"></s:text>" class="button" />
						<input type="reset" value="<s:text name="reset"></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>