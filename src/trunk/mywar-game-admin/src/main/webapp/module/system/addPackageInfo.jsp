<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.adminTool.bo.Partner"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="addPackageInfoJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script src="../../js/jquery/jquery.min.js"></script>
	<script type="text/javascript" >
		
		function selectAll(check) {
			var isCheck = check.checked;
			var pidArr = document.getElementsByName('pid');
			for (var i = 0; i < pidArr.length; i++) {
				pidArr[i].checked = isCheck;
			}			
		}
		
		function checkOne(check) {
			var isCheck = check.checked;
			if (!isCheck) {
				document.getElementById('allCheck').checked = isCheck;
				return;
			}
			
			var pidArr = document.getElementsByName('pid');
			for (var i = 0; i < pidArr.length; i++) {
				if (!pidArr[i].checked) {
					return;
				}
			}
			
			document.getElementById('allCheck').checked = isCheck;
		}
		
	</script>
	
	<body>
		&nbsp;
		<form action="addPackageInfo?isCommit=T" method="post" >
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="addPackageInfoJsp.title"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td width="150">
						<s:text name="packageInfo.version"></s:text>
						<s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="hidden" name="gameWebId" value="${gameWebId}" />
						<input type="text" name="version" value="" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="packageInfo.versions"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<input type="text"  name="versions" value="" />
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="packageInfo.fullUrl"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<input type="text" size="35" name="fullUrl" value="" />
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="packageInfo.upgradeUrl"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<input type="text" size="35" name="upgradeUrl" value="" />
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="packageInfo.pkgType"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<select name="pkgType" class="select">
							<s:generator separator="," val="%{getText('packageInfo.pkgType_value')}">
								<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
								<s:iterator var="str">
									<option value="${str}" >
										<s:text name="%{'packageInfo.pkgType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="packageInfo.isTest"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<select name="isTest" class="select">
							<s:generator separator="," val="%{getText('packageInfo.isTest_value')}">
								<s:iterator var="str">
									<option value="${str}" >
										<s:text name="%{'packageInfo.isTest_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="packageInfo.partnerId"></s:text><s:text name="colon"></s:text>
						<s:text name="allSelect"></s:text><input type="checkbox" name="allCheck" onclick="selectAll(this)" id="allCheck" />
					</td>
					<%
						List<Partner> partnerList = (ArrayList<Partner>) request.getAttribute("partnerList");
						for (int i = 0; i < partnerList.size(); i++) {
							Partner partner = partnerList.get(i);
							Integer size = partnerList.size();
					%>									
						<td>
							<input type="checkbox" onclick="checkOne(this)" name="pid" value="<%=partner.getPid()%>" /><%=partner.getPName()%>
						</td>	
						<%
							  if (i > 0 && (i + 1) % 4 == 0 && (i+1) != size) {
						%>
							</tr>
							<tr>
								<td></td>
							<%}%>
					<%}%>	
				</tr>
				<tr>
					<td>
						<s:text name="packageInfo.description"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<textarea size="45" name="description" rows="6" cols="60" value="" /></textarea>
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
				version:{
					required: true,
					maxlength: 20
				},
				pkgType:{
					required: true
				},
				isTest:{
					required: true
				},
				pid:{
					required: true
				},
				versions:{
					maxlength: 255
				},
				fullUrl:{
					maxlength: 255
				},
				upgradeUrl:{
					maxlength: 255
				},
				description:{
					maxlength:200
				}
			}		
		});	
	});
</script>