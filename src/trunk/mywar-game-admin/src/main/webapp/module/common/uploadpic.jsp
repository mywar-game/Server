<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/module/common/taglib.jsp" %>
<html>
<head>
<title>图片上传</title>
<script type="text/javascript">
function upload(){
var ff = document.forms.imageForm;
var img = ff.file.value;
if(img==null || img==""){
alert("图片路径不允许为空！");
return;
}
ff.target="_top";
ff.method="post";
ff.submit();
}
</script>
  <link href="${basePath}manager/css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
<%
  String moduleType = request.getParameter("moduleType");
  String imageFileName = request.getParameter("imageFileName");
  String imageId = request.getParameter("imageId");
  request.setAttribute("imageFileName",imageFileName);
  request.setAttribute("moduleType",moduleType);
  request.setAttribute("imageId",imageId);
 %>


<c:if test="${empty imageFileName&&not empty moduleType&&not empty sourcePath}">
<form id="imageForm" action="${sourcePath}/wap/wts/uploadimage?moduleType=${moduleType}&url=${basePath}manager/module/common/uploadpic.jsp&args=${radom}&userName=${userName}&password=${password}"
enctype="multipart/form-data">
<table cellpadding="3" cellspacing="1" border="0" width="100%"  align=center>
  <tr class="border">
    <td class="td_title" colspan="2" align="center">上传${moduleType}图片：</td>
  </tr>
  <tr>
    <td align="center"><input name="file" type=file value=''></td>
  </tr>
  <tr>
    <td align="center"><input type="button" value="上传" onclick="upload()"
style="cursor: pointer"></td>
  </tr>
</table>
</form>
</c:if>

<div>
<c:if test="${not empty imageFileName&&not empty imageId}">

<table cellpadding="3" cellspacing="1" border="0" width="100%"  align=center>
  <tr class="border">
    <td class="td_title" colspan="2" align="center">上传${moduleType}图片成功</td>
  </tr>
  <tr>
    <td align="center">图片名称为:${imageFileName} 图片ID为:${imageId}</td>
  </tr>
  <tr>
    <td align="center"><input type="button" value="确定" onclick="javascript:window.opener.document.getElementById('filename').value='${imageFileName}';window.opener.document.getElementById('photoId').value='${imageId}';window.close();"
style="cursor: pointer"></td>
  </tr>
</table>
</c:if>
</div>
<s:fielderror/> 
</body>
</html>