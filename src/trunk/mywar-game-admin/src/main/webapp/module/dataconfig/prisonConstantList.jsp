<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>监狱常量列表</title>

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
			ajaxobj.url="reflashConstantCache?cacheType=<s:property value='@com.dataconfig.constant.ReflashCacheConstant@REFLASH_TYPE_PRISON_CONSTANT'/>&number="+Math.random();
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新监狱常量缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新监狱常量缓存失败!');
				}
		    }
			ajaxobj.send();
		}
	</script>
	<body>
		<input type="button" value="刷新监狱常量缓存" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="50" align="center">
					<center>
						监狱常量列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					监狱常量编号
				</td>
				<td>
					区域编号
				</td>
				<td>
					监狱编号
				</td>
				<td>
					监狱名称
				</td>
				<td>
					监狱描述
				</td>
				<td>
					怪物波数
				</td>
				<td>
					怪物信息
				</td>
				<td>
					怪物出现方式
				</td>
				<td>
					关卡类型
				</td>
				<td>
					关卡模式
				</td>
				<td>
					关卡失败条件
				</td>
				<td>
					首次击破奖励
				</td>
				<td>
					常规掉落
				</td>
				<td>
					看守奖励
				</td>
				<td>
					宠物体力流失率
				</td>
				<td>
					三星级最大时间
				</td>
				<td>
					怪物模型ID
				</td>
				<td>
					能否被看守
				</td>
				<td>
					最大抽取数量
				</td>
				<td>
					最小抽取数量
				</td>
				<td>
					vip增加的抽取数量
				</td>

			</tr>
			<s:iterator var="prisonConstant" value="prisonConstantList">
				<tr>
					<td>
						${prisonConstant.prisonConstantId}
					</td>
					<td>
						${prisonConstant.areaId}
					</td>
					<td>
						${prisonConstant.prisonId}
					</td>
					<td>
						${prisonConstant.prisonName}
					</td>
					<td>
						${prisonConstant.prisonDesc}
					</td>
					<td>
						${prisonConstant.waveNum}
					</td>
					<td>
						${prisonConstant.monsterInfo}
					</td>
					<td>
						${prisonConstant.monsterAppearType}
					</td>
					<td>
						${prisonConstant.bossType}
					</td>
					<td>
						${prisonConstant.checkPointType}
					</td>
					<td>
						${prisonConstant.failNum}
					</td>
					<td>
						${prisonConstant.firstReward}
					</td>
					<td>
						${prisonConstant.normalReward}
					</td>
					<td>
						${prisonConstant.watchReward}
					</td>
					<td>
						${prisonConstant.petLoseSpeed}
					</td>
					<td>
						${prisonConstant.startMaxTime}
					</td>
					<td>
						${prisonConstant.modelId}
					</td>
					<td>
						${prisonConstant.canWatch}
					</td>
					<td>
						${prisonConstant.maxRewardNum}
					</td>
					<td>
						${prisonConstant.minRewardNum}
					</td>
					<td>
						${prisonConstant.vipRewardAdd}
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