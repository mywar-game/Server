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
		<script type="text/javascript">
		function filteList() {
			var trList = document.getElementById("table");
			
			for (var i = 1; i < trList.rows.length; i++) {
				if (trList.rows[i].firstElementChild.value != 0) {
					trList.deleteRow(i);
					i = i - 1;
				}
			}
			
		}
		</script>
</head>
<body>
<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
		<tr class="border">
			<td class="td_title" ><s:text name="活动审核列表"></s:text></td>
		</tr></table>
<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
		<tr>
			<td width="7%"><s:text name="活动编号"></s:text></td>
			<td width="60%"><s:text name="活动描述"></s:text></td>
			<td><s:text name="送审日期"></s:text></td>
			<td><s:text name="状态"></s:text></td>
			<td><s:text name="操作"></s:text></td>
		</tr>

		<s:iterator var="data" value="mallDiscountList">
			<tr>
				<input type="hidden" value="${data.status}"/>
				<td>${data.id}</td>
				<td>${data.description}</td>
				<td>${data.submitTime}</td>
				
				<s:if test="%{#data.status == 0}">
					<td><s:text name="待审查"></s:text></td>
					<td><a href="auditActivity?activityId=${data.activityId}"><s:text name="审核"></s:text></a></td>
				</s:if>
				<s:elseif test="%{#data.status == -1}">
					<td><s:text name="拒绝"></s:text></td>
					<td>
						<a href="updateMallDiscount?activityId=${data.activityId}"><s:text name="修改"></s:text></a> &nbsp;
						<a href="auditActivity?activityId=${data.activityId}"><s:text name="审核"></s:text></a>
					</td>
					
				</s:elseif>
				<s:elseif test="%{#data.status == 1}">
					<td><s:text name="审查通过"></s:text></td>
					<td><a href="updateMallDiscount?activityId=${data.activityId}"><s:text name="修改"></s:text></a>
				</s:elseif>

				<%-- <td>
				<a
					href="javascript:if(confirm('<s:text name="确定删除吗？"><s:param>${data.id}</s:param></s:text>'))location.replace('delAudit?id=${data.id}');">
						<s:text name="删除"></s:text>
				</a> 
					&nbsp;
					
					<a href="updateAudit?id=${data.id}"><s:text name="修改"></s:text></a>
					
					&nbsp;
					<a href="auditActivity?id=${data.id}"><s:text name="审核"></s:text></a>
				</td> --%>
			</tr>
		</s:iterator>
		<%-- <tr>
			<td colspan="10"><aldtags:pageTag/></td>
		</tr> --%>
		<tr>
			<td><a href="javascript:filteList()"><input type="button" value="仅显示待审核" /></a></td>
		</tr>
	</table>

</body>

</html>