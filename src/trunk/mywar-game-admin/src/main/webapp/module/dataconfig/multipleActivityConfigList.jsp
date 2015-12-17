<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>多倍活动配置列表</title>

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
			ajaxobj.url="reflashConstantCache?cacheType=31&number="+Math.random();
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新多倍活动配置缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新多倍活动配置缓存失败!');
				}
		    }
			ajaxobj.send();
		}
		
		function del(configId) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="multipleActivityConfig.configId"></s:text></s:param><s:param>'+configId+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delMultipleActivityConfig?configId=" + configId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(configId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(configId) {
			window.location.href = "updateMultipleActivityConfig?configId=" + configId;
		}
	
		function add() {
			window.location.href = "addMultipleActivityConfig";
		}
	</script>
	<body>
		<input type="submit" value="添加多倍活动配置" class="button" onclick="add();" />
		<input type="button" value="刷新多倍活动配置缓存" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						多倍活动配置列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="multipleActivityConfig.configId"></s:text>
				</td>
				<td>
					<s:text name="multipleActivityConfig.activityType"></s:text>
				</td>
				<td>
					<s:text name="multipleActivityConfig.multiple"></s:text>
				</td>
				<td>
					<s:text name="multipleActivityConfig.startDate"></s:text>
				</td>
				<td>
					<s:text name="multipleActivityConfig.endDate"></s:text>
				</td>
				<td>
					<s:text name="multipleActivityConfig.startTime"></s:text>
				</td>
				<td>
					<s:text name="multipleActivityConfig.endTime"></s:text>
				</td>
				<td>
					<s:text name="multipleActivityConfig.userLevelLimit"></s:text>
				</td>

				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="multipleActivityConfig" value="multipleActivityConfigList">
				<tr id="${multipleActivityConfig.configId}">
					<td>
						${multipleActivityConfig.configId}
					</td>
					<td>
						<s:text name="%{'multipleActivityConfig.activityType_'+#multipleActivityConfig.activityType}"></s:text>
					</td>
					<td>
						${multipleActivityConfig.multiple}
					</td>
					<td>
						<s:text name="format.date_ymd">
							<s:param value="#multipleActivityConfig.startDate"></s:param>
						</s:text>
					</td>
					<td>
						<s:text name="format.date_ymd">
							<s:param value="#multipleActivityConfig.endDate"></s:param>
						</s:text>
					</td>
					<td>
						${multipleActivityConfig.startTime}
					</td>
					<td>
						${multipleActivityConfig.endTime}
					</td>
					<td>
						${multipleActivityConfig.userLevelLimit}
					</td>

					<td>
						<a href="#" onclick='del("${multipleActivityConfig.configId}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${multipleActivityConfig.configId}")'><s:text name="update"></s:text></a>
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