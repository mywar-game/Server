<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:text name="tMallTreasureConstantListJsp.title"></s:text></title>
    
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
		ajaxobj.url="reflashConstantCache?cacheType=24&number="+Math.random();;
	    ajaxobj.callback=function(){
	    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
	    	//alert(responseMsg.erroCodeNum);
	    	if (responseMsg.erroCodeNum == 0) {
				alert('<s:text name="action.success"><s:param><s:text name="tMallTreasureConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
			} else {
				alert('<s:text name="action.fail"><s:param><s:text name="tMallTreasureConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
			}
	    }
		ajaxobj.send();
	 }
			
  	function del(treasureId,category,treasureName){
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="mallTreasureConstant.treasureName"></s:text></s:param><s:param>'+treasureName+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
		        ajaxobj.url="delTMallTreasureConstant?treasureId="+treasureId+"&category="+category;
		        ajaxobj.callback=function(){
		        	var tr = document.getElementById(treasureId+","+category);
					var table = document.getElementById("table");
			        table.deleteRow(tr.rowIndex);  
		        }
			    ajaxobj.send();
			}
			
		}
		
		function update(treasureId,category,treasureName){
			window.location.href="updateTMallTreasureConstant?treasureId="+treasureId+"&category="+category+"&treasureName="+encodeURI(encodeURI(treasureName));
		}
		
		function  add(){
			window.location.href="addTMallTreasureConstant";
		}
  </script>
  <body>
    <input type="submit" value="<s:text name="addTMallTreasureConstantJsp.title"></s:text>" class="button" onclick="add()" />
    <input type="button" value='<s:text name="tMallTreasureConstantListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashskillcache()" />
    <table id="table" cellpadding="3" cellspacing="1" border="0"
			width="100%" align=center>
    	<tr class="border">
				<td class="td_title" colspan="20" align="center">
					<center>
						<s:text name="tMallTreasureConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="mallTreasureConstant.treasureId"></s:text>
				</td>
				<td>
					<s:text name="mallTreasureConstant.treasureName"></s:text>
				</td>
				<td>
					<s:text name="mallTreasureConstant.category"></s:text>
				</td>
				<td>
					<s:text name="mallTreasureConstant.type"></s:text>
				</td>
				<td>
					<s:text name="mallTreasureConstant.originalPrice"></s:text>
				</td>
				<td>
					<s:text name="mallTreasureConstant.price"></s:text>
				</td>
				<td>
					<s:text name="mallTreasureConstant.canArenaBuy"></s:text>
				</td>
				<td>
					<s:text name="mallTreasureConstant.needArenaScore"></s:text>
				</td>
				<td>
					<s:text name="mallTreasureConstant.lastTime"></s:text>
				</td>
				<td>
					<s:text name="mallTreasureConstant.state"></s:text>
				</td>
				
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr> 
				
			<s:iterator var="tMallTreasureConstant" value="tmallTreasureConstantList" status="sta">
				<tr id="${tMallTreasureConstant.id.treasureId},${tMallTreasureConstant.id.category}">
					<td>
						<s:property value="#tMallTreasureConstant.id.treasureId"/>
					</td>
					<td>
						<s:if test="#tMallTreasureConstant.id.category == 2">
							<a href="eEquipmentConstantList?id=${tMallTreasureConstant.id.treasureId}">
								${tMallTreasureConstant.treasureName}
							</a>
						</s:if>
						<s:elseif test="#tMallTreasureConstant.id.category == 1">
							<a href="tTreasureConstantList?id=${tMallTreasureConstant.id.treasureId}">
								${tMallTreasureConstant.treasureName}
							</a>
						</s:elseif>
						<s:else>
							${tMallTreasureConstant.treasureName}
						</s:else>
					</td>
					<td>
						<s:text name="%{'mallTreasureConstant.id.category_'+#tMallTreasureConstant.id.category}"></s:text>
					</td>
					<td>
						<s:text name="%{'mallTreasureConstant.type_'+#tMallTreasureConstant.type}"></s:text>
					</td>
					<td>
						<s:property value="#tMallTreasureConstant.originalPrice"/>
					</td>
					<td>
						<s:property value="#tMallTreasureConstant.price"/>
					</td>
					<td>
						<s:text name="%{'mallTreasureConstant.canArenaBuy_'+#tMallTreasureConstant.canArenaBuy}"></s:text>
					</td>
					<td>
						<s:property value="#tMallTreasureConstant.needArenaScore"/>
					</td>
					<td>
						<s:property value="#tMallTreasureConstant.lastTime"/>
					</td>
					<td>
						<s:text name="%{'mallTreasureConstant.state_'+#tMallTreasureConstant.state}"></s:text>
					</td>
				
					<td>
						<input type="button" value="<s:text name="delete"></s:text>" onclick='del(${tMallTreasureConstant.id.treasureId},${tMallTreasureConstant.id.category},"${tMallTreasureConstant.treasureName}")'/>
					</td>
					<td>
						<input type="button" value="<s:text name="update"></s:text>" onclick='update(${tMallTreasureConstant.id.treasureId},${tMallTreasureConstant.id.category},"${tMallTreasureConstant.treasureName}")'/>
					</td>					
				</tr>
			</s:iterator>
				
    </table>
  </body>
</html>
