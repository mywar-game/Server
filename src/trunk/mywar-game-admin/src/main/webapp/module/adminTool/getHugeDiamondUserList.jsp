<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
		<title><s:text name="getHugeDiamondUserListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		
	</script>
	<body>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center >
			<tr class="border">
				<td class="td_title" colspan="100" align="center" >
					<center>
						<s:text name="getHugeDiamondUserListJsp.title"></s:text>
						<font color="red">${erroDescrip}</font>
					</center>
				</td>
			</tr>
			
			<tr>
				<td>
					<s:text name="user.userName"></s:text>
				</td>
				<td>
					<s:text name="user.name"></s:text>
				</td>
				<td>
					<s:text name="user.level"></s:text>
				</td>
				<td>
					<s:text name="getHugeDiamondUserListJsp.lastConsumeTime"></s:text>
				</td>
				<td>
					<s:text name="user.lastLogOutTime"></s:text>
				</td>
				<td>
					<s:text name="user.golden"></s:text>
				</td>
				<td>
					<s:text name="getHugeDiamondUserListJsp.payAmount"></s:text>
				</td>
				<td>
					<s:text name="user.money"></s:text>
				</td>
			</tr>
		
			<s:iterator value="userBeanList" var="userBean">
				<tr>
					<td>
						${userBean.userName}
					</td>
					<td>
						${userBean.name}
					</td>
					<td>
						${userBean.level}
					</td>
					<td>
						<s:if test="#userBean.lastBeAttackCityTime != null">
							<s:text name="format.date_time">
								<s:param value="#userBean.lastBeAttackCityTime"></s:param>
							</s:text>
						</s:if>
					</td>
					<td>
						<s:if test="#userBean.lastLogOutTime != null">
							<s:text name="format.date_time">
								<s:param value="#userBean.lastLogOutTime"></s:param>
							</s:text>
						</s:if>
					</td>
					<td>
						${userBean.golden}
					</td>
					<td>
						${userBean.silver}
					</td>
					<td>
						${userBean.money}
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>