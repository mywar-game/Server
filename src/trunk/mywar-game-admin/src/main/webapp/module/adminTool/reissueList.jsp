<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" pageEncoding="Utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>Insert title here</title>
	
	<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script src="../../js/jquery/jquery.min.js"></script>
		<script type="text/javascript">
		function send(orderId, index, gameServerId){
			var v = document.getElementById("partnerOrderId-"+index).value;
			$.ajax({
			    url:'reissueAudit?orderId='+orderId + '&partnerOrderId='+v + '&gameServerId='+gameServerId,
			    type:'post',
			    success:function(data){
			    	alert(data);
					document.getElementById("partnerOrderId-"+index).value = null;

			    	location.reload();
			    	
			    } 
			});
			}
		</script>
	
</head>
<body>
	<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
		<tr class="border">
			<td class="td_title" ><s:text name="补发审核列表"></s:text></td>
		</tr>
	</table>
		
	<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
		<tr>
			<td>订单 ID</td>
			<td>用户名</td>
			<td>金额</td>
			<td>状态</td>
			<td>输入合作方订单号</td>			
			<td>操作</td>
		</tr>
		
		<s:iterator var="data" status="st" value="paymentOrderList">
		<tr>
			<input type="hidden" name="orderId" value="${data.orderId}">
			<td>${data.orderId}</td>
			<td>${data.userName }</td>
			<td>${data.amount }</td>
			<td><s:if test="%{#data.status == 0}">未充值</s:if><s:else>已补发</s:else></td>
			<td><input type="text" name="partnerOrderId" id="partnerOrderId-${st.index}"></td>
			<td>
				<input type="button" onclick="send('${data.orderId}', '${st.index}', '${data.gameServerId}')" value="补发">
			</td>
		</tr>
	
		</s:iterator>

	</table>

</body>
</html>