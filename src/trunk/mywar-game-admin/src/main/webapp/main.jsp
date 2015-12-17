<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.admin.bo.AdminUser"%>
<%@page import="com.system.manager.DataSourceManager"%>
<%@page import="com.system.service.GameWebService"%>
<%@page import="com.system.service.TGameServerService"%>
<%@page import="com.framework.servicemanager.ServiceCacheFactory"%>
<%@page import="com.system.bo.GameWeb"%>
<%@page import="com.system.bo.TGameServer"%>
<%@page import="com.framework.servicemanager.CustomerContextHolder"%>
<%@page import="com.log.service.UserLoginLogService"%>
<%@page import="com.adminTool.service.UserService"%>
<%@page import="com.framework.util.DateUtil"%>
<%@page import="com.framework.common.SystemStatsDate"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.framework.common.MD5"%>
<%@page import="com.adminTool.constant.AdminToolCMDCode"%>
<%@page import="com.framework.server.msg.Msg"%>
<%@page import="com.framework.server.msg.MsgBuilder"%>
<%@page import="com.framework.client.http.HttpServersBridge"%>
<%@page import="com.framework.common.CommomMsgBody"%>
<%@page import="com.adminTool.msgbody.ResGetOnlineUserAmount"%>
<%@page import="com.log.service.UserRegLogService"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<TITLE><s:text name="mainJsp.title"></s:text></TITLE>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<style>
			.main_left {
				table-layout: auto;
				background: url(skins/images/left_bg.gif)
			}
			
			.main_left_top {
				background: url(images/left_menu_bg.gif);
				padding-top: 2px !important;
				padding-top: 5px;
			}
			
			.main_left_title {
				text-align: left;
				padding-left: 15px;
				font-size: 14px;
				font-weight: bold;
				color: #fff;
			}
			
			.left_iframe {
				HEIGHT: 92%;
				VISIBILITY: inherit;
				WIDTH: 180px;
				background: transparent;
			}
			
			.main_iframe {
				HEIGHT: 92%;
				VISIBILITY: inherit;
				WIDTH: 100%;
				Z-INDEX: 1
			}
			
			table {
				font-size: 12px;
				font-family: tahoma, 宋体, fantasy;
			}
			
			td {
				font-size: 12px;
				font-family: tahoma, 宋体, fantasy;
			}
		</style>
		<script language="javascript" src="js/ajax.js"></script>
		<script language="javascript" src="js/mask.js"></script>
		
		<SCRIPT>
			function setFrmright() {				
				var servergroupSelect = document.getElementById("servergroup");
				document.getElementById("frmright").src = "module/system/allServerInfoList";
				
				
				//打开main.jsp的时候如果选择了服务器，则主界面是世界总览信息，否则的话所有服务器信息列表。
				//if (servergroupSelect.value == "") {
				//	document.getElementById("frmright").src = "module/system/allServerInfoList";
				//} else {
				//	document.getElementById("frmright").src = "module/adminTool/getGlobalInfo";
				//}
			}
			
			function getOnlineUserAmount(){
				var servergroupSelect = document.getElementById("servergroup");
				if (servergroupSelect.value == "") {
					return;
				}
				var text = '<font color="#FF0000"><s:text name="mainJsp.onlineUserAmount"></s:text><s:text name="colon"></s:text></font>';
				var onlineUserAmountTd = document.getElementById("onlineUserAmount");
				//改变图片
				var html = "";
				html = text + '&nbsp;<img src="images/wait.gif" onclick="getOnlineUserAmount()" alt="<s:text name="mainJsp.refreshOnlineUserAmount"></s:text>" title="<s:text name="mainJsp.refreshOnlineUserAmount"></s:text>"/>';
				onlineUserAmountTd.innerHTML = html;
				
				var ajaxobj = new Ajax();
				ajaxobj.url = "module/adminTool/getOnlineUserAmount";
				ajaxobj.send();
				ajaxobj.callback = function() {
					var responseMsg =  eval('(' + ajaxobj.gettext() + ')');
					//alert("responseMsg=="+responseMsg);
					if (responseMsg.erroDescrip == null || responseMsg.erroDescrip == "") {
						html = text + '<a href="module/adminTool/getOnlineUserInfoList" target="frmright">'+responseMsg.onlineAmount+'</a>&nbsp;<img src="images/ref.gif" onclick="getOnlineUserAmount()" alt="<s:text name="mainJsp.refreshOnlineUserAmount"></s:text>" title="<s:text name="mainJsp.refreshOnlineUserAmount"></s:text>"/>';
						onlineUserAmountTd.innerHTML = html;
					} else {
						html = text + '&nbsp;<img src="images/ref.gif" onclick="getOnlineUserAmount()" alt="<s:text name="mainJsp.refreshOnlineUserAmount"></s:text>" title="<s:text name="mainJsp.refreshOnlineUserAmount"></s:text>"/>';
						onlineUserAmountTd.innerHTML = html;
						alert(responseMsg.erroDescrip);
						removeCoverDiv();
					}
				}
			}
			
			function changeSelect(gameWebSelect){
				var gameWebSid = gameWebSelect.value;
				var obj = document.getElementById('servergroup');
				obj.options.length = 0;				
				
				if (gameWebSid == 0) {
					obj.add(new Option('<s:text name="pleaseSelect"></s:text>', 0));
					return;
				}
				
				obj.add(new Option('<s:text name="pleaseSelect"></s:text>', 0));
				var objArr = document.getElementById('serverArr');
				for (var i = 0; i < objArr.options.length; i++) {
					var sid = objArr[i].value;
					// alert(document.getElementById(sid).value);
					if (document.getElementById(sid).value == gameWebSid) {
						obj.add(new Option(objArr[i].text, objArr[i].value));
					}
				}				
			}
			
			function ChangeServer(serverNum) {
				//alert("选择服务器 "+serverNum);
				//var servergroupSelect = document.getElementById("servergroup");
				//var serverNum = servergroupSelect.options[i].value;
				var ajaxobj = new Ajax();
				ajaxobj.url = "module/system/changeServer?serverNum=" + serverNum;
				ajaxobj.callback = function() {
					var responseMsg = ajaxobj.gettext();
					if (responseMsg.erroCodeNum == undefined) {
						location.href = "main.jsp";
					} else {
						return;
					}
				}
				ajaxobj.send();
			}

			function DvMenuCls() {
				var MenuHides = new Array();
				this.Show = function(obj, depth) {
					var childNode = this.GetChildNode(obj);
					if (!childNode) {
						return;
					}
					if (typeof (MenuHides[depth]) == "object") {
						this.closediv(MenuHides[depth]);
						MenuHides[depth] = '';
					}
					;
					if (depth > 0) {
						if (childNode.parentNode.offsetWidth > 0) {
							childNode.style.left = childNode.parentNode.offsetWidth + 'px';
		
						} else {
							childNode.style.left = '100px';
						}
						;
						childNode.style.top = '-2px';
					}
					;
					childNode.style.display = 'block';
					MenuHides[depth] = childNode;
				};
				this.closediv = function(obj) {
					if (typeof (obj) == "object") {
						if (obj.style.display != 'none') {
							obj.style.display = 'none';
						}
					}
				}
				this.Hide = function(depth) {
					var i = 0;
					if (depth > 0) {
						i = depth
					}
					;
					while (MenuHides[i] != null && MenuHides[i] != '') {
						this.closediv(MenuHides[i]);
						MenuHides[i] = '';
						i++;
					}
					;
				};
		
				this.Clear = function() {
					for ( var i = 0; i < MenuHides.length; i++) {
						if (MenuHides[i] != null && MenuHides[i] != '') {
							MenuHides[i].style.display = 'none';
							MenuHides[i] = '';
						}
					}
				};
		
				this.GetChildNode = function(submenu) {
					for ( var i = 0; i < submenu.childNodes.length; i++) {
						if (submenu.childNodes[i].nodeName.toLowerCase() == "div") {
							var obj = submenu.childNodes[i];
							break;
						}
					}
					return obj;
				};
			}
			var status = 1;
		
			var Menus = new DvMenuCls();
		
			document.onclick = Menus.Clear;
			function switchSysBar() {
				var switchPoint = document.getElementById("switchPoint");
				if (1 == window.status) {
					window.status = 0;
					switchPoint.innerHTML = '<img src="images/left.gif" alt="" />';
					document.all("frmTitle").style.display = "none"
				} else {
					window.status = 1;
					switchPoint.innerHTML = '<img src="images/right.gif" alt="" />';
					document.all("frmTitle").style.display = ""
				}
			}
			//管理员退出登录提示--------------------------------------------------------------------------
			function AdminOut() {
				if (confirm('<s:text name="mainJsp.quitConfirm"></s:text>'))
					location.replace("default.jsp")
			}
		</SCRIPT>
	</head>
	<body style="margin: 0px" onload="setFrmright(),getOnlineUserAmount()">
		<table border=0 cellpadding=0 cellspacing=0 height="100%" width="100%" style="background: #C3DAF9;">
			<tbody>
				<tr>
					<td height="58" colspan="3">
