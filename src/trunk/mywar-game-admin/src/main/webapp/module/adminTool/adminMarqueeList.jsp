<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="adminMarqueeListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
	
		function del(id) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param>id</s:param><s:param>"+id+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delAdminMarquee?id=" + id;
				ajaxobj.callback = function() {
					//window.location.href = "blackList";
					var tr = document.getElementById(id);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(id) {
			window.location.href = "updateAdminMarquee?id=" + id;
		}
	
		function add() {
			window.location.href = "addAdminMarquee";
		}
	</script>
	<body>
		<input type="submit" value="<s:text name='addAdminMarqueeJsp.title'></s:text>" class="button" onclick=add(); />
		<!--
		<input type="button" value='<s:text name=""></s:text>' class="button" onclick="" />
		-->
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="adminMarqueeListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<s:text name="adminMarquee.content"></s:text>
				</td>
				<td >
					<s:text name="adminMarquee.serverIds"></s:text>
				</td>
				<td >
					<s:text name="adminMarquee.partnerIds"></s:text>
				</td>
				<td >
					<s:text name="adminMarquee.playInterval"></s:text>
				</td>
				<td>
					<s:text name="adminMarquee.loopTime"></s:text>
				</td>
				<td>
					<s:text name="adminMarquee.isUse"></s:text>
				</td>
				<td>
					<s:text name="adminMarquee.startTime"></s:text>
				</td>
				<td>
					<s:text name="adminMarquee.endTime"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="marquee" value="marqueeList" >
			<tr id="${marquee.id}">
				<td >
					${marquee.content}				
				</td>
				<td >
					${marquee.serverNames}
				</td>
				<td >
					${marquee.partnerNames}
				</td>
				<td >
					${marquee.playInterval}
				</td>
				<td>
					${marquee.loopTime}
				</td>
				<td >
					<s:text name="%{'adminMarquee.isUse_'+#marquee.isUse}"></s:text>
				</td>
				<td >
					${marquee.startTime}
				</td>
				<td >
					${marquee.endTime}
				</td>				
				<td>
					<a href="#" onclick='del("${marquee.id}")'><s:text name="delete"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='update("${marquee.id}")'><s:text name="update"></s:text></a>
				</td>		
			</tr>
			</s:iterator>
			<td colspan="100">
				<aldtags:pageTag paraStr="isCommit,T,pageSize,${pageSize}" />
			</td>
		</table>
	</body>
</html>
 	