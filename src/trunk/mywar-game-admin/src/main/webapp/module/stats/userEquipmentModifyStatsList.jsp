<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userEquipmentModifyStatsListJsp.title"></s:text></title>
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
				series : getSeries([1,2,3])//第x列的值作为一条曲线
        	}
			generateGraphic(arg);
		}
		
		function order(x){
			var order = parseInt(document.getElementById("orderSelect").value)+x;
			//alert("order "+order);
			window.location.href = "userEquipmentModifyStatsList?order=" + order;
			
		}
	</script>
	<body>
		<div>
			<form action="userEquipmentModifyStatsList" method="post" onsubmit="return check()">
<!--				<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>-->
<!--				<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>-->
<!--				<s:text name="timeTo"></s:text>-->
<!--				<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>-->
				
				<s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
				
				<s:text name="userEquipmentModifyStatsListJsp.equipmentLevel"></s:text><s:text name="colon"></s:text>
				<input type="text" name="level" value="${level}" onblur="value=value.replace(/[^\d]/g,'')">
				
<!--				<input type="button" value="搜索" class="button" onclick="level()" />	-->
				<s:text name="userEquipmentModifyStatsListJsp.order"></s:text><s:text name="colon"></s:text>
				<select name="order" class="select">
					<s:generator separator="," val="%{getText('userEquipmentModifyStatsListJsp.order_value')}">
						<s:iterator var="str">
							<option value="${str}" <s:if test="order == #str">selected=selected</s:if>>
								<s:text name="%{'userEquipmentModifyStatsListJsp.order_'+#str}"></s:text>
							</option>
						</s:iterator>
					</s:generator>	
				</select>
<!--				<input type="button" value="升序排序" class="button" onclick="order(0)" />		-->
<!--				<input type="button" value="降序排序" class="button" onclick="order(1)" />-->
				<input type="submit" value="<s:text name="submit"></s:text>" class="button" />
			</form>
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="22" align="center">
						<center>
							<s:text name="userEquipmentModifyStatsListJsp.title"></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="userEquipmentModifyStatsListJsp.equipmentName"></s:text>
					</td>
					<td>
						<s:text name="userEquipmentModifyStatsListJsp.equipmentLevel"></s:text>
					</td>
					<td>
						<s:text name="userEquipmentModifyStatsListJsp.strengthenAmount"></s:text>
					</td>
					<td>
						<s:text name="userEquipmentModifyStatsListJsp.insetAmount"></s:text>
					</td>
					<td>
						<s:text name="userEquipmentModifyStatsListJsp.synthesisAmount"></s:text>
					</td>
				</tr>
				<s:iterator var="map" value="statsList">
					<tr>
						<s:iterator value="#map">
							<td>
								${value}
							</td>
						</s:iterator>
					</tr>
				</s:iterator>
				
				<tr>
					<td colspan="22">
						<aldtags:pageTag para1="order" value1="${order}" para2="level" value2="${level}" para3="pageSize" value3="${pageSize}"/>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>