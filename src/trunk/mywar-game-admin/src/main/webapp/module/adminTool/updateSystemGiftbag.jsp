<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateSystemGiftJsp.title"></s:text></title>
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
	<body>
		&nbsp;
		<form action="updateSystemGiftbag?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="updateSystemGiftJsp.title"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="giftbagId" name="giftbagId" value="${giftbag.giftbagId}"/>
						<input type="hidden" id="gameWebId" name="gameWebId" value="${gameWebId}"/>
						<input type="hidden" id="imgId" name="imgId" value="${giftbag.imgId}"/>
						<input type="hidden" id="codeNum" name="codeNum" value="${giftbag.codeNum}"/>
						<input type="hidden" id="tablePrefix" name="tablePrefix" value="${giftbag.tablePrefix}"/>
						<input type="hidden" id="createdTime" name="createdTime" value="${giftbag.createdTime}"/>
						<input type="hidden" id="updatedTime" name="updatedTime" value="${giftbag.updatedTime}"/>
						<s:text name="giftbag.type"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="hidden" name="type" value="${giftbag.type}"/>${giftbag.type}
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="giftbag.name"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="name" name="name" value="${giftbag.name}"/>
					</td>
				</tr>
				<!--
				<tr>
					<td>
						<s:text name="giftbag.imgId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="imgId" name="imgId" value="${giftbag.imgId}"/>
					</td>
				</tr>
				-->
				<tr>
					<td>
						<s:text name="giftbag.description"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<textarea size="45" rows="8" cols="60" id="description" name="description" value=""/>${giftbag.description}</textarea>
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
				type:{
					required:true,
					digits:true
				},
				subType:{
					required:true,
					digits:true
				},
				name:{
					required:true
				},
				imgId:{
					required:true,
					digits:true
				},
				description:{
					required:true
				}
			}		
		});	
	});
</script>