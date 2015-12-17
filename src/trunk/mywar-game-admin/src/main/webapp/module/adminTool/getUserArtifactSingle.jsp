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
	    
	    // 存放系统装备内容
	var toolArray = new Array();
	
	// 向 toolArray 中添加装备信息
	<s:iterator value="artifacts" status="sta">
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
	         window.location.href = "getUserArtifactSingle"
	       }
	       
	    }
	    
		function check(){
			var lodoId = document.getElementById("lodoId").value;
			if(lodoId == 0){
				alert("<s:text name="getUserEquipSingleJsp.lodoIdCondition"></s:text>");			
				return false;
			}
			var toolName = document.getElementById("toolSelect").value;
			if(!toolName || toolName == -1){
			    alert("<s:text name="getUserEquipSingleJsp.toolNameCondition"></s:text>");			
				return false;
			}
		}
		
		function del(userId, userArtifactId) {
		
			if (window.confirm("是否确认删除?")) {
			  
				window.location.href = "delUserArtifactSingle?userId=" + userId + "&userArtifactId=" + userArtifactId;
				
			}
	
		}
		
		
	</script>
	<body>
		<form action="getUserArtifactSingle" method="post" onsubmit="return check()">
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center">
						<center>
							<s:text name="getUserArtifactSingleJsp.title"><s:param>${user.lodoId}</s:param></s:text>
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
					 <select name="artifactId" id="toolSelect">
						<option value="-1"><s:text name="请先搜索"></s:text></option>
					</select>
					</td>
					<td colspan="100">
						<input type="submit" value="<s:text name="search"></s:text>" class="button">
					</td>
				</tr>
		
				<c:if test="${userArtifactSomeInfoList != null}">
						<tr>
						    <td><s:text name="userArtifactSingle.userId"></s:text></td>
							<td>	
								<s:text name="userArtifactSingle.userArtifactId"></s:text>
							</td>
							<td>	
								<s:text name="userArtifactSingle.ArtifactId"></s:text>
							</td>
							<td>	
								<s:text name="userArtifactSingle.userHeroId"></s:text>
							</td>
							<td>	
								<s:text name="userArtifactSingle.artifactAttr"></s:text>
							</td>
							<td>	
								<s:text name="userArtifactSingle.artifactLevel"></s:text>
							</td>
							
							<td>
							<s:text name="delete"></s:text>
							</td>
						</tr>
						
						<s:iterator var="userArtifactSomeInfo" value="userArtifactSomeInfoList">
							<tr> 
							    <td>
									${user.userId}
								</td>
								<td>
									${userArtifactSomeInfo.userArtifactId}
								</td>
								<td>
									${userArtifactSomeInfo.artifactId}
								</td>
								<td>
									${userArtifactSomeInfo.userHeroId}
								</td>
								<td>
									${userArtifactSomeInfo.artifactAttr}
								</td>
								<td>
									${userArtifactSomeInfo.artifactLevel}
								</td>
								<td>
								<a href="#" onclick='del("${user.userId}", "${userArtifactSomeInfo.userArtifactId}")'><s:text name="delete"></s:text></a>
								</td>
							</tr>
						</s:iterator>
				</c:if>
				</table>
		</form>
	</body>
</html>