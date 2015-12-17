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
		
			// 清空获取道具
			function clearTool(tableTd, idsColumn, showColumn, clearComfirm) {
				var mark = window.confirm(clearComfirm);
				if (!mark) {
					return;
				}
	
				var obj = tableTd.parentNode.parentNode;
				var childrenArr = obj.children;

				if (childrenArr.length > 4) {
					var children = childrenArr[idsColumn];
					var inputNode = children.children[0];
					inputNode.value = '';

					childrenArr[showColumn].innerHTML = '';
				}
			}

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
			// 添加充值配置
			function addRow() {
				var newRow = document.all.updateVitalityTable.insertRow();
				var cssTd = "style='background: #EEF7FD; height:24px; font: normal 12px SimSun; line-height:20px; border:1px solid #fff; color:#135294; padding:2px; text-align:left;'";
				newRow.innerHTML="<td " + cssTd + "><input type='text' name='activityTaskIdArr' value='' /></td>" 
					+ "<td " + cssTd + "><input type='text' name='targetTypeArr' value='' /></td>"
					+ "<td " + cssTd + "><input type='text' name='targetDescArr' value='' /></td>"
					+ "<td " + cssTd + "><input type='text' name='needFinishTimesArr' value='' /></td>"
					+ "<td " + cssTd + "><input type='text' name='pointArr' value='' /></td>";
					
		}
		// 清空获取道具
		function cTool(tableTd, idsColumn, showColumn) {		
			clearTool(tableTd, idsColumn, showColumn, "<s:text name='tool.clearConfirm'></s:text>");
		}
		</script>
	</head>
	<body>
		<form action="update7LoginReward?isCommit=true" method="post" onsubmit="return sureSubmit(this);">
			
			<table id="update7LoginRewardTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="update7LoginRewardJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="update7LoginRewardJsp.rewardId"></s:text>
				</td>
				<td>
					<s:text name="update7LoginRewardJsp.day"></s:text>
				</td>
				<td>
					<s:text name="update7LoginRewardJsp.toolIds"></s:text>
				</td>
				<td>
					<s:text name="update7LoginRewardJsp.Desc"></s:text>
				</td>
				<td size="40">
					<s:text name="addTool"></s:text>
				</td>
				<td size="40">
					<s:text name="clearTool"></s:text>
				</td>
			</tr>
			<s:iterator var="reward" value="system7LoginRewardList" >
				<tr>
					<td>
						<input type="text" size="3" readonly="readonly" value="${reward.id }" name="idArr" />
					</td>
					<td>
						<input type="text" size="3" readonly="readonly" value="${reward.day }" name="dayArr" />
					</td>
					<td>
						<input type="text" size="50" value="${reward.toolIds }" name="toolIdsArr" />
					</td>
					<td>
						${reward.desc}
					</td>
					<td>
						<a href="#" onclick='addTool(this, 2, 3)'><s:text name="addTool"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='cTool(this, 2, 3)'><s:text name="clearTool"></s:text></a>
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
	<script src="../../js/jquery/jquery.validate.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("form").validate({
				rules: {
					idArr:{
						required:true
					},
					dayArr:{
						required:true
					},
					toolIdsArr:{
						required:true
					}
				}
			});
		});
	</script>
</html>