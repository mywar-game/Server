<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
		<title><s:text name="totalLoginListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<base target="_self">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script src="../../js/jquery/jquery.validate.js"></script>
		<script src="../../js/common/chooseTool.js"></script>
		<script type="text/javascript" src="../../js/ajax.js"></script>
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
				// window.location.href="activityList";
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
			// 添加配置
			function addRow() {
				var newRow = document.all.updateAccumLoginRewardTable.insertRow();
				var cssTd = "style='background: #EEF7FD; height:24px; font: normal 12px SimSun; line-height:20px; border:1px solid #fff; color:#135294; padding:2px; text-align:left;'";
				newRow.innerHTML = ""
					+ "<td " + cssTd + "><input type='text' size='10' name='day' value='' /></td>" 
					+ "<td " + cssTd + "><input type='text' size='20' name='tools' value='' /></td>"
					+ "<td " + cssTd + "></td>"
					+ "<td " + cssTd + "></td>"
					+ "<td " + cssTd + "><a href='#' onclick='addTool(this, 1, 2)'><s:text name='addTool'></s:text></a></td>"
					+ "<td " + cssTd + "><a href='#' onclick='cTool(this, 1, 2)'><s:text name='clearTool'></s:text></a></td>";
					
		}
		// 清空获取道具
		function cTool(tableTd, idsColumn, showColumn) {		
			clearTool(tableTd, idsColumn, showColumn, "<s:text name='tool.clearConfirm'></s:text>");
		}
		var isCommit = '${isCommit}';
		if (isCommit == "T") {
			alert("修改成功!");
			//window.location.href="updateAccumLoginReward?activityId=" + 32;
		}
		
		function delById(id, activityId) {
			if (window.confirm("你确定要删除该行数据吗?")) {
				window.location.href="updateAccumLoginReward?isCommit=T&isDelete=T&did=" + id + "&activityId=" + activityId;
			} else {
				return false;
			}
		}
		</script>
		<body>
	    <form action="updateAccumLoginReward?isCommit=T" method="post" onsubmit="return sureSubmit();">
	    <input type="button" value="添加累计天数奖励" class="button" onclick='addRow()';/>
	    <table id="updateAccumLoginRewardTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
	    <tr class="border">
	       <td class="td_title" colspan="22" align="center">
	                <center>
						<s:text name="updateSystemTotalLoginRewardJsp.title"></s:text>
					</center>
	    
	       </td>
	    </tr>
	    <tr>
	     
	            <td>
					<input type="hidden" name="activityId" value="${activityId}" />
					<s:text name="updateSystemTotalLoginRewardJsp.day"></s:text>
				</td>
				<td>
					<s:text name="updateSystemTotalLoginRewardJsp.tools"></s:text>
				</td>
				<td>
					描述
				</td>
				<td>
					删除
				</td>
				<td size="40">
					<s:text name="addTool"></s:text>
				</td>
				<td size="40">
					<s:text name="clearTool"></s:text>
				</td>
	    
	    </tr>
	    
	    <s:iterator var="reward" value="systemAccumLoginRewardList" >
				<tr>
					<td>
						<input type="text" size="10" value="${reward.day}" name="day" />
					</td>
					<td>
						<input type="text" size="20" value="${reward.tools}" name="tools" />
					</td>
					<td>
						${reward.toolsDesc}
					</td>
					<td>
						<a href="#" onclick='delById(${reward.id}, ${reward.activityId})'>删除</a>
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
				<tr>
					<td colspan="5" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
					</td>
				</tr>
			</table>
	    </form>
		</body>

	
		
</html>