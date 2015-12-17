<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>技能效果常量修改</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
		function isSubmit(){
			if(window.confirm("<s:text name='updateConfirm'></s:text>"))
				return true;
			return false;
		}
	</script>
 	<body>
		&nbsp;

		<form action="updateSEffectConstant?isCommit=T" method="post" onsubmit="return isSubmit()">

			<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
				<tr class="border">
					<td class="td_title" colspan="4">
						技能效果常量修改 &nbsp;
						<font color='red'>${erroDescrip}</font>
					</td>
				</tr>
				<tr>
					<td>
						技能效果常量编号：
					</td>
					<td>
						<input type="text" name="effectId" value="${seffectConstant.effectId}" class="maxLife" size="20" maxlength="20" readonly="readonly"/>
						<font color="green">（不可修改）</font>
					</td>
					<td>
						效果描述：
					</td>
					<td>
						<input type="text" name="effectDesc" value="${seffectConstant.effectDesc}" class="maxLife" size="60" maxlength="64" />
					</td>
				</tr>
				<tr>
					<td>
						是否用公式：
					</td>
					<td>
						<input type="radio" name="useFormula" id="useFormula1" value="1" <c:if test="${seffectConstant.useFormula == 1}">checked="checked"</c:if>/><label for="useFormula1">用</label>
						<input type="radio" name="useFormula" id="useFormula2" value="2" <c:if test="${seffectConstant.useFormula == 2}">checked="checked"</c:if>/><label for="useFormula2">不用</label>
					</td>
					<td>
						数值类型
					</td>
					<td>
						<select name="valueType">
							<option><s:text name="pleaseSelect"></s:text></option>
							<option value="1" <c:if test="${seffectConstant.valueType == 1}">selected="selected"</c:if> >固定值</option>
							<option value="2" <c:if test="${seffectConstant.valueType == 2}">selected="selected"</c:if> >百分比</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						数值：
					</td>
					<td>
						<input type="text" name="value" value="${seffectConstant.value}" class="maxLife" size="20" maxlength="20" />
					</td>
					<td>
						影响类型：
					</td>
					<td>
						<select name="effectType">
							<option><s:text name="pleaseSelect"></s:text></option>
							<option value="1" <c:if test="${seffectConstant.effectType == 1}">selected="selected"</c:if> >受到的普通伤害</option>
							<option value="2" <c:if test="${seffectConstant.effectType == 2}">selected="selected"</c:if> >受到的技能伤害</option>
							<option value="3" <c:if test="${seffectConstant.effectType == 3}">selected="selected"</c:if> >攻击力</option>
							<option value="4" <c:if test="${seffectConstant.effectType == 4}">selected="selected"</c:if> >移动速度</option>
							<option value="5" <c:if test="${seffectConstant.effectType == 5}">selected="selected"</c:if> >无法普通攻击</option>
							<option value="6" <c:if test="${seffectConstant.effectType == 6}">selected="selected"</c:if> >无法技能攻击</option>
							<option value="7" <c:if test="${seffectConstant.effectType == 7}">selected="selected"</c:if> >攻击速度</option>
							<option value="8" <c:if test="${seffectConstant.effectType == 8}">selected="selected"</c:if> >攻击力伤害(根据攻击力来计算伤害的)</option>
							<option value="9" <c:if test="${seffectConstant.effectType == 9}">selected="selected"</c:if> >武器伤害(根据武器来计算伤害的)</option>
							<option value="10" <c:if test="${seffectConstant.effectType == 10}">selected="selected"</c:if> >真实伤害(直接根据技能伤害来计算伤害的)</option>
							<option value="11" <c:if test="${seffectConstant.effectType == 11}">selected="selected"</c:if> >不能移动</option>
							<option value="12" <c:if test="${seffectConstant.effectType == 12}">selected="selected"</c:if> >命中率</option>
							<option value="13" <c:if test="${seffectConstant.effectType == 13}">selected="selected"</c:if> >生命值(基数是以英雄最大生命值来计算)</option>
							<option value="14" <c:if test="${seffectConstant.effectType == 14}">selected="selected"</c:if> >治疗量(基数是以攻击力来计算)</option>
							<option value="15" <c:if test="${seffectConstant.effectType == 15}">selected="selected"</c:if> >护甲</option>
							<option value="16" <c:if test="${seffectConstant.effectType == 16}">selected="selected"</c:if> >最大生命伤害(以最大生命值为基数来计算伤害)</option>
							<option value="17" <c:if test="${seffectConstant.effectType == 17}">selected="selected"</c:if> >技能冷却时间</option>
							<option value="18" <c:if test="${seffectConstant.effectType == 18}">selected="selected"</c:if> >护甲伤害(以护甲为基数来计算伤害)</option>
							<option value="19" <c:if test="${seffectConstant.effectType == 19}">selected="selected"</c:if> >减少或增加生命固定值</option>
							<option value="20" <c:if test="${seffectConstant.effectType == 20}">selected="selected"</c:if> >减少或增加攻击固定值</option>
							<option value="21" <c:if test="${seffectConstant.effectType == 21}">selected="selected"</c:if> >减少或增加护甲固定值</option>
							<option value="22" <c:if test="${seffectConstant.effectType == 22}">selected="selected"</c:if> >减少或增加移动速度固定值</option>
							<option value="23" <c:if test="${seffectConstant.effectType == 23}">selected="selected"</c:if> >暴击率</option>
							<option value="24" <c:if test="${seffectConstant.effectType == 24}">selected="selected"</c:if> >技能抵抗</option>
							<option value="25" <c:if test="${seffectConstant.effectType == 25}">selected="selected"</c:if> >战斗获取金钱</option>
							<option value="26" <c:if test="${seffectConstant.effectType == 26}">selected="selected"</c:if> >闪避</option>
							<option value="27" <c:if test="${seffectConstant.effectType == 27}">selected="selected"</c:if> >受到的治疗效果</option>
							<option value="28" <c:if test="${seffectConstant.effectType == 28}">selected="selected"</c:if> >技能攻击力</option>
							<option value="29" <c:if test="${seffectConstant.effectType == 29}">selected="selected"</c:if> >攻击范围</option>
							<option value="30" <c:if test="${seffectConstant.effectType == 30}">selected="selected"</c:if> >治疗技能效果</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						持续类型
					</td>
					<td>
						<select name="continueType">
							<option><s:text name="pleaseSelect"></s:text></option>
							<option value="1" <c:if test="${seffectConstant.continueType == 1}">selected="selected"</c:if> >瞬间</option>
							<option value="2" <c:if test="${seffectConstant.continueType == 2}">selected="selected"</c:if> >持续</option>
							<option value="3" <c:if test="${seffectConstant.continueType == 3}">selected="selected"</c:if> >周期</option>
						</select>
					</td>
					<td>
						周期：
					</td>
					<td>
						<input type="text" name="period" value="${seffectConstant.period}" class="maxLife" size="20" maxlength="20" />
					</td>
				</tr>
				<tr>
					<td>
						持续时间：
					</td>
					<td>
						<input type="text" name="continueTime" value="${seffectConstant.continueTime}" class="maxLife" size="20" maxlength="20" />
					</td>
					<td>
						作用对象
					</td>
					<td>
						<select name="target">
							<option><s:text name="pleaseSelect"></s:text></option>
							<option value="1" <c:if test="${seffectConstant.target == 1}">selected="selected"</c:if> > 与技能一致 </option>
							<option value="2" <c:if test="${seffectConstant.target == 2}">selected="selected"</c:if> > 敌方全体 </option>
							<option value="3" <c:if test="${seffectConstant.target == 3}">selected="selected"</c:if> > 己方全体 </option>
							<option value="4" <c:if test="${seffectConstant.target == 4}">selected="selected"</c:if> > 自身 </option>
						</select>
					</td>
				</tr>
				
				
				<tr>
					<td colspan="4" align="center">
						<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
						<input type="reset" value="<s:text name='reset'></s:text>" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>