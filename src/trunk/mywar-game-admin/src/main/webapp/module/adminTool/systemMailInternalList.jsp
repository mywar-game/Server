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
			var modifyMail = function(mailId) {
				window.location.href = "systemMailInternalList?isCommit=true&mailId=" + mailId;
			}
			
			function syn(mailId) {
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
				if (confirm("你确定要同步该数据吗? " + "(服务器:" + str + ")")) {		
					//window.location.href = "synActivityList?activityId=" + activityId + "&serverIds=" + str;
					window.location.href="synMailInternalList?mailId=" + mailId + "&serverIds=" + str;
				}	
			}
		</script>
	</head>
	<body>	
		<input type="button" value='<s:text name="systemMailInternalListJsp.fresh"></s:text>' class="button" onclick="reflashAll()" />
		<table id="mailTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="25" align="center">
					<center>
						<s:text name="systemMailInternalListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="systemMailInternalListJsp.mailId"></s:text>
				</td>
				<td >
					<s:text name="systemMailInternalListJsp.mailTitle"></s:text>
				</td>
				<td>
					<s:text name="systemMailInternalListJsp.mailContent"></s:text>
				</td>
				<td>
					<s:text name="systemMailInternalListJsp.logicType"></s:text>
				</td>
				<td>
					<s:text name="systemMailInternalListJsp.showType"></s:text>
				</td>
				<td>
					<s:text name="systemMailInternalListJsp.toolsDesc"></s:text>
				</td>
				<td>
					<s:text name="systemMailInternalListJsp.param"></s:text>
				</td>
				<td>
					<s:text name="systemMailInternalListJsp.description"></s:text>
				</td>
				<td size="10">
					<s:text name="systemMailInternalListJsp.caozuo"></s:text>
				</td>
				<td size="10">
					同步
				</td>
			</tr>
			<s:iterator var="mail" value="systemMailInternalList" >
			<tr>
				
				<td>
					${mail.internalMailId}
				</td>
				<td>
					${mail.mailTitle}							
				</td>
				<td>
					${mail.mailContent}							
				</td>
				<td>					
					${mail.logicType}
				</td>
				<td>
					${mail.showType}
				</td>
				<td>
					${mail.toolsDesc}
				</td>				
				<td>
					${mail.param}
				</td>
				<td>
					${mail.description}
				</td>
				<td size="10">
					<a href="#" onclick='modifyMail(${mail.internalMailId})'><s:text name="systemMailInternalListJsp.caozuo"></s:text></a>
				</td>
				<td size="10">
					<a href="#" onclick='syn(${mail.internalMailId})'>同步</a>
				</td>
			</tr>			
			</s:iterator>
		</table>
	</body>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script>
		function reflashAll(freshClassName) {
			var ajaxobj = new Ajax();
			ajaxobj.url = "reflashActivityList?freshClassName=" + "SystemMailInternalDaoCacheImpl";
			ajaxobj.callback=function(){
				var responseMsg = eval('(' + ajaxobj.gettext() + ')');
				if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param>刷新系统内部邮件</s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param>刷新系统内部邮件</s:param></s:text>');
				}
			}
			ajaxobj.send();	
		}
	</script>	
</html>


