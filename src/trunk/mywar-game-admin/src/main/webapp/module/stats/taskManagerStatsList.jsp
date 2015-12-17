<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
		<title><s:text name="taskManagerStatsList.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
   <script type="text/javascript">
	function control() {
		var value = document.getElementById("start");
		var requestType = document.getElementById("requestType");
		if(value.value == "<s:text name='taskManagerStatsList.start'></s:text>"){
			document.getElementById("start").value = "<s:text name='taskManagerStatsList.stop'></s:text>";
			 window.location.href = "taskManagerStatsList?type="+2+"&requestType="+requestType.value;
		}else{
			document.getElementById("start").value = "<s:text name='taskManagerStatsList.start'></s:text>";
			 window.location.href = "taskManagerStatsList?type="+3+"&requestType="+requestType.value;
		}
	}
	
	function queyResult() {
		var requestType = document.getElementById("requestType");
		 window.location.href = "taskManagerStatsList?type="+1+"&requestType="+requestType.value;
	}
	
  </script>
  <body>
  <s:if test="showType == 2">
	  	<s:set name="buttonName" value="%{getText('msgManagerStatsList.start')}"></s:set>
  	</s:if>
  	<s:if test="showType == 1">
	  	<s:set name="buttonName" value="%{getText('msgManagerStatsList.stop')}"></s:set>
  	</s:if>
     <input value="${buttonName}" id="start" class="button" type="button" onclick='control()'/>
     <input value="<s:text name="taskManagerStatsList.result"></s:text>" id="result" class="button" type="button" onclick='queyResult()'/>
     <select id="requestType" name="requestType" onchange="queyResult()">
     	<option  value="1" <s:if test="requestType == 1">selected=selected</s:if>><s:text name="secondaryServer.serverType_0"></s:text></option>
     	<option value="2" <s:if test="requestType == 2">selected=selected</s:if>><s:text name="secondaryServer.serverType_2"></s:text></option>
     	<option value="3" <s:if test="requestType == 3">selected=selected</s:if>><s:text name="secondaryServer.serverType_3"></s:text></option>
     	<option value="4" <s:if test="requestType == 4">selected=selected</s:if>><s:text name="secondaryServer.serverType_1"></s:text></option>
     </select>
     &nbsp; &nbsp; &nbsp; &nbsp;
     <s:if test="requestType == 2 ||requestType == 3">
     	<s:text name="taskManagerStatsList.battleIngNum"></s:text>${battleIngNum} &nbsp; &nbsp;
     	<s:text name="taskManagerStatsList.totalBattleNum"></s:text>${totalBattleNum}
     </s:if>
     &nbsp; &nbsp; &nbsp; &nbsp;
     <s:if test="requestType == 3">
     	<s:text name="taskManagerStatsList.arenaMatchTimes"></s:text>${arenaMatchTimes} &nbsp; &nbsp;
     	<s:text name="taskManagerStatsList.qualifyMatchTimes"></s:text>${qualifyMatchTimes} &nbsp; &nbsp;
     </s:if>
     <table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
	    	<tr class="border">
				<td class="td_title" colspan="40" align="center">
					<center>
						<s:text name="taskManagerStatsList.title"></s:text>
					</center>
				</td>
			</tr>
			
			<tr>
				<td>
					<s:text name="taskManagerStatsList.taskCmdCode"></s:text>
				</td>
				<td>
					<s:text name="taskManagerStatsList.executeTimes"></s:text>
				</td>
				<td>
					<s:text name="taskManagerStatsList.executeTotalTime"></s:text>
				</td>
				<td>
					<s:text name="taskManagerStatsList.timeDiffence"></s:text>
				</td>
				<td>
					<s:text name="taskManagerStatsList.writeallbytes"></s:text>
				</td>
				<td>
					<s:text name="taskManagerStatsList.averageDataTime"></s:text>
				</td>
				<td>
					<s:text name="taskManagerStatsList.averageTime"></s:text>
				</td>
				<s:if test="requestType == 1">
					<td>
						<s:text name="taskManagerStatsList.detail"></s:text>
					</td>
					<td>
						<s:text name="taskManagerStatsList.averageData"></s:text>
					</td>
				</s:if>
			</tr>
				
			<s:iterator value="statsMap"  id="column">
				<tr>
				<td><s:property value="key"/></td>
				<td><s:property value="value.times"/></td>
				<td><s:property value="value.allTime"/></td>
				<td><s:property value="value.timeDiffence"/></td>
				<td><s:property value="value.writeallbytes"/></td>
				<td>${value.averageDataTime}</td>
				<td>${value.averageTime}</td>
				
				<!-- 显示查询缓存的详情信息 -->
				<s:if test="requestType == 1">
					<s:if test="appiontStatsMap[key].size() == 0">
									<td>无</td>
									<td>无</td>
					</s:if>
					
					<td><s:iterator value="appiontStatsMap[key]" var="map" >
						<s:property value="#map.key"/>:
						<s:property value='#map.value.times'/>
						<hr/>
					</s:iterator>
					</td>
					<td>${aveageValue[key]}</td>
				</s:if>
				</tr>	
			</s:iterator>
			
	   		<tr id="pageTagTr">
				<td colspan="20" id="page">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
  </body>
</html>
