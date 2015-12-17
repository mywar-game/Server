<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="memCacheInfoListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>

	<body>
		<form action="memCacheInfoList" method="post" >
			<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="24" align="center">
						<center>
							<s:text name="memCacheInfoListJsp.title"></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="mMemCacheInfo.adress"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.bytes_written"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.connection_structures"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.bytes"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.total_items"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.total_connections"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.uptime"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.pid"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.get_hits"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.curr_items"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.evictions"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.version"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.cmd_get"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.time"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.pointer_size"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.cmd_set"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.threads"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.limit_maxbytes"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.bytes_read"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.curr_connections"></s:text>
					</td>
					<td>
						<s:text name="mMemCacheInfo.get_misses"></s:text>
					</td>
					<td>
						<s:text name="memCacheInfoListJsp.cmdGetTimesPerSecend"></s:text>
					</td>
					<td>
						<s:text name="memCacheInfoListJsp.hitRate"></s:text>
					</td>
				</tr>
				
				<tr>
					<td>
						${memCacheInfo.adress}
					</td>
					<td>
						${memCacheInfo.bytes_written}
					</td>
					<td>
						${memCacheInfo.connection_structures}
					</td>
					<td>
						${memCacheInfo.bytes}
					</td>
					<td>
						${memCacheInfo.total_items}
					</td>
					<td>
						${memCacheInfo.total_connections}
					</td>
					<td>
						${memCacheInfo.uptime}
					</td>
					<td>
						${memCacheInfo.pid}
					</td>
					<td>
						${memCacheInfo.get_hits}
					</td>
					<td>
						${memCacheInfo.curr_items}
					</td>
					<td>
						${memCacheInfo.evictions}
					</td>
					<td>
						${memCacheInfo.version}
					</td>
					<td>
						${memCacheInfo.cmd_get}
					</td>
					<td>
						${memCacheInfo.time}
					</td>
					<td>
						${memCacheInfo.pointer_size}
					</td>
					<td>
						${memCacheInfo.cmd_set}
					</td>
					<td>
						${memCacheInfo.threads}
					</td>
					<td>
						${memCacheInfo.limit_maxbytes}
					</td>
					
					<td>
						${memCacheInfo.bytes_read}
					</td>
					<td>
						${memCacheInfo.curr_connections}
					</td>
					<td>
						${memCacheInfo.get_misses}
					</td>
					<td>
						${memCacheInfo.cmd_get/memCacheInfo.uptime}
					</td>
					<td>
						${memCacheInfo.get_hits/memCacheInfo.cmd_get*100}%
					</td>
				</tr>
				<tr>
					<td colspan="24">
						<aldtags:pageTag />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
