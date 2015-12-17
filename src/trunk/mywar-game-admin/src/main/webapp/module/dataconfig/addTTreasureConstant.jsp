<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="addTTreasureConstantJsp.title"></s:text></title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<body>
		&nbsp;
		<form action="addTTreasureConstant?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						<s:text name="addTTreasureConstantJsp.title"></s:text> &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="treasureConstant.treasureId"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="treasureId" value="${treasureConstant.treasureId}" class="maxLife" size="11" maxlength="11" readonly="readonly"/>
						<s:text name="autogeneration"></s:text>
					</td>
					<td>
						<s:text name="treasureConstant.treasureName"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="treasureName" value="${treasureConstant.treasureName}" class="maxLife" size="64" maxlength="64" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="treasureConstant.treasureDesc"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<input type="text" name="treasureDesc" value="${treasureConstant.treasureDesc}" class="maxLife" size="128" maxlength="128" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="treasureConstant.treasureType"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="treasureType" class="select">
							<s:generator separator="," val="%{getText('treasureType_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="treasureConstant.treasureType == #str">selected=selected</s:if>>
										<s:text name="%{'treasureType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>						
						</select>
					</td>
					<td>
						<s:text name="treasureConstant.salePrice"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="salePrice" value="${treasureConstant.salePrice}" class="maxLife" size="11" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="treasureConstant.canBuy"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="canBuy" class="select">
							<s:generator separator="," val="%{getText('canBuy_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="treasureConstant.canBuy == #str">selected=selected</s:if>>
										<s:text name="%{'canBuy_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>						
						</select>
					</td>
					<td>
						<s:text name="treasureConstant.price"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="price" value="${treasureConstant.price}" class="maxLife" size="11" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="treasureConstant.isOverlay"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="isOverlay" class="select">
							<s:generator separator="," val="%{getText('isOverlay_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="treasureConstant.isOverlay == #str">selected=selected</s:if>>
										<s:text name="%{'isOverlay_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>						
						</select>
					</td>
					<td>
						<s:text name="treasureConstant.overlayNumMax"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="overlayNumMax" value="${treasureConstant.overlayNumMax}" class="maxLife" size="4" maxlength="4" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="treasureConstant.medalBuy"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<input type="text" name="medalBuy" value="${treasureConstant.medalBuy}" class="maxLife" size="4" maxlength="4"  onblur="value=value.replace(/[^\d]/g,'')"/>
					</td>
					<td>
						<s:text name="treasureConstant.isBinding"></s:text><s:text name="colon"></s:text>
					</td>
					<td>
						<select name="isBinding" class="select">
							<s:generator separator="," val="%{getText('isBinding_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="treasureConstant.isBinding == #str">selected=selected</s:if>>
										<s:text name="%{'isBinding_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>	
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="treasureConstant.value"></s:text><s:text name="colon"></s:text>
					</td>
					<td colspan="3">
						<input type="text" name="value" value="${treasureConstant.value}" class="maxLife" size="30" maxlength="32"/>
					</td>
				</tr>
				
				
				<tr>
					<td colspan="5" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>