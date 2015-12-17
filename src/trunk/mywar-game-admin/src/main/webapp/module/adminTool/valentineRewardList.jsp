<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>情人节英雄配对配置</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../../css/jquery.ui.themes/base/jquery.ui.all.css">	
</head>
<script type="text/javascript" src="../../js/ajax.js"></script>
<script src="../../js/jquery/jquery.validate.js"></script>
<script type="text/javascript">
function add(){
window.location.href="addValentineReward";
}
</script>

<script type="text/javascript">
	function del(id){
		if (window.confirm("<s:text name='deleteConfirm'></s:text>")) {
			var ajaxobj = new Ajax();
			ajaxobj.url = "delValentineReward?id="+id;
			ajaxobj.callback=function(){
			}
		ajaxobj.send();
		}
	}
	
</script>
<script type="text/javascript">

			// 清空获取道具
			function clearTool(tableTd, idsColumn, showColumn, clearComfirm) {
				var mark = window.confirm(clearComfirm);
				if (!mark) {
					return;
				}
	
				var obj = tableTd.parentNode.parentNode;
				var childrenArr = obj.children;

				if (childrenArr.length > 4) {
					var children = childrenArr[idsColumn];
					var inputNode = children.children[0];
					inputNode.value = '';

					childrenArr[showColumn].innerHTML = '';
				}
			}

			// 去掉字符串的空格
			function trim(str) {
				str = str.replace(/^(\s|\u00A0)+/, '');
				for (var i = str.length - 1; i >= 0; i--) {
					if (/\S/.test(str.charAt(i))) {
						str = str.substring(0, i + 1);
						break;
					}
				}
				return str;
			}

function addTool(tableTd, idsColumn, showColumn){
	var hight = (screen.height - 300) / 2.8;
				var width = (screen.width - 500) / 2;
				var str = window.showModalDialog("addTool", "", "dialogLeft=" + width
					+ "; dialogTop=" + hight
					+ "; dialogWidth=500px; dialogHeight=300px; location=no");

				if (str == null || str == '') {
					return;
				}
				alert(str);
					// 获取到的道具
				var strArr = str.split('*');
				alert(strArr);
				var obj = tableTd.parentNode.parentNode; // tr
				var childrenArr = obj.children; // tdArr

				//if (childrenArr.length < 4) {
				//	return;
				//}
				// 获取原有的rewards
				var children = childrenArr[idsColumn]; // td
				var inputNode = children.children[0];
				var old = inputNode.value;
				trim(old);
				//alert(old2);

				if (old != "") {
					var v = old + "|" + strArr[0];
					v = trim(v);
					inputNode.value = v;
				} else {
					inputNode.value = trim(strArr[0]);
				}

				children = childrenArr[showColumn];
				old = children.innerHTML;
				if (old != "") {
					children.innerHTML = old + "|" + strArr[1];
				} else {
					children.innerHTML = strArr[1];
				}
}

       
</script>

<script type="text/javascript">
           var add = function(){
               window.location.href="addValentineReward";
                 }
</script>
<body>
<input type="submit" class="button" value="添加配对英雄奖励" onclick="add();"/>
<table id="table"cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
  <tr class="border">
	       <td class="td_title" colspan="22" align="center">
	                <center>
					     七夕英雄配对奖励配置
					</center>
	       </td>
  </tr>
  <tr>
    <td> 
                 ID
    </td>
    <td>
                                                           匹配数量
    </td>
     <td>
                                                           奖励
    </td>
     <td>
                                                           奖励描述
    </td>
      <td>
                                                         删除
    </td>
    <%--
    <td>
                                                         添加道具
    </td>
    <td>
                                                           清空道具                                           
    </td>--%>
</tr>
<s:iterator var="valentineReward" value="list">
<tr>
<td>
${valentineReward.id}
</td>
<td>
${valentineReward.match_num}
</td>
<td>
${valentineReward.rewards}
</td>
<td>
${valentineReward.rewardss}
</td>
<td>
     <a href="#" onclick='del("${valentineReward.id}")'>删除</a>
</td>
   <%--
<td>
     <a href="#" onclick='addTool(this, 2, 3)'><s:text name="addTool"></s:text></a>
</td>
         <td>
     <a href="#" onclick='cTool(this, 2, 3)'><s:text name="clearTool"></s:text></a>
        </td>
        --%>
</tr>
</s:iterator>
</table>
</body>
</html>