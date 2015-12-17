<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>注册链接列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
	</head>
		<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		function reflashconstantcache() {
			var ajaxobj = new Ajax();
			ajaxobj.url="../dataconfig/reflashConstantCache?cacheType=<s:property value='@com.dataconfig.constant.ReflashCacheConstant@REFLASH_TYPE_REG_LINK'/>&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新注册链接缓存成功');
				} else {
					alert('刷新注册链接缓存失败');
				}
		    }
			ajaxobj.send();
		}
	</script>
	<body>
		<input type="button" value='刷新注册链接缓存' class="button" onclick="reflashconstantcache()" />
		<form action="userRegLinkLogList" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr>
<!--					<td>-->
<!--						<s:text name="log.nameSearch"></s:text><s:text name="colon"></s:text>-->
<!--					</td>-->
<!--					<td>-->
<!--						<input type="text" id="name" name="name" value="${name}"/>-->
<!--					</td>-->
					
					<td>注册链接</td>
					<td>
						<input type="text" id="searchRegLink" name="searchRegLink" value="${searchRegLink}" />
					</td>
<!--				</tr>-->
				
<!--				<tr>-->
					<td>
						生成时间：
					</td>
					<td colspan="3">
						<input type="text" id="createStartTime" name="createStartTime" value="<s:if test="createStartTime != null"><s:text name="format.date_time"><s:param value="createStartTime"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){createEndTime.focus();}})"/>
						<s:text name="timeTo"></s:text>
						<input type="text" id="createEndTime" name="createEndTime" value="<s:if test="createEndTime != null"><s:text name="format.date_time"><s:param value="createEndTime"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'createStartTime\')}'})"/>
					</td>
					<td>
						<s:text name="pageSize"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
					</td>
					
					<td colspan="2">
						<center>
							<input type="submit" value="<s:text name="search"></s:text>" />
						</center>
					</td>
				</tr>
			</table>
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						注册链接列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					序号
				</td>
				<td>
					注册链接
				</td>
				<td>
					奖励
				</td>
				<td>
					生成时间
				</td>
				<td>
					有效时间
				</td>
				<td>
					有效次数
				</td>
				<td>
					使用次数
				</td>
				<td>
					剩余次数
				</td>
			</tr>
			<s:iterator var="log" value="logList" status="sta">
				<tr>
					<td>
						${sta.index+1}
					</td>
					<td>
						${log.regLinkId}
					</td>
					<td>
						<s:set var="rewardList" value="allRewardList[#sta.index]"></s:set>
						
						<s:iterator var="rewardMap" value="rewardList" status="stat">
							<s:if test="#rewardMap.category == 2">
								<a href="tTreasureConstantList?id=${rewardMap.targetId}">${treasureIDNameMap[rewardMap.targetId]}</a>*${rewardMap.num}
							</s:if>
							<s:if test="#rewardMap.category == 3">
								<a href="eEquipmentConstantList?id=${rewardMap.targetId}">${equipmentIdNameMap[rewardMap.targetId]}</a>*${rewardMap.num}
							</s:if>
							<s:if test="#rewardMap.category == 1">
								${rewardMap.num}<s:text name="%{'regLinkList.rewardInfo.rewardId_1_'+#rewardMap.targetId}"></s:text>
							</s:if>
						</s:iterator>
					</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#log.createTime"></s:param>
						</s:text>
					</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#log.effectiveTime"></s:param>
						</s:text>
					</td>
					<td>
						${log.effectiveNum}
					</td>
					<td>
						${log.useNum}
					</td>
					<td>
						${log.effectiveNum-log.useNum}
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="10">
					<aldtags:pageTag paraStr="searchRegLink,${searchRegLink},pageSize,${pageSize}" datePara1="createStartTime" dateValue1="${createStartTime}" datePara2="createEndTime" dateValue2="${createEndTime}"/>
				</td>
			</tr>
		</table>
	</body>
</html>