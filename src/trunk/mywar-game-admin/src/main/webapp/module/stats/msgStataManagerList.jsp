<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
		<title><s:text name="msgManagerStatsList.title"></s:text></title>

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
		if(value.value == "<s:text name='msgManagerStatsList.start'></s:text>"){
			document.getElementById("start").value == "<s:text name='msgManagerStatsList.stop'></s:text>";
			 window.location.href = "msgStataManagerList?type="+2+"&requestType="+requestType.value;
		}else{
			document.getElementById("start").value == "<s:text name='msgManagerStatsList.start'></s:text>";
			 window.location.href = "msgStataManagerList?type="+3+"&requestType="+requestType.value;
		}
	}
	
	function queyResult() {
		 var requestType = document.getElementById("requestType");
		 window.location.href = "msgStataManagerList?type="+1+"&requestType="+requestType.value;
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
     <input value="<s:text name="msgManagerStatsList.result"></s:text>" id="result" class="button" type="button" onclick='queyResult()'/>
     <select id="requestType" name="requestType">
     	<option  value="1" <s:if test="requestType == 1">selected=selected</s:if>><s:text name="secondaryServer.serverType_0"></s:text></option>
     	<option value="2" <s:if test="requestType == 2">selected=selected</s:if>><s:text name="secondaryServer.serverType_2"></s:text></option>
     	<option value="3" <s:if test="requestType == 3">selected=selected</s:if>><s:text name="secondaryServer.serverType_3"></s:text></option>
     	<option value="4" <s:if test="requestType == 4">selected=selected</s:if>><s:text name="secondaryServer.serverType_1"></s:text></option>
     </select>
     <table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
	    	<tr class="border">
				<td class="td_title" colspan="40" align="center">
					<center>
						<s:text name="msgManagerStatsList.title"></s:text>
					</center>
				</td>
			</tr>
			
			<tr>
				<td>
					<s:text name="msgManagerStatsList.executeTimes"></s:text>
				</td>
				<td>
					<s:text name="msgManagerStatsList.msgCmdCode"></s:text>
				</td>
				<td>
					<s:text name="msgManagerStatsList.executeTotalTime"></s:text>
				</td>
			</tr>
				
			<tr>
				<td>
					${msgBean.msgNum}
				</td>
				<td>
					${msgBean.stataTime}
				</td>
				<td>
					${msgBean.continueTime}
				</td>
			</tr>
			
	   		<tr id="pageTagTr">
				<td colspan="20" id="page">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
  </body>
</html>
