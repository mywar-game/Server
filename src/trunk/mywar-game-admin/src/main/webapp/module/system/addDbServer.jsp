<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="dbServer.add"></s:text><s:text name="colon"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<script src="../../js/jquery/jquery.min.js"></script>
	<script type="text/javascript">
		//各种服务器类型对应的默认端口
		var portArr = new Array();
		//各种服务器类型对应的现有数量
		var typeAndLastSuffixArr = new Array();
		//各种服务器类型对应的名称前缀
		var namePrefixArr = new Array();
		
		<s:iterator value="typeAndLastSuffixMap" status="sta">
			typeAndLastSuffixArr[${key}] = ${value}; 
		</s:iterator>
		//alert(typeAndLastSuffixArr);
		
		<s:generator separator="," val="%{getText('dbServer.serverType_value')}">
			<s:iterator var="type">
				namePrefixArr[${type}] = '<s:text name="%{'dbServer.serverName_'+#type+'_namePrefix'}"></s:text>'; 
				portArr[${type}] = <s:text name="%{'dbServer.serverPort_'+#type+'_defaultValue'}"></s:text>
			</s:iterator>
		</s:generator>	
		
		
		function changeServerType(serverType){
			$("#serverPort").val(portArr[serverType]);
			var order;
			if (typeAndLastSuffixArr[serverType] == null) {
				order = 1;
			} else {
				order = typeAndLastSuffixArr[serverType] + 1;
			}
			var serverName = namePrefixArr[serverType] + '' + order;
			var html = '<input type="hidden" name="serverName" value="'+serverName+'"/>'+serverName;
			$("#serverNameTd").html(html);
			//如果是缓存数据库，数据库名称、用户名、密码三个tr隐藏,否则的话显示
			if(serverType == 4){
				$("#table tr:eq(5)").hide();
				$("#table tr:eq(6)").hide();
				$("#table tr:eq(7)").hide();
			} else {
				$("#table tr:eq(5)").show();
				$("#table tr:eq(6)").show();
				$("#table tr:eq(7)").show();
			}
		}
		
		window.onload = function(){
			//默认选择第一个
			changeServerType(1);
		}
	</script>
	<body>
		&nbsp;
		<form action="addDbServer?isCommit=T" method="post">
			<table cellpadding="3" id="table" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="dbServer.add"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dbServer.serverType"></s:text><s:text name="colon"></s:text>
						<input type="hidden" name="dbServerId" value="${dbServer.dbServerId}"/>
					</td>
					<td>
						<select id="serverType" name="serverType" class="select" onchange="changeServerType(this.value)">
							<s:generator separator="," val="%{getText('dbServer.serverType_value')}">
								<s:iterator var="str">
									<option value="${str}">
										<s:text name="%{'dbServer.serverType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dbServer.serverName"></s:text><s:text name="colon"></s:text>
					</td>
					<td id="serverNameTd">
						<input type="hidden" name="serverName" value="${dbServer.serverName}"/>
						${dbServer.serverName}
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dbServer.serverIp"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="serverIp" value="${dbServer.serverIp}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dbServer.serverPort"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="serverPort" id="serverPort" value="${dbServer.serverPort}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dbServer.dbName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="dbName" value="${dbServer.dbName}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dbServer.userName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="userName" value="${dbServer.userName}" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="dbServer.password"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="password" value="${dbServer.password}"/>
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
	<script src="../../js/jquery/jquery.validate.js"></script>
	<script type="text/javascript" >
		$(document).ready(function(){
			$("form").validate({
				rules:{
					serverIp:{
						required: true,
						maxlength:64
					},
					serverPort:{
						required: true,
						digits:true
					},
					dbName:{
						required: true,
						maxlength:32
					},
					userName:{
						required: true,
						maxlength:32
					},
					password:{
						required: true,
						maxlength:32
					}
				}
			})
		});
	</script>
</html>