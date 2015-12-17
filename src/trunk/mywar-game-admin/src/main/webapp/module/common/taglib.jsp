<%@ taglib uri="/aldmanagertags" prefix="aldtags"%>
<%@ taglib uri="/aldmanagerdttags" prefix="dt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	response.setCharacterEncoding("utf-8");
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<script type="text/javascript">
	var erroDescrip = '${erroDescrip}';
	if (erroDescrip != "") {
		alert(erroDescrip);
	}
</script>

<script language="javascript" src="../../js/jquery/jquery.min.js"></script>
<script>
	$(function(){
		setTrClass();
	});
	
	function setTrClass() {
		$('table tr:even').addClass('etr');
		$('table tr:odd').addClass('otr');
		
		$("table tr").each(function(){
			$(this).click(function(){
				if($(this).hasClass("trchoose")){
					$(this).removeClass("trchoose");
				}else{
					$(this).addClass("trchoose");
				}
			});
		});
	}
</script>