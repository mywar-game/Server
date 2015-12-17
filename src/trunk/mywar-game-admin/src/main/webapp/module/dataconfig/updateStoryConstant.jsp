<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateStoryConstantJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function isSubmit(){
			if(window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
		}
	</script>
	<body>
		&nbsp;
		<form action="updateStoryConstant?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="updateStoryConstantJsp.title"></s:text> &nbsp;
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="storyConstant.storyId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="hidden" name="storyId" value="${storyConstant.storyId}" class="maxLife" size="11" maxlength="11" />
						${storyConstant.storyId}
					</td>
					<td>
						<s:text name="storyConstant.type"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="type" class="select">
							<s:generator separator="," val="%{getText('storyConstant.type_values')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="storyConstant.type == #str">selected=selected</s:if>>
										<s:text name="%{'storyConstant.type_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="storyConstant.storyDesc"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<textarea rows="3" cols="100" name="storyDesc">${storyConstant.storyDesc}</textarea>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="storyConstant.npcId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="npcId" value="${storyConstant.npcId}" class="maxLife" size="8" maxlength="8" />
					</td>
					<td>
						<s:text name="storyConstant.npcName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="npcName" value="${storyConstant.npcName}" class="maxLife" size="30" maxlength="32" />
					</td>
				</tr>
				
				<tr>
					<td colspan="5" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>