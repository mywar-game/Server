<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="addAdminPkAwardJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">	
	<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
	<script type="text/javascript" >	
	
	</script>
	<body>
		&nbsp;
		<form action="addAdminPkAward?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="addAdminPkAwardJsp.title"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
					<s:text name="adminPkAward.Award_id"></s:text><s:text name="colon"></s:text>
						<input type="hidden" id="id" name="id" value="${adminPkAward.id}"/>
						
					</td>
					<td>
						${adminPkAward.id}
					</td>
				</tr>
				
				
				<tr>
					<td>
						<s:text name="adminPkAward.Award_score"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="text" id="score" name="score" value=""/>
					</td>
				</tr>
				
				<tr>
					<td>
						<s:text name="adminPkAward.Award_tools"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="text" id="tools" name="tools" value=""/>
					</td>
				</tr>
				
				<tr>
					<td>
						<s:text name="adminPkAward.Award_name"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="text" id="name" name="name" value=""/>
					</td>
				</tr>
				
				<tr>
					<td>
						<s:text name="adminPkAward.Award_description"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<textarea size="45" name="description" rows="4" cols="60" value="" /></textarea>
					</td>
				</tr>
				
				<tr>
					<td>
						<s:text name="adminPkAward.Award_imgId"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="text" id="imgId" name="imgId" value=""/>
					</td>
				</tr>
				
				<tr>
					<td>
						<s:text name="adminPkAward.Award_day_buy_num"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="text" id="day_buy_num" name="day_buy_num" value=""/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminPkAward.Award_total_buy_num"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="text" id="total_buy_num" name="total_buy_num" value=""/>
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
				content:{
					required:true,
					maxlength:1000
				}
			}		
		});	
	});
</script>
