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
		
			var modifySystemMall = function(mallId) {
				window.location.href="updateSystemMallList?mallId=" + mallId;
			}
			
			var modifySystemMallAmountRule = function(mallId) {
				window.location.href="updateSystemMallAmountRuleList?mallId=" + mallId;
			}
			
			var updateSystemMallVipTimesRuleList = function(mallId) {
				window.location.href="updateSystemMallVipTimesRuleList?mallId=" + mallId;
			}
			
			var reflash = function(className) {
			
			}
			
			var isUpdate = "${isUpdate}";
			if (isUpdate == "T") {
				alert("更新商城配置成功!");
			}
			
			var isUpdateRule = "${isUpdateRule}";
			if (isUpdateRule == "T") {
				alert("更新商品购买次数对应价格配置表成功!");
				window.location.href = "systemMallList";
			}
			
			
		
		</script>
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script>
			function reflashAll1(freshClassName) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "reflashActivityList?freshClassName=" + "SystemMallDaoCacheImpl";
				ajaxobj.callback=function(){
					var responseMsg = eval('(' + ajaxobj.gettext() + ')');
					if (responseMsg.erroCodeNum == 0) {
						alert('<s:text name="action.success"><s:param>刷新商城</s:param></s:text>');
					} else {
						alert('<s:text name="action.fail"><s:param>刷新商城</s:param></s:text>');
					}
				}
				ajaxobj.send();	
			}
		</script>	
	</head>
	<body>	
		<input type="button" value='<s:text name="systemMailInternalListJsp.fresh"></s:text>' class="button" onclick="reflashAll1('')" />
		
		<table id="mailTable" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="25" align="center">
					<center>
						<s:text name="systemMallListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="systemMallListJsp.tag"></s:text>
				</td>
				<td>
					<s:text name="systemMallListJsp.name"></s:text>
				</td>
				<td>
					<s:text name="systemMallListJsp.description"></s:text>
				</td>
				<td>
					<s:text name="systemMallListJsp.toolType"></s:text>
				</td>
				<td>
					<s:text name="systemMallListJsp.toolId"></s:text>
				</td>
				<td>
					<s:text name="systemMallListJsp.toolNum"></s:text>
				</td>
				<td>
					<s:text name="systemMallListJsp.moneyType"></s:text>
				</td>
				<td>
					<s:text name="systemMallListJsp.amount"></s:text>
				</td>
				<td>
					<s:text name="systemMallListJsp.imgId"></s:text>
				</td>
				<td>
					<s:text name="systemMallListJsp.dailyMaxNum"></s:text>
				</td>
				<td>
					<s:text name="systemMallListJsp.maxNum"></s:text>
				</td>			
				<td>
					<s:text name="systemMallListJsp.needVipLevel"></s:text>
				</td>
				<td>
					<s:text name="systemMallListJsp.seeVipLevel"></s:text>
				</td>
				
				<td size="10">
					<s:text name="systemMallListJsp.modifySystemMall"></s:text>
				</td>
				<td size="10">
					<s:text name="systemMallListJsp.modifySystemMallAmountRule"></s:text>
				</td>
				<td size="10">
					<s:text name="systemMallListJsp.modifySystemMallVipTimesRule"></s:text>
				</td>
			</tr>
			<s:iterator var="mall" value="systemMallList" >
			<tr>
				
				<td>
					${mall.tag}
				</td>
				<td>
					${mall.name}							
				</td>
				<td>
					${mall.description}							
				</td>
				<td>					
					${mall.toolType}
				</td>
				<td>
					${mall.toolId}
				</td>
				<td>
					${mall.toolNum}
				</td>				
				<td>
					${mall.moneyType}
				</td>
				<td>
					${mall.amount}
				</td>
				<td>
					${mall.imgId}
				</td>				
				<td>
					${mall.dailyMaxNum}
				</td>
				<td>
					${mall.maxNum}
				</td>
				<td>
					${mall.needVipLevel}
				</td>				
				<td>
					${mall.seeVipLevel}
				</td>
				
				<td size="10">
					<a href="#" onclick='modifySystemMall(${mall.mallId})'><s:text name="systemMallListJsp.modifySystemMall"></s:text></a>
				</td>
				<td size="10">
					<a href="#" onclick='modifySystemMallAmountRule(${mall.mallId})'><s:text name="systemMallListJsp.modifySystemMallAmountRule"></s:text></a>
				</td>
				<td size="10">
					<a href="#" onclick='updateSystemMallVipTimesRuleList(${mall.mallId})'><s:text name="systemMallListJsp.modifySystemMallVipTimesRule"></s:text></a>
				</td>
			</tr>			
			</s:iterator>
		</table>
	</body>
	
</html>


