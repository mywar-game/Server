<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="修改邮件"></s:text></title>
	</head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="../../js/json.js"></script>
	<script language="javascript" src="../../js/ajax.js"></script>
	<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
	


	<script type="text/javascript">
	var mailAttachs = "";
	// 存放系统中所有可以发送的东西，包括装备、道具、元宝、武将等等
	var toolArray = new Array();
	
	// 向 toolArray 中添加装备信息
	<s:iterator value="tools" status="sta">
		var equipment = new Object();
		// toolId 是 String 类型，格式是 toolType,toolId
		equipment.toolId = "${key}";
		equipment.toolName = "${value}";
		toolArray.push(equipment);
	</s:iterator>
	
	var equipmentColorArray = new Array();
	<s:generator separator="," val="%{getText('userEquipment.color_values')}">
		<s:iterator var="str">
				equipmentColorArray[${str}] = "<s:text name="%{'userEquipment.color_'+#str}"></s:text>";
		</s:iterator>
	</s:generator>
	
	function searchTool() {
		var toolInput = document.getElementById("toolInput").value;
		var toolSelect = document.getElementById("toolSelect");
		for (i = toolSelect.options.length-1; i >= 0; i--) {
			toolSelect.options[i] = null;
		}
		
		var tempArray = new Array();
		for (var i = 0; i < toolArray.length; i++) {
			if (toolArray[i].toolName.indexOf(toolInput) != -1) {
				var tool = new Object();
				tool.toolId = toolArray[i].toolId;
				tool.toolName = toolArray[i].toolName;
				tempArray.push(tool);
			}
		}
		
		if (tempArray.length == 0) {
			alert("<s:text name="没有找到"></s:text>")
			return;
		}
		
		var quality = 1;
		for(var i=0; i<tempArray.length; i++){
			toolSelect.options[i] = new Option(tempArray[i].toolName, tempArray[i].toolId);
			toolSelect.options[i].style.backgroundColor = equipmentColorArray[quality];
			if (quality < 7) {
				quality++;
			} else {
				quality = 1;
			}
		}
	}
	
	function addTool() {
		var toolSelect = document.getElementById("toolSelect");
		var toolNum = document.getElementById("toolNum");
		var tools = document.getElementById("tools");
		
		var id = 0;
		var name = "";
		var num = toolNum.value;
		
		if (num == "") {
			alert("<s:text name="请输入购买数量"></s:text>");
			return;
		}
		
		for (i = toolSelect.options.length-1; i >= 0; i--) {
			if (toolSelect.options[i].selected == true) {
				name = toolSelect[i].innerHTML;
			}
		}
		
		toolList.value += "\n" + name + " * " + num;
		
		id = toolSelect.value;
		mailAttachs += id + "," + parseInt(num) + "|";
		
	
	}
	
	
	function beforeSubmit() {
		if (mailAttachs.length == 0) {
			alert("<s:text name="没有选择道具"></s:text>");
			return false;
		}
		var mailAttachInput = document.getElementById("mailAttach");
		mailAttachInput.value = mailAttachs;
	}
	
	function setTarget() {
		var lodoIds = document.getElementById("lodoIds");
		var servers = document.getElementsByName("serverIds");
		var num = 0;
		for (var i = 0; i < servers.length; i++) {
			if(servers[i].checked == true) {
				num++;
			}
		}
		
		if(num >= 2) {
			alert("选择了多个服务器，只能发全服");
			lodoIds.setAttribute("readonly", "readonly");
		} else {
			lodoIds.removeAttribute("readonly");
		}
	}
	
	function checkServerNum() {
		var servers = document.getElementsByName("serverIds");
		var lodoIds = document.getElementById("lodoIds");

		if (servers.length >= 2) {
			lodoIds.value="";
		}
	}
	
	function checkServers() {
		var selectedServers = document.getElementById("selectedServers").value.split(", ");
		var servers = document.getElementsByName("serverIds");
		for (var i = 0; i < servers.length; i++) {
			for (var j = 0; j < selectedServers.length; j++) {
				if (servers[i].value == selectedServers[j]) {
					servers[i].checked=true;
				}
			}
			
		}
		
	}
	
	function isSendAll() {
		var lodoIds = document.getElementById("lodoIds").value;
		if (lodoIds != "") {
			document.getElementById("isCheckAll").setAttribute("disabled", "disabled");
		} else {
			document.getElementById("isCheckAll").removeAttribute("disabled");
		}
		
	}
	</script>
<body>
	<form action="mailUpdate?isCommit=T" method="post">
		<table cellpadding="3" cellspacing="2" border="0" width="100%"
			align=center>
			<tr>
				<td><input type="hidden" name="adminMailId" value="${adminMail.adminMailId}"/></td>
				<td><input type="hidden" name="createdTime" value="${adminMail.createdTime}"/></td> 
			</tr>
			
			<tr>
				
				<td>选择服务器</td>
				<td>
					<input type="hidden" id="selectedServers" value="${adminMail.serverIds}">
					<s:iterator var="data" value="tgameServerList">
						<input type="checkbox" name="serverIds" onclick="checkServerNum()" value="${data.serverId}" />
					${data.serverAlias}
				</s:iterator></td>
			</tr>
			<tr>
				<td rowspan="3">收件人</td>
				<td><textarea  onfocus="setTarget()" onblur="isSendAll()" id="lodoIds" name="lodoIds" rows="5" cols="20">${adminMail.lodoIds}</textarea>  
				每个 id 之间用英文逗号分隔
				逗号和下一个 id 之间不要有空格</td>
			</tr>
			
			<tr>
				<td>发放目标	
				     <!--input id="isCheckAll" type="checkbox" name="target" value=1-->
				     <select name="target" ">
				         <option value=2>指定用户</option>
				         <option value=1>发送全服</option>
				         <option value=3>登录用户</option>
				         <option value=4>充值用户</option>
				     </select>
				     
				     输入时间：
				     <input style="width:15%" type="text" id="startTime" name="date" 
				     	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" style="width: 100px" />
				</td>
			</tr>
			<tr>
				<td>
					选择不同发送对象时输入的时间有不同作用，如下（假设输入的时间是 <b>2013-1-1</b>）：
					<br>1.如果选择<b>『登录用户』</b>，邮件只发送给 <b>2013-1-1</b> 这一天登录过的用户； 
					<br>2.如果选择<b>『发送全服』</b>，邮件只发送给 <b>2013-1-1</b>这一天之前注册的用户；
					<br>3.如果选择<b>『充值用户』</b>，邮件只发送给 <b>2013-1-1</b>这一天充值过的用户。
					<br>4.如果选择指定用户，这个时间可以不用填写。
				</td>
			</tr>
			<tr>
				<td>主题</td>
				<td><input type="text" name="title" value="${adminMail.title}"></td>
			</tr>
			<tr>
				<td>邮件内容</td>
				<td><textarea name="content" rows="4" cols="20">${adminMail.content}</textarea></td>
			</tr>
			<tr>
				<td rowspan="3">道具</td>
				<td><s:text name="道具名"></s:text>
					<input type="text" id="toolInput"> 
					<input type="button" value="<s:text name="搜索"></s:text>" onclick="searchTool()"	class="button">
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="道具列表"></s:text>
					 <select id="toolSelect">
						<option value="-1"><s:text name="请先搜索"></s:text></option>
					</select> 
					<s:text name="数量"></s:text>
					<input type="text" id="toolNum" size="5" onblur="value=value.replace(/[^\d]/g,'')"> 
					<input type="button" value="<s:text name="添加"></s:text>" onclick="addTool()" class="button">
				</td>
			</tr>
			<tr>
					<td>
					<s:text name="发放道具列表"></s:text><br/>
					<textarea name="toolList" id="toolList" rows="10" cols="40" readonly="readonly"></textarea>
					</td>
				</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="hidden" id="mailAttach" name="mailAttach"> 
					<input type="submit" value="<s:text name="提交审核"></s:text>"  class="button"></td>
			</tr>
		</table>
	</form>
</body>
 <script type="text/javascript">
checkServers();
</script>
</html>