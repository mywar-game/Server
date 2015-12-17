<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:text name="baPveConstantListJsp.title"></s:text></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="../../js/ajax.js"></script>
	
	<script type="text/javascript">
		function del(pveBigId,pveSmallId,pveSmallName){
			if(window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='pveSmallName'></s:text></s:param><s:param>"+pveSmallName+"</s:param></s:text>")){
				var ajaxobj = new Ajax();
		        ajaxobj.url="delBaPveConstant?pveBigId="+pveBigId+"&pveSmallId="+pveSmallId;
		        ajaxobj.callback=function(){
		        	//alert(ajaxobj.gettext());
		        	window.location.href = "baPveConstantList";
		        }
			    ajaxobj.send();
			}
		}
		
		function update(pveBigId,pveSmallId){
			window.location.href="updateBaPveConstant?pveBigId="+pveBigId+"&pveSmallId="+pveSmallId;
		}
	</script>
	
	</head>
	<body>
	    <table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
	    	<tr class="border">
				<td class="td_title" colspan="40" align="center">
					<center>
						<s:text name="baPveConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="pveBigId"></s:text>
				</td>
				<td>
					<s:text name="pveBigName"></s:text>
				</td>
				<td>
					<s:text name="pveSmallId"></s:text>
				</td>
				<td>
					<s:text name="pveSmallName"></s:text>
				</td>
				<td colspan="11">
					<s:text name="baPveConstant.pveDesc"></s:text>
				</td>
				
				<td width="35" rowspan="2">
					<s:text name="update"></s:text>
				</td>
				<td width="35" rowspan="2">
					<s:text name="delete"></s:text>
				</td>
			</tr>
			<tr>
				<td>
					${baPveConstant.id.pveBigId}
				</td>
				<td>
					${baPveConstant.pveBigName}
				</td>
				<td>
					${baPveConstant.id.pveSmallId}
				</td>
				<td>
					${baPveConstant.pveSmallName}
				</td>
				<td colspan="11">
					${baPveConstant.pveDesc}
				</td>
			</tr>
			
			<tr>
				<td>
					<s:text name="enterLevel"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.arenaId"></s:text>
				</td>
				<td>
					<s:text name="waveNum"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.bossType"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.checkPointType"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.failNum"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.startMaxTime"></s:text>
				</td>
				<td>
					<s:text name="maxRewardNum"></s:text>
				</td>
				<td>
					<s:text name="minRewardNum"></s:text>
				</td>
				<td>
					<s:text name="vipRewardAdd"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.maxNum"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.minNum"></s:text>
				</td>
				<td>
					<s:text name="addExp"></s:text>
				</td>
				<td>
					<s:text name="addRenow"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.modelId"></s:text>
				</td>
				
				<td rowspan="2">
					<a href="#" onclick="update(${baPveConstant.id.pveBigId},${baPveConstant.id.pveSmallId})"><s:text name="update"></s:text></a>
				</td>
				<td rowspan="2">
					<a href="#" onclick="del(${baPveConstant.id.pveBigId},${baPveConstant.id.pveSmallId},'${baPveConstant.pveSmallName}')"><s:text name="delete"></s:text></a>
				</td>
			</tr>
			<tr>
				<td>
					${baPveConstant.enterLevel}
				</td>
				<td>
					${mapAreaIdNameMap[baPveConstant.arenaId]}
				</td>
				<td>
					${baPveConstant.waveNum}
				</td>
				<td>
					<s:text name="%{'baPveConstant.bossType_'+baPveConstant.bossType}"></s:text>
				</td>
				<td>
					<s:text name="%{'baPveConstant.checkPointType_'+baPveConstant.checkPointType}"></s:text>
				</td>
				<td>
					${baPveConstant.failNum}
				</td>
				<td>
					${baPveConstant.startMaxTime}秒
				</td>
				<td>
					${baPveConstant.maxRewardNum}
				</td>
				<td>
					${baPveConstant.minRewardNum}
				</td>
				<td>
					${baPveConstant.vipRewardAdd}
				</td>
				<td>
					${baPveConstant.maxNum}
				</td>
				<td>
					${baPveConstant.minNum}
				</td>
				<td>
					${baPveConstant.addExp}
				</td>
				<td>
					${baPveConstant.addRenow}
				</td>
				<td>
					${baPveConstant.modelId}
				</td>
			</tr>
    	</table>
			
    	<table>
    		<tr class="border">
				<td class="td_title" colspan="40" align="center">
					<center>
						<s:text name="monsterInfo"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="monsterInfo_num"></s:text>
				</td>
				<td>
					<s:text name="monsterInfo_appearType"></s:text>
				</td>
				<td>
					<s:text name="monsterAppearType_addr"></s:text>
				</td>
				<td>
					<s:text name="monsterAppearType_time"></s:text>
				</td>
				<td>
					<s:text name="monsterInfo"></s:text>
				</td>
			</tr>
			<s:iterator var="info" value="monsterInfoList" status="sta">
				<tr>
					<td>
						<s:text name="monsterInfo_num_show"><s:param>${info.num}</s:param></s:text>
					</td>
					<td>
						<s:text name="%{'monsterInfo_appearType_'+#info.appearType+'_arg'}">
							<s:param>${info.time}</s:param>
						</s:text>
					</td>
					<td>
						<s:text name="%{'monsterAppearType_addr_'+monsterAppearTypeList[#sta.index].addr}"></s:text>
					</td>
					<td>
						<s:text name="monsterAppearType_time_show"><s:param>${monsterAppearTypeList[sta.index].time}</s:param></s:text>
					</td>
					<td>
						<s:iterator var="monsterMap" value="#info.monsterMapList" status="sta1">
							<s:text name="baPveConstantListJsp.monsterNum"><s:param>${sta1.index+1}</s:param></s:text><s:text name="colon"></s:text>
							<a href="mMonsterConstantList?id=${monsterMap.monsterId}">
								<s:text name="baPveConstantListJsp.monsterInfo">
									<s:param>
										${monsterMap.level}
									</s:param>
									<s:param>
										${monsterMap.monsterName}
									</s:param>
								</s:text>
							</a>
							<br/>
						</s:iterator>
					</td>
				</tr>
			</s:iterator>
    	</table>
			
    	<table>
    		<tr class="border">
				<td class="td_title" colspan="40" align="center">
					<center>
						<s:text name="reward"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="reward_rewardType"></s:text>
				</td>
				<td>
					<s:text name="reward_targetId"></s:text>
				</td>
				<td>
					<s:text name="reward_num"></s:text>
				</td>
				<td>
					<s:text name="reward_percent"></s:text>
				</td>
			</tr>
			<s:iterator var="prize" value="rewardList">
				<tr>
					<td>
						<s:text name="%{'reward_rewardType_'+#prize.rewardType}"></s:text>
					</td>
					<td>
						<s:if test="#prize.rewardType == 1">
							<font color="<s:text name="%{'userEquipment.color_'+#prize.color}"></s:text>">${prize.targetName}</font>
						</s:if>
						<s:elseif test="#prize.rewardType == 3">
							${prize.targetName}
						</s:elseif>
						<s:else>
							<a  href="tTreasureConstantList?id=${prize.targetId}">${prize.targetName}</a>
						</s:else>
					</td>
					<td>
						${prize.num}
					</td>
					<td>
						${prize.percent}<s:text name="reward_percent_suffix"></s:text>
					</td>
				</tr>
			</s:iterator>
    	</table>
			
    	<table>
    		<tr class="border">
				<td class="td_title" colspan="40" align="center">
					<center>
						<s:text name="vipReward"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="vipReward_rewardType"></s:text>
				</td>
				<td>
					<s:text name="vipReward_targetId"></s:text>
				</td>
				<td>
					<s:text name="vipReward_num"></s:text>
				</td>
				<td>
					<s:text name="vipReward_percent"></s:text>
				</td>
			</tr>
			<s:iterator var="vipPrize" value="vipRewardList">
				<tr>
					<td>
						<s:text name="%{'vipReward_rewardType_'+#vipPrize.rewardType}"></s:text>
					</td>
					<td>
						<s:if test="#vipPrize.rewardType == 1">
							<font color="<s:text name="%{'userEquipment.color_'+#vipPrize.color}"></s:text>">${vipPrize.targetName}</font>
						</s:if>
						<s:elseif test="#vipPrize.rewardType == 3">
							${vipPrize.targetName}
						</s:elseif>
						<s:else>
							<a  href="tTreasureConstantList?id=${vipPrize.targetId}">${vipPrize.targetName}</a>
						</s:else>
					</td>
					<td>
						${vipPrize.num}
					</td>
					<td>
						${vipPrize.percent}<s:text name="vipReward_percent_suffix"></s:text>
					</td>
				</tr>
			</s:iterator>
    	</table>
    	
    	<table >
    		<tr class="border">
				<td class="td_title" colspan="40" align="center">
					<center>
						<s:text name="baPveConstant.dropTaskTreasureInfo"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="dropTaskTreasureInfo.rewardType"></s:text>
				</td>
				<td>
					<s:text name="dropTaskTreasureInfo.rewardId"></s:text>
				</td>
				<td>
					<s:text name="dropTaskTreasureInfo.rewardNum"></s:text>
				</td>
			</tr>
			<s:iterator var="dropTaskTreasureInfo" value="dropTaskTreasureInfoList">
				<tr>
					<td>
						<s:text name="%{'dropTaskTreasureInfo.rewardType_'+#dropTaskTreasureInfo.rewardType}"></s:text>
					</td>
					<td>
						<s:if test="#dropTaskTreasureInfo.rewardType == 3">
							<s:text name="%{'dropTaskTreasureInfo.rewardId_3_'+#dropTaskTreasureInfo.rewardId}"></s:text>
						</s:if>
						<s:else>
							<a  href="tTreasureConstantList?id=${dropTaskTreasureInfo.rewardId}">
								${dropTaskTreasureInfo.rewardName}
							</a>
						</s:else>
					</td>
					<td>
						${dropTaskTreasureInfo.rewardNum}
					</td>
				</tr>
			</s:iterator>
    	</table>
    	
    	<table>
    		<tr class="border">
				<td class="td_title" colspan="40" align="center">
					<center>
						<s:text name="baPveConstant.firstReward"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="baPveConstant.firstReward_rewardType"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.firstReward_targetId"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.firstReward_num"></s:text>
				</td>
			</tr>
			<s:iterator var="firstReward" value="firstRewardList">
				<tr>
					<td>
						<s:text name="%{'baPveConstant.firstReward_rewardType_'+#firstReward.rewardType}"></s:text>
					</td>
					<td>
						<s:if test="#firstReward.rewardType == 1">
							<font color="<s:text name="%{'userEquipment.color_'+#firstReward.color}"></s:text>">${firstReward.targetName}</font>
						</s:if>
						<s:elseif test="#firstReward.rewardType == 3">
							${firstReward.targetName}
						</s:elseif>
						<s:else>
							<a  href="tTreasureConstantList?id=${firstReward.targetId}">${firstReward.targetName}</a>
						</s:else>
					</td>
					<td>
						${firstReward.num}
					</td>
				</tr>
			</s:iterator>
    	</table>
    	
    	<table>
    		<tr class="border">
				<td class="td_title" colspan="40" align="center">
					<center>
						<s:text name="baPveConstant.rewardShow"></s:text>
					</center>
				</td>
			</tr>
			<s:generator separator="," val="baPveConstant.rewardShow">
				<s:iterator var="one">
					<tr>
						<td>
							<s:set var="type" value="#one.split(':')[0]"></s:set>
							<s:set var="num" value="#one.split(':')[1]"></s:set>
							<s:if test="#type == 1">
								<font color="<s:text name="%{'userEquipment.color_'+#num}"></s:text>">
									<s:text name="%{'userEquipment.quality_'+#num}"></s:text><s:text name="%{'baPveConstant.rewardShow_type_'+#type}"></s:text>
								</font>
							</s:if>
							<s:elseif test="#type < 1000">
								<s:text name="%{'baPveConstant.rewardShow_type_'+#type}"></s:text>*${num}
							</s:elseif>
							<s:else>
								<a href="tTreasureConstantList?id=${type}">
									<s:property value="treasureIDNameMap[#type]"/>*${num}
								</a>
							</s:else>
						</td>
					</tr>
				</s:iterator>
			</s:generator>
    	</table>
    	
	</body>
</html>
