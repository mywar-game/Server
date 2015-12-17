<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userChatLogListJsp.title"></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="refresh" content="20">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		
	</head>
	<script type="text/javascript" src="../../js/ajax.js"></script>
	<script type="text/javascript">
		
		function deal(chatObject,type,name,systemNum){
			var typeTextArray = new Array();
			<s:generator separator="," val="%{getText('userChatLogListJsp.operation_value')}">
				<s:iterator var="str">
						typeTextArray.push("<s:text name="%{'userChatLogListJsp.operation_'+#str}"></s:text>");
				</s:iterator>
			</s:generator>
			//alert(typeTextArray);	
			if(window.confirm('<s:text name="userChatLogListJsp.operateConfirm"><s:param>'+name+'</s:param><s:param>'+typeTextArray[type]+'</s:param></s:text>')){
				var ajaxobj = new Ajax();
		        ajaxobj.url="userChatLogListdealPlayerState?operateType="+type+"&userID="+chatObject+"&sysNum="+systemNum;
		        ajaxobj.callback=function(){
			    	var responseMsg = eval('(' + ajaxobj.gettext() + ')');
			    	alert(responseMsg.erroDescrip);
			    }
			    ajaxobj.send();
			}
		}
		
		function delSensitive(word,num){
			if (window.confirm('<s:text name="deleteConfirm"><s:param><s:text name="sensitive.word"></s:text></s:param><s:param>'+word+'</s:param></s:text>')) {
				var ajaxobj = new Ajax();
		        ajaxobj.url="userChatLogList?words="+encodeURI(encodeURI(word))+"&type="+2;
	         	ajaxobj.callback=function(){
		        	var tr = document.getElementById(num);
					var table = document.getElementById("table1");
			        table.deleteRow(tr.rowIndex);  
		        }
			    ajaxobj.send();
			}
			
		}
		
		function addSensitive(chatObject){
				var ajaxobj = new Ajax();
		        ajaxobj.url="userChatLogList?words="+encodeURI(encodeURI(chatObject))+"&type="+1;
			    ajaxobj.send();
			    location = location;
		}
		

		function refreshuserChatLog(){
				var ajaxobj = new Ajax();
		        ajaxobj.url="userChatLogList";
			    ajaxobj.send();
			   
		}
		
		function dealUserChatLog(pageNum,userId,userName,name){
		        window.location.href = "userChatLogList?pageNum="+pageNum+"&userId="+userId+"&userName="+encodeURI(encodeURI(userName))+"&name="+encodeURI(encodeURI(name));
			   
		}
			
	</script>
	
	<body>
		<form action="userChatLogList" method="post" >
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align="center" id="table">
				<tr class="border">
					<td class="td_title" colspan="10" >
						<center><s:text name="userChatLogListJsp.title"></s:text> &nbsp;</center>
					</td>
				</tr>
				<tr class="border" >
					<td >
						<center><s:text name="userChatLogListJsp.searchUserId"></s:text><s:text name="colon"></s:text></center>
					</td>
					<td>
						<input type="text" name="userId" id="userId" class="maxLife" size="10" maxlength="20" onblur="value=value.replace(/[^\d]/g,'')" />
					</td>
					<td>
						<center><s:text name="userChatLogListJsp.searchUserName"></s:text><s:text name="colon"></s:text></center>
					</td>
					<td>
						<input type="text" name="userName" id="userName"  class="maxLife" size="10" maxlength="64"  />
					</td>
					<td>
						<center><s:text name="userChatLogListJsp.searchName"></s:text><s:text name="colon"></s:text></center>
					</td>
					<td>
						<input type="text" name="name" id="name" class="maxLife" size="10" maxlength="64"  />
					</td>
					<td align="center">
						<input type="submit" value="<s:text name="search"></s:text>" class="button" />
					</td>
				</tr>
				</table>
		</form>
		
				<table cellpadding="3" cellspacing="1" border="0" width="70%" align="left" id="table">
				<tr>
					<td>
						<s:text name="userChatLog.chatTime"></s:text>
					</td>
					<td>
						<s:text name="userChatLog.chatObject"></s:text>
					</td>
					<td>
						<s:text name="userChatLog.userName"></s:text>
					</td>
					<td>
						<s:text name="userChatLog.name"></s:text>
					</td>
					<td width="100">
						<s:text name="userChatLog.chatType"></s:text>
					</td>
					<td width="300" align="center">
						<s:text name="userChatLog.chatMessage"></s:text>
					</td>
					<td  align="center">
						<s:text name="userChatLog.systemNum"></s:text>
					</td>
					
					<td colspan="5" align="center">
						<s:text name="userChatLogListJsp.operation"></s:text>
					</td>
				</tr>
			<s:iterator var="userChatLog" value="userChatLogList" >
				<tr >
					<td>
						<s:text name="format.date_time">
							<s:param value="#userChatLog.chatTime"></s:param>
						</s:text>
					</td>
					
					<td>
							${userChatLog.chatObject}
					</td>
					
					<td>
							${userChatLog.userName}
					</td>
				
					<td>
							${userChatLog.name}
					</td>
					
					<td>	
						<s:text name="%{'userChatLog.chatType_'+#userChatLog.chatType}"></s:text>
					</td>
					
					<td>
							${userChatLog.chatMessage}
					</td>
					
					<td>
							${userChatLog.systemNum}
					</td>
					
					<s:generator separator="," val="%{getText('userChatLogListJsp.operation_value')}">
						<s:iterator var="str">
							<td>
								<input type="button" value="<s:text name="%{'userChatLogListJsp.operation_'+#str}"></s:text>"  onclick='deal(${userChatLog.chatObject},${str},"${userChatLog.name}",${userChatLog.systemNum})' class="button"/>
							</td>
						</s:iterator>
					</s:generator>	
				</tr>
			</s:iterator>
			<tr>
				<td colspan="12">
					<a  href="#" onclick='dealUserChatLog(${pageNum-1},"${user.userId}","${user.userName}","${user.name}")'><s:text name="upPageText"></s:text></a> (${pageNum+1}/${pageSize})&nbsp;&nbsp;<a href="#" onclick='dealUserChatLog(${pageNum+1},"${user.userId}","${user.userName}","${user.name}")'><s:text name="nextPageText"></s:text></a>
				</td>
				
			</tr>
		</table>
		<form action="userChatLogList" method="post" onsubmit="return checknum()">
			<table cellpadding="3" cellspacing="1" border="0" width="30%" align="center" id="table2">
				<tr class="border">
					<td class="td_title" colspan="3" >
						<center><s:text name="sensitive.title"></s:text> &nbsp;</center>
					</td>
				</tr>
				<tr class="border" >
					<td>
						<s:text name="sensitive.add"></s:text>
					</td>
					<td>
						<input type="text"  name="words" id="words" class="maxLife"  maxlength="20"  />
					</td>
					<td>
						<input type="button" value="<s:text name="submit"></s:text>"  class="button"  maxlength="20" onclick='addSensitive(document.getElementById("words").value)' />
					</td>
				</tr>
				</table>
		</form>
		<table cellpadding="3" cellspacing="1" border="0" width="30%" align="center" id="table1">
		<tr>
			<td>
				<s:text name="sensitive.num"></s:text>
			</td>
			<td>
				<s:text name="sensitive.word"></s:text>
			</td>
			<td>
				<s:text name="delete"></s:text>
			</td>
		</tr>
			<s:iterator var="sensitive" value="sensitiveList" status="i">
			<tr id="${i.index + 1}">
				<td>
					${i.index + 1}
				</td>
				<td>
					${sensitive.word}
				</td>
				<td>
						<input type="button" value="<s:text name="delete"></s:text>"  onclick='delSensitive("${sensitive.word}",${i.index+1})' class="button"/>
				</td>
			</tr>
			</s:iterator>
			<tr>
				<td colspan="10">
					<aldtags:pageTag />
				</td>
			</tr>
		</table>
		
	</body>
</html>