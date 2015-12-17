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
		<script src="../../js/common/chooseTool.js"></script>
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script>
			var success = '${isCommit}';
			if (success == 'T') {
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
				newRow.innerHTML="<td " + cssTd + "><input type='text' name='activityTaskRewardIdArr' value='' /></td>" 
					+ "<td " + cssTd + "><input type='text' name='pointArr' value='' /></td>"
					+ "<td " + cssTd + "><input type='text' name='rewardsArr' value='' /></td>"
					+ "<td " + cssTd + "></td>"
					+ "<td " + cssTd + "><a href='#' onclick='addTool(this, 2, 3)'><s:text name='addTool'></s:text></a></td>"
					+ "<td " + cssTd + "><a href='#' onclick='cTool(this, 2, 3)'><s:text name='clearTool'></s:text></a></td>";
					
			}
			function del(id) {
				if (window.confirm("确定删除这条记录吗?")) {
					var ajaxobj = new Ajax();
					ajaxobj.url = "delVatality?activityTaskRewardId=" + id;
					ajaxobj.callback = function() {
						var tr = document.getElementById(id);
						var table = document.getElementById("updateVitalityTable");
						table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
				}
			}
		</script>
	</head>
	<body>
		<form action="systemLoginDrawList?isCommit=T" method="post" onsubmit="return true;">
			
			<table id="systemLoginDrawListTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr>
				<%-- <td colspan="22">
					<input type="button" value="<s:text name='updateVitalityTableJsp.addTitle'></s:text>" class="button" onclick='addRow()'; />
				</td> --%>
			</tr>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="systemLoginDrawListTable.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="systemLoginDrawListTable.pos"></s:text>
				</td>
				
				<td>
					<s:text name="systemLoginDrawListTable.rewards"></s:text>
				</td>
				
				<td>
					<s:text name="systemLoginDrawListTable.drawRation"></s:text>
				</td>
				<td>
					<s:text name="systemLoginDrawListTable.activityId"></s:text>
				</td>
				<td>
					<s:text name="systemLoginDrawListTable.posX"></s:text>
				</td>
				<td>
					<s:text name="systemLoginDrawListTable.posY"></s:text>
				</td>
				<td>
					<s:text name="systemLoginDrawListTable.ImgId"></s:text>
				</td>
			</tr>
			<s:iterator var="list" value="systemLoginDrawList" >
				<tr>
					<td>
						<input type="text" size="2" value="${list.pos}" name="posArr" readonly="readonly" />
					</td>
					<td>
						<input type="text" size="10" value="${list.rewards}" name="rewardsArr" />
					</td>
					<td>
						<input type="text" size="90" value="${list.drawRation}" name="drawRationArr" />
					</td>
					<td>
						<input type="text" size="2" value="${list.activityId}" name="activityIdArr" readonly="readonly" />
					</td>
					<td>
						<input type="text" size="10" value="${list.posXer}" name="posXerArr" />
					</td>
					<td>
						<input type="text" size="10" value="${list.poxYer}" name="poxYerArr" />
					</td>
					<td>
						<input type="text" size="2" value="${list.imgId}" name="imgIdArr" />
					</td>
					<%-- <td width="35">
						<a href="#" onclick='del("${task.activityTaskRewardId}")'><s:text name="delete"></s:text></a>
					</td> --%>
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
		$(document).ready(function(){
			$("form").validate({
				rules:{
					activityTaskRewardIdArr:{
						required:true,
					},
					rewardsArr:{
						required:true,
					},
					pointArr:{
						required:true,
					},
				}
			});
		});
		// 清空获取道具
		function cTool(tableTd, idsColumn, showColumn) {		
			clearTool(tableTd, idsColumn, showColumn, "<s:text name='tool.clearConfirm'></s:text>");
		}
	</script>
</html>