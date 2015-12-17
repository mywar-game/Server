<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.adminTool.bo.IntegralMall"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
     <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
	 </head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<script src="../../js/jquery/jquery.validate.js"></script>
    <script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js">
    </script>
    
    
    <script type="text/javascript">
		function isSubmit(){
			if(window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
		}
     </script>
     
       <script>
		
	function addTool(tableTd) {
			var hight = (screen.height - 300) / 2.8;
			var width = (screen.width - 500) / 2;
			var str = window.showModalDialog("addTool", "", "dialogLeft=" + width
				+ "; dialogTop=" + hight
				+ "; dialogWidth=500px; dialogHeight=300px; location=no");
         
			if (str == null || str == '') {
				return;
			}
	    //    alert(str);
			// 获得道具
			var strArr = str.split('*');
			var obj = tableTd.parentNode.parentNode; // tr
			var childrenArr = obj.children; // tdArr
			var idArr = strArr[0].split(',');
			var nameArr = strArr[1].split(',');
			
			var tool_type = document.getElementById("tool_type");
			tool_type.value = idArr[0];
			
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
			var tool_id=document.getElementById("tool_id");
			tool_id.value=idArr[1];
			var num=document.getElementById("num");
			num.value=idArr[2];
		    //var nameArr=document.getElementById("nameArr");
			//nameArr.value= idArr[1];
			document.getElementById("tool_typeName").value=nameArr[1];
			
			
		}
	
	

	
		</script>
     
     
     
     <script type="text/javascript">
	$(document).ready(function(){
		$("form").validate({
			rules:{
				type:{
					required:true,
					maxlength:1000
				},
				tool_type:{
					required:true
				},
				tool_id:{
					required:true
				},
				need_integrate:{
					required:true,
					digits:true
				},
				num:{
					required:true
				},
				discount:{
					required:true
				},
				
			}		
		});	
	});
</script>
    <body>
    <form action="updateIntegralMall?isCommit=T" method="post"onsubmit="return isSubmit()">
    <table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
                <tr>
					<td  colspan="5">
						<center>
							修改积分商城配置
						</center>
					</td>
				</tr>
     <input type="hidden" id="mall_id" name="mall_id" value="${integralMall.mall_id}"/>
     
    <tr>
                    <td>ID</td>
                    <td>
						${integralMall.mall_id}
					</td>
    </tr>
      <tr>
                    <td>物品所属种类</td>
                    <td>
						<input type="hidden" id="type"name="type" value="${integralMall.type}">
						<input type="text" id="typeName" readonly="readonly" name="typeName" value="${integralMall.toolTypeName}"/> <a href="#" onclick='addTool(this)'>修改配置</a>
					</td>
    </tr>
      <tr>
                    <td>物品</td>
                    <td>
						<input type="hidden"id="tool_type" value="${integralMall.tool_type}" name="tool_type" />
				     	<input type="text" id="tool_typeName"readonly="readonly"  name="tool_typeName" value="${integralMall.toolName}"/>
					</td>
    </tr>
   
						<input type="hidden"id="tool_id" value="${integralMall.tool_id}" name="tool_id" />
	
  
    
     <tr>
                    <td>数量</td>
                    <td>
						<input type="text" id="num" value="${integralMall.num}" name="num" />
					</td>
    </tr>
      <tr>
                    <td>所需积分</td>
                    <td>
					<input type="text"value="${integralMall.need_integrate}" name="need_integrate" />
					</td>
    </tr>
     
      <tr>
                    <td>折扣</td>
                    <td>
					 
						<input type="text"  value="${integralMall.discount}" name="discount" />0.0到1.0之间，例如0.9代表9折
				  
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