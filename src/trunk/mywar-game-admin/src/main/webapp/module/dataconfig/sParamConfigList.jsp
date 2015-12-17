<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="sParamConfigListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		function reflashconstantcache() {
			var ajaxobj = new Ajax();
			ajaxobj.url="reflashConstantCache?cacheType=4&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="sParamConfigListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="sParamConfigListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		
		function del(configkey) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='paramConfig.configkey'/></s:param><s:param>"+configkey+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delSParamConfig?configkey=" + configkey;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(configkey);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(configkey) {
			window.location.href = "updateSParamConfig?configkey=" + configkey;
		}
	
		function add() {
			window.location.href = "addSParamConfig";
		}
	</script>
	<body>
		<input type="submit" value="<s:text name="addSParamConfigJsp.title"></s:text>" class="button" onclick=add(); />
		<input type="button" value='<s:text name="sParamConfigListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="sParamConfigListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="paramConfig.configkey"></s:text>
				</td>
				<td>
					<s:text name="paramConfig.value1"></s:text>
				</td>
				<td>
					<s:text name="paramConfig.value2"></s:text>
				</td>
				<td>
					<s:text name="paramConfig.value3"></s:text>
				</td>
				<td>
					<s:text name="paramConfig.description"></s:text>
				</td>

				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="data" value="sparamConfigList">
				<tr id="${data.configkey}">
					<td>
						${data.configkey}
					</td>
					<td>
						<s:if test="#data.configkey == 'LADDER_BATTLE_REWARD'">
<!--						${data.value1}<br/>-->
							<s:generator separator=";" val="#data.value1">
<!--							各个个区间的奖励-->
								<s:iterator var="intervalReward">
<!--									${intervalReward}<br/>-->
									<s:generator separator="_" val="intervalReward">
										<s:iterator var="str" status="sta">
<!--										${str}<br/>-->
<!--										排名区间的范围-->
											<s:if test="#sta.index == 0">
												<s:set name="rank1" value="#str.split('-')[0]"></s:set>
												<s:set name="rank2" value="#str.split('-')[1]"></s:set>
<!--												${rank1}  ${rank2}-->
												<s:if test="#rank1 == #rank2">
													<s:text name="addSParamConfigJsp.rankxReward">
														<s:param>${rank1}</s:param>
													</s:text><s:text name="colon"></s:text>
												</s:if>
												<s:else>
													<s:text name="addSParamConfigJsp.rankxtoxReward">
														<s:param>${rank1}</s:param>
														<s:param>${rank2}</s:param>
													</s:text><s:text name="colon"></s:text>
												</s:else>
											</s:if>
<!--										区间的所有奖励-->
											<s:if test="#sta.index == 1">
												<s:generator separator="," val="str">
													<s:iterator var="reward">
<!--														${reward}<br/>-->
														<s:set name="type" value="#reward.split(':')[0]"></s:set>
														<s:set name="id" value="#reward.split(':')[1]"></s:set>
														<s:set name="num" value="#reward.split(':')[2]"></s:set>
														<s:if test="#type == 1">
															<a href="eEquipmentConstantList?id=${id}">
																<s:property value="equipmentIDNameMap[#id]"/>x${num}
															</a>
														</s:if>
														<s:elseif test="#type == 2">
															<a href="tTreasureConstantList?id=${id}">
																<s:property value="treasureIDNameMap[#id]"/>x${num}
															</a>
														</s:elseif>
														<s:elseif test="#type == 3">
															${num}<s:text name="%{'battleLog.userPrize.type_'+#type+'_'+#id}"></s:text>
														</s:elseif>
														<s:else>
															${num}<s:text name="%{'battleLog.userPrize.type_'+#type}"></s:text>
														</s:else>
													</s:iterator>
												</s:generator>
											</s:if>
										</s:iterator>
										<br/>
									</s:generator>
								</s:iterator>
							</s:generator>
						</s:if>
						<s:else>
							${data.value1}
						</s:else>
					</td>
					<td>
						${data.value2}
					</td>
					<td>
						${data.value3}
					</td>
					<td>
						${data.description}
					</td>

					<td>
						<a href="#" onclick='del("${data.configkey}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${data.configkey}")'><s:text name="update"></s:text></a>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="22">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>