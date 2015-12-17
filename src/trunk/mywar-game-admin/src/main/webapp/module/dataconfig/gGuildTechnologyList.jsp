<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>军团科技常量列表</title>

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
			ajaxobj.url="reflashConstantCache?cacheType=16&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="gGuildTechnologyListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="gGuildTechnologyListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		
		function del(guildTechnologyId) {
			if (window.confirm("是否确定删除guildTechnologyId = "+guildTechnologyId)) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delGGuildTechnology?guildTechnologyId=" + guildTechnologyId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(guildTechnologyId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(guildTechnologyId) {
			window.location.href = "updateGGuildTechnology?guildTechnologyId=" + guildTechnologyId;
		}
	
		function add() {
			window.location.href = "addGGuildTechnology";
		}
	</script>
	<body>
		<input type="submit" value="添加军团科技常量" class="button" onclick=add(); />
		<input type="button" value='<s:text name="gGuildTechnologyListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						军团科技常量列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					军团科技id
				</td>
				<td>
					科技id
				</td>
				<td>
					军团科技名称
				</td>
				<td>
					科技等级
				</td>
				<td>
					增加数值
				</td>
				<td>
					升级消耗军团粮食
				</td>
				<td>
					升级消耗军团矿石
				</td>
				<td>
					升级消耗军团金钱
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="guildTechnology" value="guildTechnologyConstantList">
				<tr id="${guildTechnology.guildTechnologyId}">
					<td>
						${guildTechnology.guildTechnologyId}
					</td>
					<td>
						${guildTechnology.technologyId}
					</td>
					<td>
						${guildTechnology.technologyName}
					</td>
					<td>
						${guildTechnology.technologyLevel}
					</td>
					<td>
						<s:if test="#guildTechnology.valueType == 1">
							${guildTechnology.vaue}%
						</s:if>
						<s:if test="#guildTechnology.valueType == 2">
							${guildTechnology.vaue}
						</s:if>
					</td>
					<td>
						${guildTechnology.costGuildGrain}
					</td>
					<td>
						${guildTechnology.costGuildMineral}
					</td>
					<td>
						${guildTechnology.costGuildMoney}
					</td>



					<td>
						<a href="#" onclick='del("${guildTechnology.guildTechnologyId}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${guildTechnology.guildTechnologyId}")'><s:text name="update"></s:text></a>
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