<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="addSystemMailJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function isAllUser(input){
				//alert(input.value);
				//alert(input.checked);
				var nameStrInput = document.getElementById("nameStr");
				var chooseServer = document.getElementById("chooseServer");
				if (input.checked == true) {
					nameStrInput.style.display = "none";
					chooseServer.style.display = "";
				} else {
					nameStrInput.style.display = "";
					chooseServer.style.display = "none";
				}
			}
		
			function check(){
				var allUser = document.getElementById("allUser1");
				//alert("allUser=="+allUser.checked);
				var nameStr = document.getElementById("nameStr").value;
				//没发全服又没填角色名
				if(allUser.checked ==false && nameStr == ""){
					alert("<s:text name="addSystemMailJsp.nameStrIsEmpty"></s:text>");
					return false;
				}
				var theme = document.getElementById("theme").value;
				if(theme == "{0}" && !window.confirm("<s:text name="addSystemMailJsp.themeOnlyHaveName"></s:text>")){
					return false;
				}
				var content = document.getElementById("content").value;	
				//alert("content=="+content);
				if(content == "{0}" && !window.confirm("<s:text name="addSystemMailJsp.contentOnlyHaveName"></s:text>")){
					return false;
				}
				if (allUser.checked == true && !window.confirm("<s:text name="addSystemMailJsp.allUserConfirm"></s:text>")) {
					//alert("aaa");
					return false;
				}
				return true;
			}
			
			function changeAllCheck(isCheck){
				//所有的input标签
				var allInputTags = document.getElementsByTagName("INPUT");
				var x = 0;
				for ( var i = 0; i < allInputTags.length; i++) {
					//如果input标签的那么是powerIdArr，那么就选中
					if(allInputTags[i].name == "serverIds") {
						allInputTags[i].checked = isCheck;
						x++;
					}
				}
				//alert(x);
			}
			
		</script>
	</head>
	<body>
		<form action="addSystemMail?isCommit=T" method="post" onsubmit="return check()">
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center">
						<center>
							<s:text name="addSystemMailJsp.title"></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="addSystemMailJsp.nameStr"></s:text><s:text name=""></s:text>
					</td>
					<td colspan="6">
						<input type="text" name="nameStr" id="nameStr" size="100" value="${nameStr}">
						<input type="checkbox" name="allUser" id="allUser1" value="1" onclick="isAllUser(this)"/><label for="allUser1"><s:text name="addSystemMailJsp.allUser"></s:text></label>
						<br>
						<s:text name="addSystemMailJsp.nameStr_note"></s:text>
					</td>	
				</tr>
				<tr id="chooseServer" style="display:none">
					<td>
						选择服务器
						<br/>
						<a href="javascript:;" onclick="changeAllCheck(true)">全选</a>
						<a href="javascript:;" onclick="changeAllCheck(false)">全不选</a>
					</td>
						<s:iterator value="%{@com.system.manager.DataSourceManager@getInstatnce().getGameServerMap()}" status="sta">
	<!--					最后一个要对齐格式-->
							<td <s:if test="#sta.last">colspan='<s:property value="6 - #sta.count%6 + 1"/>'</s:if>>
								<input name="serverIds" class="checkbox" value="${key}" <s:if test="serverIds.indexOf(key+'') != -1">checked="checked"</s:if> type="checkbox">
								${value.serverAlias}
		<!--						一行6个-->
								<s:if test="#sta.count%6 == 0">
									<tr>
								</s:if>
							</td>
						</s:iterator>
				</tr>
				<tr>
					<td>
						<s:text name="addSystemMailJsp.theme"></s:text><s:text name=""></s:text>
					</td>
					<td colspan="6">
						<input type="text" name="theme" id="theme" size="100" value='${theme}'>
						<br>
						<s:text name="addSystemMailJsp.theme_note"></s:text>
					</td>	
				</tr>
				<tr>
					<td>
						<s:text name="addSystemMailJsp.content"></s:text><s:text name=""></s:text>
					</td>
					<td colspan="6">
						<textarea name="content" id="content" rows="5" cols="100">${content}</textarea>
						<br>
						<s:text name="addSystemMailJsp.content_note"></s:text>
					</td>	
				</tr>
				<tr>
					<td align="center" colspan="10">
						<input type="submit" value="<s:text name="addSystemMailJsp.send"></s:text>" class="button">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>