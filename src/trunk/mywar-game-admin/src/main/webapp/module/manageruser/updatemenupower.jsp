<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="updatemenupower.title"><s:param><font color='red'>${adminUser.adminName}</font></s:param></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script>
			var changeMenuPower = function() {
				// add 
				var cbx = document.getElementsByName("powerList");
  				var cbxValue = "";
  				var powerListChecked = "";
  				var powerListUnChecked = "";
  				var indexChecked = 0;
  				var indexUnChecked = 0;
  				for (var i = 0; i < cbx.length; i++) {
  					if (cbx[i].checked) {
    					powerListChecked += cbx[i].value;
    					powerListChecked += ",";
    				} else {
    					powerListUnChecked += cbx[i].value;
    					powerListUnChecked += ",";
    				}
				}
				var ajaxObj = new Ajax();
				ajaxObj.url = "updatemenupower?isCommit=T&Id=${Id}&powerListChecked=" + powerListChecked + "&powerListUnChecked=" + powerListUnChecked;
				ajaxObj.callback = function() {
					alert("修改成功");
					window.location.href="adminuserlist";
				}
				ajaxObj.send();
			}
    		
		</script>
	</head>
	<body>
		<form action="updatemenupower?isCommit=T&Id=${Id}" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="6">
						<s:text name="updatemenupower.title"><s:param><font color='red'>${adminUser.adminName}</font></s:param></s:text>
					</td>
				</tr>
			</table>
			${treeStr}
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr>
					<td colspan="6" align="center">
						<input type="button" value="<s:text name='submit'></s:text>" class="button" onclick='changeMenuPower();'/>
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>

	</body>
</html>
