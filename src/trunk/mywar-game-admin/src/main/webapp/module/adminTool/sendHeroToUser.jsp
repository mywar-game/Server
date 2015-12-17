<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="sendHeroToUserJsp.title"></s:text></title>
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
		var heroArray = new Array();
		<s:iterator value="heroIdNameMap" status="sta">
			var hero = new Object();
			hero.id = ${key};
			hero.name = "${value}";
			heroArray.push(hero);
		</s:iterator>
		
		function isCanAdd(){
			if(addArray.length >= 10){
				alert("<s:text name="issueSomethingToUserJsp.atMostTen"></s:text>");
				return false;
			}
			return true;
		}
		
		var addArray = new Array();
		function addHero() {
			if (!isCanAdd()) {
				return;
			}
			
			var heroSelect = document.getElementById("heroSelect");
			var heroNum = document.getElementById("heroNum");
			var heros = document.getElementById("heros");
			
			var id = 0;
			var name = "";
			var num = heroNum.value;
			
			if (num == "") {
				alert("<s:text name="issueSomethingToUserJsp.pleaseInputNum"></s:text>");
				return;
			}
			
			id = heroSelect.value;
			if (id == -1) {
				alert("<s:text name="sendHeroToUserJsp.noSelectHero"></s:text>");
				return;
			}
			
			var index = heroSelect.selectedIndex;
			name = heroSelect.options[index].innerHTML;
			heros.value += "\n" + name + " * " + num;
			
			var mailAttach = "{}";
			mailAttach = JSON.parse(mailAttach);
			mailAttach.attachId = parseInt(id);
			mailAttach.attachType = 3;
			mailAttach.attachNum = parseInt(num);
			addArray.push(mailAttach);
		}
		
		// 搜索英雄
		function searchHero() {
			var heroInput = document.getElementById("heroInput").value;
			var heroSelect = document.getElementById("heroSelect");
			
			for (i = heroSelect.options.length - 1; i >= 0; i--) {
				heroSelect.options[i] = null;
			}
			
			var tempArray = new Array();
			for (var i = 0; i < heroArray.length; i++) {
				if(heroArray[i].name.indexOf(heroInput) != -1) {
					var heroObject = new Object();
					heroObject.id = heroArray[i].id;
					heroObject.name = heroArray[i].name;
					tempArray.push(heroObject);
				}
			}
			
			if (tempArray.length == 0) {
				alert("<s:text name="issueSomethingToUserJsp.searchNoResult"></s:text>");
				return;
			}
			
			for (var i = 0; i < tempArray.length; i++) {
				heroSelect.options[i] = new Option(tempArray[i].name, tempArray[i].id);
			}
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
			if (addArray.length == 0) {
				alert("<s:text name="sendHeroToUserJsp.noSelectHero"></s:text>");
				return false;
			}
			var addHeroInput = document.getElementById("addHeros");
			addHeroInput.value = addArray.toJSONString();
			
			var allUser = document.getElementById("allUser");
			var nameStr = document.getElementById("nameStr").value;
			
			//没发全服又没填角色名
			if(allUser.checked ==false && nameStr == ""){
				alert("<s:text name="issueSomethingToUserJsp.nameStrIsEmpty"></s:text>");
				return false;
			}
			
			if(allUser.checked == true && !window.confirm("<s:text name="issueSomethingToUserJsp.allUserConfirm"></s:text>")){
				return false;
			}
			
			popCoverDiv();			
			return true;
		}
		
	</script>
	
	<body>
		<form action="sendHeroToUser?isCommit=T" method="post" onsubmit="return beforeSubmit()">
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center" >
						<center>
							<s:text name="sendHeroToUserJsp.title"></s:text>
						</center>
					</td>
				</tr>				
				<tr>
					<td>
						<s:text name="issueSomethingToUserJsp.note"></s:text>
					</td>
					<td>
						<font color="green">
							<s:text name="sendHeroToUserJsp.note1"></s:text><br>						
						</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="issueSomethingToUserJsp.nameStr"></s:text>
					</td>
					<td>
						<textarea name="nameStr" id="nameStr" rows="5" cols="100" ></textarea>
						<input type="checkbox" name="allUser" id="allUser" value="2" onclick="isAllUser(this)"/><label for="allUser"><s:text name="issueSomethingToUserJsp.allUser"></s:text></label>
						<br>
						<s:text name="issueSomethingToUserJsp.nameStr_note"></s:text>
					</td>	
				</tr>
				<tr>
					<td rowspan="3">
						<s:text name="sendHeroToUserJsp.hero"></s:text>
					</td>
					<td>
						<s:text name="sendHeroToUserJsp.heroName"></s:text><s:text name="colon"></s:text>
						<input type="text" id="heroInput">
						<input type="button" value="<s:text name="search"></s:text>" onclick="searchHero()" class="button">
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="sendHeroToUserJsp.heroList"></s:text><s:text name="colon"></s:text>
						<select id="heroSelect">
							<option value="-1"><s:text name="issueSomethingToUserJsp.pleaseSearchFirst"></s:text></option>
						</select>
						<s:text name="issueSomethingToUserJsp.num"></s:text><s:text name="colon"></s:text>
						<input type="text" id="heroNum" size="5" onblur="value=value.replace(/[^\d]/g,'')">
						<input type="button" value="<s:text name="issueSomethingToUserJsp.add"></s:text>" onclick="addHero()" class="button">
					</td>
				</tr>
				<tr>
					<td>
					<s:text name="sendHeroToUserJsp.sendHeroList"></s:text><s:text name="colon"></s:text><br/>
					<textarea name="heros" id="heros" rows="10" cols="130" readonly="readonly"></textarea>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input type="hidden" id="addHeros" name="addHeros">
						<input type="submit" value="<s:text name="issueSomethingToUserJsp.issue"></s:text>" class="button">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
