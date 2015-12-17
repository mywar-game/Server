<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userRankSnapshotListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/ajax.js"></script>
	</head>
	<script type="text/javascript">
		function del(snapshotName,ids,time) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="userRankSnapshotListJsp.userRankSnapshot"></s:text></s:param><s:param>'+snapshotName+'('+time+')</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delUserRankSnapshot?ids=" + ids;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(ids);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
		}
	
		function add() {
			window.location.href = "addUserRankSnapshot";
		}
	
		function show(ids) {
			window.location.href = "userRankSnapshotDetailList?ids="+ids;
		}
	</script>
	<body>
		<form action="addUserRankSnapshot" method="post">
			<select name="createSnapshotType" class="select">
				<s:generator separator="," val="%{getText('userRankSnapshot.type_value')}">
					<s:iterator var="str">
						<option value="${str}">
							<s:text name="%{'userRankSnapshot.type_'+#str}"></s:text>
						</option>
					</s:iterator>
				</s:generator>	
			</select>
			<input type="submit" value="<s:text name="userRankSnapshotListJsp.createSnapshot"></s:text>" class="button" onclick=add(); />
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						<s:text name="userRankSnapshotListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="userRankSnapshot.type"></s:text>
				</td>
				<td>
					<s:text name="userRankSnapshot.createName"></s:text>
				</td>
				<td>
					<s:text name="userRankSnapshot.createTime"></s:text>
				</td>

				<td width="35">
					<s:text name="userRankSnapshotListJsp.see"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
			</tr>
			
			<s:iterator var="userRankSnapshot" value="userRankSnapshotList" status="sta">
<!--			一次快照的id的集合	-->
<!--			如果排名为1，直接集合等于id，否则的话在原来集合的基础上添加都好再添加id	-->
				<s:if test="#userRankSnapshot.rank == 1">
					<s:set name="ids" value="#userRankSnapshot.id"></s:set>
				</s:if>
				<s:else>
					<s:set name="ids" value="#ids+','+#userRankSnapshot.id"></s:set>
				</s:else>
<!--			如果没有了下一个或者下一个排名为1，说明这一次排名快照结束了，此时显示下此次排名快照-->
				<s:if test="(#sta.index+1) == userRankSnapshotList.size || userRankSnapshotList.get(#sta.index+1).rank == 1">
					<tr id="${ids}">
						<td>
							<s:text name="%{'userRankSnapshot.type_'+#userRankSnapshot.type}"></s:text>
						</td>
						<td>
							${userRankSnapshot.createName}
						</td>
						<td>
							<s:text name="format.date_time"><s:param value="#userRankSnapshot.createTime"></s:param></s:text>
						</td>
						<td>
							<a href="#" onclick='show("${ids}")'><s:text name="userRankSnapshotListJsp.see"></s:text></a>
						</td>
						<td>
							<a href="#" onclick='del("<s:text name="%{'userRankSnapshot.type_'+#userRankSnapshot.type}"></s:text>","${ids}","<s:text name="format.date_time"><s:param value="#userRankSnapshot.createTime"></s:param></s:text>")'><s:text name="delete"></s:text></a>
						</td>
					</tr>
				</s:if>
			</s:iterator>
		</table>
	</body>
</html>