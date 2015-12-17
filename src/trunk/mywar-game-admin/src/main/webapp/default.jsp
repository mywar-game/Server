<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<title><s:text name="defaultJsp.title"></s:text></title>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<LINK href="css/style_left.css" type=text/css rel=stylesheet>
		<script language="javascript" src="js/ajax.js"></script>
		<SCRIPT type=text/javascript>
		    <!--
		    if(self!=top){top.location=self.location;} 
		    function ShowSoftKeyboard(obj)
		    {
		        if((typeof(CheckLoaded) == "function"))
		        {
		            password1 = obj;
		            showkeyboard();
		            Calc.password.value = '';
		        }
		        else
		        {
		            return false;
		        }
		    }
		    // -->
		    function logins(){
			    var adminName=document.login.adminName.value;
			    var adminPassword=document.login.adminPassword.value;
			    var randString = document.login.randString.value;
			    if(adminName==''||adminName==null){
			    	alert('<s:text name="adminNameEmpty"></s:text>');
			    	return;
			    }
			    if(adminPassword==''||adminPassword==null){
				    alert('<s:text name="adminPasswordEmpty"></s:text>');
				    return;
			    }
			    if(randString==''||randString==null){
				    alert('<s:text name="randStringEmpty"></s:text>');
				    return;
			    }
			    var ajaxobj = new Ajax();
			    ajaxobj.url="adminlogin?adminName="+adminName+"&adminPassword="+adminPassword+"&randString="+randString;
			    ajaxobj.callback=function(){
				    var responseMsg = eval('(' + ajaxobj.gettext() + ')');
				    if(responseMsg.erroCodeNum!=0){
					    alert(responseMsg.erroDescrip);
					    return;
				    }else{
				    	location.href='main.jsp';
				    }
			    }
			    ajaxobj.send();
		    }
    	</SCRIPT>

		<SCRIPT src="js/softkeyboard.js" type=text/javascript></SCRIPT>

		<META content="MSHTML 6.00.6000.16809" name=GENERATOR>
	</HEAD>

	<BODY id=loginbody>
		<form name="login" method="post" target="_parent" onSubmit="return false;">
			<DIV id=adminboxall>
				<DIV class=adminboxtop></DIV>
				<DIV id=adminboxmain>
					<div style="margin-left: 380px; margin-top: 5px;">
						<INPUT name="IbtnEnter" id="IbtnEnter2" type=image src="images/admin_menu.gif" onClick="logins();" style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; WIDTH: 76px; HEIGHT: 26px; BORDER-RIGHT-WIDTH: 0px" />
					</div>
				</DIV>
				<DIV class=adminboxbottom>
					<DIV id=login>
						<UL>
							<LI class=text>
								<s:text name="adminName"></s:text><s:text name="colon"></s:text>
								<BR>
								<DIV class=box1>
									<INPUT class=boxcontent id=adminName style="FONT-FAMILY: 宋体" maxLength=20 name=adminName>
								</DIV>
							</LI>
							<LI class=text>
								<s:text name="adminPassword"></s:text><s:text name="colon"></s:text>
								<BR>
								<DIV class=box2>
									<INPUT class=boxcontent id=adminPassword type=password maxLength=20 name=adminPassword>
								</DIV>
							</LI>
							<LI class=textCode>
								<s:text name="randString"></s:text><s:text name="colon"></s:text>
								<BR>
								<DIV class=box4>

									<INPUT class=boxcontent2 id=randString style="IME-MODE: disabled" maxLength=20 name=randString>

									<img src="module/common/VerifyCode.jsp" align="absmiddle" title="<s:text name='clickRefreshRandString'></s:text>" onclick="this.src='module/common/VerifyCode.jsp?'+Math.random()">
								</DIV>
							</LI>
						</UL>
					</DIV>
				</DIV>
				<DIV class=clearbox></DIV>
			</DIV>
			<SPAN id=ValrUserName style="DISPLAY: none; COLOR: red"></SPAN>
			<SPAN id=ValrPassword style="DISPLAY: none; COLOR: red"></SPAN>
			<SPAN id=ValrAdminValidateCode style="DISPLAY: none; COLOR: red"></SPAN>
			<SPAN id=ValrValidateCode style="DISPLAY: none; COLOR: red"></SPAN>
			<DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV>
		</FORM>
	</BODY>
</HTML>