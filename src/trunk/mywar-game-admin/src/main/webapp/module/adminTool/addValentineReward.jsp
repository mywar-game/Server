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
	function addTool(dd){
	var hight = (screen.height - 300) / 2.8;
				var width = (screen.width - 500) / 2;
				var str = window.showModalDialog("addTool", "", "dialogLeft=" + width
					+ "; dialogTop=" + hight
					+ "; dialogWidth=500px; dialogHeight=300px; location=no");

				if (str == null || str == '') {
					return;
				}
				//alert(str);
				// 获取到的道具
				var strArr = str.split('*');
				//alert(strArr);
				
				var va = document.getElementById("jk").value;
				var ve= document.getElementById("kl").value;
				//alert(va);
				if (va != "") {
					var v = va + "|" + strArr[0];
					var v2 = ve+ "|" + strArr[1];
					v = trim(v);
					v2= trim(v2);
					document.getElementById("jk").value = v;
					document.getElementById("kl").value=v2;
					//alert(va);
				} else {
				    document.getElementById("kl").value = trim(strArr[1]);
					document.getElementById("jk").value = trim(strArr[0]);
					//alert(document.getElementById("jk").value);
				}
				
}
		// 清空获取道具
			function clearTool() {
				document.getElementById("jk").value="";
				document.getElementById("kl").value="";
			}

			// 去掉字符串的空格
			function trim(str) {
				str = str.replace(/^(\s|\u00A0)+/, '');
				for (var i = str.length - 1; i >= 0; i--) {
					if (/\S/.test(str.charAt(i))) {
						str = str.substring(0, i + 1);
						break;
					}
				}
				return str;
			}
	
	</script>
	<body>
<form action="addValentineReward?isCommit=T" method="post">
	<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							添加配对英雄奖励
						</center>
					</td>
		    </tr>
	     
		 <tr>
		   <td>匹配数量</td>
		   <td><input id="pp" name="match_num" type=text value=""></td>
	     </tr>
		 <tr>
		   <td>奖励</td>
		   <td><input id="jk" name="rewards" type="text" value="" readonly="readonly"></td>
		   <td><input id="kl" name="kl" type="text" value="" readonly= "readonly"></td>
		   <td><a href="#" onclick='addTool(this)'>添加道具</a></td>
           <td><a href="#" onclick='clearTool()'>清空道具</a></td>
	     </tr>
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
	     		match_num:{
					required:true
				},
				rewards:{
					required:true
				},
				kl:{
					required:true
				},
				
			}		
		});	
	});
	 </script>


</html>