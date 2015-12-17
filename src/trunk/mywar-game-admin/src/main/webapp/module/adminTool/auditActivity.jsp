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
 /* function setDiscountPrice(index) {
		var originalPrice = document.getElementById("op-"+index).value;
		var discount = document.getElementById("d-"+index).value;
		if (discount != 0) {
			document.getElementById("dp-"+index).value=originalPrice*discount*0.01;
		
		} else {
			document.getElementById("d-"+index).value="100";
		}
	} */
	
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

</script>


<body>

	<form name="updateForm" method="post">
		<div style="overflow:auto; width:100%">
	
		<table cellpadding="3" cellspacing="2" border="0" width="100%" align=center>
		<input type="hidden" name="id" value="${mallDiscount.id}"/>
		<input type="hidden" name="activityId" valaue="${mallDiscount.activityId}"/>
		<tr>
			<td><s:text name="活动开始时间"></s:text></td>
			<td><input disabled style="width:15%" type="text" id="startDate"
				name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
				value="<s:if test="mallDiscount.startTime != null">${startDate}</s:if>"
				class="Wdate" /> 
				<input type="text" name="startHour" style="width: 10%" value="${startHour}" disabled/> 时
				<input type="text" name="startMin" style="width: 10%" value="${startMin}" disabled/> 分
			</td>
		</tr>
		<tr>
			<td><s:text name="活动结束时间"></s:text></td>
			<td><input disabled style="width:15%" type="text" id="endTime"
				name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
				value="<s:if test="mallDiscount.startTime != null">${endDate}</s:if>"
				class="Wdate" /> 
				<input type="text" name="endHour" style="width:10%" value="${endHour}" disabled/> 时
				<input type="text" name="endMin" style="width: 10%" value="${endMin}" disabled/> 分
			</td>
		</tr>
		<tr>
			<td><s:text name="活动描述"></s:text></td>
			<td><textarea rows="2" cols="40" name="description" disabled>${mallDiscount.description}</textarea></td>
		</tr>
		<tr>
			<td>
				<s:text name="活动服务器"></s:text>
			</td>
			<td>
				${selectedServers}
				<%-- <s:iterator var="data" value="mallDiscount.servers">

				</s:iterator> --%>
			</td>
		</tr>
		</table>
		</div>	
			
		<table cellpadding="3" cellspacing="2" border="0" width="100%" align=center>
		<tr>
			<td width="15px"><input  disabled type="checkbox" name="checkAll" onclick="if(this.checked==true) { checkAll('selectedItems'); } else { clearAll('selectedItems'); }"/></td>
			<td width="60px"><s:text name="商品编号"></s:text></td>
			<td width="200px"><s:text name="商品名称"></s:text></td>
			<td width="100px"><s:text name="商品原价"></s:text></td>
			<td width="200px"><s:text name="商品折扣率"></s:text></td>
			<td width="200px"><s:text name="折后价"></s:text></td>
		</tr>
		</table>
		
		<div style="overflow:auto; height:55%">
		<table cellpadding="3" cellspacing="2" border="0" width="100%" align=center>
		<s:iterator var="data" status="st" value="mallDiscountItemList">
			<tr>
				<td width="15px"><input type="checkbox" disabled="disabled" name="selectedItems" <s:if test="#data.isChecked!=0">checked="checked"</s:if>/></td>
				<s:hidden name="mallDiscountItemList[%{#st.index}].id"/>
				<s:hidden name="mallDiscountItemList[%{#st.index}].activityId"/>
				<td width="60px"><s:hidden>${data.mallId}</s:hidden></td>
				<td width="200px"><s:hidden>${data.name}</s:hidden></td>
				<td width="100px"><s:hidden>${data.originalPrice}</s:hidden></td>
				<td width="200px"><s:hidden>${data.discount}</s:hidden></td>
				<td width="200px"><s:hidden>${data.discountPrice}</s:hidden></td>
				
			</tr>
		</s:iterator>
		</table>
		<table cellpadding="3" cellspacing="2" border="0" width="100%" align=center>
			<tr>
			<td colspan="10" align="center">
				<a href="auditActivity?isAudited=1&isCommit=T&activityId=${mallDiscount.activityId}"><input type="button" value="<s:text name='同意'></s:text>" /></a>
				<a href="auditActivity?isAudited=-1&isCommit=T&activityId=${mallDiscount.activityId}"><input type="button" value="<s:text name='拒绝'></s:text>"/></a>
				<a href="mallAuditList"><input type="button" value="<s:text name='返回'></s:text>"/></a>
			</td>
		</tr>
	</table>
		</div>
		
		
	</form>

</body>
</html>