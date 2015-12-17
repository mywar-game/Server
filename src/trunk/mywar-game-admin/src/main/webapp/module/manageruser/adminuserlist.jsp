<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title><s:text name="adminUserListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script type="text/javascript">
			function changeRole(adminUserId,roleId) {
				//选中请选择，返回
				if (roleId == "") {
					return;
				}
				//alert(adminUserId);
				//alert(roleId);
				var ajaxobj = new Ajax();
				ajaxobj.url = "updateAdminUserRole?roleId=" + roleId + "&adminUserId=" + adminUserId;
				ajaxobj.callback = function() {
					//alert("ajaxobj.gettext()=="+ajaxobj.gettext());
				    var responseMsg = eval('(' + ajaxobj.gettext() + ')');
				    //alert("responseMsg=="+responseMsg);
					alert(responseMsg.erroDescrip);
				}
				ajaxobj.send();
			}
		</script>
		
	</head>
	<body>
		<table cellpadding="3" cellspacing="1" border="0" width="100%"
			align=center>
			<tr class="border">
				<td class="td_title" colspan="10">
					<s:text name="adminUserListJsp.title"></s:text>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="adminUser.adminName"></s:text>
				</td>
				<td>
					<s:text name="adminUser.description"></s:text>
				</td>
				<td>
					<s:text name="adminUserListJsp.operation"></s:text>
				</td>
				<td>
					角色
				</td>
			</tr>
			<s:iterator var="data" value="adminList">
				<tr>
					<td>
						${data.adminName}
					</td>
					<td>
						${data.description}
					</td>
<!--					如果角色是超级管理员,并且是管理员自个儿登录，可以改密码	-->
					<s:if test="#data.roleId == 1 && adminUser.id == 1 && 'admin'.equals(adminUser.adminName)">
						<td>
							<a href="updatepassword?Id=${data.id}"><s:text name="adminUserListJsp.updatepwd"></s:text></a>&nbsp;
						</td>
						<td></td>
					</s:if>
<!--				其他人登录，不能对超级管理员操作	-->
					<s:elseif test="#data.roleId == 1">
						<td></td>
						<td></td>
					</s:elseif>
					<s:else>
						<td>
						<a href="updatepassword?Id=${data.id}"><s:text name="adminUserListJsp.updatepwd"></s:text></a>
						&nbsp;
						<a href="javascript:if(confirm('<s:text name="deleteConfirm"><s:param><s:text name="adminUser.adminName"></s:text></s:param><s:param>${data.adminName}</s:param></s:text>')) location.replace('deladminuser?Id=${data.id}');">
							<s:text name="adminUserListJsp.delete"></s:text>
						</a>
						&nbsp;
						<a href="updatemenupower?Id=${data.id}"><s:text name="adminUserListJsp.menuPower"></s:text></a>
						&nbsp;
						<a href="updatepower?Id=${data.id}"><s:text name="adminUserListJsp.power"></s:text></a>
						<s:if test="adminUser.id == 1 && 'admin'.equals(adminUser.adminName)">
							<a href="updatePwdDueTime?Id=${data.id}"><s:text name="adminUserListJsp.dueTime"></s:text></a>
							<a href="updateLock?Id=${data.id}"><s:text name="adminUserListJsp.lock"></s:text></a>
						</s:if>
						</td>
						<td>
							<s:select list="roleIdAndNameMap" onchange="changeRole(%{#data.id},this.value)" id="changeRole" name="changeRole" cssClass="select" theme="simple" headerKey="" headerValue="%{getText('pleaseSelect')}" value="#data.roleId" ></s:select>
						</td>
					</s:else>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="10">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>
