<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="onlineDetailStatsAoListJsp.title"></s:text></title>
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
		
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script> 
	</head>
	<script type="text/javascript">
		function check1(){
			var serverId = document.getElementById("serverId").value;
			if(serverId == 0){
				alert("请选择服务器");
				return false;
			}
			return true;
		}
	</script>
	<body>
		<div>
			<form action="onlineDetailStatsAoList?isCommit=T" method="post" onsubmit="return check1()">
				<s:text name="searchServer"></s:text><s:text name="colon"></s:text>
				<s:select name="sysNum" id="serverId" list="serverMap" headerKey="0" headerValue="%{getText('pleaseSelect')}"/>
				<s:text name="queryTime"></s:text><s:text name="colon"></s:text>
				<input type="text" id="startDate" name="startDate" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
				<input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
				<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
			</form>
			<form>
				<s:text name="maxOnline"></s:text><s:text name="colon"></s:text>
				<s:text name="maxOnlineAmount"></s:text>
				<s:text name="avgOnline"></s:text><s:text name="colon"></s:text>
				<s:text name="avgOnlineAmount"></s:text>
				<s:text name="maxIp"></s:text><s:text name="colon"></s:text>
				<s:text name="maxIpNum"></s:text>
				<s:text name="avgIp"></s:text><s:text name="colon"></s:text>
				<s:text name="avgIpNum"></s:text>
			</form>
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="22" align="center">
						<center>
							<s:text name="onlineDetailStatsAoListJsp.title"></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="server"></s:text>
					</td>
					<td>
						<s:text name="onlineDetailStats.statsTime"></s:text>
					</td>
					<td>
						<s:text name="onlineDetailStats.onlineAmount"></s:text>
					</td>
					<td>
						<s:text name="onlineDetailStats.ipNum"></s:text>
					</td>
				</tr>
				<s:iterator var="stats" value="statsList">
					<tr>
						<td>
							${serverMap[stats.sysNum]}
						</td>
						<td>
							${stats.statsTime}
						</td>
						<td>
							${stats.onlineAmount}
						</td>
						<td>
							${stats.ipNum}
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="22">
						<aldtags:pageTag paraStr="sysNum,${sysNum},pageSize,${pageSize},isCommit,T" datePara1="startDate" dateValue1="${startDate}"/>
					</td>
				</tr>
			</table>
			<div style=”color:red”> 
    			<s:fielderror /> 
			</div> 
		</div>
	</body>
</html>