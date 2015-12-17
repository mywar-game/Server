<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="legionListJsp.title"></s:text></title>
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
					军团列表
				</th>
			</tr>
			<tr>
				<td>
					军团编号
				</td>
				<td>
					军团名称
				</td>
				<td>
					军团公告
				</td>
				<td>
					军团等级
				</td>
				<td>
					军团战斗力
				</td>
				<td>
					军团贡献值
				</td>
				<td>
					查看军团成员
				</td>
			</tr>
			<s:iterator var="map" value="list">
				<tr>
					<td>
						${map.id}
					</td>
					<td>
						${map.name}
					</td>
					<td>
						${map.notice}
					</td>
					<td>
						${map.level}
					</td>
					<td>
						${map.power}
					</td>
					<td>
						${map.contribution}
					</td>
					<td>
						<a href="legionUserInfoList?legionId=${map.id}"><s:text name="查看军团成员"></s:text></a>
					</td>
				</tr>
			</s:iterator>
			<tr>
			</tr>
		</table>
		</div>
	</body>
</html>