<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@page import="java.util.*" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateActivityJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
	<script src="../../js/jquery/jquery.min.js"></script>
	<script type="text/javascript">
		function isSubmit(){
			if(window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
		}
		
		function addOpenTime() {
			var startTime = document.getElementById("startOpenTime");
			var endTime = document.getElementById("endOpenTime"); 			
			var openTime = document.getElementById("openTime");
			
			if (startTime.value.length == 0 || endTime.value.length == 0) {
				return;
			}
			
			if (openTime.value.length == 0) {
				openTime.value = startTime.value + '-' + endTime.value;
			} else {
				openTime.value = openTime.value + '|' + startTime.value + '-' + endTime.value;
			}
		}
		
		function clearOpenTime() {
			document.getElementById("openTime").value = '';
		}
		
	</script>
 <body>
		&nbsp;

		<form action="updateActivity?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="updateActivityJsp.title"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="activityId" name="activityId" value="${activity.activityId}"/>
						<s:text name="activity.activityId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						${activity.activityId}
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.activityShowType"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="activityShowType" value="${activity.activityShowType}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.activityType"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="hidden" name="activityType" value="${activity.activityType}" />
						<s:text name="%{'activity.activityType_'+ activity.activityType}"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.activityName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="activityName" size="50" name="activityName" value="${activity.activityName}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.activityTitle"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="activityTitle" name="activityTitle" value="${activity.activityTitle}" size="50"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.activityDesc"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="activityDesc" name="activityDesc" value="${activity.activityDesc}" size="50"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.startTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="startTime" name="startTime" value="<s:if test="activity.startTime != null"><s:text name="format.date_time"><s:param value="activity.startTime"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){endTime.focus();}})"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.endTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
					<input type="text" id="endTime" name="endTime" value="<s:if test="activity.endTime != null"><s:text name="format.date_time"><s:param value="activity.endTime"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.openWeeks"></s:text><s:text name="colon"></s:text>	
					</td>
					<td>
						<input type="text" id="openWeeks" name="openWeeks" value="${activity.openWeeks}" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.openTime"></s:text><s:text name="colon"></s:text>	
					</td>
					<td>
						<s:text name="activity.startTime"></s:text><s:text name="colon"></s:text>
						<input type="text" id="startOpenTime" name="startOpenTime" class="Wdate" style="width:60px" onfocus="WdatePicker({dateFmt:'HH:mm', startDate:'00:00', onpicked:function(){endOpenTime.focus();}})"/>
						<s:text name="activity.endTime"></s:text><s:text name="colon"></s:text>
						<input type="text" id="endOpenTime" name="endOpenTime" class="Wdate" style="width:60px" onfocus="WdatePicker({dateFmt:'HH:mm', startDate:'00:00', minDate:'#F{$dp.$D(\'startOpenTime\')}'})"/>
						<input type="button" onclick="addOpenTime()" value="<s:text name='add'></s:text>" class="button" />
						<br/><input type="text" size="34" readonly="readonly" id="openTime" name="openTime" value="${activity.openTime}" />
						&nbsp;<input type="button" onclick="clearOpenTime()" value="<s:text name='clear'></s:text>" class="button" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.param"></s:text><s:text name="colon"></s:text>	
					</td>
					<td>
						<s:if test="activity.activityType == 100 || activity.activityType == 18">
						    
							<s:text name="奖励翻倍"/>
							<input type="text" id="beiShu1" name="beiShu1" value="${beiShu1}" />
							<s:text name="开始时间"/>
							<input type="text" id="startTime1" name="startTime1" value="<s:if test="startTime1 != null"><s:text name="format.date_time"><s:param value="startTime1"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){endTime.focus();}})"/>
							<s:text name="结束时间"/>
							<input type="text" id="endTime1" name="endTime1" value="<s:if test="endTime1 != null"><s:text name="format.date_time"><s:param value="endTime1"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"/>
							<br/>
							
							<s:text name="次数翻倍"/>
							<input type="text" id="beiShu2" name="beiShu2" value="${beiShu2}" />
							<s:text name="开始时间"/>
							<input type="text" id="startTime2" name="startTime2" value="<s:if test="startTime2 != null"><s:text name="format.date_time"><s:param value="startTime2"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){endTime.focus();}})"/>
							<s:text name="结束时间"/>
							<input type="text" id="endTime2" name="endTime2" value="<s:if test="endTime2 != null"><s:text name="format.date_time"><s:param value="endTime2"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"/>
						
						</s:if>
						<s:else>
							<input type="text" id="param" name="param" value="${activity.param}" />
						</s:else>					
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.display"></s:text><s:text name="colon"></s:text>	
					</td>
					<td>
						<select id="display" name="display" class="select" >
							<s:generator separator="," val="%{getText('activity.display_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="activity.display == #str">selected=selected</s:if>>
										<s:text name="%{'activity.display_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.status"></s:text><s:text name="colon"></s:text>	
					</td>
					<td>
						<select id="status" name="status" class="select" >
							<s:generator separator="," val="%{getText('activity.status_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="activity.status == #str">selected=selected</s:if>>
										<s:text name="%{'activity.status_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.sort"></s:text><s:text name="colon"></s:text>	
					</td>
					<td>
						<input type="text" id="sort" name="sort" value="${activity.sort}" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.imgId"></s:text><s:text name="colon"></s:text>	
					</td>
					<td>
						<input type="text" id="imgId" name="imgId" value="${activity.imgId}" />
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
</html>