<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="getUserBagSingleJsp.title"><s:param>${user.userName}</s:param><s:param>${user.name}</s:param></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript">
	    
	    // 存放系统背包内容
	var toolArray = new Array();
	
	// 向 toolArray 中添加背包信息
	<s:iterator value="tools" status="sta">
		var equipment = new Object();
		// toolId 是 String 类型，格式是 toolType,toolId
		equipment.toolId = "${key}";
		equipment.toolName = "${value}";
		toolArray.push(equipment);
	</s:iterator>
	    
	    function searchTool() {
	    
		var toolInput = document.getElementById("toolInput").value;
		var toolSelect = document.getElementById("toolSelect");
		for (i = toolSelect.options.length-1; i >= 0; i--) {
			toolSelect.options[i] = null;
		}
		
		var tempArray = new Array();
	
		for (var i = 0; i < toolArray.length; i++) {
			if (toolArray[i].toolName==toolInput) {
			   
				var tool = new Object();
				tool.toolId = toolArray[i].toolId;
				tool.toolName = toolArray[i].toolName;
				tempArray.push(tool);
			}
		}
	
	    	for (var i = 0; i < toolArray.length; i++) {
			if (toolArray[i].toolName!=toolInput&&toolArray[i].toolName.indexOf(toolInput) != -1) {
				var tool = new Object();
				tool.toolId = toolArray[i].toolId;
				tool.toolName = toolArray[i].toolName;
				tempArray.push(tool);
			}
		}
		 
		if (tempArray.length == 0) {
			alert("<s:text name="没有找到"></s:text>")
			toolSelect.options[0] = new Option("请先搜索",'-1');
			return;
		}
		
		var quality = 1;
		
		for(var i=0; i<tempArray.length; i++){
		    
			toolSelect.options[i] = new Option(tempArray[i].toolName, tempArray[i].toolId);
		}

	}
	    window.onload = function hid(){
	       var flag = '<%=request.getAttribute("flag")%>';
	       
	       if(flag == "del"){
	         alert("删除成功");
	         window.location.href = "getUserBagSingle"
	       }
	       if(flag == "update"){
	          var uid = '<%=request.getAttribute("userId")%>';
	          var toolId = '<%=request.getAttribute("toolId")%>';
	          var toolType = '<%=request.getAttribute("toolType")%>';
	          var toolTypeId = toolType+","+toolId;
	          window.location.href = "getUserBagSingle?userId=" + uid + "&toolTypeId=" + toolTypeId;
	       }
	    }
	    
		function check(){
			var lodoId = document.getElementById("lodoId").value;
			if(lodoId == 0){
				alert("<s:text name="getUserBagSingleJsp.lodoIdCondition"></s:text>");			
				return false;
			}
			var toolName = document.getElementById("toolSelect").value;
			if(!toolName || toolName == -1){
			    alert("<s:text name="getUserBagSingleJsp.toolNameCondition"></s:text>");			
				return false;
			}
		}
		
		function del(userId,toolType,toolId,toolNum) {
		
			if (window.confirm("是否确认删除?")) {
			  
				window.location.href = "delUserBagSingle?userId=" + userId + "&toolType=" + toolType+ "&toolId=" + toolId+ "&toolNum=" + toolNum;
				
			}
	
		}
		
		function diag(userId, toolType, toolId, toolNum, toolName){
		
		   var tn = prompt("输入删除道具的数量:").trim();
		   if(!tn)
		      return false;
		   if(tn<0||tn>toolNum){
		      alert("请输入0到"+toolNum+"之间的数字!");
		      return false;
		   }
		   del(userId,toolType,toolId,tn);
		   //if(tn&&tn != toolNum){
		     //  update(userId, toolType, toolId, toolNum-tn, toolName);
		   //}
	    }
		function update(userId, toolType, toolId, toolNum, toolName) {
		    
			window.location.href = "updateUserBagSingle?userId=" + userId +"&toolType=" + toolType +"&toolId=" + toolId + "&toolNum=" + toolNum + "&toolName=" + toolName;
		}
		
		
	</script>
	<body>
		<form action="getUserBagSingle" method="post" onsubmit="return check()">
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center">
						<center>
							<s:text name="getUserBagSingleJsp.title"><s:param>${user.lodoId}</s:param></s:text>
						</center>
					</td>
				</tr>
			<tr>
				
				<td><s:text name="道具名"></s:text>
					<input type="text" id="toolInput"> 
			    </td>
			    <td>
					<input type="button" value="<s:text name="搜索"></s:text>" onclick="searchTool()"	class="button">
				</td>
			  </tr>
				<tr>
					<td colspan="3">
						<s:text name="log.lodoSearch"></s:text><s:text name="colon"></s:text><input type="text" id="lodoId" name="lodoId" size="10">
					</td>
					<!--  <td colspan="3">
						<s:text name="userTreasureSingle.toolName"></s:text><s:text name="colon"></s:text><input type="text" id="toolName" name="toolName" size="10">
					</td>-->
					<td>
					<s:text name="道具列表"></s:text>
					 <select name="toolTypeId" id="toolSelect">
						<option value="-1"><s:text name="请先搜索"></s:text></option>
					</select>
					</td>
					<td colspan="100">
						<input type="submit" value="<s:text name="search"></s:text>" class="button">
					</td>
				</tr>
		
				<c:if test="${userBagSingle != null}">
						<tr>
							<td><s:text name="userBag.userId"></s:text></td>
							<td><s:text name="userBag.toolType"></s:text></td>
							<td><s:text name="userBag.toolId"></s:text></td>
							<td><s:text name="userBag.toolName"></s:text></td>
							<td><s:text name="userBag.toolNum"></s:text></td>
							
							  <td width="35">
					<s:text name="delete"></s:text>
				</td>
				<!--<td width="35">
					<s:text name="update"></s:text>
				</td>-->
							
						</tr>
						
							<tr>
								<td>
								<!-- 
									<s:set var="treasureId" value="#userTreasure.treasureID"></s:set>
									<s:property value="treasureIDNameMap[#treasureId]"/><br>
								 -->	
									${user.userId}
								</td>
								<td>
								<!--  先通过getText 读出资源文件里的values,再通过循环取出放入str再进行比较从而获取资源文件中对应的类型 -->
								<s:generator separator="," val="%{getText('userBag.type_values')}">
										<s:iterator var="str">
											 <s:if test="userBagSingle.toolType == #str">
												<s:text name="%{'userBag.type_'+#str}"></s:text>
											 </s:if>
										</s:iterator>
									</s:generator>
									
								</td>
								<td>
								<!-- 
									<s:text name="%{'treasureType_'+#userTreasure.type}"></s:text>
									-->
									${userBagSingle.toolId}
								</td>
								<td>
								    ${toolName}
								</td>
								<td>
									${userBagSingle.toolNum}
								</td>
								<!--  <td>
					<a href="#" onclick='del("${user.userId}", "${userTreasure.toolType}", "${userTreasure.toolId}", "${userTreasure.toolNum}")'><s:text name="delete"></s:text></a>
				</td>-->
				<td>
					<a href="#" onclick='diag("${user.userId}", "${userBagSingle.toolType}", "${userBagSingle.toolId}", "${userBagSingle.toolNum}" ,"${toolName}")'><s:text name="delete"></s:text></a>
				</td>
								
				</tr>
						
				</c:if>
				</table>
		</form>
	</body>
</html>