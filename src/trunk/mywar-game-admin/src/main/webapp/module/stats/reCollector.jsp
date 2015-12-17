<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
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
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/stats_WdatePicker.js"></script>  
		<script>
			var caiji = function(name) {
				var searchDate = document.getElementById("caijiDate").value;
				if (!searchDate || searchDate == "") {
					alert("请选择重新采集时间点!");
					return;
				} else {
					alert("暂时还在测试中!");
					//window.location.href="reCollector";
				}
			}
			var newCaiji = function(name) {
				var searchDate = document.getElementById("caijiDate").value;
				if (!searchDate || searchDate == "") {
					alert("请选择重新采集时间点!");
					return;
				} else {
					var nameDesc = '';
					if (name == 'userDiamond') {
						nameDesc = '钻石统计';
					} else if (name == 'userCopper') {
						nameDesc = '金币统计';
					} else if (name == 'userEquip') {
						nameDesc = '装备统计';
					} else if (name == 'userVip') {
						nameDesc = 'VIP统计';
					} else if (name == 'plunder') {
						nameDesc = '夺宝统计';
					} else if (name == 'zhengba') {
						nameDesc = '争霸统计';
					} else if (name == 'userTimeLoss') {
						nameDesc = '随着时间玩家流失统计';
					} else if (name == 'userNodeLoss') {
						nameDesc = '玩家节点统计';
					} else if (name == 'userOnline') {
						nameDesc = '在线人数统计';
					} else if (name == 'userLoginDraw') {
						nameDesc = '大富翁统计';
					} else if (name == 'payUserInfoStatsCollector') {
						nameDesc = '付费用户信息统计';
					} else if (name == 'reUserRemainByIpStatsCollector') {
						nameDesc = '用户留存统计(根据ip)';
					} else if (name == 'reNormalStatsDataCollector') {
						nameDesc = '常规数据统计';
					} else if (name == 'rechargeStatsCollector') {
						nameDesc = '充值统计';
						alert("该统计不通用");
					} else if (name == 'reUserMallRankStatsCollector') {
						nameDesc = '商城TOP统计';
					} else if (name == 'rePayIntervalStatsCollector') {
						nameDesc = '充值区间统计';
					} else if (name == 'reUserRemainCollector') {
						nameDesc = '留存统计';
					} else if (name == 'reUserEverydayLoginStatsCollector') {
						nameDesc = '玩家每天登入统计';
					} else if (name == 'reUserLevelStatsCollector') {
						nameDesc = '等级统计';
					} else if (name == 'reHomePageStatsCollector') {
						nameDesc = '首页统计';
					}
					
					if (searchDate == new Date().Format("yyyy-MM-dd")) {
						// alert(new Date().Format("yyyy-MM-dd"));
						alert("不能采集今天的数据");
						return false;
					}
					if (confirm("你确定要重新采集数据吗? (" + nameDesc + ")")) {
						window.location.href="reCollector?isCommit=T&reCollectorTime=" + searchDate + "&className=" + name;
					}
				}
			}
			var success = '${isCommit}';
			if (success == 'T') {
				alert("采集成功！");
			} else if (success == "NOTFOUND") {
				alert("不存在该采集功能！");
			}		
		</script>
		
		<script>
			Date.prototype.Format = function(fmt)	{ //author: meizz   
				var o = {   
    				"M+" : this.getMonth()+1,                 //月份   
    				"d+" : this.getDate(),                    //日   
    				"h+" : this.getHours(),                   //小时   
    				"m+" : this.getMinutes(),                 //分   
    				"s+" : this.getSeconds(),                 //秒   
    				"q+" : Math.floor((this.getMonth()+3)/3), //季度   
    				"S"  : this.getMilliseconds()             //毫秒   
  				};   
  				if(/(y+)/.test(fmt))   
    				fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  				for(var k in o)   
    				if(new RegExp("("+ k +")").test(fmt))   
  						fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  				return fmt;   
			}  
		</script>
	</head>
	<body>
	
	<form action="reCollector?isCommit=true" id="" name="" method="post">
	
		<s:text name="reCollectorJsp.searchTime"></s:text><s:text name="colon"></s:text>
		<input type="text" id="caijiDate" name="caijiDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" class="Wdate" style="width:100px"/>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="reCollectorJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="reCollectorJsp.name"/>
				</td>
				<td>
					<center>
						<s:text name="reCollectorJsp.caiji" />
					</center>
					
				</td>
				
			</tr>
			<tr>
				<td>
					钻石统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('userDiamond');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					金币统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('userCopper');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					装备统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('userEquip');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					VIP统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('userVip');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					夺宝统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('plunder');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					争霸统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('zhengba');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					随着时间玩家流失统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('userTimeLoss');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					玩家节点统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('userNodeLoss');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					在线人数统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('userOnline');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					大富翁统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('userLoginDraw');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					付费用户信息统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('payUserInfoStatsCollector');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					常规数据统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('reNormalStatsDataCollector');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>		
			<tr>
				<td>
					充值统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('rechargeStatsCollector');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					商城TOP10统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('reUserMallRankStatsCollector');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					留存统计(只可管理员)
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('reUserRemainCollector');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					玩家每天登入统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('reUserEverydayLoginStatsCollector');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					等级统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('reUserLevelStatsCollector');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					首页数据统计（只可以管理员使用）
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('reHomePageStatsCollector');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr>
			<%-- <tr>
				<td>
					充值区间统计
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('rePayIntervalStatsCollector');"><s:text name="reCollectorJsp.caiji" /></a>	
					</center>
				</td>
			</tr> --%>
			<%-- <tr>
				<td>
					用户留存统计统计(根据ip)
				</td>
				<td>
					<center>
						<a href="#" name="newCaiji" onclick="newCaiji('reUserRemainByIpStatsCollector');"><s:text name="reCollectorJsp.caiji" /></a>
					</center>
				</td>
			</tr> --%>
		</table>
	</form>
	</body>
	<br/>
	说明：
	<br/>
	<font color="red">1、重新采集前，请慎重(较重要数据，可以联系开发人员先导出数据，以免丢失数据。)</font>
	<br/>
	2、选择要采集的日期，点击采集
</html>