<!--					顶端选项卡	-->
						<iframe frameborder="0" id="top" name="top" scrolling="no" src="top.jsp" style="height: 100%; visibility: inherit; width: 100%;"></iframe>
					</td>
				</tr>
				<tr>
					<td height="30" colspan="3">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr height="32">
								<td background="images/bg2.gif" style="text-align: left; color: #135294;">
									<font color="#FF0000">
										<%
										  	AdminUser adminUser = (AdminUser)request.getSession().getAttribute("aldadmin");
										  	
										  	GameWebService gameWebService = ServiceCacheFactory.getServiceCache().getService(GameWebService.class);
										  	List<GameWeb> gameWebList = gameWebService.findGameWebList();
										  	
										  	TGameServerService gameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
										  	List<TGameServer> list = gameServerService.findTGameServerList();
										  	
										  	//第几个服务器
										  //	Integer num = null;
										  	Integer activeUserAmount = null;
										  	Integer totalRegisterNum = null;
										  	Date openDate = null;
										  	String systemNum = null;
											if (adminUser != null) {
										  		systemNum = adminUser.getExp1();
										  		
											  	out.print(adminUser.getAdminName());
											  	//out.print(adminUser.getExp1());
											  	//String checkNum  = request.getParameter("checkNum");
											  	//if (checkNum != null && !checkNum.equals("")){
												//	num = Integer.parseInt(checkNum.toString());
											  //	}
												
										  		//设置系统编号 线程变量
												if(systemNum!=null&&!systemNum.equals("")){
													CustomerContextHolder.setSystemNum(Integer.valueOf(systemNum));
													UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
													UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
													try{
														activeUserAmount = userLoginLogService.findNowActiveUserAmount();
														totalRegisterNum = userRegLogService.findTotalRegUserNum();
													} catch(Exception e){
										%>
   											<script>
												alert("发生错误：<%=e.getMessage()%>\n请确认服务器的数据库是否可用！");
											</script>
										<%
													}
												}
										  	}
									 	%>
									 </font>
									<s:text name="mainJsp.welcome"></s:text>|
									<a href="module/system/allServerInfoList" target="frmright"><s:text name="mainJsp.main"></s:text></a> |
