<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.adminTool.bo.Partner"%>
<%@page import="com.adminTool.bo.PartnerQn"%>
<%@page import="com.system.bo.VersionManagerApk"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateVersionManagerApkJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	
	<script src="../../js/jquery/jquery.min.js"></script>
	<script type="text/javascript">
		var qnArr = new Array();
		
		<s:iterator var="partnerQn" value="qnList">
			var qn = new Object();
			qn.partnerId = "${partnerQn.partnerId}";
			qn.qn = "${partnerQn.qn}";
			qn.name = "${partnerQn.name}";
			
			qnArr.push(qn);
		</s:iterator>
		
		function selectFun() {
		    
			var partnerId = document.getElementById("partnerId").value;	
				
			var obj = document.getElementById('qn');
			
			for (i = obj.options.length-1; i >= 0; i--) {
			    obj.options[i] = null;
	        }
			 // 不是梵町Android  需要显示请选择
			if(partnerId != "1001")
			    obj.add(new Option("---0---", 0));
			for (var i = 0; i < qnArr.length; i++) {
			    
				if (qnArr[i].partnerId == partnerId) {					
					
					obj.add(new Option(qnArr[i].name, qnArr[i].qn));
						
				}				
			}
		}
		window.onload = function hid(){
	       selectFun();
	       var qn = "${versionManagerApk.qn}";
	       var obj = document.getElementById('qn');
	       for(i=0; i<obj.options.length; i++){
	           if(obj.options[i].value == qn){
	              obj.options[i].selected = true;
	           }
	       }
	    }
	
	</script>
	
	<body>
		&nbsp;
		<form action="updateVersionManagerApk?isCommit=T" method="post" >
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="updateVersionManagerApkJsp.title"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td >
						<s:text name="versionManagerApk.version"></s:text><s:text name="colon"></s:text>
					</td>
					<td >
						<input type="hidden" name="id" value="${versionManagerApk.id}" />
						<input type="hidden" name="gameWebId" value="${gameWebId}" />
						<input type="hidden" size="6" name="apkBigVersion" value="${versionManagerApk.apkBigVersion}" />
						<input type="hidden" size="6" name="apkSmallVersion" value="${versionManagerApk.apkSmallVersion}" />
						${versionManagerApk.apkBigVersion}.${versionManagerApk.apkSmallVersion}
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="versionManagerApk.partnerId"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<% List<Partner> partnerList = (ArrayList<Partner>) request.getAttribute("partnerList"); 
						   VersionManagerApk versionManagerApk = (VersionManagerApk) request.getAttribute("versionManagerApk");	
						%>
						
						<select name="partnerId" id="partnerId" onchange="selectFun()" class="select">
							<% for (Partner p : partnerList) {%>
								<option value="<%=p.getPid()%>"
								<% if (p.getPid().toString().equals(versionManagerApk.getPartnerId())) { %>
									selected="true"
								<%}%>
								><%=p.getPName()%>
							<%}%>
						</select>
					</td>	
				</tr>
				<tr>
					<td>
						qn<s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<% List<PartnerQn> qnList = (ArrayList<PartnerQn>) request.getAttribute("qnList"); %>
						<select name="qn" id="qn" class="select">
							<option value="0">---0---
							<%for(PartnerQn pq : qnList) { %>
							<option value="<%=pq.getQn()%>" 
							  <% if(pq.getQn().equals(versionManagerApk.getQn())){
									 %>selected = "true" <%}%>>
									 <%=pq.getName()%>
							<%}%>
						</select>
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="versionManagerApk.isTest"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<select name="isTest" class="select">
							<s:generator separator="," val="%{getText('packageInfo.isTest_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="versionManagerApk.isTest == #str">selected=selected</s:if>>
										<s:text name="%{'packageInfo.isTest_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="versionManagerApk.url"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<input type="text" size="70" name="url" value="${versionManagerApk.url}" />
					</td>	
				</tr>								
				<tr>
					<td>
						<s:text name="versionManagerApk.description"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<textarea size="45" name="description" rows="6" cols="60" value="" />${versionManagerApk.description}</textarea>
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
				apkBigVersion:{
					required: true,
					digits:true
				},
				apkSmallVersion:{
					required: true,
					digits:true
				},
				partnerId:{
					required: true
				},
				url:{
					required: true
				}
			}		
		});	
	});
</script>