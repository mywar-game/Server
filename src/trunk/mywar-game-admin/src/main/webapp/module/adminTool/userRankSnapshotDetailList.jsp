<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name=""></s:text></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<input type="button" class="button" onclick="copyToClipBoard()" id="copy" value="<s:text name="userRankSnapshotDetailListJsp.copyButton"></s:text>">
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="10" align="center">
					<center>
						<s:text name="%{'userRankSnapshot.type_'+userRankSnapshotList.get(0).type}"></s:text>(<s:text name="format.date_time"><s:param value="userRankSnapshotList.get(0).createTime"></s:param></s:text>)
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="userRankSnapshot.rank"></s:text>
				</td>
				<td>
					<s:text name="userRankSnapshot.name"></s:text>
				</td>
				<td>
					<s:text name="userRankSnapshot.note"></s:text>
				</td>
			</tr>
			<s:iterator var="userRankSnapshot" value="userRankSnapshotList" status="sta">
				<s:if test="#sta.index == 0">
					<s:set var="names" value="#userRankSnapshot.name"></s:set>
				</s:if>
				<s:else>
					<s:set var="names" value="#names+','+#userRankSnapshot.name"></s:set>
				</s:else>
					<tr>
						<td>
							${userRankSnapshot.rank}
						</td>
						<td>
							${userRankSnapshot.name}
						</td>
						<td>
							<s:if test="#userRankSnapshot.type == 5">
								${userRankSnapshot.note}						
							</s:if>
							<s:else>
								<s:text name="%{'userRankSnapshot.note_'+#userRankSnapshot.type+'_show'}"><s:param>${userRankSnapshot.note}</s:param></s:text>
							</s:else>
						</td>
					</tr>
			</s:iterator>
		</table>
	</body>
	<script type="text/javascript" src="../../js/zeroclipboard/ZeroClipboard.js"></script>
	<script type="text/javascript">
		var clip = new ZeroClipboard.Client(); // 新建一个对象
		clip.setHandCursor( true ); // 设置鼠标为手型
		clip.setText('${names}'); // 设置要复制的文本。
		clip.glue("copy"); // 和上一句位置不可调换
		clip.addEventListener('complete', function () {
			alert("<s:text name="userRankSnapshotDetailListJsp.copySuccess"></s:text>");
		}); 
	</script>
</html>