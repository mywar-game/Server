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
			
			function del(id) {
				if (window.confirm("确定删除这条记录吗?")) {
					var ajaxobj = new Ajax();
					ajaxobj.url = "delDropTool?giftbagDropToolId=" + id;
					alert(ajaxobj.url);
					ajaxobj.callback = function() {
						var tr = document.getElementById(id);
						var table = document.getElementById("updateDropToolTable");
						table.deleteRow(tr.rowIndex);
					}
					ajaxobj.send();
				}
			}
		</script>
	</head>
	<body>
		<form action="updateDropTool?isCommit=true" method="post">
			
			<table id="updateDropToolTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						在线奖励配置
					</center>
				</td>
			</tr>
			<tr>
				<td>
					系统ID
				</td>
				<td>
					礼包ID
				</td>
				
				<td>
					道具类型
				</td>
				
				<td>
					道具ID
				</td>
				<td>
					名字
				</td>
				<td>
					道具数量
				</td>
				<td>
					道具数量下限
				</td>
				<td>
					道具数量上限
				</td>
				
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
			</tr>
			<s:iterator var="tool" value="giftbagDropToolList" >
				<tr id="${tool.giftbagDropToolId}" >
					<td>
						<input size="5" type="text" value="${tool.giftbagDropToolId}" name="giftbagDropToolIdArr" />
					</td>
					<td>
						<input size="5" type="text" value="${tool.giftbagId}" name="giftbagIdArr" />
					</td>
					<td>
						<input size="5" type="text" value="${tool.toolType}" name="toolTypeArr" />
					</td>
					<td>
						<input size="5" type="text" value="${tool.toolId}" name="toolIdArr" />
					</td>
					<td>
						<input size="15" type="text" value="${tool.name}" name="nameArr" />
					</td>
					<td>
						<input size="5" type="text" value="${tool.toolNum}" name="toolNumArr" />
					</td>
					<td>
						<input size="5" type="text" value="${tool.lowerNum}" name="lowerNumArr" />
					</td>
					<td>
						<input size="5" type="text" value="${tool.upperNum}" name="upperNumArr" />
					</td>
					
					<td width="35">
						<a href="#" onclick='del("${tool.giftbagDropToolId}")'><s:text name="delete"></s:text></a>
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
		
		说明：<br>
		<s:generator separator="," val="%{getText('updateDropToolJsp.note_value')}">
			<s:iterator var="str">
				<s:text name="%{'updateDropToolJsp.note'+#str}"></s:text><br>
			</s:iterator>
		</s:generator>
	</body>
	
</html>