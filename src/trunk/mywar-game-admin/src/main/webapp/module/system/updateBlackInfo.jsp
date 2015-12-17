<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateBlackInfoJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script src="../../js/jquery/jquery.min.js"></script>
	
	<body>
		&nbsp;
		<form name="f" action="updateBlackInfo?isCommit=T" method="post" >
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="updateBlackInfoJsp.title"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="blackInfo.value"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="hidden" name="gameWebId" value="${gameWebId}" />
						<input type="hidden" name="id" value="${blackInfo.id}" />
						<input type="text" name="value" value="${blackInfo.value}" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="blackInfo.valueType"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="valueType" class="select">
							<s:generator separator="," val="%{getText('blackInfo.valueType_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="blackInfo.valueType == #str">selected=selected</s:if>>
										<s:text name="%{'blackInfo.valueType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>		
				</tr>				
				<tr>
					<td>
						<s:text name="blackInfo.blackType"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="blackType" class="select">
							<s:generator separator="," val="%{getText('blackInfo.blackType_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="blackInfo.blackType == #str">selected=selected</s:if>>
										<s:text name="%{'blackInfo.blackType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>					
					</td>		
				</tr>
				<tr>
					<td colspan="5" align="center">
						<input type="submit"  value="<s:text name='submit'></s:text>" class="button" />
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
				value:{
					required:true,
					maxlength: 50
				},
				blackType:{
					required:true
				},
				valueType:{
					required:true
				}
			}		
		});	
	});
</script>