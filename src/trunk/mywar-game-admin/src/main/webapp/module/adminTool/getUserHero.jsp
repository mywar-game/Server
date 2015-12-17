<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
		<title><s:text name="getUserHeroJsp.title"><s:param>${searchName}</s:param></s:text></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript">
	</script>
	<body>
		<form action="getUserHero?isCommit=T" method="post">
			<table>
				<tr class="border">
					<td class="td_title" colspan="100" align="center">
						<center>
							<s:text name="getUserHeroJsp.title"><s:param>${lodoId}</s:param></s:text>
						</center>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<s:text name="log.lodoSearch"></s:text><s:text name="colon"></s:text><input type="text" id="lodoId" name="lodoId">
					</td>
					<td colspan="100">
						<input type="submit" value="<s:text name="search"></s:text>" class="button">
					</td>
				</tr>
		
				<c:if test="${userHeroSomeInfoList != null}">
					<tr>
						<td>
							<s:text name="userHero.userId"></s:text>
						</td>
						<td>
							<s:text name="userHero.userHeroId"></s:text>
						</td>
						<td>
							<s:text name="userHero.heroId"></s:text>
						</td>
						<td>
							<s:text name="userHero.systemHeroId"></s:text>
						</td>
						<td>
							<s:text name="userHero.imgId"></s:text>
						</td>
						<td>
							<s:text name="userHero.cardId"></s:text>
						</td>
						<td>
							<s:text name="userHero.name"></s:text>
						</td>
						<td>
							<s:text name="userHero.pos"></s:text>
						</td>
						<td>
							<s:text name="userHero.level"></s:text>
						</td>
						<td>
							<s:text name="userHero.exp"></s:text>
						</td>
						<td>
							<s:text name="userHero.dexp"></s:text>
						</td>
						<td>
							<s:text name="userHero.career"></s:text>
						</td>
						<td>
							<s:text name="userHero.life"></s:text>
						</td>
						<td>
							<s:text name="userHero.physicalAttack"></s:text>
						</td>
						<td>
							<s:text name="userHero.physicalDefense"></s:text>
						</td>
						<td>
							<s:text name="userHero.plan"></s:text>
						</td>
						<td>
							<s:text name="userHero.normalPlan"></s:text>
						</td>
						<td>
							<s:text name="userHero.skill1"></s:text>
						</td>
						<td>
							<s:text name="userHero.skill2"></s:text>
						</td>
						<td>
							<s:text name="userHero.skill3"></s:text>
						</td>
						<td>
							<s:text name="userHero.skill4"></s:text>
						</td>
						<td>
							<s:text name="userHero.parry"></s:text>
						</td>
						<td>
							<s:text name="userHero.crit"></s:text>
						</td>
						<td>
							<s:text name="userHero.dodge"></s:text>
						</td>
						<td>
							<s:text name="userHero.hit"></s:text>
						</td>
						<td>
							<s:text name="userHero.price"></s:text>
						</td>
					</tr>
					<s:iterator var="userHeroSomeInfo" value="userHeroSomeInfoList" status="sta">
						<tr>
							<td>
								${userHeroSomeInfo.userId}
							</td>
							<td>
								${userHeroSomeInfo.userHeroId}
							</td>
							<td>
								${userHeroSomeInfo.heroId}
							</td>
							<td>
								${userHeroSomeInfo.systemHeroId}
							</td>
							<td>
								${userHeroSomeInfo.imgId}
							</td>
							<td>
								${userHeroSomeInfo.cardId}
							</td>
							<td>
								${userHeroSomeInfo.name}
							</td>
							<td>
								${userHeroSomeInfo.pos}
							</td>
							<td>
								${userHeroSomeInfo.level}
							</td>
							<td>
								${userHeroSomeInfo.exp}
							</td>
							<td>
								${userHeroSomeInfo.dexp}
							</td>
							<td>
								${userHeroSomeInfo.career}
							</td>
							<td>
								${userHeroSomeInfo.life}
							</td>
							<td>
								${userHeroSomeInfo.physicalAttack}
							</td>
							<td>
								${userHeroSomeInfo.physicalDefense}
							</td>
							<td>
								${userHeroSomeInfo.plan}
							</td>
							<td>
								${userHeroSomeInfo.normalPlan}
							</td>
							<td>
								${userHeroSomeInfo.skill1}
							</td>
							<td>
								${userHeroSomeInfo.skill2}
							</td>
							<td>
								${userHeroSomeInfo.skill3}
							</td>
							<td>
								${userHeroSomeInfo.skill4}
							</td>
							<td>
								${userHeroSomeInfo.parry}
							</td>
							<td>
								${userHeroSomeInfo.crit}
							</td>
							<td>
								${userHeroSomeInfo.dodge}
							</td>
							<td>
								${userHeroSomeInfo.hit}
							</td>
							<td>
								${userHeroSomeInfo.price}
							</td>
						</tr>
					</s:iterator>
				</c:if>
			</table>
		</form>
	</body>
	<script src="../../js/jquery/jquery.min.js"></script>
	<script src="../../js/jquery/jquery.validate.js"></script>
	<script type="text/javascript" >
		$(document).ready(function(){
			$("form").validate({
				rules:{
					searchName:{
						required: true,
					}
				}
			})
		});
	</script>
</html>