<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title><s:text name="baPveConstantListJsp.title"></s:text></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script language="javascript" src="../../js/json.js"></script>
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		function reflashbapvecache() {
			var ajaxobj = new Ajax();
			ajaxobj.url="reflashConstantCache?cacheType=3&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('<s:text name="action.success"><s:param><s:text name="baPveConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				} else {
					alert('<s:text name="action.fail"><s:param><s:text name="baPveConstantListJsp.reflashConstantCache"></s:text></s:param></s:text>');
				}
		    }
			ajaxobj.send();
		}
		
		function init(){
			var bigName = "${bigName}";
			var smallName = "${smallName}";
			if (bigName != "" || smallName != "") {
				var td = document.getElementById("page");
				//alert(td.innerHTML);
				var childNodes = td.childNodes;
				for(var k = 0; k<childNodes.length; k++){
					if(childNodes[k].nodeType==1 && childNodes[k].nodeName=="A"){
						childNodes[k].href += "&bigName="+ encodeURI(encodeURI(bigName))+"&smallName="+ encodeURI(encodeURI(smallName));
					}
				}
			}				
		}
		
  		function del(pveBigId,pveSmallId,pveSmallName){
			if(window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='pveSmallName'></s:text></s:param><s:param>"+pveSmallName+"</s:param></s:text>")){
				var ajaxobj = new Ajax();
		        ajaxobj.url="delBaPveConstant?pveBigId="+pveBigId+"&pveSmallId="+pveSmallId;
		        ajaxobj.callback=function(){
		        	//alert(ajaxobj.gettext());
		        	var tr = document.getElementById(pveBigId+","+pveSmallId);
					var table = document.getElementById("table");
			        table.deleteRow(tr.rowIndex);  
		        }
			    ajaxobj.send();
			}
		}
		
		function update(pveBigId,pveSmallId){
			window.location.href="updateBaPveConstant?pveBigId="+pveBigId+"&pveSmallId="+pveSmallId;
		}
		
		function detail(pveBigId,pveSmallId){
			window.location.href="baPveDetailList?pveBigId="+pveBigId+"&pveSmallId="+pveSmallId;
		}
		
		function  add(){
			window.location.href="addBaPveConstant";
		}
	</script>
	<body onload="init()">
	    <input type="hidden" value="<s:text name='addbaPveConstantJsp.title'></s:text>" class="button" onclick="add()" />
	    <input type="button" value='<s:text name="baPveConstantListJsp.reflashConstantCache"></s:text>' class="button" onclick="reflashbapvecache()" />
	    <form action="baPveConstantList" method="post">
	    	<s:text name="pveBigName"></s:text>：<input type="text" name="bigName" value="${bigName}">
	    	<s:text name="pveSmallName"></s:text>：<input type="text" name="smallName" value="${smallName}">
	    	<input type="submit" value="<s:text name="search"></s:text>" class="button" />
	    </form>
	    <table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
	    	<tr class="border">
				<td class="td_title" colspan="40" align="center">
					<center>
						<s:text name="baPveConstantListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="pveBigId"></s:text>
				</td>
				<td>
					<s:text name="pveBigName"></s:text>
				</td>
				<td>
					<s:text name="pveSmallId"></s:text>
				</td>
				<td>
					<s:text name="pveSmallName"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.pveDesc"></s:text>
				</td>
				<td>
					<s:text name="enterLevel"></s:text>
				</td>
				<td>
					<s:text name="maxRewardNum"></s:text>
				</td>
				<td>
					<s:text name="minRewardNum"></s:text>
				</td>
				<td>
					<s:text name="vipRewardAdd"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.maxNum"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.minNum"></s:text>
				</td>
				<td>
					<s:text name="waveNum"></s:text>
				</td>
				<td>
					<s:text name="addExp"></s:text>
				</td>
				<td>
					<s:text name="addRenow"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.bossType"></s:text>
				</td>
				<td>
					<s:text name="monsterConstant.modelId"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.checkPointType"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.failNum"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.arenaId"></s:text>
				</td>
				<td>
					<s:text name="baPveConstant.startMaxTime"></s:text>
				</td>
				
				<td width="35">
					<s:text name="baPveConstantListJsp.detail"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
			</tr>
				
			<s:iterator var="baPveConstant" status="sta" value="baPveConstantList">
				<tr id="${baPveConstant.id.pveBigId},${baPveConstant.id.pveSmallId}">
					<td>
						${baPveConstant.id.pveBigId}
					</td>
					<td>
						${baPveConstant.pveBigName}
					</td>
					<td>
						${baPveConstant.id.pveSmallId}
					</td>
					<td>
						${baPveConstant.pveSmallName}
					</td>
					<td>
						${baPveConstant.pveDesc}
					</td>
					<td>
						${baPveConstant.enterLevel}
					</td>
					<td>
						${baPveConstant.maxRewardNum}
					</td>
					<td>
						${baPveConstant.minRewardNum}
					</td>
					<td>
						${baPveConstant.vipRewardAdd}
					</td>
					<td>
						${baPveConstant.maxNum}
					</td>
					<td>
						${baPveConstant.minNum}
					</td>
					<td>
						${baPveConstant.waveNum}
					</td>
					<td>
						${baPveConstant.addExp}
					</td>
					<td>
						${baPveConstant.addRenow}
					</td>
					<td>
						<s:text name="%{'baPveConstant.bossType_'+#baPveConstant.bossType}"></s:text>
					</td>
					<td>
						${baPveConstant.modelId}
					</td>
					<td>
						<s:text name="%{'baPveConstant.checkPointType_'+#baPveConstant.checkPointType}"></s:text>
					</td>
					<td>
						${baPveConstant.failNum}
					</td>
					<td>
						${mapAreaIdNameMap[baPveConstant.arenaId]}
					</td>
					<td>
						${baPveConstant.startMaxTime}秒
					</td>
					<td>
						<a href="#" onclick="detail(${baPveConstant.id.pveBigId},${baPveConstant.id.pveSmallId})"><s:text name="baPveConstantListJsp.detail"></s:text></a>
					</td>
					<td>
						<a href="#" onclick="update(${baPveConstant.id.pveBigId},${baPveConstant.id.pveSmallId})"><s:text name="update"></s:text></a>
					</td>
					<td>
						<a href="#" onclick="del(${baPveConstant.id.pveBigId},${baPveConstant.id.pveSmallId},'${baPveConstant.pveSmallName}')"><s:text name="delete"></s:text></a>
					</td>
				</tr>
			</s:iterator>
			
	   		<tr id="pageTagTr">
				<td colspan="40" id="page">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>
