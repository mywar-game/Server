<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" pageEncoding="Utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>Insert title here</title>
	
	<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/ajax.js"></script>
</head>
<script type="text/javascript">
function filteList() {
	var trList = document.getElementById("table");
	
	for (var i = 1; i < trList.rows.length; i++) {
		if (trList.rows[i].firstElementChild.value != 1) {
			/* trList.deleteRow(i);
			i = i - 1; */
			trList.rows[i].hidden=true;
		}
	}
	
}

function showHistory() {
	var trList = document.getElementById("table");
	
	for (var i = 1; i < trList.rows.length; i++) {
		var status = trList.rows[i].firstElementChild.value;
		if ( status != 2 || status != 3 ) {
			/* trList.deleteRow(i);
			i = i - 1; */
			trList.rows[i].hidden=false;
		}
	}
	
}
</script>
<body>
	<input type="button" value='<s:text name="刷新商城打折"></s:text>' class="button" onclick="reflashAll()" />
	<table cellpadding="3" cellspacing="1" border="0" width="100%"
		align=center>
		<tr class="border">
			<td class="td_title" ><s:text name="商城道具打折活动列表"></s:text>	</td>
		</tr>
	</table>
	
	<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>

		<tr>
			<td><s:text name="活动编号"></s:text></td>
			<td><s:text name="开始时间"></s:text></td>
			<td><s:text name="结束时间"></s:text></td>
			<td><s:text name="结束倒计时"></s:text></td>
			<td><s:text name="操作"></s:text></td>
		</tr>
		<s:iterator var="data" value="discountList">
			<tr>
				<input type="hidden" value="${data.status}"/>
				<input type="hidden" name="activityId" value="${data.activityId}"/>
				<td>${data.id}</td>
				<td>${data.startTime}</td>
				<td>${data.endTime}</td>
				<td>${data.countdown}</td>
				<td>
				<a
					href="javascript:if(confirm('<s:text name="确定删除吗？"><s:param>${data.activityId}</s:param></s:text>'))location.replace('delMallDiscount?delHistory=F&activityId=${data.activityId}');">
						<s:text name="删除"></s:text>
				</a> 
					&nbsp;
					<a href="updateMallDiscount?activityId=${data.activityId}"><s:text name="修改"></s:text></a>
				</td>
			</tr>
		</s:iterator>
		
		
		<tr>
			<td colspan="10"><aldtags:pageTag/></td>
		</tr>
		<tr>
			<td>
			<a href="addMallDiscount"><input type="button" value="<s:text name="添加活动"/>"/></a>
			<a href="mallHistoryList"><input type="button" value="<s:text name="显示历史活动"/>"/></a>
			</td>
		</tr>
	</table>
</body>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script>
		function reflashAll(freshClassName) {
			var ajaxobj = new Ajax();
			ajaxobj.url = "reflashActivityList?freshClassName=" + "SystemMallDiscountDaoCacheImpl";
			ajaxobj.callback=function(){
				var responseMsg = eval('(' + ajaxobj.gettext() + ')');
				if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="刷新商城打折"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="刷新商城打折"></s:text></s:param></s:text>');
				}
			}
			ajaxobj.send();	
		}
	</script>	
</html>