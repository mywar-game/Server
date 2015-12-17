<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>活跃事件常量列表</title>

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
			ajaxobj.url="reflashConstantCache?cacheType=<s:property value='@com.dataconfig.constant.ReflashCacheConstant@REFLASH_TYPE_ACTIVE_EVENT'/>&number="+Math.random();
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新活跃事件常量缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新活跃事件常量缓存失败!');
				}
		    }
			ajaxobj.send();
		}
	</script>
	<body>
		<input type="button" value="刷新活跃事件常量缓存" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						活跃事件常量列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					活跃事件id
				</td>
				<td>
					活跃事件名字
				</td>
				<td>
					活跃事件完成需要次数
				</td>
				<td>
					奖励类型
				</td>
				<td>
					分次奖励时，每次奖励的数量
				</td>
				<td>
					总共奖励的数量
				</td>
				<td>
					完成目标的类型
				</td>
				<td>
					完成目标的类型
				</td>
				<td>
					是否能前往
				</td>
			</tr>
			<s:iterator var="activeEvent" value="activeEventList">
				<tr>
					<td>
						${activeEvent.activeEventId}
					</td>
					<td>
						${activeEvent.activeEventName}
					</td>
					<td>
						${activeEvent.finishNeedTimes}
					</td>
					<td>
						${activeEvent.rewardType}
					</td>
					<td>
						${activeEvent.rewardNumPerTime}
					</td>
					<td>
						${activeEvent.rewardTotalNum}
					</td>
					<td>
						${activeEvent.targetType}
					</td>
					<td>
						${activeEvent.targetNum}
					</td>
					<td>
						${activeEvent.canGoto}
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