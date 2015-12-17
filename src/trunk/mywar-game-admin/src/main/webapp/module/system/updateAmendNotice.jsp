<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.adminTool.bo.Partner"%>
<%@page import="com.system.bo.Server"%>
<%@page import="com.system.bo.AmendNotice"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateAmendNoticeJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script src="../../js/jquery/jquery.min.js"></script>
	
	<body>
		&nbsp;
		<form name="f" action="updateAmendNotice?isCommit=T&serverIdParam=${amendNotice.serverId}&partnerIdParam=${amendNotice.partnerId}"  method="post" >
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="updateAmendNoticeJsp.title"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="amendNotice.id"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="hidden" name="serverId" value="${amendNotice.serverId}" />
						<input type="hidden" name="gameWebId" value="${gameWebId}" />
						<input type="hidden" name="createdTime" value="${amendNotice.createdTime}" />
							${amendNotice.serverId}
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="amendNotice.partnerId"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<% List<Partner> partnerList = (ArrayList<Partner>) request.getAttribute("partnerList"); 
						AmendNotice amendNotice = (AmendNotice) request.getAttribute("amendNotice"); 
						%>
						<select name="partnerId" class="select">
							
							<% for (Partner p : partnerList) {%>
								<option value="<%=p.getPid()%>"
									<% if (p.getPid().toString().equals(amendNotice.getPartnerId())) {%>
										selected="true"
									<%}%>
								><%=p.getPName()%>
							<%}%>
						</select>
					</td>	
				</tr>				
				<tr>
					<td>
						<s:text name="amendNotice.title"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="title" value="${amendNotice.title}" />
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="amendNotice.isEnable"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="isEnable" class="select">
							<s:generator separator="," val="%{getText('amendNotice.isEnable_value')}">
								<s:iterator var="str">
									<option value="${str}" 
										<s:if test="amendNotice.isEnable == #str">
											selected=selected </s:if>>
										<s:text name="%{'amendNotice.isEnable_' + #str}"></s:text> 
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="amendNotice.content"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<textarea size="45" name="content" rows="8" cols="60" />${amendNotice.content}</textarea>
					</td>	
				</tr>
				<tr>
					<td colspan="5" align="center">
						<input type="submit"  value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
<script src="../../js/jquery/jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("form").validate({
			rules:{
				serverId:{
					required:true
				},
				title:{
					required:true,
					maxlength:20
				},
				isEnable:{
					required:true
				},
				content:{
					required:true
				}
			}		
		});	
	});
</script>