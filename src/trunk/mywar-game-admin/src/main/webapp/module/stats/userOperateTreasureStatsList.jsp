<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userTreasureStatsListJsp.title"></s:text></title>
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
		$(function () {
			//var arr = [['Firefox',45.0], ['IE',26.8]];
			
			//var arr = new Array();
			//<s:iterator  value="map.get(3043)" status="sta">
			//var tempArr = new Array();
			//	tempArr[0] = '<s:text name="%{'userTreasureLog.operation_'+key}"></s:text>';
			//	tempArr[1] = ${value};
			//	arr[${sta.index}] = tempArr;
			//</s:iterator>
			
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
		                text: '<s:text name="userTreasureStatsListJsp.title"></s:text>'
		            },
		            tooltip: {
		                pointFormat: '数量:<b>{point.y}</b>  {series.name}: <b>{point.percentage}%</b>',
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
		                name: '所占百分比',
		                data: arr
		            }]
		        });
		    });
		    
		});
		
	</script>
	<body>
		<div>
<!--			<form action="userTreasureStatsList" method="post">-->
<!--				<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>-->
<!--				<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>-->
<!--				<s:text name="timeTo"></s:text>-->
<!--				<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>-->
				
<!--				<s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">-->
<!--				<input type="submit" value="<s:text name="submit"></s:text>" class="button" />-->
<!--			</form>-->
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="100" align="center">
						<center>
							<s:text name="userTreasureStatsListJsp.title"></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="userTreasureStatsListJsp.treasureName"></s:text>
					</td>
					<s:generator separator="," val="%{getText('userTreasureLog.category_values')}">
						<s:iterator var="category">
							<s:generator separator="," val="%{getText('userTreasureLog.operation_'+#category+'_values')}">
								<s:iterator var="str">
									<td>
										<s:text name="%{'userTreasureLog.operation_'+#category+'_'+#str}"></s:text>
									</td>
								</s:iterator>
							</s:generator>	
						</s:iterator>
					</s:generator>	
				</tr>
				
				<s:iterator value="map">
					<tr>
					<td>
						<a href="../dataconfig/tTreasureConstantList?id=${key}">
							${treasureIDNameMap[key]}
						</a>
					</td>
					
					<s:set name="typeMap" value="value"></s:set>
					
					<s:generator separator="," val="%{getText('userTreasureLog.category_values')}">
						<s:iterator var="category">
							<s:generator separator="," val="%{getText('userTreasureLog.operation_'+#category+'_values')}">
								<s:iterator var="str">
									<td>
										<s:if test="#typeMap.get(#str) == null">
											
										</s:if>
										<s:else>
											<s:property value="#typeMap.get(#str)"/>
										</s:else>
									</td>
								</s:iterator>
							</s:generator>	
						</s:iterator>
					</s:generator>	
					
					
					<s:generator separator="," val="%{getText('userTreasureLog.operation_values')}">
							<s:iterator var="str">
								
							</s:iterator>
						</s:generator>	
					</tr>	
				</s:iterator>
				
<!--				<tr>-->
<!--					<td colspan="22">-->
<!--						<aldtags:pageTag para1="order" value1="${order}" para2="type" value2="${type}" para3="pageSize" value3="${pageSize}"/>-->
<!--					</td>-->
<!--				</tr>-->
			</table>
		</div>
	</body>
</html>