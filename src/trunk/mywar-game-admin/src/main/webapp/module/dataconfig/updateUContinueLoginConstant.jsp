<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateUContinueLoginConstantJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	
	<script language="javascript" src="../../js/jquery/jquery.min.js"></script>
	<script language="javascript" src="../../js/jquery/ui/jquery.ui.core.js"></script>
	<script language="javascript" src="../../js/jquery/ui/jquery.ui.widget.js"></script>
	<script language="javascript" src="../../js/jquery/ui/jquery.ui.position.js"></script>
	<script language="javascript" src="../../js/jquery/ui/jquery.ui.autocomplete.js"></script>
	
	<script type="text/javascript">
		//初始化时
		$(function() {
			//因为输入框可能动态添加，所以要再次绑定
			$("input[name='rewardName']").live('focus', function() { 
				$("input[name='rewardName']").autocomplete({
					minLength: 0,
					source: function(request, response) {
						var rewardType = $(this.element).parent().parent().find(":selected")[0].value;
						var action = "";
						if(rewardType == 1){
							action = "../dataconfig/tTreasureConstantListfindTreasureIdNameMapByCondition";
						} else {
							action = "../dataconfig/eEquipmentConstantListfindEquipmentIdNameMapByCondition";
						}
						$.ajax({
							url: action,
							data: {name:encodeURI(request.term)},
							success: function(data) {
								response($.map( data, function(name,id) {
									return {
										id: id,
										label: name
									}
								}));
							}
						});
					},
					select: function( event, ui ) {
						//$(this).parent().parent().find("input")[3].value = ui.item.id;
						$(this).parent().parent().find(":input[name='rewardId']")[0].value = ui.item.id;
						
					},
					open: function() {
						$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
					},
					close: function() {
						$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
					}
				});
			});
		});
		
		function changeRewardType(rewardTypeSelect){
			var tr = rewardTypeSelect.parentNode.parentNode;
			var rewardIdTd = tr.cells[rewardTypeSelect.parentNode.cellIndex+2];
			var html = '';
			//如果是经济
			if(rewardTypeSelect.value == 3) {
				html += '<select name="rewardId" class="select">';
								<s:generator separator="," val="%{getText('continueLoginConstant.rewardInfo.rewardId_value')}">
									<s:iterator var="str">
				html += '				<option value="${str}">';
				html += '					<s:text name="%{'continueLoginConstant.rewardInfo.rewardId_3_'+#str}"></s:text>';
				html += '				</option>';
									</s:iterator>
								</s:generator>	
				html += '</select>';
			} else {
				html += '<input name="rewardName" class="maxLife" type="text">';
				html += '<input name="rewardId" type="hidden">';
			}
			rewardIdTd.innerHTML = html; 
			//装备或道具
		}
		
		function addReward(addRewardButton){
			var table = document.getElementById("table");
			var tr = table.insertRow(addRewardButton.parentNode.parentNode.rowIndex);
			var td1 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<s:text name="continueLoginConstant.rewardInfo.rewardType"></s:text><s:text name="colon"></s:text>';
			td1.innerHTML = html;
			var td2 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<select name="rewardType" class="select" onchange="changeRewardType(this)">';
						<s:generator separator="," val="%{getText('continueLoginConstant.rewardInfo.rewardType_value')}">
							<s:iterator var="str" status="sta">
			html += '			<option value="${str}" <s:if test="#sta.index == 0">selected=selected</s:if>>';
			html += '				<s:text name="%{'continueLoginConstant.rewardInfo.rewardType_'+#str}"></s:text>';
			html += '			</option>';
							</s:iterator>
						</s:generator>	
			html += '</select>';
			td2.innerHTML = html;
			var td3 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<s:text name="continueLoginConstant.rewardInfo.rewardId"></s:text><s:text name="colon"></s:text>';
			td3.innerHTML = html;
			var td4 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<input name="rewardName" class="maxLife" type="text">';
			html += '<input name="rewardId" type="hidden">';
			td4.innerHTML = html;
			var td5 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<s:text name="continueLoginConstant.rewardInfo.rewardNum"></s:text><s:text name="colon"></s:text>';
			td5.innerHTML = html;
			var td6 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<input name="rewardNum" value="1" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\\d]/g,\'\')" type="text">';
			td6.innerHTML = html;
			var td7 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<input type="button" value="删除奖励" onclick="delReward(this)" class="button" />';
			td7.innerHTML = html;
			var rewardInfoTd = document.getElementById("rewardInfoTd");
			rewardInfoTd.rowSpan = rewardInfoTd.rowSpan+1;
		}
		
		function delReward(delButton){
			//alert(delButton.value);
			var table = document.getElementById("table");
			var tr = delButton.parentNode.parentNode;
			//alert(tr.innerHTML);
			//alert(tr.cells[0].id);
			//如果是第一行
			if (tr.cells[0].id == "rewardInfoTd") {
				var td = table.rows[tr.rowIndex+1].insertCell(0);
				td.innerHTML = tr.cells[0].innerHTML;
				td.id = tr.cells[0].id;
				td.rowSpan = tr.cells[0].rowSpan;
				table.deleteRow(tr.rowIndex);
			} else {
				table.deleteRow(tr.rowIndex);
			}
			//奖励信息的td的长度减一
			var rewardInfoTd = document.getElementById("rewardInfoTd");
			rewardInfoTd.rowSpan = rewardInfoTd.rowSpan-1;
		}
		
		function rewardInfoToJSON() {
			var rewardInfo = document.getElementById("rewardInfo");
			
			var rewardTypes = document.getElementsByName("rewardType");
			var rewardIds = document.getElementsByName("rewardId");
			var rewardNums = document.getElementsByName("rewardNum");
			var rewardArray = new Array();
			for(var i=0; i<rewardTypes.length; i++){
				var reward = new Object;
				if (rewardIds[i].value == "" || rewardNums[i].value == "") {
					alert("第"+(i+1)+"个奖励的数据未填写！");
					return false;
				}
				reward.rewardType = parseInt(rewardTypes[i].value);
				reward.rewardId = parseInt(rewardIds[i].value);
				reward.rewardNum = parseInt(rewardNums[i].value);
				rewardArray.push(reward);
			}
			var rewardInfo = document.getElementById("rewardInfo");
			rewardInfo.value = JSON.stringify(rewardArray);
			return true;
		}
	
		function isSubmit(){
			if(rewardInfoToJSON() && window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
		}
	</script>
	
	<body>
		&nbsp;
		<form name="form" action="updateUContinueLoginConstant?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center id="table">
				<tr class="border">
					<td class="td_title" colspan="10">
						<s:text name="updateUContinueLoginConstantJsp.title"></s:text> &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="continueLoginConstant.continueDays"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="hidden" name="rewardInfo" id="rewardInfo">
						<input type="hidden" name="id" value="${continueLoginConstant.id}">
						<input type="hidden" name="continueDays" value="${continueLoginConstant.continueDays}" class="maxLife" size="2" maxlength="4" onblur="value=value.replace(/[^\d]/g,'')" />
						<s:if test="continueLoginConstant.continueDays == 0">
							一个月
						</s:if>
						<s:else>
							${continueLoginConstant.continueDays}
						</s:else>
						
					</td>
					<td>
						<s:text name="continueLoginConstant.rewardInfo.rewardType"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="10">
						<input type="hidden" name="type" value="${continueLoginConstant.type}">
						<s:text name="%{'continueLoginConstant.type_'+continueLoginConstant.type}"></s:text>
					</td>
				</tr>
				
				<s:iterator var="reward" value="rewardList" status="sta">
					<tr>
						<s:if test="#sta.index == 0">
							<td id="rewardInfoTd" rowspan="${fn:length(rewardList)}">
								<s:text name="continueLoginConstant.rewardInfo"></s:text><s:text name="colon"></s:text>
							</td>
						</s:if>
						<td>
							<s:text name="continueLoginConstant.rewardInfo.rewardType"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<select name="rewardType" class="select" onchange="changeRewardType(this)">
								<s:generator separator="," val="%{getText('continueLoginConstant.rewardInfo.rewardType_value')}">
									<s:iterator var="str">
										<option value="${str}" <s:if test="#reward.rewardType == #str">selected=selected</s:if>>
											<s:text name="%{'continueLoginConstant.rewardInfo.rewardType_'+#str}"></s:text>
										</option>
									</s:iterator>
								</s:generator>	
							</select>
						</td>
						<td>
							<s:text name="continueLoginConstant.rewardInfo.rewardId"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<s:if test="#reward.rewardType == 1">
								<input type="text" name="rewardName" value="${treasureIDNameMap[reward.rewardId]}" class="maxLife" />
								<input type="hidden" name="rewardId" value="${reward.rewardId}">
							</s:if>
							<s:if test="#reward.rewardType == 2">
								<input type="text" name="rewardName" value="${equipmentIdNameMap[reward.rewardId]}" class="maxLife" />
								<input type="hidden" name="rewardId" value="${reward.rewardId}">
							</s:if>
							<s:if test="#reward.rewardType == 3">
								<select name="rewardId" class="select">
									<s:generator separator="," val="%{getText('continueLoginConstant.rewardInfo.rewardId_value')}">
										<s:iterator var="str">
											<option value="${str}" <s:if test="#reward.rewardId == #str">selected=selected</s:if>>
												<s:text name="%{'continueLoginConstant.rewardInfo.rewardId_'+#reward.rewardType+'_'+#str}"></s:text>
											</option>
										</s:iterator>
									</s:generator>	
								</select>
							</s:if>
							
						</td>
						<td>
							<s:text name="continueLoginConstant.rewardInfo.rewardNum"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<input type="text" name="rewardNum" value="${reward.rewardNum}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
						</td>
						<td>
							<input type="button" value="删除奖励" onclick="delReward(this)" class="button" />
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="10">
						<input type="button" value="添加奖励信息" onclick="addReward(this)" class="button" />
					</td>
				</tr>
				
				<tr>
					<td colspan="10" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>