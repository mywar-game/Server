<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="drawGoodsListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">	
	
		function del(goodsId) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='drawGoods.id'></s:text></s:param><s:param>"+goodsId+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delDrawGoods?goodsId=" + goodsId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(goodsId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(goodsId) {
			window.location.href = "updateDrawGoods?goodsId=" + goodsId;
		}
	
		function add(activityId) {			
			window.location.href = "addDrawGoods?activityId=" + activityId;
		}		
		
	</script>
	<body>
		<input type="submit" value="<s:text name='drawGoods.add'></s:text>" class="button" onclick=add(${activityId}); />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="drawGoodsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<s:text name="drawGoods.pos"></s:text>
				</td>
				<td>
					<s:text name="drawGoods.toolType"></s:text>
				</td>
				<td>
					<s:text name="drawGoods.toolId"></s:text>
				</td>
				<td>
					<s:text name="drawGoods.toolNum"></s:text>
				</td>
				<td>
					<s:text name="drawGoods.refreshLowerNum"></s:text>
				</td>
				<td>
					<s:text name="drawGoods.refreshUpperNum"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="drawGoods" value="drawGoodsList">
			<input type="hidden" name="activityId" value="${activityId}" />
			<tr id="${drawGoods.goodsId}" >
				<td >
					${drawGoods.pos}				
				</td>
				<td >
					${drawGoods.toolTypeName}
				</td>
				<td >
					${drawGoods.toolName}
				</td>
				<td >
					${drawGoods.toolNum}
				</td>
				<td >
					${drawGoods.refreshLowerNum}
				</td>
				<td >
					${drawGoods.refreshUpperNum}
				</td>
				<td>
					<a href="#" onclick='del("${drawGoods.goodsId}")'><s:text name="delete"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='update("${drawGoods.goodsId}")'><s:text name="update"></s:text></a>
				</td>
			</tr>
			</s:iterator>		
			<td colspan="100">
				<aldtags:pageTag paraStr="activityId,${activityId}" />
			</td>
		</table>
	</body>
</html>

