<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="uContinueLoginConstantListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="../../js/ajax.js"></script>
	</head>
	<script type="text/javascript">
		function reflashconstantcache() {
			var ajaxobj = new Ajax();
			ajaxobj.url="reflashConstantCache?cacheType=21&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="uContinueLoginConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="uContinueLoginConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
	
		function update(id) {
			window.location.href = "updateUContinueLoginConstant?id=" + id;
		}
	</script>
	<body>
		<input type="button" value='<s:text name="uContinueLoginConstantListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="9" align="center">
					<center>
						<s:text name="uContinueLoginConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="continueLoginConstant.id"></s:text>
				</td>
				<td>
					<s:text name="continueLoginConstant.type"></s:text>
				</td>
				<td>
					<s:text name="continueLoginConstant.continueDays"></s:text>
				</td>
				<td>
					<center><s:text name="continueLoginConstant.rewardInfo"></s:text></center>
				</td>

				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="continueLoginConstant" value="continueLoginConstantList" status="sta">
				<s:set var="rewardList" value="allRewardList[#sta.index]"></s:set>
				<tr>
					<td>
						${continueLoginConstant.id}
					</td>
					<td>
						<s:text name="%{'continueLoginConstant.type_'+#continueLoginConstant.type}"></s:text>
					</td>
					<td>
						${continueLoginConstant.continueDays}<s:if test="#continueLoginConstant.continueDays == 0">（一个月）</s:if>
					</td>
					<td>
						<s:iterator var="reward" value="rewardList" status="stat">
							<s:if test="#reward.rewardType == 1">
								<a href="tTreasureConstantList?id=${reward.rewardId}">${treasureIDNameMap[reward.rewardId]}</a>x${reward.rewardNum}
							</s:if>
							<s:if test="#reward.rewardType == 2">
								<a href="eEquipmentConstantList?id=${reward.rewardId}">${equipmentIdNameMap[reward.rewardId]}</a>x${reward.rewardNum}
							</s:if>
							<s:if test="#reward.rewardType == 3">
								<s:if test="#reward.rewardId == 1">
									${reward.rewardNum}<s:text name="continueLoginConstant.rewardInfo.rewardId_3_1"></s:text>
								</s:if>
								<s:if test="#reward.rewardId == 2">
									${reward.rewardNum}<s:text name="continueLoginConstant.rewardInfo.rewardId_3_2"></s:text>
								</s:if>
							</s:if>
						</s:iterator>
					</td>
					<td>
						<a href="#" onclick='update("${continueLoginConstant.id}")'><s:text name="update"></s:text></a>
					</td>
					
				</tr>
			</s:iterator>
		</table>
	</body>
</html>