<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="bBuildingconstraintConstantListJsp.title"></s:text></title>

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
			ajaxobj.url="reflashConstantCache?cacheType=11&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="bBuildingconstraintConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="bBuildingconstraintConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		
		function del(buildingConstraintId) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="buildingconstraintConstant.buildingConstraintId"></s:text></s:param><s:param>'+buildingConstraintId+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delBBuildingconstraintConstant?buildingConstraintId=" + buildingConstraintId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(buildingConstraintId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(buildingConstraintId) {
			window.location.href = "updateBBuildingconstraintConstant?buildingConstraintId=" + buildingConstraintId;
		}
	
		function add() {
			window.location.href = "addBBuildingconstraintConstant";
		}
	</script>
	<body>
		<input type="submit" value="<s:text name="addBBuildingconstraintConstantJsp.title"></s:text>" class="button" onclick=add(); />
		<input type="button" value='<s:text name="bBuildingconstraintConstantListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="11" align="center">
					<center>
						<s:text name="bBuildingconstraintConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="buildingconstraintConstant.buildingConstraintId"></s:text>
				</td>
				<td>
					<s:text name="buildingconstraintConstant.buildingId"></s:text>
				</td>
				<td>
					<s:text name="buildingconstraintConstant.buildingLevel"></s:text>
				</td>
				<td>
					<s:text name="buildingconstraintConstant.prebuildings"></s:text>
				</td>
				<td>
					<s:text name="buildingconstraintConstant.description"></s:text>
				</td>
				<td>
					<s:text name="buildingconstraintConstant.costGrain"></s:text>
				</td>
				<td>
					<s:text name="buildingconstraintConstant.costMineral"></s:text>
				</td>
				<td>
					<s:text name="buildingconstraintConstant.costMoney"></s:text>
				</td>
				<td>
					<s:text name="buildingconstraintConstant.coolTime"></s:text>
				</td>

				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="buildingconstraint" value="buildingconstraintConstantList">
				<tr id="${buildingconstraint.buildingConstraintId}">
					<td>
						${buildingconstraint.buildingConstraintId}
					</td>
					<td>
						${buildingIDNameMap[buildingconstraint.buildingId]}
					</td>
					<td>
						${buildingconstraint.buildingLevel}
					</td>
					<td>
						${buildingconstraint.prebuildings}
					</td>
					<td>
						${buildingconstraint.description}
					</td>
					<td>
						${buildingconstraint.costGrain}
					</td>
					<td>
						${buildingconstraint.costMineral}
					</td>
					<td>
						${buildingconstraint.costMoney}
					</td>
					<td>
						${buildingconstraint.coolTime}
					</td>

					<td>
						<a href="#" onclick='del("${buildingconstraint.buildingConstraintId}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${buildingconstraint.buildingConstraintId}")'><s:text name="update"></s:text></a>
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