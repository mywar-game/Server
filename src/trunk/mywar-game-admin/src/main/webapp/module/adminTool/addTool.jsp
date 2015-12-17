<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="chooseTool"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<base target="_self">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script src="../../js/jquery/jquery.min.js"></script>
	<script type="text/javascript" >
		var toolTypeArr = new Array();
		var toolArr = new Array();
		var equipmentArr = new Array();
		var heroArr = new Array();
		var artifactArr = new Array();
		
		<s:iterator var="sysHero" value="heroList">
			var hero = new Object();
			hero.id = ${sysHero.systemHeroId};
			hero.name = '${sysHero.heroName}';
			hero.color = ${sysHero.heroColor};
		
			heroArr.push(hero);
		</s:iterator>
		
		<s:iterator var="sysTool" value="toolList" >
			var tool = new Object();
			tool.id = ${sysTool.toolId};
			tool.type = ${sysTool.type};
			tool.name = '${sysTool.name}';
			
			toolArr.push(tool);
		</s:iterator>
		
		/**
		<s:iterator value="artifactIdNameMap">
			var artifact = new Object();
			artifact.id = ${key};
			artifact.name = '${value}';
			
			artifactArr.push(artifact);
		</s:iterator>
		
		<s:iterator value="equipmentMap" >
			var equipment = new Object();
			equipment.id = ${key};
			equipment.name = '${value}';
		
			equipmentArr.push(equipment);
		</s:iterator>
		**/
		
		<s:iterator value="typeNameMap" >
			var toolType = new Object();
			toolType.typeId = ${key};
			toolType.typeName = '${value}';
			
			toolTypeArr.push(toolType);
		</s:iterator>
		
		
		var equipmentColorArray = new Array();
		/**
		<s:generator separator="," val="%{getText('userEquipment.color_values')}">
			<s:iterator var="str">
					equipmentColorArray[${str}] = "<s:text name="%{'userEquipment.color_'+#str}"></s:text>";
			</s:iterator>
		</s:generator>
		**/
		
		// 选择类型
		function changeSelect(typeSelect) {
			var typeId = typeSelect.value;
			var obj = document.getElementById('chooseTool');
			obj.options.length = 0;

			switch (parseInt(typeId)) {
				case 2001: // 装备
				{
					for (var i = 0; i < equipmentArr.length; i++) {
						obj.add(new Option(equipmentArr[i].name, equipmentArr[i].id));
					}
					break;
				}
				case 4:
				{
					for (var i = 0; i < heroArr.length; i++) {						
						obj.add(new Option(heroArr[i].name + '_' + heroArr[i].color, heroArr[i].id));
					}
					break;
				}
				case 9001:
				{
					for (var i = 0; i < artifactArr.length; i++) {
						obj.add(new Option(artifactArr[i].name, artifactArr[i].id));
					}
					break;
				}
				default: {
					for (var i = 0; i < toolArr.length; i++) {
						if (toolArr[i].type == typeId) {
							obj.add(new Option(toolArr[i].name, toolArr[i].id));
						}
					}
					break;
				}
			}

			if (obj.options.length == 0) {
				var index = typeSelect.selectedIndex;
				obj.add(new Option(typeSelect.options[index].label, typeSelect.value));
			}
		}

		// 搜索
		function searchTool() {
			var searchName = document.getElementById("inputText").value;
			var typeId = document.getElementById('toolType').value;
			if (searchName == '' || searchName == null) {
				return;
			}
			
			if (typeId == '') {
				alert("请先选择类型。");
				return;
			}
			var obj = document.getElementById('chooseTool');
			obj.options.length = 0;

			switch (parseInt(typeId)) {
				case 2001: // 装备
				{
					for (var i = 0; i < equipmentArr.length; i++) {
						if (equipmentArr[i].name.indexOf(searchName) != -1) {
							obj.add(new Option(equipmentArr[i].name, equipmentArr[i].id));
						}
					}
					break;
				}
				case 4:
				{
					for (var i = 0; i < heroArr.length; i++) {
						if (heroArr[i].name.indexOf(searchName) != -1) {							
							obj.add(new Option(heroArr[i].name + '_' + heroArr[i].color, heroArr[i].id));	
						}						
					}
					break;
				}
				case 9001:
				{
					for (var i = 0; i < artifactArr.length; i++) {
						if (artifactArr[i].name.indexOf(searchName) != -1) {
							obj.add(new Option(artifactArr[i].name, artifactArr[i].id));							
						}
					}
					break;
				}
				default: {
					for (var i = 0; i < toolArr.length; i++) {
						if (toolArr[i].type == typeId && 
							toolArr[i].name.indexOf(searchName) != -1) {
							obj.add(new Option(toolArr[i].name, toolArr[i].id));
						}
					}
					break;
				}
			}

			if (obj.options.length == 0) {
				var typeSelect = document.getElementById("toolType");
				var index = typeSelect.selectedIndex;
				obj.add(new Option(typeSelect.options[index].label, typeSelect.value));
			}
			
		}

		function add() {		
			// 类型、id、数量
			var num = document.getElementById("num").value;
			var typeObj = document.getElementById("toolType");
			var toolObj = document.getElementById("chooseTool");
			
			var typeId = typeObj.value;
			var toolId = toolObj.value;
			
			var reg = new RegExp("^[0-9]*$");
			if (num == '' || !reg.test(num) 
					|| typeId == '' || toolId == '') {
				return;
			}
			
			if (num <= 0) {
				alert("数量小于或等于0！");
				return;
			}
			
			switch (parseInt(typeId)) {
				case 1:
				case 2:
				case 6:
				case 7:
				case 12:
				case 100001:
					toolId = 0;				
					break;
				default:
					break;
			}			
		
			var typeIndex = typeObj.selectedIndex;
			var toolIndex = toolObj.selectedIndex;
		
			var ids = typeId + ',' + toolId + ',' + num;
			var names = typeObj.options[typeIndex].label + ',' + toolObj.options[toolIndex].label + ',' + num;
			
			window.returnValue = ids + '*' + names;
			window.close();
		}
		
	</script>
	
	<body>
		&nbsp;	
		<form >	
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="chooseTool"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>			
				<tr>
					<td>
						<s:text name="tool.toolName"></s:text><s:text name="colon"></s:text>
					</td>
					<td >
						<input type="text" name="inputText" id="inputText" /> 
						<input type="button" onclick="searchTool()" name="search" class="button" value="<s:text name="search"></s:text>" />
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="tool.chooseTool"></s:text><s:text name="colon"></s:text>
					</td>
					<td >
						<select name="toolType" id="toolType" onchange="changeSelect(this)" class="select">
							<option value=""><s:text name="pleaseSelect"></s:text></option>
							<s:iterator value="typeNameMap" >
								<option value="${key}" >${value}</option>
							</s:iterator>						
						</select>
						
						<select name="chooseTool" id="chooseTool" class="select">
							<option value=""><s:text name="pleaseSelect"></s:text></option>
							<s:iterator value="typeNameMap" >
								<option value="${key}" >${value}</option>
							</s:iterator>													
						</select>
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="tool.num"></s:text><s:text name="colon"></s:text>
					</td>
					<td >
						<input type="text" id="num" name="num" />
					</td>	
				</tr>
				<tr>
					<td colspan="5" align="center">
						<input type="submit" onclick="add()" value="<s:text name='add'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
<script src="../../js/jquery/jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("form").validate({
			rules:{
				toolType:{
					required:true
				},
				chooseTool:{
					required:true
				},
				num:{
					required:true,
					digits:true
				}
			}		
		});	
	});
</script>
