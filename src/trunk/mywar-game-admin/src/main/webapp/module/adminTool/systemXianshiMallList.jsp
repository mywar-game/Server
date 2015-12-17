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
			
			var config = function(activityId, mallId) {
			
				window.location.href="systemXianshiMallList?activityId = " + activityId + "&mallId=" + mallId;
			}
			
			var addMall = function() {
				window.location.href="addSystemXianshiMall";
			}
			
		</script>
	</head>
	<body>
		<input type="button" value="添加" onclick="addMall();"/>
		<form method="post" action="updateSystemXianshiMall?isCommit=T" onsubmit='return check();'>
			<table id="updateSystemXianshiMallTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						限时购买
					</center>
				</td>
			</tr>
			<tr>
				<td>
					道具
				</td>
				<td>
					<input type="hidden" id="activityId" name="activityId" value="${activityId}" />
					天数
				</td>
				<td>
					显示位置
				</td>
				<td>
					钻石
				</td>
				<td>
					道具数目
				</td>
				<td>
					购买次数
				</td>
				<td>
					修改
				</td>
				
			</tr>
			<s:iterator var="config" value="list">
				<tr>
					<td>
						${config.desc}
					</td>
					<td>
						${config.dayType2}
					</td>
					<td>
						${config.toolType2}
					</td>
					<td>
						${config.diamond}
					</td>
					<td>
						${config.toolNum}
					</td>
					<td>
						${config.buyTimes}
					</td>
					<td>
						<a href="#" onclick="config(${activityId}, ${config.id})">修改</a>
					</td>
				</tr>
			</s:iterator>
			  <tr>
             <td colspan="100">
				<aldtags:pageTag paraStr="isCommit,T,pageSize,${pageSize}" />
			</td>
			</tr>
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