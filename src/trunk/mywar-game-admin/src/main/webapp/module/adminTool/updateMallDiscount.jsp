<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改角色</title>

<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>

</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<link href="../../css/main.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery/jquery.min.js"></script>
<script language="javascript" type="text/javascript"
	src="../../My97DatePicker/newDate_WdatePicker.js"></script>

<script type="text/javascript">
// 说明：Javascript 控制 CheckBox 的全选与取消全选
// 整理：http://www.CodeBit.cn
 function setDiscountPrice(index) {
		var originalPrice = document.getElementById("op-"+index).value;
		var discount = document.getElementById("d-"+index).value;
		if (discount != 0) {
			document.getElementById("dp-"+index).value=originalPrice*discount*0.01;
			document.getElementById("i-"+index).removeAttribute("disabled");
		} else {
			document.getElementById("dp-"+index).value=null;
			document.getElementById("i-"+index).checked=false;

			document.getElementById("i-"+index).setAttribute("disabled", "disabled");
		}
	}
	
function checkAll(name)
{
    var el = document.getElementsByTagName("input");
    var len = el.length;
    for(var i=0; i<len; i++)
    {
        if((el[i].type=="checkbox") && (el[i].name==name))
        {
            el[i].checked = true;
        }
    }
}


function clearAll(name)
{
    var el = document.getElementsByTagName('input');
    var len = el.length;
    for(var i=0; i<len; i++)
    {
        if((el[i].type=="checkbox") && (el[i].name==name))
        {
            el[i].checked = false;
        }
    }
} 

function checkDiscountEmpty(index) {
	var discount = document.getElementById("d-"+index).value;
	if (discount == 100) {
		alert("折扣率不能为空");
		document.getElementById("i-"+index).removeAttribute("checked");
	}
}

function checkServers() {
	var selectedServers = document.getElementById("selectedServers").value.split(", ");
	var servers = document.getElementsByName("selectedServers");
	for (var i = 0; i < servers.length; i++) {
		for (var j = 0; j < selectedServers.length; j++) {
			if (servers[i].value == selectedServers[j]) {
				servers[i].checked=true;
			}
		}
		
	}
	
}

</script>


<body>

	<form name="updateForm" action="updateMallDiscount?isCommit=T" method="post">
		<div style="overflow:auto; width:100%">
	
		<table cellpadding="3" cellspacing="2" border="0" width="100%" align=center>
		<input type="hidden" name="id" value="${mallDiscount.id}"/>
		<input type="hidden" name="activityId" value="${mallDiscount.activityId}"/>
		<tr>
			<td><s:text name="活动开始时间"></s:text></td>
			<td><input style="width:15%" type="text" id="startDate"
				name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
				value="<s:if test="startDate != null">${startDate}</s:if>"
				class="Wdate" /> 
				<input type="text" name="startHour" style="width: 10%" value="${startHour}"/> 时
				<input type="text" name="startMin" style="width: 10%" value="${startMin}"/> 分
			</td>
		</tr>
		<tr>
			<td><s:text name="活动结束时间"></s:text></td>
			<td><input style="width:15%" type="text" id="endDate"
				name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
				value="<s:if test="mallDiscount.startTime != null">${endDate}</s:if>"
				class="Wdate"  /> 
				<input type="text" name="endHour" style="width:10%" value="${endHour}"/> 时
				<input type="text" name="endMin" style="width: 10%" value="${endMin}"/> 分
			</td>
		</tr>
		<tr>
			<td><s:text name="活动描述"></s:text></td>
			<td><textarea rows="2" cols="20" name="description">${mallDiscount.description}</textarea></td>
		</tr>
		<tr>
			<td>
				<input type="checkbox" name="checkAll" onclick="if(this.checked==true) { checkAll('selectedServers'); } else { clearAll('selectedServers'); }"/>
				<s:text name="选择服务器"></s:text>
			</td>
		
			<td>
				<input type="hidden" id="selectedServers" value="${mallDiscount.servers}">
				<s:iterator status="st" var="data" value="tgameServerList">
					<input type="checkbox" name="selectedServers" value="${data.serverId}"/>
					${data.serverAlias}
				</s:iterator>
			</td>
		</tr>
		</table>
		</div>
		
		<table width="100%">
		<tr>
			<td width="3%"><input type="checkbox" name="checkAll" onclick="if(this.checked==true) { checkAll('selectedItems'); } else { clearAll('selectedItems'); }"/></td>
			<td width="5%"><s:text name="商品编号"></s:text></td>
			<td width="10%"><s:text name="商品名称"></s:text></td>
			<td width="10%"><s:text name="商品原价"></s:text></td>
			<td width="10%"><s:text name="商品折扣率"></s:text></td>
			<td width="10%"><s:text name="折后价"></s:text></td>
			<td width="10%"><s:text name="操作"></s:text></td>
		</tr>
		</table>
		
		<div style="overflow:auto; height:55%">
		<table>
		<s:iterator var="data" status="st" value="mallDiscountItemList">
			<tr>
				<td width="3%"><input id="i-${st.index}" type="checkbox" name="selectedItems" value="${data.mallId}" 
					<s:if test="#data.isChecked!=0">checked="checked"</s:if>
					<s:if test="#data.discount==0">disabled</s:if>/>
				</td>
				<s:hidden name="mallDiscountItemList[%{#st.index}].id"/>
				<s:hidden name="mallDiscountItemList[%{#st.index}].activityId"/>
				<td width="5%"><s:hidden name="mallDiscountItemList[%{#st.index}].mallId">${data.mallId}</s:hidden></td>
				<td width="9%"><s:hidden name="mallDiscountItemList[%{#st.index}].name">${data.name}</s:hidden></td>
				<td width="10%"><s:hidden id="op-%{#st.index}" name="mallDiscountItemList[%{#st.index}].originalPrice">${data.originalPrice}</s:hidden></td>
				<td width="5%"><s:textfield id="d-%{#st.index}" theme="simple" name="mallDiscountItemList[%{#st.index}].discount" onblur="setDiscountPrice('%{#st.index}')" value="%{#data.discount}"></s:textfield></td>
				<td width="5%"><s:textfield id="dp-%{#st.index}" theme="simple" name="mallDiscountItemList[%{#st.index}].discountPrice" value="%{#data.discountPrice}"></s:textfield></td>
				<td width="10%"><a href="javascript:setDiscountPrice(${st.index})"><s:text name="修改"></s:text></a></td>
			</tr>
		</s:iterator>
		</table>
		</div>
		
		<table cellpadding="3" cellspacing="2" border="0" width="100%" align=center>
			<tr>
			<td colspan="10" align="center">
				<input type="submit" value="<s:text name='提交'></s:text>" /> 
				<input type="reset"	value="<s:text name='重置'></s:text>" />
			</td>
		</tr>
	</table>
	</form>

</body>
<script type="text/javascript">
checkServers();
</script>
</html>