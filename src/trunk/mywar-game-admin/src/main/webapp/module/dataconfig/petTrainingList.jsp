<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>驯兽术常量列表</title>

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
			ajaxobj.url="reflashConstantCache?cacheType=<s:property value='@com.dataconfig.constant.ReflashCacheConstant@REFLASH_TYPE_PET_TRAINING'/>&number="+Math.random();
			ajaxobj.callback=function(){
		    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
		    	//alert(responseMsg.erroCodeNum);
		    	if (responseMsg.erroCodeNum == 0) {
					alert('刷新驯兽术常量缓存成功!');
				} else {
					alert(responseMsg.erroDescrip+'刷新驯兽术常量缓存失败!');
				}
		    }
			ajaxobj.send();
		}
	</script>
	<body>
		<input type="button" value="刷新驯兽术常量缓存" class="button" onclick="reflashconstantcache()" />
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="20" align="center">
					<center>
						驯兽术常量列表
					</center>
				</td>
			</tr>
			<tr>
				<td>
					驯兽术升级编号
				</td>
				<td>
					驯兽术等级
				</td>
				<td>
					驯兽术星级
				</td>
				<td>
					所需经验
				</td>
				<td>
					普通教学小暴击概率
				</td>
				<td>
					高级教学
				</td>
				<td>
					小暴击比率
				</td>
				<td>
					普通教学大暴击概率
				</td>
				<td>
					高级教学大暴击概率
				</td>
				<td>
					大暴击比率
				</td>
				<td>
					低攻击加成
				</td>
				<td>
					高攻击加成
				</td>
				<td>
					攻击速度加成
				</td>
				<td>
					暴击率加成
				</td>
				<td>
					命中加成
				</td>
			</tr>
			<s:iterator var="petTraining" value="petTrainingList">
				<tr>
					<td>
						${petTraining.petTrainingId}
					</td>
					<td>
						${petTraining.petTrainingLevel}
					</td>
					<td>
						${petTraining.petTrainingStar}
					</td>
					<td>
						${petTraining.needExp}
					</td>
					<td>
						${petTraining.commonSmallCritPercent}
					</td>
					<td>
						${petTraining.highSmallCritPercent}
					</td>
					<td>
						${petTraining.smallCritMax}
					</td>
					<td>
						${petTraining.commonBigCritPercent}
					</td>
					<td>
						${petTraining.highBigCritPercent}
					</td>
					<td>
						${petTraining.bigCritMax}
					</td>
					<td>
						${petTraining.lowAttackAdd}
					</td>
					<td>
						${petTraining.highAttackAdd}
					</td>
					<td>
						${petTraining.attackSpeedAdd}
					</td>
					<td>
						${petTraining.cirtRateAdd}
					</td>
					<td>
						${petTraining.hitRateAdd}
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="20">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
	</body>
</html>