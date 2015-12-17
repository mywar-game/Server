<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>角色界面权限配置</title>
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
			for ( var i = 0; i < allInputTags.length; i++) {
				//如果input标签的那么是powerIdArr，那么就选中
				if(allInputTags[i].name == "powerIdArr") {
					allInputTags[i].checked = isCheck;
				}
			}
		}
		
		function changeSonCheck(a,isCheck){
			var td = a.parentNode;
			var inputs = td.getElementsByTagName("INPUT");
			for ( var i = 0; i < inputs.length; i++) {
				if(inputs[i].name == "powerIdArr") {
					inputs[i].checked = isCheck;
				}
			}
			var tr = a.parentNode.parentNode;
			var nextTd = tr.cells[td.cellIndex+1];
			var inputs = nextTd.getElementsByTagName("INPUT");
			for ( var i = 0; i < inputs.length; i++) {
				if(inputs[i].name == "powerIdArr") {
					inputs[i].checked = isCheck;
				}
			}
		}
		
	</script>
	<body>
		&nbsp;
		<form action="updateAdminRoleMenuPower?roleId=${adminRole.roleId}&isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tbody>
					<tr class="border">
						<td class="td_title" colspan="5">
							<center>
								配置角色<font color='red'>${adminRole.roleName}</font>的界面权限 &nbsp;
								<font color='red'>${erroDescrip}</font>
								<a href="javascript:;" onclick="changeAllCheck(true)">全选</a>
								<a href="javascript:;" onclick="changeAllCheck(false)">全不选</a>
							</center>
						</td>
					</tr>
					
<!--				将角色的菜单权限分割为字符串-->
					<s:set name="rolePowerArr" value="adminRole.menuPowerString.toCharArray()"></s:set>
	<!--			一级菜单，即最上面的一排菜单	-->
					<s:set name="firstMenuList" value="map.get(0)"></s:set>
					<s:iterator var="firstMenu" value="#firstMenuList">
						<s:set name="secondMenuList" value="map.get(#firstMenu.id)"></s:set>
						<tr>
							<td>
								<a href="javascript:;" onclick="changeSonCheck(this,true)">全选</a>
								<a href="javascript:;" onclick="changeSonCheck(this,false)">全不选</a><br/>
								--${firstMenu.menuName}  
								<input name="powerIdArr" class="checkbox" value="${firstMenu.powerId}" <s:if test="#rolePowerArr[#firstMenu.powerId-1] == '1'">checked="checked"</s:if> type="checkbox">
							</td>
							<td>
								<table>
									<tbody>
<!--									第二级菜单-->
										<s:iterator var="secondMenu" value="#secondMenuList">
											<tr>
												<td>
													--${secondMenu.menuName}
													<input name="powerIdArr" class="checkbox" value="${secondMenu.powerId}" <s:if test="#rolePowerArr[#secondMenu.powerId-1] == '1'">checked="checked"</s:if> type="checkbox">
												</td>
<!--											第三级菜单-->
												<s:if test='"F".equals(#secondMenu.ifLeaf)'>
													<s:set name="thirdMenuList" value="map.get(#secondMenu.id)"></s:set>
													<td>
														<table>
															<tbody>
																<s:iterator var="thirdMenu" value="#thirdMenuList">
																	<tr>
																		<td>
																			--${thirdMenu.menuName}
																			<input name="powerIdArr" class="checkbox" value="${thirdMenu.powerId}" <s:if test="#rolePowerArr[#thirdMenu.powerId-1] == '1'">checked="checked"</s:if> type="checkbox">
																		</td>
																	</tr>
																</s:iterator>
															</tbody>
														</table>
													</td>
												</s:if>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</td>
						</tr>
					</s:iterator>
					
					<tr>
						<td colspan="5" align="center">
							<input type="submit" value="下一步" class="button" />
							<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>