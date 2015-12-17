/**
 * 绘制曲线图
 * 
 */

function generateGraphic(arg) {
	//alert("generateGraphic()");
	//alert("arg "+arg);
	//alert("arg.title.text "+arg.title.text);
	//alert("arg.xAxis.categories "+arg.xAxis.categories);
	//alert("arg.yAxis.max "+arg.yAxis.max);
	//alert("arg.series "+arg.series);
	//alert("arg.yAxis "+arg.yAxis);
	//alert(arg.yAxis==undefined?"a1":(arg.yAxis.title==undefined?"a2":arg.yAxis.title.text));
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
			categories : arg.xAxis.categories
		},
		yAxis: {
			title: {
				text : arg.yAxis==undefined?'<s:text name="stats.num"></s:text>':(arg.yAxis.title==undefined?'<s:text name="stats.num"></s:text>':arg.yAxis.title.text)
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
				var s = '<b>' + this.series.name + '</b><br/>' + '<s:text name="stats.date"></s:text><s:text name="colon"></s:text>' + this.x +"    "+(arg.yAxis==undefined?'<s:text name="stats.num"></s:text>':(arg.yAxis.title==undefined?'<s:text name="stats.num"></s:text>':arg.yAxis.title.text))+'<s:text name="colon"></s:text> ' + this.y;
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