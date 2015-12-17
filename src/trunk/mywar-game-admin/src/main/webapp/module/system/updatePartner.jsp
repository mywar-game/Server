<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@page import="java.util.*" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updatePartnerJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script src="../../js/jquery/jquery.min.js"></script>
	<script type="text/javascript">
		function isSubmit(){
			if(window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
		}
	</script>
 <body>
		&nbsp;

		<form action="updatePartner?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="updatePartnerJsp.title"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="partner.pid"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="hidden" id="createTime" name="createTime" value="${partner.createTime}" />
						<input type="hidden" id="removeStatus" name="removeStatus" value="${partner.removeStatus}" />
						<input type="hidden" id="pid" name="pid" value="${partner.pid}" />
						${partner.pid}
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="partner.PNum"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="PNum" value="${partner.PNum}" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="partner.PName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="PName" value="${partner.PName}" />
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
				pid:{
					required: true,
					maxlength: 11,
					digits: true
				},
				PNum:{
					required: true,
					maxlength: 255
				},
				PName:{
					required: true,
					maxlength: 255
				}
			}		
		});	
	});
</script>