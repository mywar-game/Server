<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="addBDummyJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<body>
		&nbsp;
		<form action="addBDummy?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<s:text name="addBDummyJsp.title"></s:text> &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dummy.dummyId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="hidden" name="dummyId" value="${dummy.dummyId}"/>
						${dummy.dummyId}
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dummy.dummyName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="dummyName" value="${dummy.dummyName}" class="maxLife" size="60" maxlength="64" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dummy.dummyDuration"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="dummyDuration" value="${dummy.dummyDuration}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dummy.buyPrice"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="buyPrice" value="${dummy.buyPrice}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dummy.factor1"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="factor1" value="${dummy.factor1}" class="maxLife" size="10" maxlength="11"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dummy.factor2"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="factor2" value="${dummy.factor2}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dummy.moneyFactor1"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="moneyFactor1" value="${dummy.moneyFactor1}" class="maxLife" size="10" maxlength="11"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dummy.moneyFactor2"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="moneyFactor2" value="${dummy.moneyFactor2}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
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
	<script src="../../js/jquery/jquery.min.js"></script>
	<script src="../../js/jquery/jquery.validate.js"></script>
	<script type="text/javascript" >
		$(document).ready(function(){
			$("form").validate({
				rules:{
					dummyName:{
						required: true,
						maxlength:4
					},
					dummyDuration:{
						required: true,
						digits:true
					},
					buyPrice:{
						required: true,
						digits:true
					},
					factor1:{
						required: true,
						number:true
					},
					factor2:{
						required: true,
						digits:true
					},
					moneyFactor1:{
						required: true,
						number:true
					},
					moneyFactor2:{
						required: true,
						digits:true
					}
				}
			})
		});
	</script>
</html>