<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加宠物装备额外加成</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
		var erroDescrip = '${erroDescrip}';
		if (erroDescrip != "") {
			alert(erroDescrip);
		}
	</script>
	<body>
		&nbsp;
		<form action="addPPetEquipmentConstant?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<center>
							添加宠物装备额外加成 &nbsp;
							<font color='red'>${erroDescrip}</font>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						宠物装备额外加成编号
					</td>
					<td>
						<input type="text" name="petEquipmentId" value="${pPetEquipment.petEquipmentId}" class="maxLife" size="11" maxlength="11" />
					</td>
					<td>
						宠物类型
					</td>
					<td>
						<input type="text" name="type" value="${pPetExtra.type}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				
				<tr>
					<td>
						宠物最低攻击加成
					</td>
					<td>
						<input type="text" name="lowAttackAdd" value="${pPetExtra.lowAttackAdd" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				
				<tr>
					<td>
						宠物最高攻击加成
					</td>
					<td>
						<input type="text" name="highAttackAdd" value="${pPetExtra.highAttackAdd}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				
				<tr>
					<td>
						宠物攻击速度加成
					</td>
					<td>
						<input type="text" name="attackSpeedAdd" value="${pPetExtra.attackSpeedAdd}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td>
						宠物暴击值加成
					</td>
					<td>
						<input type="text" name="cirtRateAdd" value="${pPetExtra.cirtRateAdd}" class="maxLife" size="11" maxlength="11" />
					</td>
					<td>
						宠物命中值加成
					</td>
					<td>
						<input type="text" name="hitRateAdd" value="${pPetExtra.hitRateAdd}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>
				
				<tr>
					<td>
						宠物最大等级
					</td>
					<td>
						<input type="text" name="petLevelMax" value="${pPetExtra.petLevelMax}" class="maxLife" size="11" maxlength="11" />
					</td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>