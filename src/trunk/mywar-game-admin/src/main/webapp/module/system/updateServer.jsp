<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateServerJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	
	<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script src="../../js/jquery/jquery.min.js"></script>
		<script type="text/javascript">
		function isSubmit(){
			if(window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
		}
		
		function selectServer(serverSelect) {
			var serverName = serverSelect.value;
			
			document.getElementById('aliaServerId').value = serverName;
		}
		
	</script>
	<body>
		&nbsp;
		<form action="updateServer?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="updateServerJsp.title"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="gameWebId" value="${gameWebId}" />
						<s:text name="gameWebServer.serverId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="hidden" name="oldServerId" value="${server.serverId}" />
						<input type="hidden" name="oldPartnerId" value="${server.partenerId}" />
						<input type="hidden" name="oldAliaServerId" value="${server.aliaServerId}" />
						<select name="serverId" onchange="selectServer(this)" class="select">
							<s:iterator var="sid" value="serverIdList">
								<option value="${sid}" <s:if test="server.serverId == #sid">selected=selected</s:if> >
									${sid}
								</option>
							</s:iterator> 
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="server.serverName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="serverName" value="${server.serverName}" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="server.status"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="status" class="select">
							<s:generator separator="," val="%{getText('server.status_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="server.status == #str">selected=selected</s:if>>
										<s:text name="%{'server.status_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="server.partenerId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="partenerId" class="select">
							<s:iterator var="partner" value="partnerList">
								<option value="${partner.pid}" <s:if test="server.partenerId == #partner.pid">selected=selected</s:if>>
									${partner.PName}
								</option>
							</s:iterator> 
						</select>						
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="server.openTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="openTime" value="${server.openTime}" name="openTime" class="Wdate" style="width:160px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="server.aliaServerId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="aliaServerId" name="aliaServerId" value="${server.aliaServerId}" />
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
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
					required: true,
					maxlength: 10
				},
				serverName:{
					required: true,
					maxlength: 64
				},
				status:{
					required: true
				},
				partenerId:{
					required: true
				},
				openTime:{
					required: true
				},
				aliaServerId:{
					required: true,
					maxlength: 10
				}
			}		
		});	
	});
</script>