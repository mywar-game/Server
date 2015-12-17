<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>怪物图鉴列表</title>

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
			ajaxobj.url="reflashConstantCache?cacheType=<s:property value='@com.dataconfig.constant.ReflashCacheConstant@REFLASH_TYPE_MONSTER_ATTRIBUTE'/>&number="+Math.random();
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新怪物图鉴缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新怪物图鉴缓存失败!');
				}
		    }
			ajaxobj.send();
		}
	</script>
	<body>
		<input type="button" value="刷新怪物图鉴缓存" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="100" align="center">
					<center>
						怪物图鉴列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					野怪头像ID
				</td>
				<td>
					野怪姓名
				</td>
				<td>
					攻击星级
				</td>
				<td>
					生命值星级
				</td>
				<td>
					护甲星级
				</td>
				<td>
					攻击速度星级
				</td>
				<td>
					移动速度星级
				</td>
				<td>
					攻击类型
				</td>
				<td>
					攻击方式
				</td>
				<td>
					怪物信息描述
				</td>
				<td>
					技能常量IDS
				</td>
			</tr>
			<s:iterator var="monsterAttribute" value="monsterAttributeList">
				<tr>
					<td>
						${monsterAttribute.monsterId}
					</td>
					<td>
						${monsterAttribute.name}
					</td>
					<td>
						${monsterAttribute.attack}
					</td>
					<td>
						${monsterAttribute.life}
					</td>
					<td>
						${monsterAttribute.armor}
					</td>
					<td>
						${monsterAttribute.attackSpeed}
					</td>
					<td>
						${monsterAttribute.moveSpeed}
					</td>
					<td>
						${monsterAttribute.attackType}
					</td>
					<td>
						${monsterAttribute.attackRange}
					</td>
					<td>
						${monsterAttribute.monsterDescription}
					</td>
					<td>
						${monsterAttribute.skillIds}
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="100">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>