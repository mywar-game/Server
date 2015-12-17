<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>宠物解锁配置列表</title>

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
			ajaxobj.url="reflashConstantCache?cacheType=<s:property value='@com.dataconfig.constant.ReflashCacheConstant@REFLASH_TYPE_PET_UNLOCK'/>&number="+Math.random();
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新宠物解锁配置缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新宠物解锁配置缓存失败!');
				}
		    }
			ajaxobj.send();
		}
	</script>
	<body>
		<input type="button" value="刷新宠物解锁配置缓存" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						宠物解锁配置列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					宠物解锁等级
				</td>
				<td>
					宠物屋解锁等级
				</td>
				<td>
					驯兽术解锁等级
				</td>
				<td>
					宠物数组
				</td>
				<td>
					宠物数量
				</td>
				<td>
					解锁所消耗钻石
				</td>
				<td>
					当前宠物解锁，最多可以查看到多少阶
				</td>
				<td>
					解锁宠物属性加成
				</td>
			</tr>
			<s:iterator var="petUnlock" value="petUnlockList">
				<tr>
					<td>
						${petUnlock.petUnlockId}
					</td>
					<td>
						${petUnlock.petStoreUnlockLevel}
					</td>
					<td>
						${petUnlock.petTechnologyUnlockLevel}
					</td>
					<td>
						${petUnlock.petIds}
					</td>
					<td>
						${petUnlock.petNum}
					</td>
					<td>
						${petUnlock.petUnlockGolden}
					</td>
					<td>
						${petUnlock.maxLookPetUnlockId}
					</td>
					<td>
						${petUnlock.addAttribute}
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