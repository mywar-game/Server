<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>训练营配置修改</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function isSubmit(){
			if(window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
		}
	</script>
	<body>
		&nbsp;
		<form action="updateTrainingConfig?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							训练营配置修改 &nbsp;
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						训练营等级<s:text name="colon"></s:text>
					</td>
					<td>
						${trainingConfig.level}
						<input type="hidden" name="level" value="${trainingConfig.level}">
					</td>
				</tr>
				<tr>
					<td>
						训练时长<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="trainingTime" value="${trainingConfig.trainingTime}" class="maxLife" size="128"/>
					</td>
				</tr>
				<tr>
					<td>
						消耗金币数<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="costMoney" value="${trainingConfig.costMoney}" class="maxLife" size="128"/>
					</td>
				</tr>
				<tr>
					<td>
						使用金币的训练效率<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="moneyGetExp" value="${trainingConfig.moneyGetExp}" class="maxLife" size="11" />
					</td>
				</tr>
				<tr>
					<td>
						消耗钻石数<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="costGolden" value="${trainingConfig.costGolden}" class="maxLife" size="128" />
					</td>
				</tr>
				<tr>
					<td>
						使用钻石的训练效率<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="goldenGetExp" value="${trainingConfig.goldenGetExp}" class="maxLife" size="11" />
					</td>
				</tr>
				<tr>
					<td>
						魔鬼训练获得的经验值<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="devilTrainingGetExp" value="${trainingConfig.devilTrainingGetExp}" class="maxLife" size="11" />
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
	<script src="../../js/jquery/jquery.min.js"></script>
	<script src="../../js/jquery/jquery.validate.js"></script>
	<script type="text/javascript" >
		$(document).ready(function(){
			$("form").validate({
				rules:{
					trainingTime:{
						maxlength:128,
						required: true
					},
					costMoney:{
						maxlength:128,
						required: true
					},
					moneyGetExp:{
						digits:true,
						required: true
					},
					costGolden:{
						maxlength:128,
						required: true
					},
					goldenGetExp:{
						digits:true,
						required: true
					},
					devilTrainingGetExp:{
						digits:true,
						required: true
					},
				}
			})
		});
	</script>
</html>