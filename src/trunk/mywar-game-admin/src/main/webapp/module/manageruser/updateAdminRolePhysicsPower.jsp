<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>角色功能权限配置</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function changeAllCheck(isCheck){
			//所有的input标签
			var allInputTags = document.getElementsByTagName("INPUT");
			var x = 0;
			for ( var i = 0; i < allInputTags.length; i++) {
				//如果input标签的那么是powerIdArr，那么就选中
				if(allInputTags[i].name == "powerIdArr") {
					allInputTags[i].checked = isCheck;
					x++;
				}
			}
			//alert(x);
		}
		
		function changeSonCheck(a,isCheck){
			var tr = a.parentNode.parentNode;
			var table = document.getElementById("table");
			//在表中，从当前的tr往下查，如果tr中有input，并且是功能配置，那么就选中；如果木有，说明到了下一个全选，那么结束
			for ( var i = tr.rowIndex+1; i < table.rows.length; i++) {
				var tempTr = table.rows[i];
				var inputs = tempTr.getElementsByTagName("INPUT");
				
				if (inputs.length == 0) {
					break;
				}
				for ( var j = 0; j < inputs.length; j++) {
					if(inputs[j].name == "powerIdArr") {
						inputs[j].checked = isCheck;
					}
				}
			}
		}
		
	</script>
	<body>
		&nbsp;
		<form action="updateAdminRolePhysicsPower?roleId=${adminRole.roleId}&isCommit=T" method="post" >
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center id="table">
				<tr class="border">
					<td class="td_title" colspan="6">
						<center>
							配置角色<font color='red'>${adminRole.roleName}</font>的功能权限 &nbsp;
							<font color='red'>${erroDescrip}</font>
							<a href="javascript:;" onclick="changeAllCheck(true)">全选</a>
							<a href="javascript:;" onclick="changeAllCheck(false)">全不选</a>
						</center>
					</td>
				</tr>
				
<!--			将角色的菜单权限分割为字符串-->
				<s:set name="rolePowerArr" value="adminRole.powerString.toCharArray()"></s:set>
				<s:iterator var="entry" value="map">
					<s:set name="powerList" value="#entry.value"></s:set>
					<tr>
						<td colspan="6">
							<b>${entry.key}</b>
							<a href="javascript:;" onclick="changeSonCheck(this,true)">全选</a>
							<a href="javascript:;" onclick="changeSonCheck(this,false)">全不选</a><br/>
						</td>
					</tr>
					<tr>
						<s:iterator var="power" value="#powerList" status="sta">
<!--						最后一个要对齐格式-->
							<td <s:if test="#sta.last">colspan='<s:property value="6 - #sta.count%6 + 1"/>'</s:if>>
								<input name="powerIdArr" class="checkbox" value="${power.powerId}" <s:if test="#rolePowerArr[#power.powerId-1] == '1'">checked="checked"</s:if> type="checkbox">
								${power.name}
<!--							一行6个-->
								<s:if test="#sta.count%6 == 0">
									<tr>
								</s:if>
							</td>
						</s:iterator>
				</s:iterator>
				
				<tr>
					<td colspan="6" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>