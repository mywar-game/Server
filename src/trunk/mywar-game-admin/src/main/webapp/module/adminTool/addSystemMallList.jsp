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
			var check = function() {
				var inn = document.getElementsByTagName("input");
				for (var i = 0; i <= inn.length; i ++) {
					if (inn[i].value=="") {
						alert("请填写完整信息!");
						return false;
					}
				}
				return true;
			}
		</script>
	</head>
	<body>
	<form action="addSystemMallList?isUpdate=T" onsubmit="return check();">
		<table id="mailTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="25" align="center">
					<center>
						<s:text name="systemMallListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<s:iterator var="mall" value="systemMallList">
			<tr>
				<td size="10">
					<s:text name="systemMallListJsp.tag"></s:text>
				</td>
				<td>
					<input type="hidden" value="${mall.mallId}" name="mallIdArr" />
					<input type="text" value="${mall.tag}" name="tagArr" size="80"/>
				</td>
			</tr>
			<tr>
				<td size="10">
					<s:text name="systemMallListJsp.name"></s:text>
				</td>
				<td>
					<input type="text" value="${mall.name}" name="nameArr" size="80"/>
				</td>
			</tr>
			<tr>
				<td size="10">
					<s:text name="systemMallListJsp.description"></s:text>
				</td>
				<td>
					<input type="text" value="${mall.description}" name="descriptionArr" size="80"/>
				</td>
			</tr>
			<tr>
				<td size="10">
					<s:text name="systemMallListJsp.toolType"></s:text>
				</td>
				<td>
					<input type="text" value="${mall.toolType}" name="toolTypeArr" size="80"/>
				</td>
			</tr>
			<tr>
				<td size="10">
					<s:text name="systemMallListJsp.toolId"></s:text>
				</td>
				<td>
					<input type="text" value="${mall.toolId}" name="toolIdArr" size="80"/>
				</td>
			</tr>
			<tr>
				<td size="10">
					<s:text name="systemMallListJsp.toolNum"></s:text>
				</td>
				<td>
					<input type="text" value="${mall.toolNum}" name="toolNumArr" size="80"/>
				</td>
			</tr>
			<tr>
				<td size="10">
					<s:text name="systemMallListJsp.moneyType"></s:text>
				</td>
				<td>
					<input type="text" value="${mall.moneyType}" name="moneyTypeArr" size="80"/>
				</td>
			</tr>
			<tr>
				<td size="10">
					<s:text name="systemMallListJsp.amount"></s:text>
				</td>
				<td>
					<input type="text" value="${mall.amount}" name="amountArr" size="80"/>
				</td>
			</tr>
			<tr>
				<td size="10">
					<s:text name="systemMallListJsp.imgId"></s:text>
				</td>
				<td>
					<input type="text" value="${mall.imgId}" name="imgIdArr" size="80"/>
				</td>
			</tr>
			<tr>
				<td size="10">
					<s:text name="systemMallListJsp.dailyMaxNum"></s:text>
				</td>
				<td>
					<input type="text" value="${mall.dailyMaxNum}" name="dailyMaxNumArr" size="80"/>
				</td>
			</tr>
			<tr>
				<td size="10">
					<s:text name="systemMallListJsp.maxNum"></s:text>
				</td>	
				<td>
					<input type="text" value="${mall.maxNum}" name="maxNumArr" size="80"/>
				</td>		
			</tr>
			<tr>
				<td size="10">
					<s:text name="systemMallListJsp.needVipLevel"></s:text>
				</td>
				<td>
					<input type="text" value="${mall.needVipLevel}" name="needVipLevelArr" size="80"/>
				</td>
			</tr>
			<tr>
				<td size="10">
					<s:text name="systemMallListJsp.seeVipLevel"></s:text>
				</td>
				<td>
					<input type="text" value="${mall.seeVipLevel}" name="seeVipLevelArr" size="80"/>
				</td>
			</tr>
			</s:iterator>
		</table>
		<input type="submit" value="提交修改" />
	</form>
	</body>
	
</html>


