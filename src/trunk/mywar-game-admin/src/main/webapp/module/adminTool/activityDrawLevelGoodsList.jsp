<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="drawLevelListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">	
	
		function del(id) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='drawLevel.id'></s:text></s:param><s:param>"+id+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delDrawLevelGoods?id=" + id;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(id);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(id) {
			window.location.href = "updateDrawLevelGoods?id=" + id;
		}
	
		function add(activityId) {			
			window.location.href = "addDrawLevelGoods?activityId=" + activityId;
		}		
		
	</script>
	<body>
		<input type="submit" value="<s:text name='drawLevel.add'></s:text>" class="button" onclick=add(${activityId}); />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="drawLevelListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<s:text name="drawLevel.level"></s:text>
				</td>
				<td >
					<s:text name="drawLevel.replacePos"></s:text>
				</td>
				<td>
					<s:text name="drawLevel.toolType"></s:text>
				</td>
				<td>
					<s:text name="drawLevel.toolId"></s:text>
				</td>
				<td>
					<s:text name="drawLevel.toolNum"></s:text>
				</td>
				<td>
					<s:text name="drawLevel.refreshLowerNum"></s:text>
				</td>
				<td>
					<s:text name="drawLevel.refreshUpperNum"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="levelGoods" value="drawLevelGoodsList">
			<input type="hidden" name="activityId" value="${activityId}" />
			<tr id="${levelGoods.id}" >
				<td >
					${levelGoods.level}				
				</td>
				<td >
					${levelGoods.replacePos}				
				</td>
				<td >
					${levelGoods.toolTypeString}
				</td>
				<td >
					${levelGoods.toolString}
				</td>
				<td >
					${levelGoods.toolNum}
				</td>
				<td >
					${levelGoods.refreshLowerNum}
				</td>
				<td >
					${levelGoods.refreshUpperNum}
				</td>
				<td>
					<a href="#" onclick='del("${levelGoods.id}")'><s:text name="delete"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='update("${levelGoods.id}")'><s:text name="update"></s:text></a>
				</td>
			</tr>
			</s:iterator>		
			<td colspan="100">
				<aldtags:pageTag paraStr="isCommit,T,activityId,${activityId},pageSize,${pageSize}" />
			</td>
		</table>
	</body>
</html>

