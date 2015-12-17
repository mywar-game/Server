<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>训练营配置</title>

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
			ajaxobj.url="reflashConstantCache?cacheType=<s:property value='@com.dataconfig.constant.ReflashCacheConstant@REFLASH_TYPE_TRAINING_CONFIG'/>&number="+Math.random();;
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新训练营配置缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新训练营配置缓存失败!');
				}
		    }
			ajaxobj.send();
		}
		
		function update(level) {
			window.location.href = "updateTrainingConfig?level=" + level;
		}
	
	</script>
	<body>
<!--		<input type="submit" value="添加常量" class="button" onclick=add(); />-->
		<input type="button" value="刷新训练营配置缓存" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						训练营配置
					</center>
				</td>
			</tr>
			<tr>
				<td>
					训练营等级
				</td>
				<td>
					训练时长
				</td>
				<td>
					消耗金币数
				</td>
				<td>
					使用金币的训练效率
				</td>
				<td>
					消耗钻石数
				</td>
				<td>
					使用钻石的训练效率
				</td>
				<td>
					魔鬼训练获得的经验值
				</td>

				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="trainingConfig" value="trainingConfigList">
				<tr>
					<td>
						${trainingConfig.level}
					</td>
					<td>
						${trainingConfig.trainingTime}
					</td>
					<td>
						${trainingConfig.costMoney}
					</td>
					<td>
						${trainingConfig.moneyGetExp}
					</td>
					<td>
						${trainingConfig.costGolden}
					</td>
					<td>
						${trainingConfig.goldenGetExp}
					</td>
					<td>
						${trainingConfig.devilTrainingGetExp}
					</td>
					<td>
						<a href="#" onclick='update("${trainingConfig.level}")'><s:text name="update"></s:text></a>
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