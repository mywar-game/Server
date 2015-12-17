<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="java.util.*"%>
<%@include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
        <title>订单号比较</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../../js/ajax.js"></script>
		<script src="../../js/jquery/jquery.validate.js"></script>

		<script>
			var searchReceipt = function() {
				var gameWebId = document.getElementById("gameWebId").value;
				if (gameWebId == -1) {
					alert("请选择服务区");
					return false;
				} else { 
					return true;
				}
			}
		</script>

		</head>
<body>
<form name="comparePayment" action="comparePayment?isCommit=T" method="post" onsubmit="return searchReceipt()">
<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
                <tr>
                     <s:select list="gameWebList" name="gameWebId" id="gameWebId" headerKey="-1" headerValue="--请选择服务区--" 
                     listKey="serverId" listValue="serverName" onchange=""/>
                </tr>
	            <tr class="border">
					<td class="td_title" colspan="100" align="center" >
						<center>
							订单号比较（订单号须换行输入）
						</center>
					</td>
				</tr>
	            <tr>
				    <td colspan="8">
						订单号
						<textarea name="lodoId" id="lodoId" rows="8" cols="80"></textarea>
					</td>
					
					<td>
						<input type="submit" value="比较" class="button">
					</td>
				</tr>
<%--
            <tr>查询到的异常订单数目</tr>
            <tr><s:property value ="a"/></tr>--%>
</table>
<table>
	            <tr>
					
						查询到的异常订单号：
					
				</tr>
	           <s:iterator var="list" value="resultList">
					<tr>
							${list}
						
					</tr>
				</s:iterator>
	</table>
</form>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("form").validate({
			rules:{
				lodoId:{
					required:true
				},
			}		
		});	
	});
</script>
</html>