<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="totalPayRewardListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<base target="_self">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script src="../../js/common/chooseTool.js"></script>
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		var success = '${isCommit}';
		if (success == 'T') {
			alert("配置成功！");
			window.location.href="activityList";
		}
	
		// 清空获取道具
		function cTool(tableTd, idsColumn, showColumn) {		
			clearTool(tableTd, idsColumn, showColumn, "<s:text name='tool.clearConfirm'></s:text>");
		}
		
		// 添加充值配置
		function addRow() {
			var newRow = document.all.toolTable.insertRow();
			var cssTd = "style='background: #EEF7FD; height:24px; font: normal 12px SimSun; line-height:20px; border:1px solid #fff; color:#135294; padding:2px; text-align:left;'";
			newRow.innerHTML=""
				+ "<td " + cssTd + "><input type='text' size='10' name='payMoneyArr' value='' /></td>" 
				+ "<td " + cssTd + "><input type='text' readonly='readonly' size='30' name='rewardsArr' value='' /></td><td " + cssTd + "></td>"
				+ "<td " + cssTd + "><input type='text' size='5' name='timesLimitArr' value='' /></td>"
				+ "<td " + cssTd + "><input type='text' size='50' name='descriptionArr' value='' /></td>" 
				+ "<td " + cssTd + "><a href='#' onclick='addTool(this, 1, 2)'><s:text name='addTool'></s:text></a></td>"
				+ "<td " + cssTd + "><a href='#' onclick='cTool(this, 1, 2)'><s:text name='clearTool'></s:text></a></td>";
	
		}
				
	</script>
	<body>
		<form name="f" action="addTotalPayReward?isCommit=T" method="post" >
		<input type="button" value="<s:text name='addTotalPayRewardJsp.title'></s:text>" class="button" onclick='addRow()'; />
		<table id="toolTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="totalPayRewardListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<!--
				<td >
					<s:text name="reward.activityId"></s:text>
				</td>
				-->
				
				<td >
					<input type="hidden" name="activityId" value="${activityId}" />
					<s:text name="reward.payMoney"></s:text>
				</td>
				<td>
					<s:text name="reward.rewards"></s:text>(ids)
				</td>
				<td>
					<s:text name="reward.rewards"></s:text>
				</td>
				<td>
					<s:text name="reward.timesLimit"></s:text>
				</td>
				<td>
					<s:text name="reward.description"></s:text>
				</td>
				<td size="40">
					<s:text name="addTool"></s:text>
				</td>
				<td size="40">
					<s:text name="clearTool"></s:text>
				</td>
			</tr>
			<s:iterator var="totalPayReward" value="totalPayList" >
			<tr id="${totalPayReward.id}" >
			<!--
				<td >
					${totalPayReward.activityId}									
				</td>
			-->
				
				<td >
					<input type="text" size="10" name="payMoneyArr" value="${totalPayReward.payMoney}" />								
				</td>
				<td >					
					<input type="text" size="35" name="rewardsArr" readonly="readonly" value="${totalPayReward.rewards}" />
				</td>
				<td >					
					${totalPayReward.showRewards}		
				</td>
				<td >
					<input type="text" size="5" name="timesLimitArr" value="${totalPayReward.timesLimit}" />					
				</td>
				<td >
					<input type="text" size="50" name="descriptionArr" value="${totalPayReward.description}" />
				</td>				
				<td>
					<a href="#" onclick='addTool(this, 1, 2)'><s:text name="addTool"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='cTool(this, 1, 2)'><s:text name="clearTool"></s:text></a>
				</td>
			</tr>			
			</s:iterator>
		</table>
		<table>
			<td colspan="5">
				<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
			</td>
		</table>
		</form>
	</body>
</html>
<script src="../../js/jquery/jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("form").validate({
			rules:{
				payMoneyArr:{
					required:true,
					digits:true
				},
				rewardsArr:{
					required:true
				},
				timesLimitArr:{
					required:true,
					digits:true
				},
				descriptionArr:{
					required:true
				}
			}		
		});	
	});
</script>
