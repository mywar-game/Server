<%@ include file="./module/common/taglib.jsp"%>
<%@ include file="./module/common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="sParamConfigListJsp.title"></s:text></title>
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
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script>  
		<script type="text/javascript" src="../../js/ajax.js"></script> 
	</head>
	<script type="text/javascript"> 
	
	
	// 存放所有小渠道
	var qnArray = new Array();
	
	// 向 qnArray 中添加所有小渠道
	<s:iterator id="QN" value="partnerQnList" status="sta">
		var partnerQn = new Object();
		
		partnerQn.partnerId = "${QN.partnerId}";
		partnerQn.name = "${QN.name}";
		partnerQn.qn = "${QN.qn}";
		
		qnArray.push(partnerQn);
	</s:iterator>
	
		//设置曲线图数据
		function commit(value){
			if(value==1){
				document.getElementById("isGrap").value = "F";
			}else{
				document.getElementById("isGrap").value = "T";
			}
			document.form1.submit();
		}
		window.onload=function hid(){
			var isGrap = document.getElementById("isGrap").value;
			if(isGrap=="T"){
				var table = document.getElementById("table");
				table.style.display ="none";
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
					series : getSeries([4,5,10])//第x列的值作为一条曲线
			       }
				generateGraphic(arg);
			}
			
			var channel = document.getElementById("pid").value;
			if (channel != 0) {
				// 
				searchQn();
				
			}
		}
		function downloadExcel() {
				var ajaxobj = new Ajax();
				var channel = document.getElementById("pid").value;
				var sysNum = document.getElementById("serverId").value;
				var startDate = document.getElementById("startDate").value;
				var endDate = document.getElementById("endDate").value;
				var qn = document.getElementById("qnSelect").value;
				//ajaxobj.url = "allServerInfoListgenerateExcel?channel="+ channel +"&sysNum="+sysNum + "&startDate=" + startDate + "&endDate=" + endDate;
				ajaxobj.url = "allServerInfoList?isExportFlag=1&qn="+ qn +"&channel="+ channel +"&sysNum="+sysNum + "&startDate=" + startDate + "&endDate=" + endDate;
				ajaxobj.callback = function() {
					window.location.href = "../system/action/downloadingZip?fileName=serverInfo.xls";
				}
				ajaxobj.send();
		}
			
			
	function searchQn() {
	    
		var qnSelect = document.getElementById("qnSelect");
		var pid = document.getElementById("pid").value;

		for (i = qnSelect.options.length-1; i >= 1; i--) {
			qnSelect.options[i] = null;
	      }
	      
		
		var tempArray = new Array();
		
	    	for (var i = 0; i < qnArray.length; i++) {
			if (qnArray[i].partnerId == pid) {
				var qn = new Object();
				qn.partnerId = qnArray[i].partnerId;
				qn.name = qnArray[i].name;
				qn.qn = qnArray[i].qn;
				tempArray.push(qn);
				
			}
		}
		
	    var  qn ='<%=request.getAttribute("qn")%>';
		for(var i=0; i<tempArray.length; i++){
			qnSelect.options[i+1] = new Option(tempArray[i].name, tempArray[i].qn);
			
			if (qn == tempArray[i].qn) {
				qnSelect.options[i + 1].selected = true;	
			}
		}
		
	
	}
	</script>
	<body>
		<div>
		<form id="form1" action="allServerInfoList" method="post" onsubmit="return check()">
			<input type="hidden" name="isGrap" id="isGrap" value="${isGrap}"/>
			<s:text name="searchPartner"></s:text><s:text name="colon"></s:text>
			<s:select name="channel" id="pid" list="partnerList" listKey="pid" listValue="PName" headerKey="0" headerValue="%{getText('pleaseSelect')}" onclick="searchQn()"/>
				
			<s:text name="secondSearchPartner"></s:text><s:text name="colon"></s:text>
			<select id="qnSelect" name="qn">
				<option value="-1"><s:text name="请选择"></s:text></option>
			</select>
			
			<s:text name="searchServer"></s:text><s:text name="colon"></s:text>
			<s:select name="sysNum" id="serverId" list="serverMap" headerKey="0" headerValue="%{getText('pleaseSelect')}"/>
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			<s:text name="timeTo"></s:text>
			<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
			<input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="5" onblur="value=value.replace(/[^\d]/g,'')">
			<input id="bt1" type="submit" value="<s:text name='submit'></s:text>" class="button" onclick="commit(1)"/>
			<input id="bt2" type="button" value='导出' class="button" onclick="downloadExcel()" />
			<input id="bt3" type="submit" value='查看曲线图' class="button" onclick="commit(2)"/>
		</form>
		<form>
			<s:text name="totalNewReg"></s:text> <s:text name="colon"></s:text>${totalNewReg}&nbsp;&nbsp;&nbsp;&nbsp;
			<s:text name="totalPayUserNum"></s:text> <s:text name="colon"></s:text>${totalPayUserNum}&nbsp;&nbsp;&nbsp;&nbsp;
			<s:text name="totalPayAmount"></s:text> <s:text name="colon"></s:text>${totalPayAmount}&nbsp;&nbsp;&nbsp;&nbsp;
			<s:text name="totalAct"></s:text><s:text name="colon"></s:text>${totalAct}&nbsp;&nbsp;&nbsp;&nbsp;
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr>
				<th colspan="22">
					服务器列表
				</th>
			</tr>
			<tr>
				<td>
					日期
				</td>
				<td>
					<s:text name="server"></s:text>
				</td>
				<td>
					<s:text name="channel"></s:text>
				</td>
				<td>
					<s:text name="secondChannel"></s:text>
				</td>
				
				<td>
					累计用户
				</td>
				<td>
					日活跃
				</td>
				<td>
					创角数
				</td>
				<td>
					付费人数
				</td>
				<td>
					新增付费率
				</td>
				<td>
					活跃付费率
				</td>
				<td>
					黏度
				</td>
				<td>
					收入
				</td>
				<td>
					ARPU
				</td>
				<td>
					ARPPU
				</td>
				<td>
					采集时间
				</td>
				<td>
					当天钻石消耗
				</td>
			</tr>
			<s:iterator var="map" value="list">
				<tr>
					<td>
						${map.date}
					</td>
					<td>
						<a href="" onclick="parent.ChangeServer(${map.sysNum})">${map.server}</a>
					</td>
					<td>
						${map.channel}
					</td>
					<td>
						${map.qn}
					</td>
					<td>
						${map.totalNum}
					</td>
					<td>
						${map.dayActive}
					</td>
					<td>
						${map.newUser}
					</td>
					<td>
						${map.payUserNum}
					</td>
					<td>
						${map.newPayRate}%
					</td>
					<td>
						${map.activePayRate}%
					</td>
					<td>
						${map.viscosity}
					</td>
					<td>
						${map.payAmount}
					</td>
					<td>
						${map.arpu}
					</td>
					<td>
						${map.arppu}
					</td>
					<td>
						${map.recordTime}
					</td>
					<td>
						${map.usedDiamond}
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="22">
					<aldtags:pageTag paraStr="isGrap,${isGrap},channel,${channel},qn,${qn},sysNum,${sysNum},pageSize,${pageSize}" datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}"/>
				</td>
			</tr>
		</table>
		<s:text name="userActiveStatsListJsp.note"></s:text><br>
		<s:text name="1、首页数据半小时更新一次"></s:text><br>
		<s:text name="2、提交按钮可以查看数据列表"></s:text><br>
		<s:text name="3、导出按钮可以导出当前数据列表到excel文件"></s:text><br>
		<s:text name="4、查看曲线图按钮可以查看当前查询条件下数据走势"></s:text><br>
		<s:text name="5、黏度：DAU/MAU 即昨日的日活跃 除以 前30天内的排重活跃量，黏度*30 表示平均每个用户在一个月内登录过的天数"></s:text><br>
		</div>
		<!-- <div id="loading" style="text-align:center; margin-right:auto; margin-left:auto; display:block"><font SIZE=5>asfasdfasdfasdfasdfasdfasdfasdfasdfloading</font></div> -->
	</body>
</html>