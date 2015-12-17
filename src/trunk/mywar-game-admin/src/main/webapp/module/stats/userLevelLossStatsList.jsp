<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userLevelLossStatsListJsp.title"></s:text></title>
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
						text : '<s:text name="userLevelLossStatsListJsp.population"></s:text>'
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
						return '<b>' + this.series.name + '</b><br/>' + "<s:text name="userLevelLossStatsListJsp.level"></s:text><s:text name="colon"></s:text>" + this.x +"    <s:text name="userLevelLossStatsListJsp.population"></s:text><s:text name="colon"></s:text> " + this.y;
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
		
		function checkSubmit() {
			var fromTime1 = document.getElementById("fromTime1").value;
			var endTime1 = document.getElementById("endTime1").value;
			if (!fromTime1 || !endTime1) {
				alert("请填写完整时间范围");
				return;
			} else {
				window.location.href="userLevelLossStatsList?fromTime1=" + fromTime1 + "&endTime1=" + endTime1;
			}
		}
	</script>
	<body>
<!--		<form action="userActiveStatsList" method="post" onsubmit="return check()">-->
<!--			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>-->
<!--			<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>-->
<!--			<s:text name="timeTo"></s:text>-->
<!--			<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>-->
<!--			-->
<!--			<s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">-->
<!--			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />-->
<!--		</form>-->

		<form action="userLevelLossStatsList" method="post">
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" id="fromTime1" size="30" name="fromTime1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${fromTime1}" class="Wdate" style="width:100px"/>
			<s:text name="timeTo"></s:text>
			<input type="text" id="endTime1" size="30" name="endTime1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${endTime1}" class="Wdate" style="width:100px"/>
			
			<input type="button" onclick="checkSubmit();" value="<s:text name='submit'></s:text>" class="button" />(玩家注册时间筛选)
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="userLevelLossStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="userLevelLossStatsListJsp.level"></s:text>
				</td>
				<td>
					<s:text name="userLevelLossStatsListJsp.population"></s:text>
				</td>
				<td>
					<s:text name="userLevelLossStatsListJsp.wastageRate1"></s:text>
				</td>
				<td>
					<s:text name="userLevelLossStatsListJsp.wastageRate2"></s:text>
				</td>
				<td>
					累计流失率
				</td>
			</tr>
			<s:iterator value="statsMap" status="sta">   
				<tr>
					<td>
		                ${key}
		            </td>
		            <td>
		                ${value}
					</td>
					<s:if test="#sta.index == 0">
						<s:set name="regUserNum" value="value"></s:set>
					</s:if>
	                <s:set name="nextValue" value="statsMap.get(#sta.index+2)"></s:set>
					<td>
<!--						${value}减去${nextValue}再除以${regUserNum}等于-->
						<fmt:formatNumber type="percent" value="${(value-nextValue)/regUserNum}" pattern="#.#%" />
					</td>
					<td>
						<s:if test="#sta.index == 0">
							<s:text name="userLevelLossStatsListJsp.nothing"></s:text>
						</s:if>
						<s:else>
<!--							${value}减去${nextValue}再除以${lastValue}等于-->
							<fmt:formatNumber type="percent" value="${(value-nextValue)/lastValue}" pattern="#.#%" />
						</s:else>
		                <s:set var="lastValue" value="value"></s:set>
					</td>
					<td>
<!--					流失率累加-->
						<fmt:formatNumber type="percent" value="${(regUserNum-nextValue)/regUserNum}" pattern="#.#%" />
					</td>
				</tr>
				 <s:set var="lastValue" value="value"></s:set>
			</s:iterator>
			
			<tr>
				<td colspan="22">
					<aldtags:pageTag para1="startDate" value1="${startDate}" para2="endDate" value2="${endDate}" para3="pageSize" value3="${pageSize}"/>
				</td>
			</tr>
		</table>
		<s:text name="userLevelLossStatsListJsp.note"></s:text><br>
		<s:generator separator="," val="%{getText('userLevelLossStatsListJsp.note_value')}">
			<s:iterator var="str">
				<s:text name="%{'userLevelLossStatsListJsp.note'+#str}"></s:text><br>
			</s:iterator>
		</s:generator>
	</body>
</html>