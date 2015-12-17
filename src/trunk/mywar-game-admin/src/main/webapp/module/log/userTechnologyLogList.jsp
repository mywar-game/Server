<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userTechnologyLogListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="../../js/ajax.js"></script>
				<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script>
	</head>
	<body>
		<form action="userTechnologyLogList" method="post" >
			<table>
				<tr>
<!--					<td>-->
<!--						<s:text name="log.userIdSearch"></s:text><s:text name="colon"></s:text>-->
<!--					</td>-->
<!--					<td>-->
<!--						<input type="text" id="userId" name="userId" onblur="value=value.replace(/[^\d]/g,'')" value="${userId}"/>-->
<!--					</td>-->
<!--					<td>-->
<!--						<s:text name="log.userNameSearch"></s:text><s:text name="colon"></s:text>-->
<!--					</td>-->
<!--					<td>-->
<!--						<input type="text" id="userName" name="userName" value="${userName}"/>-->
<!--					</td>-->
					<td>
						<s:text name="log.nameSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="name" name="name" value="${name}"/>
					</td>
					<td>
						<s:text name="userTechnologyLog.technologyName"></s:text>
					</td>
					<td>
						<s:select list="technologyIdAndNameMap" onchange="submit()" id="searchId" name="searchId" cssClass="select" theme="simple" headerKey="" headerValue="%{getText('pleaseSelect')}" value="searchId" ></s:select>
					</td>
					<td>
						<s:text name="userTechnologyLog.type"></s:text>
					</td>
					<td>
						<select id="searchType" name="searchType" onchange="submit()" class="select">
							<s:generator separator="," val="%{getText('userTechnologyLog.type_values')}">
								<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
								<s:iterator var="str">
									<option value="${str}" <s:if test="searchType == #str">selected=selected</s:if>>
										<s:text name="%{'userTechnologyLog.type_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
				</tr>
				<tr>
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
					
					<td><input type="submit" value="<s:text name="search"></s:text>"  onClick=""/></td>
				</tr>
			</table>
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center >
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						<s:text name="userTechnologyLogListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="userTechnologyLogListJsp.id"></s:text>
				</td>
				<td>
					<s:text name="userTechnologyLog.name"></s:text>
				</td>
				<td>
					<s:text name="userTechnologyLog.technologyName"></s:text>
				</td>
				<td>
					<s:text name="userTechnologyLog.type"></s:text>
				</td>
				<td>
					<s:text name="userTechnologyLog.technologyLevel"></s:text>
				</td>
				<td>
					<s:text name="userTechnologyLogJsp.cost"></s:text>
				</td>
				<td>
					<s:text name="userTechnologyLog.speedTime"></s:text>
				</td>
				<td>
					<s:text name="userTechnologyLog.createTime"></s:text>
				</td>
			</tr>
			
			<s:iterator var="userTechnologyLog" value="userTechnologyLogList" status="sta">
				<tr>
					<td>${sta.index+1}</td>
					<td>${userTechnologyLog.name}</td>
					<td>
						${technologyIdAndNameMap[userTechnologyLog.technologyId]}
					</td>
					<td>
						<s:text name="%{'userTechnologyLog.type_'+#userTechnologyLog.type}"></s:text>
					</td>
					<td>
						${userTechnologyLog.technologyLevel}
					</td>
					<td>
						<s:text name="%{'userTechnologyLog.costType_'+#userTechnologyLog.costType}">
							<s:param>${userTechnologyLog.costNum}</s:param>
						</s:text>
					</td>
					<td>
						<fmt:formatNumber type="number" value="${userTechnologyLog.speedSeconds/60/60 + 0.5}" pattern="#"></fmt:formatNumber>小时(${userTechnologyLog.speedSeconds}秒)
					</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#userTechnologyLog.createTime"></s:param>
						</s:text>
					</td>
					
				</tr>
			</s:iterator>
			
			<tr>
				<td colspan="100">
					<aldtags:pageTag paraStr="name,${name},searchId,${searchId},searchType,${searchType},pageSize,${pageSize}" />
				</td>
			</tr>
		</table>
	</body>
</html>