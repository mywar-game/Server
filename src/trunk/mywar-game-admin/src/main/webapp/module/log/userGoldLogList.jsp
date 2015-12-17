<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userGoldLogListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/ajax.js"></script>
		
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script>
		<script type="text/javascript">
			function changeTypeSelect(categorySelect){
				//alert("categorySelect=="+categorySelect);
				//当前category的值
				var category = categorySelect.value;
				var searchTypeTd = document.getElementById("searchType").parentNode;
				var tr = categorySelect.parentNode.parentNode;
				tr.deleteCell(searchTypeTd.cellIndex);
				var newSearchTypeTd = tr.insertCell(categorySelect.parentNode.cellIndex+2);
				var html = '';
				html +='<select id="searchType" name="searchType" class="select">';
				//获得
				if (category == 1) {
					<s:generator separator="," val="%{getText('userResourceLog.get_value')}">
						html +='<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>';
						<s:iterator var="str">
							html +='<option value="${str}"> <s:text name="%{'userResourceLog.operation_'+#str}"></s:text> </option>';
						</s:iterator>
					</s:generator>
				//使用
				} else if (category == 2) {
					<s:generator separator="," val="%{getText('userResourceLog.use_value')}">
						html +='<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>';
						<s:iterator var="str">
							html +='<option value="${str}"> <s:text name="%{'userResourceLog.operation_'+#str}"></s:text> </option>';
						</s:iterator>
					</s:generator>
				//未选择
				} else {
					html +='<option value="" selected="selected">请先选择类别</option>';
				}
				html +='</select>';
				newSearchTypeTd.innerHTML = html;
			}
			
			function dateMust(){
				var startDate = document.getElementById("startDate").value;
				var endDate = document.getElementById("endDate").value;
				//alert("startDate "+startDate);
				//alert("endDate "+endDate);
				if(startDate =="" || endDate == ""){
					alert("请选择查询时间段");
					return false;
				}
				return true;
			}
		</script>
	</head>
	<!-- 以后要注意lodoId 与  userId的区别 -->
	<body>
		<form action="userGoldLogList?isCommit=T" method="post" onsubmit="return dateMust()">
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
						<s:text name="userGoldLog.category"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select id="searchCategory" name="searchCategory" class="select" onchange="changeTypeSelect(this)">
							<s:generator separator="," val="%{getText('userGoldLog.category_values')}">
								<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
								<s:iterator var="str">
									<option value="${str}" <s:if test="searchCategory == #str">selected=selected</s:if>>
										<s:text name="%{'userGoldLog.category_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
					<td>
						<s:text name="userGoldLog.type"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select id="searchType" name="searchType" class="select">
							<s:if test="searchCategory != null">
									<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
									<s:if test="searchCategory == 1">
										<s:generator separator="," val="%{getText('userResourceLog.get_value')}">
										<s:iterator var="str">
											<option value="${str}" <s:if test="searchType == #str">selected=selected</s:if>>
												<s:text name="%{'userResourceLog.operation_'+#str}"></s:text>
											</option>
										</s:iterator>
									</s:generator>
									</s:if>
									<s:else>
										<s:generator separator="," val="%{getText('userResourceLog.use_value')}">
											<s:iterator var="str">
												<option value="${str}" <s:if test="searchType == #str">selected=selected</s:if>>
													<s:text name="%{'userResourceLog.operation_'+#str}"></s:text>
												</option>
											</s:iterator>
										</s:generator>
									</s:else>
							</s:if>
							<s:else>
									<option value="" selected="selected">请先选择类别</option>
							</s:else>
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
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center >
			<tr class="border">
				<td class="td_title" colspan="20" align="center">
					<center>
						<s:text name="userGoldLogListJsp.title"></s:text>&nbsp;&nbsp;<s:if test="count != -1">获得总计：${count}</s:if>&nbsp;&nbsp;<s:if test="useCount != -1">使用总计：${useCount}</s:if>
						<font color="red">${erroDescrip}</font>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="userGoldLogListJsp.order"></s:text>
				</td>
				<td>
					<s:text name="userGoldLog.userId"></s:text>
				</td>
				<td>
					<s:text name="userGoldLog.userName"></s:text>
				</td>
				<td>
					<s:text name="userGoldLog.lodoId"></s:text>
				</td>
				<td>
					<s:text name="userGoldLog.category"></s:text>
				</td>
				<td>
					<s:text name="userGoldLog.type"></s:text>
				</td>
				<td>
					<s:text name="userGoldLog.changeNum"></s:text>
				</td>
				<td>
					<s:text name="userGoldLog.time"></s:text>
				</td>
			</tr>
			
			<s:set name="totalChange" value="0"></s:set>
			<s:iterator var="userGoldLog" value="userGoldLogList" status="sta">
				<tr>
					<td>${sta.index+1}</td>
					<td>
						${userGoldLog.userId}						
					</td>
					<td>
						${userGoldLog.userName}
					</td>
					<td>
						${userGoldLog.lodoId}
					</td>
					<td>
						<s:text name="%{'userGoldLog.category_'+#userGoldLog.category}"></s:text>
					</td>
					<td>
						<s:text name="%{'userResourceLog.operation_'+#userGoldLog.type}"></s:text>
					</td>
					<td>
						${userGoldLog.changeNum}
						<s:set name="totalChange" value="#totalChange+#userGoldLog.changeNum"></s:set>
					</td>
					<td>
						<s:text name="format.date_time">
							<s:param value="#userGoldLog.time"></s:param>
						</s:text>
					</td>
				</tr>
				<s:if test="#sta.isLast() == true">
					<tr>
						<td colspan="6">
						</td>
						<td>
							总：${totalChange}
						</td>
						<td colspan="2">
						</td>
					</tr>
				</s:if>
			</s:iterator>
			
			<tr>
				<td colspan="100">
					<aldtags:pageTag paraStr="isCommit,T,lodoId,${lodoId},userName,${userName},name,${name},pageSize,${pageSize},searchCategory,${searchCategory},searchType,${searchType}" datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}"/>
				</td>
			</tr>
		</table>
	</body>
</html>