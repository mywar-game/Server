<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>宠物进阶配置列表</title>

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
			ajaxobj.url="reflashConstantCache?cacheType=<s:property value='@com.dataconfig.constant.ReflashCacheConstant@REFLASH_TYPE_EVOUTION_GRAPH_CONSTANT'/>&number="+Math.random();
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新宠物进阶配置缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新宠物进阶配置缓存失败!');
				}
		    }
			ajaxobj.send();
		}
	</script>
	<body>
		<input type="button" value="刷新宠物进阶配置缓存" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						宠物进阶配置列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					进化图谱编号
				</td>
				<td>
					进化前宠物编号
				</td>
				<td>
					净化后宠物编号
				</td>
				<td>
					成功百分比
				</td>
			</tr>
			<s:iterator var="evoutionGraphConstant" value="evoutionGraphConstantList">
				<tr>
					<td>
						${evoutionGraphConstant.petEvolutionGraphId}
					</td>
					<td>
						${evoutionGraphConstant.petIdEvolutionBefore}
					</td>
					<td>
						${evoutionGraphConstant.petIdEvolutionAfter}
					</td>
					<td>
						${evoutionGraphConstant.sucessPercent}
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