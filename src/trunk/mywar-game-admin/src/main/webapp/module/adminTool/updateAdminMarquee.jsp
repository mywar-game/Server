<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.system.bo.TGameServer"%>
<%@page import="com.adminTool.bo.AdminMarquee"%>
<%@page import="com.adminTool.bo.Partner"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateAdminMarqueeJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">	
	<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
	<script type="text/javascript" >	
	
		function checkedFun(checkId) {
			var isCheck = checkId.checked;
			var gid = checkId.value;
			var sidArr = new Array();
			
			var inputs = document.getElementsByTagName("input");
			for (var i = 0; i < inputs.length; i++) {
				var obj = inputs[i];
				if (obj.type == 'checkbox') {
					sidArr.push(obj.id);
				}
			}
			
			for (var j = 0; j < sidArr.length; j++) {
				var sid = sidArr[j];
				var id = sid.split('_');
				
				if (id[0] != "" && id[0] == gid) {
					document.getElementById(sid).checked = isCheck;
				}
			}
			
		}
		
	</script>
	<body>
		&nbsp;
		<form action="updateAdminMarquee?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="updateAdminMarqueeJsp.title"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminMarquee.content"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<textarea size="45" name="content" rows="4" cols="60" value="" />${adminMarquee.content}</textarea>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminMarquee.startTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="hidden" id="id" name="id" value="${adminMarquee.id}">
						<input type="hidden" id="createdTime" name="createdTime" value="${adminMarquee.createdTime}">
						<input type="hidden" id="playTime" name="playTime" value="${adminMarquee.playTime}">
						<input type="text" id="startTime" value="${adminMarquee.startTime}" name="startTime" class="Wdate" style="width:160px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', startDate:'%y-%M-%d 00:00:00', onpicked:function(){endTime.focus();}})"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminMarquee.endTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="text" id="endTime" name="endTime" value="${adminMarquee.endTime}" class="Wdate" style="width:160px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', startDate:'%y-%M-%d 00:00:00', minDate:'#F{$dp.$D(\'startTime\')}'})"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminMarquee.playInterval"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="text" id="playInterval" value="${adminMarquee.playInterval}" name="playInterval" value=""/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminMarquee.loopTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<input type="text" id="loopTime" name="loopTime" value="${adminMarquee.loopTime}" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminMarquee.isUse"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="4">
						<select id="isUse" name="isUse" class="select" >
							<s:generator separator="," val="%{getText('adminMarquee.isUse_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="adminMarquee.isUse == #str">selected=selected</s:if>>
										<s:text name="%{'adminMarquee.isUse_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
				</tr>								
				<tr>
					<td colspan="5">
						<s:text name="adminMarquee.serverIds"></s:text><s:text name="colon"></s:text>
					</td>					
				</tr>								
				<%
					Map<GameWeb, List<TGameServer>> map = (Map<GameWeb, List<TGameServer>>) request.getAttribute("map");
					Map<GameWeb, Map<String, String>> sidMap = (Map<GameWeb, Map<String, String>>) request.getAttribute("sidMap");
					// 排序
					ArrayList<Map.Entry<GameWeb, List<TGameServer>>> mappingList = new ArrayList<Map.Entry<GameWeb, List<TGameServer>>>(map.entrySet());
  					// 通过比较器实现比较排序
  					Collections.sort(mappingList, new Comparator<Map.Entry<GameWeb, List<TGameServer>>>(){
   						public int compare(Map.Entry<GameWeb, List<TGameServer>> mapping1,Map.Entry<GameWeb, List<TGameServer>> mapping2){
    						return mapping1.getKey().getServerId().compareTo(mapping2.getKey().getServerId());
   						}
  					}); 
				
  					for(Map.Entry<GameWeb, List<TGameServer>> mapping : mappingList) { 
  						//List<VersionManagerApk> apkList = mapping.getValue();
  						GameWeb gameWeb = mapping.getKey(); 
					//for (GameWeb gameWeb : map.keySet()) {
				%>
						<tr><td colspan="5"><%=gameWeb.getServerName()%><s:text name="colon"></s:text><input type="checkbox" onclick="checkedFun(this)" value="<%=gameWeb.getServerId()%>" name="gServerId" /></td></tr>
						<tr>
						<%
							List<TGameServer> serverList = map.get(gameWeb);
							Map<String, String> serverIdMap = sidMap.get(gameWeb);
							
							int size = serverList.size();
							for (TGameServer server : serverList) {
						%>
								<td><input type="checkbox" value="<%=gameWeb.getServerId()%>_<%=server.getServerId()%>"
								<%
									if (serverIdMap.containsKey(server.getServerId().toString())) {
										%>checked="true"<%
									}								
								%>
								 id="<%=gameWeb.getServerId()%>_<%=server.getServerId()%>" name="serverIdArr" /><%=server.getServerAlias()%></td>
								<%if (size % 5 == 0) {%>
									</tr>
								<%}%>
						<%	}
						%>	
						</tr>
				<%}%>
				<tr>
					<td>
						<s:text name="adminMarquee.partnerIds"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						<% List<Partner> partnerList = (ArrayList<Partner>) request.getAttribute("partnerList"); 
						AdminMarquee adminMarquee = (AdminMarquee) request.getAttribute("adminMarquee"); 
						%>
						<select name="partnerIds" class="select">
							<option value="0"><s:text name="versionManagerApk.allPartner"></s:text>
							<% for (Partner p : partnerList) {%>
								<option value="<%=p.getPid()%>"
									<% if (p.getPid().toString().equals(adminMarquee.getPartnerIds())) {%>
										selected="true"
									<%}%>
								><%=p.getPName()%>
							<%}%>
						</select>
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
				content:{
					required:true,
					maxlength:1000
				},
				startTime:{
					required:true
				},
				endTime:{
					required:true
				},
				playInterval:{
					required:true,
					digits:true
				},
				isUse:{
					required:true
				},
				serverIdArr:{
					required:true
				},
				loopTime:{
					required:true
				}
			}		
		});	
	});
</script>
