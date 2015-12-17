<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.adminTool.bo.Partner"%>
<%@page import="com.adminTool.bo.SystemGoldSet"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>SystemGoldSetList.jsp</title>
	
	<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script>
			var success = '${isCommit}';
			if (success == 'true') {
				alert("修改成功！");
				window.location.href="systemGoldSetList?isCommit=false";
			}
			
			function syn() {
				var hight = (screen.height - 300) / 2.8;
				var width = (screen.width - 500) / 2;
				var str = window.showModalDialog("chooseServer", "", "dialogLeft=" + width
					+ "; dialogTop=" + hight
					+ "; dialogWidth=500px; dialogHeight=300px; location=no");
				
					
				if (str == null || str == '') {
					return;
				}

				// 获取到的道具
				var strArr = str.split(',');
				if (confirm("你确定要同步该数据吗? " + "(服务器:" + str + ")")) {		
					
					window.location.href="synSystemGoldSetList?serverIds=" + str;
				}	
			}
		</script>
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
</head>

<body>
	<input type="button" value='<s:text name="systemGoldSetListJsp.refresh"></s:text>' class="button" onclick="reflashAll()" />
	<input type="button" value='<s:text name="systemGoldSetListJsp.syn"></s:text>' class="button" onclick="syn()" />
	<form action="systemGoldSetList?isCommit=true" method="post">
		<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="24" align="center">
					<center>
						<s:text name="systemGoldSetListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			
			<tr>
				<td><s:text name="systemGoldSetListJsp.goldId"></s:text></td>
				<td><s:text name="systemGoldSetListJsp.partnerId"></s:text></td>
				<td><s:text name="systemGoldSetListJsp.money"></s:text></td>
				<td><s:text name="systemGoldSetListJsp.gold"></s:text></td>
				<td><s:text name="systemGoldSetListJsp.type"></s:text></td>
				<td><s:text name="systemGoldSetListJsp.startTime"></s:text></td>
				<td><s:text name="systemGoldSetListJsp.endTime"></s:text></td>
				<td><s:text name="systemGoldSetListJsp.title"></s:text></td>
				<td><s:text name="systemGoldSetListJsp.desc"></s:text></td>
			</tr>
			<%
			List<SystemGoldSet> systemGoldSetList = (ArrayList<SystemGoldSet>) request.getAttribute("systemGoldSetList");
			List<Partner> partnerList = (ArrayList<Partner>) request.getAttribute("partnerList"); 
			for (SystemGoldSet goldSet: systemGoldSetList) {%>
				<tr>
					<td>
						<input type="text" size="3" value="<%=goldSet.getSystemGoldSetId()%>" readonly=readonly = name="systemGoldSetIdArr"/>
					</td>
					<td>						
						<select name="partnerIdArr" class="select">
							<option value="0" >全匹配</option>
							<% for (Partner p : partnerList) {%>
								<option value="<%=p.getPid()%>"
								<% if (p.getPid().toString().equals(goldSet.getPartnerId())) { %>
									selected="true"
								<%}%>
								><%=p.getPName()%></option>
							<%}%>
						</select>
					</td>
					<td>
						<input type="text" value="<%=goldSet.getMoney()%>" name="moneyArr"/>
					</td>
					<td>
						<input type="text" value="<%=goldSet.getGold()%>" name="goldArr"/>
					</td>
					<td>
						<input type="text" size="3" value="<%=goldSet.getType()%>" name="typeArr"/>
					</td>
					<td>
						<input type="text" value="<%=goldSet.getStartTime()%>" name="startTimeArr" class="Wdate" style="width:160px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', startDate:'%y-%M-%d 00:00:00', onpicked:function(){endTime.focus();}})"/>
					</td>
					
					<td>
						<input type="text" value="<%=goldSet.getEndTime()%>" name="endTimeArr" class="Wdate"  style="width:160px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', startDate:'%y-%M-%d 00:00:00', onpicked:function(){endTime.focus();}})"/>
					</td>
					
					<td>
						<input type="text" value="<%=goldSet.getTitle()%>" name="titleArr"/>
					</td>
					<td>
						<input type="text" value="<%=goldSet.getDescription()%>" name="descriptionArr"/>
					</td>					
				</tr>
			<% }%>
			<tr>
				<td>
					<input type="submit" onsubmit="return sureSubmit();" value="<s:text name='systemGoldSetListJsp.modify'></s:text>"/>
				</td>
			</tr>
		</table>
	</form>
	<script src="../../js/jquery/jquery.validate.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("form").validate({
			rules:{
				systemGoldSetIdArr:{
					required:true
				},
				partnerIdArr:{
					required:true
				},
				moneyArr:{
					required:true
				},
				goldArr:{
					required:true
				},
				type:{
					required:true
				},
				titleArr:{
					required:true
				},
				descriptionArr:{
					required:true
				}
			}
		});
	});
	var sureSubmit = function(obj) {
		var inputArr = document.getElementsByTagName("input");
		var mark = "ok";
		for (var i = 0; i < inputArr.length; i++) {
			var tempValue = inputArr[i].value;
			if (tempValue == "") {
				alert("请填完整信息!");
				mark = "no";
				break;
			}
		}
		if (mark == "no") {
			return false;
		}
		if(window.confirm("<s:text name='updateConfirm'></s:text>")) {
				return true;
		} else {
				return false;
		}
	}
	</script>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script>
		function reflashAll(freshClassName) {
			var ajaxobj = new Ajax();
			ajaxobj.url = "reflashActivityList?freshClassName=" + "SystemGoldSetDaoCacheImpl";
			ajaxobj.callback=function(){
				var responseMsg = eval('(' + ajaxobj.gettext() + ')');
				if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="systemGoldSetListJsp.refalshOneActivity"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="systemGoldSetListJsp.refalshOneActivity"></s:text></s:param></s:text>');
				}
			}
			ajaxobj.send();	
		}
	</script>
</body>

