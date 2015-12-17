<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
		<title><s:text name="getOnlineUserInfoListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
<!--	<script type="text/javascript" src="../../js/ajax.js"></script>-->
	<script type="text/javascript" src="../../js/jquery/jquery.min.js"></script>
	<script type="text/javascript">
		//		function savePlayerTreatment(chatObject,type,name){
//			var typeTextArray = new Array();
//			<s:generator separator="," val="%{getText('userChatLogListJsp.operation_value')}">
//				<s:iterator var="str">
//						typeTextArray.push("<s:text name="%{'userChatLogListJsp.operation_'+#str}"></s:text>");
//				</s:iterator>
//			</s:generator>
//			//alert(typeTextArray);	
//			if(window.confirm('<s:text name="userChatLogListJsp.operateConfirm"><s:param>'+name+'</s:param><s:param>'+typeTextArray[type]+'</s:param></s:text>')){
//				var ajaxobj = new Ajax();
//		        ajaxobj.url="../log/userChatLogList?operateType="+type+"&userID="+chatObject+"&sysNum="+${adminUser.exp1};
//			    ajaxobj.send();
//			}
//		}

		function findAddress(a,ip){
			$.getScript("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip="+ip,function(){
				country=remote_ip_info.country;           //获得国家信息
				province=remote_ip_info.province;         //获得省份信息
				city = remote_ip_info.city;               //获得城市信息
				//ip = remote_ip_info.start;                //获得IP地址
				a.parentNode.innerHTML = country + " " + province + " " + city;
				//district=remote_ip_info.district; //获得区信息
				//ISP=remote_ip_info.isp; //获得ISP信息
				//type=remote_ip_info.type; //获得服务提供商类型
				//other=remote_ip_info.desc; //获取其他信息
				 
				//alert(country);
				// alert(province);
				//alert(city);
				//alert(ip);
			});
		}

	</script>
	<body>
		<form action="getOnlineUserInfoList?isSearch=T" method="post" >
			<table>
				<tr>
					<td><s:text name="log.userIdSearch"></s:text><s:text name="colon"></s:text></td>
					<td><input type="text" id="user.userId" name="user.userId" value="${user.userId}" onblur="value=value.replace(/[^\d]/g,'')" onkeyup="value=value.replace(/[^\d]/g,'')" /></td>
					<td><s:text name="log.userNameSearch"></s:text><s:text name="colon"></s:text></td>
					<td><input type="text" id="user.userName" name="user.userName" value="${user.userName}"/></td>
					<td><s:text name="log.nameSearch"></s:text><s:text name="colon"></s:text></td>
					<td><input type="text" id="user.name" name="user.name" value="${user.name}"/></td>
					<td><input type="submit" value="<s:text name="search"></s:text>" /></td>
				</tr>
			</table>
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center >
			<tr class="border">
				<td class="td_title" colspan="100" align="center" >
					<center>
						<s:text name="getOnlineUserInfoListJsp.title"></s:text>
						<font color="red">${erroDescrip}</font>
					</center>
				</td>
			</tr>
			
			<tr>
				<td colspan="10">
					<b>在线人数:<s:property value="userIdAndLastLoginTimeMap.size()"/></b>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="user.userId"></s:text>
				</td>
				<td>
					<s:text name="user.userName"></s:text>
				</td>
				<td>
					<s:text name="user.name"></s:text>
				</td>
				<td>
					<s:text name="getOnlineUserInfoListJsp.loginTime"></s:text>
				</td>
				<td>
					<s:text name="getOnlineUserInfoListJsp.onlineTimes"></s:text>
				</td>
				<td>
					地理位置
				</td>
<!--				<td colspan="4">-->
<!--					 <s:text name="getOnlineUserInfoListJsp.operation"></s:text>-->
<!--				</td>-->
			</tr>
			<s:iterator var="userSomeInfo" value="list">
				<tr>
					<td>
						${userSomeInfo.userId}
					</td>
					<s:if test="userSomeInfo.name == ''">
						<td colspan="2">
							<font color="grey">没有创建角色</font>
						</td>
					</s:if>
					<s:else>
					<td>
						${userSomeInfo.userName}
					</td>
					<td>
						${userSomeInfo.name}
					</td>
					</s:else>
					<td>
						${userSomeInfo.loginTime}
					</td>
					<td>
						<s:property value="#userSomeInfo.liveTime/60"/>
					</td>
					<td>
						<a href="javascript:;" onclick='findAddress(this,"${userSomeInfo.ip}")'>地理位置</a>
					</td>
<!--					<s:generator separator="," val="%{getText('userChatLogListJsp.operation_value')}">-->
<!--						<s:iterator var="str">-->
<!--							<td>-->
<!--								<input type="button" value="<s:text name="%{'userChatLogListJsp.operation_'+#str}"></s:text>"  onclick='savePlayerTreatment(${key},${str},"${userIdUserMap[key].name}")'/>-->
<!--							</td>-->
<!--						</s:iterator>-->
<!--					</s:generator>-->
				</tr>
			</s:iterator>
		</table>
	</body>
</html>