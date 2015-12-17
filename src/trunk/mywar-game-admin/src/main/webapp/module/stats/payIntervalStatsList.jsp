<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="payIntervalStatsListJsp.title"></s:text></title>
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
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/stats_WdatePicker.js"></script>  
	</head>
	<script type="text/javascript"> 
		//设置曲线图数据
		function setGraphicData(){
			//alert("setGraphicData()");
			var arg={
				xAxis : {
					categories : getColumnValue(0)//得到所有的统计日期作为纵坐标
				},
				yAxis : {
				//	title: {
				//		text : '数量'
				//	}
				//	,
				//	max : sendAjax("module/log/userCreateRoleLossStatsList"+"findYAxisMaxValue")// 纵坐标的最大值
				},
				series : getSeries([1])//第x列的值作为一条曲线
        	}
			generateGraphic(arg);
		}
	</script>
	<body>
		<form action="payIntervalStatsList?isFlag=1" method="post" onsubmit="return check()">
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" id="sendBeginDate" name="sendBeginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<s:if test="sendBeginDate != null">${sendBeginDate}</s:if>" class="Wdate" style="width:100px"/>
			<s:text name="timeTo"></s:text>
			<input type="text" id="sendEndDate" name="sendEndDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<s:if test="sendEndDate != null">${sendEndDate}</s:if>" class="Wdate" style="width:100px"/>

			<s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="payIntervalStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="payIntervalStatsListJsp.payInterval"></s:text>
				</td>
				<td>
					<s:text name="payIntervalStatsListJsp.population"></s:text>
				</td>
			</tr>
			<s:iterator var="stats" value="statsList" status="sta">
				<tr>
					<td>
						${stats.payInterval}
					</td>
					<td>
						${stats.userNum}
					</td>
				</tr>
			</s:iterator>
			
			<tr>
				<td colspan="22">
					<aldtags:pageTag para1="pageSize" value1="${pageSize}"/>
				</td>
			</tr>
		</table>
	</body>
</html>