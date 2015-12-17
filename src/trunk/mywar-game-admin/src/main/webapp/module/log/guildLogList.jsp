<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="guildLogListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<form action="guildLogList?isCommit=1" method="post" >
			<table>
				<tr>
					<td>
						<s:text name="guildLogListJsp.guildIdSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="guildKey" name="guildKey" onblur="value=value.replace(/[^\d]/g,'')" value="${guildKey}"/>
					</td>	
					<td>
						<s:text name="guildLogListJsp.guildNameSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="inputName" name="inputName" value="${inputName}"/>
					</td>
					<td>
						<s:text name="guildLog.operation"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="searchOperation" class="select" onchange="submit();">
							<s:generator separator="," val="%{getText('guildLog.operation_value')}">
								<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
								<s:iterator var="str">
									<option value="${str}" <s:if test="searchOperation == #str">selected=selected</s:if>>
										<s:text name="%{'guildLog.operation_'+#str}"></s:text>
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
				<td class="td_title" colspan="50" align="center">
					<center>
						<s:text name="guildLogListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="guildLog.guildLogId"></s:text>
				</td>
				<td>
					<s:text name="guildLog.guildId"></s:text>
				</td>
				<td>
					<s:text name="guildLog.guildName"></s:text>
				</td>
				<td>
					<s:text name="guildLogListJsp.user"></s:text>
				</td>
				<td>
					<s:text name="guildLog.operation"></s:text>
				</td>
				<td>
					<s:text name="guildLogListJsp.beOptedUser"></s:text>
				</td>
				<td>
					<s:text name="guildLog.createTime"></s:text>
				</td>
				
			</tr>
			<s:iterator var="guildLog" value="guildLogList" status="sta">
				<tr>
					<s:if test="#sta.index == 0 && inputName!=null && !inputName.equals('') && guildKey==null">
						<s:set value="guildKey = #guildLog.guildId"></s:set>
					</s:if>
					<td>${sta.index+1}</td>
					<td>${guildLog.guildId}</td>
					<td>${guildLog.guildName}</td>
					<td>${userIdNameMap[guildLog.userId]}</td>
					<td><s:text name="%{'guildLog.operation_'+#guildLog.operation}"></s:text></td>
					<td>${userIdNameMap[guildLog.optUserId]}</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#guildLog.createTime"></s:param>
						</s:text>
					</td>
				</tr>
			</s:iterator>
		
			<tr>
				<td colspan="50">
					<aldtags:pageTag paraStr="guildKey,${guildKey},searchOperation,${searchOperation},pageSize,${pageSize}"/>
				</td>
			</tr>
		</table>
	</body>
</html>