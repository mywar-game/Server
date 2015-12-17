<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:text name="hHeroConstantListJsp.title"></s:text></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	 </head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	 <script type="text/javascript">
  		function del(heroId){
			if(window.confirm("<s:text name='deleteConfirm'> <s:param><s:text name='heroId'></s:text></s:param><s:param>"+heroId+"</s:param></s:text>")){
				var ajaxobj = new Ajax();
		        ajaxobj.url="delHHeroConstant?heroId="+heroId;
		        ajaxobj.callback=function(){
		        	//alert(ajaxobj.gettext());
		        	var tr = document.getElementById(heroId);
					var table = document.getElementById("table");
			        table.deleteRow(tr.rowIndex);  
		        }
			    ajaxobj.send();
			}
			
		}
		
		function reflashherocache() {
			var ajaxobj = new Ajax();
			ajaxobj.url="reflashConstantCache?cacheType=7&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="hHeroConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="hHeroConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		
		function update(heroId){
			window.location.href="updateHHeroConstant?heroId="+heroId;
		}
		
		function  add(){
			window.location.href="addHHeroConstant";
		}
  </script>
  
  <body>
   <input type="submit" value='<s:text name="addHHeroConstantJsp.title"></s:text>' class="button" onclick="add()" />
   <input type="button" value='<s:text name="hHeroConstantListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashherocache()" />
    <table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
    	<tr class="border">
				<td class="td_title" colspan="50" align="center">
					<center>
						<s:text name="hHeroConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="heroId"></s:text>
				</td>
				<td>
					<s:text name="heroName"></s:text>
				</td>
				<td>
					<s:text name="heroDesc"></s:text>
				</td>
				<td>
					<s:text name="basicLife"></s:text>
				</td>
				<td>
					<s:text name="basicArmor"></s:text>
				</td>
				<td>
					<s:text name="heroConstant.basicHighAttack"></s:text>
				</td>
				<td>
					<s:text name="heroConstant.basicLowerAttack"></s:text>
				</td>
				<td>
					<s:text name="heroConstant.lifeAdd"></s:text>
				</td>
				<td>
					<s:text name="heroConstant.armorAdd"></s:text>
				</td>
				<td>
					<s:text name="heroConstant.highAttackAdd"></s:text>
				</td>
				<td>
					<s:text name="heroConstant.highAttackAdd"></s:text>
				</td>
				<td>
					<s:text name="attackSpeed"></s:text>
				</td>
				<td>
					<s:text name="skillResist"></s:text>
				</td>
				<td>
					<s:text name="critRate"></s:text>
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
					<s:text name="moveSpeed"></s:text>
				</td>
				<td>
					<s:text name="attackScope"></s:text>
				</td>
				<td>
					<s:text name="activeAttackScope"></s:text>
				</td>
				<td>
					<s:text name="hero.type"></s:text>
				</td>
				<td>
					<s:text name="baseSkill"></s:text>
				</td>
				<td>
					<s:text name="quality"></s:text>
				</td>
				<td>
					<s:text name="heroConstant.generateHeroName"></s:text>
				</td>
				
				
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr> 
			<s:iterator var="heroConstant" value="hheroConstantList">
				<tr id="${heroConstant.heroId}">
					<td>
						${heroConstant.heroId}
					</td>
					<td>
						${heroConstant.heroName}
					</td>
					<td>
						${heroConstant.heroDesc}
					</td>
					<td>
						${heroConstant.basicLife}
					</td>
					<td>
						${heroConstant.basicArmor}
					</td>
					<td>
						${heroConstant.basicHighAttack}
					</td>
					<td>
						${heroConstant.basicLowerAttack}
					</td>
					<td>
						${heroConstant.lifeAdd}
					</td>
					<td>
						${heroConstant.armorAdd}
					</td>
					<td>
						${heroConstant.highAttackAdd}
					</td>
					<td>
						${heroConstant.lowerAttackAdd}
					</td>
					<td>
						${heroConstant.attackSpeed}
					</td>
					<td>
						${heroConstant.skillResist}
					</td>
					<td>
						${heroConstant.critRate}
					</td>
					<td>
						${heroConstant.dodgeRate}
					</td>
					<td>
						${heroConstant.hitRate}
					</td>
					<td>
						${heroConstant.skillCooling}
					</td>
					<td>
						${heroConstant.moveSpeed}
					</td>
					<td>
						${heroConstant.attackScope}
					</td>
					<td>
						${heroConstant.activeAttackScope}
					</td>
					<td>
						<s:text name="%{'heroConstant.type_'+#heroConstant.type}"></s:text>
					</td>
					<td>
						<s:generator separator="," val="#heroConstant.baseSkill">
							<s:iterator var="skillId">
								<a href="sSkillConstantList?id=${skillId}">
									<s:property value="skillIDNameMap[#skillId]"/>
								</a>
							</s:iterator>
						</s:generator>
					</td>
					<td>
						<s:text name="%{'heroConstant.quality_'+#heroConstant.quality}"></s:text>
					</td>
					<td>
						${heroConstant.generateHeroName}
					</td>

					<td>
						<a href="#" onclick="del(${heroConstant.heroId})"><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick="update(${heroConstant.heroId})"><s:text name="update"></s:text></a>
					</td>
				</tr>
			</s:iterator>
    		<tr>
				<td colspan="50">
					<aldtags:pageTag />
				</td>
			</tr>
    </table>
  </body>
</html>
