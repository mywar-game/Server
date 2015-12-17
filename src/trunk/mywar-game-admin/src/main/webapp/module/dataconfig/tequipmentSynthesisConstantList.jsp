<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="tequipmentSynthesisConstantListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		function reflashconstantcache() {
			var ajaxobj = new Ajax();
			ajaxobj.url="reflashConstantCache?cacheType=19&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="tequipmentSynthesisConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="tequipmentSynthesisConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		function update(equipmentId,equipmentName) {
			window.location.href = "updateTEquipmentSynthesisConstant?equipmentId="+equipmentId+"&equipmentName="+encodeURI(encodeURI(equipmentName));
		}
		
		function del(equipmentId) {
			if(window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='equipmentSynthesisConstant.equipmentId'/></s:param><s:param>"+equipmentId+"</s:param></s:text>")){
					var ajaxobj = new Ajax();
			        ajaxobj.url="delTEquipmentSynthesisConstant?equipmentId="+equipmentId;
			        ajaxobj.callback=function(){
			        	var tr = document.getElementById(equipmentId);
						var table = document.getElementById("table");
				        table.deleteRow(tr.rowIndex);  
			        }
				    ajaxobj.send();
				}
				
		}
		
		function  add(){
				window.location.href="addTEquipmentSynthesisConstant";
			}
	</script>
	<body>
	<input type="submit" value="<s:text name="addTEquipmentSynthesisConstantJsp.title"></s:text>" class="button" onclick="add()" />
	<input type="button" value='<s:text name="tequipmentSynthesisConstantListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashconstantcache()" />
		<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center id="table">
				<tr class="border">
					<td class="td_title" colspan="10" align="center">
						<center><s:text name="tequipmentSynthesisConstantListJsp.title"></s:text> &nbsp;</center>
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="equipmentSynthesisConstant.equipmentId"></s:text>
					</td>
					<td>
						<s:text name="equipmentSynthesisConstant.equipmentName"></s:text>
					</td>
					<td>
						<s:text name="equipmentSynthesisConstant.buildMeterials1"></s:text>
					</td>
					<td>
						<s:text name="equipmentSynthesisConstant.buildMeterials2"></s:text>
					</td>
					<td>
						<s:text name="equipmentSynthesisConstant.buildMeterials3"></s:text>
					</td>
					
					<td>
						<s:text name="update"></s:text>
					</td>
					<td>
						<s:text name="delete"></s:text>
					</td>
				</tr>
			<s:iterator var="tequipmentSynthesis" value="tequipmentSynthesisConstantList">
				<tr id="${tequipmentSynthesis.equipmentId}">
				
					<td>
							${tequipmentSynthesis.equipmentId}
					</td>
					
					<td>
							${tequipmentSynthesis.equipmentName}
					</td>
					
					<td>
						<s:generator separator=":" val="#tequipmentSynthesis.buildMeterials1" var="args">
							<s:iterator var="arg" status="sta">
								<s:if test="#sta.index == 0"><s:property value="treasureIDNameMap[#arg]"/></s:if>
								<s:if test="#sta.index == 1">x ${arg}</s:if>
							</s:iterator>
						</s:generator>
					</td>
					<td>
						<s:generator separator=":" val="#tequipmentSynthesis.buildMeterials2" var="args">
							<s:iterator var="arg" status="sta">
								<s:if test="#sta.index == 0"><s:property value="treasureIDNameMap[#arg]"/></s:if>
								<s:if test="#sta.index == 1">x ${arg}</s:if>
							</s:iterator>
						</s:generator>
					</td>
					<td>
						<s:generator separator=":" val="#tequipmentSynthesis.buildMeterials3" var="args">
							<s:iterator var="arg" status="sta">
								<s:if test="#sta.index == 0"><s:property value="treasureIDNameMap[#arg]"/></s:if>
								<s:if test="#sta.index == 1">x ${arg}</s:if>
							</s:iterator>
						</s:generator>
					</td>
					<td>	
						<a href="#" onclick='update(${tequipmentSynthesis.equipmentId},"${tequipmentSynthesis.equipmentName}")'><s:text name="update"></s:text></a>
					</td>
					<td>	
						<a href="#" onclick='del("${tequipmentSynthesis.equipmentId}")'><s:text name="delete"></s:text></a>
					</td>
				</tr>
			</s:iterator>
				<tr>
				<td colspan="10">
					<aldtags:pageTag />
				</td>
			</tr>
				
		</table>
	</body>
</html>