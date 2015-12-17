<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="giftCode.createGiftCode"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">
	<script language="javascript" type="text/javascript" src="../../My97DatePicker/newDate_WdatePicker.js"></script>
	
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script src="../../js/jquery/jquery.min.js"></script>
	<script language="javascript" src="../../js/mask.js"></script>
	<script type="text/javascript">
	
		function beforeSubmit(){
			//var count = document.getElementById('num').value;
			//if (count > 3000) {
			//	alert("请每次生成礼包码数量小于3000个。");
			//	return false;
			//}
			
			document.form1.subBtn.disabled = "true";
			document.body.innerHtml="<center>Waiting...</center>";
			popCoverDiv(); // 添加遮挡层
			return true;
		}
	
	</script>
	
	<body>
		&nbsp;
		<form action="createGiftCode?isCommit=T&" name="form1" onsubmit="return beforeSubmit()" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							<s:text name="giftCode.createGiftCode"></s:text>
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="giftbag.name"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						${giftCode.giftbagName}
						<input type="hidden" name="giftBagId" value="${giftCode.giftBagId}"/>
						<input type="hidden" name="gameWebId" value="${gameWebId}" />
					</td>
				</tr>
			<!--	<tr>
					<td width="180">
						
						<s:text name="giftCode.code"></s:text><s:text name="colon"></s:text>
					</td>
					  <td colspan="3">
						<input type="text" name="code" value=""/><s:text name="giftCode.codeEx"></s:text>
					</td>
				</tr>
		    -->
				
				<tr>
					<td>
						<s:text name="giftCode.codeNum"></s:text><s:text name="colon"></s:text>
					</td>							
					<td colspan="4">
						
						<select name="codeNum" class="select">
							
							<% for (int i=8;i<=16;i++) {%>
								<option value="<%=i%>"><%=i%>
							<%}%>
						</select>
					</td>	
				</tr>
				
				<tr>
					<td>
						<s:text name="giftCode.num"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<input type="text" id="num" name="num" value="1" />
						<font color="red">说明：礼包码个数超过50万，请联系管理员导出礼包码。</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="giftCode.effectiveTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<input type="text" id="effectiveTime" name="effectiveTime" class="Wdate" style="width:160px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', startDate:'%y-%M-%d 00:00:00', onpicked:function(){loseTime.focus();}})"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="giftCode.loseTime"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<input type="text" id="loseTime" name="loseTime" class="Wdate" style="width:160px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', startDate:'%y-%M-%d 00:00:00', minDate:'#F{$dp.$D(\'effectiveTime\')}'})"/>
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						<input name="subBtn" type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
<script src="../../js/jquery/jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("form").validate({
			rules:{
				code:{
					maxlength:1
				},
				effectiveTime:{
					required:true
				},
				num:{
					required:true,
					maxlength:8,
					digits:true
				},
				loseTime:{
					required:true
				}
			}		
		});	
	});
</script>