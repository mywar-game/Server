<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userBuildingLogListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="../../js/json.js"></script>
	</head>
	<body>
		<form action="userBuildingLogList" method="post" >
			<table>
				<tr>
					<td>
						<s:text name="log.nameSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="name" name="name" value="${name}"/>
					</td>
					<td>
						<s:text name="userBuildingLog.buildingName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="searchBuildingId" class="select" onchange="submit();">
							<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
							<s:iterator value="buildingIDNameMap">
								<option value="${key}" <s:if test="searchBuildingId == key">selected=selected</s:if>>${value}</option>
							</s:iterator>
						</select>
					</td>
					<td>
						<s:text name="userBuildingLog.operation"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="searchOperation" class="select" onchange="submit();">
							<s:generator separator="," val="%{getText('userBuildingLog.operation_value')}">
								<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
								<s:iterator var="str">
									<option value="${str}" <s:if test="searchOperation == #str">selected=selected</s:if>>
										<s:text name="%{'userBuildingLog.operation_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
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
				<td class="td_title" colspan="8" align="center">
					<center>
						<s:text name="userBuildingLogListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="userBuildingLog.userBuildingLogId"></s:text>
				</td>
				<td>
					<s:text name="userBuildingLog.Name"></s:text>
				</td>
				<td>
					<s:text name="userBuildingLog.buildingName"></s:text>
				</td>
				<td>
					<s:text name="userBuildingLog.operation"></s:text>
				</td>
				<td>
					<s:text name="userBuildingLog.lastLevel"></s:text>
				</td>
				<td>
					<s:text name="userBuildingLog.cost"></s:text>
				</td>
				<td>
					<s:text name="userBuildingLog.createTime"></s:text>
				</td>
			</tr>
			
			<s:iterator var="userBuildingLog" value="userBuildingLogList" status="sta">
				<tr>
					<td>
						${sta.index+1}
					</td>
					<td>
						${userBuildingLog.name}
					</td>
					<td>
						${buildingIDNameMap[userBuildingLog.buildingId]}
					</td>
					<td>
						<s:text name="%{'userBuildingLog.operation_'+#userBuildingLog.operation}"></s:text>
					</td>
					<td>
						${userBuildingLog.lastLevel}
					</td>
					<td>
						<s:text name="%{'userBuildingLog.cost_'+#userBuildingLog.operation}">
							<s:param>${userBuildingLog.cost}</s:param>
						</s:text>
						<s:if test="#userBuildingLog.operation == 'accelerate'">
							加速<fmt:formatNumber type="number" value="${userBuildingLog.speedSeconds/60/60 + 0.5}" pattern="#"></fmt:formatNumber>小时(${userBuildingLog.speedSeconds}秒)
						</s:if>
					</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#userBuildingLog.createTime"></s:param>
						</s:text>
					</td>
				</tr>
			</s:iterator>
				
			<tr>
				<td colspan="100">
					<aldtags:pageTag paraStr="userId,${userId},userName,${userName},name,${name},pageSize,${pageSize},searchBuildingId,${searchBuildingId},searchOperation,${searchOperation}" />
				</td>
			</tr>
		</table>
	</body>
</html>