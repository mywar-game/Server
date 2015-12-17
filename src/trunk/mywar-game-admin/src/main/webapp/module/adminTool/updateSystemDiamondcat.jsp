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
			var modify = function(obj) {
				window.location.href="updateSystemConfigData?isCommit=T&configDataId=" + obj;
			}
			var addCat = function() {		
				var newRow = document.all.updateSystemDiamondcatTable.insertRow();
				var cssTd = "style='background: #EEF7FD; height:24px; font: normal 12px SimSun; line-height:20px; border:1px solid #fff; color:#135294; padding:2px; text-align:left;'";
				newRow.innerHTML="<td " + cssTd + "><input type='text' name='diamondUseArr' value='' /></td>" 
					+ "<td " + cssTd + "><input type='text' name='diamondReceiveArr' value='' /></td>";
			}
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
			var isCommit = "${isCommit}";
			var activityId = "${activityId}";
			if (isCommit == "T") {
				alert("修改成功");
				window.location.href="updateSystemDiamondcat?isCommit=F&activityId=" + activityId;
			}
		</script>
	</head>
	<body>
		<input type="button" value="添加" onclick="addCat();"/>
		<form method="post" action="updateSystemDiamondcat?isCommit=T" onsubmit='return check();'>
			<table id="updateSystemDiamondcatTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						招财猫配置
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<input type="hidden" id="activityId" name="activityId" value="${activityId}" />
					花费钻石数
				</td>
				<td>
					获得概率（大于1）
				</td>
				<%-- <td>
					<s:text name="updateSystemConfigDataJsp.modify"></s:text>
				</td> --%>
			</tr>
			<s:iterator var="configData1" value="list" >
				<tr>
					<td>
						<input type="text" id="diamondUse" name="diamondUseArr" value="${configData1.diamondUse}"/>
					</td>
					<td>
						<input type="text" id="diamondReceive" name="diamondReceiveArr" value="${configData1.diamondReceive}"/>				
					</td>			
					<%-- <td>
						<a href="#" onclick='modify("")'><s:text name="updateSystemConfigDataJsp.modify"></s:text></a>
					</td> --%>
				</tr>
			</s:iterator>
			
		</table>
		<input type="submit" value="提交修改" />
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