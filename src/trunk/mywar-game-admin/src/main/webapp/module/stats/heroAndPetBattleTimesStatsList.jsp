<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="heroAndPetBattleTimesStatsListJsp.title"></s:text></title>
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
		<table id="table1" cellpadding="3" cellspacing="1" border="0" width="50%" align=left>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="heroAndPetBattleTimesStatsListJsp.heroBattleTimes"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="heroAndPetBattleTimesStatsListJsp.name"></s:text>
				</td>
				<td>
					<s:text name="heroAndPetBattleTimesStatsListJsp.battleTimes"></s:text>
				</td>
			</tr>
			
			<s:iterator value="statsMap">
				<s:set var="name" value="heroIdNameMap[key]"></s:set>
				<s:if test="#name != null">
					<tr>
						<td>
							${name}
						</td>
						<td>
							${value}
						</td>
					</tr>
				</s:if>
			</s:iterator>
		</table>
		<table id="table2" cellpadding="3" cellspacing="1" border="0" width="50%" align=left>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="heroAndPetBattleTimesStatsListJsp.petBattleTimes"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="heroAndPetBattleTimesStatsListJsp.name"></s:text>
				</td>
				<td>
					<s:text name="heroAndPetBattleTimesStatsListJsp.battleTimes"></s:text>
				</td>
			</tr>
			
			<s:iterator value="statsMap">
				<s:set var="name" value="petIdNameMap[key]"></s:set>
				<s:if test="#name != null">
					<tr>
						<td>
							${name}
						</td>
						<td>
							${value}
						</td>
					</tr>
				</s:if>
			</s:iterator>
		</table>
	</body>
</html>