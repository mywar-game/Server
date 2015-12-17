<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="giftbagDropToolListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<base target="_self">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		var success = '${isCommit}';
		if (success == 'T') {
			alert("配置成功！");
		}
	
		function addTool(tableTd) {
			var hight = (screen.height - 300) / 2.8;
			var width = (screen.width - 500) / 2;
			var str = window.showModalDialog("addTool", "", "dialogLeft=" + width
				+ "; dialogTop=" + hight
				+ "; dialogWidth=500px; dialogHeight=300px; location=no");

			if (str == null || str == '') {
				return;
			}
			
			// 获得道具
			var strArr = str.split('*');
			var obj = tableTd.parentNode.parentNode; // tr
			var childrenArr = obj.children; // tdArr
			
			if (childrenArr.length < 6) {
				return;
			}
			
			var idArr = strArr[0].split(',');
			var nameArr = strArr[1].split(',');
			
			var children = childrenArr[0]; // td toodType
			var typeId = children.children[0];
			var typeName = children.children[1];
			
			typeId.value = idArr[0];
			typeName.value = nameArr[0];
			
			var toolId = childrenArr[1];
			var toolName = childrenArr[2];
			var num = childrenArr[3];
			
			toolId.children[0].value = idArr[1];
			toolName.children[0].value = nameArr[1];
			num.children[0].value = idArr[2];
			
		}
	
		// 清空获取道具
		function clearTool(tableTd) {
			var mark = window.confirm("<s:text name='tool.clearConfirm'></s:text>");
			if (!mark) {
				return;
			}
				
			var obj = tableTd.parentNode.parentNode; // tr
			var childrenArr = obj.children; // tdArr
			
			if (childrenArr.length > 4) {
				var type = childrenArr[0];
				var toolId = childrenArr[1];
				var toolName = childrenArr[2];
				var num = childrenArr[3];
				
				type.children[0].value = '';
				type.children[1].value = '';
				toolId.children[0].value = '';
				toolName.children[0].value = '';
				num.children[0].value = '';
			}			
		}
	
		// 添加充值配置
		function addRow() {
			var newRow = document.all.toolTable.insertRow();
			var cssTd = "style='background: #EEF7FD; height:24px; font: normal 12px SimSun; line-height:20px; border:1px solid #fff; color:#135294; padding:2px; text-align:left;'";
			newRow.innerHTML="<td " + cssTd + "><input type='hidden' name='toolTypeArr' value='' /><input type='text'  readonly='readonly' name='toolTypeNameArr' value='' /></td>" 
				+ "<td " + cssTd + "><input type='text' readonly='readonly' name='toolIdArr' value='' /></td>"
				+ "<td " + cssTd + "><input type='text' readonly='readonly' name='nameArr' value='' /></td>"
				+ "<td " + cssTd + "><input type='text' name='toolNumArr' value='' /></td>" 
				+ "<td " + cssTd + "><input type='hidden' name='lowerNumArr' value='1' /><input type='hidden' name='upperNumArr' value='10000' /><a href='#' onclick='addTool(this)'><s:text name='giftbagDropTool.addDropTool'></s:text></a></td>"
				+ "<td " + cssTd + "><a href='#' onclick='clearTool(this)'><s:text name='giftbagDropTool.clearDropTool'></s:text></a></td>";	
		}
		
		
				
	</script>
	<body>
		<form name="f" action="addGiftbagDropTool?isCommit=T" method="post" >
		<input type="button" value="<s:text name='addGiftbagDropTool.title'></s:text>" class="button" onclick='addRow()'; />
		<table id="toolTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="giftbagDropToolListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<input type="hidden" name="gameWebId" value="${gameWebId}" />
					<input type="hidden" name="giftbagId" value="${giftbagId}" />
					<s:text name="giftbagDropTool.toolType"></s:text>
				</td>
				<td>
					<s:text name="giftbagDropTool.toolId"></s:text>
				</td>
				<td>
					<s:text name="giftbagDropTool.name"></s:text>
				</td>
				<td>
					<s:text name="giftbagDropTool.toolNum"></s:text>
				</td>
				<td size="40">
					<s:text name="giftbagDropTool.addDropTool"></s:text>
				</td>
				<td size="40">
					<s:text name="giftbagDropTool.clearDropTool"></s:text>
				</td>
			</tr>
			<s:iterator var="giftbagDropTool" value="dropToolList" >
			<tr id="${giftbagDropTool.giftbagDropToolId}" >
				<td >
					<input type="hidden" name="toolTypeArr" value="${giftbagDropTool.toolType}" />
					<input type="text" name="toolTypeNameArr"  readonly='readonly' value="${giftbagDropTool.toolTypeName}" />								
				</td>
				<td >
					<input type="text" readonly="readonly" name="toolIdArr" value="${giftbagDropTool.toolId}" />								
				</td>
				<td >					
					<input type="text" readonly="readonly" name="nameArr" value="${giftbagDropTool.name}" />
				</td>
				<td >					
					<input type="text" name="toolNumArr" value="${giftbagDropTool.toolNum}" />		
				</td>
				<td>
					<input type="hidden" name="lowerNumArr" value="${giftbagDropTool.lowerNum}" />
					<input type="hidden" name="upperNumArr" value="${giftbagDropTool.upperNum}" />
					<a href="#" onclick='addTool(this)'><s:text name="giftbagDropTool.addDropTool"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='clearTool(this)'><s:text name="giftbagDropTool.clearDropTool"></s:text></a>
				</td>
			</tr>			
			</s:iterator>
		</table>
		<table>
			<td colspan="5">
				<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
			</td>
		</table>
		</form>
	</body>
</html>
<script src="../../js/jquery/jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("form").validate({
			rules:{
				toolTypeNameArr:{
					required:true
				},
				toolIdArr:{
					required:true,
					digits:true
				},
				nameArr:{
					required:true
				},
				toolNumArr:{
					required:true,
					digits:true
				},
				lowerNumArr:{
					required:true,
					digits:true
				},
				upperNumArr:{
					required:true,
					digits:true
				}
			}		
		});	
	});
</script>
