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
		
			// 去掉字符串的空格
			function trim(str) {
				str = str.replace(/^(\s|\u00A0)+/, '');
				for (var i = str.length - 1; i >= 0; i--) {
					if (/\S/.test(str.charAt(i))) {
						str = str.substring(0, i + 1);
						break;
					}
				}
				return str;
			}

			// 添加获取道具
			function addTool() {
			
				var hight = (screen.height - 300) / 2.8;
				var width = (screen.width - 500) / 2;
				var str = window.showModalDialog("addTool", "", "dialogLeft=" + width
					+ "; dialogTop=" + hight
					+ "; dialogWidth=500px; dialogHeight=300px; location=no");

				if (str == null || str == '') {
					return;
				}

				// 获取到的道具
				var strArr = str.split('*');
				var toolType = document.getElementById("toolType");
				var toolId = document.getElementById("toolId");

				toolType.value = strArr[0].split(",")[0];
				toolId.value = strArr[0].split(",")[1];
			}
		
		</script>
		<script>
			var success = '${isCommit}';
			if (success == 'true') {
				alert("配置成功！");
				window.location.href="activityList";
			}
			var sureSubmit = function(obj) {
				var inputArr = document.getElementsByTagName("input");
				var mark = "ok";
				for (var i = 0; i < inputArr.length; i++) {
					var tempValue = inputArr[i].value;
					if (tempValue == "") {
						alert("请填完整信息!");
						mark = "no";
						break;
					}
				}
				if (mark == "no") {
					return false;
				}
				if(window.confirm("<s:text name='updateConfirm'></s:text>")) {
					return true;
				} else {
					return false;
				}
			}
		
			// 清空获取道具
			function cTool() {		
				var toolType = document.getElementById("toolType");
				var toolId = document.getElementById("toolId");

				toolType.value = "";
				toolId.value = "";
			}
			var isCommit = '${isCommit}';
			if (isCommit == "T") {
				alert("修改成功!");
				//window.location.href="updateSystemTotalConsumeReward?activityId=" + 32;
			}
		
		</script>
	</head>
	<body>
	
		<form action="updateSystemXianshiMall" method="post" onsubmit="return sureSubmit();">
			
			<table id="updateSystemXianshiMallTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						限时购买配置(${mallId})
					</center>
				</td>
			</tr>
			
			<s:iterator var="con" value="list" >
				<tr>
					
					<td>
						道具类型
					</td>
					<td>
					    <input type="hidden" size="2" value="${con.activityId}"/>
						<input type="hidden" size="2" value="${con.id}" name="id" />
						<input type="text" size="50" value="${con.toolType}" name="toolType" id="toolType" />
					</td>
				</tr>
				<tr>
					<td>
						道具ID
					</td>
					<td>
						<input type="text" size="50" value="${con.toolId}" name="toolId" id="toolId" />
					</td>				
				</tr>
				<tr>
					<td>
						天数
					</td>
					<td>
						<input type="text" size="50" value="${con.type}" name="type" />填入选项为1,2,3 其中1表示1-2天，2表示3-4天，3表示5-7天
					</td>
				</tr>
				<tr>
					<td>
						显示位置
					</td>
					<td>
						<input type="text" size="50" value="${con.subType}" name="subType" />填入选项分1,2,3 其中1表示尊贵宝库，2表示华丽宝库，3表示英雄宝库
					</td>
				</tr>
				<tr>
					<td>
						钻石
					</td>
					<td>
						<input type="text" size="50" value="${con.diamond}" name="diamond" />
					</td>
				</tr>
				<tr>
					<td>
						道具数目
					</td>
					<td>
						<input type="text" size="50" value="${con.toolNum}" name="toolNum" />
					</td>
				</tr>
				<tr>
					<td>
						购买数量
					</td>
					<td>
						<input type="text" size="50" value="${con.buyTimes}" name="buyTimes" />
					</td>
				</tr>
				</tr>
					<td>
						<a href="#" onclick='addTool()'><s:text name="addTool"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='cTool()'><s:text name="clearTool"></s:text></a>
					</td>
				</tr>
			</s:iterator>
			</table>
			<table>
				<tr>
					<td colspan="5" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
	
</html>