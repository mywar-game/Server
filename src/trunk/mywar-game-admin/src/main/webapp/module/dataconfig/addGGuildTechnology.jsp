<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加军团科技常量</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<body>
		&nbsp;
		<form action="addGGuildTechnology?isCommit=T" method="post">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="5">
						添加军团科技常量 &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
								<tr>
					<td>
						军团科技id
					</td>
					<td>
						<input type="text" name="guildTechnologyId" value="${guildTechnologyConstant.guildTechnologyId}" class="maxLife" size="10" maxlength="11" readonly="readonly" />
						自动生成
					</td>
					<td>
						科技id
					</td>
					<td>
						<input type="text" name="technologyId" value="${guildTechnologyConstant.technologyId}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						军团科技名称
					</td>
					<td colspan="3">
						<input type="text" name="technologyName" value="${guildTechnologyConstant.technologyName}" class="maxLife" size="50" maxlength="50" />
					</td>
				</tr>
				<tr>
					<td>
						科技等级
					</td>
					<td>
						<input type="text" name="technologyLevel" value="${guildTechnologyConstant.technologyLevel}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						增加数值
					</td>
					<td>
						<input type="text" name="vaue" value="${guildTechnologyConstant.vaue}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						数值类型
					</td>
					<td>
						<select name="valueType" class="select">
							<s:generator separator="," val="%{getText('guildTechnologyConstant.valueType_value')}">
								<s:iterator var="str">
									<option value="${str}" <s:if test="guildTechnologyConstant.valueType == #str">selected=selected</s:if>>
										<s:text name="%{'guildTechnologyConstant.valueType_'+#str}"></s:text>
									</option>
								</s:iterator>
							</s:generator>
						</select>
					</td>
					<td>
						升级消耗军团粮食
					</td>
					<td>
						<input type="text" name="costGuildGrain" value="${guildTechnologyConstant.costGuildGrain}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<td>
						升级消耗军团矿石
					</td>
					<td>
						<input type="text" name="costGuildMineral" value="${guildTechnologyConstant.costGuildMineral}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						升级消耗军团金钱
					</td>
					<td>
						<input type="text" name="costGuildMoney" value="${guildTechnologyConstant.costGuildMoney}" class="maxLife" size="10" maxlength="11" onblur="value=value.replace(/[^\d]/g,'')" />
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