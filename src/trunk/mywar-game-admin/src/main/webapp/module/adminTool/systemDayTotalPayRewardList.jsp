<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/jquery/jquery.min.js"></script>
		<script>
			
			var check = function() {
				var inputArr = document.getElementsByTagName("input");
				for (var i = 0; i < inputArr.length; i++) {
					if (inputArr[i].value == "") {
						alert("请填写完整信息");
						return false;
					}
				}
				if (confirm("你确定要修改吗?"))
				{
					return true;
				} else {
					return false;
				}
			}
			
			var config = function(activityId, id) {
			
				window.location.href="systemDayTotalPayRewardList?activityId = " + activityId + "&id=" + id;
			}
			
			var addReward = function(activityId) {
				window.location.href="addSystemDayTotalPayReward?activityId = " + activityId;
			}
			
		</script>
	</head>
	<body>
		<input type="button" value="添加" onclick="addReward(${activityId});"/>
		<form method="post" action="updateSystemDayTotalPayReward?isCommit=T" onsubmit='return check();'>
			<table id="updateSystemDayTotalPayRewardTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						单日累积充值
					</center>
				</td>
			</tr>
			<tr>
				<td>
					充值金额
					<input type="hidden" value="${activityId}" name="activityId" />
				</td>
				<td>
					奖励
				</td>
				
				<td>
					修改
				</td>
				
			</tr>
			<s:iterator var="config" value="list">
				<tr>
					<td>
						${config.payMoney}
					</td>
					<td>
						${config.desc}
					</td>
					
					<td>
						<a href="#" onclick="config(${activityId}, ${config.id})">修改</a>
					</td>
				</tr>
			</s:iterator>
			
		</table>
		
		</form>
	</body>
	<script src="../../js/jquery/jquery.validate.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("form").validate({
				rules: {
					diamondUseArr:{
						required:true
					},
					diamondReceiveArr:{
						required:true
					}
				}
			});
		});
	</script>
</html>