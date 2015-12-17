<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:text name="sSkillConstantListJsp.title"></s:text></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
  </head>
  <script type="text/javascript" src="../../js/ajax.js"></script>
  <script type="text/javascript">
	function reflashskillcache() {
		var ajaxobj = new Ajax();
		ajaxobj.url="reflashConstantCache?cacheType=2&number="+Math.random();;
	    ajaxobj.callback=function(){
	    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
	    	//alert(responseMsg.erroCodeNum);
	    	if (responseMsg.erroCodeNum == 0) {
				alert('<s:text name="action.success"><s:param><s:text name="sSkillConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
			} else {
				alert('<s:text name="action.fail"><s:param><s:text name="sSkillConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
			}
	    }
		ajaxobj.send();
	 }
			
  	function del(skillConstantId){
			if(window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='skillConstantId'/></s:param><s:param>"+skillConstantId+"</s:param></s:text>")){
				var ajaxobj = new Ajax();
		        ajaxobj.url="delSSkillConstant?skillConstantId="+skillConstantId;
		        ajaxobj.callback=function(){
		        	//alert(ajaxobj.gettext());
		        	var tr = document.getElementById(skillConstantId);
					var table = document.getElementById("table");
			        table.deleteRow(tr.rowIndex);  
		        }
			    ajaxobj.send();
			}
			
		}
		
		function update(skillConstantId){
			//alert("skillConstantId "+skillConstantId);
			window.location.href="updateSSkillConstant?skillConstantId="+skillConstantId;
		}
		
		function  add(){
			window.location.href="addSSkillConstant";
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
    <input type="button" value="<s:text name="addSSkillConstantJsp.title"></s:text>" class="button" onclick="add()" />
    <input type="button" value='<s:text name="sSkillConstantListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashskillcache()" />
    <form action="sSkillConstantList" method="post">
    	<s:text name="skillConstant.skillName"></s:text><s:text name="colon"></s:text>
    	<input type="text" name="name" value="${name}">
    	<input type="submit" value="<s:text name="search"></s:text>" class="button" />
    </form>
    <table id="table" cellpadding="3" cellspacing="1" border="0"
			width="100%" align=center>
    	<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="sSkillConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="skillConstant.skillConstantId"></s:text>
				</td>
				<td>
					<s:text name="skillConstant.skillId"></s:text>
				</td>
				<td>
					<s:text name="skillConstant.skillName"></s:text>
				</td>
				<td>
					<s:text name="skillConstant.skillDesc"></s:text>
				</td>
				<td>
					<s:text name="skillConstant.level"></s:text>
				</td>
				<td>
					<s:text name="skillConstant.skillType"></s:text>
				</td>
				<td>
					<s:text name="skillConstant.target"></s:text>
				</td>
				<td>
					<s:text name="skillConstant.rangeCenter"></s:text>
				</td>
				<td>
					<s:text name="skillConstant.skillRange"></s:text>
				</td>
				<td>
					<s:text name="skillConstant.castRange"></s:text>
				</td>
				<td>
					<s:text name="skillConstant.coolingTime"></s:text>
				</td>
				<td>
					<s:text name="skillConstant.skillEffect"></s:text>
				</td>
				<td>
					<s:text name="skillConstant.isSpecial"></s:text>
				</td>
				<td>
					<s:text name="skillConstant.numString"></s:text>
				</td>
				<td>
					<s:text name="skillConstant.skillTiggerCondition"></s:text>
				</td>
				<td>
					<s:text name="skillConstant.skillEndCondition"></s:text>
				</td>
				
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr> 
			
			<s:iterator var="sskillConstant" value="sskillConstantList" status="sta">
				<tr id='<s:property value="#sskillConstant.skillConstantId"/>'>
					<td>
						<s:property value="#sskillConstant.skillConstantId"/>
					</td>
					<td>
						<s:property value="#sskillConstant.skillId"/>
					</td>
					<td>
						<s:property value="#sskillConstant.skillName"/>
					</td>
					<td>
						<s:property value="#sskillConstant.skillDesc"/>
					</td>
					<td>
						<s:property value="#sskillConstant.level"/>
					</td>
					<td>
						<s:text name="%{'skillType_'+#sskillConstant.skillType}"></s:text>
					</td>
					<td>
						<s:text name="%{'target_'+#sskillConstant.target}"></s:text>
					</td>
					<td>
						<s:text name="%{'rangeCenter_'+#sskillConstant.rangeCenter}"></s:text>
					</td>
					<td>
						<s:property value="#sskillConstant.skillRange"/>
					</td>
					<td>
						<s:property value="#sskillConstant.castRange"/>
					</td>
					<td>
						<s:property value="#sskillConstant.coolingTime"/>
					</td>
					<td>
						<s:property value="#sskillConstant.skillEffect"/>
					</td>
					<td>
						<s:text name="%{'isSpecial_'+#sskillConstant.isSpecial}"></s:text>
					</td>
					<td>
						<s:property value="#sskillConstant.numString"/>
					</td>
					<td>
						<s:property value="#sskillConstant.skillTiggerCondition"/>
					</td>
					<td>
						<s:property value="#sskillConstant.skillEndCondition"/>
					</td>
				

					<td>
						<a href="#" onclick='del(<s:property value="#sskillConstant.skillConstantId"/>)'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update(<s:property value="#sskillConstant.skillConstantId"/>)'><s:text name="update"></s:text></a>
					</td>					
				</tr>
			</s:iterator>
				
			
    		<tr>
				<td colspan="22" id="page">
					<aldtags:pageTag />
				</td>
			</tr>
    </table>
  </body>
</html>
