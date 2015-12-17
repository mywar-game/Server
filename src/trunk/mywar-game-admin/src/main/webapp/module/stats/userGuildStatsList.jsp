<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userGuildStatsListJsp.title"></s:text></title>
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
			var chart = new Highcharts.Chart({
				chart: {
					renderTo: 'container',
					type: 'line',
					marginRight: 160,
					marginBottom: 50
				},
				title: {
					text : document.getElementsByTagName('title')[0].innerHTML,
					x: -20 //center
				},
				xAxis : {
					title: {
						text : arg.yAxis="<s:text name="userGuildStatsListJsp.guildName"></s:text>"
					},
					categories : arg.xAxis.categories
				},
				yAxis: {
					title: {
						text : arg.yAxis="<s:text name="userGuildStatsListJsp.guildAllUserPayAmount"></s:text>"
					},
					plotLines: [{
						value: 0,
						width: 1,
						color: '#808080'
					}],
				},
				tooltip: {
					formatter: function() {
						var s = '<b>' + this.series.name + '</b><br/>' + "<s:text name="userGuildStatsListJsp.guildName"></s:text><s:text name="colon"></s:text>" + this.x +"  <s:text name="userGuildStatsListJsp.guildAllUserPayAmount"></s:text><s:text name="colon"></s:text>" + this.y;
						return arg.tooltip==undefined?s:arg.tooltip.formatter
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
				series : arg.series
			});
		}
	</script>
	<body>
		<form action="userGuildStatsList" method="post" onsubmit="return check()">
<!--			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>-->
<!--			<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>-->
<!--			<s:text name="timeTo"></s:text>-->
<!--			<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>-->
			
			<s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="userGuildStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="userGuildStatsListJsp.guildName"></s:text>
				</td>
				<td>
					<s:text name="userGuildStatsListJsp.guildAllUserPayAmount"></s:text>
				</td>
				<td>
					<s:text name="userGuildStatsListJsp.guildLevel"></s:text>
				</td>
				<td>
					<s:text name="userGuildStatsListJsp.guildPopulation"></s:text>
				</td>
				<td>
					<s:text name="userGuildStatsListJsp.honor"></s:text>
				</td>
				<td>
					<s:text name="userGuildStatsListJsp.technologyExp"></s:text>
				</td>
				<td>
					<s:text name="userGuildStatsListJsp.technologyMaxResource"></s:text>
				</td>
				<td>
					<s:text name="userGuildStatsListJsp.technolgoyBuildingCd"></s:text>
				</td>
				<td>
					<s:text name="userGuildStatsListJsp.technolgoyHeroDefense"></s:text>
				</td>
				<td>
					<s:text name="userGuildStatsListJsp.technologyHeroLife"></s:text>
				</td>
				<td>
					<s:text name="userGuildStatsListJsp.technologyHeroAttack"></s:text>
				</td>
			</tr>
			<s:iterator var="map" value="statsList">
				<tr>
					<td>
						${map['guild'].guildName}
					</td>
					<td>
						${map['guildAllUserPayAmount']}
					</td>
					<td>
						${map['guild'].guildLevel}
					</td>
					<td>
						${map['guild'].guildPopulation}
					</td>
					<td>
						${map['guild'].honor}
					</td>
					<td>
						${map['guild'].technologyExp}
					</td>
					<td>
						${map['guild'].technologyMaxResource}
					</td>
					<td>
						${map['guild'].technolgoyBuildingCd}
					</td>
					<td>
						${map['guild'].technolgoyHeroDefense}
					</td>
					<td>
						${map['guild'].technologyHeroLife}
					</td>
					<td>
						${map['guild'].technologyHeroAttack}
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="22">
					<aldtags:pageTag para3="pageSize" value3="${pageSize}"/>
				</td>
			</tr>
		</table>
	</body>
</html>