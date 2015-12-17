<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userResourceLogListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="../../js/json.js"></script>
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script>
		<script type="text/javascript">
			function dateMust(){
				var userId = document.getElementById("userId").value;
				var lodoId = document.getElementById("lodoId").value;
				var startDate = document.getElementById("startDate").value;
				var endDate = document.getElementById("endDate").value;
				//alert("startDate "+startDate);
				//alert("endDate "+endDate);
				if(userId=="" && lodoId==0){
					alert("请输入一个用户编号或游戏ID");
					return false;
				}
				if(startDate =="" || endDate == ""){
					alert("请选择查询时间段");
					return false;
				}
				return true;
			}
		</script>
	</head>
	<body>
		<form action="userResourceLogList?isCommit=T" method="post" onsubmit="return dateMust()">
			<table>
				<tr>
					<td>
						<s:text name="log.userIdSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="userId" name="userId" value="${userId}"/>
					</td>
					 <td>
						<s:text name="log.lodoSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="lodoId" name="lodoId" value="${lodoId}"/>
					</td>
					
					<td>
						<s:text name="userResourceLog.operation"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<!-- <select name="searchOperation" class="select" onchange="submit();"> -->
						<select name="searchOperation" class="select">
							<s:generator separator="," val="%{getText('userResourceLog.operation_value')}">
								<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
								<s:iterator var="str">
									<option value="${str}" <s:if test="searchOperation== #str">selected=selected</s:if>>
										<s:text name="%{'userResourceLog.operation_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
					<td>
						<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<input type="text" id="startDate" name="startDate" value="<s:if test="startDate != null"><s:text name="format.date_time"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){endDate.focus();}})"/>
						<s:text name="timeTo"></s:text>
						<input type="text" id="endDate" name="endDate" value="<s:if test="endDate != null"><s:text name="format.date_time"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}'})"/>
					</td>
					<td>
						<s:text name="pageSize"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
					</td>
					
					<td>
						<input type="submit" value="<s:text name="search"></s:text>" />
					</td>
				</tr>
			</table>
		</form>

		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center >
			<tr class="border">
				<td class="td_title" colspan="9" align="center">
					<center>
						<s:text name="userResourceLogListJsp.title"></s:text>
					</center>
				</td>
			</tr>	
			<tr>
				<td>
					<s:text name="userResourceLog.userResourceLogId"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.userId"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.userName"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.lodoId"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.sourceType"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.operation"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.changeNum"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.nowNum"></s:text>
				</td>
				<td>
					<s:text name="userResourceLog.createTime"></s:text>
				</td>
			</tr>
			<s:iterator var="userResourceLog" value="userResourceLoglist" status="sta">
				<tr>
					<td>${sta.index+1}</td>
					<td>${userResourceLog.userId}</td>
					<td>${userIdUserMap[userResourceLog.userId].userName}</td>
					<td>${userIdUserMap[userResourceLog.userId].lodoId}</td>
					<td><s:text name="%{'userBag.type_'+#userResourceLog.sourceType}"></s:text></td>
					<td><s:text name="%{'userResourceLog.operation_'+#userResourceLog.operation}"></s:text></td>
					<td>${userResourceLog.changeNum}</td>
					<td>${userResourceLog.nowNum}</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#userResourceLog.createTime"></s:param>
						</s:text>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="9">
					<aldtags:pageTag paraStr="isCommit,T,userId,${userId},lodoId,${lodoId},pageSize,${pageSize},searchOperation,${searchOperation}" datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}"/>
				</td>
			</tr>
		</table>
	</body>
</html>