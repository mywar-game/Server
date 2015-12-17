<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="storyConstantListJsp.title"></s:text></title>

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
			ajaxobj.url="reflashConstantCache?cacheType=28&number="+Math.random();;
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新常量配置缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新常量配置缓存失败!');
				}
		    }
			ajaxobj.send();
		}
		
		function del(storyId) {
			if (window.confirm("是否确定删除storyId = "+storyId)) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delStoryConstant?storyId=" + storyId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(storyId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(storyId) {
			window.location.href = "updateStoryConstant?storyId=" + storyId;
		}
	
		function add() {
			window.location.href = "addStoryConstant";
		}
	</script>
	<body>
		<input type="submit" value="<s:text name="addStoryConstantJsp.title"></s:text>" class="button" onclick=add(); />
		<input type="button" value="<s:text name="storyConstantListJsp.reflashConstantCache"></s:text>" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="100" align="center">
					<center>
						<s:text name="storyConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="storyConstant.storyId"></s:text>
				</td>
				<td>
					<s:text name="storyConstant.storyDesc"></s:text>
				</td>
				<td>
					<s:text name="storyConstant.type"></s:text>
				</td>
				<td>
					<s:text name="storyConstant.npcId"></s:text>
				</td>
				<td>
					<s:text name="storyConstant.npcName"></s:text>
				</td>

				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="storyConstant" value="storyConstantList">
				<tr id="${storyConstant.storyId}">
					<td>
						${storyConstant.storyId}
					</td>
					<td>
						${storyConstant.storyDesc}
					</td>
					<td>
						<s:text name="%{'storyConstant.type_'+#storyConstant.type}"></s:text>
					</td>
					<td>
						${storyConstant.npcId}
					</td>
					<td>
						${storyConstant.npcName}
					</td>

					<td>
						<a href="#" onclick='del("${storyConstant.storyId}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${storyConstant.storyId}")'><s:text name="update"></s:text></a>
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