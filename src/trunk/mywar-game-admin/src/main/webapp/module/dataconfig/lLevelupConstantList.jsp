<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="lLevelupConstantListJsp.title"></s:text></title>

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
			ajaxobj.url="reflashConstantCache?cacheType=17&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="lLevelupConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="lLevelupConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		
		function del(level) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="levelupConstant.level"></s:text></s:param><s:param>'+level+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delLLevelupConstant?level=" + level;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(level);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(level) {
			window.location.href = "updateLLevelupConstant?level=" + level;
		}
	
		function add() {
			window.location.href = "addLLevelupConstant";
		}
	</script>
	<body>
		<input type="submit" value="<s:text name="addLLevelupConstantJsp.title"></s:text>" class="button" onclick=add(); />
		<input type="button" value='<s:text name="lLevelupConstantListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="50" align="center">
					<center>
						<s:text name="lLevelupConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="levelupConstant.level"></s:text>
				</td>
				<td>
					<s:text name="levelupConstant.heroNeedExp"></s:text>
				</td>
				<td>
					<s:text name="levelupConstant.userNeedRenown"></s:text>
				</td>
				<td>
					宠物升级所需金钱
				</td>

				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="levelupConstant" value="levelupConstantList">
				<tr id="${levelupConstant.level}">
					<td>
						${levelupConstant.level}
					</td>
					<td>
						${levelupConstant.heroNeedExp}
					</td>
					<td>
						${levelupConstant.userNeedRenown}
					</td>
					<td>
						${levelupConstant.petLevelupMoney}
					</td>

					<td>
						<a href="#" onclick='del("${levelupConstant.level}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${levelupConstant.level}")'><s:text name="update"></s:text></a>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="50">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>