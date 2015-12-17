<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userQuestionnaireStatsListJsp.title"></s:text></title>
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
	<script type="text/javascript"> 
	</script>
	<body>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="userQuestionnaireStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					问题
				</td>
				<td colspan="6">
					答案人数（回答该问题所占百分比）（具体选项）
				</td>
			</tr>
			<s:iterator var="questionnaireConstant" value="questionnaireConstantList" status="sta">
				<s:set var="optionList" value="allOptionList[#sta.index]"></s:set>
				<s:set var="choiceList" value="statsMap[#questionnaireConstant.questionId]"></s:set>
				<s:set var="amount" value="0"></s:set>
				<s:iterator value="choiceList">
					<s:set var="amount" value="#amount+value"></s:set>
				</s:iterator>
				<tr>
					<td>
						${sta.index+1}. ${questionnaireConstant.question}
		            </td>
		            <s:iterator var="one" value="optionList" status="sta">
						<s:set var="choiceNum" value="0"></s:set>
		            	<s:iterator value="choiceList">
							<s:if test="key == #one.optionId">
								<s:set var="choiceNum" value="value"></s:set>
							</s:if>
						</s:iterator>
			            
			            <td <s:if test="#sta.last">colspan=${7-fn:length(optionList)}</s:if>>
			            	${choiceNum}人
			            	<s:if test="#amount == 0">0%</s:if>
			            	<s:else>
				            	(<fmt:formatNumber type="percent" value="${choiceNum/amount}" pattern="#.#%" />)
			            	</s:else>
			            	(${one.option})
						</td>
		            </s:iterator>
				</tr>
			</s:iterator>
			
		</table>
	</body>
</html>