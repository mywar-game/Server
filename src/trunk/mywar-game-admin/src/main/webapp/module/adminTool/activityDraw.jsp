<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/tab.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/jquery/jquery.min.js"></script>
		<script type="text/javascript">
			
			function change(action, activityId, n){
				document.getElementById("mainFrame").src = action + "?activityId=" + activityId;
 				var tli=document.getElementById("menu1").getElementsByTagName("li");
 				for(i=0;i<tli.length;i++){
  					tli[i].className=i==n?"hover":"";				
				}
			}
		</script>
		
	</head>
	<body>	
		<div id="tabs1">
 			<div class="menu1box">
				<ul id="menu1">
					<li class="hover"><a href="#" onclick="change('activityDrawPosList', ${activityId}, 0)" ><s:text name="activityDraw.drawPos"></s:text></a></li>
					<li ><a href="#" onclick="change('activityDrawConfigList', ${activityId}, 1)" ><s:text name="activityDraw.drawConfig"></s:text></a></li>
					<li ><a href="#" onclick="change('activityDrawGoodsList', ${activityId}, 2)" ><s:text name="activityDraw.drawGoods"></s:text></a></li>
					<li ><a href="#" onclick="change('activityDrawLevelGoodsList', ${activityId}, 3)" ><s:text name="activityDraw.drawLevelGoods"></s:text></a></li>
				</ul>
			</div>
			<div class="main1box">
				<iframe id="mainFrame" width="1315" height="605" frameborder="0" scrolling="auto" src="activityDrawPosList?activityId=${activityId}"></iframe>
			</div>
		</div>
	</body>	
</html>


