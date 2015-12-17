<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>基因点日志</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script>
		<script>
			var erroDescrip = '${erroDescrip}';
			if (erroDescrip != "") {
				alert(erroDescrip);
			}
			
			function changeTypeSelect(categorySelect){
				//alert("categorySelect=="+categorySelect);
				//当前category的值
				var category = categorySelect.value;
				var searchTypeTd = document.getElementById("searchType").parentNode;
				var tr = categorySelect.parentNode.parentNode;
				tr.deleteCell(searchTypeTd.cellIndex);
				var newsearchTypeTd = tr.insertCell(categorySelect.parentNode.cellIndex+2);
				var html = '';
				html +='<select id="searchType" name="searchType" class="select">';
				//获得
				if (category == 1) {
					<s:generator separator="," val="%{getText('userGenePointLog.type_1_values')}">
						html +='<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>';
						<s:iterator var="str">
							html +='<option value="${str}"> <s:text name="%{'userGenePointLog.type_1_'+#str}"></s:text> </option>';
						</s:iterator>
					</s:generator>
				//使用
				} else if (category == 2) {
					<s:generator separator="," val="%{getText('userGenePointLog.type_2_values')}">
						html +='<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>';
						<s:iterator var="str">
							html +='<option value="${str}"> <s:text name="%{'userGenePointLog.type_2_'+#str}"></s:text> </option>';
						</s:iterator>
					</s:generator>
				//未选择
				} else {
					html +='<option value="" selected="selected">请先选择类别</option>';
				}
				html +='</select>';
				newsearchTypeTd.innerHTML = html;
			}
		</script>
	</head>
	<body>
	
		<form action="userGenePointLogList" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr>
					<td>
						<s:text name="log.userIdSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="userId" name="userId" onblur="value=value.replace(/[^\d]/g,'')" value="${userId}"/>
					</td>
					<td>
						<s:text name="log.userNameSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="userName" name="userName" value="${userName}"/>
					</td>
					<td>
						<s:text name="log.nameSearch"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" id="name" name="name" value="${name}"/>
					</td>
					<td>
						<s:text name="pageSize"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
					</td>
					<td rowspan="2">
						<center>
							<input type="submit" value="<s:text name="search"></s:text>" />
						</center>
					</td>
				</tr>
				
				<tr>
					<td>
						类别：
					</td>
					<td>
						<select id="searchCategory" name="searchCategory" class="select" onchange="changeTypeSelect(this)">
							<s:generator separator="," val="%{getText('userGenePointLog.category_values')}">
								<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
								<s:iterator var="str">
									<option value="${str}" <s:if test="searchCategory == #str">selected=selected</s:if>>
										<s:text name="%{'userGenePointLog.category_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
					<td>
						类型：
					</td>
					<td>
						<select id="searchType" name="searchType" class="select">
							<s:if test="searchCategory != null">
								<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
								<s:generator separator="," val="%{getText('userGenePointLog.type_'+searchCategory+'_values')}">
									<s:iterator var="str">
										<option value="${str}" <s:if test="searchType == #str">selected=selected</s:if>>
											<s:text name="%{'userGenePointLog.type_'+searchCategory+'_'+#str}"></s:text>
										</option>
									</s:iterator>
								</s:generator>
							</s:if>
							<s:else>
									<option value="" selected="selected">请先选择类别</option>
							</s:else>
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
				</tr>
			</table>
		</form>
			
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="20" align="center">
						<center>
							基因点日志
							<font color="red">${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						序号
					</td>
					<td>
						玩家编号
					</td>
					<td>
						角色名
					</td>
					<td>
						类别
					</td>
					<td>
						类型
					</td>
					<td>
						改变数量
					</td>
					<td>
						最终数量
					</td>
					<td>
						时间
					</td>
					<td>
						备注
					</td>
				</tr>
				<s:iterator var="log" value="logList" status="sta">
					<tr>
						<td>
							${sta.index+1}
						</td>
						<td>
							${log.userId}
						</td>
						<td>
							${log.name}
						</td>
						<td>
							<s:text name="%{'userGenePointLog.category_'+#log.category}"></s:text>
						</td>
						<td>
							<s:text name="%{'userGenePointLog.type_'+#log.category+'_'+#log.type}"></s:text>
						</td>
						<td>
							${log.changeNum}
						</td>
						<td>
							${log.finalNum}
						</td>
						<td>
							<s:text name="format.date_time">
								<s:param value="#log.createTime"></s:param>
							</s:text>
						</td>
						<td>
							${log.note}
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="100">
						<aldtags:pageTag paraStr="userId,${userId},userName,${userName},name,${name},pageSize,${pageSize},searchCategory,${searchCategory},searchType,${searchType}" datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}"/>
					</td>
				</tr>
			</table>
	</body>
</html>