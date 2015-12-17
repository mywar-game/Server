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
function checkall(name) {
	var names = document.getElementsByName(name);
	var len = names.length;
	if (len > 0) {
		var i = 0;
		for (i = 0; i < len; i++)
			names[i].checked = true;

	}
}
function uncheckall(id) {
	var names = document.getElementsByName(id);
	var len = names.length;
	if (len > 0) {
		var i = 0;
		for (i = 0; i < len; i++)
			names[i].checked=false;
	}
}
</script>
<form action="delMallDiscount?delHistory=T" method="post">
<body>
	
	<table cellpadding="3" cellspacing="1" border="0" width="100%"
		align=center>
		<tr class="border">
			<td class="td_title" ><s:text name="历史活动列表"></s:text>	</td>
		</tr>
	</table>
	
	<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>

		<tr>
			<td><input type="checkbox" onclick="if(this.checked==true) {checkall('activityId');} else {uncheckall('activityId');}"></td>
			<td><s:text name="活动编号"></s:text></td>
			<td><s:text name="开始时间"></s:text></td>
			<td><s:text name="结束时间"></s:text></td>
			<td><s:text name="状态"></s:text></td>
			
		</tr>
		<s:iterator var="data" value="discountList">
			<tr>
				<input type="hidden" value="${data.status}"/>
				<td><input type="checkbox" name="activityId" value="${data.activityId}"></td>
				<td>${data.id}</td>
				<td>${data.startTime}</td>
				<td>${data.endTime}</td>
				<td>${data.countdown}</td>
			</tr>
		</s:iterator>
		<tr>
			<td colspan="10" align="center">
			<input type="submit" value="<s:text name='删除选中记录'></s:text>" /> 
			<a href="mallDiscountList"><input type="button" value="<s:text name='返回'></s:text>"/></a>
			
		</tr>
		<tr>
				<td colspan="10">
					<aldtags:pageTag />
				</td>
		</tr>
	</table>
</body>
</form>

</html>