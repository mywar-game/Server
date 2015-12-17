<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
		<title><s:text name="getOnlineUserIPInfoListJsp.title"></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/jquery/jquery.min.js"></script>
	</head>
	<script type="text/javascript">
		function findAddress(a,ip){
			$.getScript("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip="+ip,function(){
				country=remote_ip_info.country;           //获得国家信息
				province=remote_ip_info.province;         //获得省份信息
				city = remote_ip_info.city;               //获得城市信息
				//ip = remote_ip_info.start;                //获得IP地址
				a.parentNode.innerHTML = country + " " + province + " " + city + " " + ip;
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
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center >
			<tr class="border">
				<td class="td_title" colspan="100" align="center" >
					<center>
						<s:text name="getOnlineUserIPInfoListJsp.title"></s:text>
						<font color="red">${erroDescrip}</font>
					</center>
				</td>
			</tr>

			<tr>
				<td colspan="10">
					<s:text name="getOnlineUserIPInfoListJsp.onlineUserNum"></s:text><s:text name="colon"></s:text>
					<s:set name="userNum" value="0"></s:set>
					<s:iterator value="map">
						<s:iterator var="name" value="value">
							<s:if test="#name != ''">
								<s:set name="userNum" value="#userNum+1"></s:set>
							</s:if>
						</s:iterator>
					</s:iterator>
					<s:property value="#userNum"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<s:text name="getOnlineUserIPInfoListJsp.onlineIPNum"></s:text><s:text name="colon"></s:text>
					<s:property value="map.size()"/>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="getOnlineUserIPInfoListJsp.IP"></s:text>
				</td>
				<td>
					<s:text name="getOnlineUserIPInfoListJsp.userNum"></s:text>
				</td>
				<td>
					<s:text name="user.name"></s:text>
				</td>
			</tr>
		
			<s:iterator value="map">
				<tr>
					<td>
						${key}&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick='findAddress(this,"${key}")'>地理位置</a>
					</td>
					<td>
						<s:property value="value.length"/>
					</td>
					<td>
						<s:iterator var="name" value="value">
							<s:if test="#name == ''">
								<font color="grey">没有创建角色</font>,
							</s:if>
							<s:else>
								${name},
							</s:else>
						</s:iterator>
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>