<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="partnerListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
	
		function del(pid) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='partner.pid'></s:text></s:param><s:param>"+pid+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delPartner?pid=" + pid;
				ajaxobj.callback = function() {
					var tr = document.getElementById(pid);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(pid) {
			window.location.href = "updatePartner?pid=" + pid;
		}
	
		function add() {
			window.location.href = "addPartner";
		}
	</script>
	<body>
		<input type="submit" value="<s:text name='addPartnerJsp.title'></s:text>" class="button" onclick=add(); />
		<!--
		<input type="button" value='<s:text name=""></s:text>' class="button" onclick="" />
		-->
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="partnerListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<s:text name="partner.pid"></s:text>
				</td>
				<td >
					<s:text name="partner.PNum"></s:text>
				</td>
				<td>
					<s:text name="partner.PName"></s:text>
				</td>
				<td>
					<s:text name="partner.createTime"></s:text>
				</td>
				<td>
					<s:text name="partner.editTime"></s:text>
				</td>
				<!--				
				<td>
					<s:text name="partner.removeTime"></s:text>
				</td>
				<td>
					<s:text name="partner.removeStatus"></s:text>
				</td>
				-->
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="partner" value="partnerList">
				<tr id="${partner.pid}">
					<td title="${partner.pid}">
						${partner.pid}
					</td>
					<td title="${partner.PNum}">
						${partner.PNum}
					</td>
					<td title="${partner.PName}">
						${partner.PName}
					</td>
					<td title="${partner.createTime}">
						<s:if test="%{#partner.createTime != null}"> 
							<s:text name="format.date_time">
								<s:param value="#partner.createTime"></s:param>
							</s:text>
						</s:if>	
					</td>
					<td title="${partner.editTime}">
						<s:if test="%{#partner.editTime != null}"> 
							<s:text name="format.date_time">
								<s:param value="#partner.editTime"></s:param>
							</s:text>
						</s:if>
					</td>
					
					<!--
					<td title="${partner.removeTime}">
						<s:if test="%{#partner.removeTime != null}">
							<s:text name="format.date_time">
								<s:param value="#partner.removeTime"></s:param>
							</s:text>
						</s:if>
					</td>
					<td title="${partner.removeStatus}">
						${partner.removeStatus}
					</td>
					-->
					
					<td>
						<a href="#" onclick='del("${partner.pid}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${partner.pid}")'><s:text name="update"></s:text></a>
					</td>
				</tr>
			</s:iterator>
			<td colspan="100">
				<aldtags:pageTag paraStr="isCommit,T,pageSize,${pageSize}" />
			</td>
		</table>
	</body>
</html>
 	