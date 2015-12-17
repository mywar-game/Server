<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>
	<head>
	<script>

		var responseMsgFlag = '${responseMsg}';
		
		if (responseMsgFlag == 'success') {
			//alert("同步成功");
			var failIds = '${failIds}';
			var successIds = '${successIds}';
			if (failIds != "") {
				alert("同步失败的服务器包括:" + failIds);
			}
			if (successIds != "") {
				alert("同步成功的服务器包括:" + successIds);
			}
			window.location.href = "systemMailInternalList";
		} else if (responseMsgFlag == 'fail') {
			alert("还没添加");
			window.location.href = "systemMailInternalList";
		}

	</script>
</head>
</html>