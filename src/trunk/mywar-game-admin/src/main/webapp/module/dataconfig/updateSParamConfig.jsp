<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateSParamConfigJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
		function delReward(delButton){
			var td = delButton.parentNode;
			var tr = td.parentNode;
			tr.deleteCell(td.cellIndex);
			tr.cells[tr.cells.length-1].colSpan = 100-tr.cells.length+1;
		}
		
		function addReward(addButton){
			var td = addButton.parentNode;
			var tr = td.parentNode;
			//原先的最后一个的colspan改为1
			tr.cells[tr.cells.length-1].colSpan = 1;
			var newTd = tr.insertCell(tr.cells.length);
			newTd.colSpan = 100-tr.cells.length+1;
			
			var html = '';
			html += '<s:text name="updateSParamConfigJsp.type"></s:text><s:text name="colon"></s:text>';
			html += '<select name="type" class="select">';
				<s:generator separator="," val="%{getText('battleLog.userPrize.type_value')}">
					<s:iterator var="s" status="sta">
			html += '<option value="${s}" <s:if test="#sta.index == 1">selected=selected</s:if>>';
			html += '<s:text name="%{'battleLog.userPrize.type_'+#s}"></s:text>';
			html += '</option>';
					</s:iterator>
				</s:generator>	
			html += '</select>';
			html += '<s:text name="updateSParamConfigJsp.id"></s:text><s:text name="colon"></s:text>';
			html += '<input name="id" class="maxLife" size="4" maxlength="6" onblur="value=value.replace(/[^\\d]/g,\'\')" type="text">';
			html += '<s:text name="updateSParamConfigJsp.num"></s:text><s:text name="colon"></s:text>';
			html += '<input name="num" class="maxLife" size="4" maxlength="6" onblur="value=value.replace(/[^\\d]/g,\'\')" type="text">';
			html += '<input value="<s:text name="delete"></s:text>" onclick="delReward(this)" class="button" type="button">';
			newTd.innerHTML = html;
		}
		
		function isSubmit(){
			var configkey = document.getElementById("configkey").value;
			if(configkey == "LADDER_BATTLE_REWARD"){
				var value1 = '';
				//alert(configkey);
				var fromRanks = document.getElementsByName("fromRank");
				var toRanks = document.getElementsByName("toRank");
				var types = document.getElementsByName("type");
				var ids = document.getElementsByName("id");
				var nums = document.getElementsByName("num");
				for ( var i = 0; i < fromRanks.length; i++) {
					value1 += fromRanks[i].value+'-'+toRanks[i].value+'_';
					var reward = '';
					//alert(fromRanks[i].value);
					var rowIndex = fromRanks[i].parentNode.parentNode.rowIndex;
					//alert(rowIndex);
					for ( var j = 0; j < types.length; j++) {
						if (types[j].parentNode.parentNode.rowIndex == rowIndex) {
							if (reward.indexOf(":") != -1) {
								reward += ',';
							}
							reward += types[j].value+':'+ids[j].value+':'+nums[j].value;
						}
					}
					value1 += reward;
					if (i < fromRanks.length-1) {
						value1 += ';';
					}
				}
				//alert(value1);
				document.getElementById("value1").value = value1;
			}
			if(window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
		}
	</script>
 <body>
		&nbsp;

		<form action="updateSParamConfig?isCommit=T" method="post" onsubmit="return isSubmit()">

			<table cellpadding="3" cellspacing="1" border="0" width="100%"
				align=center>
				<tr class="border">
					<td class="td_title" colspan="100">
						<center>
							<s:text name="updateSParamConfigJsp.title"></s:text> &nbsp;
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="paramConfig.configkey"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="100">
						<input type="text" id="configkey" name="configkey" value="${sparamConfig.configkey}" class="maxLife" size="32" maxlength="32" readonly/>
						<font color='green'><s:text name="cannotEdit"></s:text></font>
					</td>
				</tr>
				<s:if test="sparamConfig.configkey == 'LADDER_BATTLE_REWARD'">
					<tr>
						<td rowspan="6">
							<s:text name="paramConfig.value1"></s:text><s:text name="colon"></s:text>
							<input type="hidden" id="value1" name="value1" class="maxLife" size="64" maxlength="64" />
						</td>
					</tr>
							<!--						${data.value1}<br/>-->
							<s:generator separator=";" val="sparamConfig.value1">
					
<!--							各个个区间的奖励-->
								<s:iterator var="intervalReward">
<!--									${intervalReward}<br/>-->
									<s:generator separator="_" val="intervalReward">
										<s:iterator var="str" status="sta">
<!--										${str}<br/>-->
<!--										排名区间的范围-->
											<s:if test="#sta.index == 0">
												<s:set name="fromRank" value="#str.split('-')[0]"></s:set>
												<s:set name="toRank" value="#str.split('-')[1]"></s:set>
<!--												${fromRank}  ${toRank}-->
													<tr>
													<td>
														<s:text name="addSParamConfigJsp.rankxtoxReward">
															<s:param>
															<input type="text" name="fromRank" value="${fromRank}" class="maxLife" size="3" maxlength="4" onblur="value=value.replace(/[^\d]/g,'')"/>
															</s:param>
															<s:param>
															<input type="text" name="toRank" value="${toRank}" class="maxLife" size="3" maxlength="4" onblur="value=value.replace(/[^\d]/g,'')"/>
															</s:param>
														</s:text>
														<input type="button" value="<s:text name="updateSParamConfigJsp.add"></s:text>" onclick="addReward(this)" class="button" />
													</td>
											</s:if>
<!--										区间的所有奖励-->
											<s:if test="#sta.index == 1">
												<s:generator separator="," val="str">
													<s:iterator var="reward" status="stat">
<!--														${reward}<br/>-->
														<s:set name="type" value="#reward.split(':')[0]"></s:set>
														<s:set name="id" value="#reward.split(':')[1]"></s:set>
														<s:set name="num" value="#reward.split(':')[2]"></s:set>
														<td <s:if test="#stat.last==true"> colspan="${100-2-stat.index}"</s:if>>
<!--														type-->
															<s:text name="updateSParamConfigJsp.type"></s:text><s:text name="colon"></s:text>
															<select name="type" class="select">
																<s:generator separator="," val="%{getText('battleLog.userPrize.type_value')}">
																	<s:iterator var="s">
																		<option value="${s}" <s:if test="#type == #s">selected=selected</s:if>>
																			<s:text name="%{'battleLog.userPrize.type_'+#s}"></s:text>
																		</option>
																	</s:iterator>
																</s:generator>	
															</select>
<!--														id-->
															<s:text name="updateSParamConfigJsp.id"></s:text><s:text name="colon"></s:text>
																<input type="text" name="id" value="${id}" class="maxLife" size="4" maxlength="6" onblur="value=value.replace(/[^\d]/g,'')"/>
<!--														num-->
															<s:text name="updateSParamConfigJsp.num"></s:text><s:text name="colon"></s:text>
															<input type="text" name="num" value="${num}" class="maxLife" size="4" maxlength="6" onblur="value=value.replace(/[^\d]/g,'')"/>
															<input type="button" value="<s:text name="delete"></s:text>" onclick="delReward(this)" class="button" />
														</td>
													</s:iterator>
												</s:generator>
											</s:if>
										</s:iterator>
									</s:generator>
								</s:iterator>
							</s:generator>
				</s:if>
				<s:else>
					<tr>
						<td>
							<s:text name="paramConfig.value1"></s:text><s:text name="colon"></s:text>
						</td>
						<td colspan="100">
							<input type="text" name="value1" value="${sparamConfig.value1}" class="maxLife" size="150" maxlength="2048" />
						</td>
					</tr>	
				</s:else>
				<tr>
					<td>
						<s:text name="paramConfig.value2"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="100">
						<input type="text" name="value2" value="${sparamConfig.value2}" class="maxLife" size="64" maxlength="64" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="paramConfig.value3"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="100">
						<input type="text" name="value3" value="${sparamConfig.value3}" class="maxLife" size="64" maxlength="64" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="paramConfig.description"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="100">
						<input type="text" name="description" value="${sparamConfig.description}" class="maxLife" size="128" maxlength="128" />
					</td>
				</tr>
				<tr>
					<td colspan="100" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
			<s:if test="sparamConfig.configkey == 'LADDER_BATTLE_REWARD'">
				<s:text name="updateSParamConfigJsp.note"></s:text><s:text name="colon"></s:text><br>
	 			<s:text name="updateSParamConfigJsp.note1"></s:text><br>
			</s:if>
		</form>
	</body>
</html>