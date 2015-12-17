<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="getSystemNoticeLogJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
		<script type="text/javascript">
			function reflashconstantcache() {
				var ajaxobj = new Ajax();
				ajaxobj.url="../dataconfig/reflashConstantCache?cacheType=32&number="+Math.random();
			    ajaxobj.callback=function(){
			    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
			    	//alert(responseMsg.erroCodeNum);
			    	if (responseMsg.erroCodeNum == 0) {
						alert('<s:text name="action.success"><s:param><s:text name="getSystemNoticeLogJsp.reflashConstantCache"></s:text></s:param></s:text>');
					} else {
						alert('<s:text name="action.fail"><s:param><s:text name="getSystemNoticeLogJsp.reflashConstantCache"></s:text></s:param></s:text>');
					}
			    }
				ajaxobj.send();
			}	
			
			function delteSystemNotice(noticeLogId,index) {
				if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="systemNoticeLog.noticeLogId"></s:text></s:param><s:param>'+index+'</s:param></s:text>')) {
					var ajaxobj = new Ajax();
					ajaxobj.url = "delSystemNoticeLog?noticeLogId=" + noticeLogId;
					ajaxobj.callback = function() {
						//alert(ajaxobj.gettext());
						var tr = document.getElementById(noticeLogId);
						var table = document.getElementById("table");
						table.deleteRow(tr.rowIndex);
					}
					ajaxobj.send();
				}
		
			}
		
			function updateSystemNotice(noticeLogId) {
				window.location.href = "updateSystemNoticeLog?noticeLogId=" + noticeLogId;
			}
		
			function add() {
				window.location.href = "addSystemNoticeLog";
			}
			
		</script>
	</head>
	<body>
		<input type="button" value="<s:text name="getSystemNoticeLogJsp.add"></s:text>" class="button" onclick=add(); />
		<input type="button" value='<s:text name="getSystemNoticeLogJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center >
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						<s:text name="getSystemNoticeLogJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td> 
					<s:text name="systemNoticeLog.noticeLogId"></s:text>
				</td>
				<td>
					<s:text name="systemNoticeLog.startDate"></s:text>
				</td>
				<td>
					<s:text name="systemNoticeLog.endDate"></s:text>
				</td>
				<td>
					<s:text name="systemNoticeLog.startTime"></s:text>
				</td>
				<td>
					<s:text name="systemNoticeLog.endTime"></s:text>
				</td>
				<td>
					<s:text name="systemNoticeLog.period"></s:text>
				</td>
				<td>
					<s:text name="systemNoticeLog.type"></s:text>
				</td>
				<td>
					<s:text name="systemNoticeLog.content"></s:text>
				</td>
				<td>
					<s:text name="systemNoticeLog.operation"></s:text>
				</td>
			</tr>
			<s:iterator var="data" value="systemNoticeLogList" status="sta">
				<tr id="${noticeLogId}">
					<td>
						${sta.index+1}
					</td>
					<td>
						<s:text name="format.date_ymd">
							<s:param value="#data.startDate"></s:param>
						</s:text>
					</td>
					<td>
						<s:text name="format.date_ymd">
							<s:param value="#data.endDate"></s:param>
						</s:text>
					</td>
					<td>
						${data.startTime}
					</td>
					<td>
						${data.endTime}
					</td>
					<td>
						${data.period}
					</td>
					<td>
						<s:text name="%{'systemNoticeLog.type_'+#data.type}"></s:text>
					</td>
					<td>
						${data.content}
					</td>
					<td>
						<input type="button" class="button" id="delete" name="delete" value="<s:text name="delete"></s:text>" onclick="delteSystemNotice(${data.noticeLogId}, ${sta.index+1})" />
						<input type="button" class="button" id="update" name="update" value="<s:text name="update"></s:text>" onclick="updateSystemNotice(${data.noticeLogId})" />
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>