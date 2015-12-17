<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <title><s:text name="eEquipmentConstantListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script type="text/javascript">
			function reflashequipmentcache() {
				var ajaxobj = new Ajax();
				ajaxobj.url="reflashConstantCache?cacheType=1&number="+Math.random();
			    ajaxobj.callback=function(){
			    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
			    	//alert(responseMsg.erroCodeNum);
			    	if (responseMsg.erroCodeNum == 0) {
						alert('<s:text name="action.success"><s:param><s:text name="eEquipmentConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
					} else {
						alert('<s:text name="action.fail"><s:param><s:text name="eEquipmentConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
					}
			    }
				ajaxobj.send();
			}
			
  			function del(equipmentId){
  				//alert("equipmentId=="+equipmentId);
				if(window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='equipmentId'></s:text></s:param><s:param>"+equipmentId+"</s:param></s:text>")){
					var ajaxobj = new Ajax();
			        ajaxobj.url="delEEquipmentConstant?equipmentId="+equipmentId;
			        ajaxobj.callback=function(){
			        	//alert(ajaxobj.gettext());
			        	var tr = document.getElementById(equipmentId);
						var table = document.getElementById("table");
				        table.deleteRow(tr.rowIndex);  
			        }
				    ajaxobj.send();
				}
				
			}
			
			function update(equipmentId){
				window.location.href="updateEEquipmentConstant?equipmentId="+equipmentId;
			}
			
			function  add(){
				window.location.href="addEEquipmentConstant";
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
	</head>
	<body onload="init()">
		<input type="submit" value="<s:text name='addEEquipmentConstantJsp.title'></s:text>" class="button" onclick="add()" />
	    <input type="button" value='<s:text name="eEquipmentConstantListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashequipmentcache()" />
	    <form action="eEquipmentConstantList" method="post">
	    	<s:text name="equipmentName"></s:text><s:text name="colon"></s:text><input type="text" name="name" value="${name}">
	    	<input type="submit" value="<s:text name="search"></s:text>" class="button" />
	    </form>
	    <table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
    		<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="eEquipmentConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="equipmentId"></s:text>
				</td>
				<td>
					<s:text name="equipmentName"></s:text>
				</td>
				<td>
					<s:text name="eequipmentConstant.equipmentDescription"></s:text>
				</td>
				<td>
					<s:text name="equipmentLevel"></s:text>
				</td>
				<td>
					<s:text name="equipmentPrice"></s:text>
				</td>
				<td>
					<s:text name="part"></s:text>
				</td>
				<td>
					<s:text name="material"></s:text>
				</td>
				<td>
					<s:text name="exclusiveHeroId"></s:text>
				</td>
				<td>
					<s:text name="highAttack"></s:text>
				</td>
				<td>
					<s:text name="lowerAttack"></s:text>
				</td>
				<td>
					<s:text name="critRate"></s:text>
				</td>
				<td>
					<s:text name="life"></s:text>
				</td>
				<td>
					<s:text name="armor"></s:text>
				</td>
				<td>
					<s:text name="moveSpeed"></s:text>
				</td>
				<td>
					<s:text name="attackSpeed"></s:text>
				</td>
				<td>
					<s:text name="dodgeRate"></s:text>
				</td>
				<td>
					<s:text name="hitRate"></s:text>
				</td>
				<td>
					<s:text name="skillCooling"></s:text>
				</td>
				<td>
					<s:text name="skillPoints"></s:text>
				</td>
				<td>
					<s:text name="eequipmentConstant.pathWay"></s:text>
				</td>

				

				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr> 
			<s:iterator var="eequipmentConstant" value="eequipmentConstantList">
				<tr id="${eequipmentConstant.equipmentId}">
					<td>
						${eequipmentConstant.equipmentId}
					</td>
					<td>
						${eequipmentConstant.equipmentName}
					</td>
					<td>
						${eequipmentConstant.equipmentDescription}
					</td>
					<td>
						${eequipmentConstant.equipmentLevel}
					</td>
					<td>
						${eequipmentConstant.equipmentPrice}
					</td>
					<td>
						<s:text name="%{'eequipmentConstant.part_'+#eequipmentConstant.part}"></s:text>
					</td>
					<td>
						<s:text name="%{'eequipmentConstant.material_'+#eequipmentConstant.material}"></s:text>
					</td>
					<td>
						${heroIdNameMap[eequipmentConstant.exclusiveHeroId]}
					</td>
					<td>
						${eequipmentConstant.highAttack}
					</td>
					<td>
						${eequipmentConstant.lowerAttack}
					</td>
					<td>
						${eequipmentConstant.critRate}
					</td>
					<td>
						${eequipmentConstant.life}
					</td>
					<td>
						${eequipmentConstant.armor}
					</td>
					<td>
						${eequipmentConstant.moveSpeed}
					</td>
					<td>
						${eequipmentConstant.attackSpeed}
					</td>
					<td>
						${eequipmentConstant.dodgeRate}
					</td>
					<td>
						${eequipmentConstant.hitRate}
					</td>
					<td>
						${eequipmentConstant.skillCooling}
					</td>
					<td>
						<s:generator separator="," val="#eequipmentConstant.skillPoints">
							<s:iterator var="str">
								<a href="sSkillConstantList?id=${str}">
									<s:property value="skillIdNameMap[#str]"/><br/>
								</a>
							</s:iterator>						
						</s:generator>
					</td>
					<td>
						<s:generator separator="," val="#eequipmentConstant.pathWay">
							<s:iterator var="one">
								<s:text name="%{'eequipmentConstant.pathWay_'+#one}"></s:text><br/>
							</s:iterator>
						</s:generator>
					</td>

					<td>
						<a href="#" onclick="del(${eequipmentConstant.equipmentId})"><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick="update(${eequipmentConstant.equipmentId})"><s:text name="update"></s:text></a>
					</td>
				</tr>
			</s:iterator>
    		<tr>
				<td colspan="22" id="page">
					<aldtags:pageTag/>
				</td>
			</tr>
	    </table>
	</body>
</html>