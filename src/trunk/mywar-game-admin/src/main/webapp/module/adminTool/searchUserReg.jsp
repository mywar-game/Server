<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
		<title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
		<script>
			function checkIsNull() {
				var lodoId = document.getElementById("lodoId").value;
				var userName = document.getElementById("userName").value;
				
				if (!lodoId && !userName) {
					alert("请输入查询条件");
					return false;
				}
				return true;
			}
		</script>
	</head>
	<body>
		<form action="searchUserReg?isCommit=T" method="post" onsubmit="return checkIsNull();">
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center" >
						<center>
							查询用户注册信息
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="log.lodoSearch"></s:text><s:text name="colon"></s:text><input type="text" name="lodoId" value="${lodoId}" id="lodoId" size="10">
					</td>
					<td>
						<s:text name="log.userNameSearch"></s:text><s:text name="colon"></s:text><input type="text" name="userName" value="${userName}" id="userName" size="10">
					</td>
					<td>
						<input type="submit" value="<s:text name="search"></s:text>" class="button">
					</td>
				</tr>
			</table>
			<table>
				<c:if test="${userRegLogList != null}">
				<tr>
					<td>
						用户ID
					</td>
					<td>
						合作商游戏用户ID
					</td>
					<td>
						大渠道号
					</td>
					<td>
						小渠道号
					</td>
					<td>
						注册时间
					</td>
					<td>
						注册IP
					</td>
					<td>
						注册MAC
					</td>
					<td>
						注册IMEI
					</td>
					<td>
						注册IDFA
					</td>
					<td>
						机型
					</td>
				</tr>
				<s:iterator var="userReg" value="userRegLogList">
					<tr>
						<td>
							${userReg.userId}
						</td>
						<td>
							${userReg.userName}
						</td>
						<td>
							${userReg.channel}
						</td>
						<td>
							${userReg.smallChannel}
						</td>
						<td>
							${userReg.regTime}
						</td>
						<td>
							${userReg.regIp}
						</td>
						<td>
							${userReg.regMac}
						</td>
						<td>
							${userReg.regImei}
						</td>
						<td>
							${userReg.regIdfa}
						</td>
						<td>
							${userReg.regMobile}
						</td>
					</tr>
				</s:iterator>
				</c:if>
			</table>
		</form>
	</body>
</html>