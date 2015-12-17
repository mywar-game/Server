<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
		<title><s:text name="getUserInfoJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		function deal(chatObject,type,name){
			var typeTextArray = new Array();
			<s:generator separator="," val="%{getText('userChatLogListJsp.operation_value')}">
				<s:iterator var="str">
						typeTextArray.push("<s:text name="%{'userChatLogListJsp.operation_'+#str}"></s:text>");
				</s:iterator>
			</s:generator>
			//alert(typeTextArray);	
			if(window.confirm('<s:text name="userChatLogListJsp.operateConfirm"><s:param>'+name+'</s:param><s:param>'+typeTextArray[type]+'</s:param></s:text>')){
				var ajaxobj = new Ajax();
		        ajaxobj.url="../log/userChatLogListdealPlayerState?operateType="+type+"&userID="+chatObject+"&sysNum="+${adminUser.exp1};
		        ajaxobj.callback=function(){
			    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
			    	alert(responseMsg.erroDescrip);
			    }
			    ajaxobj.send();
			}
		}
	</script>
	<body>
		<form action="getUserInfo?isCommit=T" method="post">
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center" >
						<center>
							<s:text name="getUserInfoJsp.title"></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<s:text name="log.lodoSearch"></s:text><s:text name="colon"></s:text><input type="text" name="lodoId" size="10">
					</td>
					<td colspan="6">
						<s:text name="log.userNameSearch"></s:text><s:text name="colon"></s:text><input type="text" name="userName" size="10">
					</td>
					<td colspan="50">
						<input type="submit" value="<s:text name="search"></s:text>" class="button">
					</td>
				</tr>
		
		<c:if test="${infoList != null}">
				<tr>
					<td>
						<s:text name="user.regName"></s:text>
					</td>
					<td>
						<s:text name="user.userName"></s:text>
					</td>
					<td>
						<s:text name="user.channel"></s:text>
					</td>
					<td>
						<s:text name="user.smallChannel"></s:text>
					</td>
					<td>
						<s:text name="user.lodoId"></s:text>
					</td>
					<td>
						<s:text name="user.level"></s:text>
					</td>
					<td>
						<s:text name="user.goldNum"></s:text>
					</td>
					<td>
						<s:text name="user.copper"></s:text>
					</td>
					<!--
					<td>
						<s:text name="user.orderNum"></s:text>
					</td>
					-->
					<td>
						<s:text name="user.exp"></s:text>
					</td>
					<td>
						<s:text name="user.power"></s:text>
					</td>
					<td>
						<s:text name="user.vipLevel"></s:text>
					</td>
					<td>
						<s:text name="user.rechargeNum"></s:text>
					</td>
					<td>
						<s:text name="getUserInfoJsp.getUserHeroLink"></s:text>
					</td>
					<td>
						<s:text name="getUserInfoJsp.getUserEquipmentLink"></s:text>
					</td>
					<td>
						<s:text name="getUserInfoJsp.getUserTreasureLink"></s:text>
					</td>
				</tr>
				<s:iterator var="userSomeInfo" value="infoList">
					<tr>
						<td>
							${userRegMap[userSomeInfo.userId].userName}
						</td>
						<td>
							${userSomeInfo.roleName}
						</td>
						<td>
							${userRegMap[userSomeInfo.userId].channelName}
						</td>
						<td>
							${userRegMap[userSomeInfo.userId].smallChannel}
						</td>
						<td>
							${userSomeInfo.ftId}
						</td>
						<td>
							${userSomeInfo.level}
						</td>
						<td>
							${userSomeInfo.money}
						</td>
						<td>
							${userSomeInfo.gold}
						</td>
						<td>
							${userSomeInfo.exp}
						</td>
						<td>
							${userSomeInfo.power}
						</td>
						<td>
							${userSomeInfo.vipLevel}
						</td>
						<td>
							${rechargeMap[userSomeInfo.userId]}
						</td>
						<td>
							<a href="getUserHero?isCommit=T&lodoId=${userSomeInfo.ftId}"><s:text name="getUserInfoJsp.getUserHeroLink"></s:text></a>
						</td>
						<td>
							<a href="getUserEquipment?isCommit=T&lodoId=${userSomeInfo.ftId}"><s:text name="getUserInfoJsp.getUserEquipmentLink"></s:text></a>
						</td>
						<td>
							<a href="getUserTreasure?userId=${userSomeInfo.userId}"><s:text name="getUserInfoJsp.getUserTreasureLink"></s:text></a>
						</td>
					</tr>
				</s:iterator>
				</c:if>
			</table>
		</form>
	</body>
</html>