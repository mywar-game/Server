<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>宠物常量列表</title>

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
			ajaxobj.url="reflashConstantCache?cacheType=30&number="+Math.random();
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新宠物装备缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新宠物装备缓存失败!');
				}
		    }
			ajaxobj.send();
		}
		
		function del(petEquipmentId) {
			if (window.confirm('确定删除宠物装备额外加成')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delPPetEquipment?=petEquipmentId" + petEquipmentId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(petEquipmentId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(petEquipmentId) {
			window.location.href = "updatePPetEquipment?petEquipmentId=" + petEquipmentId;
		}
	
		function add() {
			window.location.href = "addPPetEquipment";
		}
	</script>
	<body>
		<input type="submit" value="添加宠物装备额外加成" class="button" onclick=add(); />
		<input type="button" value="刷新宠物装备额外加成" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="50" align="center">
					<center>
						宠物装备额外加成常量
					</center>
				</td>
			</tr>
			<tr>
				<td>
					宠物装备加成编号
				</td>
				<td>
					宠物类型
				</td>
				<td>
					最低攻击加成
				</td>
				<td>
					最高攻击加成
				</td>
				<td>
					攻击速度加成
				</td>
				<td>
					暴击加成
				</td>
				<td>
					命中加成
				</td>
				<td>
					等级上限
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="pPetEquipment" value="petEquipmentList">
				<tr id="${pPetEquipment.petEquipmentId}">
					<td>
						${pPetEquipment.petEquipmentId}
					</td>
					<td>
						${pPetEquipment.type}
					</td>
					<td>
						${pPetEquipment.lowAttackAdd}
					</td>
					<td>
						${pPetEquipment.highAttackAdd}
					</td>
					<td>
						${pPetEquipment.attackSpeedAdd}
					</td>
					<td>
						${pPetEquipment.cirtRateAdd}
					</td>
					<td>
						${pPetEquipment.hitRateAdd}
					</td>
					<td>
						${pPetEquipment.petLevelMax}
					</td>
					<td>
						<a href="#" onclick='del("${pPetEquipment.petEquipmentId}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${pPetEquipment.petEquipmentId}")'><s:text name="update"></s:text></a>
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