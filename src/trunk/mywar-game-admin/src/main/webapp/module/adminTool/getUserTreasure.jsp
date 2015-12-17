<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="getUserTreasureJsp.title"><s:param>${user.userName}</s:param><s:param>${user.name}</s:param></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript">
		function check(){
			var lodoId = document.getElementById("lodoId").value;
			if(lodoId == 0){
				alert("<s:text name="getUserTreasureJsp.atLeastOneCondition"></s:text>");			
				return false;
			}
		}
	</script>
	<body>
		<form action="getUserTreasure" method="post" onsubmit="return check()">
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center">
						<center>
							<s:text name="getUserTreasureJsp.title"><s:param>${user.lodoId}</s:param></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<s:text name="log.lodoSearch"></s:text><s:text name="colon"></s:text><input type="text" id="lodoId" name="lodoId" size="10">
					</td>
					<td colspan="100">
						<input type="submit" value="<s:text name="search"></s:text>" class="button">
					</td>
				</tr>
		
				<c:if test="${userTreasureList != null}">
						<tr>
							<td><s:text name="userTreasure.userId"></s:text></td>
							<td><s:text name="userTreasure.toolId"></s:text></td>
							<td><s:text name="userTreasure.toolNum"></s:text></td>
							<td><s:text name="userTreasure.toolType"></s:text></td>
						</tr>
						<s:iterator var="userTreasure" value="userTreasureList">
							<tr>
								<td>
								<!-- 
									<s:set var="treasureId" value="#userTreasure.treasureID"></s:set>
									<s:property value="treasureIDNameMap[#treasureId]"/><br>
								 -->	
									${user.userId}
								</td>
								<td>
								<!-- 
									<s:text name="%{'treasureType_'+#userTreasure.type}"></s:text>
									-->
									${userTreasure.toolId}
								</td>
								<td>
									${userTreasure.toolNum}
								</td>
								<td>
									${userTreasure.toolType}
								</td>
							</tr>
						</s:iterator>
				</c:if>
				</table>
		</form>
	</body>
</html>