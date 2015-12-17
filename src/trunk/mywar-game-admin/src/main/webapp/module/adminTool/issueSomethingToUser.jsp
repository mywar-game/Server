<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="issueSomethingToUserJsp.title"></s:text></title>
	</head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<link href="../../css/loading.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="../../js/json.js"></script>
	<script language="javascript" src="../../js/ajax.js"></script>
	<script language="javascript" src="../../js/mask.js"></script>
	
	<script type="text/javascript">
		var mailAttachArray = new Array();
		
		var equipmentArray = new Array();
		<s:iterator value="equipmentIDNameMap" status="sta">
			var equipmentIDName = new Object();
			equipmentIDName.id = ${key};
			equipmentIDName.name = "${value}";
			equipmentArray.push(equipmentIDName);
		</s:iterator>
		
		var equipmentColorArray = new Array();
		<s:generator separator="," val="%{getText('userEquipment.color_values')}">
			<s:iterator var="str">
					equipmentColorArray[${str}] = "<s:text name="%{'userEquipment.color_'+#str}"></s:text>";
			</s:iterator>
		</s:generator>
		//alert(equipmentColorArray);
		
		var treasureArray = new Array();
		<s:iterator value="treasureIDNameMap" status="sta">
			var treasureIDName = new Object();
			treasureIDName.id = ${key};
			treasureIDName.name = "${value}";
			treasureArray.push(treasureIDName);
		</s:iterator>
		function canAdd(){
			if(mailAttachArray.length == 10){
				alert("<s:text name="issueSomethingToUserJsp.atMostTen"></s:text>");
				return false;
			}
			return true;
		}
		
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
				alert("<s:text name="issueSomethingToUserJsp.searchNoResult"></s:text>");
				return;
			}

			var quality = 1;
			for(var i=0; i<tempArray.length; i++){
				equipmentSelect.options[i] = new Option(tempArray[i].name, tempArray[i].id);
				equipmentSelect.options[i].style.backgroundColor = equipmentColorArray[quality];
				if (quality < 7) {
					quality++;
				} else {
					quality = 1;
				}
			}
		}
		
		function addEquipment(){
			if(!canAdd()){
				return;
			}
			var equipmentSelect = document.getElementById("equipmentSelect");
			var equipmentNum = document.getElementById("equipmentNum");
			var equipments = document.getElementById("equipments");
			var id = 0;
			var name = "";
			var num = equipmentNum.value;
			
			if(num == ""){
				alert("<s:text name="issueSomethingToUserJsp.pleaseInputNum"></s:text>");
				return;
			}
			for (i=equipmentSelect.options.length-1;i>=0;i--){
				//if(equipmentSelect.options[i].value == equipmentSelect.value){
				if(equipmentSelect.options[i].selected == true){
					//alert(equipmentSelect[i].innerHTML);
					name = equipmentSelect[i].innerHTML;
				}
			}
			//alert("equipmentSelect.value "+equipmentSelect.value);
			equipments.value +="\n"+name+" * "+num;
			
			id = equipmentSelect.value;
			var mailAttach = "{}";
			mailAttach = JSON.parse(mailAttach);
			mailAttach.attachId = parseInt(id);
			mailAttach.attachType = 2;
			mailAttach.attachNum = parseInt(num);
			mailAttachArray.push(mailAttach);
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
				alert("<s:text name="issueSomethingToUserJsp.searchNoResult"></s:text>");
				return;
			}
			for(var i=0; i<tempArray.length; i++){
				treasureSelect.options[i] = new Option(tempArray[i].name, tempArray[i].id);
			}
			//alert(treasureSelect.value);
			//	treasureSelect.options[0].selected=true;
		}
		
		function addTreasure(){
			if(!canAdd()){
				return;
			}
			var treasureSelect = document.getElementById("treasureSelect");
			var treasureNum = document.getElementById("treasureNum");
			var treasures = document.getElementById("treasures");
			var id = 0;
			var name = "";
			var num = treasureNum.value;
			
			if(num == ""){
				alert("<s:text name="issueSomethingToUserJsp.pleaseInputNum"></s:text>");
				return;
			}
			for (i=treasureSelect.options.length-1;i>=0;i--){
				//if(treasureSelect.options[i].value == treasureSelect.value){
				if(treasureSelect.options[i].selected == true){
					//alert(treasureSelect[i].innerHTML);
					name = treasureSelect[i].innerHTML;
				}
			}
			//alert("treasureSelect.value "+treasureSelect.value);
			treasures.value +="\n"+name+" * "+num;
			
			
			id = treasureSelect.value;
			var mailAttach = "{}";
			mailAttach = JSON.parse(mailAttach);
			mailAttach.attachId = parseInt(id);
			mailAttach.attachType = 1;
			mailAttach.attachNum = parseInt(num);
			mailAttachArray.push(mailAttach);
		}
		
		function addEquipmentsToContent(){
			var equipments = document.getElementById("equipments");
			var content = document.getElementById("content");
			content.value += "\n"+equipments.value;
		}
		
		function addTreasuresToContent(){
			var content = document.getElementById("content");
			content.value += "\n"+treasures.value;
		}
		
		function isAllUser(input){
			var nameStrInput = document.getElementById("nameStr");
			nameStrInput.readOnly = input.checked;
			
			if (input.checked) {
				input.value = 1;
			} else {
				input.value = 2;
			}
		}
		
		function beforeSubmit(){
			//alert("mailAttachArray"+mailAttachArray.length);
			if (mailAttachArray.length == 0) {
				alert("<s:text name="issueSomethingToUserJsp.noTreasureOrEquipment"></s:text>");
				return false;
			}
			var mailAttachInput = document.getElementById("mailAttach");
			mailAttachInput.value = mailAttachArray.toJSONString();
			//alert(mailAttachInput.value);
			
			var allUser = document.getElementById("allUser1");
			//alert("allUser=="+allUser.checked);
			var nameStr = document.getElementById("nameStr").value;
			//没发全服又没填角色名
			if(allUser.checked ==false && nameStr == ""){
				alert("<s:text name="issueSomethingToUserJsp.nameStrIsEmpty"></s:text>");
				return false;
			}
			if(allUser.checked == true && !window.confirm("<s:text name="issueSomethingToUserJsp.allUserConfirm"></s:text>")){
				return false;
			}
			// 
			popCoverDiv();			
			return true;
		}

		function addUserGolden(){
			var allUser = document.getElementById("allUser1");
			var nameStr = document.getElementById("nameStr").value;
			var golden = document.getElementById("golden").value;
			
			if(allUser.checked ==true){
				nameStr = "";
				//alert("<s:text name="issueSomethingToUserJsp.cannotIssueAllUserDiamond"></s:text>");
				//return false;
			}			
			
			if(!allUser.checked && nameStr == ""){
				alert("<s:text name="issueSomethingToUserJsp.nameStrIsEmpty"></s:text>");
				return false;
			}
			
			if(golden == null || golden <= 0){
				alert("<s:text name="issueSomethingToUserJsp.pleaseInputDiamondNum"></s:text>");
				return false;
			}
			//钻石一次发放不超过5万
			if (golden > 50000) {
				alert("<s:text name="issueSomethingToUserJsp.DiamondNumCannotGreaterThan50000"></s:text>");
				return false;
			}
			var issueReason = document.getElementById("issueReason").value;	
			if(issueReason == ""){
				alert("<s:text name="issueSomethingToUserJsp.issueReasonNeeded"></s:text>");
				return false;
			}
			
			if(allUser.checked == true && !window.confirm("将全服发放，确认发放？")){
				return false;
			}
			
			var ajaxobj = new Ajax();
		    ajaxobj.url="module/adminTool/sendUserGolden?name="+encodeURI(encodeURI(nameStr))+"&golden="+golden+"&issueReason="+encodeURI(encodeURI(issueReason)) + "&allUser=" + allUser.value;
		    ajaxobj.callback=function(){
		    	//alert("ajaxobj.gettext()=="+ajaxobj.gettext());
			    var responseMsg = eval('(' + ajaxobj.gettext() + ')');
			    //alert("responseMsg=="+responseMsg);
				alert(responseMsg.erroDescrip);
				removeCoverDiv(); // 去除遮挡层
		    }
		    popCoverDiv(); // 添加遮挡层
		    ajaxobj.send();
		}
		
	</script>
	
	<body>
		<form action="issueSomethingToUser?isCommit=T" method="post" onsubmit="return beforeSubmit()">
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center" >
						<center>
							<s:text name="issueSomethingToUserJsp.title"></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="issueSomethingToUserJsp.note"></s:text>
					</td>
					<td>
						<font color="green">
						<s:generator separator="," val="%{getText('issueSomethingToUserJsp.note_value')}">
							<s:iterator var="str">
								<s:text name="%{'issueSomethingToUserJsp.note'+#str}"></s:text><br>
							</s:iterator>
						</s:generator>
						</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="issueSomethingToUserJsp.nameStr"></s:text>
					</td>
					<td>
						<!-- <input type="text" name="nameStr" id="nameStr" size="100" value="${nameStr}"> -->
						<textarea name="nameStr" id="nameStr" rows="5" cols="100" >${nameStr}</textarea>
						<input type="checkbox" name="allUser" id="allUser1" value="2" onclick="isAllUser(this)"/><label for="allUser1"><s:text name="issueSomethingToUserJsp.allUser"></s:text></label>
						<br>
						<s:text name="issueSomethingToUserJsp.nameStr_note"></s:text>
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="issueSomethingToUserJsp.issueReason"></s:text>
					</td>
					<td>
						<textarea name="issueReason" id="issueReason" rows="5" cols="100">${issueReason}</textarea>
					</td>
				</tr>
				<tr>
					<td><s:text name="issueSomethingToUserJsp.diamond"></s:text></td>
					<td>
						<s:text name="issueSomethingToUserJsp.num"></s:text><s:text name="colon"></s:text>
						<input type="text" id="golden" name="golden" size="5" onblur="value=value.replace(/[^\d]/g,'')">
						<input type="button" class="button"  value="<s:text name="issueSomethingToUserJsp.issueDiamond"></s:text>" onclick="addUserGolden()">
					</td>
				</tr>
				<tr>
					<td rowspan="3">
						<s:text name="issueSomethingToUserJsp.equipment"></s:text>
					</td>
					<td>
						<s:text name="issueSomethingToUserJsp.equipmentName"></s:text><s:text name="colon"></s:text>
						<input type="text" id="equipmentInput">
						<input type="button" value="<s:text name="search"></s:text>" onclick="searchEquipment()" class="button">
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="issueSomethingToUserJsp.equipmentList"></s:text><s:text name="colon"></s:text>
						<select id="equipmentSelect">
							<option value="-1"><s:text name="issueSomethingToUserJsp.pleaseSearchFirst"></s:text></option>
						</select>
						<s:text name="issueSomethingToUserJsp.num"></s:text><s:text name="colon"></s:text>
						<input type="text" id="equipmentNum" size="5" onblur="value=value.replace(/[^\d]/g,'')">
						<input type="button" value="<s:text name="issueSomethingToUserJsp.add"></s:text>" onclick="addEquipment()" class="button">
					</td>
				</tr>
				<tr>
					<td>
					<s:text name="issueSomethingToUserJsp.issueEquipments"></s:text><s:text name="colon"></s:text><br/>
					<textarea name="equipments" id="equipments" rows="10" cols="40" readonly="readonly"></textarea>
					</td>
				</tr>
				<tr>
					<td rowspan="3">
						<s:text name="issueSomethingToUserJsp.treasure"></s:text>
					</td>
					<td>
						<s:text name="issueSomethingToUserJsp.treasureName"></s:text><s:text name="colon"></s:text>
						<input type="text" id="treasureInput">
						<input type="button" value="<s:text name="search"></s:text>" onclick="searchTreasure()" class="button">
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="issueSomethingToUserJsp.treasureList"></s:text><s:text name="colon"></s:text>
						<select id="treasureSelect">
							<option value="-1"><s:text name="issueSomethingToUserJsp.pleaseSearchFirst"></s:text></option>
						</select>
						<s:text name="issueSomethingToUserJsp.num"></s:text><s:text name="colon"></s:text>
						<input type="text" id="treasureNum" size="5" onblur="value=value.replace(/[^\d]/g,'')">
						<input type="button" value="<s:text name="issueSomethingToUserJsp.add"></s:text>" onclick="addTreasure()" class="button">
					</td>
				</tr>
				<tr>
					<td>
					<s:text name="issueSomethingToUserJsp.issueTreasures"></s:text><s:text name="colon"></s:text><br/>
					<textarea name="treasures" id="treasures" rows="10" cols="40" readonly="readonly"></textarea>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input type="hidden" id="mailAttach" name="mailAttach">
						<input type="submit" value="<s:text name="issueSomethingToUserJsp.issue"></s:text>" class="button">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
