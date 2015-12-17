<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加多倍活动配置</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
	<script type="text/javascript">
	</script>
	<body>
		&nbsp;
		<form action="addMultipleActivityConfig?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							添加多倍活动配置修改 &nbsp;
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="multipleActivityConfig.activityType"></s:text><s:text name="colon"></s:text>
						<input type="hidden" name="configId" value="${multipleActivityConfig.configId}">
					</td>
					<td>
						<select name="activityType" class="select">
							<s:generator separator="," val="%{getText('multipleActivityConfig.activityType_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="multipleActivityConfig.activityType == #str">selected=selected</s:if>>
										<s:text name="%{'multipleActivityConfig.activityType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="multipleActivityConfig.multiple"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="multiple" value="${multipleActivityConfig.multiple}" class="maxLife" size="11" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="multipleActivityConfig.startDate"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="startDate" name="startDate" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="multipleActivityConfig.startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',onpicked:function(){endDate.focus();}})"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="multipleActivityConfig.endDate"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="endDate" name="endDate" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="multipleActivityConfig.endDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="multipleActivityConfig.startTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="startTime" name="startTime" value="<s:if test="startTime != null"><s:text name="format.date_hms"><s:param value="startTime"></s:param></s:text></s:if>" class="Wdate" style="width:100px" onfocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',onpicked:function(){endTime.focus();}})"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="multipleActivityConfig.endTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="endTime" name="endTime" value="<s:if test="endTime != null"><s:text name="format.date_hms"><s:param value="multipleActivityConfig.endTime"></s:param></s:text></s:if>" class="Wdate" style="width:100px" onfocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss'})"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="multipleActivityConfig.userLevelLimit"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="userLevelLimit" value="${multipleActivityConfig.userLevelLimit}" class="maxLife" size="11" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')"/>
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