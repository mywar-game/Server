<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="adminPkAwardListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
	
		function del(id) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param>id</s:param><s:param>"+id+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delAdminPkAward?id=" + id;
				ajaxobj.callback = function() {
					//var tr = document.getElementById(id);
					//var table = document.getElementById("table");
					//table.deleteRow(tr.rowIndex);
					window.location.href = "adminPkAwardList";
				}
				ajaxobj.send();
			}
	
		}
	
		function update(id) {
			window.location.href = "updateAdminPkAward?id=" + id;
		}
	
		function add() {
			window.location.href = "addAdminPkAward";
		}
	</script>
	<body>
		<input type="submit" value="<s:text name='addAdminPkAwardJsp.title'></s:text>" class="button" onclick=add(); />
		<input type="button" value="<s:text name='adminPkAward.fresh'></s:text>" class="button" onclick=reflashAll(''); />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="adminPkAwardListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td >
					<s:text name="adminPkAward.Award_id"></s:text>
				</td>
				<td >.
					<s:text name="adminPkAward.Award_score"></s:text>
				</td>
				<td >
					<s:text name="adminPkAward.Award_tools"></s:text>
				</td>

				<td>
					<s:text name="adminPkAward.Award_name"></s:text>
				</td>
				<td>
					<s:text name="adminPkAward.Award_description"></s:text>
				</td>
				<td>
					<s:text name="adminPkAward.Award_imgId"></s:text>
				</td>
				<td>
					<s:text name="adminPkAward.Award_day_buy_num"></s:text>
				</td>
				<td>
					<s:text name="adminPkAward.Award_total_buy_num"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="pkAward" value="pkAwardList" >
			<tr>
			    <td>
			        ${pkAward.id}
			    </td>
				<td >
					${pkAward.score}				
				</td>
				<td >
					${pkAward.tools}
				</td>
				
				<td >
					${pkAward.name}
				</td>
				<td >
					${pkAward.description}
				</td>
				<td >
					${pkAward.imgId}
				</td>
				<td >
					${pkAward.day_buy_num}
				</td>
				<td>
				   ${pkAward.total_buy_num}		
				<td>
					<a href="#" onclick='del("${pkAward.id}")'><s:text name="delete"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='update("${pkAward.id}")'><s:text name="update"></s:text></a>
				</td>		
			</tr>
			</s:iterator>
			<td colspan="100">
				<aldtags:pageTag paraStr="isCommit,T,pageSize,${pageSize}" />
			</td>
		</table>
	</body>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script>
		function reflashAll(freshClassName) {
			var ajaxobj = new Ajax();
			ajaxobj.url = "reflashActivityList?freshClassName=" + "PkAwardDaoCacheImpl";
			ajaxobj.callback=function(){
				var responseMsg = eval('(' + ajaxobj.gettext() + ')');
				if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param>刷新系统PK奖励</s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param>刷新系统PK奖励</s:param></s:text>');
				}
			}
			ajaxobj.send();	
		}
	</script>
</html>
 	