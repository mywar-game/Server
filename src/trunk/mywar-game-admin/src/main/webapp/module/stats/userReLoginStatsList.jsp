<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>再登录率统计</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script>  
	</head>
	<script type="text/javascript">
		function changeAllCheck(isCheck){
			//所有的input标签
			var allInputTags = document.getElementsByTagName("INPUT");
			var x = 0;
			for ( var i = 0; i < allInputTags.length; i++) {
				//如果input标签的那么是powerIdArr，那么就选中
				if(allInputTags[i].name == "serverIds") {
					allInputTags[i].checked = isCheck;
					x++;
				}
			}
			//alert(x);
		}
	</script>
	<body>
		<form action="userReLoginStatsList">
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="10" align="center">
						<center>
							再登录率统计
						</center>
					</td>
				</tr>
				<tr>
					<td>
						选择服务器
						<br/>
						<a href="javascript:;" onclick="changeAllCheck(true)">全选</a>
						<a href="javascript:;" onclick="changeAllCheck(false)">全不选</a>
					</td>
						<s:iterator value="%{@com.system.manager.DataSourceManager@getInstatnce().getGameServerMap()}" status="sta">
	<!--					最后一个要对齐格式-->
							<td <s:if test="#sta.last">colspan='<s:property value="6 - #sta.count%6 + 1"/>'</s:if>>
								<input name="serverIds" class="checkbox" value="${key}" <s:if test="serverIds.indexOf(key+'') != -1">checked="checked"</s:if> type="checkbox">
								${value.serverAlias}
		<!--						一行6个-->
								<s:if test="#sta.count%6 == 0">
									<tr>
								</s:if>
							</td>
						</s:iterator>
				</tr>
				<tr>
					<td>
						注册日期：
					</td>
					<td colspan="6">
						<input type="text" id="regStartDate" name="regStartDate" value="<s:if test="regStartDate != null"><s:text name="format.date_ymd"><s:param value="regStartDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({regStartDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',onpicked:function(){regEndDate.focus();}})"/>
						~
						<input type="text" id="regEndDate" name="regEndDate" value="<s:if test="regEndDate != null"><s:text name="format.date_ymd"><s:param value="regEndDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({regStartDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"/>				
					</td>
				</tr>
				<tr>
					<td>
						登陆日期：
					</td>
					<td colspan="6">
						<input type="text" id="startDate" name="startDate" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',onpicked:function(){endDate.focus();}})"/>
						~
						<input type="text" id="endDate" name="endDate" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" class="Wdate" style="width:160px" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"/>				
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
				</table>
				
				<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr>
					<td>
						服务器名
					</td>
					<td>
						注册人数
					</td>
					<td>
						再登陆人数
					</td>
					<td>
						每天登陆人数(总和)
					</td>
					<td>
						再登录率
					</td>
				</tr>
				
				<s:iterator value="map">
					<tr>
						<td>
							${key}	
						</td>
						<td>
							${value.regAmount}
						</td>
						<td>
							${value.reLoginAmount}
						</td>
						<td>
							${value.allDayAmount}
						</td>
						<td>
							<fmt:formatNumber type="percent" value="${value.reLoginAmount/value.regAmount}" pattern="#.##%" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</form>
	</body>
</html>