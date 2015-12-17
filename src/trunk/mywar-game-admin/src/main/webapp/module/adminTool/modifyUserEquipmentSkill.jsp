<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="modifyUserEquipmentSkillJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	
	<script src="../../js/jquery/jquery.min.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.core.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.position.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.autocomplete.js"></script>
	
	<script type="text/javascript">
		var erroDescrip = '${erroDescrip}';
		if (erroDescrip != "") {
			alert(erroDescrip);
		}
		
		$(function() {
			$("#searchSkillName").autocomplete({
				minLength: 0,
				source: function( request, response ) {
					$.ajax({
						url: "../dataconfig/sSkillConstantListfindTreasureIdNameMapByCondition",
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
					var beforeVal = $( "#skillPoints" ).val();
					if(beforeVal == "") {
						$( "#skillPoints" ).val( ui.item.id );
					} else {
						$( "#skillPoints" ).val(beforeVal +","+ui.item.id );
					}
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
	<body>
		&nbsp;
		<form action="modifyUserEquipmentSkill?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="6">
						<center>
							<s:text name="modifyUserEquipmentSkillJsp.title"></s:text> &nbsp;
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="searchName" name="searchName" value="${searchName}">
						<input type="hidden" id="userId" name="userId" value="${userId}">
						<input type="hidden" id="userEquipmentID" name="userEquipmentID" value="${userEquipmentID}">
						<s:text name="modifyUserEquipmentSkillJsp.equipmentName"></s:text><s:text name="colon"></s:text>
						${equipmentName}
					</td>
					<td>
						<s:text name="modifyUserEquipmentSkillJsp.skillNames"></s:text><s:text name="colon"></s:text>
						${skillNames}
					</td>
					<td>
						<s:text name="modifyUserEquipmentSkillJsp.searchSkillName"></s:text><s:text name="colon"></s:text>
						<input type="text" id="searchSkillName" name="searchSkillName">
					</td>
					<td>
						<s:text name="modifyUserEquipmentSkillJsp.skillPoints"></s:text><s:text name="colon"></s:text>
						<input type="text" id="skillPoints" name="skillPoints" value="${skillPoints}">
					</td>
				</tr>
				<tr>
				</tr>
				
				<tr>
					<td colspan="6" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>