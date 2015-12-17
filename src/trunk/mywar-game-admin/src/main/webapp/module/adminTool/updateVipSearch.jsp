<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>常量列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
		</head>


<body>
	<form action="updateVipSearch?isCommit=T" method="post">
		<table>
			<tr class="border">
					<td class="td_title" colspan="100" align="center" >
						<center>
							<s:text name="查看玩家信息"></s:text>
						</center>
					</td>
				</tr>
			<tr>
				<td>游戏 ID</td>
				<td><input type="text" name="lodoId"></td>
			</tr>
			<tr>
				<td><input type="submit" value="搜索"></td>
			</tr>
		</table>
	
	</form>
</body>		
</html>
		