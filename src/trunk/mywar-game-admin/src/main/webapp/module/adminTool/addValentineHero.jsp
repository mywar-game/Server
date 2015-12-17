<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	<script src="../../js/jquery/jquery.validate.js"></script>
	<script type="text/javascript">
	
	function addTool1(){
		var hight = (screen.height - 300) / 2.8;
			var width = (screen.width - 500) / 2;
			var str = window.showModalDialog("addTool", "", "dialogLeft=" + width
				+ "; dialogTop=" + hight
				+ "; dialogWidth=500px; dialogHeight=300px; location=no");

			if (str == null || str == '') {
				return;
			}
			//alert(str);
			var strArr=str.split('*');
			var toolArr = strArr[0].split(',');
			var nameArr = strArr[1].split(',');
			if(toolArr[0]==3001){
			document.getElementById("hero_id1").value = toolArr[1];
			document.getElementById("id1").value = nameArr[1];
			//alert("11");
			}else{
				//document.getElementById("id1").value = '';
				alert("请添加英雄！");
			}
			
	}
	function addTool2(){
		var hight = (screen.height - 300) / 2.8;
			var width = (screen.width - 500) / 2;
			var str = window.showModalDialog("addTool", "", "dialogLeft=" + width
				+ "; dialogTop=" + hight
				+ "; dialogWidth=500px; dialogHeight=300px; location=no");

			if (str == null || str == '') {
				return;
			}
			//alert(str);
			var strArr=str.split('*');
			var toolArr = strArr[0].split(',');
			var nameArr = strArr[1].split(',');
			if(toolArr[0]==3001){
			document.getElementById("hero_id2").value = toolArr[1];
			document.getElementById("id2").value = nameArr[1];
			//alert("11");
			}else{
				//document.getElementById("id2").value = '';
				alert("请添加英雄！");
			}
			
	}
	</script>
	<script type="text/javascript">
	 $(document).ready(function(){
		$("form").validate({
			rules:{
			    id1:{
					required:true
				},
				id2:{
					required:true
				},
				hero_id1:{
					required:true
				},
				hero_id2:{
					required:true
				},
			}		
		});	
	});
	 </script>
<body>
<form action="addValentineHero?isCommit=T" method="post">
	<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							添加配对英雄
						</center>
					</td>
				</tr>
                <tr>
					<td>
						英雄一
					</td>
					<td>
					     <input type="hidden" id="hero_id1" value="${hero_id1}"name="hero_id1">  
					     <input type="text"value=""readonly="readonly"id="id1"name="id1">
					</td>
					<td>
						<a href="#" onclick='addTool1()'>添加英雄</a>
					</td>
				</tr>
  <tr>
					<td>
						英雄二
					</td>
					<td>
					     <input type="hidden" name="hero_id2"id="hero_id2" value="${hero_id2}">
					     <input type="text"value=""readonly="readonly"id="id2"name="id2">
					</td>
					<td>
						<a href="#" onclick='addTool2()'>添加英雄</a>
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