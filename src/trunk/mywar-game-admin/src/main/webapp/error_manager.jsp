<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>操作错误页面</title>
	</head>
	<body>
	<p>
		<%
		    int erroCode;
		    if((Integer)request.getAttribute("erroCodeNum")==null){
		     erroCode = 0;
		    }else{
		     erroCode = (Integer)request.getAttribute("erroCodeNum");
		    }
		    
			String str = "";
			
			switch(erroCode) {
			 /*  case 0: str=str+"undefine error<br/>";break; */
			  case 0: str=str+"未定义错误码，系统抛出异常。";break;
			  case 1: str=str+"发生未知错误<br/>";break;
			  case 7001: str=str+"反馈ID不存在！<br/>";break;
			}
			%>
			
			<%
			out.print(str);
		%>
	</p>
	</body>
</html>
