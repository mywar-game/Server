<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>1</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="../../js/ajax.js"></script>
 <script type="text/javascript">
    
    var add = function() {
    	window.location.href="addValentineHero";
    }
    </script>
<script type="text/javascript">
 function a(hero_id1,hero_id2){
 if (window.confirm("<s:text name='deleteConfirm'></s:text>")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delValentineHero?hero_id1="+hero_id1+"&hero_id2="+hero_id2;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					//var tr = document.getElementById(trId);
					//var table = document.getElementById("table");
					//table.deleteRow(tr.rowIndex);
					//alert(2);
				}
				ajaxobj.send();
			}
 }

</script>
<body>
<input type="submit" class="button" value="添加配对英雄" onclick="add();" />

<table id="table"cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
  <tr class="border">
	       <td class="td_title" colspan="22" align="center">
	                <center>
					     七夕英雄配对配置
					</center>
	       </td>
  </tr>
<tr>
    <td> 
                                              
                                               英雄1
                                               
    </td>
    <td>
          
                                               英雄2
           
    </td>
    <td>
             删除
    </td>
</tr>
<s:iterator var="valentineHero" value="list">
<tr id="trId">
         <td>
         <input type="hidden" id="hero_id1" name="hero_id1" value="${valentineHero.hero_id1}">
         <input type="text" id="name1" readonly="readonly" name="name1" value=" ${valentineHero.name1}"/>
         </td>
         <td>
         <input type="hidden" id="hero_id2" name="hero_id2" value="${valentineHero.hero_id2}">
         <input type="text" id="name2" readonly="readonly" name="name2" value=" ${valentineHero.name2}"/>
         </td>
         <td>
				<a href="#" onclick='a("${valentineHero.hero_id1}","${valentineHero.hero_id2}");'>删除</a>
	     </td>
</tr>
</s:iterator>
</table>
</body>
</html>