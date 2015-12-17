<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="tTreasureConstantListJsp.title"></s:text></title>

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
			ajaxobj.url="reflashConstantCache?cacheType=20&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="tTreasureConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="tTreasureConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		
		function del(treasureId) {
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="treasureConstant.treasureId"></s:text></s:param><s:param>'+treasureId+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delTTreasureConstant?treasureId=" + treasureId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(treasureId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(treasureId) {
			window.location.href = "updateTTreasureConstant?treasureId=" + treasureId;
		}
	
		function add() {
			window.location.href = "addTTreasureConstant";
		}
		
		function init(){
			var name = "${name}";
			if (name != "") {
				var td = document.getElementById("page");
				//alert(td.innerHTML);
				var childNodes = td.childNodes;
				for(var k = 0; k<childNodes.length; k++){
					if(childNodes[k].nodeType==1 && childNodes[k].nodeName=="A"){
						childNodes[k].href += "&name="+ encodeURI(encodeURI(name));
					}
				}
			}				
		}
	</script>
	<body onload="init()">
		<input type="button" value="<s:text name="addTTreasureConstantJsp.title"></s:text>" class="button" onclick=add(); />
		<input type="button" value='<s:text name="tTreasureConstantListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashconstantcache()" />
		<form action="tTreasureConstantList" method="post">
	    	<s:text name="treasureConstant.treasureName"></s:text><s:text name="colon"></s:text>
	    	<input type="text" name="name" value="${name}">
	    	<input type="submit" value="<s:text name="search"></s:text>" class="button" />
	    </form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="20" align="center">
					<center>
						<s:text name="tTreasureConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="treasureConstant.treasureId"></s:text>
				</td>
				<td>
					<s:text name="treasureConstant.treasureName"></s:text>
				</td>
				<td>
					<s:text name="treasureConstant.treasureDesc"></s:text>
				</td>
				<td>
					<s:text name="treasureConstant.treasureType"></s:text>
				</td>
				<td>
					<s:text name="treasureConstant.salePrice"></s:text>
				</td>
				<td>
					<s:text name="treasureConstant.canBuy"></s:text>
				</td>
				<td>
					<s:text name="treasureConstant.price"></s:text>
				</td>
				<td>
					<s:text name="treasureConstant.isOverlay"></s:text>
				</td>
				<td>
					<s:text name="treasureConstant.overlayNumMax"></s:text>
				</td>
				<td>
					<s:text name="treasureConstant.value"></s:text>
				</td>
				<td>
					<s:text name="treasureConstant.isBinding"></s:text>
				</td>
				<td>
					<s:text name="treasureConstant.medalBuy"></s:text>
				</td>
				<td>
					<s:text name="treasureConstant.pathWay"></s:text>
				</td>
				
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="treasureConstant" value="treasureConstantList">
				<tr id="${treasureConstant.treasureId}">
					<td>
						${treasureConstant.treasureId}
					</td>
					<td>
						${treasureConstant.treasureName}
					</td>
					<td>
						${treasureConstant.treasureDesc}
					</td>
					<td>
						<s:text name="%{'treasureType_'+#treasureConstant.treasureType}"></s:text>
					</td>
					<td>
						${treasureConstant.salePrice}
					</td>
					<td>
						<s:text name="%{'canBuy_'+#treasureConstant.canBuy}"></s:text>
					</td>
					<td>
						${treasureConstant.price}
					</td>
					<td>
						<s:text name="%{'isOverlay_'+#treasureConstant.isOverlay}"></s:text>
					</td>
					<td>
						${treasureConstant.overlayNumMax}
					</td>
					<td>
						${treasureConstant.value}
					</td>
					<td>
						<s:text name="%{'isBinding_'+#treasureConstant.isBinding}"></s:text>
					</td>
					<td>
						<s:if test="#treasureConstant.medalBuy == 0">
							<s:text name="treasureConstant.medalBuy_0"></s:text>
						</s:if>
						<s:else>
							${treasureConstant.medalBuy}
						</s:else>
					</td>
					<td>
						<s:generator separator="," val="#treasureConstant.pathWay">
							<s:iterator var="one">
								<s:text name="%{'treasureConstant.pathWay_'+#one}"></s:text><br/>
							</s:iterator>
						</s:generator>
					</td>

					<td>
						<a href="#" onclick='del("${treasureConstant.treasureId}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${treasureConstant.treasureId}")'><s:text name="update"></s:text></a>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="14" id="page">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>