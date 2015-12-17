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
			var modifyStar = function(starId) {
				
			}
			var isUpdate = '${isUpdate}';
			if (isUpdate == 'T') {
				alert("更新成功!");
				window.location.href="systemMailInternalList";
			}
			
			var check = function() {
				if (confirm("你确认要提交修改吗?")) {
					return true;
				} else {
					return false;
				}
			}
		</script>
	</head>
	<body>	
	<form action="updateSystemMailInternal" method="post" onsubmit="return check();">
		<table id="starTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="systemMailInternalListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<s:iterator var="mail" value="systemMailInternalList" >
				<tr class="border">
					<td>
						<s:text name="systemMailInternalListJsp.mailId"></s:text>
					</td>
					<td>
						<input type="text" value="${mail.internalMailId}" size="50" readonly="readonly" name="internalMailId"/>
					</td>
				</tr>
				<tr>
					<td >
						<s:text name="systemMailInternalListJsp.mailTitle"></s:text>
					</td>
					<td>
						<input type="text" value="${mail.mailTitle}" size="50" name="mailTitle"/>							
					</td>
				</tr>
				<tr>
					<td >
						<s:text name="systemMailInternalListJsp.mailTitle"></s:text>
					</td>
					<td>
						<input type="text" value="${mail.mailContent}" size="50" name="mailContent"/>							
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="systemMailInternalListJsp.logicType"></s:text>
					</td>
					<td>
						<input type="text" value="${mail.logicType}" size="50" name="logicType"/>							
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="systemMailInternalListJsp.showType"></s:text>
					</td>
					<td>
						<input type="text" value="${mail.showType}" size="50" name="showType"/>							
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="systemMailInternalListJsp.param"></s:text>
					</td>
					<td>
						<input type="text" value="${mail.param}" size="50" name="param"/>							
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="systemMailInternalListJsp.description"></s:text>
					</td>
					<td>
						<input type="text" value="${mail.description}" size="50" name="description"/>							
					</td>
				</tr>
			</s:iterator>
		</table>
		<table cellpadding="0" cellspacing="0" border="0" width="100%" align=center>
			<s:iterator var="mail" value="systemMailInternalList" >
				<tr>
					<td>
						<s:text name="systemMailInternalListJsp.toolsDesc"></s:text>
					</td>
					<td>
						<s:text name="systemMailInternalListJsp.toolsDesc"></s:text>
					</td>
					<td>
						<s:text name="systemStarListJsp.add"></s:text>
					</td>
					<td>
						<s:text name="systemStarListJsp.clear"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" size="50" name="tools" value="${mail.tools}"/>
					</td>
					<td>					
						${mail.toolsDesc}
					</td>
					<td>
						<a href="#" onclick='addStar(this, 0, 1)'><s:text name="systemStarListJsp.add"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='clearStar(this, 0, 1)'><s:text name="systemStarListJsp.clear"></s:text></a>
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
		<script>
			var addStar = function(obj, idsColumn, showColumn) {
				addTool(obj, idsColumn, showColumn);
			}
			
			var clearStar = function(obj, idsColumn, showColumn) {
				clearTool(obj, idsColumn, showColumn, "<s:text name='tool.clearConfirm'></s:text>");
			}
		</script>
	</body>	
	<script src="../../js/jquery/jquery.validate.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("form").validate({
				rules:{
					rewards:{
						required:true,
					},	
				}		
			});	
		});
		
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
	// 清空获取道具
	function clearTool(tableTd, idsColumn, showColumn, clearComfirm) {
		var mark = window.confirm(clearComfirm);
		if (!mark) {
			return;
		}
	
		var obj = tableTd.parentNode.parentNode;
		var childrenArr = obj.children;

		if (childrenArr.length > 0) {
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
</script>
</html>


