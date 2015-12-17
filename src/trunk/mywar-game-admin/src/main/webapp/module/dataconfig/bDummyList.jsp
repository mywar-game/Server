<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="bDummyListJsp.title"></s:text></title>

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
			ajaxobj.url="reflashConstantCache?cacheType=12&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="bDummyListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="bDummyListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		
		function del(dummyId) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="dummy.dummyId"></s:text></s:param><s:param>'+dummyId+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delBDummy?dummyId=" + dummyId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(dummyId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(dummyId) {
			window.location.href = "updateBDummy?dummyId=" + dummyId;
		}
	
		function add() {
			window.location.href = "addBDummy";
		}
	</script>
	<body>
		<input type="submit" value="<s:text name="addBDummyJsp.title"></s:text>" class="button" onclick=add(); />
		<input type="button" value='<s:text name="bDummyListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						<s:text name="bDummyListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="dummy.dummyId"></s:text>
				</td>
				<td>
					<s:text name="dummy.dummyName"></s:text>
				</td>
				<td>
					<s:text name="dummy.dummyDuration"></s:text>
				</td>
				<td>
					<s:text name="dummy.buyPrice"></s:text>
				</td>
				<td>
					<s:text name="dummy.factor1"></s:text>
				</td>
				<td>
					<s:text name="dummy.factor2"></s:text>
				</td>
				<td>
					<s:text name="dummy.moneyFactor1"></s:text>
				</td>
				<td>
					<s:text name="dummy.moneyFactor2"></s:text>
				</td>

				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="dummy" value="dummyList">
				<tr id="${dummy.dummyId}">
					<td>
						${dummy.dummyId}
					</td>
					<td>
						${dummy.dummyName}
					</td>
					<td>
						${dummy.dummyDuration}
					</td>
					<td>
						${dummy.buyPrice}
					</td>
					<td>
						${dummy.factor1}
					</td>
					<td>
						${dummy.factor2}
					</td>
					<td>
						${dummy.moneyFactor1}
					</td>
					<td>
						${dummy.moneyFactor2}
					</td>

					<td>
						<a href="#" onclick='del("${dummy.dummyId}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${dummy.dummyId}")'><s:text name="update"></s:text></a>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="10">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>