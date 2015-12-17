<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>修改玩家数据</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript">
	</script>
	<body>
		<form action="modifyUserSomeData?isCommit=T" method="post" >
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							修改玩家数据 &nbsp;
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						角色名<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="name" value="${name}" class="maxLife" size="64" />
					</td>
				</tr>
				<tr>
					<td>
						科技点<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="userTechnologyPoint" value="${userTechnologyPoint}" class="maxLife" size="11" />
						0时不变，正数(如xx)时当前值加xx，负数(如-xx)时当前值减xx
					</td>
				</tr>
				<tr>
					<td>
						基因点<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="userGenePoint" value="${userGenePoint}" class="maxLife" size="11" />
						0时不变，正数(如xx)时当前值加xx，负数(如-xx)时当前值减xx
					</td>
				</tr>
				<tr>
					<td>
						建筑点<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="userBuildingPoint" value="${userBuildingPoint}" class="maxLife" size="11" />
						0时不变，正数(如xx)时当前值加xx，负数(如-xx)时当前值减xx
					</td>
				</tr>
				<tr>
					<td>
						体力值<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="pveTimesPerDay" value="${pveTimesPerDay}" class="maxLife" size="11" />
						0时不变，正数(如xx)时当前值加xx，负数(如-xx)时当前值减xx
					</td>
				</tr>
				<tr>
					<td>
						添加充值数<s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="payAmount" value="${payAmount}" class="maxLife" size="11" />
						0时不充值，正数(如xx)时充xx
					</td>
				</tr>
				<tr>
					<td>
						重置<s:text name="colon"></s:text>
					</td>
					<td>
						<select name="sex" id="sex" class="select">
							<option value="0">无重置</option>
							<option value="1">酒馆 啤酒购买次数</option>
							<option value="2">npc产出次数</option>
							<option value="4">天梯 体力值</option>
							<option value="5">天梯 体力值的购买次数</option>
							<option value="6">日常任务完成个数</option>
							<option value="7">好友帮助次数</option>
							<option value="8">炼金术次数及交流次数</option>
							<option value="9">宠物勘探次数</option>
							<option value="10">竞技场攻打次数</option>
							<option value="11">竞技场攻打排行次数</option>
							<option value="12">监狱pve次数</option>
							<option value="13">监狱pve购买次数</option>
							<option value="14">监狱pvp次数</option>
							<option value="15">监狱pvp购买次数</option>
						</select>
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
					name:{
						maxlength:64,
						required: true
					},
					userGenePoint:{
						number:true,
						required: true
					},
					userTechnologyPoint:{
						number:true,
						required: true
					},
					userBuildingPoint:{
						number:true,
						required: true
					},
					pveTimesPerDay:{
						number:true,
						required: true
					},
					payAmount:{
						digits:true,
						required: true
					}
				}
			})
		});
	</script>
</html>