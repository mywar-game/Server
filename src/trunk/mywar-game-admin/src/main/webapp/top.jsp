<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.admin.service.AdminMenuService"%>
<%@page import="com.admin.bo.AdminMenu"%>
<%@page import="com.admin.bo.AdminUser"%>
<%@page import="com.framework.servicemanager.ServiceCacheFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>myspace</title>
		<style type="text/css">
			body {
				margin: 0px;
				background: #337ABB url("images/top_bg.gif");
				font-size: 12px;
			}
			
			div {
				margin: 0px;
				padding: 0px;
			}
			
			.system_logo {
				width: 160px;
				float: left;
				text-align: left;
				margin-top: 5px;
				margin-left: 5px;
			}
			
			/*- Menu Tabs 6--------------------------- */
			#tabs {
				position: absolute;
				left: 165px;
				width: 870px;
				line-height: normal;
			}
			
			#tabs ul {
				margin: 0;
				padding: 26px 10px 0 0px !important; *
				padding: 27px 10px 0 0px !important;
				padding: 27px 10px 0 0px;
				list-style: none;
			}
			
			#tabs li {
				display: inline;
				margin: 0;
				padding: 0;
			}
			
			#tabs a {
				float: left;
				background: url("images/tableft6.gif") no-repeat left top;
				margin: 0;
				padding: 0 0 0 4px;
				text-decoration: none;
			}
			
			#tabs a span {
				float: left;
				display: block;
				background: url("images/tabright6.gif") no-repeat right top;
				padding: 8px 8px 6px 6px;
				color: #E9F4FF;
			}
			
			/* Commented Backslash Hack hides rule from IE5-Mac \*/
			#tabs a span {
				float: none;
			}
			
			/* End IE5-Mac hack */
			#tabs a:hover span {
				color: #fff;
			}
			
			#tabs a:hover {
				background-position: 0% -42px;
			}
			
			#tabs a:hover span {
				background-position: 100% -42px;
				color: #222;
			}
		</style>
		<script >
		function changeSpanBackGroup(id,aobj){
		parent.frmleft.disp(id);
		var allitemspan = document.getElementsByTagName("span");
          for(var i=0;i<allitemspan.length;i++){
           allitemspan[i].style.backgroundPosition = '';
           allitemspan[i].style.color='#E9F4FF';
         }
         var allitema = document.getElementsByName("topatag");
         for(var i=0;i<allitema.length;i++){
           allitema[i].style.backgroundPosition='';
         }
         var obj = document.getElementById("span"+id);
          obj.style.backgroundPosition='100% -42px';
          obj.style.color='#222';
          aobj.style.backgroundPosition='0% -42px';
         }
		</script>
	</head>
	<body>
		<div class="menu">
			<div class="system_logo"></div>
			<div id="tabs">

				<ul>
					<%
						Object o = session.getAttribute("aldadmin");
						if (o != null) {
							String menuPowerString = ((AdminUser) o).getMenuPowerString();
							AdminMenuService ams = ServiceCacheFactory.getServiceCache().getService(AdminMenuService.class);
							List<AdminMenu> list = ams.findAllRootList();
							String str = "";
							String menuPath = "";
							for (AdminMenu am : list) {
								//鉴权
								if(am.getId().intValue()==8){
									continue;//数据配置部分先屏蔽
								}
								int powerId = am.getPowerId();
								char[] array = menuPowerString.toCharArray();
								if (array[powerId - 1] != '1') {
									continue;
								}
								if (am.getMenuPath() == null || am.getMenuPath().equals("")) {
									menuPath = "javascript:void(0);";
								} else {
									menuPath = am.getMenuPath();
								}
								str = str + "<li><a href='" + menuPath
										+ "' onclick='changeSpanBackGroup(" + am.getId()
										+ ",this);' target='frmright' name='topatag'><span id='span"+am.getId()+"'>"
										+ am.getMenuName() + "</span></a></li>";
							}
							out.print(str);
						}
					%>
					<!-- 
         <li><a href="module/feedback/list" onmouseover="parent.frmleft.disp(1);" target="frmright" ><span>反馈</span></a></li>

         <li><a href="module/goods/index" onmouseover="parent.frmleft.disp(2);" target="frmright" ><span>奖品管理</span></a></li>
			
		 <li><a href="module/work/index" onmouseover="parent.frmleft.disp(3);" target="frmright" ><span>劳动管理</span></a></li>
		 
		  <li><a href="module/s_channel/index" onmouseover="parent.frmleft.disp(6);" target="frmright" ><span>渠道管理</span></a></li>
		 
		 <li><a href="module/system/index" onmouseover="parent.frmleft.disp(4);" target="frmright" ><span>系统管理</span></a></li>
			
		 <li><a href="module/statistics/userreg" onmouseover="parent.frmleft.disp(5);" target="frmright" ><span>统计</span></a></li>
		 
		 <li><a href="javascript:void(0);" onmouseover="parent.frmleft.disp(7);" target="frmright" ><span>管理员</span></a></li>
			 -->
				</ul>
			</div>
		</div>
	</body>
</html>