<!--									<a onclick="setFrmright()"><s:text name="mainJsp.main"></s:text></a> |-->
									<a href="javascript:AdminOut()" target=_top><s:text name="mainJsp.quit"></s:text></a>
								</td>
<!--							在线人数	-->
								<td id="onlineUserAmount" background="images/bg2.gif" style="text-align: left; color: #135294;">
									<font color="#FF0000"><s:text name="mainJsp.onlineUserAmount"></s:text><s:text name="colon"></s:text></font>
									<img src="images/ref.gif" alt=""/>
								</td>
<!--							注册总人数	
								<td background="images/bg2.gif" style="text-align: left; color: #135294;">
									<font color="#FF0000"><s:text name="mainJsp.totalRegisterNum"></s:text><s:text name="colon"></s:text></font>
									<%
										out.print(totalRegisterNum==null?"":totalRegisterNum);
									%>
								</td>
-->
<!--							活跃人数	
								<td background="images/bg2.gif" style="text-align: left; color: #135294;">
									<font color="#FF0000"><s:text name="mainJsp.activeUserAmount"></s:text><s:text name="colon"></s:text></font>
									<%
										out.print(activeUserAmount==null?"":activeUserAmount);
									%>
								</td>
-->
<!--							选择服务器	-->
								<td background="images/bg2.gif" style="text-align: left; color: #135294;">
									<s:text name="mainJsp.chooseServer"></s:text><s:text name="colon"></s:text>
									<%
										if(systemNum == null || systemNum.equals("")){
									%>
										<select name="gameweb" id="gameweb" onchange="changeSelect(this)" class="select">
											<option value="0"><s:text name="pleaseSelect"></s:text></option>
											<%
											  	for (int i = 0; i < gameWebList.size(); i++) {
											  	  	GameWeb gameweb = gameWebList.get(i);
										  	%>
													<option value="<%=gameweb.getServerId()%>" >
														<%=gameweb.getServerName()%>
													</option>
											<%}%>
										</select>
									
									<select name="servergroup" id="servergroup" onchange="ChangeServer(this.value)" class="select">
										<option value=""><s:text name="pleaseSelect"></s:text></option>
									</select>
								<%} else {%>
									<select name="gameweb" id="gameweb" onchange="changeSelect(this)" class="select">
											<option value="0"><s:text name="pleaseSelect"></s:text></option>
											<%
												int gameWebId = 0;
												for (TGameServer gameServer : list) {
													if (systemNum != null && systemNum.equals(gameServer.getServerId() + "")) {
														gameWebId = gameServer.getGameWebServerId();
														break;
													}
												}
											
											  	for (int i = 0; i < gameWebList.size(); i++) {
											  	  	GameWeb gameweb = gameWebList.get(i);
										  	%>
													<option value="<%=gameweb.getServerId()%>"
													 <%
													 	if (gameWebId == gameweb.getServerId()) {
													 %>
													 	selected=selected
													 <%	} %>
													 >
														<%=gameweb.getServerName()%>
													</option>
											<%}%>
										</select>
									
									<select name="servergroup" id="servergroup" onchange="ChangeServer(this.value)" class="select">
										<%
											for (int i = 0; i < list.size(); i++) {
												TGameServer gameServer = list.get(i);
												if (gameWebId == gameServer.getGameWebServerId()) {
										%>
													<option value="<%=gameServer.getServerId()%>" 
													<%
														
														if (systemNum != null && systemNum.equals(gameServer.getServerId() + "")) {										
													%>	
														selected=selected
														<%}%>									
													><%=gameServer.getServerAlias()%>
													</option>
												<%}%>
										<%}%>						
										
										<option value=""><s:text name="pleaseSelect"></s:text></option>				
									</select>
								<%}%>
									
									<select name="serverArr" id="serverArr" style="display:none;" class="select">
										<%
											for (int i = 0; i < list.size(); i++) {
												TGameServer gameServer = list.get(i);
										%>
										<option value="<%=gameServer.getServerId()%>" >
											<%=gameServer.getServerAlias()%>
										</option>
										<%}%>
									</select>
									
									<%
										for (TGameServer gameServer : list) {
									%>
										<input type="hidden" id="<%=gameServer.getServerId()%>" name="<%=gameServer.getServerId()%>" value="<%=gameServer.getGameWebServerId()%>"/>
									<%}%>
									
								</td>
