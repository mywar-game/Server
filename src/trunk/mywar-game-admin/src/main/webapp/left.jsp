<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.admin.bo.AdminUser"%>
<%@page import="com.admin.service.AdminMenuService"%>
<%@page import="com.framework.servicemanager.ServiceCacheFactory"%>
<%@page import="com.admin.bo.AdminMenu"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>末日危机</title>
		<style type="text/css">
html { 
	overflow-y: scroll; 
	overflow-x: scroll;
}
body {
	margin: 0px;
	background: transparent;
	overflow: hidden;
	background: url("images/leftbg.gif");
}

.left_color {
	text-align: left;
}

.left_color a:link {
	color: #083772;
	text-decoration: none;
	font-size: 12px;
	display: block;
	width: 178px;
	text-align: left;
	background: url("images/menubg.gif");
	background-repeat: no-repeat;
	height: 23px;
	line-height: 23px;
}

span {
	margin-left: 20px;
}

.left_color  a:visited {
	color: #083772;
	text-decoration: none;
	font-size: 12px;
	display: block;
	width: 178px;
	text-align: left;
	background: url("images/menubg.gif");
	background-repeat: no-repeat;
	height: 23px;
	line-height: 23px;
}

.left_color a:hover {
	color: #7B2E00;
	background: url("images/menubg_hover.gif") left no-repeat;
}

.content {
	width: 178px;
	height: 23px;
	margin: 0px;
	padding: 0px;
}

.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
	width: 178px;
	height: 23px;
}

.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 23px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 23px;
	width: 178px;
	padding-left: 0px;
}

.MM {
	width: 182px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px, 0px, 0px, 0px);
}

.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}

.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}

.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}

.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #006600;
	/*此图片不好看 注释掉*/
	background-image: url(images/menu_bg2.gif);
	background-repeat: no-repeat;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
</style>
		<script language="javascript" src="js/admin.js"></script>
		<script src="js/prototype.lite.js" type="text/javascript"></script>
		<script src="js/moo.fx.js" type="text/javascript"></script>
		<script src="js/moo.fx.pack.js" type="text/javascript"></script>
		<script type="text/javascript">
<!--
	function disp(n){
	    var str = document.getElementById("tempId").innerHTML;
	    document.getElementById("left"+str).style.display="none";
		document.getElementById("left"+n).style.display="";
		document.getElementById("tempId").innerHTML=n;
	}
//-->

   function showUrl(url,obj){
     parent.frmright.location.href=url;
     var allitemspan = document.getElementsByName("atag");
          for(var i=0;i<allitemspan.length;i++){
           allitemspan[i].style.backgroundImage = "";
         }
    obj.style.backgroundImage="url('images/menubg_hover.gif')";
   }
</script>
	</head>
	<body>
<div id="tempId" style='display: none'>-1</div>

		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td valign="top" style="padding-top: 10px;" class="left_color"
					id="menubar">
				<div id="left-1" style="display: ">

					</div>
<%
     Object o =  session.getAttribute("aldadmin");
		  if(o!=null){
		  String menuPowerString =  ((AdminUser)o).getMenuPowerString();
		  AdminMenuService ams = ServiceCacheFactory.getServiceCache().getService(AdminMenuService.class);
		  Map<Integer,List<AdminMenu>> map = ams.findOneToTwoMap();
		  List<AdminMenu> tempList=null; 
		  List<AdminMenu> temp2List=null; 
		  
		  String str = "";
		    for(Integer i:map.keySet()){

		      tempList = map.get(i);
		      str = str+"<div id='left"+i+"' style='display: none'>";
		      for(AdminMenu am:tempList){
		         //鉴权
		     	int powerId = am.getPowerId();
				char[] array = menuPowerString.toCharArray();
				if(array[powerId-1]!='1'){
				    continue;
 				}
		          if(map.containsKey(am.getId())&&am.getIfLeaf().equals("F")){
		            str = str +"<span class='type'><a href='javascript:void(0);'>"+am.getMenuName()+"</a></span>";
		            str = str +"<div class='content'><ul class='MM'>";
		            temp2List = map.get(am.getId());
		            for(AdminMenu am2:temp2List){
		            	 int powerId2 = am.getPowerId();
				         char[] array2 = menuPowerString.toCharArray();
				         if(array2[powerId2-1]!='1'){
				           continue;
 				          }
		               str = str +"<li>";
		              // str = str+"<a href='"+am2.getMenuPath()+"' target='frmright'>"+am2.getMenuName()+"</a>";
		              str = str+"<a href='javascript:void(0);' onclick=showUrl('"+am2.getMenuPath()+"',this) target='frmright' name='atag'>"+am2.getMenuName()+"</a>";
		               str = str+ "</li>";
		            }							
					str = str + "</ul></div>";			
		          }else{
		            str = str +"<a href='javascript:void(0);' onclick=showUrl('"+am.getMenuPath()+"',this) target='frmright' name='atag'>"+am.getMenuName()+"</a>";
		          }
		       }
		       str = str + "</div>";
		    }
		    out.print(str);
		  }
 %>
 
				</td>
			</tr>
<script type="text/javascript">
		var contents = document.getElementsByClassName('content');
		var toggles = document.getElementsByClassName('type');
	
		var myAccordion = new fx.Accordion(
			toggles, contents, {opacity: true, duration: 400}
		);
		myAccordion.showThisHideOpen(contents[-1]);
	</script>
		</table>
	</body>
</html>