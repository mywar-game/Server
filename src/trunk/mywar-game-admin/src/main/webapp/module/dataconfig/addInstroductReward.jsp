<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加推荐奖励</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<body>
		&nbsp;
		<form action="addInstroductReward?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
							添加好友推荐奖励 &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
								<tr>
					<td>
						推荐奖励编号
					</td>
					<td>
						<input type="text" name="instrocuctRewardId" value="${instrocuctReward.instrocuctRewardId}" class="maxLife" size="10" maxlength="11" readonly="readonly" />
						自动生成
					</td>
					<td>
						推荐奖励等级
					</td>
					<td>
						<input type="text" name="instroductorUserLevel" value="${instrocuctReward.instroductorUserLevel}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						奖励一道具编号
					</td>
					<td>
						<input type="text" name="reward1Id" value="${instrocuctReward.reward1Id}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						奖励一道具数量
					</td>
					<td>
						<input type="text" name="reward1Num" value="${instrocuctReward.reward1Num}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						奖励二道具编号
					</td>
					<td>
						<input type="text" name="reward2Id" value="${instrocuctReward.reward2Id}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						奖励二道具数量
					</td>
					<td>
						<input type="text" name="reward2Num" value="${instrocuctReward.reward2Num}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
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