<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>留存统计</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/stats_WdatePicker.js"></script>
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
	</script>
	<body>
		<form action="userRemainStatsByIpList" method="post" onsubmit="return check()">
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" onfocus="WdatePicker({onpicked:function(){endDate.focus();}})" class="Wdate" id="startDate" name="startDate" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" style="width:100px"/>
			<s:text name="timeTo"></s:text>
			<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" class="Wdate" id="endDate" name="endDate" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" style="width:100px"/>
			
			<s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="35" align="center">
					<center>
						留存统计(根据ip)
					</center>
				</td>
			</tr>
			<tr>
				<td>
					日期
				</td>
				<td>
					用户总量
				</td>
				<td>
					新增创建角色
				</td>
				<td>
					新增注册
				</td>
				<td>
					日活跃
				</td>
				<td>
					次留
				</td>
				<td>
					2留
				</td>
				<td>
					3留
				</td>
				<td>
					4留
				</td>
				<td>
					5留
				</td>
				<td>
					6留
				</td>
				<td>
					7留
				</td>
				<td>
					8留
				</td>
				<td>
					9留
				</td>
				<td>
					10留
				</td>
				<td>
					11留
				</td>
				<td>
					12留
				</td>
				<td>
					13留
				</td>
				<td>
					14留
				</td>
				<td>
					15留
				</td>
				<td>
					16留
				</td>
				<td>
					17留
				</td>
				<td>
					18留
				</td>
				<td>
					19留
				</td>
				<td>
					20留
				</td>
				<td>
					21留
				</td>
				<td>
					22留
				</td>
				<td>
					23留
				</td>
				<td>
					24留
				</td>
				<td>
					25留
				</td>
				<td>
					26留
				</td>
				<td>
					27留
				</td>
				<td>
					28留
				</td>
				<td>
					29留
				</td>
				
				<td>
					30留
				</td>
			</tr>
			<s:iterator var="stats" value="statsList">
				<tr>
					<td>
						<s:text name="format.date_ymd">
							<s:param value="#stats.time"></s:param>
						</s:text>
					</td>
					<td>
						${stats.userTotal}
					</td>
					<td>
						${stats.newCreated}
					</td>
					<td>
						${stats.newReg}
					</td>
					<td>
						${stats.dayActive}
					</td>
					<td>
						<s:if test="#stats.oneDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.oneDayRemain}" pattern="#.##%" />
							
								<s:if test="#stats.oneDayRemainNum!=null">
									(${stats.oneDayRemainNum}
								</s:if>
								
								<s:if test="#stats.oneDayRegNum!=null">
									/${stats.oneDayRegNum})
								</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.twoDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.twoDayRemain}" pattern="#.##%" />
							
							<s:if test="#stats.twoDayRemainNum!=null">
								(${stats.twoDayRemainNum}
							</s:if>
								
							<s:if test="#stats.twoDayRegNum!=null">
								/${stats.twoDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.threeDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.threeDayRemain}" pattern="#.##%" />
							<s:if test="#stats.threeDayRemainNum!=null">
								(${stats.threeDayRemainNum}
							</s:if>
								
							<s:if test="#stats.threeDayRegNum!=null">
								/${stats.threeDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.fourDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.fourDayRemain}" pattern="#.##%" />
							<s:if test="#stats.fourDayRemainNum!=null">
								(${stats.fourDayRemainNum}
							</s:if>
								
							<s:if test="#stats.fourDayRegNum!=null">
								/${stats.fourDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.fiveDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.fiveDayRemain}" pattern="#.##%" />
							<s:if test="#stats.fiveDayRemainNum!=null">
								(${stats.fiveDayRemainNum}
							</s:if>
								
							<s:if test="#stats.fiveDayRegNum!=null">
								/${stats.fiveDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.sixDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.sixDayRemain}" pattern="#.##%" />
							<s:if test="#stats.sixDayRemainNum!=null">
								(${stats.sixDayRemainNum}
							</s:if>
								
							<s:if test="#stats.sixDayRegNum!=null">
								/${stats.sixDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.sevenDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.sevenDayRemain}" pattern="#.##%" />
							<s:if test="#stats.sevenDayRemainNum!=null">
								(${stats.sevenDayRemainNum}
							</s:if>
								
							<s:if test="#stats.sevenDayRegNum!=null">
								/${stats.sevenDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.eightDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.eightDayRemain}" pattern="#.##%" />
							<s:if test="#stats.eightDayRemainNum!=null">
								(${stats.eightDayRemainNum}
							</s:if>
								
							<s:if test="#stats.eightDayRegNum!=null">
								/${stats.eightDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.neightDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.neightDayRemain}" pattern="#.##%" />
							<s:if test="#stats.neightDayRemainNum!=null">
								(${stats.neightDayRemainNum}
							</s:if>
								
							<s:if test="#stats.neightDayRegNum!=null">
								/${stats.neightDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.tenDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.tenDayRemain}" pattern="#.##%" />
							<s:if test="#stats.tenDayRemainNum!=null">
								(${stats.tenDayRemainNum}
							</s:if>
								
							<s:if test="#stats.tenDayRegNum!=null">
								/${stats.tenDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.elevenDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.elevenDayRemain}" pattern="#.##%" />
							<s:if test="#stats.elevenDayRemainNum!=null">
								(${stats.elevenDayRemainNum}
							</s:if>
								
							<s:if test="#stats.elevenDayRegNum!=null">
								/${stats.elevenDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.twelfDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.twelfDayRemain}" pattern="#.##%" />
							<s:if test="#stats.twelfDayRemainNum!=null">
								(${stats.twelfDayRemainNum}
							</s:if>
								
							<s:if test="#stats.twelfDayRegNum!=null">
								/${stats.twelfDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.thirteenDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.thirteenDayRemain}" pattern="#.##%" />
							<s:if test="#stats.thirteenDayRemainNum!=null">
								(${stats.thirteenDayRemainNum}
							</s:if>
								
							<s:if test="#stats.thirteenDayRegNum!=null">
								/${stats.thirteenDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.fourteenDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.fourteenDayRemain}" pattern="#.##%" />
							<s:if test="#stats.fourteenDayRemainNum!=null">
								(${stats.fourteenDayRemainNum}
							</s:if>
								
							<s:if test="#stats.fourteenDayRegNum!=null">
								/${stats.fourteenDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.fifteenDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.fifteenDayRemain}" pattern="#.##%" />
							<s:if test="#stats.fifteenDayRemainNum!=null">
								(${stats.fifteenDayRemainNum}
							</s:if>
								
							<s:if test="#stats.fifteenDayRegNum!=null">
								/${stats.fifteenDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.sixteenDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.sixteenDayRemain}" pattern="#.##%" />
							<s:if test="#stats.sixteenDayRemainNum!=null">
								(${stats.sixteenDayRemainNum}
							</s:if>
								
							<s:if test="#stats.sixteenDayRegNum!=null">
								/${stats.sixteenDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.seventeenDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.seventeenDayRemain}" pattern="#.##%" />
							<s:if test="#stats.seventeenDayRemainNum!=null">
								(${stats.seventeenDayRemainNum}
							</s:if>
								
							<s:if test="#stats.seventeenDayRegNum!=null">
								/${stats.seventeenDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.eightteenDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.eightteenDayRemain}" pattern="#.##%" />
							<s:if test="#stats.eightteenDayRemainNum!=null">
								(${stats.eightteenDayRemainNum}
							</s:if>
								
							<s:if test="#stats.eightteenDayRegNum!=null">
								/${stats.eightteenDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.neightteenDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.neightteenDayRemain}" pattern="#.##%" />
							<s:if test="#stats.neightteenDayRemainNum!=null">
								(${stats.neightteenDayRemainNum}
							</s:if>
								
							<s:if test="#stats.neightteenDayRegNum!=null">
								/${stats.neightteenDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.twentieDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.twentieDayRemain}" pattern="#.##%" />
							<s:if test="#stats.twentieDayRemainNum!=null">
								(${stats.twentieDayRemainNum}
							</s:if>
								
							<s:if test="#stats.twentieDayRegNum!=null">
								/${stats.twentieDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.twentieoneDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.twentieoneDayRemain}" pattern="#.##%" />
							<s:if test="#stats.twentieoneDayRemainNum!=null">
								(${stats.twentieoneDayRemainNum}
							</s:if>
								
							<s:if test="#stats.twentieoneDayRegNum!=null">
								/${stats.twentieoneDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.twentietwoDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.twentietwoDayRemain}" pattern="#.##%" />
							<s:if test="#stats.twentietwoDayRemainNum!=null">
								(${stats.twentietwoDayRemainNum}
							</s:if>
								
							<s:if test="#stats.twentietwoDayRegNum!=null">
								/${stats.twentietwoDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.twentiethreeDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.twentiethreeDayRemain}" pattern="#.##%" />
							<s:if test="#stats.twentiethreeDayRemainNum!=null">
								(${stats.twentiethreeDayRemainNum}
							</s:if>
								
							<s:if test="#stats.twentiethreeDayRegNum!=null">
								/${stats.twentiethreeDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.twentiefourDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.twentiefourDayRemain}" pattern="#.##%" />
							<s:if test="#stats.twentiefourDayRemainNum!=null">
								(${stats.twentiefourDayRemainNum}
							</s:if>
								
							<s:if test="#stats.twentiefourDayRegNum!=null">
								/${stats.twentiefourDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.twentiefiveDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.twentiefiveDayRemain}" pattern="#.##%" />
							<s:if test="#stats.twentiefiveDayRemainNum!=null">
								(${stats.twentiefiveDayRemainNum}
							</s:if>
								
							<s:if test="#stats.twentiefiveDayRegNum!=null">
								/${stats.twentiefiveDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.twentiesixDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.twentiesixDayRemain}" pattern="#.##%" />
							<s:if test="#stats.twentiesixDayRemainNum!=null">
								(${stats.twentiesixDayRemainNum}
							</s:if>
								
							<s:if test="#stats.twentiesixDayRegNum!=null">
								/${stats.twentiesixDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.twentiesevenDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.twentiesevenDayRemain}" pattern="#.##%" />
							<s:if test="#stats.twentiesevenDayRemainNum!=null">
								(${stats.twentiesevenDayRemainNum}
							</s:if>
								
							<s:if test="#stats.twentiesevenDayRegNum!=null">
								/${stats.twentiesevenDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.twentieeightDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.twentieeightDayRemain}" pattern="#.##%" />
							<s:if test="#stats.twentieeightDayRemainNum!=null">
								(${stats.twentieeightDayRemainNum}
							</s:if>
								
							<s:if test="#stats.twentieeightDayRegNum!=null">
								/${stats.twentieeightDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.twentienightDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.twentienightDayRemain}" pattern="#.##%" />
							<s:if test="#stats.twentieneightDayRemainNum!=null">
								(${stats.twentieneightDayRemainNum}
							</s:if>
								
							<s:if test="#stats.twentieneightDayRegNum!=null">
								/${stats.twentieneightDayRegNum})
							</s:if>
						</s:if>
					</td>
					<td>
						<s:if test="#stats.thirtyDayRemain!=null">
							<fmt:formatNumber type="percent" value="${stats.thirtyDayRemain}" pattern="#.##%" />
							<s:if test="#stats.thirtyDayRemainNum!=null">
								(${stats.thirtyDayRemainNum}
							</s:if>
								
							<s:if test="#stats.thirtyDayRegNum!=null">
								/${stats.thirtyDayRegNum})
							</s:if>
						</s:if>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="35">
					<aldtags:pageTag datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}" para1="pageSize" value1="${pageSize}"/>
				</td>
			</tr>
		</table>
		<br>
		<br>
		<s:text name="userRemainByIpStatsListJsp.note"></s:text><br>
		<s:generator separator="," val="%{getText('userRemainByIpStatsListJsp.note_value')}">
			<s:iterator var="str">
				<s:text name="%{'userRemainByIpStatsListJsp.note'+#str}"></s:text><br>
			</s:iterator>
		</s:generator>
	</body>
</html>