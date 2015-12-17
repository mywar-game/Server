<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="legionUserInfoListJsp.title"></s:text></title>
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
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/log_WdatePicker.js"></script>  
		<script type="text/javascript" src="../../js/ajax.js"></script> 
	</head>
	<script type="text/javascript"> 
			
	</script>
	<body>
		<div>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr>
				<th colspan="22">
					军团成员列表
				</th>
			</tr>
			<tr>
				<td>
					游戏ID
				</td>
				<td>
					玩家名
				</td>
				<td>
					军团身份
				</td>
				<td>
					贡献值
				</td>
			</tr>
			<s:iterator var="map" value="list">
				<tr>
					<td>
						${map.lodoId}
					</td>
					<td>
						${map.name}
					</td>
					<td>
						<s:if test="#map.identity == 0">
							普通成员
						</s:if>
						<s:if test="#map.identity == 1">
							军团长
						</s:if>
						<s:if test="#map.identity == 2">
							副军团长
						</s:if>
					</td>
					<td>
						${map.contribution}
					</td>
				</tr>
			</s:iterator>
			<tr>
			</tr>
		</table>
		</div>
	</body>
</html>