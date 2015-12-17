<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>缓存使用统计</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<body>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						缓存使用记录
					</center>
				</td>
			</tr>
			<tr>
				<td>
					缓存名称
				</td>
				<td>
					get方法调用次数
				</td>
				<td>
					根据索引查询次数
				</td>
				<td>
					列表查询次数
				</td>
				<td>
					删除次数
				</td>
				<td>
					更新次数
				</td>
				<td>
					添加次数
				</td>
				<td>
					从DB查询次数
				</td>
				<td>
					同步查询次数
				</td>
				<td>
					查不到数据的次数
				</td>
				<td>
					当前key数量
				</td>
			</tr>
			<s:iterator var="memcacheTemp" value="memcacheInfoList">
				<tr>
					<td>
						${memcacheTemp.cacheName}
					</td>
					<td>
						${memcacheTemp.queryNum}
						<s:if test="!memcacheInfo.queryNum.equals('')">
							(<fmt:formatNumber type="percent" value="${memcacheTemp.queryNum/memcacheInfo.queryNum}" pattern="#.#%" />)
						</s:if>
						<s:else>
							(0%)
						</s:else>
					</td>
					<td>
						${memcacheTemp.indexNum}
						<s:if test="!memcacheInfo.indexNum.equals('')">
							(<fmt:formatNumber type="percent" value="${memcacheTemp.indexNum/memcacheInfo.indexNum}" pattern="#.#%" />)
						</s:if>
						<s:else>
							(0%)
						</s:else>
					</td>
					<td>
						${memcacheTemp.listNum}
						<s:if test="!memcacheInfo.listNum.equals('')">
							(<fmt:formatNumber type="percent" value="${memcacheTemp.listNum/memcacheInfo.listNum}" pattern="#.#%" />)
						</s:if>
						<s:else>
							(0%)
						</s:else>
					</td>
					<td>
						${memcacheTemp.delNum}
						<s:if test="!memcacheInfo.delNum.equals('')">
							(<fmt:formatNumber type="percent" value="${memcacheTemp.delNum/memcacheInfo.delNum}" pattern="#.#%" />)
						</s:if>
						<s:else>
							(0%)
						</s:else>
					</td>
					<td>
						${memcacheTemp.saveorupdateNum}
						<s:if test="!memcacheInfo.saveorupdateNum.equals('')">
							(<fmt:formatNumber type="percent" value="${memcacheTemp.saveorupdateNum/memcacheInfo.saveorupdateNum}" pattern="#.#%" />)
						</s:if>
						<s:else>
							(0%)
						</s:else>
					</td>
					<td>
						${memcacheTemp.addNum}
						<s:if test="!memcacheInfo.addNum.equals('')">
							(<fmt:formatNumber type="percent" value="${memcacheTemp.addNum/memcacheInfo.addNum}" pattern="#.#%" />)
						</s:if>
						<s:else>
							(0%)
						</s:else>
					</td>
					<td>
						${memcacheTemp.fromDataBaseNum}
						<s:if test="!memcacheInfo.fromDataBaseNum.equals('')">
							(<fmt:formatNumber type="percent" value="${memcacheTemp.fromDataBaseNum/memcacheInfo.fromDataBaseNum}" pattern="#.#%" />)
						</s:if>
						<s:else>
							(0%)
						</s:else>
					</td>
					<td>
						${memcacheTemp.synNum}
						<s:if test="!memcacheInfo.synNum.equals('')">
							(<fmt:formatNumber type="percent" value="${memcacheTemp.synNum/memcacheInfo.synNum}" pattern="#.#%" />)
						</s:if>
						<s:else>
							(0%)
						</s:else>
					</td>
					<td>
						${memcacheTemp.missFromMemcachedNum}
						<s:if test="!memcacheInfo.missFromMemcachedNum.equals('')">
							(<fmt:formatNumber type="percent" value="${memcacheTemp.missFromMemcachedNum/memcacheInfo.missFromMemcachedNum}" pattern="#.#%" />)
						</s:if>
						<s:else>
							(0%)
						</s:else>
					</td>
					<td>
						${memcacheTemp.keyNum}
						<s:if test="!memcacheInfo.keyNum.equals('')">
							(<fmt:formatNumber type="percent" value="${memcacheTemp.keyNum/memcacheInfo.keyNum}" pattern="#.#%" />)
						</s:if>
						<s:else>
							(0%)
						</s:else>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td>
					总计
				</td>
				<td>
					${memcacheInfo.queryNum}
				</td>
				<td>
					${memcacheInfo.indexNum}
				</td>
				<td>
					${memcacheInfo.listNum}
				</td>
				<td>
					${memcacheInfo.delNum}
				</td>
				<td>
					${memcacheInfo.saveorupdateNum}
				</td>
				<td>
					${memcacheInfo.addNum}
				</td>
				<td>
					${memcacheInfo.fromDataBaseNum}
				</td>
				<td>
					${memcacheInfo.synNum}
				</td>
				<td>
					${memcacheInfo.missFromMemcachedNum}
				</td>
				<td>
					${memcacheInfo.keyNum}
				</td>
			</tr>
		</table>
	</body>
</html>