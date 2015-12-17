<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="addmenuchildJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript">
			function checkform(){
			  if(document.pform.menuName.value==''){
			    	alert('<s:text name="editmenuchildJsp.nameNeed"></s:text>');
			    return false ;
			  }
			  if(document.pform.ifLeaf.value=='T'&&document.pform.menuPath.value==''){
			    	alert('<s:text name="editmenuchildJsp.leafMenuPathNeed"></s:text>');
			    return false;
			  }
			  return true;
			}
		</script>
	</head>
	<body>
		<form action="addmenuchild?isCommit=T&parentId=${parentId}" method="post" name="pform" onsubmit="return checkform()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="3">
						<s:text name="addmenuchildJsp.title"></s:text> &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminMenu.menuName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="menuName" class="textbox" size="26" maxlength="14" value="${menuName}" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminMenu.menuPath"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="menuPath" class="textbox" size="50" value="${menuPath}" />
						<s:text name="adminMenu.menuPath_note"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminMenu.ifLeaf"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="ifLeaf" class="select">
							<option value="T" <c:if test="${ifLeaf=='T'}">selected='selected'</c:if>>
								<s:text name="adminMenu.ifLeaf_T"></s:text>
							</option>
							<option value="F" <c:if test="${ifLeaf=='F'}">selected='selected'</c:if>>
								<s:text name="adminMenu.ifLeaf_F"></s:text>
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="adminMenu.orderId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="orderId" class="select">
							<s:generator separator="," val="%{getText('adminMenu.orderId_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="adminMenu.orderId == #str">selected=selected</s:if>>
										<s:text name="%{'adminMenu.orderId_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
