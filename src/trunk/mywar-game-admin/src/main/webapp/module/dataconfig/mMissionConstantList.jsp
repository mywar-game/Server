<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <title><s:text name='missionConstantListJsp.title'></s:text></title>
	    
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	    <script type="text/javascript" src="../../js/ajax.js"></script>
	    <script type="text/javascript" src="../../js/jquery/jquery.min.js"></script>
	 	<script type="text/javascript">
			function reflashtaskcache() {
				var ajaxobj = new Ajax();
				ajaxobj.url="reflashConstantCache?cacheType=6&number="+Math.random();;
			    ajaxobj.callback=function(){
			    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
			    	//alert(responseMsg.erroCodeNum);
			    	if (responseMsg.erroCodeNum == 0) {
						alert('<s:text name="action.success"><s:param><s:text name="missionConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
					} else {
						alert('<s:text name="action.fail"><s:param><s:text name="missionConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
					}
			    }
				ajaxobj.send();
			}
			
			function del(missionId){
				if(window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='missionConstant.missionId'/></s:param><s:param>"+missionId+"</s:param></s:text>")){
					var ajaxobj = new Ajax();
			        ajaxobj.url="delMMissionConstant?missionId="+missionId;
			        ajaxobj.callback=function(){
			        	//alert(ajaxobj.gettext());
			        	var tr = document.getElementById(missionId);
						var table = document.getElementById("table");
				        table.deleteRow(tr.rowIndex);  
			        }
				    ajaxobj.send();
				}
				
			}
			
			function update(missionId){
				window.location.href="updateMMissionConstant?missionId="+missionId;
			}
			
			function  add(){
				//alert("add()");
				window.location.href="addMMissionConstant";
			}
		
			function init(){
				var name = "${name}";
				var desc = "${desc}";
				if (name != "" || desc != "") {
					var td = document.getElementById("page");
					//alert(td.innerHTML);
					var childNodes = td.childNodes;
					for(var k = 0; k<childNodes.length; k++){
						if(childNodes[k].nodeType==1 && childNodes[k].nodeName=="A"){
							childNodes[k].href += "&name="+ encodeURI(encodeURI(name))+"&desc="+ encodeURI(encodeURI(desc));
						}
					}
				}				
			}
		</script>
	</head>
	<body onload="init()">
		<input type="submit" value="<s:text name='addMMissionConstantJsp.title'></s:text>" class="button" onclick="add()" />
		<input type="button" value='<s:text name="missionConstantListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashtaskcache()" />
	    <form action="mMissionConstantList" method="post">
	    	<table>
			    <tr>
	    			<td>
		    			<s:text name="missionConstant.missionName"></s:text>：<input type="text" name="name" value="${name}">
		    		</td>
			    	<td>
		    			<s:text name="missionConstant.missionDesc"></s:text>：<input type="text" name="desc" value="${desc}">
		    		</td>
			    	<td>
		    			<input type="submit" value="<s:text name="search"></s:text>" class="button" />
		    		</td>
			    </tr>
		    </table>
	    </form>
	    <table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
	    	<tr class="border">
				<td class="td_title" colspan="100" align="center">
					<center>
						<s:text name='missionConstantListJsp.title'></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td rowspan="2">
					<s:text name="missionConstant.missionId"></s:text>
				</td>
				<td rowspan="2">
					<s:text name="missionConstant.missionName"></s:text>
				</td>
				<td rowspan="2">
					<s:text name="missionConstant.missionDesc"></s:text>
				</td>
				<td rowspan="2">
					<s:text name="missionConstant.type"></s:text>
				</td>
				<td rowspan="2">
					<s:text name="missionConstant.nextMissionIds"></s:text>
				</td>
				<td rowspan="2">
					<s:text name="missionConstant.isTheFirst"></s:text>
				</td>
				<td rowspan="2">
					<s:text name="missionConstant.missionAreaLimit"></s:text>
				</td>
				<td rowspan="2">
					<s:text name="missionConstant.needTaskTreasure"></s:text>
				</td>
				<td rowspan="2">
					<s:text name="missionConstant.pveIds"></s:text>
				</td>
				<td rowspan="2">
					<s:text name="missionConstant.openFunctionId"></s:text>
				</td>
				<td colspan="6" align="center">
					<s:text name="missionConstant.missionCondition"></s:text>
				</td>
				<td colspan="4">
					<s:text name="missionConstant.missionPrize"></s:text>
				</td>

				<td width="35" rowspan="2">
					<s:text name="delete"></s:text>
				</td>
				<td width="35" rowspan="2">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="missionCondition.id"></s:text>
				</td>
				<td>
					<s:text name="missionCondition.conditionDesc"></s:text>
				</td>
				<td>
					<s:text name="missionCondition.operatorNum"></s:text>
				</td>
				<td>
					<s:text name="missionCondition.targetType"></s:text>
				</td>
				<td>
					<s:text name="missionCondition.targetNum"></s:text>
				</td>
				<td>
					<s:text name="missionCondition.num"></s:text>
				</td>
				<td>
					<s:text name="missionPrize.id"></s:text>
				</td>
				<td>
					<s:text name="missionPrize.category"></s:text>
				</td>
				<td>
					<s:text name="missionPrize.targetId"></s:text>
				</td>
				<td>
					<s:text name="missionPrize.type"></s:text>
				</td>
			</tr>

			<s:iterator id="missionConstant" value="mmissionConstantList">
				<s:set var="missionConditionList" value="allMissionConditionMap[#missionConstant.missionId]"></s:set>
				<s:set var="missionPrizeList" value="allMissionPrizeMap[#missionConstant.missionId]"></s:set>
<!--			size是遍历完成条件、奖励是的循环次数	-->
<!--			size是完成条件个数和奖励个数两个中的较大值	-->
<!--			如果既没有奖励也没有任务，则size为0	-->
				<s:if test="#missionConstant.missionCondition == null && #missionConstant.missionPrize == null">
					<s:set var="size" value="0"></s:set>
				</s:if>
				<s:elseif test="#missionConstant.missionCondition != null && #missionConstant.missionPrize == null">
					<s:set var="size" value="#missionConditionList.size()"></s:set>
				</s:elseif>
				<s:elseif test="#missionConstant.missionCondition == null && #missionConstant.missionPrize != null">
					<s:set var="size" value="#missionPrizeList.size()"></s:set>
				</s:elseif>
				<s:else>
					<s:if test="#missionConditionList.size() >= #missionPrizeList.size()">
						<s:set var="size" value="#missionConditionList.size()"></s:set>
					</s:if>
					<s:else>
						<s:set var="size" value="#missionPrizeList.size()"></s:set>
					</s:else>
				</s:else>
				
<!--				size${size}-->
				<tr id="${missionConstant.missionId}">
					<td rowspan="${size}">
						${missionConstant.missionId}
					</td>
					<td rowspan="${size}" style="width: 100px">
						${missionConstant.missionName}
					</td>
					<td rowspan="${size}">
						${missionConstant.missionDesc}
					</td>
					<td rowspan="${size}">
						<s:text name="%{'missionConstant.type_'+#missionConstant.type}"></s:text>
					</td>
					<td rowspan="${size}" style="width: 100px">
						<s:if test="#missionConstant.type == 3">
							<s:text name="dayMissionLevelLimit"><s:param>${missionConstant.nextMissionIds}</s:param></s:text>
						</s:if>
						<s:else>
							<s:generator separator="," val="#missionConstant.nextMissionIds">
								<s:iterator id="nextMissionId">
									<a href="?id=${nextMissionId}">
										<s:property value="missionIdNameMap[#nextMissionId]"/>
									</a>
									<br/>
								</s:iterator>
							</s:generator>
						</s:else>
					</td>
					<td rowspan="${size}">
						<s:text name="%{'missionConstant.isTheFirst_'+#missionConstant.isTheFirst}"></s:text>
					</td>
					<td rowspan="${size}" style="width: 100px">
						<s:if test="#missionConstant.missionAreaLimit == 0"><s:text name="missionAreaNoLimit"></s:text></s:if>
						<s:else>
							<s:generator separator="," val="userLevelIntervalAreaNamesMap[#missionConstant.missionAreaLimit]">
								<s:iterator var="str">
									${str}<br/>
								</s:iterator>
							</s:generator>
						</s:else>
					</td>
					<td rowspan="${size}">
						<s:generator separator="," val="#missionConstant.needTaskTreasure" >
							<s:iterator var="treasureId">
								<a href="tTreasureConstantList?id=${treasureId}">
									<s:property value="treasureIDNameMap[#treasureId]"/>
								</a>
							</s:iterator>
						</s:generator>
					</td>
					<td rowspan="${size}">
						<a href="baPveConstantList?bigId=<s:property value='#missionConstant.pveIds.split("_")[0]' />&smallId=<s:property value='#missionConstant.pveIds.split("_")[1]' />">
							<s:property value="baPveIdNamesMap[#missionConstant.pveIds.replace('_',',')]"/>
						</a>
					</td>
					<td rowspan="${size}">
						${missionConstant.openFunctionId}
					</td>
					
<!--				无条件无奖励	-->
					<s:if test="#size == 0">
						<td colspan="10"></td>
						<td rowspan="${size}">
							<a href="#" onclick="del(${missionConstant.missionId})"><s:text name="delete"></s:text> </a>
						</td>
						<td rowspan="${size}">
							<a href="#" onclick="update(${missionConstant.missionId})"><s:text name="update"></s:text> </a>
						</td>
					</s:if>
					<s:else>
						<s:bean name="org.apache.struts2.util.Counter" id="counter">
							<s:param name="first" value="0" />
							<s:param name="last" value="#size - 1" />
							<s:iterator>
								<s:set var="i" value="current -1"></s:set>
<!--								${i}-->
								<s:set var="condition" value="#missionConditionList[#i]"></s:set>
								<s:set var="prize" value="#missionPrizeList[#i]"></s:set>
								
<!--							第二个换行	-->
								<s:if test="#i > 0"><tr></s:if>
<!--							无条件	-->
								<s:if test="#condition == null">
									<td colspan="6"></td>
								</s:if>
								<s:else>
									<td>
										<s:text name="missionCondition.id_show"><s:param>${condition.id}</s:param></s:text>
									</td>
									<td>
										${condition.conditionDesc}
									</td>
									<td>
										<s:text name="%{'missionCondition.operatorNum_'+#condition.operatorNum}"></s:text>
									</td>
									<td>
										<s:if test="#condition.targetType <= 200">
											<s:text name="%{'missionCondition.targetType_'+#condition.targetType}"></s:text>
										</s:if>
										<s:else>
											<s:text name="missionCondition.targetType_others"></s:text>
										</s:else>
									</td>
									<td>
										<s:if test="#condition.targetType == 1 || #condition.targetType == 4">
											<a href="baPveConstantList?bigId=<s:property value='#missionConstant.pveIds.split("_")[0]' />&smallId=<s:property value='#missionConstant.pveIds.split("_")[1]' />">
												<s:property value="baPveIdNamesMap[#missionConstant.pveIds.replace('_',',')]"/>
											</a>
										</s:if>
										<s:elseif test="#condition.targetType == 2 || #condition.targetType > 200">
											<a href="mMonsterConstantList?id=${condition.targetNum}">
												<s:property value="monsterCategoryAndNameMap[#condition.targetNum%1000]"/>
											</a>
										</s:elseif>
										<s:elseif test="#condition.targetType == 3">
											<a href="tTreasureConstantList?id=${condition.targetNum}">
												<s:property value="treasureIDNameMap[#condition.targetNum]"/>
											</a>
										</s:elseif>
										<s:elseif test="#condition.targetType == 20">
											${buildingIDNameMap[condition.targetNum]}
										</s:elseif>
										<s:elseif test="#condition.targetType == 38">
											${technologyIdAndNameMap[condition.targetNum]}
										</s:elseif>
										<s:else>
											<s:text name="missionConstantListJsp.nothing"></s:text>
<!--										测试代码	-->
<!--											<s:if test="#condition.targetNum != 0">-->
<!--												<font color="red">aaa${condition.targetNum}</font>-->
<!--											</s:if>-->
<!--											<s:else>-->
<!--												${condition.targetNum}-->
<!--											</s:else>-->
										</s:else>
									</td>
									<td>
										${condition.num}
									</td>
								</s:else>
								
<!--							无奖励	-->
								<s:if test="#prize == null">
						     		<td colspan="4"></td>
								</s:if>
								<s:else>
									<td>
										<s:text name="missionPrize.id_show"><s:param>${prize.id}</s:param></s:text>
									</td>
									<td>
										<s:text name="%{'missionPrize.category_'+#prize.category}"></s:text>
									</td>
									<td>
										<s:if test="#prize.category == 1">
											<s:text name="%{'missionPrize.targetId_1_'+#prize.targetId}"></s:text>${prize.num}
										</s:if>
										<s:elseif test="#prize.category == 2">
											<a href="eEquipmentConstantList?id=${prize.targetId}">
												${equipmentIDNameMap[prize.targetId]} x ${prize.num}
											</a>
										</s:elseif>
										<s:elseif test="#prize.category == 3">
											<a href="tTreasureConstantList?id=${prize.targetId}">
												${treasureIDNameMap[prize.targetId]} x ${prize.num}
											</a>
										</s:elseif>
										<s:else>
											${prize.num}
										</s:else>
									</td>
									<td>
										<s:text name="%{'missionPrize.type_'+#prize.type}"></s:text>
									</td>
								</s:else>
								<s:if test="#i == 0">
									<td rowspan="${size}">
										<a href="#" onclick="del(${missionConstant.missionId})"><s:text name="delete"></s:text> </a>
									</td>
									<td rowspan="${size}">
										<a href="#" onclick="update(${missionConstant.missionId})"><s:text name="update"></s:text> </a>
									</td>
								</s:if>
							</s:iterator>
						</s:bean>
					</s:else>
						
			</s:iterator>
			<tr>
				<td colspan="100" id="page">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>
