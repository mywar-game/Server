<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>装备扩展常量列表</title>

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
			ajaxobj.url="reflashConstantCache?cacheType=<s:property value='@com.dataconfig.constant.ReflashCacheConstant@REFLASH_TYPE_TECHNOLOGY_EQUIPMENT_EXT'/>&number="+Math.random();
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新装备扩展常量缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新装备扩展常量缓存失败!');
				}
		    }
			ajaxobj.send();
		}
	</script>
	<body>
		<input type="button" value="刷新装备扩展常量缓存" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="20" align="center">
					<center>
						装备扩展常量列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					装备主键ID
				</td>
				<td>
					强化系数
				</td>
				<td>
					强化攻击偏移值
				</td>
				<td>
					强化消耗金币系数
				</td>
				<td>
					升品质装备目标ID
				</td>
				<td>
					升品质消耗材料列表
				</td>
				<td>
					升品质等级折损系数
				</td>
				<td>
					升等级装备目标ID
				</td>
				<td>
					升等级消耗金钱
				</td>
				<td>
					要求铁匠铺等级
				</td>
				<td>
					转换武器功能
				</td>
			</tr>
			<s:iterator var="equipmentExt" value="equipmentExtList">
				<tr>
					<td>
						${equipmentExt.equipmentId}
					</td>
					<td>
						${equipmentExt.strengthValue}
					</td>
					<td>
						${equipmentExt.strengthDeviant}
					</td>
					<td>
						${equipmentExt.strengthCostMoney}
					</td>
					<td>
						${equipmentExt.qualityLevelUp}
					</td>
					<td>
						${equipmentExt.qualityCostMaterials}
					</td>
					<td>
						${equipmentExt.qualityWreck}
					</td>
					<td>
						${equipmentExt.rankUp}
					</td>
					<td>
						${equipmentExt.rankCostMoney}
					</td>
					<td>
						${equipmentExt.buildingLevelLimit}
					</td>
					<td>
						${equipmentExt.changHeroWeapon}
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="20">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>