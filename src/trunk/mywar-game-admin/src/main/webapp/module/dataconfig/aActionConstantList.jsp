<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="aActionConstantListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript">
		function showUpdate(a){
			a.style.display="none";
			var childNodes = a.parentNode.childNodes;
			for(var k=0; k<childNodes.length; k++){
				if(childNodes[k].nodeType==1 && childNodes[k].nodeName=="INPUT"){
					childNodes[k].style.display="";
				}
			}
		}
		
		function showAdd(a){
			a.style.display="none";
			var newActionDescInput = document.getElementById("newActionDesc");
			var addButton = document.getElementById("addButton");
			newActionDescInput.style.display="";
			addButton.style.display="";
		}
		
		function del(actionId) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="actionConstant.actionId"></s:text></s:param><s:param>'+actionId+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delAActionConstant?actionId=" + actionId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(actionId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(actionId,updateButton) {
			var modifyActionDesc = "";
			var childNodes = updateButton.parentNode.childNodes;
			for(var k=0; k<childNodes.length; k++){
				if(childNodes[k].nodeType==1 && childNodes[k].nodeName=="INPUT" && childNodes[k].type=="text"){
					modifyActionDesc = childNodes[k].value;
				}
			}
			window.location.href = "updateAActionConstant?actionId="+actionId+"&actionDesc=" + encodeURI(encodeURI(modifyActionDesc));
		}
	
		function add() {
			var newActionDescInput = document.getElementById("newActionDesc");
			window.location.href = "addAActionConstant?actionDesc=" + encodeURI(encodeURI(newActionDescInput.value));
		}
	</script>
	<body>
		<input type="button" value='<s:text name="aActionConstantListJsp.add"></s:text>' onclick='showAdd(this)' class="button">
		<input type="text" id="newActionDesc" value="" class="maxLife" size="20" maxlength="64" style="display:none;"/>
		<input type="submit" id="addButton" value='<s:text name="aActionConstantListJsp.add"></s:text>' class="button" onclick=add(); style="display:none;"/>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="5" align="center">
					<center>
						<s:text name="aActionConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="actionConstant.actionId"></s:text>
				</td>
				<td>
					<s:text name="actionConstant.actionDesc"></s:text>
				</td>
				<td>
					<s:text name="update"></s:text>
				</td>

				<td width="35">
					<s:text name="delete"></s:text>
				</td>
			</tr>
			<s:iterator var="action" value="actionConstantList">
				<tr id="${action.actionId}">
					<td>
						${action.actionId}
					</td>
					<td>
						${action.actionDesc}
					</td>
					<td>
						<a href="#" onclick='showUpdate(this)'><s:text name="update"></s:text></a>
						<input type="text" value="${action.actionDesc}" class="maxLife" size="20" maxlength="64" style="display:none;"/>
						<input type="button" value='<s:text name="update"></s:text>' class="button" onclick='update("${action.actionId}",this)' style="display:none;"/>
					</td>

					<td>
						<a href="#" onclick='del("${action.actionId}")'><s:text name="delete"></s:text></a>
					</td>
				</tr>
			</s:iterator>
			
			<tr>
				<td colspan="100">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>