<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="添加商品打折活动"></s:text><s:text name="colon"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
	<script src="../../js/jquery/jquery.min.js"></script>
	
	<script type="text/javascript">
	function setDiscount() {
		var discount = prompt("输入折扣率: ");
		document.getElementById("discount").name=discount;
	}
	
	function setDiscountPrice(index) {
		var originalPrice = document.getElementById("op-"+index).value;
		var discount = document.getElementById("d-"+index).value;
		if (discount != 0) {
			document.getElementById("dp-"+index).value=Math.floor(originalPrice*discount*0.1);
			document.getElementById("i-"+index).removeAttribute("disabled");
		} else {
			document.getElementById("dp-"+index).value=null;
			document.getElementById("i-"+index).checked=false;
			document.getElementById("i-"+index).setAttribute("disabled", "disabled");
			document.getElementById("itemCheckbox").setAttribute("disabled", "disabled");
			
		}
	}
	
	function checkall(name) {
		var names = document.getElementsByName(name);
		var len = names.length;
		if (len > 0) {
			var i = 0;
			for (i = 0; i < len; i++)
				names[i].checked = true;

		}
	}
	function uncheckall(id) {
		var names = document.getElementsByName(id);
		var len = names.length;
		if (len > 0) {
			var i = 0;
			for (i = 0; i < len; i++)
				names[i].checked=false;
		}
	}

	</script>
<body>
	<form action="addMallDiscount?isCommit=T" onsubmit = "return checkForm()" method="post" >
	<div style="overflow:auto; width:100%">
		<table cellpadding="3" cellspacing="2" border="0" width="100%" align=center>
		<input type="hidden" name="id" value="${mallDiscount.id}"/>
		<tr>
			<td><s:text name="活动开始时间"></s:text></td>
			<td><input style="width:15%" type="text" id="startTime"
				name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
				class="Wdate" style="width: 100px" /> 
				<input type="text" name="startHour" style="width: 10%" /> 时
				<input type="text" name="startMin" style="width: 10%" /> 分
			</td>
		</tr>
		<tr>
			<td><s:text name="活动结束时间"></s:text></td>
			<td><input style="width:15%" type="text" id="endTime"
				name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
				class="Wdate" style="width: 100px" /> 
				<input type="text" name="endHour" style="width:10%" /> 时
				<input type="text" name="endMin" style="width: 10%" /> 分
			</td>
		</tr>
		<tr>
			<td>活动描述</td>
		  	<td><textarea rows="2" cols="20" type="text" name="description"></textarea></td>
		</tr>
		
		<tr>
			<td>
				<input type="checkbox" name="checkAll" onclick="if(this.checked==true) {checkall('selectedServers');} else {uncheckall('selectedServers');}"/>
				<s:text name="选择服务器"></s:text>
			</td>
			<td>
				<s:iterator var="data" value="tgameServerList">
					<input type="checkbox" name="selectedServers" value="${data.serverId}"/>
					${data.serverAlias}
				</s:iterator>
			</td>
		</tr>
		</table>
		</div>
		<div style="overflow:auto">
		<table cellpadding="3" cellspacing="2" border="0" width="100%" align=center>
		<tr>
			<td width="15px"><input disabled id="itemCheckbox" type="checkbox" name="checkAll" onclick="if(this.checked==true) { checkall('selectedItems'); } else { uncheckall('selectedItems'); }"/></td>
			<td width="70px"><s:text name="商品编号"></s:text></td>
			<td width="200px"><s:text name="商品名称"></s:text></td>
			<td width="50px"><s:text name="类型"></s:text></td>
			<td width="100px"><s:text name="商品原价"></s:text></td>
			<td width="200px"><s:text name="商品折扣率(1-10)"></s:text></td>
			<td width="200px"><s:text name="折后价"></s:text></td>
			<%-- <td width="200px"><s:text name="操作"></s:text></td> --%>
		</tr>
		</table>
		</div>
		<div style="overflow:auto; height:55%">
		
		<table cellpadding="3" cellspacing="2" border="0" width="100%" align=center >
		
		<s:iterator var="data" status="st" value="systemMallDiscountList">
			<tr>
				<td width="15x"><input id="i-${st.index}"  type="checkbox" name="selectedItems" value="${data.mallId}" disabled/></td>
				<td width="70px"><s:hidden name="systemMallDiscountList[%{#st.index}].mallId">${data.mallId}</s:hidden></td>
				<td width="200px"><s:hidden name="systemMallDiscountList[%{#st.index}].name">${data.name}</s:hidden></td>
				<td width="50px">
					<s:if test="#data.amountType == 1">
						钻石
					</s:if>
					<s:elseif test="#data.amountType == 2">
						金币
					</s:elseif>
				</td>
				<td width="100px"><s:hidden id="op-%{#st.index}" name="systemMallDiscountList[%{#st.index}].originalPrice">${data.originalPrice}</s:hidden></td>
				
				<td width="200px"><s:textfield id="d-%{#st.index}" theme="simple" name="systemMallDiscountList[%{#st.index}].discount" onblur="setDiscountPrice('%{#st.index}')"></s:textfield></td>
				<td width="200px"><s:textfield id="dp-%{#st.index}" theme="simple" name="systemMallDiscountList[%{#st.index}].discountPrice"></s:textfield></td>
				<%-- <td width="200px"><a href="javascript:setDiscountPrice(${st.index})"><s:text name="修改"></s:text></a></td> --%>
			</tr>
		</s:iterator>
		<%-- <tr>
				<td colspan="10">
					<aldtags:pageTag />
				</td>
		</tr> --%>
		
	</table>
	<table cellpadding="3" cellspacing="2" border="0" width="100%" align=center>
		<tr>
			<td colspan="10" align="center">
			<input type="submit" value="<s:text name='提交'></s:text>" onclick= /> 
			<input type="reset"	value="<s:text name='重置'></s:text>" /></td>
		</tr>
	</table>
	</div>
	</form>
</body>
  
<script>
	function checkForm() {
	
	   //检查是否有选择了要提交 的活动
		//var systemMallDiscountList = ${"systemMallDiscountList"}; // list
		var systemMallDiscountList = document.getElementsByName("selectedItems");
		var activityFlag = false;
		for (var index = 0; index < systemMallDiscountList.length; index++) {
			var tempD = document.getElementById("d-" + index).value;
		
			if (tempD) {
				//  获取是否选择要提交的活动
				activityFlag = true;
				var tempI = document.getElementById("i-" + index).checked;
				if (tempI == false) {
					alert("请选择要提交的活动!");
					return false;
				}
			}
		}
		if(!activityFlag){
		   alert("您没有填写活动信息，无法提交!");
		   return false;
	    }
		
		//检查是否已选择了至少一个服务器
		var selectedServers = document.getElementsByName("selectedServers");
		
		var flag = false;
		for (var j = 0; j < selectedServers.length; j++) {
			var tempChecked = selectedServers[j].checked;
			
			if (tempChecked) {
				flag = true;
				break;
			}
		}
		if(!flag){
		   alert("您还没有选择服务器!");
		   return false;
		}
		
		var ST = document.getElementById("startTime").value;
		var ET = document.getElementById("endTime").value;

		if(ST.length == 0){
		   alert("您还没有选择活动开始时间!");
		    return false;
	    }
		if(ET.length == 0){
		    alert("您还没有选择活动结束时间!");
		    return false;
		}
		
		return true;
		
	}
</script>
</html>
