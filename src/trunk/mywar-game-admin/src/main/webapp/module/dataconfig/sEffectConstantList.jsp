<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <title>技能效果常量列表</title>
	    
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="../../js/ajax.js"></script>
		<script type="text/javascript">
  			function del(effectId){
				if(window.confirm("<s:text name='deleteConfirm'><s:param><s:text name='effectId'/></s:param><s:param>"+effectId+"</s:param></s:text>")){
					var ajaxobj = new Ajax();
			        ajaxobj.url="delSEffectConstant?effectId="+effectId;
			        ajaxobj.callback=function(){
			        	//alert(ajaxobj.gettext());
			        	var tr = document.getElementById(effectId);
						var table = document.getElementById("table");
				        table.deleteRow(tr.rowIndex);  
			        }
				    ajaxobj.send();
				}
				
			}
		
			function update(effectId){
				window.location.href="updateSEffectConstant?effectId="+effectId;
			}
		
			function  add(){
				window.location.href="addSEffectConstant";
			}
		</script>
	</head>
	<body>
    	<input type="submit" value="添加技能效果常量" class="button" onclick="add()" />
    	<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
    		<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						技能效果常量列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					效果编号
				</td>
				<td>
					效果描述
				</td>
				<td>
					是否用公式
				</td>
				<td>
					数值类型
				</td>
				<td>
					数值
				</td>
				<td>
					影响类型
				</td>
				<td>
					持续类型
				</td>
				<td>
					周期
				</td>
				<td>
					持续时间
				</td>
				<td>
					作用对象
				</td>
				<td>
					<s:text name="delete"></s:text>
				</td>
				<td>
					<s:text name="update"></s:text>
				</td>
			</tr>
				
				
			<s:iterator var="effectConstant" value="seffectConstantList">
				<tr id="${effectConstant.effectId}">
					<td>
						${effectConstant.effectId}
					</td>
					<td>
						${effectConstant.effectDesc}
					</td>
					<td>
						<s:text name="%{'effectConstant.useFormula_'+#effectConstant.useFormula}"></s:text>
					</td>
					<td>
						<s:text name="%{'effectConstant.valueType_'+#effectConstant.valueType}"></s:text>
					</td>
					<td>
						${effectConstant.value}
					</td>
					<td>
						<s:text name="%{'effectConstant.effectType_'+effectConstant.effectType}"></s:text>
					</td>
					<td>
						<s:text name="%{'effectConstant.continueType_'+#effectConstant.continueType}"></s:text>
					</td>
					<td>
						${effectConstant.period}
					</td>
					<td>
						${effectConstant.continueTime}
					</td>
					<td>
						<s:text name="%{'effectConstant.target_'+#effectConstant.target}"></s:text>
					</td>

					<td>
						<a href="#" onclick='del("${effectConstant.effectId}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${effectConstant.effectId}")'><s:text name="update"></s:text></a>
					</td>
				</tr>
			</s:iterator>
			
    		<tr>
				<td colspan="22">
					<aldtags:pageTag />
				</td>
			</tr>
    </table>
  </body>
</html>
