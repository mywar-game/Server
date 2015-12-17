<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userHeroLogListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script>
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script type="text/javascript">
			function getHeroBack(userHeroLogId){
				var ajaxobj = new Ajax();
			    ajaxobj.url="module/log/getHeroBack?userHeroLogId=" + userHeroLogId;
			    ajaxobj.callback=function(){
			    	//alert("ajaxobj.gettext()=="+ajaxobj.gettext());
				    var responseMsg = eval('(' + ajaxobj.gettext() + ')');
				    //alert("responseMsg=="+responseMsg);
					alert(responseMsg.erroDescrip);
					if (responseMsg.erroCodeNum == 0) {
				    	var tr = document.getElementById(userHeroLogId);
						var table = document.getElementById("table");
						table.deleteRow(tr.rowIndex);
					}
			    }
			    ajaxobj.send();
			}
			function dateMust(){
				var userId = document.getElementById("userId").value;
				var lodoId = document.getElementById("lodoId").value;
				var startDate = document.getElementById("startDate").value;
				var endDate = document.getElementById("endDate").value;
				//alert("startDate "+startDate);
				//alert("endDate "+endDate);
				if(userId=="" && lodoId==0){
					alert("请输入一个用户编号或游戏ID");
					return false;
				}
				if(startDate =="" || endDate == ""){
					alert("请选择查询时间段");
					return false;
				}
				return true;
			}
		</script>
	</head>
	<body>
		<form action="userHeroLogList?isCommit=T" method="post" onsubmit="return dateMust()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="11" >
						<center><s:text name="userHeroLogListJsp.title"></s:text> &nbsp;</center>
					</td>
				</tr>
				<tr class="border" >
					<td>
						<s:text name="log.userIdSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="userId" name="userId" value="${userId}"/>
					</td>
					 <td>
						<s:text name="log.lodoSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="lodoId" name="lodoId" value="${lodoId}"/>
					</td>
					<td>
						<s:text name="userTreasureLog.category"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select id="logType" name="logType" class="select">
							<s:generator separator="," val="%{getText('userHeroLog.type_value')}">
								<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
								<s:iterator var="str">
									<option value="${str}" <s:if test="logType == #str">selected=selected</s:if>>
										<s:text name="%{'userHeroLog.type_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
					<td>
						<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<input type="text" id="startDate" name="startDate" value="<s:if test="startDate != null"><s:text name="format.date_time"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){endDate.focus();}})"/>
						<s:text name="timeTo"></s:text>
						<input type="text" id="endDate" name="endDate" value="<s:if test="endDate != null"><s:text name="format.date_time"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}'})"/>
					</td>
					
					<td align="center">
						<input type="submit" value="<s:text name="search"></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
		
		<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center id="table">
			<tr>
				<td>
					<s:text name="userHeroLog.userId"></s:text>
				</td>
				<td>
					<s:text name="userHeroLog.userHeroId"></s:text>
				</td>
				<td>
					<s:text name="userHeroLog.userName"></s:text>
				</td>
				<td>
					<s:text name="userHeroLog.lodoId"></s:text>
				</td>
				<td>
					<s:text name="userHeroLog.heroName"></s:text>
				</td>
				
				<td>
					<s:text name="userHeroLog.exp"></s:text>
				</td>
				<td>
					<s:text name="userHeroLog.level"></s:text>
				</td>
				<td>
					<s:text name="userHeroLog.expNum"></s:text>
				</td>
				<td>
					<s:text name="userHeroLog.pos"></s:text>
				</td>
				<td>
					<s:text name="userHeroLog.type"></s:text>
				</td>
				<td>
					<s:text name="userHeroLog.operationTime"></s:text>
				</td>
				<!-- 
				<td>
					<s:text name="userHeroLogListJsp.getBack"></s:text>
				</td>
				 -->
			</tr>
			<s:iterator var="userHeroLog" value="userHeroLogList">
				<tr id="${userHeroLog.userHeroLogId}">
					
					<td>
							${userHeroLog.userId}
					</td>
					<td>
							${userHeroLog.userHeroId}
					</td>
					
					<td>
							${userIdUserMap[userHeroLog.userId].userName}
					</td>
				
					<td>
							${userIdUserMap[userHeroLog.userId].lodoId}
					</td>
					
					
					<td>
							${userHeroLog.heroName}
					</td>
					
					<td>
							${userHeroLog.exp}
					</td>
					
					<td>
							${userHeroLog.level}
					</td>
					
					<td>
							${userHeroLog.expNum}
					</td>
					
					<td>
							${userHeroLog.pos}
					</td>
					
					<td>
						<s:text name="%{'userHeroLog.type_'+#userHeroLog.type}"></s:text>
					</td>
				
					<td>
						<s:text name="format.date_time">
							<s:param value="#userHeroLog.operationTime"></s:param>
						</s:text>
					</td>
					<!-- 
					<td>
						<s:if test="#userHeroLog.type == 5">
							<input type="button" value="<s:text name="userHeroLogListJsp.getBack"></s:text>" onclick="getHeroBack(${userHeroLog.userHeroLogId})" class="button">
						</s:if>
					</td>
					 -->
				
				</tr>
			</s:iterator>
				<tr>
				<td colspan="100">
					<aldtags:pageTag paraStr="isCommit,T,userId,${userId},lodoId,${lodoId},logType,${logType}" datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}"/>
				</td>
			</tr>
		</table>
	</body>
</html>