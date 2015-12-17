<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:text name="updateTMallTreasureConstantJsp.title"></s:text></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
  </head>
  <script type="text/javascript" src="../../js/ajax.js"></script>
  <body>
   	<form action="updateTMallTreasureConstant?isCommit=T" method="post">
	    <table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
	    	<tr class="border">
					<td class="td_title" colspan="20" align="center">
						<center>
							<s:text name="updateTMallTreasureConstantJsp.title"></s:text>
						</center>
					</td>
				</tr>
					<tr>
						<td>
							<s:text name="mallTreasureConstant.treasureId"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							${tmallTreasureConstant.id.treasureId}
							<input type="hidden" name="id.treasureId" value="${tmallTreasureConstant.id.treasureId}" class="maxLife" size="20" maxlength="20" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td>
							<s:text name="mallTreasureConstant.treasureName"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							${tmallTreasureConstant.treasureName}
						</td>
					</tr>
					<tr>
						<td>
							<s:text name="mallTreasureConstant.category"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<s:text name="%{'mallTreasureConstant.id.category_'+tmallTreasureConstant.id.category}"></s:text>
							<input type="hidden" name="id.category" value="${tmallTreasureConstant.id.category}" class="maxLife" size="20" maxlength="20" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td>
							<s:text name="mallTreasureConstant.type"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<select name="type" class="select">
								<s:generator separator="," val="%{getText('mallTreasureConstant.type_value')}">
									<s:iterator var="str">
										<option value="${str}" <s:if test="tMallTreasureConstant.type == #str">selected=selected</s:if>>
											<s:text name="%{'mallTreasureConstant.type_'+#str}"></s:text>
										</option>
									</s:iterator>
								</s:generator>							
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<s:text name="mallTreasureConstant.originalPrice"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<input type="text" name="originalPrice" value="${tmallTreasureConstant.originalPrice}" class="maxLife" size="10" maxlength="11" />
						</td>
					</tr>
					<tr>
						<td>
							<s:text name="mallTreasureConstant.price"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<input type="text" name="price" value="${tmallTreasureConstant.price}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
						</td>
					</tr>
					<tr>
						<td>
							<s:text name="mallTreasureConstant.canArenaBuy"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<select name="canArenaBuy" class="select">
								<s:generator separator="," val="%{getText('mallTreasureConstant.canArenaBuy_value')}">
									<s:iterator var="str">
										<option value="${str}" <s:if test="tmallTreasureConstant.canArenaBuy == #str">selected=selected</s:if>>
											<s:text name="%{'mallTreasureConstant.canArenaBuy_'+#str}"></s:text>
										</option>
									</s:iterator>
								</s:generator>	
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<s:text name="mallTreasureConstant.needArenaScore"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<input type="text" name="needArenaScore" value="${tmallTreasureConstant.needArenaScore}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
						</td>
						</tr>
					<tr>
						<td>
							<s:text name="mallTreasureConstant.lastTime"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<input type="text" name="lastTime" value="${tmallTreasureConstant.lastTime}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
						</td>
					</tr>
					<tr>
						<td>
							<s:text name="mallTreasureConstant.state"></s:text><s:text name="colon"></s:text>
						</td>
						<td>
							<select name="state" class="select">
								<s:generator separator="," val="%{getText('mallTreasureConstant.state_value')}">
									<s:iterator var="str">
										<option value="${str}" <s:if test="tmallTreasureConstant.state == #str">selected=selected</s:if>>
											<s:text name="%{'mallTreasureConstant.state_'+#str}"></s:text>
										</option>
									</s:iterator>
								</s:generator>							
							</select>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<input type="submit" value="<s:text name="submit"></s:text>" class="button" />
							<input type="reset" value="<s:text name="reset"></s:text>" class="button" />
						</td>					
					</tr>
	    </table>
    </form>
  </body>
</html>
