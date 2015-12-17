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
			var sureSubmit = function(obj) {
				var mark = window.confirm("确定修改吗?");
				if (!mark) {
					return false;
				}
			}
			var success = '${isCommit1}';
			if (success == "T") {
				alert("修改成功");
			}
		</script>
	</head>
	<body>
		<form action="modifySystemConfigData?isCommit=T" method="post" onsubmit="return sureSubmit(this);">
			
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
					<input size="50" type="text" readonly="readonly" value="${configData.configDataId}" name="configDataId" />
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="updateSystemConfigDataJsp.configKey"></s:text>
				</td>
				<td>
					<input size="50" type="text" value="${configData.configKey}" name="configKey" />
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="updateSystemConfigDataJsp.configValue"></s:text>
				</td>
				<td>
					<input size="50" type="text" value="${configData.configValue}" name="configValue" />
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="updateSystemConfigDataJsp.isSendClient"></s:text><s:text name="colon"></s:text>
				</td>
				<td colspan="4">
					<select id="isSendClient" name="isSendClient" class="select" >
						<s:generator separator="," val="%{getText('updateSystemConfigDataJsp.isSendClient_value')}">
							<s:iterator var="str">
								<option value="${str}" <s:if test="configData.isSendClient == #str">selected=selected</s:if>>
									<s:text name="%{'updateSystemConfigDataJsp.isSendClient_'+#str}"></s:text>
								</option>
							</s:iterator>
						</s:generator>	
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="updateSystemConfigDataJsp.desc"></s:text>					
				</td>
				<td>
					<input size="50" type="hidden" value="${configData.description}" name="description" />
					${configData.description}
				</td>
			</tr>
			</table>
			<input type="submit" value="提交修改"/>
		</form>
	</body>
	<script src="../../js/jquery/jquery.validate.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("form").validate({
				rules: {
					configDataId:{
						required:true
					},
					configKey:{
						required:true
					},
					configValue:{
						required:true
					}
				}
			});
		});
	</script>
</html>