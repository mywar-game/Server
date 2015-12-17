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
			ajaxobj.url="reflashConstantCache?cacheType=27&number="+Math.random();
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新宠物常量配置缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新宠物常量配置缓存失败!');
				}
		    }
			ajaxobj.send();
		}
		
		function del(petId) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="petConstant.petId"></s:text></s:param><s:param>'+petId+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delPetConstant?petId=" + petId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(petId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(petId) {
			window.location.href = "updatePetConstant?petId=" + petId;
		}
	
		function add() {
			window.location.href = "addPetConstant";
		}
	</script>
	<body>
		<input type="submit" value="添加宠物常量" class="button" onclick=add(); />
		<input type="button" value="刷新宠物常量配置缓存" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="50" align="center">
					<center>
						宠物常量列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="petConstant.petId"></s:text>
				</td>
				<td>
					<s:text name="petConstant.name"></s:text>
				</td>
				<td>
					<s:text name="petConstant.description"></s:text>
				</td>
				<td>
					<s:text name="petConstant.quality"></s:text>
				</td>
				<td>
					<s:text name="petConstant.stamina"></s:text>
				</td>
				<td>
					<s:text name="petConstant.attackSpeed"></s:text>
				</td>
				<td>
					<s:text name="petConstant.attackSpeedAdd"></s:text>
				</td>
				<td>
					<s:text name="petConstant.lowAttack"></s:text>
				</td>
				<td>
					<s:text name="petConstant.lowAttackAdd"></s:text>
				</td>
				<td>
					<s:text name="petConstant.highAttack"></s:text>
				</td>
				<td>
					<s:text name="petConstant.highAttackAdd"></s:text>
				</td>
				<td>
					<s:text name="petConstant.cirtRate"></s:text>
				</td>
				<td>
					<s:text name="petConstant.cirtRateAdd"></s:text>
				</td>
				<td>
					<s:text name="petConstant.hitRate"></s:text>
				</td>
				<td>
					<s:text name="petConstant.hitRateAdd"></s:text>
				</td>
				<td>
					<s:text name="petConstant.moveSpeed"></s:text>
				</td>
				<td>
					<s:text name="petConstant.attackScope"></s:text>
				</td>
				<td>
					<s:text name="petConstant.activeAttackScope"></s:text>
				</td>
				<td>
					<s:text name="petConstant.life"></s:text>
				</td>
				<td>
					<s:text name="petConstant.buyType"></s:text>
				</td>
				<td>
					<s:text name="petConstant.type"></s:text>
				</td>
				<td>
					<s:text name="petConstant.price"></s:text>
				</td>
				<td>
					<s:text name="petConstant.unlockGolden"></s:text>
				</td>
				<td>
					<s:text name="petConstant.unlockLevel"></s:text>
				</td>

				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="petConstant" value="petConstantList">
				<tr id="${petConstant.petId}">
					<td>
						${petConstant.petId}
					</td>
					<td>
						${petConstant.name}
					</td>
					<td>
						${petConstant.description}
					</td>
					<td>
						<s:text name="%{'petConstant.quality_'+#petConstant.quality}"></s:text>
					</td>
					<td>
						${petConstant.stamina}
					</td>
					<td>
						${petConstant.attackSpeed}
					</td>
					<td>
						${petConstant.attackSpeedAdd}
					</td>
					<td>
						${petConstant.lowAttack}
					</td>
					<td>
						${petConstant.lowAttackAdd}
					</td>
					<td>
						${petConstant.highAttack}
					</td>
					<td>
						${petConstant.highAttackAdd}
					</td>
					<td>
						${petConstant.cirtRate}
					</td>
					<td>
						${petConstant.cirtRateAdd}
					</td>
					<td>
						${petConstant.hitRate}
					</td>
					<td>
						${petConstant.hitRateAdd}
					</td>
					<td>
						${petConstant.moveSpeed}
					</td>
					<td>
						${petConstant.attackScope}
					</td>
					<td>
						${petConstant.activeAttackScope}
					</td>
					<td>
						${petConstant.life}
					</td>
					<td>
						<s:text name="%{'petConstant.buyType_'+#petConstant.buyType}"></s:text>
					</td>
					<td>
						<s:text name="%{'petConstant.type_'+#petConstant.type}"></s:text>
					</td>
					<td>
						${petConstant.price}
					</td>
					<td>
						${petConstant.unlockGolden}
					</td>
					<td>
						${petConstant.unlockLevel}
					</td>

					<td>
						<a href="#" onclick='del("${petConstant.petId}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${petConstant.petId}")'><s:text name="update"></s:text></a>
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