<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.adminTool.bo.Activity"%>
<%@page import="com.adminTool.constant.ActivityConstant"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="activityListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">	
		function reflashServerCache() {
			if (confirm("是否需要刷新多个服务器?")) {
				var hight = (screen.height - 300) / 2.8;
				var width = (screen.width - 500) / 2;
				var str = window.showModalDialog("chooseServer", "", "dialogLeft=" + width
					+ "; dialogTop=" + hight
					+ "; dialogWidth=500px; dialogHeight=300px; location=no");
				if (str == null || str == '') {
					return;
				}
				var ajaxobj = new Ajax();
				ajaxobj.url = "reflashActivityList?number=" + Math.random() + "&serverIds=" + str;
				ajaxobj.callback=function(){
					var responseMsg = eval('(' + ajaxobj.gettext() + ')');
					if (responseMsg.erroCodeNum == 0) {
						alert('<s:text name="action.success"><s:param><s:text name="activityListJsp.reflashActivity"></s:text></s:param></s:text>');
					} else {
						alert(responseMsg.erroDescrip);
					}
				}
				ajaxobj.send();	
			}else{
				var ajaxobj = new Ajax();
				ajaxobj.url = "reflashActivityList?number=" + Math.random();
				ajaxobj.callback=function(){
					var responseMsg = eval('(' + ajaxobj.gettext() + ')');
					if (responseMsg.erroCodeNum == 0) {
						alert('<s:text name="action.success"><s:param><s:text name="activityListJsp.reflashActivity"></s:text></s:param></s:text>');
					} else {
						alert('<s:text name="action.fail"><s:param><s:text name="activityListJsp.reflashActivity"></s:text></s:param></s:text>');
					}
				}
				ajaxobj.send();	
			}
		}
	
		function del(activityId, activityType) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='activity.activityId'></s:text></s:param><s:param>"+activityId+"</s:param></s:text>(只是修改状态)")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delActivity?activityId=" + activityId + "&activityType=" + activityType;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(activityId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(activityId) {
			window.location.href = "updateActivity?activityId=" + activityId;
		}
	
		function add() {			
			window.location.href = "addActivity";
		}		
		
		// 活动配置
		function config(action, activityId) {
			window.location.href = action + "?activityId=" + activityId;
		}
		
		function refresh(activityId) {
			if (confirm("是否需要刷新多个服务器?")) {
				var hight = (screen.height - 300) / 2.8;
				var width = (screen.width - 500) / 2;
				var str = window.showModalDialog("chooseServer", "", "dialogLeft=" + width
					+ "; dialogTop=" + hight
					+ "; dialogWidth=500px; dialogHeight=300px; location=no");
				if (str == null || str == '') {
					return;
				}
				var ajaxobj = new Ajax();
				ajaxobj.url = "reflashActivityList?activityId=" + activityId + "&serverIds=" + str;
				ajaxobj.callback=function(){
					var responseMsg = eval('(' + ajaxobj.gettext() + ')');
					if (responseMsg.erroCodeNum == 0) {
						alert('<s:text name="action.success"><s:param><s:text name="activityListJsp.refalshOneActivity"></s:text></s:param></s:text>');
					} else {
						alert(responseMsg.erroDescrip);
					}
				}
				ajaxobj.send();
			}else{
				var ajaxobj = new Ajax();
				ajaxobj.url = "reflashActivityList?activityId=" + activityId;
				ajaxobj.callback=function(){
					var responseMsg = eval('(' + ajaxobj.gettext() + ')');
					if (responseMsg.erroCodeNum == 0) {
						alert('<s:text name="action.success"><s:param><s:text name="activityListJsp.refalshOneActivity"></s:text></s:param></s:text>');
					} else {
						alert('<s:text name="action.fail"><s:param><s:text name="activityListJsp.refalshOneActivity"></s:text></s:param></s:text>');
					}
				}
				ajaxobj.send();	
			}
		}
		
		function syn(activityId, activityName) {
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
			if (confirm("你确定要同步该数据吗? (" + activityName + ")" + "(服务器:" + str + ")")) {		
				window.location.href = "synActivityList?activityId=" + activityId + "&serverIds=" + str;
			}	
		}
		var responseMsgFlag = '${responseMsg}';
		
		if (responseMsgFlag == 'success') {
			//alert("同步成功");
			var failIds = '${failIds}';
			var successIds = '${successIds}';
			if (failIds != "") {
				alert("同步失败的服务器包括:" + failIds);
			}
			if (successIds != "") {
				alert("同步成功的服务器包括:" + successIds);
			}
			window.location.href = "activityList";
		} else if (responseMsgFlag == 'fail') {
			alert("还没添加");
			window.location.href = "activityList";
		}
	</script>
	<body>
		<input type="submit" value="<s:text name='addActivityJsp.title'></s:text>" class="button" onclick=add(); />
		<input type="button" value='<s:text name="activityListJsp.reflashActivity"></s:text>' class="button" onclick="reflashServerCache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="activityListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<s:text name="activity.activityId"></s:text>
				</td>
				<td >
					<s:text name="activity.activityShowType"></s:text>
				</td>
				<td>
					<s:text name="activity.activityType"></s:text>
				</td>
				<td>
					<s:text name="activity.activityName"></s:text>
				</td>
				<td>
					<s:text name="activity.activityTitle"></s:text>
				</td>
				<td>
					<s:text name="activity.startTime"></s:text>
				</td>
				<td>
					<s:text name="activity.endTime"></s:text>
				</td>
				<td>
					<s:text name="activity.openWeeks"></s:text>
				</td>
				<td>
					<s:text name="activity.openTime"></s:text>
				</td>
				<td >
					<s:text name="activity.param"></s:text>
				</td>
				<td>
					<s:text name="activity.display"></s:text>
				</td>
				<td>
					<s:text name="activity.status"></s:text>
				</td>
				<td>
					<s:text name="activity.sort"></s:text>
				</td>
				<td>
					<s:text name="activity.config"></s:text>
				</td>
				<td width="35">
					<s:text name="refresh"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
				<td width="35">
					同步
				</td>
			</tr>
			
			<%
				Date now = new Date();
				List<Activity> activityList = (List<Activity>) request.getAttribute("activityList");
				for (Activity activity : activityList) {
					if (activity.getStatus() == 2) {
						continue;
					}
			%>

			<tr id="<%=activity.getActivityId()%>" >
				<td >
					<%	if (activity.getActivityId() != null) { %>
							<%=activity.getActivityId()%>
					<%}%>					
				</td>
				<td >
					<%	if (activity.getActivityShowType() != null) { %>
							<%=activity.getActivityShowType()%>
					<%}%>
				</td>
				<td >
					<%	if (activity.getActivityType() != null) { %>
						<%
							switch (activity.getActivityType()) {
							case 1:
							%><s:text name="activity.activityType_1"></s:text><% 
							break;
							case 2:
							%><s:text name="activity.activityType_2"></s:text><% 
							break;
							case 3:
							%><s:text name="activity.activityType_3"></s:text><% 
							break;
							case 4:
							%><s:text name="activity.activityType_4"></s:text><% 
							break;
							case 5:
							%><s:text name="activity.activityType_5"></s:text><% 
							break;
							case 6:
							%><s:text name="activity.activityType_6"></s:text><% 
							break;
							case 7:
							%><s:text name="activity.activityType_7"></s:text><% 
							break;
							case 8:
							%><s:text name="activity.activityType_8"></s:text><% 
							break;
							case 9:
							%><s:text name="activity.activityType_9"></s:text><% 
							break;
							case 10:
							%><s:text name="activity.activityType_10"></s:text><% 
							break;
							case 11:
							%><s:text name="activity.activityType_11"></s:text><% 
							break;
							case 18:
							%><s:text name="activity.activityType_18"></s:text><% 
							break;
							case 19:
							%><s:text name="activity.activityType_19"></s:text><% 
							break;
							case 21:
							%><s:text name="activity.activityType_21"></s:text><% 
							break;
							case 25:
							%><s:text name="activity.activityType_25"></s:text><% 
							break;
							case 30:
							%><s:text name="activity.activityType_30"></s:text><% 
							break;
							case 31:
							%><s:text name="activity.activityType_31"></s:text><% 
							break;
							case 32:
							%><s:text name="activity.activityType_32"></s:text><% 
							break;
							case 33:
							%><s:text name="activity.activityType_33"></s:text><% 
							break;
							case 34:
							%><s:text name="activity.activityType_34"></s:text><% 
							break;
							case 41:
							%><s:text name="activity.activityType_41"></s:text><% 
							break;
							case 100:
							%><s:text name="activity.activityType_100"></s:text><% 
							break;
							default:
							break;
							}
						%>
					<%}%>
				</td>
				<td >
					<%	if (activity.getActivityName() != null) { %>
							<%=activity.getActivityName()%>
					<%}%>
				</td>
				<td >
					<%	if (activity.getActivityTitle() != null) { %>
							<%=activity.getActivityTitle()%>
					<%}%>
				</td>
				<td >
					<%	if (activity.getStartTime() != null) { %>
							<%=activity.getStartTime()%>
					<%}%>
				<!--
					<s:text name="format.date_ymd">
						<s:param value="#activity.startTime"></s:param>
					</s:text>
				-->
				</td>
				<% if (activity.getEndTime() != null && 
					now.getTime() > activity.getEndTime().getTime()) {%>					
					<td style="color: red;">
				<%} else {%>
					<td >
				<%}%>
					<%	if (activity.getEndTime() != null) { %>
							<%=activity.getEndTime()%>
					<%}%>
				</td>
				<td >
					<%	if (activity.getOpenWeeks() != null) { %>
							<%=activity.getOpenWeeks()%>
					<%}%>
				</td>
				<td>
					<%  if (activity.getOpenTime() != null) { %>
							<%=activity.getOpenTime()%>	
					<%}%>
				</td>
				<td >
					<%	if (activity.getParam() != null) { %>
							<%=activity.getParam()%>
					<%}%>
				</td>
				
				<% if (activity.getDisplay() != null && activity.getDisplay() == 0) {%>
					<td style="color: red;">
				<%} else {%>
					<td>
				<%}%>		
					<%	if (activity.getDisplay() != null) { %>
						<%
							switch (activity.getDisplay()) {
							case 0:
							%><s:text name="activity.display_0"></s:text><% 
							break;
							case 1:
							%><s:text name="activity.display_1"></s:text><% 
							break;
							default:
							break;
							}
						%>
					<%}%>
				</td>
				
				<% if (activity.getStatus() != null && activity.getStatus() == 1) {%>
					<td style="color: red;">
				<% } else {%>
					<td >
				<%}%>
					<%	if (activity.getStatus() != null) { %>
						<%
							switch (activity.getStatus()) {
							case 0:
							%><s:text name="activity.status_0"></s:text><% 
							break;
							case 1:
							%><s:text name="activity.status_1"></s:text><% 
							break;
							default:
							break;
							}
						%>
					<%}%>
				</td>
				<td >
					<%	if (activity.getSort() != null) { %>
							<%=activity.getSort()%>
					<%}%>
				</td>
				<td>
					<%
						switch (activity.getActivityType()) {
							case ActivityConstant.ACTIVITY_TYPE_TOTALPAYREWARD:
						%>					
						<a href="#" onclick='config("totalPayRewardList", "<%=activity.getActivityId()%>")' ><s:text name="activity.config" ></s:text></a>
						<%
							break;
							case ActivityConstant.ACTIVITY_TYPE_ONCEPAYREWARD:
						%>
						<a href="#" onclick='config("oncePayRewardList", "<%=activity.getActivityId()%>")' ><s:text name="activity.config" ></s:text></a>
						<%
							break;
							case ActivityConstant.ACTIVITY_TYPE_TOOLEXCHANGE:
						%>
						<a href="#" onclick='config("toolExchangeList", "<%=activity.getActivityId()%>")' ><s:text name="activity.config" ></s:text></a>
						<%
							break;
							case ActivityConstant.ACTIVITY_TYPE_DAYPAYREWARD:
						%>
						<a href="#" onclick='config("totalDayPayRewardList", "<%=activity.getActivityId()%>")' ><s:text name="activity.config" ></s:text></a>
						<%
							break;
							case ActivityConstant.ACTIVITY_TYPE_DRAWGOODS:
						%>
						<a href="#" onclick='config("activityDraw", "<%=activity.getActivityId()%>")' ><s:text name="activity.config" ></s:text></a>					
						<%
							break;
							case ActivityConstant.ACTIVITY_TYPE_TASK:
						%>
						<a href="#" onclick='config("updateVitality", "<%=activity.getActivityId()%>")' ><s:text name="activity.config" ></s:text></a>
						<%
							break;
							case ActivityConstant.ACTIVITY_TYPE_LOGINDRAW:
						%>					
						<a href="#" onclick='config("systemLoginDrawList", "<%=activity.getActivityId()%>")' ><s:text name="activity.config" ></s:text></a>
						<%		
							break;
							case ActivityConstant.ACTIVITY_TYPE_GIFTBAG:
						%>
						
						<a href="#" onclick='config("updateDropTool", "<%=activity.getActivityId()%>")'><s:text name="activity.config"></s:text></a>
						<%
							break;
							case ActivityConstant.ACTIVITY_TYPE_30LOGINREWARD:
						%>
						<a href="#" onclick='config("update30LoginReward", "<%=activity.getActivityId()%>")'><s:text name="activity.config"></s:text></a>
						<%
							break;
							case ActivityConstant.ACTIVITY_TYPE_7LOGINREWARD:
						%>
						<a href="#" onclick='config("update7LoginReward", "<%=activity.getActivityId()%>")'><s:text name="activity.config"></s:text></a>
						<%
							break;
							case ActivityConstant.ACTIVITY_TYPE_SCRATCH:
						%>
						<a href="#" onclick='config("updateSystemScratchReward", "<%=activity.getActivityId()%>")'><s:text name="activity.config"></s:text></a>
						<%
							break;
							case ActivityConstant.ACTIVITY_TYPE_TOTALCONSUME:
						%>
						<a href="#" onclick='config("updateSystemTotalConsumeReward", "<%=activity.getActivityId()%>")'><s:text name="activity.config"></s:text></a>
						<%
							break;
							case ActivityConstant.ACTIVITY_TYPE_DIAMONDCAT:
						%>
						<a href="#" onclick='config("updateSystemDiamondcat", "<%=activity.getActivityId()%>")'><s:text name="activity.config"></s:text></a>
						<%
							break;
							case ActivityConstant.ACTIVITY_TYPE_XIANSHI:
						%>
						<a href="#" onclick='config("systemXianshiMallList", "<%=activity.getActivityId()%>")'><s:text name="activity.config"></s:text></a>
						<%
							break;
							case ActivityConstant.ACTIVITY_TYPE_DAY_TOTAL_PAY:
						%>
						<a href="#" onclick='config("systemDayTotalPayRewardList", "<%=activity.getActivityId()%>")'><s:text name="activity.config"></s:text></a>
						<%
							break;
							case ActivityConstant.USER_LOGIN_CHARGE:
						%>
						<a href="#" onclick='config("updateAccumLoginReward", "<%=activity.getActivityId()%>")'><s:text name="activity.config"></s:text></a>
						<%
							break;
							case ActivityConstant.ACTIVITY_TYPE_VALENTINE:
						%>
						<a href="#" onclick='config("systemValentineList", "<%=activity.getActivityId()%>")'><s:text name="activity.config"></s:text></a>
						<%
							break;
						}
					%>
				</td>
				<td>
					<a href="#" onclick='refresh("<%=activity.getActivityType()%>")'><s:text name="refresh"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='del("<%=activity.getActivityId()%>", "<%=activity.getActivityType()%>")'><s:text name="delete"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='update("<%=activity.getActivityId()%>")'><s:text name="update"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='syn("<%=activity.getActivityId()%>", "<%=activity.getActivityName()%>")'>同步</a>
				</td>
			</tr>

			<%}%>
			
			<td colspan="100">
				<aldtags:pageTag paraStr="isCommit,T,pageSize,${pageSize}" />
			</td>
		</table>
		<br/>
		说明:<br/>
		1、可重复添加的活动<br/>
		      &nbsp;&nbsp;累积充值模块<br/>
		      &nbsp;&nbsp;单笔充值模块<br/>
              &nbsp;&nbsp;道具兑换<br/>
              &nbsp;&nbsp;充值累积天数模块<br/>
              &nbsp;&nbsp;抽奖(即疯狂的盖伦)<br/>
              &nbsp;&nbsp;限时招募<br/>
              &nbsp;&nbsp;热血呱呱乐<br/>
              &nbsp;&nbsp;累计消费<br/>
              &nbsp;&nbsp;累计登录<br/>
              &nbsp;&nbsp;招财猫<br/><br/>
              &nbsp;&nbsp;<font color="red">注意:以上活动，开启过后，除（疯狂的盖伦外）都不能重复利用，需要重新添加配置新的活动内容，即如果不修改疯狂的盖伦抽奖配置的话，可修改时间进行开启。如要修改请点击活动配置进行。</font>
		<br/><br/>
		2、不可重复添加的活动<br/>
           &nbsp;&nbsp;30日累积登陆<br/>
           &nbsp;&nbsp;7日连续登陆<br/>
                      &nbsp;&nbsp;体力恢复<br/>
                      &nbsp;&nbsp;活跃度<br/>
                      &nbsp;&nbsp;在线奖励<br/>
                      &nbsp;&nbsp;礼包码<br/>
                      &nbsp;&nbsp;首冲<br/>
           &nbsp;&nbsp;N倍开启<br/>
                      &nbsp;&nbsp;登陆抽奖活动(即大富翁)<br/>
                      &nbsp;&nbsp;活动副本<br/>
                      &nbsp;&nbsp;神秘商店<br/>
                      &nbsp;&nbsp;活动副本<br/>
                      &nbsp;&nbsp; 坐享其成<br/>
                      &nbsp;&nbsp;活动副本<br/>
           &nbsp;&nbsp;vip每日领取奖励<br/><br/>
                     &nbsp;&nbsp;<font color="red">注意:以上活动不可重复添加，只能修改相应的配置<br/></font>
		<br/>
		3、同步功能，把当前数据同步到其它服务器数据表。
	</body>
</html>

