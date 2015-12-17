<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="bNpcConstantListJsp.title"></s:text></title>

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
			ajaxobj.url="reflashConstantCache?cacheType=13&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="bNpcConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="bNpcConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		
		function del(npcId) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="npcConstant.npcId"></s:text></s:param><s:param>'+npcId+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delBNpcConstant?npcId=" + npcId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(npcId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(npcId) {
			window.location.href = "updateBNpcConstant?npcId=" + npcId;
		}
	
		function add() {
			window.location.href = "addBNpcConstant";
		}
	</script>
	<body>
		<input type="submit" value="<s:text name="addBNpcConstantJsp.title"></s:text>" class="button" onclick=add(); />
		<input type="button" value='<s:text name="bNpcConstantListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						<s:text name="bNpcConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="npcConstant.npcId"></s:text>
				</td>
				<td>
					<s:text name="npcConstant.buildingId"></s:text>
				</td>
				<td>
					<s:text name="npcConstant.npcName"></s:text>
				</td>
				<td>
					<s:text name="npcConstant.npcDescription"></s:text>
				</td>
				<td>
					<s:text name="npcConstant.coolTime"></s:text>
				</td>

				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="npcConstant" value="npcConstantList">
				<tr id="${npcConstant.npcId}">
					<td>
						${npcConstant.npcId}
					</td>
					<td>
						${buildingIDNameMap[npcConstant.buildingId]}
					</td>
					<td>
						${npcConstant.npcName}
					</td>
					<td>
						${npcConstant.npcDescription}
					</td>
					<td>
						${npcConstant.coolTime}
					</td>

					<td>
						<a href="#" onclick='del("${npcConstant.npcId}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${npcConstant.npcId}")'><s:text name="update"></s:text></a>
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