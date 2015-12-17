<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!--绘图插件-->
	
	<!-- 弹出框插件css -->
<!--	<link href="../../css/jquery.tipswindown/tipswindown.css" rel="stylesheet" type="text/css"/>-->
    <!-- jquery支持源文件 -->
	<script type="text/javascript" src="../../js/jquery/jquery.min.js"></script>
	<!-- 弹出框插件 --> 
<!--	<script type="text/javascript" src="../../js/jquery/tipswindown.js"></script>-->
	<!-- ajax源文件 -->
	<script type="text/javascript" src="../../js/jquery/highcharts.js"></script>
	<!-- 打印，下载图片插件 -->
	<script type="text/javascript" src="../../js/jquery/exporting.js"></script>
	<!-- 绘图插件 --> 
	<script type="text/javascript" src="../../js/common/tools.js"></script>
	<!-- 绘图源文件 -->
	<script type="text/javascript" src="../../js/common/graphics.js"></script>
	<script type="text/javascript">
		function check(){
			var startDate = document.getElementById("startDate").value;
			var endDate = document.getElementById("endDate").value;
			//alert("startDate "+startDate);
			//alert("endDate "+endDate);
			//如果出现一个不为空，一个为空的情况
			if((startDate =="" && endDate != "") || (startDate !="" && endDate == "")){
				alert("<s:text name="stats.oneDateIsEmpty"></s:text>");
				return false;
			}
			return true;
		}
	</script>
</head>
<body onload="setGraphicData()">
<!--<div><input type="button" value="曲线图" class="button" id="btn"></div>-->
<div id="container"></div><br>
</body>
</html>