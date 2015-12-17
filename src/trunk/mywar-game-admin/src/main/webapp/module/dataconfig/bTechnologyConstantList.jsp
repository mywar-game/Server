<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="bTechnologyConstantListJsp.title"></s:text></title>

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
			ajaxobj.url="reflashConstantCache?cacheType=14&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="bTechnologyConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="bTechnologyConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		
		function del(technologyConstantId) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="technologyConstant.technologyConstantId"></s:text></s:param><s:param>'+technologyConstantId+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delBTechnologyConstant?technologyConstantId=" + technologyConstantId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(technologyConstantId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(technologyConstantId) {
			window.location.href = "updateBTechnologyConstant?technologyConstantId=" + technologyConstantId;
		}
	
		function add() {
			window.location.href = "addBTechnologyConstant";
		}
	</script>
	<body>
		<input type="submit" value="<s:text name="addBTechnologyConstantJsp.title"></s:text>" class="button" onclick=add(); />
		<input type="button" value='<s:text name="bTechnologyConstantListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="11" align="center">
					<center>
						<s:text name="bTechnologyConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="technologyConstant.technologyConstantId"></s:text>
				</td>
				<td>
					<s:text name="technologyConstant.technologyId"></s:text>
				</td>
				<td>
					<s:text name="technologyConstant.technologyName"></s:text>
				</td>
				<td>
					<s:text name="technologyConstant.technologyDesc"></s:text>
				</td>
				<td>
					<s:text name="technologyConstant.effectType"></s:text>
				</td>
				<td>
					<s:text name="technologyConstant.level"></s:text>
				</td>
				<td>
					<s:text name="technologyConstant.value"></s:text>
				</td>
				<td>
					<s:text name="technologyConstant.needGold"></s:text>
				</td>
				<td>
					<s:text name="technologyConstant.coolingTime"></s:text>
				</td>

				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="technologyConstant" value="technologyConstantList">
				<tr id="${technologyConstant.technologyConstantId}">
					<td>
						${technologyConstant.technologyConstantId}
					</td>
					<td>
						${technologyConstant.technologyId}
					</td>
					<td>
						${technologyConstant.technologyName}
					</td>
					<td>
						${technologyConstant.technologyDesc}
					</td>
					<td>
						<s:text name="%{'effectType_'+#technologyConstant.effectType}"></s:text>
					</td>
					<td>
						${technologyConstant.level}
					</td>
					<td>
						<c:if test="${technologyConstant.valueType == 1}">${technologyConstant.value}%</c:if>
						<c:if test="${technologyConstant.valueType == 2}">${technologyConstant.value}</c:if>
					</td>
					<td>
						${technologyConstant.needGold}
					</td>
					<td>
						${technologyConstant.coolingTime}
					</td>

					<td>
						<a href="#" onclick='del("${technologyConstant.technologyConstantId}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${technologyConstant.technologyConstantId}")'><s:text name="update"></s:text></a>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="11">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>