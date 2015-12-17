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
		</script>
	</head>
	<body>
		<table id="updateSystemConfigDataTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="updateSystemConfigDataJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="updateSystemConfigDataJsp.configDataId"></s:text>
				</td>
				<td>
					<s:text name="updateSystemConfigDataJsp.configKey"></s:text>
				</td>
				<td>
					<s:text name="updateSystemConfigDataJsp.configValue"></s:text>
				</td>
				<td>
					<s:text name="updateSystemConfigDataJsp.desc"></s:text>
				</td>
				<td>
					<s:text name="updateSystemConfigDataJsp.modify"></s:text>
				</td>
			</tr>
			<s:iterator var="configData1" value="systemConfigDataList" >
				<tr>
					<td>
						${configData1.configDataId}
					</td>
					<td>
						${configData1.configKey}
					</td>			
					<td>
						${configData1.configValue}
					</td>
					<td>
						${configData1.description}
					</td>
					<td>
						<a href="#" onclick='modify("${configData1.configDataId}")'><s:text name="updateSystemConfigDataJsp.modify"></s:text></a>
					</td>
				</tr>
			</s:iterator>
		</table>
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