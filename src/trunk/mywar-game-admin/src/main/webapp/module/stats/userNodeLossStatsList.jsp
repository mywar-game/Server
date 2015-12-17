<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userNodeLossStatsListJsp.title"></s:text></title>
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
	<!-- 
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
						text : '<s:text name="userNodeLossStatsListJsp.population"></s:text>'
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
						return '<b>' + this.series.name + '</b><br/>' + "<s:text name="userNodeLossStatsListJsp.node"></s:text><s:text name="colon"></s:text>" + this.x +"    <s:text name="userNodeLossStatsListJsp.population"></s:text><s:text name="colon"></s:text> " + this.y;
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
				series : getSeries([1])//第x列的值作为一条曲线
			});
		}
	</script>
	 -->
	<body>
		<form action="userNodeLossStatsList" method="post" onsubmit="return check()">
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			<s:text name="timeTo"></s:text>
			<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align="center">
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="userNodeLossStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<s:iterator var="actionConstant" value="actionList">
				<tr>
					<td >
						${actionConstant.actionDesc}
					</td>
					<td >
						<s:if test="statsMap[#actionConstant.actionId]==null">
							0
						</s:if>
						<s:else>
							${statsMap[actionConstant.actionId]}
						</s:else>
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