<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>激活码各平台统计</title>
		<style type="text/css">
		td {
			text-align: center;
		}
		</style>		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						激活码各平台统计
					</center>
				</td>
			</tr>
			<tr>
				<td>
					平台
				</td>
				<td>
					生成数
				</td>
				<td>
					使用数
				</td>
				<td>
					剩余数
				</td>
			</tr>
			<s:iterator value="statsMap">
				<tr>
					<td>
						<s:if test="key == ''">
							未指定平台
						</s:if>
						<s:else>
							${key}
						</s:else>
					</td>
					<td>
						<s:property value="value.split('_')[0]"/>
					</td>
					<td>
						<s:property value="value.split('_')[1]"/>
					</td>
					<td>
						<s:property value="value.split('_')[0] - value.split('_')[1]"/>
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>