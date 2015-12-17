<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userSexStatsListJsp.title"></s:text></title>
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
		<script type="text/javascript" src="../../js/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="../../js/jquery/highcharts.js"></script>
		<script type="text/javascript" src="../../js/jquery/exporting.js"></script>
	</head>
	<script type="text/javascript"> 
		$(function () {
			//var arr = [['Firefox',   45.0], ['IE',       26.8],];
			var arr = new Array();
			<s:iterator var="userSexStats" value="statsList" status="sta">
			var tempArr = new Array();
				tempArr[0] = '<s:text name="%{'user.sex_'+#userSexStats.sex}"></s:text>';
				tempArr[1] = ${userSexStats.num};
				arr[${sta.index}] = tempArr;
			</s:iterator>
			
		    var chart;
		    $(document).ready(function() {
		        
		        // Radialize the colors
		        Highcharts.getOptions().colors = $.map(Highcharts.getOptions().colors, function(color) {
		            return {
		                radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
		                stops: [
		                    [0, color],
		                    [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
		                ]
		            };
		        });
		        
		        // Build the chart
		        chart = new Highcharts.Chart({
		            chart: {
		                renderTo: 'container',
		                plotBackgroundColor: null,
		                plotBorderWidth: null,
		                plotShadow: false
		            },
		            title: {
		                text: '<s:text name="userSexStatsListJsp.title"></s:text>'
		            },
		            tooltip: {
		                pointFormat: '{series.name}: <b>{point.percentage}%</b>',
		                percentageDecimals: 1
		            },
		            plotOptions: {
		                pie: {
		                    allowPointSelect: true,
		                    cursor: 'pointer',
		                    dataLabels: {
		                        enabled: true,
		                        color: '#000000',
		                        connectorColor: '#000000',
		                        formatter: function() {
		                            return '<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(1) +'%';
		                        }
		                    }
		                }
		            },
		            series: [{
		                type: 'pie',
		                name: '<s:text name="userSexStatsListJsp.percent"></s:text>',
		                data: arr
		            }]
		        });
		    });
		    
		});
	</script>
	<body>
		<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="userSexStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="user.sex"></s:text>
				</td>
				<td>
					<s:text name="userSexStats.num"></s:text>
				</td>
			</tr>
			<s:iterator var="userSexStats" value="statsList">
				<tr>
					<td>
						<s:text name="%{'user.sex_'+#userSexStats.sex}"></s:text>
					</td>
					<td>
						${userSexStats.num}
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>