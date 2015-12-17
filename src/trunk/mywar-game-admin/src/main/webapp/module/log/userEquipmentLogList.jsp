<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userEquipmentLogListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script>
		
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script type="text/javascript">
			function getEquipmentBack(userEquipmentLogId){
				var ajaxobj = new Ajax();
			    ajaxobj.url="module/log/getEquipmentBack?userEquipmentLogId=" + userEquipmentLogId;
			    ajaxobj.callback=function(){
			    	//alert("ajaxobj.gettext()=="+ajaxobj.gettext());
				    var responseMsg = eval('(' + ajaxobj.gettext() + ')');
				    //alert("responseMsg=="+responseMsg);
					alert(responseMsg.erroDescrip);
					if (responseMsg.erroCodeNum == 0) {
				    	var tr = document.getElementById(userEquipmentLogId);
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
		<form action="userEquipmentLogList?isCommit=T" method="post" onsubmit="return dateMust()">
			<table>
				<tr>
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
						<s:text name="userEquipmentLog.type"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="searchType" class="select">
							<s:generator separator="," val="%{getText('userResourceLog.operation_value')}">
								<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
								<s:iterator var="str">
									<option value="${str}" <s:if test="searchType == #str">selected=selected</s:if>>
										<s:text name="%{'userResourceLog.operation_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<input type="text" id="startDate" name="startDate" value="<s:if test="startDate != null"><s:text name="format.date_time"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){endDate.focus();}})"/>
						<s:text name="timeTo"></s:text>
						<input type="text" id="endDate" name="endDate" value="<s:if test="endDate != null"><s:text name="format.date_time"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}'})"/>
					</td>
					
					<td>
						<s:text name="pageSize"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
					</td>
					
					<td colspan="2">
						<input type="submit" value="<s:text name="search"></s:text>"/>
					</td>
				</tr>
			</table>
		</form>
		<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center id="table">
			<tr class="border">
				<td class="td_title" colspan="50" >
					<center><s:text name="userEquipmentLogListJsp.title"></s:text> &nbsp;</center>
				</td>
			</tr>
			<tr class="border" >
				<td>
					序号
				</td>
				<td>
					<s:text name="userEquipmentLog.userName"></s:text>
				</td>
				<td>
					<s:text name="userEquipmentLog.lodoId"></s:text>
				</td>
				<td>
					<s:text name="userEquipmentLogListJsp.equipmentName"></s:text>
				</td>
				<td>
					<s:text name="userEquipmentLog.equipmentLevel"></s:text>
				</td>
				<td>
					<s:text name="userEquipmentLog.type"></s:text>
				</td>
				<td>
					<s:text name="userEquipmentLog.operationTime"></s:text>
				</td>
				<!-- 
				<td>
					<s:text name="userEquipmentLogListJsp.getBack"></s:text>
				</td>
				 -->
			</tr>
			
			<s:iterator var="userEquipmentLog" value="userEquipmentLogList" status="sta">
				<tr id="${userEquipmentLog.userEquipmentLogId}">
					
					<td>
							${sta.index+1}
					</td>
					
					<td>
							${userIdUserMap[userEquipmentLog.userId].userName}
					</td>
				
					<td>
							${userIdUserMap[userEquipmentLog.userId].lodoId}
					</td>
					
					<td>
							${equipmentIdNameMap[userEquipmentLog.equipmentId]}
					</td>
					
					<td>
							${userEquipmentLog.equipmentLevel}
					</td>
					
					<td>
						<s:text name="%{'userResourceLog.operation_'+#userEquipmentLog.type}"></s:text>
					</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#userEquipmentLog.operationTime"></s:param>
						</s:text>
					</td>
					<!-- 
					<td>
						<s:if test="#userEquipmentLog.type == 5">
							<input type="button" value="<s:text name="userEquipmentLogListJsp.getBack"></s:text>" onclick="getEquipmentBack(${userEquipmentLog.userEquipmentLogId})" class="button">
						</s:if>
					</td>
					 -->
				</tr>
			</s:iterator>
				<td colspan="100">
					<aldtags:pageTag paraStr="isCommit,T,userId,${userId},lodoId,${lodoId},searchType,${searchType},pageSize,${pageSize}"  datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}"/>
				</td>
		</table>
	</body>
</html>