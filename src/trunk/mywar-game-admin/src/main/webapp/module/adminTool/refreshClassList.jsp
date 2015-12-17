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
		<link href="../../css/tab.css" rel="stylesheet" type="text/css" />
		
	</head>
	<body>	
		<table id="refreshClassTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="24" align="center">
				
					<center>
						<s:text name="refreshClassListJsp.title"></s:text>
						
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="refreshClassListJsp.ID"></s:text>
				</td>
				<td >
					<s:text name="refreshClassListJsp.NAME"></s:text>
				</td>
				<td>
					<s:text name="refreshClassListJsp.DESC"></s:text>
				</td>
				<td>
					<s:text name="refreshClassListJsp.refresh"></s:text>
				</td>
				<td>
					<s:text name="refreshClassListJsp.multirefresh"></s:text>
				</td>
			</tr>
			<s:iterator var="list" value="refreshClassList" >
			<tr id="${list.id}" >
				
				<td>
					${list.id}
				</td>
				<td>
					${list.className}							
				</td>
				<td>
					${list.classDesc}							
				</td>
							
				<td>
					<a href="#" onclick='refresh(${list.id})'><s:text name="refreshClassListJsp.refresh"></s:text></a>
				</td>
				
				<td>
				<a href="#" onclick='multiRefresh("${list.id}","${list.className}")'>多服刷新</a>
				</td>
			</tr>			
			</s:iterator>
		</table>
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script>
			
			function refresh(classId) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "refreshClassListDo?classId=" + classId + "&isCommit=" + true;
				ajaxobj.callback=function(){
					var responseMsg = eval('(' + ajaxobj.gettext() + ')');
					if (responseMsg.erroCodeNum == 0) {
						alert('<s:text name="action.success"><s:param><s:text name="refreshClassListJsp.refreshConstantClass"></s:text></s:param></s:text>');
					} else {
						alert('<s:text name="action.fail"><s:param><s:text name="refreshClassListJsp.refreshConstantClass"></s:text></s:param></s:text>');
					}					
				}
				ajaxobj.send();
			}
			
			function multiRefresh(classId,className) {
			var hight = (screen.height - 300) / 2.8;
			var width = (screen.width - 500) / 2;
			var str = window.showModalDialog("chooseServer", "", "dialogLeft=" + width
				+ "; dialogTop=" + hight
				+ "; dialogWidth=500px; dialogHeight=300px; location=no");
				
					
			if (str == null || str == '') {
				return;
			}

			// 
			var strArr = str.split(',');
			if (confirm("你确定要刷新该数据吗? (" + className + ")" + "(服务器:" + str + ")")) {		

				var ajaxobj = new Ajax();
				ajaxobj.url = "multiRefreshClassListDo?classId=" + classId + "&serverIds=" + str + "&isCommit=" + true;
				ajaxobj.callback=function(){
					var responseMsg = eval('(' + ajaxobj.gettext() + ')');
					if (responseMsg.erroCodeNum == 0) {
						alert('<s:text name="action.success"><s:param><s:text name="refreshClassListJsp.refreshConstantClass"></s:text></s:param></s:text>');
					} else {
						alert('<s:text name="action.fail"><s:param><s:text name="refreshClassListJsp.refreshConstantClass"></s:text></s:param></s:text>');
					}					
				}
				ajaxobj.send();
			}	
		}
		</script>
	</body>	
</html>
