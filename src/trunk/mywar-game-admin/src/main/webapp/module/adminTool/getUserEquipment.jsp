<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="getUserEquipmentJsp.title1"><s:param>${searchName}</s:param></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript">
		function modify(userEquipmentID,userId,searchName,equipmentName,skillPoints,skillNames){
			equipmentName = encodeURI(encodeURI(equipmentName));
			skillNames = encodeURI(encodeURI(skillNames));
			var url = "modifyUserEquipmentSkill?userEquipmentID="+userEquipmentID+"&userId="+userId+"&searchName="+searchName+"&equipmentName="+equipmentName+"&skillPoints="+skillPoints+"&skillNames="+skillNames;
			//alert("url:"+url);
			location.href = url;
		}
	</script>
	<body>
		<form action="getUserEquipment?isCommit=T" method="post">
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center">
						<center>
							<s:text name="getUserEquipmentJsp.title1"><s:param>${lodoId}</s:param></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<s:text name="log.lodoSearch"></s:text><s:text name="colon"></s:text>
						<input type="text" id="lodoId" name="lodoId">
					</td>
					<td colspan="50">
						<input type="submit" value="<s:text name="search"></s:text>" class="button">
					</td>
				</tr>


				<c:if test="${userEquipmentSomeInfoList != null}">
						<tr>
							<td>	
								<s:text name="userEquipment.userEquipId"></s:text>
							</td>
							<td>	
								<s:text name="userEquipment.equipId"></s:text>
							</td>
							<td>	
								<s:text name="userEquipment.userHeroId"></s:text>
							</td>
							<td>	
								<s:text name="userEquipment.equipLevel"></s:text>
							</td>
							<td>	
								<s:text name="userEquipment.equipName"></s:text>
							</td>
							<td>	
								<s:text name="userEquipment.equipType"></s:text>
							</td>
							<td>	
								<s:text name="userEquipment.life"></s:text>
							</td>
							<td>	
								<s:text name="userEquipment.physicalAttack"></s:text>
							</td>
							<td>	
								<s:text name="userEquipment.physicalDefense"></s:text>
							</td>
							<td>	
								<s:text name="userEquipment.upgradeRate"></s:text>
							</td>
							<td>	
								<s:text name="userEquipment.price"></s:text>
							</td>
						</tr>
						
						<s:iterator var="userEquipmentSomeInfo" value="userEquipmentSomeInfoList">
							<tr>
								<td>
									${userEquipmentSomeInfo.userEquipId}
								</td>
								<td>
									${userEquipmentSomeInfo.equipId}
								</td>
								<td>
									${userEquipmentSomeInfo.userHeroId}
								</td>
								<td>
									${userEquipmentSomeInfo.equipLevel}
								</td>
								<td>
									${userEquipmentSomeInfo.equipName}
								</td>
								<td>
									${userEquipmentSomeInfo.equipType}
								</td>
								<td>
									${userEquipmentSomeInfo.life}
								</td>
								<td>
									${userEquipmentSomeInfo.physicsAttack}
								</td>
								<td>
									${userEquipmentSomeInfo.physicsDefense}
								</td>
								<td>
									${userEquipmentSomeInfo.upgradetRate}
								</td>
								<td>
									${userEquipmentSomeInfo.price}
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