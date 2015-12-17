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
	</head>
	
	<body>
		<div>
			<form action="cheatStatsList" method="post">
				<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
				<input type="text" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
				<s:text name="timeTo"></s:text>
				<input type="text" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
				
				<s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
				<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
			</form>
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="22" align="center">
						<center>
							疑似作弊统计
						</center>
					</td>
				</tr>
				<tr>
					
					<td>
						作弊时间点
					</td>
					<td>
						用户
					</td>
					<td>
						操作
					</td>
					<td>
						道具类型
					</td>
					<td>
						道具ID
					</td>
					<td>
						重复获得次数
					</td>
				</tr>


				<s:iterator var="stats" value="statsList">
					<tr>
						<td>
							${stats.createTime}
						</td>
						<td>
							${stats.userId}
						</td>
						<td>
							<%-- ${stats.operation} --%>
							<s:text name="%{'userResourceLog.operation_'+#stats.operation}"></s:text>
						</td>
						<td>
							<%-- ${stats.treasureType} --%>
							<s:text name="%{'userTreasureLog.treasureType_'+#stats.treasureType}"></s:text>
						</td>
						<td>
							<%-- ${stats.treasureId} --%>
							<s:if test="#stats.treasureType == 1">
								钻石
							</s:if>
							<s:if test="#stats.treasureType == 2">
								金币
							</s:if>
							<s:if test="#stats.treasureType == 4">
								体力
							</s:if>
							<s:elseif test="#stats.treasureType == 5">
								经验值
							</s:elseif>
							<s:elseif test="#stats.treasureType == 9">
								<%-- <a href="../dataconfig/tTreasureConstantList?id=${userTreasureLog.treasureId}"> --%>
								耐力		
							</s:elseif>  
							<s:elseif test="#stats.treasureType == 10">
								<%-- <a href="../dataconfig/tTreasureConstantList?id=${userTreasureLog.treasureId}"> --%>
								英灵
							</s:elseif>
							<s:elseif test="#stats.treasureType == 12">
								<%-- <a href="../dataconfig/tTreasureConstantList?id=${userTreasureLog.treasureId}"> --%>
								声望
								<!-- </a> -->
							</s:elseif>
							<s:elseif test="#stats.treasureType == 13">
								<%-- <a href="../dataconfig/tTreasureConstantList?id=${userTreasureLog.treasureId}"> --%>
								星星								
							</s:elseif>
							<s:elseif test="#stats.treasureType == 14">
								商城积分
							</s:elseif>
							<s:elseif test="#stats.treasureType == 2001">
								<!-- 装备 -->
								${equipIdNameMap[stats.treasureId]}
							</s:elseif>
							<s:elseif test="#stats.treasureType == 3001">
								<!-- 武将 -->
								${heroIdNameMap[stats.treasureId]}
							</s:elseif>
							<s:elseif test="#stats.treasureType == 9001">
								<!-- 神器 -->
								${artifactIdNameMap[stats.treasureId]}
							</s:elseif>
							<s:else>
								${treasureIdNameMap[stats.treasureId]}
							</s:else>	
						</td>
						<td>
							${stats.times}
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