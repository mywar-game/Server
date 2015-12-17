<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="addActivityJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	
	<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script src="../../js/jquery/jquery.min.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.core.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.position.js"></script>
	<script src="../../js/jquery/ui/jquery.ui.autocomplete.js"></script>
	<script type="text/javascript" >
		function selectFun(type) {
			var tp = type.value;
			document.getElementById("sort").value = tp;
			document.getElementById("imgId").value = tp;
			
			// 限时招募 param : 20,260,150,10,10,116
			if (tp == 31) {
				document.getElementById("activityShowType").value = 2;
				document.getElementById("param").value = '20,260,150,10,10,116';
				document.getElementById("activityDesc").value = '在活动时间内，所有招募将获得高级英雄（参数配置：免费招募时间间隔，招募一次花费钻石数，积分达到150积分的倍数送钻石免费招募一次，第十次必送S级英雄，每次招募加10积分，第116次招募必送超级偶像）';
			// 热血刮刮乐
			} else if (tp == 32) {
				document.getElementById("activityShowType").value = 2;
				document.getElementById("param").value = '20,100,24';
				document.getElementById("activityDesc").value = '在活动时间内，可以随机获得道具池中的道具（参数配置：刮一次20钻石，刮五次100钻石，每24小时免费一次）';
			//累计登录
			}else if(tp == 41){
			    document.getElementById("activityShowType").value = 2;
			   // document.getElementById("param").value = '20';
			    document.getElementById("activityDesc").value ='根据累计登录天数的不同，获得相对应的奖励';
			}
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
		<form action="addActivity?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="addActivityJsp.title"></s:text>
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
						<input type="text" name="activityShowType" id = "activityShowType" value="${activity.activityShowType}"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.activityType"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select id="activityType" name="activityType" class="select" onchange="selectFun(this)" >
							<s:generator separator="," val="%{getText('activity.activityType_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="activity.activityType == #str">selected=selected</s:if>>
										<s:text name="%{'activity.activityType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
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
						<input type="text" id="activityTitle" name="activityTitle" size="50"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.activityDesc"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="activityDesc" name="activityDesc" size="50"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.startTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="startTime" name="startTime" class="Wdate" style="width:160px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', startDate:'%y-%M-%d 00:00:00', onpicked:function(){endTime.focus();}})"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.endTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
					<input type="text" id="endTime" name="endTime" class="Wdate" style="width:160px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', startDate:'%y-%M-%d 00:00:00', minDate:'#F{$dp.$D(\'startTime\')}'})"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.openWeeks"></s:text><s:text name="colon"></s:text>	
					</td>
					<td>
						<input type="text" id="openWeeks" name="openWeeks"/>
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
						<br/><input type="text" size="34" readonly="readonly" id="openTime" name="openTime"/>
						&nbsp;<input type="button" onclick="clearOpenTime()" value="<s:text name='clear'></s:text>" class="button" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.param"></s:text><s:text name="colon"></s:text>	
					</td>
					<td>
						<input type="text" id="param" name="param"/>
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
						<input type="text" id="sort" name="sort"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="activity.imgId"></s:text><s:text name="colon"></s:text>	
					</td>
					<td>
						<input type="text" id="imgId" name="imgId"/>
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