<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
		<title><s:text name="getUserInfoJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
		<script src="../../js/jquery/jquery.validate.js"></script>
		<script type="text/javascript">
	 $(document).ready(function(){
		$("form").validate({
			rules:{
			    lodo_id:{
					required:true
				},
			}		
		});	
	});
	 </script>
	<body>
	<form action="searchByLodoId?isCommit=T" method="post">
	<table>
	<tr>
	                 <td class="td_title" colspan="100" align="center" >
						<center>
                                                                                                                                             查询用户渠道信息
						</center>
					</td>
	</tr>
	<tr>
	                <td colspan="6">
	                  <s:text name="log.lodoSearch"></s:text><s:text name="colon"></s:text><input type="text" name="lodo_id" size="10" value="">
					</td>
					<td colspan="50">
					  <input type="submit" value="<s:text name="search"></s:text>" class="button">
					</td>
	</tr>
	<%--<c:if text="${partner_id==null}">
	<tr>输入的lodo_id不正确</tr>
	</c:if>--%>
	<c:if test="${list!=null}">
	<tr>
	<td>lodo_id</td>
	<td>user_mapper_id</td>
	<td>partner_id</td>
	<td>server_id</td>
	<td>user_id</td>
	<td>partner_user_id</td>
	</tr>
	<tr>
		<td>${lodo_id}</td>
		<td>${user_mapper_id}</td>
		<td>${partner_id}</td>
		<td>${server_id}</td>
		<td>${s}</td>
		<td>${partner_user_id}</td>
	</tr>

	</c:if>
	</table>	
	</form>
	</body>
</html>