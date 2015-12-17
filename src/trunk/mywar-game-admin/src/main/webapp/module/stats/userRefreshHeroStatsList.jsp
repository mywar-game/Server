<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userRefreshHeroStatsListJsp.title"></s:text></title>
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
			var chart = new Highcharts.Chart({
				chart: {
					renderTo: 'container',
					type: 'line',
					marginRight: 130,
					marginBottom: 25
				},
				title: {
					text : document.getElementsByTagName('title')[0].innerHTML,
					x: -20 //center
				},
				xAxis : {
					categories : getColumnValue(0)//得到所有的统计日期作为纵坐标
				},
				yAxis: {
					title: {
						text : '<s:text name="stats.num"></s:text>'
					},
					plotLines: [{
						value: 0,
						width: 1,
						color: '#808080'
					}],
		//			max : arg.yAxis==undefined?null:(arg.yAxis.max==undefined?null:arg.yAxis.max)
					//max : arg.yAxis==undefined?null:arg.yAxis.max
				},
				tooltip: {
					formatter: function() {
						return '<b>' + this.series.name + '</b><br/>' + "<s:text name="userRefreshHeroStats.date"></s:text><s:text name="colon"></s:text>" + this.x +"    <s:text name="stats.num"></s:text><s:text name="colon"></s:text> " + this.y;
					}
				},
				legend: {
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -10,
					y: 100,
					borderWidth: 0
				},
				credits : {
					href : "",
					text : ''
				},
				series : getSeries([1,2,3,4,5,6])//第x列的值作为一条曲线
			});
		}
	</script>
	<body>
		<form action="userRefreshHeroStatsList" method="post" onsubmit="return check()">
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="startDate" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" style="width:100px"/>
			<s:text name="timeTo"></s:text>
			<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="endDate" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" style="width:100px"/>
			
			<s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="userRefreshHeroStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="userRefreshHeroStats.date"></s:text>
				</td>
				<td>
					<s:text name="userRefreshHeroStats.refreshTimes"></s:text>
				</td>
				<td>
					<s:text name="userRefreshHeroStats.recruitHeroNum"></s:text>
				</td>
				<td>
					<s:text name="userRefreshHeroStats.commonQualityNum"></s:text>
				</td>
				<td>
					<s:text name="userRefreshHeroStats.excellentQualityNum"></s:text>
				</td>
				<td>
					<s:text name="userRefreshHeroStats.eliteQualityNum"></s:text>
				</td>
				<td>
					<s:text name="userRefreshHeroStats.superQualityNum"></s:text>
				</td>
			</tr>
			<s:iterator var="stats" value="statsList">
				<tr>
					<td>
						<s:text name="format.date_ymd">
							<s:param value="#stats.date"></s:param>
						</s:text>
					</td>
					<td>
						${stats.refreshTimes}
					</td>
					<td>
						${stats.recruitHeroNum}
					</td>
					<td>
						${stats.commonQualityNum}
					</td>
					<td>
						${stats.excellentQualityNum}
					</td>
					<td>
						${stats.eliteQualityNum}
					</td>
					<td>
						${stats.superQualityNum}
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="22">
					<aldtags:pageTag datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}" para1="pageSize" value1="${pageSize}"/>
				</td>
			</tr>
		</table>
	</body>
</html>