<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userNodeStatsListJsp.title"></s:text></title>
		<style type="text/css">
		td {
			text-align: center;
		}
		</style>		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script> 
		<script type="text/javascript">
			function dateMust(){
				var startDate = document.getElementById("startDate").value;
				var endDate = document.getElementById("endDate").value;
				//alert("startDate "+startDate);
				//alert("endDate "+endDate);
				if(startDate =="" || endDate == ""){
					alert("请选择查询时间段");
					return false;
				}
				return true;
			}
		</script>
		 
	</head>
	<body>
		<form action="userNodeStatsList?isCommit=T" method="post" onsubmit="return dateMust()">
			<s:text name="userNodeStats.type"></s:text><s:text name="colon"></s:text>
			<select name="actionType" class="select">
			<s:generator separator="," val="%{getText('userNodeStats.type_value')}">
				<s:iterator var="str">
					<option value="${str}" <s:if test="actionType == #str">selected=selected</s:if>>
						<s:text name="%{'userNodeStats.type_'+#str}"></s:text>
					</option> 
				</s:iterator>
			</s:generator>
			</select>
			
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" id="startDate" name="startDate" value="<s:if test="startDate != null"><s:text name="format.date_time"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){endDate.focus();}})"/>
			<s:text name="timeTo"></s:text>
			<input type="text" id="endDate" name="endDate" value="<s:if test="endDate != null"><s:text name="format.date_time"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}'})"/>
			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
			
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align="center">
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="userNodeStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td width="100">
					<s:text name="userNodeStats.actionId"></s:text>
				</td>
				<td>
					<s:text name="userNodeStats.action"></s:text>
				</td>
				<td>
					<s:text name="userNodeStats.userNum"></s:text>
				</td>
				<td>
					<s:text name="userNodeStats.times"></s:text>
				</td>
			</tr>			
			<s:iterator var="node" value="nodeStatsList">
				<tr>
					<td >
						${node.actionId}
					</td>
					<td >						
						${node.actionDesc}
					</td>
					<td >
						${node.userNum}
					</td>
					<td >
						${node.times}
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="22">
					<aldtags:pageTag para1="startDate" value1="${startDate}" para2="endDate" value2="${endDate}" para3="pageSize" value3="${pageSize}"/>
				</td>
			</tr>
		</table>
	</body>
</html>