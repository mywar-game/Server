<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="getUserMissionJsp.title"><s:param>${user.userName}</s:param><s:param>${user.name}</s:param></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
		
		<script type="text/javascript" src="../../js/ajax.js"></script>
		
		<script src="../../js/jquery/jquery.min.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.core.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.widget.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.position.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.autocomplete.js"></script>
		<script>
			$(function() {
				$("#searchMissionName").autocomplete({
					minLength: 0,
					source: function( request, response ) {
						$.ajax({
							url: "../dataconfig/mMissionConstantListfindMissionIdNameMapByCondition",
							data:{name: encodeURI(request.term)},
							success: function(data) {
								var x;
								response($.map( data, function(name,id) {
									return {
										id: id,
										label: name,
										value: name
									}
								}));
							}
						});
					},
					select: function( event, ui ) {
						$( "#missionID" ).val( ui.item.id );
					},
					open: function() {
						$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
					},
					close: function() {
						$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
					}
				});
			});
		</script>
	</head>
	<body>
		<form action="getUserMissionList?isCommit=T" method="post" >
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center">
						<center>
							<s:text name="getUserMissionJsp.title"><s:param>${user.userName}</s:param><s:param>${user.name}</s:param></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="getUserMissionJsp.nameSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="name" name="name" size="10">
					</td>
					<td>
						任务名：
					</td>
					<td>
						<input type="text" id="searchMissionName" name="searchMissionName" />
					</td>
					<td><s:text name="getUserMissionJsp.missionID"></s:text></td>
					<td>
						<input type="text" id="missionID" name="missionID" value="${missionID}" />
					</td>
				</tr>
				<tr>
					<td colspan="50">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
		
			</table>
		</form>
	</body>
</html>