<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="getUserBuildingJsp.title"><s:param>${user.userName}</s:param><s:param>${user.name}</s:param></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript">
	</script>
	<body>
		<form action="getUserBuilding?isCommit=T" method="post">
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center">
						<center>
							<s:text name="getUserBuildingJsp.title"><s:param>${user.userName}</s:param><s:param>${user.name}</s:param></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<s:text name="getUserBuildingJsp.nameSearch"></s:text><s:text name="colon"></s:text><input type="text" id="searchName" name="searchName" >
					</td>
					<td colspan="50">
						<input type="submit" value="<s:text name="search"></s:text>" class="button">
					</td>
				</tr>
		
			<c:if test="${userBuildingSomeInfoList != null}">
				<tr>
					<td>
						<s:text name="userBuilding.userBuildingId"></s:text>
					</td>
					<td>
						<s:text name="userBuilding.userId"></s:text>
					</td>
					<td>
						<s:text name="userBuilding.buildingName"></s:text>
					</td>
					<td>
						<s:text name="userBuilding.level"></s:text>
					</td>
					<td>
						<s:text name="userBuilding.npc1Id"></s:text>
					</td>
					<td>
						<s:text name="userBuilding.npc1EndTime"></s:text>
					</td>
					<td>
						<s:text name="userBuilding.npc1WorkTime"></s:text>
					</td>
					<td>
						<s:text name="userBuilding.state"></s:text>
					</td>
					<td>
						<s:text name="userBuilding.startTime"></s:text>
					</td>
					<td>
						<s:text name="userBuilding.endTime"></s:text>
					</td>
					<td>
						<s:text name="userBuilding.accelerateNPCCount"></s:text>
					</td>
					<td>
						<s:text name="userBuilding.accelerateaFirstTime"></s:text>
					</td>
				</tr>
				<s:iterator var="userBuildingSomeInfo" value="userBuildingSomeInfoList">
					<tr>
						<td>
							${userBuildingSomeInfo.userBuildingId}
						</td>
						<td>
							${userBuildingSomeInfo.userId}
						</td>
						<td>
							${userBuildingSomeInfo.buildingName}
						</td>
						<td>
							${userBuildingSomeInfo.level}
						</td>
						<td>
							${userBuildingSomeInfo.npc1Id}
						</td>
						<td>
							<s:text name="format.date_time">
								<s:param value="#userBuildingSomeInfo.npc1EndTime"></s:param>
							</s:text>
						</td>
						<td>
							<s:text name="format.date_time">
								<s:param value="#userBuildingSomeInfo.npc1WorkTime"></s:param>
							</s:text>
						</td>
						<td>
							${userBuildingSomeInfo.state}
						</td>
						<td>
							<s:text name="format.date_time">
								<s:param value="#userBuildingSomeInfo.startTime"></s:param>
							</s:text>
						</td>
						<td>
							<s:text name="format.date_time">
								<s:param value="#userBuildingSomeInfo.endTime"></s:param>
							</s:text>
						</td>
						<td>
							${userBuildingSomeInfo.accelerateNPCCount}
						</td>
						<td>
							<s:if test="#userBuildingSomeInfo.accelerateaFirstTime == null">
								<s:text name="getUserBuildingJsp.accelerateaFirstTime_empty"></s:text>
							</s:if>
							<s:else>
								<s:text name="format.date_time">
									<s:param value="#userBuildingSomeInfo.accelerateaFirstTime"></s:param>
								</s:text>
							</s:else>
							
						</td>
					</tr>
				</s:iterator>
			</c:if>
			</table>
		</form>
	</body>
	<script src="../../js/jquery/jquery.min.js"></script>
	<script src="../../js/jquery/jquery.validate.js"></script>
	<script type="text/javascript" >
		$(document).ready(function(){
			$("form").validate({
				rules:{
					searchName:{
						required: true,
					}
				}
			})
		});
	</script>
</html>