<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="drawLevel.update"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	
	<script src="../../js/jquery/jquery.min.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.core.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.position.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.autocomplete.js"></script>
	<script type="text/javascript">
		
		function addTool() {
			var hight = (screen.height - 300) / 2.8;
			var width = (screen.width - 500) / 2;
			var str = window.showModalDialog("addTool", "", "dialogLeft=" + width
				+ "; dialogTop=" + hight
				+ "; dialogWidth=500px; dialogHeight=300px; location=no");

			if (str == null || str == '') {
				return;
			}
			
			var strArr = str.split('*');
			var toolArr = strArr[0].split(',');
			var nameArr = strArr[1].split(',');
			
			if (toolArr.length < 3) {
				return;
			}			
			document.getElementById("toolType").value = toolArr[0];
			document.getElementById("toolId").value = toolArr[1];
			document.getElementById("toolNum").value = toolArr[2];
			
			if (nameArr.length < 3) {
				return;
			}
			document.getElementById("toolTypeName").value = nameArr[0];
			document.getElementById("toolName").value = nameArr[1];
		}
		
		
	</script>
	<body>
		&nbsp;
		<form action="updateDrawLevelGoods?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="drawLevel.update"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="id" name="id" value="${activityDrawLevelGoods.id}"/>
						<input type="hidden" id="activityId" name="activityId" value="${activityDrawLevelGoods.activityId}"/>
						<s:text name="drawLevel.level"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="level" value="${activityDrawLevelGoods.level}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="drawLevel.replacePos"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="replacePos" value="${activityDrawLevelGoods.replacePos}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="drawLevel.toolType"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="hidden" id="toolType" name="toolType" value="${activityDrawLevelGoods.toolType}"/>
						<input type="text" id="toolTypeName" readonly="readonly" name="toolTypeName" value="${activityDrawLevelGoods.toolTypeString}"/>
						<a href="#" onclick='addTool()'><s:text name="updateTool"></s:text></a>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="drawLevel.toolId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="hidden" id="toolId" name="toolId" value="${activityDrawLevelGoods.toolId}"/>
						<input type="text" size="40" readonly="readonly" id="toolName" name="toolName" value="${activityDrawLevelGoods.toolString}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="drawLevel.toolNum"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text"  id="toolNum" name="toolNum" value="${activityDrawLevelGoods.toolNum}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="drawLevel.refreshLowerNum"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="refreshLowerNum" name="refreshLowerNum" value="${activityDrawLevelGoods.refreshLowerNum}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="drawLevel.refreshUpperNum"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
					<input type="text" id="refreshUpperNum" name="refreshUpperNum" value="${activityDrawLevelGoods.refreshUpperNum}"/>
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
<script src="../../js/jquery/jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("form").validate({
			rules:{
				level:{
					required:true,
					digits:true
				},
				replacePos:{
					required:true,
					digits:true
				},
				toolType:{
					required:true,
					digits:true
				},
				toolId:{
					required:true,
					digits:true
				},
				toolNum:{
					required:true,
					digits:true
				},
				refreshLowerNum:{
					required:true,
					digits:true
				},
				refreshUpperNum:{
					required:true,
					digits:true
				}
			}		
		});	
	});
</script>