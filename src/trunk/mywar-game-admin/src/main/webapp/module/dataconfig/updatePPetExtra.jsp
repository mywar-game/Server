<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="updateBNpcConstantJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
		function isSubmit(){
			if(window.confirm("确认更新宠物额外加成"))
				return true;
			return false;
		}
	</script>
	<body>
		&nbsp;
		<form action="updatePPetExtra?isCommit=T" method="post" onsubmit="return isSubmit()">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						更新宠物额外加成
					</td>
				</tr>
				<tr>
					<td>
						宠物类型（2进程3远程）
					</td>
					<td>
						<input type="text" name="type" value="${petExtra.type}" class="maxLife" size="10" maxlength="11" readonly="readonly" />
						<s:text name="cannotEdit"></s:text>
					</td>
				</tr>
				<tr>
					<td>
						最低攻击加成
					</td>
					<td>
						<input type="text" name="lowAttackAdd" value="${petExtra.lowAttackAdd}" class="maxLife" size="10" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						最高攻击加成
					</td>
					<td>
						<input type="text" name="highAttackAdd" value="${petExtra.highAttackAdd}" class="maxLife" size="10" maxlength="11"/>
					</td>
				</tr>
				<tr>
					<td>
						攻击速度加成
					</td>
					<td>
						<input type="text" name="attackSpeedAdd" value="${petExtra.attackSpeedAdd}" class="maxLife" size="10" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						暴击加成
					</td>
					<td>
						<input type="text" name="cirtRateAdd" value="${petExtra.cirtRateAdd}" class="maxLife" size="150" maxlength="200" />
					</td>
				</tr>
				<tr>
					<td>
						命中加成
					</td>
					<td>
						<input type="text" name="hitRateAdd" value="${petExtra.hitRateAdd}" class="maxLife" size="150" maxlength="200" />
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