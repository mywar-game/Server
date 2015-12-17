<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userLevelIntervalConsumeStatsListJsp.title"></s:text></title>
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
	<body>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="100" align="center">
					<center>
						<s:text name="userLevelIntervalConsumeStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td rowspan="2">
					<s:text name="userLevelIntervalConsumeStatsListJsp.userLevelInterval"></s:text>
				</td>
<!--			colspan等于类型的个数	-->
				<s:set name="types" value="%{getText('userResourceLog.use_value')}"></s:set>
				<s:set name="typeArr" value="#types.split(',')"></s:set>
				<td colspan="${fn:length(typeArr)}">
					<center>
						各个消费类型对应的钻石消费数
					</center>
				</td>
				<td rowspan="2">
					等级区间消费总额
				</td>
			</tr>
			<tr>
				<s:generator separator="," val="%{getText('userResourceLog.use_value')}">
					<s:iterator var="str">
						<td>
							<s:text name="%{'userResourceLog.operation_'+#str}"></s:text>
						</td>
					</s:iterator>
				</s:generator>
			</tr>

			<s:bean name="org.apache.struts2.util.Counter" id="counter">
				<s:param name="first" value="1" />
				<s:param name="last" value="20" />
				<s:iterator>
					<s:set var="levelIntervalIndex" value="current-1"></s:set>
					<tr>
						<td>
							<s:text name="%{'userLevelIntervalConsumeStatsListJsp.userLevelInterval_'+#levelIntervalIndex}"></s:text>
						</td>
<!--					当前等级区间的消费总额	-->
						<s:set var="intervalConsumeAmount" value="0"></s:set>
						<s:generator separator="," val="%{getText('userResourceLog.use_value')}">
							<s:iterator var="consumeType">
<!--							当前等级区间的当前消费类型的消费额，没有则为空	-->
								<s:set var="consumeAmount" value="statsMap[#levelIntervalIndex + '-' + #consumeType]"></s:set>
								<s:set name="intervalConsumeAmount" value="#intervalConsumeAmount+#consumeAmount*1"></s:set>
								<td>
									<fmt:formatNumber type="number" value="${consumeAmount}" pattern="#,###" />
								</td>
							</s:iterator>
						</s:generator>
						<td>
							<fmt:formatNumber type="number" value="${intervalConsumeAmount}" pattern="#,###" />
						</td>
					 </tr>
				</s:iterator>
			</s:bean>
			<tr>
				<td>
					总计
				</td>
				<s:set var="totalConsumeAmount" value="0"></s:set>
				<s:generator separator="," val="%{getText('userResourceLog.use_value')}">
					<s:iterator var="consumeType">
						<s:set var="typeConsumeAmount" value="0"></s:set>
						<s:bean name="org.apache.struts2.util.Counter" id="counter">
							<s:param name="first" value="1" />
							<s:param name="last" value="20" />
							<s:iterator>
								<s:set var="levelIntervalIndex" value="current-1"></s:set>
								<s:set var="consumeAmount" value="statsMap[#levelIntervalIndex + '-' + #consumeType]"></s:set>
								<s:set name="typeConsumeAmount" value="#typeConsumeAmount+#consumeAmount*1"></s:set>
							</s:iterator>
						</s:bean>
						<td>
							<s:set name="totalConsumeAmount" value="#totalConsumeAmount+#typeConsumeAmount*1"></s:set>
							<fmt:formatNumber type="number" value="${typeConsumeAmount}" pattern="#,###" />
						</td>
					</s:iterator>
				</s:generator>
				<td>
					<fmt:formatNumber type="number" value="${totalConsumeAmount}" pattern="#,###" />
				</td>
			</tr>
			<tr>
				<td colspan="100">
					<aldtags:pageTag para1="pageSize" value1="${pageSize}"/>
				</td>
			</tr>
		</table>
	</body>
</html>