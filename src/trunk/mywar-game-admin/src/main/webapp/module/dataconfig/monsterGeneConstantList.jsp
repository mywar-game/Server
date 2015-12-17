<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>丧尸基因常量列表</title>

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
			ajaxobj.url="reflashConstantCache?cacheType=<s:property value='@com.dataconfig.constant.ReflashCacheConstant@REFLASH_TYPE_MONSTER_GENE_CONSTANT'/>&number="+Math.random();
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新丧尸基因常量缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新丧尸基因常量缓存失败!');
				}
		    }
			ajaxobj.send();
		}
	</script>
	<body>
		<input type="button" value="刷新丧尸基因常量缓存" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="20" align="center">
					<center>
						丧尸基因常量列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					丧失基因id
				</td>
				<td>
					丧尸基因名称
				</td>
				<td>
					基因描述
				</td>
				<td>
					丧失基因技能
				</td>
				<td>
					最低攻击加成
				</td>
				<td>
					最高攻击加成
				</td>
				<td>
					攻速加成
				</td>
				<td>
					丧失命中加成
				</td>
				<td>
					丧失基因暴击加成
				</td>
				<td>
					怪物编号
				</td>
				<td>
					伪装消耗金钱
				</td>
				<td>
					伪装持续时间
				</td>
				<td>
					钻石伪装时间
				</td>
			</tr>
			<s:iterator var="monsterGeneConstant" value="monsterGeneConstantList">
				<tr>
					<td>
						${monsterGeneConstant.monsterGeneId}
					</td>
					<td>
						${monsterGeneConstant.monsterGeneName}
					</td>
					<td>
						${monsterGeneConstant.monsterGeneDescription}
					</td>
					<td>
						${monsterGeneConstant.monsterGeneSkill}
					</td>
					<td>
						${monsterGeneConstant.monsterGeneLowAttackAdd}
					</td>
					<td>
						${monsterGeneConstant.monsterGeneHighAttackAdd}
					</td>
					<td>
						${monsterGeneConstant.monsterGeneAttackSpeedAdd}
					</td>
					<td>
						${monsterGeneConstant.monsterGeneHitRateAdd}
					</td>
					<td>
						${monsterGeneConstant.monsterGeneCirtAdd}
					</td>
					<td>
						${monsterGeneConstant.monsterId}
					</td>
					<td>
						${monsterGeneConstant.costMoney}
					</td>
					<td>
						${monsterGeneConstant.fakeLastTime}
					</td>
					<td>
						${monsterGeneConstant.goldenFakeLastTime}
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