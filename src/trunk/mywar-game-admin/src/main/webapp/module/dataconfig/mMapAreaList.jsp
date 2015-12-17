<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="mMapAreaListJsp.title"></s:text></title>

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
			ajaxobj.url="reflashConstantCache?cacheType=18&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="mMapAreaListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="mMapAreaListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		
		function del(areaId) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="mapArea.areaId"></s:text></s:param><s:param>'+areaId+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delMMapArea?areaId=" + areaId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(areaId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(areaId) {
			window.location.href = "updateMMapArea?areaId=" + areaId;
		}
	
		function add() {
			window.location.href = "addMMapArea";
		}
	</script>
	<body>
		<input type="submit" value="<s:text name="addMMapAreaJsp.title"></s:text>" class="button" onclick=add(); />
		<input type="button" value='<s:text name="mMapAreaListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="100" align="center">
					<center>
						<s:text name="mMapAreaListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="mapArea.areaId"></s:text>
				</td>
				<td>
					<s:text name="mapArea.areaName"></s:text>
				</td>
				<td>
					<s:text name="mapArea.areaDesc"></s:text>
				</td>
				<td>
					<s:text name="mapArea.minLevel"></s:text>
				</td>
				<td>
					<s:text name="mapArea.maxLevel"></s:text>
				</td>
				<td>
					<s:text name="mapArea.ppveIds"></s:text>
				</td>
				<td>
					<s:text name="mapArea.bigPveIds"></s:text>
				</td>
				<td>
					<s:text name="mapArea.trimIds"></s:text>
				</td>
				<td>
					<s:text name="mapArea.pveIds"></s:text>
				</td>

				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="mapArea" value="mapAreaList">
				<tr id="${mapArea.areaId}">
					<td>
						${mapArea.areaId}
					</td>
					<td>
						${mapArea.areaName}
					</td>
					<td>
						${mapArea.areaDesc}
					</td>
					<td>
						${mapArea.minLevel}
					</td>
					<td>
						${mapArea.maxLevel}
					</td>
					<td>
						<a href="baPveConstantList?bigId=<s:property value='#mapArea.ppveIds.split("_")[0]' />&smallId=<s:property value='#mapArea.ppveIds.split("_")[1]' />">
							<s:property value="baPveIdNamesMap[#mapArea.ppveIds.replace('_',',')]"/>
						</a>
					</td>
					<td>
						<s:generator separator="," val="#mapArea.bigPveIds">
							<s:iterator var="bigId" >
								<a href="baPveConstantList?bigId=${bigId}">
									<s:property value="baPveIdNamesMap[#bigId+',1'].split(',')[0]"/>
								</a>
							</s:iterator>
						</s:generator>
					</td>
					<td>
						${mapArea.trimIds}
					</td>
					<td>
						<a href="baPveConstantList?bigId=<s:property value='#mapArea.pveIds.split("_")[0]' />&smallId=<s:property value='#mapArea.pveIds.split("_")[1]' />">
							<s:property value="baPveIdNamesMap[#mapArea.pveIds.replace('_',',')]"/>
						</a>
					</td>

					<td>
						<a href="#" onclick='del("${mapArea.areaId}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${mapArea.areaId}")'><s:text name="update"></s:text></a>
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