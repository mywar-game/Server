<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>推荐领取奖励</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		function reflashconstantcache() {
			var ajaxobj = new Ajax();
			ajaxobj.url="reflashConstantCache?cacheType=26&number="+Math.random();;
		    ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新成功');
				} else {
					alert('刷新失败');
				}
		    }
			ajaxobj.send();
		}
		
		function del(instroductorRewardId) {
			if (window.confirm("是否确定删除")) {
				var ajaxobj = new Ajax();
				ajaxobj.url = "delInstroductReward?instroductorRewardId=" + instroductorRewardId;
				ajaxobj.callback = function() {
					//alert(ajaxobj.gettext());
					var tr = document.getElementById(instroductorRewardId);
					var table = document.getElementById("table");
					table.deleteRow(tr.rowIndex);
				}
				ajaxobj.send();
			}
	
		}
	
		function update(InstroductReward) {
			window.location.href = "updateInstroductReward?instroductorRewardId=" + InstroductReward;
		}
	
		function add() {
			window.location.href = "addInstroductReward";
		}
	</script>
	<body>
		<input type="submit" value="添加推荐奖励" class="button" onclick=add(); />
		<input type="button" value='刷新常量' class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						推荐奖励常量
					</center>
				</td>
			</tr>
			<tr>
				<td>
					推荐奖励编号
				</td>
				<td>
					被邀请者玩家等级
				</td>
				<td>
					被邀请者获得奖励
				</td>
				<td>
					邀请者获得奖励
				</td>
				
				<td width="35">
					<s:text name="delete"></s:text>
				</td>
				<td width="35">
					<s:text name="update"></s:text>
				</td>
			</tr>
			<s:iterator var="instroduct" value="introductorRewardList">
				<tr id="${instroduct.instroductorRewardId}">
					<td>
						${instroduct.instroductorRewardId}
					</td>
					<td>
						${instroduct.beInvitedUserLevel}
					</td>
					<td>
						<s:generator separator=";" val="#instroduct.beInvitedUserReward">
							<s:iterator var="reward">
								<s:set var="id" value="#reward.split(':')[0]"></s:set>
								<s:set var="num" value="#reward.split(':')[1]"></s:set>
								<a href="tTreasureConstantList?id=${id}"><s:property value="treasureIDNameMap[#id]"/></a>*${num}<br/>
							</s:iterator>
						</s:generator>
					</td>
					<td>
						<s:generator separator=";" val="#instroduct.invitedUserReward">
							<s:iterator var="reward">
								<s:set var="id" value="#reward.split(':')[0]"></s:set>
								<s:set var="num" value="#reward.split(':')[1]"></s:set>
								<a href="tTreasureConstantList?id=${id}"><s:property value="treasureIDNameMap[#id]"/></a>*${num}<br/>
							</s:iterator>
						</s:generator>
					</td>
					
					
					<td>
						<a href="#" onclick='del("${instroduct.instroductorRewardId}")'><s:text name="delete"></s:text></a>
					</td>
					<td>
						<a href="#" onclick='update("${instroduct.instroductorRewardId}")'><s:text name="update"></s:text></a>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="10">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>