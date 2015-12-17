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
		function addTool(tableTd, idsColumn, showColumn) {
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
			var obj = tableTd.parentNode.parentNode; // tr
			var childrenArr = obj.children; // tdArr

			if (childrenArr.length < 4) {
				return;
			}

			// 获取原有的rewards
			var children = childrenArr[idsColumn]; // td
			var inputNode = children.children[0];
			var old = inputNode.value;
			trim(old);

			if (old != "") {
				var v = old + "|" + strArr[0];
				v = trim(v);
				inputNode.value = v;
			} else {
				inputNode.value = trim(strArr[0]);
			}

			children = childrenArr[showColumn];
			old = children.innerHTML;
			if (old != "") {
				children.innerHTML = old + "|" + strArr[1];
			} else {
				children.innerHTML = strArr[1];
			}
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
			function clearTool(tableTd, idsColumn, showColumn, clearComfirm) {
				var mark = window.confirm(clearComfirm);
				if (!mark) {
					return;
				}
	
				var obj = tableTd.parentNode.parentNode;
				var childrenArr = obj.children;

				if (childrenArr.length > 2) {
					var children = childrenArr[idsColumn];
					var inputNode = children.children[0];
					inputNode.value = '';

					childrenArr[showColumn].innerHTML = '';
				}
			}
			
			// 清空获取道具
			function cTool(tableTd, idsColumn, showColumn) {		
				clearTool(tableTd, idsColumn, showColumn, "<s:text name='tool.clearConfirm'></s:text>");
			}
			
			var isCommit = '${isCommit}';
			if (isCommit == "T") {
				alert("修改成功!");
			}
		
		</script>
	</head>
	<body>
	
		<form action="updateSystemDayTotalPayReward" method="post" onsubmit="return sureSubmit();">
			
			<table id="addSystemDayTotalPayRewardTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						当日充值累积配置(activityId = ${activityId})
					</center>
				</td>
			</tr>
			<tr>
				<td>
					充值金额	
					<input type="hidden" value="${activityId}" name="activityId" />
				</td>
				<td>
					<input type="text" name="payMoney" />
				</td>
				<td>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>
					奖励描述
				</td>
					
				<td>
					奖励
				</td>
				<td>
					<s:text name="addTool"></s:text>
				</td>
				<td>
					<s:text name="clearTool"></s:text>
				</td>
			</tr>
			<tr>
				<td>
				</td>
				<td>
					<input type="text" name="rewards" />
				</td>
				<td>
					<a href="#" onclick='addTool(this, 1, 0)'><s:text name="addTool"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='cTool(this, 1, 0)'><s:text name="clearTool"></s:text></a>
				</td>
			</tr>
					
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