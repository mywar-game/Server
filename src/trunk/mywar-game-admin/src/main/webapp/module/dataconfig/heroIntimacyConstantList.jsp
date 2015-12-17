<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>英雄亲密度配置</title>

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
			ajaxobj.url="reflashConstantCache?cacheType=<s:property value='@com.dataconfig.constant.ReflashCacheConstant@REFLASH_TYPE_HERO_INTIMACY_CONSTANT'/>&number="+Math.random();
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新英雄亲密度配置缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新英雄亲密度配置缓存失败!');
				}
		    }
			ajaxobj.send();
		}
	</script>
	<body>
		<input type="button" value="刷新英雄亲密度配置缓存" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						英雄亲密度配置列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					道具ID
				</td>
				<td>
					亲密值
				</td>
				<td>
					开启等级
				</td>
				<td>
					消耗金钱数量
				</td>
				<td>
					消耗钻石数量
				</td>
				<td>
					钻石额外增加值
				</td>
				<td>
					每天可以购买的次数
				</td>
			</tr>
			<s:iterator var="heroIntimacyConstant" value="heroIntimacyConstantList">
				<tr>
					<td>
						${heroIntimacyConstant.heroIntimacyId}
					</td>
					<td>
						${heroIntimacyConstant.intimacyValue}
					</td>
					<td>
						${heroIntimacyConstant.openLevel}
					</td>
					<td>
						${heroIntimacyConstant.costMoney}
					</td>
					<td>
						${heroIntimacyConstant.costGolden}
					</td>
					<td>
						${heroIntimacyConstant.goldenAdditionalValue}
					</td>
					<td>
						${heroIntimacyConstant.canBuyForDay}
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