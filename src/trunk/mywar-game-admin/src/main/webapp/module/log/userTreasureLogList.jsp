<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userTreasureLogListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
		
		<script type="text/javascript" src="../../js/ajax.js"></script>
		
		<script src="../../js/jquery/jquery.min.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.core.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.widget.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.position.js"></script>
		<script src="../../js/jquery/ui/jquery.ui.autocomplete.js"></script>
		
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script>
		<script>
			var erroDescrip = '${erroDescrip}';
			if (erroDescrip != "") {
				alert(erroDescrip);
			}
			
			$(function() {
				$("#searchTreasureName").autocomplete({
					minLength: 0,
					source: function( request, response ) {
						$.ajax({
							url: "../dataconfig/tTreasureConstantListfindTreasureIdNameMapByCondition",
							data:{name: encodeURI(request.term)},
							success: function(data) {
								var x;
								response($.map( data, function(name,id) {
									return {
										id: id,
										label: name,
										value: name
									}
								}));
							}
						});
					},
					select: function( event, ui ) {
						$( "#searchTreasureId" ).val( ui.item.id );
					},
					open: function() {
						$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
					},
					close: function() {
						$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
					}
				});
			})
			
			function changeOperationSelect(categorySelect){
				//alert("categorySelect=="+categorySelect);
				//当前category的值
				var category = categorySelect.value;
				var searchOperationTd = document.getElementById("searchOperation").parentNode;
				var tr = categorySelect.parentNode.parentNode;
				tr.deleteCell(searchOperationTd.cellIndex);
				var newSearchOperationTd = tr.insertCell(categorySelect.parentNode.cellIndex+2);
				var html = '';
				html +='<select id="searchOperation" name="searchOperation" class="select">';
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
				newSearchOperationTd.innerHTML = html;
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
	
		<form action="userTreasureLogList?isCommit=T" method="post" onsubmit="return dateMust()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
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
					
					<td><s:text name="userTreasureLog.treasureName"></s:text></td>
					<td>
						<input type="text" id="searchTreasureName" name="searchTreasureName" value="${treasureIdNameMap[searchTreasureId]}" />
					</td>
					<td><s:text name="userTreasureLog.treasureId"></s:text></td>
					<td>
						<input type="text" id="searchTreasureId" name="searchTreasureId" value="${searchTreasureId}" />
					</td>
				</tr>
				
				<tr>
					<td>
						<s:text name="userTreasureLog.category"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select id="searchCategory" name="searchCategory" class="select" onchange="changeOperationSelect(this)">
							<s:generator separator="," val="%{getText('userTreasureLog.category_values')}">
								<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
								<s:iterator var="str">
									<option value="${str}" <s:if test="searchCategory == #str">selected=selected</s:if>>
										<s:text name="%{'userTreasureLog.category_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
					<td>
						<s:text name="userTreasureLog.operation"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select id="searchOperation" name="searchOperation" class="select">
							<s:if test="searchCategory != null && searchCategory != ''">
								<option value="" selected="selected"><s:text name="pleaseSelect"></s:text></option>
								<s:if test="searchCategory == 1">
									<s:generator separator="," val="%{getText('userResourceLog.get_value')}">
										<s:iterator var="str">
											<option value="${str}" <s:if test="searchOperation == #str">selected=selected</s:if>>
												<s:text name="%{'userResourceLog.operation_'+#str}"></s:text>
											</option>
										</s:iterator>
									</s:generator>
								</s:if>
								<s:else>
									<s:generator separator="," val="%{getText('userResourceLog.use_value')}">
										<s:iterator var="str">
											<option value="${str}" <s:if test="searchOperation == #str">selected=selected</s:if>>
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
						<center>
							<input type="submit" value="<s:text name="search"></s:text>" />
						</center>
					</td>
				</tr>
			</table>
		</form>
			
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="20" align="center">
						<center>
							<s:text name="userTreasureLogListJsp.title"></s:text>
							<font color="red">${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						序号
					</td>
					<td>
						<s:text name="userTreasureLog.userId"></s:text>
					</td>
					<td>
						<s:text name="userTreasureLog.userName"></s:text>
					</td>
					<td>
						<s:text name="userTreasureLog.lodoId"></s:text>
					</td>
					<td>
						<s:text name="userTreasureLog.category"></s:text>
					</td>
					<td>
						<s:text name="userTreasureLog.operation"></s:text>
					</td>
					<td>
						<s:text name="userTreasureLog.treasureName"></s:text>
					</td>
					<td>
						<s:text name="userTreasureLog.treasureType"></s:text>
					</td>
					<td>
						<s:text name="userTreasureLog.changeNum"></s:text>
					</td>
					<td>
						<s:text name="userTreasureLog.extinfo"></s:text>
					</td>
					<td>
						<s:text name="userTreasureLog.createTime"></s:text>
					</td>
				</tr>
				<s:iterator var="userTreasureLog" value="userTreasureLogList" status="sta">
					<tr>
						<td>
							${sta.index+1}
						</td>
						<td>
							${userTreasureLog.userId}
						</td>
						<td>
							${userIdUserMap[userTreasureLog.userId].userName}
						</td>
						<td>
							${userIdUserMap[userTreasureLog.userId].lodoId}
						</td>
						<td>
							<s:text name="%{'userTreasureLog.category_'+#userTreasureLog.category}"></s:text>
						</td>
						<td>
							<s:text name="%{'userResourceLog.operation_'+#userTreasureLog.operation}"></s:text>
						</td>
						<td>
							<s:if test="#userTreasureLog.treasureType == 3">
								金币
							</s:if>
							<s:if test="#userTreasureLog.treasureType == 4">
								钻石
							</s:if>
							<s:elseif test="#userTreasureLog.treasureType == 5">
								团队经验
							</s:elseif>
							<s:elseif test="#userTreasureLog.treasureType == 8">
								声望	
							</s:elseif>
							<s:elseif test="#userTreasureLog.treasureType == 9">
								荣誉		
							</s:elseif>  
							<s:elseif test="#userTreasureLog.treasureType == 10">
								背包扩展次数
							</s:elseif>
							<s:elseif test="#userTreasureLog.treasureType == 11">
								用户等级
							</s:elseif>
							<s:elseif test="#userTreasureLog.treasureType == 12">
								声望等级							
							</s:elseif>
							<s:elseif test="#userTreasureLog.treasureType == 13">
								英雄经验
							</s:elseif>
							<s:elseif test="#userTreasureLog.treasureType == 15">
								英雄技能
							</s:elseif>
							<s:elseif test="#userTreasureLog.treasureType == 2">
								<!-- 装备 -->
								${equipIdNameMap[userTreasureLog.treasureId]}
							</s:elseif>
							<s:elseif test="#userTreasureLog.treasureType == 7">
								<!-- 武将 -->
								${heroIdNameMap[userTreasureLog.treasureId]}
							</s:elseif>
							<s:else>
								<a href="../dataconfig/tTreasureConstantList?id=${userTreasureLog.treasureId}">
									${treasureIdNameMap[userTreasureLog.treasureId]}
								</a>
							</s:else>
						</td>
						<td>
							<s:text name="%{'userTreasureLog.treasureType_'+#userTreasureLog.treasureType}"></s:text>
						</td>
						<td>
							${userTreasureLog.changeNum}
						</td>
						<td>
							${userTreasureLog.extinfo}
						</td>
						<td>
							<s:text name="format.date_time">
								<s:param value="#userTreasureLog.createTime"></s:param>
							</s:text>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="100">
						<aldtags:pageTag paraStr="isCommit,T,userId,${userId},lodoId,${lodoId},pageSize,${pageSize},searchTreasureId,${searchTreasureId},searchCategory,${searchCategory},searchOperation,${searchOperation}" datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}"/>
					</td>
				</tr>
			</table>
	</body>
</html>