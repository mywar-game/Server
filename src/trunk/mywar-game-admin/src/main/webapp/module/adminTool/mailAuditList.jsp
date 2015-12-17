<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" pageEncoding="Utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>Insert title here</title>
	
	<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/ajax.js"></script>
	
</head>
<body>
<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
		<tr class="border">
			<td class="td_title" ><s:text name="邮件审核列表"></s:text></td>
		</tr></table>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
		<tr>
			<td><s:text name="编号"></s:text></td>
			<td width="5%"><s:text name="主题"></s:text></td>
			<td><s:text name="内容"></s:text></td>
			<td><s:text name="发送服务器"></s:text></td>
			<td><s:text name="收件人"></s:text></td>
			<td><s:text name="邮件附件"></s:text></td>
			<td><s:text name="创建日期"></s:text></td>
			<td><s:text name="发送者"></s:text></td>
			<td><s:text name="审核者"></s:text></td>
			<td width="4%"><s:text name="状态"></s:text></td>
			<td width="3%" colspan="3"><s:text name="操作"></s:text></td>
		</tr>

		<s:iterator var="data" value="mailList">
			<tr>
				<input type="hidden" value="${data.status}"/>
				<td>${data.adminMailId}</td>
				<td>${data.title}</td>
				<td>
					<div style="width:400px; overflow:auto" align="left">
							${data.content}
					</div>
				</td>				
				<td>
					<div style="word-break:keep-all">
						${data.serverIds}
					</div></td>
				<td>
					<div style="width:400px; word-break:break-all">
						<s:if test="#data.target == 1">
							<s:if test="#data.lodoIds != ''">
								注册时间在 <b>${data.lodoIds}</b> 至 <b>${data.date}</b> 之间的用户
							</s:if>
							<s:else>
								注册时间在 <b>${data.date}</b> 之前的用户
							</s:else>						
						</s:if>
						<s:elseif test="#data.target == 2">lodoId 为 <b>${data.lodoIds}</b> 的用户</s:elseif>
						<s:elseif test="#data.target == 3">在 <b>${data.date}</b> 这一天登录的用户</s:elseif>
						<s:elseif test="#data.target == 4">在 <b>${data.date}</b> 这一天充值过的用户</s:elseif>
						<s:elseif test="#data.target == 5">渠道商是 <b>${data.partner}</b> 的用户</s:elseif>
					</div>
				</td>
				<td>
					<div style="width:200px;word-break:keep-all">
						<s:if test="#data.toolList.length() == 0">无</s:if>
						<s:else>${data.toolList}</s:else>
					</div>
				</td>
				<td>${data.createdTime}</td>
				<td>${data.senderm}</td>
				<td>${data.auditer}</td>
				<s:if test="%{#data.status == 0}">
					<td><s:text name="待审查"></s:text></td>
					<td><a href="mailAudit?adminMailId=${data.adminMailId}"><s:text name="审核"></s:text></a></td>
				</s:if>
				<s:elseif test="%{#data.status == -1}">
					<td><s:text name="拒绝"></s:text></td>
					<%-- <td>
						<a href="mailUpdate?adminMailId=${data.adminMailId}"><s:text name="修改"></s:text></a>
					</td> --%>
					<td>
						<a
							href="javascript:if(confirm('<s:text name="确定删除吗？"><s:param>${data.adminMailId}</s:param></s:text>'))location.replace('mailDelete?adminMailId=${data.adminMailId}');">
							<s:text name="删除"></s:text>
						</a>
					</td> 
					<td>
						<a href="mailAudit?adminMailId=${data.adminMailId}"><s:text name="审核"></s:text></a>
					</td>
					
				</s:elseif>
				<s:elseif test="%{#data.status == 1}">
					<td><s:text name="审查通过"></s:text></td>
				</s:elseif>

				<%-- <td>
				<a
					href="javascript:if(confirm('<s:text name="确定删除吗？"><s:param>${data.id}</s:param></s:text>'))location.replace('delAudit?id=${data.id}');">
						<s:text name="删除"></s:text>
				</a> 
					&nbsp;
					
					<a href="updateAudit?id=${data.id}"><s:text name="修改"></s:text></a>
					
					&nbsp;
					<a href="auditActivity?id=${data.id}"><s:text name="审核"></s:text></a>
				</td> --%>
			</tr>
		</s:iterator>
		 <tr>
			<td colspan="10"><aldtags:pageTag/></td>
		</tr> 
		
	</table>

</body>

</html>