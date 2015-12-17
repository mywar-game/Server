<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="secondaryServer.add"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
		
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script src="../../js/jquery/jquery.min.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.core.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.position.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.autocomplete.js"></script>
	<script type="text/javascript">
		//各种服务器类型对应的默认id
		var defaultIdArr = new Array();
		//各种服务器类型对应的默认端口
		var portArr = new Array();
		//各种服务器类型对应的现有数量
		var typeAndLastServerIdArr = new Array();
		//各种服务器类型对应的默认名称前缀
		var namePrefixArr =  new Array();
		
		<s:iterator value="typeAndLastServerIdMap">
			typeAndLastServerIdArr[${key}] = ${value}; 
		</s:iterator>
		
		<s:generator separator="," val="%{getText('secondaryServer.serverType_value')}">
			<s:iterator var="type">
				defaultIdArr[${type}] = <s:text name="%{'secondaryServer.serverId_'+#type+'_defaultValue'}"></s:text>;
				portArr[${type}] = <s:text name="%{'secondaryServer.serverPort_'+#type+'_defaultValue'}"></s:text>;
				namePrefixArr[${type}] = '<s:text name="%{'secondaryServer.serverName_'+#type+'_namePrefix'}"></s:text>';
			</s:iterator>
		</s:generator>
		
		function changeServerType(serverType){
			$("#serverPort").val(portArr[serverType]);
			var serverId;
			if (typeAndLastServerIdArr[serverType] == null) {
				serverId = defaultIdArr[serverType];
			} else {
				serverId = typeAndLastServerIdArr[serverType] + 1;
			}
			//alert(serverId);
			$("#serverId").val(serverId);
			var serverName = namePrefixArr[serverType] + '' + serverId;
			var html = '<input type="hidden" name="serverName" value="'+serverName+'"/>'+serverName;
			$("#serverNameTd").html(html);
		}
		
		window.onload = function(){
			changeServerType(1);
		}
		
		$(function() {
				$("input[name='searchDbName']").autocomplete({
					minLength: 0,
					source: function(request, response) {
						//用id来区分是哪种数据库类型
						var serverType = $(this.element).attr("id");
						$.ajax({
							url: "../system/dbServerListfindDbSeverIdAndInfoMapByCondition",
							data: {searchCondition:encodeURI(request.term), searchType:serverType},
							success: function(data) {
								response($.map( data, function(name,id) {
									return {
										id: id,
										label: name,
										value: name
									}
								}));
							}
						});
					},
					focus: function(event, ui) {
			            this.value = ui.item.value;
						$(this).parent().find(":input[name$=DbServerCode]")[0].value = ui.item.id;;
			        },
					select: function( event, ui ) {
						$(this).parent().find(":input[name$=DbServerCode]")[0].value = ui.item.id;;
					},
					open: function() {
						$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
					},
					close: function() {
						$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
					}
				}).bind("focus", function() {
			        //console.debug("aaaa="+this.value+"=");
			        if (this.value === '') {
			            $(this).autocomplete("search", "");
			        }
    			});
			});
	</script>
	<body>
		&nbsp;
		<form action="addSecondaryServer?isCommit=T" method="post" >
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="secondaryServer"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="serverId" name="serverId" value="${secondaryServer.serverId}"/>
						<s:text name="secondaryServer.serverType"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="serverType" class="select" onchange="changeServerType(this.value)">
							<s:generator separator="," val="%{getText('secondaryServer.serverType_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="secondaryServer.serverType == #str">selected=selected</s:if>>
										<s:text name="%{'secondaryServer.serverType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="secondaryServer.serverName"></s:text><s:text name="colon"></s:text>
					</td>
					<td id="serverNameTd">
						<input type="hidden" name="serverName" value="${secondaryServer.serverName}"/>
						${secondaryServer.serverName}
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="secondaryServer.serverIp"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="serverIp" value="${secondaryServer.serverIp}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="secondaryServer.serverPort"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="serverPort" name="serverPort" value="${secondaryServer.serverPort}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="secondaryServer.configDbServerCode"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="1" name="searchDbName" size="50"/>
						<input type="hidden" name="configDbServerCode" value="${secondaryServer.configDbServerCode}" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="secondaryServer.logDbServerCode"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="2" name="searchDbName" size="50"/>
						<input type="hidden" name="logDbServerCode" value="${secondaryServer.logDbServerCode}" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="secondaryServer.cacheDbServerCode"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="3" name="searchDbName" size="24"/>
						<input type="hidden" name="cacheDbServerCode" value="${secondaryServer.cacheDbServerCode}" />
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
						maxlength:32
					},
					serverPort:{
						required: true,
						digits:true
					},
					searchDbName:{
						required: true,
						maxlength:64
					}
				}
			})
		});
	</script>
</html>