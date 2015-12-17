<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userOnlineStatsListJsp.title"></s:text></title>
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
			var type = ${type};
			//alert("type "+type);
			var dateStr = "";
			<s:if test="type == 1">dateStr = '${statsList[0].time}';</s:if>
			<s:if test="type == 2">dateStr = '${statsList[0].date}';</s:if>
			//alert(dateStr);
			if(type == 1){
				
				var year = parseInt(dateStr.substring(0,4));
				var month = parseInt(dateStr.substring(5,7));
				var day = parseInt(dateStr.substring(8,10));
				//alert("year "+year);
				//alert("month "+month);
				//alert("day "+day);
				//alert("hour "+hour);
				var hour = parseInt(dateStr.substring(12,14));
				var chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						type: 'spline',
						//marginRight: 130,
						//marginBottom: 25
					},
					title: {
						text : document.getElementsByTagName('title')[0].innerHTML,
						x: -20 //center
					},
					xAxis: {
						type: 'datetime'
					},
					yAxis: {
						title: {
							text : "数值"
						},
						plotLines: [{
							value: 0,
							width: 1,
							color: '#808080'
						}]
					},
					tooltip: {
						formatter: function() {
								return ''+ Highcharts.dateFormat('%Y-%m-%d, %H:%M', this.x) +'    人数：'+ this.y;
						}
					},
					plotOptions: {
						spline: {
							 marker: {
                        		enabled: false,
                       			states: {
	                           		hover: {
	                                	enabled: true
	                            	}
                        		}		
                    		},
							pointInterval: 3600000, // one hour
							pointStart: Date.UTC(year, month-1, day, hour, 0, 0)
						}
					},
					series: getSeries([1,2])//第x列的值作为一条曲线
				});
			}
			if(type == 2 || type == 3){
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
				series : getSeries([1,2])//第x列的值作为一条曲线
        	}
			generateGraphic(arg);
			}
		}
	</script>
	<body>
		<form action="userOnlineStatsList" method="post" onsubmit="return check()">
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			<s:text name="timeTo"></s:text>
			<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			
			<s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="userOnlineStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>

			<s:if test="type == 1">
				<tr>
					<td>
						<s:text name="userOnlineStats.date"></s:text>
					</td>
					<td>
						<s:text name="userOnlineStats.onlineAmount"></s:text>
					</td>
				</tr>
				<s:iterator var="stats" value="statsList">
					<tr>
						<td>
							<s:text name="format.date_time">
								<s:param value="#stats.time"></s:param>
							</s:text>
						</td>
						<td>
							${stats.onlineAmount}
						</td>
					</tr>
				</s:iterator>
			</s:if>
			<s:if test="type == 2 || type == 3">
				<tr>
					<td>
						<s:text name="userOnlineStats.date"></s:text>
					</td>
					<td>
						<s:text name="userOnlineStats.averageOnlineAmount"></s:text>
					</td>
					<td>
						<s:text name="userOnlineStats.onlinePeak"></s:text>
					</td>
				</tr>
				<s:iterator var="stats" value="statsList" status="sta">
					<tr>
						<td>
							<s:text name="format.date_ymd">
								<s:param value="#stats.date"></s:param>
							</s:text>
						</td>
						
						<td>
							${stats.onlineAmount}
						</td>
						<td>
							${stats.onlinePeak}
						</td>
					</tr>
				</s:iterator>
			</s:if>
			<tr>
				<td colspan="22">
					<aldtags:pageTag datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}" para1="pageSize" value1="${pageSize}"/>
				</td>
			</tr>
		</table>
	</body>
</html>