<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="cChatKeywordListJsp.title"></s:text></title>

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
			ajaxobj.url="reflashConstantCache?cacheType=15&number="+Math.random();
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="cChatKeywordListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="cChatKeywordListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		
		function showAdd(a){
			a.style.display="none";
			var addKeyWordInput = document.getElementById("addKeyWordInput");
			var addButton = document.getElementById("addButton");
			addKeyWordInput.style.display="";
			addButton.style.display="";
		}
		
		function showUpdate(a){
			a.style.display="none";
			var childNodes = a.parentNode.childNodes;
			for(var k=0; k<childNodes.length; k++){
				if(childNodes[k].nodeType==1 && childNodes[k].nodeName=="INPUT"){
					childNodes[k].style.display="";
				}
			}
		}
		
		function del(keyWord) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="cChatKeywordListJsp.keyword"></s:text></s:param><s:param>'+keyWord+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				//alert(ajaxobj);
				ajaxobj.url = "delCChatKeyword?keyWord=" + encodeURI(encodeURI(keyWord));
				//alert(ajaxobj.url);
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(keyWord);
					//alert(tr);
					var table = document.getElementById("table");
					//alert(table);
					table.deleteRow(tr.rowIndex);
					//alert();
				}
				ajaxobj.send();
			}
	
		}
	
		function update(keyWord,updateButton) {
			var newKeyWord = "";
			var childNodes = updateButton.parentNode.childNodes;
			for(var k=0; k<childNodes.length; k++){
				if(childNodes[k].nodeType==1 && childNodes[k].nodeName=="INPUT" && childNodes[k].type=="text"){
					//alert(childNodes[k].value);
					newKeyWord = childNodes[k].value;
				}
			}
			window.location.href = "updateCChatKeyword?keyWord=" + encodeURI(encodeURI(keyWord)) +"&newKeyWord="+encodeURI(encodeURI(newKeyWord));
		}
	
		function add() {
			var addKeyWordInput = document.getElementById("addKeyWordInput");
			window.location.href = "addCChatKeyword?keyWord=" + encodeURI(encodeURI(addKeyWordInput.value));
		}
	</script>
	<body>
		<input type="button" value="<s:text name="cChatKeywordListJsp.addKeyword"></s:text>" onclick='showAdd(this)' class="button">
		<input type="text" id="addKeyWordInput" value="" class="maxLife" size="20" maxlength="32" style="display:none;"/>
		<input type="submit" id="addButton" value="<s:text name="cChatKeywordListJsp.addKeyword"></s:text>" class="button" onclick=add(); style="display:none;"/>
		<input type="button" value='<s:text name="cChatKeywordListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="5" align="center">
					<center>
						<s:text name="cChatKeywordListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="cChatKeywordListJsp.keyword"></s:text>
				</td>
				<td>
					<s:text name="update"></s:text>
				</td>

				<td width="35">
					<s:text name="delete"></s:text>
				</td>
			</tr>
			<s:iterator var="chatKeyword" value="chatKeywordList">
				<tr id="${chatKeyword.keyWord}">
					<td>
						${chatKeyword.keyWord}
					</td>
					<td>
						<a href="#" onclick='showUpdate(this)'><s:text name="update"></s:text></a>
						<input type="text" value="${chatKeyword.keyWord}" class="maxLife" size="20" maxlength="32" style="display:none;"/>
						<input type="button" value="<s:text name="update"></s:text>" class="button" onclick='update("${chatKeyword.keyWord}",this)' style="display:none;"/>
					</td>

					<td>
						<a href="#" onclick='del("${chatKeyword.keyWord}")'><s:text name="delete"></s:text></a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>