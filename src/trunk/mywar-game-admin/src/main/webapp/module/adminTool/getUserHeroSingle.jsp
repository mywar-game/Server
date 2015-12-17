<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="getUserBagSingleJsp.title"><s:param>${user.userName}</s:param><s:param>${user.name}</s:param></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript">
	    
	    // 存放系统装备内容
	var toolArray = new Array();
	
	// 向 toolArray 中添加装备信息
	<s:iterator value="heros" status="sta">
		var equipment = new Object();
		// toolId 是 String 类型，格式是 toolType,toolId
		equipment.toolId = "${key}";
		equipment.toolName = "${value}";
		toolArray.push(equipment);
	</s:iterator>
	    
	    function searchTool() {
	    
		var toolInput = document.getElementById("toolInput").value;
		var toolSelect = document.getElementById("toolSelect");
		for (i = toolSelect.options.length-1; i >= 0; i--) {
			toolSelect.options[i] = null;
		}
		
		var tempArray = new Array();
	
		for (var i = 0; i < toolArray.length; i++) {
			if (toolArray[i].toolName==toolInput) {
			   
				var tool = new Object();
				tool.toolId = toolArray[i].toolId;
				tool.toolName = toolArray[i].toolName;
				tempArray.push(tool);
			}
		}
	
	    	for (var i = 0; i < toolArray.length; i++) {
			if (toolArray[i].toolName!=toolInput&&toolArray[i].toolName.indexOf(toolInput) != -1) {
				var tool = new Object();
				tool.toolId = toolArray[i].toolId;
				tool.toolName = toolArray[i].toolName;
				tempArray.push(tool);
			}
		}
		 
		if (tempArray.length == 0) {
			alert("<s:text name="没有找到"></s:text>")
			toolSelect.options[0] = new Option("请先搜索",'-1');
			return;
		}
		
		var quality = 1;
		
		for(var i=0; i<tempArray.length; i++){
		    
			toolSelect.options[i] = new Option(tempArray[i].toolName, tempArray[i].toolId);
		}

	}
	    window.onload = function hid(){
	       var flag = '<%=request.getAttribute("flag")%>';
	       
	       if(flag == "del"){
	         alert("删除成功");
	         window.location.href = "getUserHeroSingle"
	       }
	       
	    }
	    
		function check(){
			var lodoId = document.getElementById("lodoId").value;
			if(lodoId == 0){
				alert("<s:text name="getUserEquipSingleJsp.lodoIdCondition"></s:text>");			
				return false;
			}
			var toolName = document.getElementById("toolSelect").value;
			if(!toolName || toolName == -1){
			    alert("<s:text name="getUserEquipSingleJsp.toolNameCondition"></s:text>");			
				return false;
			}
		}
		
		function del(userId, userHeroId, mainRoleId, systemHeroId) {
		
		    if(mainRoleId == systemHeroId){
		        alert("不能删除用户主角!");
		        return false;
		    }
		    
			if (window.confirm("是否确认删除?")) {
			  
				window.location.href = "delUserHeroSingle?userId=" + userId + "&userHeroId=" + userHeroId;
				
			}
	
		}
		
		
	</script>
	<body>
		<form action="getUserHeroSingle" method="post" onsubmit="return check()">
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center">
						<center>
							<s:text name="getUserHeroSingleJsp.title"><s:param>${user.lodoId}</s:param></s:text>
						</center>
					</td>
				</tr>
			<tr>
				
				<td><s:text name="道具名"></s:text>
					<input type="text" id="toolInput"> 
			    </td>
			    <td>
					<input type="button" value="<s:text name="搜索"></s:text>" onclick="searchTool()"	class="button">
				</td>
			  </tr>
				<tr>
					<td colspan="3">
						<s:text name="log.lodoSearch"></s:text><s:text name="colon"></s:text><input type="text" id="lodoId" name="lodoId" size="10">
					</td>
					<!--  <td colspan="3">
						<s:text name="userTreasureSingle.toolName"></s:text><s:text name="colon"></s:text><input type="text" id="toolName" name="toolName" size="10">
					</td>-->
					<td>
					<s:text name="道具列表"></s:text>
					 <select name="systemHeroId" id="toolSelect">
						<option value="-1"><s:text name="请先搜索"></s:text></option>
					</select>
					</td>
					<td colspan="100">
						<input type="submit" value="<s:text name="search"></s:text>" class="button">
					</td>
				</tr>
		
				<c:if test="${userHeroSomeInfoList != null}">
					<tr>
						<td>
							<s:text name="userHero.userId"></s:text>
						</td>
						<td>
							<s:text name="userHero.userHeroId"></s:text>
						</td>
						<td>
							<s:text name="userHero.heroId"></s:text>
						</td>
						<td>
							<s:text name="userHero.systemHeroId"></s:text>
						</td>
						<td>
							<s:text name="userHero.imgId"></s:text>
						</td>
						<td>
							<s:text name="userHero.cardId"></s:text>
						</td>
						<td>
							<s:text name="userHero.name"></s:text>
						</td>
						<td>
							<s:text name="userHero.pos"></s:text>
						</td>
						<td>
							<s:text name="userHero.level"></s:text>
						</td>
						<td>
							<s:text name="userHero.exp"></s:text>
						</td>
						<td>
							<s:text name="userHero.dexp"></s:text>
						</td>
						<td>
							<s:text name="userHero.career"></s:text>
						</td>
						<td>
							<s:text name="userHero.life"></s:text>
						</td>
						<td>
							<s:text name="userHero.physicalAttack"></s:text>
						</td>
						<td>
							<s:text name="userHero.physicalDefense"></s:text>
						</td>
						<td>
							<s:text name="userHero.plan"></s:text>
						</td>
						<td>
							<s:text name="userHero.normalPlan"></s:text>
						</td>
						<td>
							<s:text name="userHero.skill1"></s:text>
						</td>
						<td>
							<s:text name="userHero.skill2"></s:text>
						</td>
						<td>
							<s:text name="userHero.skill3"></s:text>
						</td>
						<td>
							<s:text name="userHero.skill4"></s:text>
						</td>
						<td>
							<s:text name="userHero.parry"></s:text>
						</td>
						<td>
							<s:text name="userHero.crit"></s:text>
						</td>
						<td>
							<s:text name="userHero.dodge"></s:text>
						</td>
						<td>
							<s:text name="userHero.hit"></s:text>
						</td>
						<td>
							<s:text name="userHero.price"></s:text>
						</td>
						<td>
							<s:text name="delete"></s:text>
					    </td>
					</tr>
					<s:iterator var="userHeroSomeInfo" value="userHeroSomeInfoList" status="sta">
						<tr>
							<td>
								${userHeroSomeInfo.userId}
							</td>
							<td>
								${userHeroSomeInfo.userHeroId}
							</td>
							<td>
								${userHeroSomeInfo.heroId}
							</td>
							<td>
								${userHeroSomeInfo.systemHeroId}
							</td>
							<td>
								${userHeroSomeInfo.imgId}
							</td>
							<td>
								${userHeroSomeInfo.cardId}
							</td>
							<td>
								${userHeroSomeInfo.name}
							</td>
							<td>
								${userHeroSomeInfo.pos}
							</td>
							<td>
								${userHeroSomeInfo.level}
							</td>
							<td>
								${userHeroSomeInfo.exp}
							</td>
							<td>
								${userHeroSomeInfo.dexp}
							</td>
							<td>
								${userHeroSomeInfo.career}
							</td>
							<td>
								${userHeroSomeInfo.life}
							</td>
							<td>
								${userHeroSomeInfo.physicalAttack}
							</td>
							<td>
								${userHeroSomeInfo.physicalDefense}
							</td>
							<td>
								${userHeroSomeInfo.plan}
							</td>
							<td>
								${userHeroSomeInfo.normalPlan}
							</td>
							<td>
								${userHeroSomeInfo.skill1}
							</td>
							<td>
								${userHeroSomeInfo.skill2}
							</td>
							<td>
								${userHeroSomeInfo.skill3}
							</td>
							<td>
								${userHeroSomeInfo.skill4}
							</td>
							<td>
								${userHeroSomeInfo.parry}
							</td>
							<td>
								${userHeroSomeInfo.crit}
							</td>
							<td>
								${userHeroSomeInfo.dodge}
							</td>
							<td>
								${userHeroSomeInfo.hit}
							</td>
							<td>
								${userHeroSomeInfo.price}
							</td>
							<td>
								<a href="#" onclick='del("${user.userId}", "${userHeroSomeInfo.userHeroId}", "${user.mainRoleId}", "${userHeroSomeInfo.heroId}")'><s:text name="delete"></s:text></a>
							</td>
						</tr>
					</s:iterator>
				</c:if>
				</table>
		</form>
	</body>
</html>