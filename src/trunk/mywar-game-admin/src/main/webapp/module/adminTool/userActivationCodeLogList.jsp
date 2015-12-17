<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>激活码列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
		
		<script type="text/javascript" src="../../js/ajax.js"></script>
		
		<script src="../../js/jquery/ui/jquery.ui.core.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.widget.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.position.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.autocomplete.js"></script>
		
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
		
		<script>
			$(function() {
				$("#searchPlatform").autocomplete({
					minLength: 0,
					source: function( request, response ) {
						$.ajax({
							url: "../adminTool/activationCodeListfindPlatformMapByCondition",
							data:{platform: encodeURI(request.term)},
							success: function(data) {
								response($.map( data, function(name,id) {
									return {
										id: id,
										label: name,
										value: name
									}
								}));
							}
						});
					},
					open: function() {
						$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
					},
					close: function() {
						$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
					}
				});
			});
			
			function reflashconstantcache() {
				var ajaxobj = new Ajax();
				ajaxobj.url="../dataconfig/reflashConstantCache?cacheType=<s:property value='@com.dataconfig.constant.ReflashCacheConstant@REFLASH_TYPE_ACTIVATIONCODE'/>&number="+Math.random();;
			    ajaxobj.callback=function(){
			    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
			    	//alert(responseMsg.erroCodeNum);
			    	if (responseMsg.erroCodeNum == 0) {
						alert('刷新激活码缓存成功');
					} else {
						alert('刷新激活码缓存失败');
					}
			    }
				ajaxobj.send();
			}
			
			function downloadExcel() {
				var ajaxobj = new Ajax();
				var searchPlatform = document.getElementById("searchPlatform").value;
				var searchCode = document.getElementById("searchCode").value;
				var createStartTime = document.getElementById("createStartTime").value;
				var createEndTime = document.getElementById("createEndTime").value;
				var pageSize = document.getElementById("pageSize").value;
				ajaxobj.url = "userActivationCodeLogListgenerateExcel?searchPlatform="+ searchPlatform +"&searchCode="+searchCode + "&createStartTime=" + createStartTime + "&createEndTime=" + createEndTime + "&pageSize=" + pageSize;
				ajaxobj.callback = function() {
					window.location.href = "../system/action/downloadingZip?fileName=activationCode.xls";
				}
				ajaxobj.send();
			}
		</script>
	</head>
	<body>
		<input type="button" value='刷新激活码缓存' class="button" onclick="reflashconstantcache()" />
		<input type="button" value='下载激活码' class="button" onclick="downloadExcel()" />
		<form action="userActivationCodeLogList" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr>
					<td>
						渠道：
					</td>
					<td>
						<input type="text" id="searchPlatform" name="searchPlatform" value="${searchPlatform}"/>
					</td>
					<td>
						生成时间：
					</td>
					<td colspan="3">
						<input type="text" id="createStartTime" name="createStartTime" value="<s:if test="createStartTime != null"><s:text name="format.date_time"><s:param value="createStartTime"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){createEndTime.focus();}})"/>
						<s:text name="timeTo"></s:text>
						<input type="text" id="createEndTime" name="createEndTime" value="<s:if test="createEndTime != null"><s:text name="format.date_time"><s:param value="createEndTime"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'createStartTime\')}'})"/>
					</td>
					</tr>
					<tr>
					<td>
						激活码
					</td>
					<td>
						<input type="text" id="searchCode" name="searchCode" value="${searchCode}" />
					</td>
					<td>
						<s:text name="pageSize"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="pageSize" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
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
						激活码列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					序号
				</td>
				<td>
					激活码
				</td>
				<td>
					渠道
				</td>
				<td>
					生成时间
				</td>
				<td>
					有效时间
				</td>
				<td>
					玩家
				</td>
				<td>
					激活时间
				</td>
				<td>
					奖励
				</td>
			</tr>
			<s:iterator var="log" value="logList" status="sta">
				<tr>
					<td>
						${sta.index+1}
					</td>
					<td>
						${log.activationCode}
					</td>
					<td>
						${log.platform}
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
					<s:if test="#log.name != null">
						<td>
							${log.name}
						</td>
						<td>
							<s:text name="format.date_time">
								<s:param value="#log.activationTime"></s:param>
							</s:text>
						</td>
					</s:if>
					<s:else>
						<td colspan="2">未激活</td>
					</s:else>
					<td>
						<s:set var="rewardList" value="allRewardList[#sta.index]"></s:set>
						<s:iterator var="reward" value="rewardList" status="stat">
							<s:if test="#reward.category == 2">
								<a href="../dataconfig/tTreasureConstantList?id=${reward.targetId}">${treasureIDNameMap[reward.targetId]}</a>*${reward.num}
							</s:if>
							<s:if test="#reward.category == 3">
								<a href="../dataconfig/eEquipmentConstantList?id=${reward.targetId}">${equipmentIdNameMap[reward.targetId]}</a>*${reward.num}
							</s:if>
							<s:if test="#reward.category == 1">
								${reward.num}<s:text name="%{'activationCodeList.rewardInfo.rewardId_1_'+#reward.targetId}"></s:text>
							</s:if>
						</s:iterator>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="10">
					<aldtags:pageTag paraStr="searchPlatform,${searchPlatform},searchCode,${searchCode},pageSize,${pageSize}" datePara1="createStartTime" dateValue1="${createStartTime}" datePara2="createEndTime" dateValue2="${createEndTime}"/>
				</td>
			</tr>
		</table>
	</body>
</html>