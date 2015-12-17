<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title><s:text name="addSParamConfigJsp.title"></s:text></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
		<script language="javascript" src="../../js/jquery/jquery.min.js"></script>
	</head>

	<body>
		<form action="addSParamConfig?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="3">
						<s:text name="addSParamConfigJsp.title"></s:text> &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="paramConfig.configkey"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="configkey" name="configkey" value="${sparamConfig.configkey}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="paramConfig.value1"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="value1" value="${sparamConfig.value1}" class="maxLife"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="paramConfig.value2"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="value2" value="${sparamConfig.value2}" class="maxLife" size="64" maxlength="64" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="paramConfig.value3"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="value3" value="${sparamConfig.value3}" class="maxLife" size="64" maxlength="64" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="paramConfig.description"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="description" value="${sparamConfig.description}" class="maxLife" size="128" maxlength="128" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
	<script src="../../js/jquery/jquery.validate.js"></script>
	<script type="text/javascript" >
		$(document).ready(function(){
			$("form").validate({
				rules:{
					configkey:{
						required: true,
						maxlength:32
					},
					value1:{
						required: true,
						maxlength:2048
					}
				}
			})
		});
	</script>
</html>
