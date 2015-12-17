<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>问卷常量列表</title>

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
			ajaxobj.url="reflashConstantCache?cacheType=33&number="+Math.random();
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新问卷常量配置缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新问卷常量配置缓存失败!');
				}
		    }
			ajaxobj.send();
		}
		
		function del(question, questionId,optionNum) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param>问题</s:param><s:param>'+question+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delQuestionnaireConstant?questionId=" + questionId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var table = document.getElementById("table");
					var tr = document.getElementById(questionId);
					for(var i = optionNum; i >= 0; i--){
						table.deleteRow(tr.rowIndex+i);
					}
				}
				ajaxobj.send();
			}
	
		}
	
		function update(questionId) {
			window.location.href = "updateQuestionnaireConstant?questionId=" + questionId;
		}
	
		function add() {
			window.location.href = "addQuestionnaireConstant";
		}
	</script>
	<body>
		<input type="submit" value="添加问卷常量" class="button" onclick=add(); />
		<input type="button" value="刷新问卷常量配置缓存" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						问卷常量列表
					</center>
				</td>
			</tr>
			<tr>
				<td rowspan="2" width="35">
					id
				</td>
				<td colspan="2">
					问题
				</td>

				<td width="35" rowspan="2">
					<s:text name="delete"></s:text>
				</td>
				<td width="35" rowspan="2">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<tr>
				<td width="70">
					选项编号
				</td>
				<td>
					选项
				</td>
			</tr>
			<s:iterator var="questionnaireConstant" value="questionnaireConstantList" status="sta">
				<s:set var="optionList" value="allOptionList[#sta.index]"></s:set>
				<tr id="${questionnaireConstant.questionId}">
					<td width="35" rowspan="${fn:length(optionList)+1}">
						${questionnaireConstant.questionId}
					</td>
					<td colspan="2">
						${questionnaireConstant.question}
					</td>

					<td rowspan="${fn:length(optionList)+1}">
						<a href="#" onclick='del("问题","${questionnaireConstant.questionId}",${fn:length(optionList)})'><s:text name="delete"></s:text></a>
					</td>
					<td rowspan="${fn:length(optionList)+1}">
						<a href="#" onclick='update("${questionnaireConstant.questionId}")'><s:text name="update"></s:text></a>
					</td>
				</tr>
				<s:iterator var="optionMap" value="optionList">
					<tr>
						<td>
							${optionId}
						</td>
						<td>
							${option}
						</td>
					</tr>
				</s:iterator>
			</s:iterator>
			
			<tr>
				<td colspan="10">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>