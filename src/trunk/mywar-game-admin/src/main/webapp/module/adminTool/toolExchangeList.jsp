<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="toolExchangeListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<base target="_self">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script src="../../js/artDialog/artDialog.js?skin=default"></script>
		<script src="../../js/artDialog/plugins/iframeTools.js"></script>
		<script src="../../js/common/chooseTool.js"></script>
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		var success = '${isCommit}';
		if (success == 'T') {
			alert("配置成功！");
			window.location.href="activityList";
		}
		
		// 清空消耗物品
		function clearPreTool(tableTd, idsColumn, showColumn) {
			clearTool(tableTd, idsColumn, showColumn, "<s:text name='toolExchange.clearPreConfirm'></s:text>");
		}		
		
		// 清空获得物品
		function clearPostTool(tableTd, idsColumn, showColumn) {
			clearTool(tableTd, idsColumn, showColumn, "<s:text name='toolExchange.clearPostConfirm'></s:text>");
		}
		
		// 添加一行
		function addRow() {
			var newRow = document.all.toolTable.insertRow();
			var cssTd = "style='background: #EEF7FD; height:24px; font: normal 12px SimSun; line-height:20px; border:1px solid #fff; color:#135294; padding:2px; text-align:left;'";
			newRow.innerHTML=""
				+ "<td " + cssTd + "><input type='text' size='23' readonly='readonly' name='preExchangeItemsArr' value='' /></td>" 
				+ "<td " + cssTd + "></td>"
				+ "<td " + cssTd + "><input type='text' size='23' readonly='readonly' name='postExchangeItemsArr' value='' /></td>"
			 	+ "<td " + cssTd + "></td>"
				+ "<td " + cssTd + "><input type='text' size='5' name='timesArr' value='' /></td>"
				+ "<td " + cssTd + "><input type='text' size='40' name='descriptionArr' value='' /></td>" 
				+ "<td " + cssTd + "><a href='#' onclick='addTool(this, 0, 1)'><s:text name='toolExchange.addPreExchangeItems'></s:text></a>|"
				+ "<a href='#' onclick='addTool(this, 2, 3)'><s:text name='toolExchange.addPostExchangeItems'></s:text></a><br/>"
				+ "<a href='#' onclick='clearPreTool(this, 0, 1)'><s:text name='toolExchange.clearPreExchangeItems'></s:text></a>|"
				+ "<a href='#' onclick='clearPostTool(this, 2, 3)'><s:text name='toolExchange.clearPostExchangeItems'></s:text></a></td>";
	
		}
				
	</script>
	<body>
		<form name="f" action="addToolExchange?isCommit=T" method="post" >
		<input type="button" value="<s:text name='addToolExchangeJsp.title'></s:text>" class="button" onclick='addRow()'; />
		<table id="toolTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="toolExchangeListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				
				<td >
					<input type="hidden" name="activityId" value="${activityId}" />
					<s:text name="toolExchange.preExchangeItems"></s:text>(ids)
				</td>
				<td>
					<s:text name="toolExchange.preExchangeItems"></s:text>
				</td>
				<td>
					<s:text name="toolExchange.postExchangeItems"></s:text>(ids)
				</td>
				<td>
					<s:text name="toolExchange.postExchangeItems"></s:text>
				</td>
				<td>
					<s:text name="reward.timesLimit"></s:text>
				</td>
				<td>
					<s:text name="reward.description"></s:text>
				</td>
				<td >
					<s:text name="toolExchange.operation"></s:text>
				</td>
			</tr>
			<s:iterator var="toolExchange" value="toolExchangeList" >
			<tr id="${toolExchange.exchangeId}">
				
				<td >
					<input type="text" size="23" readonly="readonly" name="preExchangeItemsArr" value="${toolExchange.preExchangeItems}" />								
				</td>
				<td >
					${toolExchange.showPreItems}								
				</td>
				<td >					
					<input type="text" size="23" readonly="readonly" name="postExchangeItemsArr" value="${toolExchange.postExchangeItems}" />
				</td>
				<td >					
					${toolExchange.showPostItems}		
				</td>
				<td >
					<input type="text" size="5" name="timesArr" value="${toolExchange.times}" />					
				</td>
				<td >
					<input type="text" size="40" name="descriptionArr" value="${toolExchange.description}" />
				</td>				
				<td>
					<a href="#" onclick='addTool(this, 0, 1)'><s:text name="toolExchange.addPreExchangeItems"></s:text></a>|
					<a href="#" onclick='addTool(this, 2, 3)'><s:text name="toolExchange.addPostExchangeItems"></s:text></a><br/>
					<a href="#" onclick='clearPreTool(this, 0, 1)'><s:text name="toolExchange.clearPreExchangeItems"></s:text></a>|
					<a href="#" onclick='clearPostTool(this, 2, 3)'><s:text name="toolExchange.clearPostExchangeItems"></s:text></a>
				</td>
			</tr>			
			</s:iterator>
		</table>
		<table>
			<td colspan="5">
				<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
			</td>
		</table>
		</form>
	</body>
</html>
<script src="../../js/jquery/jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("form").validate({
			rules:{
				preExchangeItemsArr:{
					required:true
				},
				postExchangeItemsArr:{
					required:true
				},
				timesArr:{
					required:true,
					digits:true
				}
			}		
		});	
	});
</script>
