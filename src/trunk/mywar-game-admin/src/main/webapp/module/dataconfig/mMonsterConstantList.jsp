<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="mMonsterConstantListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		function reflashmonstercache() {
			var ajaxobj = new Ajax();
			ajaxobj.url="reflashConstantCache?cacheType=5&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="mMonsterConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="mMonsterConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		
		function del(monsterId) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='monsterConstant.monsterId'/></s:param><s:param>"+monsterId+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delMMonsterConstant?monsterId=" + monsterId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(monsterId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(monsterId) {
			window.location.href = "updateMMonsterConstant?monsterId=" + monsterId;
		}
	
		function add() {
			window.location.href = "addMMonsterConstant";
		}
		
		function init(){
			var name = "${name}";
			if (name != "") {
				var td = document.getElementById("page");
				//alert(td.innerHTML);
				var childNodes = td.childNodes;
				for(var k = 0; k<childNodes.length; k++){
					if(childNodes[k].nodeType==1 && childNodes[k].nodeName=="A"){
						childNodes[k].href += "&name="+ encodeURI(encodeURI(name));
					}
				}
			}				
		}
	</script>
	<body onload="init()">
		<input type="button" value="<s:text name="addMMonsterConstantJsp.title"></s:text>" class="button" onclick=add(); />
		<input type="button" value='<s:text name="mMonsterConstantListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashmonstercache()" />
		<form action="mMonsterConstantList" method="post">
	    	<s:text name="monsterConstant.monsterName"></s:text><s:text name="colon"></s:text>
	    	<input type="text" name="name" value="${name}">
	    	<s:text name="monsterConstant.monsterLevel"></s:text><s:text name="colon"></s:text>
	    	<input type="text" name="level" value="${level}" onblur="value=value.replace(/[^\d]/g,'')" >
	    	<input type="submit" value="<s:text name="search"></s:text>" class="button" />
	    </form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="100" align="center">
					<center>
						<s:text name="mMonsterConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="monsterConstant.monsterId"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.monsterName"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.type"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.modelId"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.attribute"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.quality"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.monsterLevel"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.basicLife"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.basicArmor"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.basicHighAttack"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.baseicLowerAttack"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.lifeAdd"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.armorAdd"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.attackAdd"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.attackSpeed"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.critRate"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.dodgeRate"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.hitRate"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.skillCooling"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.moveSpeed"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.attackScope"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.activeAttackScope"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.baseSkill"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.color"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.arenaId"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.battleModel"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>


			<s:iterator var="monsterConstant" value="monsterConstantList">
				<tr id="${monsterConstant.monsterId}">
					<td>
						${monsterConstant.monsterId}
					</td>
					<td>
						${monsterConstant.monsterName}
					</td>
					<td>
						<s:text name="%{'monsterConstant.type_'+#monsterConstant.type}"></s:text>
					</td>
					<td>
						${monsterConstant.modleId}
					</td>
					<td>
						<s:text name="%{'monsterConstant.attribute_'+#monsterConstant.attribute}"></s:text>
					</td>
					<td>
<!--					当id长度为8且以3开头的时候是怪物英雄，此时品质跟英雄品质一样	-->
						<s:if test="(#monsterConstant.monsterId+'').length() == 8 && (#monsterConstant.monsterId+'').substring(0,1) == 3">
							<s:text name="%{'heroConstant.quality_'+#monsterConstant.quality}"></s:text>
						</s:if>
						<s:else>
							<s:text name="%{'monsterConstant.quality_'+#monsterConstant.quality}"></s:text>
						</s:else>
					</td>
					<td>
						${monsterConstant.monsterLevel}
					</td>
					<td>
						${monsterConstant.basicLife}
					</td>
					<td>
						${monsterConstant.basicArmor}
					</td>
					<td>
						${monsterConstant.basicHighAttack}
					</td>
					<td>
						${monsterConstant.baseicLowerAttack}
					</td>
					<td>
						${monsterConstant.lifeAdd}
					</td>
					<td>
						${monsterConstant.armorAdd}
					</td>
					<td>
						${monsterConstant.attackAdd}
					</td>
					<td>
						${monsterConstant.attackSpeed}
					</td>
					<td>
						${monsterConstant.critRate}
					</td>
					<td>
						${monsterConstant.dodgeRate}
					</td>
					<td>
						${monsterConstant.hitRate}
					</td>
					<td>
						${monsterConstant.skillCooling}
					</td>
					<td>
						${monsterConstant.moveSpeed}
					</td>
					<td>
						${monsterConstant.attackScope}
					</td>
					<td>
						${monsterConstant.activeAttackScope}
					</td>
					<td>
						${monsterConstant.baseSkill}
					</td>
					<td>
						<s:text name="%{'monsterConstant.color_'+#monsterConstant.color}"></s:text>
					</td>
					<td>
						${monsterConstant.arenaId}
					</td>
					<td>
						<s:text name="%{'monsterConstant.battleModel_'+#monsterConstant.battleModel}"></s:text>
					</td>
					
					
					<td>
						<a href="#" onclick='del("${monsterConstant.monsterId}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${monsterConstant.monsterId}")'><s:text name="update"></s:text></a>
					</td>
				</tr>
			</s:iterator>


			<tr>
				<td colspan="100" id="page">
					<aldtags:pageTag para1="level" value1="${level}"/>
				</td>
			</tr>
		</table>
	</body>
</html>
