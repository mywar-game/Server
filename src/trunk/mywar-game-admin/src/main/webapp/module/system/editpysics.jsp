<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title><s:text name="editpysicsJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript">
			function checkform(){
			  if(document.pform.name.value==''){
			    alert('<s:text name="addpysicsJsp.nameNeed"></s:text>');
			    return false ;
			  }
			  if(document.pform.actionName.value==''){
			    alert('<s:text name="addpysicsJsp.actionNameNeed"></s:text>');
			  return false;
			  }
			  return true;
			}
		</script>
	</head>
	<body>
		<form action="editpysics?isCommit=T&id=${id}" method="post"
			name="pform" onsubmit="return checkform();">
			<table cellpadding="3" cellspacing="1" border="0" width="100%"
				align=center>
				<tr class="border">
					<td class="td_title" colspan="3">
						<s:text name="editpysicsJsp.title"></s:text> &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminPowerPhysics.name"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="name" class="textbox" size="20" maxlength="20" / value="${name}">
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminPowerPhysics.actionName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="actionName" class="textbox" size="50" maxlength="128" value="${actionName}" />
						<font color='red'><s:text name="adminPowerPhysics.actionName_note"></s:text></font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="addpysicsJsp.rootName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="rootName" class="select">
							<s:iterator var="data" value="rootList">
								<option value="${data.id},${data.name}"
									<c:if test="${moduleId==data.id}">selected</c:if>>
									${data.name}
								</option>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>"
							class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>"
							class="button" />
					</td>
				</tr>
			</table>
		</form>
		<s:fielderror />

	</body>
</html>
