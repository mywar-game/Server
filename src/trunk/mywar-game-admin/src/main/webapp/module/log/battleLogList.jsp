<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="battleLogListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script>
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		var erroDescrip = '${erroDescrip}';
		if (erroDescrip != "") {
			alert(erroDescrip);
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
	<body>
		<form action="battleLogList?isCommit=T" method="post" onsubmit="return dateMust()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center id="table">
				<tr class="border">
					<td class="td_title" colspan="100">
						<center>
							<s:text name="battleLogListJsp.title"></s:text> &nbsp;
							<font color="red">${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr class="border">
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
						<center>
							<s:text name="battleLogListJsp.battleType"></s:text><s:text name="colon"></s:text>
						</center>
					</td>
					<td colspan="2">
						<!-- <select  name="battleType" onchange="submit();"> -->
						<select  name="battleType">
							<option value=""><s:text name="battleLogListJsp.choseBattleType"></s:text></option>
							<s:generator separator="," val="%{getText('battleLog.type_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="battleType == #str">selected=selected</s:if>>
										<s:text name="%{'battleLog.type_'+#str}"></s:text>
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
						<center>
							<s:text name="pageSize"></s:text><s:text name="colon"></s:text>
						</center>
					</td>
					<td>
						<input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
					</td>
					
					<td align="center">
						<input type="submit" value="<s:text name="search"></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>

		<table cellpadding="15" cellspacing="1" border="0" width="100%" align=center id="table">
			<tr>
				<td>
					<s:text name="battleLog.battleId"></s:text>
				</td>
				<td>
					<s:text name="battleLog.userId"></s:text>
				</td>
				<td>
					<s:text name="battleLog.userLevel"></s:text>
				</td>
				<td>
					<s:text name="battleLog.targetId"></s:text>
				</td>
				<td>
					<s:text name="battleLog.type"></s:text>
				</td>
				<td>
					<s:text name="battleLog.flag"></s:text>
				</td>
				<td>
					<s:text name="battleLog.createdTime"></s:text>
				</td>
			</tr>
			<s:iterator var="battleLog" value="battleLogList" status="i">
				<tr id="i.index">

					<td>
						${i.index+1}
					</td>
					<td>
						${battleLog.userId}
					</td>
					<td>
						${battleLog.userLevel}
					</td>
					<td>
						${battleLog.targetId}
					</td>
					<td>
						<s:text name="%{'battleLog.type_'+#battleLog.type}"></s:text>
					</td>
					<td>
						<s:if test="#battleLog.flag==1">
							<s:text name="%{'battleLog.flag_win'}"></s:text>
						</s:if>
						<s:else>
							<s:text name="%{'battleLog.flag_fail'}"></s:text>
						</s:else>
					</td>
					<td>
						${battleLog.creatTime}
					</td>
				</tr>
				
			</s:iterator>
			<tr>
				<td colspan="14">
					<aldtags:pageTag paraStr="isCommit,T,userId,${userId},lodoId,${lodoId},pageSize,${pageSize},battleType,${battleType}" datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}"/>
				</td>
			</tr>
		</table>
	</body>
</html>