<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="mallStatsHistoryListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
		
		<script type="text/javascript" src="../../js/ajax.js"></script>
		
		<script src="../../js/jquery/jquery.min.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.core.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.widget.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.position.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.autocomplete.js"></script>
		
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/stats_WdatePicker.js"></script> 
	</head>
	<body>
		<div>
			<form action="mallStatsHistoryList?isCommit=T" method="post">
				<s:text name="searchPartner"></s:text><s:text name="colon"></s:text>
				<s:select name="channel" id="pid" list="partnerList" listKey="pid" listValue="PName" headerKey="0" headerValue="%{getText('pleaseSelect')}"/>
				<s:text name="searchServer"></s:text><s:text name="colon"></s:text>
				<s:select name="sysNum" id="serverId" list="serverMap" headerKey="0" headerValue="%{getText('pleaseSelect')}"/>
				<s:text name="selectOrder"></s:text><s:text name="colon"></s:text>
				<select id="order" name="order" class="select"">
					<s:generator separator="," val="%{getText('mall.order_values')}">
						<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
						<s:iterator var="str">
							<option value="${str}" <s:if test="order == #str">selected=selected</s:if>>
								<s:text name="%{'mall.order_'+#str}"></s:text>
							</option>
						</s:iterator>
					</s:generator>
				</select>
				<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
				<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
				<s:text name="timeTo"></s:text>
				<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
				<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
			</form>
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="22" align="center">
						<center>
							<s:text name="mallStatsHistoryListJsp.title"></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="mall.treasureId"></s:text>
					</td>
					<td>
						<s:text name="mall.treasureName"></s:text>
					</td>
					<td>
						<s:text name="mall.price"></s:text>
					</td>
					<td>
						<s:text name="mall.saleNum"></s:text>
					</td>
					<td>
						<s:text name="mall.costGold"></s:text>
					</td>
					<td>
						<s:text name="mall.buyUserNum"></s:text>
					</td>
				</tr>
				<s:iterator var="stats" value="statsList">
					<tr>
						<td>
							${stats.treasureId}
						</td>
						<td>
							${stats.treasureName}
						</td>
						<td>
							${stats.price}
						</td>
						<td>
							${stats.saleNum}
						</td>
						<td>
							${stats.costGold}
						</td>
						<td>
							${stats.buyUserNum}
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="22">
						<aldtags:pageTag datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}" paraStr="channel,${channel},sysNum,${sysNum},order,${order},isCommit,T"/>
					</td>
				</tr>
			</table>
			<div style=”color:red”> 
    			<s:fielderror /> 
			</div> 
		</div>
	</body>
</html>