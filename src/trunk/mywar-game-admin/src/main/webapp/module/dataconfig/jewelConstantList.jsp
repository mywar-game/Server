<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>宝石常量列表</title>

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
			ajaxobj.url="reflashConstantCache?cacheType=<s:property value='@com.dataconfig.constant.ReflashCacheConstant@REFLASH_TYPE_JEWEL_CONSTANT'/>&number="+Math.random();
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新宝石常量缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新宝石常量缓存失败!');
				}
		    }
			ajaxobj.send();
		}
	</script>
	<body>
		<input type="button" value="刷新宝石常量缓存" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						宝石常量列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					宝石id
				</td>
				<td>
					宝石名称
				</td>
				<td>
					宝石描述
				</td>
				<td>
					宝石等级
				</td>
				<td>
					装备品质
				</td>
				<td>
					类型
				</td>
				<td>
					增加值
				</td>
				<td>
					合成宝石目标ID
				</td>
				<td>
					合成需要数量
				</td>
				<td>
					合成消耗金钱数量
				</td>
			</tr>
			<s:iterator var="jewelConstant" value="jewelConstantList">
				<tr>
					<td>
						${jewelConstant.jewjelId}
					</td>
					<td>
						${jewelConstant.name}
					</td>
					<td>
						${jewelConstant.description}
					</td>
					<td>
						${jewelConstant.level}
					</td>
					<td>
						${jewelConstant.quality}
					</td>
					<td>
						${jewelConstant.type}
					</td>
					<td>
						${jewelConstant.value}
					</td>
					<td>
						${jewelConstant.targetId}
					</td>
					<td>
						${jewelConstant.composeValue}
					</td>
					<td>
						${jewelConstant.composeCostMoney}
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