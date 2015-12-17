<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>注册链接</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
		
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script type="text/javascript" src="../../js/json2.js"></script>
		
		<script src="../../js/jquery/ui/jquery.ui.core.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.widget.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.position.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.autocomplete.js"></script>
		
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
		
		<script>
			//初始化时
			$(function() {
				//因为输入框可能动态添加，所以要再次绑定
				$("input[name='rewardName']").live('focus', function() { 
					$("input[name='rewardName']").autocomplete({
						minLength: 0,
						source: function(request, response) {
							var rewardType = $(this.element).parent().parent().find(":selected")[0].value;
							var action = "";
							if(rewardType == 2){
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
			
			//保留之前的奖励
			$(function() {
				var rewardInfo = '${rewardInfo}';
				if (rewardInfo == '') {
					return;
				}
				var rewardArr = JSON.parse(rewardInfo);

				for ( var i = 0; i < rewardArr.length; i++) {
					var html = ''; 
					html += '<div style="float: left;">';
					html += '<input type="hidden" name="category" value="'+ rewardArr[i].category +'">';
					html += '<input type="hidden" name="targetId" value="'+ rewardArr[i].targetId +'">';
					html += '<input type="hidden" name="targetName" value="'+ rewardArr[i].targetName +'">';
					html += '<input type="hidden" name="num" value="'+ rewardArr[i].num +'">';
					html +=  rewardArr[i].targetName  + '*' +  rewardArr[i].num ;
					html += '<input class="button" type="button" onclick="delReward(this)" value="删">&nbsp;&nbsp;';
					html += '</div>';
					
					document.getElementById("rewardListTd").innerHTML += html;
				}
				
			});
			
			function changeRewardType(rewardTypeSelect){
				var tr = rewardTypeSelect.parentNode.parentNode;
				var rewardIdTd = tr.cells[rewardTypeSelect.parentNode.cellIndex+2];
				var html = '';
				//如果是经济
				if(rewardTypeSelect.value == 1) {
					html += '<select id="rewardId" name="rewardId" class="select">';
									<s:generator separator="," val="%{getText('activationCodeList.rewardInfo.rewardId_value')}">
										<s:iterator var="str">
					html += '				<option value="${str}">';
					html += '					<s:text name="%{'activationCodeList.rewardInfo.rewardId_1_'+#str}"></s:text>';
					html += '				</option>';
										</s:iterator>
									</s:generator>	
					html += '</select>';
				} else {
					html += '<input type="text" name="rewardName" id="rewardName"/>';
					html += '<input type="hidden" name="rewardId" id="rewardId">';
				}
				rewardIdTd.innerHTML = html; 
				//装备或道具
			}
			
			function addReward(){
				var rewardType = document.getElementById("rewardType");
				var rewardId = document.getElementById("rewardId");
				var rewardNum = document.getElementById("rewardNum");
				var rewardName = document.getElementById("rewardName");

				if (rewardName == null) {
					name = rewardId.options[rewardId.selectedIndex].text; 
				} else {
					name = rewardName.value;
				}
				
				var html = ''; 
				html += '<div style="float: left;">';
				html += '<input type="hidden" name="category" value="'+rewardType.value+'">';
				html += '<input type="hidden" name="targetId" value="'+rewardId.value+'">';
				html += '<input type="hidden" name="targetName" value="'+ name +'">';
				html += '<input type="hidden" name="num" value="'+rewardNum.value+'">';
				html += name + '*' + rewardNum.value;
				html += '<input class="button" type="button" onclick="delReward(this)" value="删">&nbsp;&nbsp;';
				html += '</div>';
				
				document.getElementById("rewardListTd").innerHTML += html;
			}
			
			function delReward(delButton){
				delButton.parentNode.parentNode.removeChild(delButton.parentNode);
			}
			
			function rewardInfoToJSON() {
				var rewardInfo = document.getElementById("rewardInfo");
				
				var rewardTypes = $("input[name='category']");;
				var rewardIds = document.getElementsByName("targetId");
				var targetNames = document.getElementsByName("targetName");
				var rewardNums = document.getElementsByName("num");
				
				if (rewardTypes == null || rewardTypes.length == 0) {
					alert("一个奖励都木有！");
					return false;
				}
				
				var rewardArray = new Array();
				for(var i=0; i<rewardTypes.length; i++){
					var reward = new Object;
					if (rewardIds[i].value == "" || rewardNums[i].value == "") {
						alert("第"+(i+1)+"个奖励的数据未填写！");
						return false;
					}
					reward.category = parseInt(rewardTypes[i].value);
					reward.targetId = parseInt(rewardIds[i].value);
					reward.targetName = targetNames[i].value;
					reward.num = parseInt(rewardNums[i].value);
					rewardArray.push(reward);
				}
				var rewardInfo = document.getElementById("rewardInfo");
				rewardInfo.value = JSON.stringify(rewardArray);
				
				return true;
			}

			function changeAllCheck(isCheck){
				//所有的input标签
				var allInputTags = document.getElementsByTagName("INPUT");
				var x = 0;
				for ( var i = 0; i < allInputTags.length; i++) {
					//如果input标签的那么是powerIdArr，那么就选中
					if(allInputTags[i].name == "serverIds") {
						allInputTags[i].checked = isCheck;
						x++;
					}
				}
				//alert(x);
			}
			
			function changeNumLimit(noLimit) {
				//alert(noLimit.checked);
				var numLimitInput = document.getElementById("numLimit");
				//如果有效次数不限制
				if (noLimit.checked) {
					numLimitInput.value = 999999;
				} else {
					numLimitInput.value = '';
				}
			}
		</script>
	</head>
	<body>
		<form action="regLinkList?isCommit=T" method="post" onsubmit="return rewardInfoToJSON()">
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="10" align="center">
						<center>
							注册链接
						</center>
					</td>
				</tr>
				<tr>
					<td>
						选择服务器
						<br/>
						<a href="javascript:;" onclick="changeAllCheck(true)">全选</a>
						<a href="javascript:;" onclick="changeAllCheck(false)">全不选</a>
					</td>
						<s:iterator value="%{@com.system.manager.DataSourceManager@getInstatnce().getGameServerMap()}" status="sta">
	<!--					最后一个要对齐格式-->
							<td <s:if test="#sta.last">colspan='<s:property value="6 - #sta.count%6 + 1"/>'</s:if>>
								<input name="serverIds" class="checkbox" value="${key}" <s:if test="serverIds.indexOf(key+'') != -1">checked="checked"</s:if> type="checkbox">
								${value.serverAlias}
		<!--						一行6个-->
								<s:if test="#sta.count%6 == 0">
									<tr>
								</s:if>
							</td>
						</s:iterator>
				</tr>
				<tr>
					<td>
						有效时间：
					</td>
					<td colspan="6">
						<input type="text" id="effectiveTime" name="effectiveTime" value="<s:if test="effectiveTime != null"><s:text name="format.date_time"><s:param value="effectiveTime"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
					</td>
				</tr>
				<tr>
					<td>
						有效次数：
						<input type="checkbox" name="noLimit" <s:if test="noLimit == true">checked="checked"</s:if> onclick="changeNumLimit(this)" value="true">
						不限
					</td>
					<td colspan="6">
						<input type="text" id="numLimit" name="numLimit" value="${numLimit}">
					</td>
				</tr>
				<tr>
					<td>
						选择奖励：
					</td>
					<td>
						类型<s:text name="colon"></s:text>
						<select id="rewardType" class="select" onchange="changeRewardType(this)">
							<s:generator separator="," val="%{getText('activationCodeList.rewardInfo.rewardType_value')}">
								<s:iterator var="str">
									<option value="${str}">
										<s:text name="%{'activationCodeList.rewardInfo.rewardType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
					<td>
						具体奖励<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="rewardName" id="rewardName"/>
						<input type="hidden" name="rewardId" id="rewardId">
					</td>
					<td>
						奖励数量<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="rewardNum" name="rewardNum" class="maxLife" size="10" maxlength="11"/>
					</td>
					<td>
						<input type="button" value="添加奖励" onclick="addReward()" class="button" />
					</td>
				</tr>
				<tr>
					<td>所有奖励：</td>
					<td colspan="6"  id="rewardListTd">
					</td>
				</tr>
				<tr>
					<td colspan="10" align="center">
						<input type="hidden" name="rewardInfo" id="rewardInfo">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
				</table>
		</form>
		
	</body>
	<script src="../../js/jquery/jquery.validate.js"></script>
	<script type="text/javascript" >
		$(document).ready(function(){
			$("form").validate({
				rules:{
					effectiveTime:{
						required: true
					},
					numLimit:{
						required: true,
						digits:true
					},
					rewardNum:{
						digits:true
					}
				}
			})
		});
	</script>
</html>