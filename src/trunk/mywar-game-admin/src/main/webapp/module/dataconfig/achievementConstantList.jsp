<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="achievementConstantListJsp.title"></s:text></title>

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
			ajaxobj.url="reflashConstantCache?cacheType=9&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="achievementConstantJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="achievementConstantJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		
		function del(achievementId) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="achievementConstant.achievementId"></s:text></s:param><s:param>'+achievementId+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delAchievementConstant?achievementId=" + achievementId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(achievementId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(achievementId) {
			window.location.href = "updateAchievementConstant?achievementId=" + achievementId;
		}
	
		function add() {
			window.location.href = "addAchievementConstant";
		}
	</script>
	<body>
		<input type="submit" value='<s:text name="addAchievementConstantJsp.title"></s:text>' class="button" onclick=add(); />
		<input type="button" value='<s:text name="achievementConstantJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="20" align="center">
					<center>
						<s:text name="achievementConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="achievementConstant.achievementId"></s:text>
				</td>
				<td>
					<s:text name="achievementConstant.achievementName"></s:text>
				</td>
				<td>
					<s:text name="achievementConstant.effectDesc"></s:text>
				</td>
				<td>
					<s:text name="achievementConstant.achievementType"></s:text>
				</td>
				<td>
					<s:text name="achievementConstant.isSendNotice"></s:text>
				</td>
				<td>
					<s:text name="achievementConstant.conditionDesc"></s:text>
				</td>
				<td>
					<s:text name="achievementConstant.conditionType"></s:text>
				</td>
				<td>
					<s:text name="achievementConstant.conditionNum"></s:text>
				</td>
				<td>
					<s:text name="achievementConstant.attackAdd"></s:text>
				</td>
				<td>
					<s:text name="achievementConstant.lifeAdd"></s:text>
				</td>
				<td>
					<s:text name="achievementConstant.armorAdd"></s:text>
				</td>
				<td>
					<s:text name="achievementConstant.critAdd"></s:text>
				</td>
				<td>
					<s:text name="achievementConstant.dodgeAdd"></s:text>
				</td>
				<td>
					<s:text name="achievementConstant.speedAdd"></s:text>
				</td>
				<td>
					<s:text name="achievementConstant.hitAdd"></s:text>
				</td>
				
				
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="achievementConstant" value="achievementConstantList">
				<tr id="${achievementConstant.achievementId}">
					<td>
						${achievementConstant.achievementId}
					</td>
					<td>
						${achievementConstant.achievementName}
					</td>
					<td>
						${achievementConstant.effectDesc}
					</td>
					<td>
						<s:text name="%{'achievementConstant.achievementType_'+#achievementConstant.achievementType}"></s:text>
					</td>
					<td>
						<s:text name="%{'achievementConstant.isSendNotice_'+#achievementConstant.isSendNotice}"></s:text>
					</td>
					<td>
						${achievementConstant.conditionDesc}
					</td>
					<td>
						<s:text name="%{'achievementConstant.conditionType_'+#achievementConstant.conditionType}"></s:text>
					</td>
					<td>
						${achievementConstant.conditionNum}
					</td>
					<s:set var="percentFlag" value="''"></s:set>
					<s:if test="#achievementConstant.valueType == 1">
						<s:set var="percentFlag" value="'%'"></s:set>
					</s:if>
					<td>
						${achievementConstant.attackAdd}${percentFlag}
					</td>
					<td>
						${achievementConstant.lifeAdd}${percentFlag}
					</td>
					<td>
						${achievementConstant.armorAdd}${percentFlag}
					</td>
					<td>
						${achievementConstant.critAdd}${percentFlag}
					</td>
					<td>
						${achievementConstant.dodgeAdd}${percentFlag}
					</td>
					<td>
						${achievementConstant.speedAdd}${percentFlag}
					</td>
					<td>
						${achievementConstant.hitAdd}${percentFlag}
					</td>
					
					<td>
						<a href="#" onclick='del("${achievementConstant.achievementId}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${achievementConstant.achievementId}")'><s:text name="update"></s:text></a>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="20">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>