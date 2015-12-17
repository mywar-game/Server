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
				var inn = document.getElementsByTagName("input");
				for (var i = 0; i <= inn.length; i ++) {
					if (inn[i].value=="") {
						alert("请填写完整信息!");
						return false;
					}
				}
				return true;
			}
			
			// 添加充值配置
			function addRow() {
				var newRow = document.all.mallTable.insertRow();
				var cssTd = "style='background: #EEF7FD; height:24px; font: normal 12px SimSun; line-height:20px; border:1px solid #fff; color:#135294; padding:2px; text-align:left;'";
				newRow.innerHTML="<td " + cssTd + "><input type='text' name='vipLevel' value='' /></td>" 
					+ "<td " + cssTd + "><input type='text' name='dailyMaxTimes' value='' /></td>";			
			}
			
			function del(id, mallId) {
				if (window.confirm("确定要删除该记录吗?")) {
					window.location.href="delSystemMallVipTimesRuleList?id=" + id + "&mallId = " + mallId;
				} else {
					return false;
				}
			}
			
			var isDel = "${isDel}";
			if (isDel) {
				window.location.href="updateSystemMallVipTimesRuleList?mallId = " + ${mallId};
			}
		</script>
	</head>
	<body>
	<input type="button" value="添加" onclick="addRow()" />
	<form action="updateSystemMallVipTimesRuleList?isUpdateRule=T" onsubmit="return check();" method="post">
		<table id="mallTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="25" align="center">
					<center>
						商品vip对应的每日可购买次数(商品ID=${mallId})
						<input type="hidden" value="${mallId}" name="mallId" />
					</center>
				</td>
			</tr>
			<tr>
				<td size="10">
					vip等级
				</td>
				<td size="10">
					每日可购买次数
				</td>
				<td>
					删除
				</td>
			</tr>
			
			<s:iterator var="mall" value="list">
				<tr>
					
					<td>
						<input type="text" value="${mall.vipLevel}" name="vipLevel" />
					</td>
					<td>
						<input type="text" value="${mall.dailyMaxTimes}" name="dailyMaxTimes" />
					</td>
					<td>
						<input type="button" value="删除" onclick="del(${mall.id}, ${mall.mallId})" />
					</td>
				</tr>
			</s:iterator>
		</table>
		<input type="submit" value="提交修改" />
	</form>
	</body>
</html>


