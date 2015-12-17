<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>常量修改</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
	<script type="text/javascript">
		function init() {
	        // 设置textarea自适应高度
	        var handler = function () {
	            setTareaAutoHeight('content');
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
	    
		function isSubmit(){
			var serverIds = document.getElementsByName("serverIds");
			var num = 0;
			for ( var i = 0; i < serverIds.length; i++) {
				if (serverIds[i].checked) {
					num++;
				}
			}
			if (num == 0) {
				alert("还没有选择服务器！");
				return false;
			}
			if(window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
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
	<body>
		&nbsp;
		<form action="addSystemNoticeLog?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="10">
						<center>
							添加常量 &nbsp;
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
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
						<s:text name="systemNoticeLog.startDate"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="10">
						<input type="text" id="startDate" name="startDate" value="<s:if test="systemNoticeLog.startDate != null"><s:text name="format.date_ymd"><s:param value="systemNoticeLog.startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',onpicked:function(){endDate.focus();}})"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="systemNoticeLog.endDate"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="10">
						<input type="text" id="endDate" name="endDate" value="<s:if test="systemNoticeLog.endDate != null"><s:text name="format.date_ymd"><s:param value="systemNoticeLog.endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="systemNoticeLog.startTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="10">
						<input type="text" id="startTime" name="startTime" value="<s:if test="systemNoticeLog.startTime != null"><s:text name="format.date_hms"><s:param value="systemNoticeLog.startTime"></s:param></s:text></s:if>" class="Wdate" style="width:100px" onfocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',onpicked:function(){endTime.focus();}})"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="systemNoticeLog.endTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="10">
						<input type="text" id="endTime" name="endTime" value="<s:if test="systemNoticeLog.endTime != null"><s:text name="format.date_hms"><s:param value="systemNoticeLog.endTime"></s:param></s:text></s:if>" class="Wdate" style="width:100px" onfocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss'})"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="systemNoticeLog.period"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="10">
						<input type="text" id="period" name="period" value="${systemNoticeLog.period}" onblur="value=value.replace(/[^\d]/g,'')" />秒
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="systemNoticeLog.type"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="10">
						<select id="type" name="type" class="select">
							<s:generator separator="," val="%{getText('systemNoticeLog.type_values')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="systemNoticeLog.type == #str">selected=selected</s:if>>
										<s:text name="%{'systemNoticeLog.type_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="systemNoticeLog.content"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="10">
						<textarea id='content' name="content" onpropertychange='setTareaAutoHeight("content")' oninput='setTareaAutoHeight("content")' style="width:80%; overflow-y:hidden;">${systemNoticeLog.content}</textarea>
					</td>
					
				</tr>
				<tr>
					<td colspan="10" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>