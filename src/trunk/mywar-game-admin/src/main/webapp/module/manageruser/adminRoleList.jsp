<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>角色列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		function del(roleId,roleName) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="roleName"></s:text></s:param><s:param>'+roleName+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delAdminRole?roleId=" + roleId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(roleId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(roleId) {
			window.location.href = "updateAdminRole?roleId=" + roleId;
		}
	
		function add() {
			window.location.href = "addAdminRole";
		}
	</script>
	<body>
		<input type="submit" value="添加角色" class="button" onclick=add(); />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						角色列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					角色名
				</td>
				<td>
					描述
				</td>
				<td>
					<s:text name="update"></s:text>
				</td>
				<td>
					<s:text name="delete"></s:text>
				</td>
			</tr>
			<s:iterator var="adminRole" value="adminRoleList">
				<tr id="${adminRole.roleId}">
					<td>
						${adminRole.roleName}
					</td>
					<td>
						${adminRole.description}
					</td>

<!--				如果是超级管理员，不能改动	-->
					<s:if test="#adminRole.roleId == 1">
						<td></td>
						<td></td>
					</s:if>
					<s:else>
						<td>
							<a href="updateAdminRoleMenuPower?roleId=${adminRole.roleId}">【界面权限】</a>
							<a href="updateAdminRolePhysicsPower?roleId=${adminRole.roleId}">【功能权限】</a>
							<a href="#" onclick='update("${adminRole.roleId}")'>【<s:text name="update"></s:text>】</a>
						</td>
						<td>
							<a href="#" onclick='del("${adminRole.roleId}","${adminRole.roleName}")'><s:text name="delete"></s:text></a>
						</td>
					</s:else>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>