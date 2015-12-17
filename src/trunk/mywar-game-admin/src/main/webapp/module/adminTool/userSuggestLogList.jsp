<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userSuggestLogListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		function dealSuggest(userId,suggestId,suggestType,dealType,name) {
			window.location.href = "userSuggestLogListdealSuggest?userId="+userId+"&suggestId="+suggestId+"&suggestType="+suggestType+"&dealType="+dealType+"&name="+ encodeURI(encodeURI(name));
			alert();
		}
	</script>
	<body>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						<s:text name="userSuggestLogListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="userSuggestLogListJsp.name"></s:text>
				</td>
				<td>
					日期
				</td>
				<td>
					<s:text name="userSuggestLogListJsp.content"></s:text>
				</td>
				<td>
					<s:text name="userSuggestLogListJsp.suggestType"></s:text>
				</td>
				<td>
					处理
				</td>
				<td>
					处理人
				</td>
				<td>
					处理时间
				</td>
			</tr>
			<s:iterator var="userSuggest" value="userSuggestlist">
				<tr>
					<td>
						${userSuggest.name}
					</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#userSuggest.commitTime"></s:param>
						</s:text>
					</td>
					<td style="word-wrap: break-word;word-break:break-all;">
						${userSuggest.content}
					</td>
					<td>
						<s:text name="%{'userSuggestLogListJsp.suggestType_'+#userSuggest.suggestType}"></s:text>
					</td>
					<td>
						<s:if test="#userSuggest.dealType == null">
							<s:generator separator="," val="%{getText('userSuggestLogListJsp.dealType_'+#userSuggest.suggestType+'_value')}">
								<s:iterator var="str">
									<a href="#" onclick='dealSuggest(${userSuggest.userId},${userSuggest.id},${userSuggest.suggestType},${str},"${userSuggest.name}")'>
										<s:text name="%{'userSuggestLogListJsp.dealType_'+#userSuggest.suggestType+'_'+#str}"></s:text>
									</a>
								</s:iterator>
							</s:generator>	
						</s:if>
						<s:else>
							<s:text name="%{'adminDealSuggestLog.dealType_'+#userSuggest.suggestType+'_'+#userSuggest.dealType}"></s:text>
						</s:else>
					</td>
					<td>
						<s:if test="#userSuggest.adminName == null">
							暂无
						</s:if>
						<s:else>
							${userSuggest.adminName}
						</s:else>
					</td>
					<td>
						<s:if test="#userSuggest.dealTime != null">
							<s:text name="format.date_time">
								<s:param value="#userSuggest.dealTime"></s:param>
							</s:text>
						</s:if>
					</td>
				</tr>
			</s:iterator>
			
			<tr>
				<td colspan="10">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>