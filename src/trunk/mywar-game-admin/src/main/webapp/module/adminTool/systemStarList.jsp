<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/jquery/jquery.min.js"></script>
		<script src="../../js/common/chooseTool.js"></script>
		<script>
			var modifyStar = function(starId) {
				window.location.href = "systemStarList?isCommit=true&starId=" + starId;
			}
			
			function syn() {
				var hight = (screen.height - 300) / 2.8;
				var width = (screen.width - 500) / 2;
				var str = window.showModalDialog("chooseServer", "", "dialogLeft=" + width
					+ "; dialogTop=" + hight
					+ "; dialogWidth=500px; dialogHeight=300px; location=no");
				
					
				if (str == null || str == '') {
					return;
				}

				// 获取到的道具
				var strArr = str.split(',');
				if (confirm("你确定要同步该数据吗? (服务器:" + str + ")")) {		
					window.location.href = "synSystemStarList?serverIds=" + str;
				}	
			}
		</script>
	</head>
	<body>	
		<input type="button" value='<s:text name="systemStarListJsp.fresh"></s:text>' class="button" onclick="reflashAll()" />
		<!-- <input type="button" value="同步" onclick='syn()' /> -->
		
		<table id="starTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="24" align="center">
					<center>
						<s:text name="systemStarListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="systemStarListJsp.starId"></s:text>
				</td>
				<td >
					<s:text name="systemStarListJsp.starType"></s:text>
				</td>
				<td>
					<s:text name="systemStarListJsp.needLevel"></s:text>
				</td>
				<td>
					<s:text name="systemStarListJsp.showRewards"></s:text>
				</td>
				<%-- <td>
					<s:text name="systemStarListJsp.showRewards"></s:text>
				</td> --%>
				<td>
					<s:text name="systemStarListJsp.caozuo"></s:text>
				</td>
			</tr>
			<s:iterator var="star" value="systemStarList" >
			<tr id="${star.starId}" >
				
				<td>
					${star.starId}
				</td>
				<td>
					${star.starType}							
				</td>
				<td>
					${star.needLevel}							
				</td>
				<td>					
					${star.showRewards}
				</td>
				<%-- <td >
					<input type="text" name="descriptionArr" value="${starList.showRewards}" />
				</td> --%>				
				<td>
					<a href="#" onclick='modifyStar(${star.starId})'><s:text name="systemStarListJsp.caozuo"></s:text></a>
				</td>
				
			</tr>			
			</s:iterator>
		</table>
	</body>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script>
		function reflashAll(freshClassName) {
			var ajaxobj = new Ajax();
			ajaxobj.url = "reflashActivityList?freshClassName=" + "SystemStarDaoCacheImpl";
			ajaxobj.callback=function(){
				var responseMsg = eval('(' + ajaxobj.gettext() + ')');
				if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="systemStarListJsp.refalshOneActivity"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="systemStarListJsp.refalshOneActivity"></s:text></s:param></s:text>');
				}
			}
			ajaxobj.send();	
		}
	</script>	
</html>