<!--							开服日期	-->
								<td background="images/bg2.gif" style="text-align: left; color: #135294;">
									<s:text name="serverOpernTime"></s:text>：
									<%
										if(openDate != null){
											SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
											out.print(sdf.format(openDate));
										}
									%>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center" id="frmTitle" valign="top" style="background: #c9defa">
<!--					左侧边栏	-->
						<iframe frameborder="0" id="frmleft" name="frmleft" scrolling="yes" src="left.jsp" style="height: 100%; visibility: inherit; width: 200px; background: url(images/leftop.gif) no-repeat" allowtransparency="true"></iframe>
					</td>
					<td style="width: 0px;" valign="middle">
<!--					打开、关闭侧边栏的工具条	-->
						<div onClick=switchSysBar();>
							<span class="navpoint" id="switchPoint" title='<s:text name="mainJsp.closeOrOpenLeftJsp"></s:text>'><img src="images/right.gif" alt="" /></span>
						</div>
					</td>
					<td style="width: 100%" valign="top">
<!--					主界面	-->
						<iframe frameborder="0" id="frmright" name="frmright" scrolling="yes" style="height: 100%; visibility: inherit; width: 100%; z-index: 1"></iframe>
					</td>
				</tr>
				<tr>
					<td height="30" colspan="3">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" style="background: url(images/botbg.gif)">
							<tr height="32">
								<td style="padding-left: 30px; font-family: arial; font-size: 11px;">
									Copyright Right 2006-2009 dogdog WebSite
								</td>
								<td style="text-align: right; color: #91B1C6;"></td>
								<td style="text-align: right; color: #135294; padding-right: 20px;"><br><br></td>
							</tr>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
		<%
			if (adminUser == null) {
		  		response.sendRedirect("default.jsp");
		  		return;
		  	}
		 %>
	</body>
</html>