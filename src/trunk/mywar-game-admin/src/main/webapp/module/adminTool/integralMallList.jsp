<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>
	    <title><s:text name="integralListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
</head>

	<script type="text/javascript" src="../../js/ajax.js"></script>
		<script src="../../js/jquery/jquery.validate.js"></script>
	<script language="javascript" type="text/javascript">
	
		function del(mall_id) {
			if (window.confirm("<s:text name='deleteConfirm'><s:param>mall_id</s:param><s:param>"+mall_id+"</s:param></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delIntegralMall?mall_id=" + mall_id;
				ajaxobj.callback = function() {
					
					var tr = document.getElementById(mall_id);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(mall_id) {
			window.location.href = "updateIntegralMall?mall_id=" + mall_id;
		}
	
		function add() {
			window.location.href = "addIntegralMall";
		}
	</script>
		

<body>

<input type="submit" value="<s:text name='添加积分商城配置'></s:text>" class="button" onclick=add(); />

 
<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
  <tr class="border">
	       <td class="td_title" colspan="22" align="center">
	                <center>
						<s:text name="integralListJsp.title"></s:text>
					</center>
	    
	       </td>
  </tr>
  
  <tr>
                <td>
                <input type="hidden" id="mall_id" name="mall_id" value="${integralMall.mall_id}"/>
                    <s:text name="integralListJsp.mall_id"></s:text>
                </td>
                <td>
					<s:text name="integralListJsp.type"></s:text>
				</td>
				
				<td>
				    <s:text name="integralListJsp.tool_type"></s:text>
				</td>
  <%--<td>
				    <s:text name="integralListJsp.tool_id"></s:text>
				</td>--%>
				<td>
				    <s:text name="integralListJsp.need_integrate"></s:text>
				</td>
				
				<td>
				    <s:text name="integralListJsp.num"></s:text>
				</td>
				
				<td>
				    <s:text name="integralListJsp.discount"></s:text>
				</td>
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
                    
  
  
  
  </tr>
<s:iterator var="integralMall" value="integralMallList">
  				<tr id="${integralMall.mall_id}">
  				    <td>
  					 <%--  <input type="text" readonly="readonly" value="${integralMall.mall_id}" /> --%>
  					 ${integralMall.mall_id}
  				    </td>
                    <td>
						<input type="hidden"value="${integralMall.type}"readonly="readonly">
						${integralMall.toolTypeName}
					</td>
					
					<td>
						${integralMall.toolName}
						
					</td>
					<%--<td>
						${integralMall.tool_id}
							<input type=""name=""value=""id=""readonly="readonly">
					</td>--%>
  
					<td>
						${integralMall.need_integrate}
					</td>
                    <td>
						${integralMall.num}
					</td>
					<td>
						${integralMall.discount}
					</td>
                  
  	             <td>
					<a href="#" onclick='del("${integralMall.mall_id}")'><s:text name="delete"></s:text></a>
				</td>
				<td>
					<a href="#" onclick='update("${integralMall.mall_id}")'><s:text name="update"></s:text></a>
				</td>
  
  </tr>
  </s:iterator>
  <tr>
             <td colspan="100">
				<aldtags:pageTag paraStr="isCommit,T,pageSize,${pageSize}" />
			</td>
			</tr>
            
</table>

</body>
</html>