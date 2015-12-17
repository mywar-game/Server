<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>常量列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
		<script type="text/javascript"> 
			//设置曲线图数据
			function setGraphicData(){
				//alert("setGraphicData()");
				//alert('<s:text name="format.date_ymd"><s:param value="chooseDate"></s:param></s:text>');
				var chooseDateStr = '<s:text name="format.date_ymd"><s:param value="chooseDate"></s:param></s:text>';
				var chooseDateStrArr = chooseDateStr.split("-");
				var year = chooseDateStrArr[0];
				var month = chooseDateStrArr[1]-1;
				var day = chooseDateStrArr[2];
				//alert("year"+year);
				//alert("month"+month);
				//alert("day"+day);
				var time = Date.UTC(year,month,day);
				var data = new Array();
				<s:iterator var="one" value="list" status="sta">
					data[${sta.index}] = ${one.onlineAmount};
				</s:iterator>
				//alert(data);
				
				var chart = new Highcharts.Chart({
					chart: {
		                renderTo: 'container',
		                zoomType: 'x',
		                spacingRight: 20
		            },
		            title: {
		                text: new Date(time).toLocaleDateString()+'的在线玩家数'
		            },
		            subtitle: {
		                text: document.ontouchstart === undefined ?
		                    '点击并选中某一块区域可只查看区域数据' :
		                    'Drag your finger over the plot to zoom in'
		            },
		            xAxis: {
		                type: 'datetime',
		                maxZoom: 60 * 60 *1000, // fourteen days
		                title: {
		                    text: null
		                },
		                dateTimeLabelFormats: {
		                	day: '%m.%d'
		                }
		            },
		            yAxis: {
		                title: {
		                    text: '在线玩家数'
		                },
		                showFirstLabel: true
		            },
		            tooltip: {
		                shared: true,
		                formatter: function() {
							return new Date(this.x).getUTCHours()+":"+new Date(this.x).getUTCMinutes()+"<br/>在线玩家数：" + this.y;
						}
		            },
		            legend: {
		                enabled: false
		            },
		            plotOptions: {
		                area: {
		                    fillColor: {
		                        linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
		                        stops: [
		                            [0, Highcharts.getOptions().colors[0]],
		                            [1, 'rgba(2,0,0,0)']
		                        ]
		                    },
		                    lineWidth: 1,
		                    marker: {
		                        enabled: false,
		                        states: {
		                            hover: {
		                                enabled: true,
		                                radius: 5
		                            }
		                        }
		                    },
		                    shadow: false,
		                    states: {
		                        hover: {
		                            lineWidth: 1
		                        }
		                    },
		                    threshold: null
		                }
		            },
		    
		            series: [{
		                type: 'area',
		                pointInterval: 5 * 60 *1000,
		                pointStart: time,
		                data: data
		            }]
				});
			}
	</script>
		
	</head>
	<body>
		<form action="userOnlineGraph" method="post">
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="chooseDate" value="<s:if test="chooseDate != null"><s:text name="format.date_ymd"><s:param value="chooseDate"></s:param></s:text></s:if>" style="width:100px"/>
			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
		<!-- 
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						常量列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					序号
				</td>
				<td>
					时间点
				</td>
				<td>
					在线人数
				</td>
			</tr>
			<s:iterator var="one" value="list" status="sta">
				<tr id="${one.id}">
					<td>
						${sta.index+1}
					</td>
					<td>
						${one.time}
					</td>
					<td>
						${one.onlineAmount}
					</td>
				</tr>
			</s:iterator>
			<s:property value="list.size()"/>-->
		</table>
	</body>
</html>