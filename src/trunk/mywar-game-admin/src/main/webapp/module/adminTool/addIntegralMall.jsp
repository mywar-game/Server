<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.adminTool.bo.IntegralMall"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="addAdminMarqueeJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">	
		<script src="../../js/jquery/jquery.validate.js"></script>
	
	<script language="javascript" type="text/javascript">
	
	
	function addTool(tableTd) {
			var hight = (screen.height - 300) / 2.8;
			var width = (screen.width - 500) / 2;
			var str = window.showModalDialog("addTool", "", "dialogLeft=" + width
				+ "; dialogTop=" + hight
				+ "; dialogWidth=500px; dialogHeight=300px; location=no");
         
			if (str == null || str == '') {
				return;
			}
	        //alert(str);
			// 获得道具
			var strArr = str.split('*');
			var obj = tableTd.parentNode.parentNode; // tr
			var childrenArr = obj.children; // tdArr
			var idArr = strArr[0].split(',');
			var nameArr = strArr[1].split(',');
			
			var toolTypeArr = document.getElementById("toolTypeArr");
			toolTypeArr.value = idArr[0];
			
			if(idArr[0]==3001){
			     document.getElementById("type").value=0;
			     document.getElementById("typeName").value='英雄';
			
			}else if(idArr[0]==9001){
			     document.getElementById("type").value=1;
			     document.getElementById("typeName").value='神器';
			
			}else{
				 document.getElementById("type").value=2;
				 document.getElementById("typeName").value='道具';
			}
			var toolIdArr=document.getElementById("toolIdArr");
			toolIdArr.value=idArr[1];
			var numArr=document.getElementById("numArr");
			numArr.value=idArr[2];
		    //var nameArr=document.getElementById("nameArr");
			//nameArr.value= idArr[1];
			document.getElementById("tool_typeName").value=nameArr[1];
			
			
		}
	
		// 清空获取道具
		
		function clearTool(tableTd) {
			var mark = window.confirm("<s:text name='tool.clearConfirm'></s:text>");
			if (!mark) {
				return;
			}
			if (str == null || str == '') {
				return;
			}
			var obj = tableTd.parentNode.parentNode; // tr
			var childrenArr = obj.children; // tdArr
			
		
				var strArr = str.split('*');
			    var obj = tableTd.parentNode.parentNode; // tr
			    var childrenArr = obj.children; // tdArr
			    var idArr = strArr[0].split(',');
			    var nameArr = strArr[1].split(',');
				
			var toolTypeArr = document.getElementById("toolTypeArr");
			toolTypeArr.value = '';
			var toolIdArr=document.getElementById("toolIdArr");
			toolIdArr.value='';
			var numArr=document.getElementById("numArr");
			numArr.value='';
		}
	</script>
		
<body>
	 <form action="addIntegralMall?isCommit=T" method="post">
	 <table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
						<s:text name="integralListJsp.title"></s:text>
						<center>
					</td>
				</tr>
				    <tr>
				    
				  
                 
                  </tr>
	            <tr>
					<td>
						<input type="hidden" id="mall_id" name="mall_id" value="${integralMall.mall_id}"/>
						ID
					</td>
					<td>
						<%-- ${integralMall.mall_id} --%>
						<input type="text" readonly="readonly" value="${integralMall.mall_id}" />
					</td>
				</tr>
					<tr>
					<td>
						物品所属种类
					</td>
					<td>
						<input type="hidden" id="type" name="type" value="${integralMall.type}"/>
						
						<input type="text" id="typeName" readonly="readonly" name="typeName" value=""/>  <a href="#" onclick='addTool(this)'>修改配置</a>
					</td>
				</tr>
	           </tr>
					<tr>
					<td>
						物品
					</td>
					<td>
						<input type="hidden"id="toolTypeArr"name="toolTypeArr" value="${integralMall.tool_type}"/>
						<input type="text" id="tool_typeName"readonly="readonly"  name="tool_typeName" value=""/>
					</td>
				</tr>
	
	          
						<input type="hidden" id="toolIdArr"name="toolIdArr" value="${integralMall.tool_id}"/>
				
				
				 <tr>
					<td>
						<s:text name="integralListJsp.num"></s:text>
					</td>
					<td>
						<input type="text"id="numArr" name="numArr"value="${integralMall.num}"/>
					</td>
				</tr>
				
				
				<tr>
					<td>
						所需积分
					</td>
					<td>
						<input type="text" name="need_integrate" value="${integralMall.need_integrate}"/>
					</td>
				</tr>
	 <tr>
					<td>
						折扣
					</td>
					<td>
						<input type="text" id="discount" name="discount" value="${integralMall.discount}"/>0.0到1.0之间，例如0.9代表9折
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
	<script type="text/javascript">
	 $(document).ready(function(){
		$("form").validate({
			rules:{
				need_integrate:{
					required:true
				},
				discount:{
					required:true
				},
				
			}		
		});	
	});
	 </script>
	
</html>