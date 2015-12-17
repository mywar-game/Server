<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="sGlobalParamListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		function reflashconstantcache() {
			var ajaxobj = new Ajax();
			ajaxobj.url="reflashConstantCache?cacheType=23&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="sGlobalParamListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="sGlobalParamListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		
		function update(sGlobalParamKey) {
			window.location.href = "updateSGlobalParam?globalKey="+sGlobalParamKey;
		}
	</script>
	<body>
		<input type="button" value='<s:text name="sGlobalParamListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashconstantcache()" />
		<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="7" align="center">
						<center><s:text name="sGlobalParamListJsp.title"></s:text> &nbsp;</center>
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="globalParam.globalKey"></s:text>
					</td>
					<td>
						<s:text name="globalParam.globalValue"></s:text>
					</td>
					<td>
						<s:text name="globalParam.golobalTransportPat"></s:text>
					</td>
					<td>
						<s:text name="globalParam.globalDesc"></s:text>
					</td>
					
					<td>
						<s:text name="update"></s:text>
					</td>
				</tr>
			<s:iterator var="sGlobalParam" value="globalParamList">
				<tr id="${sGlobalParam.globalKey}">
					<td>
							${sGlobalParam.globalKey}
					</td>
					<td>
							${sGlobalParam.globalValue}
					</td>
					<td>	
							${sGlobalParam.golobalTransportPat}
					</td>
					<td>
							${sGlobalParam.globalDesc}
					</td>
					<td>	
						<a href="#" onclick='update("${sGlobalParam.globalKey}")'><s:text name="update"></s:text></a> 
					</td>
					
				</tr>
			</s:iterator>
			<tr>
				<td colspan="22" id="page">
					<aldtags:pageTag/>
				</td>
			</tr>	
		</table>
	</body>
</html>