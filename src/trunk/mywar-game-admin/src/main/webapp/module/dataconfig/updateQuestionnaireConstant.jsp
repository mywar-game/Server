<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>问卷常量修改</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		  function init() {
	        // 设置textarea自适应高度
	        var handler = function () {
	            setTareaAutoHeight('question');
	        };
	
	        // 添加监听
	        if (window.addEventListener) {//FF
	            window.addEventListener('DOMContentLoaded', handler, false);
	            window.addEventListener('load', handler, false);
	        } else if (window.attachEvent)//IE
	            window.attachEvent('onload', handler);
	        else
	            window.onload = handler;
	    } // end init
	
	    init();
	
	    function setTareaAutoHeight(id) {
	        document.getElementById(id).style.height = document.getElementById(id).scrollHeight + "px";
	    }
	    
	    function optionsToJSON() {
			var options = document.getElementById("options");
			
			var optionDescs = document.getElementsByName("option");
			var optionArray = new Array();
			for(var i=0; i<optionDescs.length; i++){
				var option = new Object;
				if (optionDescs[i].value == "") {
					alert("第"+(i+1)+"个选项未填写！");
					return false;
				}
				option.optionId = (i+1);
				option.option = optionDescs[i].value;
				optionArray.push(option);
			}
			options.value = JSON.stringify(optionArray);
			return true;
		}
		
		function isSubmit(){
			if(optionsToJSON() && window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
		}
		
		function addOption(addOptionButton){
			var showTd = document.getElementById("showTd");
			var table = document.getElementById("table");
			var tr = table.insertRow(addOptionButton.parentNode.parentNode.rowIndex);
			var td1 = tr.insertCell(tr.cells.length);
			var html = '';
			html += '<input name="option" size="100" type="text">';
			td1.innerHTML = html;
			var td2 = tr.insertCell(tr.cells.length);
			html = '';
			html += '<input value="删除奖励" onclick="delOption(this)" class="button" type="button">';
			td2.innerHTML = html;
			showTd.rowSpan = showTd.rowSpan+1;
		}
		
		function delOption(delButton){
			//alert(delButton.value);
			var table = document.getElementById("table");
			var tr = delButton.parentNode.parentNode;
			//alert(tr.innerHTML);
			//alert(tr.cells[0].id);
			//如果是第一行
			if (tr.cells[0].id == "showTd") {
				//如果是最后一个选项（下一行的id是addOptionTd），不能删
				if(table.rows[tr.rowIndex+1].id == 'addOptionTd'){
					alert("最后一个选项不能删！");
					return false;
				}
				var td = table.rows[tr.rowIndex+1].insertCell(0);
				td.innerHTML = tr.cells[0].innerHTML;
				td.id = tr.cells[0].id;
				td.rowSpan = tr.cells[0].rowSpan;
				table.deleteRow(tr.rowIndex);
			} else {
				table.deleteRow(tr.rowIndex);
			}
			//选项的td的长度减一
			var showTd = document.getElementById("showTd");
			showTd.rowSpan = showTd.rowSpan-1;
		}
	</script>
	<body>
		&nbsp;
		<form action="updateQuestionnaireConstant?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center id="table">
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							问卷常量修改 &nbsp;
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						问题<s:text name="colon"></s:text>
						<input type="hidden" name="questionId" value="${questionId}">
						<input type="hidden" id="options" name="options" value="${options}">
					</td>
					<td colspan="2">
						<textarea id='question' name="question" onpropertychange='setTareaAutoHeight("question")' oninput='setTareaAutoHeight("question")' style="width:80%; overflow-y:hidden;">${questionnaireConstant.question}</textarea>
					</td>
				</tr>
				<s:iterator var="optionMap" value="optionList" status="sta">
					<tr>
						<s:if test="#sta.index == 0">
							<td id="showTd" rowspan="${fn:length(optionList)}">
								选项：
							</td>
						</s:if>
						<td>
							<input type="text" name="option" value="${option}" size="100">
						</td>
						<td>
							<input type="button" value="删除选项" onclick="delOption(this)" class="button" />
						</td>
					</tr>
				</s:iterator>
				<tr id="addOptionTd">
					<td colspan="10">
						<input type="button" value="添加选项" onclick="addOption(this)" class="button" />
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