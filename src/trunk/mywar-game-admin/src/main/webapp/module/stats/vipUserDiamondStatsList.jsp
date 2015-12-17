<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<style type="text/css">
		td {
			text-align: center;
		}
		</style>		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/stats_WdatePicker.js"></script>  
		<script>
			function changeSelect(obj) {
				var sType = document.getElementById("sType").value;
				if (sType == "T") {
					var sName = 1;
					window.location.href="vipUserDiamondStatsList?isCommit=T";
				} else if (sType == "F") {
					var sName = 2;
					window.location.href="vipUserDiamondStatsList?isCommit=F";
				}
			}
		</script>
	</head>
	
	<body>
		<div>
			<form action="vipUserDiamondStatsList" method="post">
				<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
				<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
				<s:text name="timeTo"></s:text>
				<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
				
				<s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
				<select name="isCommit" id="sType" onchange="changeSelect(this)" class="select">
					<option value="TF"><s:text name="pleaseSelect"></s:text></option>
					<option value="T" <s:if test='isCommit=="T"'>selected=selected</s:if>>产出</option>
					<option value="F"  <s:if test='isCommit=="F"'>selected=selected</s:if>>消耗</option>
				</select>
				<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
			</form>
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="22" align="center">
						<center>
							活跃用户钻石统计
						</center>
					</td>
				</tr>
				<tr>				
					<td>
						时间
					</td>
					<td>
						vip1以上活跃人数
					</td>
					<td>
						vip1以上人数
					</td>
					<td>
						操作
					</td>
					<td>
						钻石
					</td>
					<td>
						消耗、获得
					</td>			
				</tr>


				<s:iterator var="stats" value="statsList">
					<tr>
						<td>
							${stats.date}
						</td>
						<td>
							${stats.actityCount}
						</td>
						<td>
							${stats.count}
						</td>
						<td>
							<s:text name="%{'userResourceLog.operation_'+#stats.type}"></s:text>
						</td>
						<td>
							${stats.diamond}
						</td>
						<td>					
							<s:if test="#stats.caterory == 1">
								获得
							</s:if>
							<s:if test="#stats.caterory == 2">
								消耗
							</s:if>
								
						</td>
						
					</tr>
				</s:iterator>
				
				<tr>
					<td colspan="35">
						<aldtags:pageTag datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}" para1="pageSize" value1="${pageSize}"/>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>