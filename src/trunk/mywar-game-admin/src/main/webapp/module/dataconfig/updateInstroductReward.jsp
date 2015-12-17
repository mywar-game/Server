<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>军团科技常量修改</title>
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
		<form action="updateInstroductReward?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
							更新好友推荐奖励 &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						推荐奖励编号
					</td>
					<td>
						<input type="text" name="instroductorRewardId" value="${instroductorReward.instroductorRewardId}" class="maxLife" size="10" maxlength="11" readonly="readonly" />
						自动生成
					</td>
					<td>
						被邀请者玩家等级
					</td>
					<td>
						<input type="text" name="beInvitedUserLevel" value="${instroductorReward.beInvitedUserLevel}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				
				<s:generator separator=";" val="instroductorReward.beInvitedUserReward">
					<s:iterator var="reward" status="sta">
						<s:set var="id" value="#reward.split(':')[0]"></s:set>
						<s:set var="num" value="#reward.split(':')[1]"></s:set>
							<tr>
							<s:if test="#sta.isFirst()">
								<td rowspan="<s:property value="instroductorReward.beInvitedUserReward.split(';').length"/>">
									被邀请者获得奖励
								</td>
							</s:if>
							<td>
								<input type="text" name="beInvitedUserRewardId" value="${id}" class="maxLife" size="5" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
							</td>
							<td>
								<input type="text" name="beInvitedUserRewardNum" value="${num}" class="maxLife" size="2" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
							</td>
					</s:iterator>
				</s:generator>
				
				<s:generator separator=";" val="instroductorReward.invitedUserReward">
					<s:iterator var="reward" status="sta">
						<s:set var="id" value="#reward.split(':')[0]"></s:set>
						<s:set var="num" value="#reward.split(':')[1]"></s:set>
							<tr>
							<s:if test="#sta.isFirst()">
								<td rowspan="<s:property value="instroductorReward.invitedUserReward.split(';').length"/>">
									邀请者获得奖励
								</td>
							</s:if>
							<td>
								<input type="text" name="invitedUserRewardId" value="${id}" class="maxLife" size="5" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
							</td>
							<td>
								<input type="text" name="invitedUserRewardNum" value="${num}" class="maxLife" size="2" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
							</td>
					</s:iterator>
				</s:generator>
				
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