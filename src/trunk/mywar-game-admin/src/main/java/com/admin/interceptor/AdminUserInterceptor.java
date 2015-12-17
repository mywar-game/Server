package com.admin.interceptor;

import java.util.Map;

import com.admin.bo.AdminPowerPhysics;
import com.admin.bo.AdminUser;
import com.admin.service.AdminPowerPhysicsService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminUserInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		// TODO Auto-generated method stub
		//HttpSession session = ServletActionContext.getRequest().getSession();
        //String sessionID = session.getId();
		Map session = actionInvocation.getInvocationContext().getSession();
		Object tag = session.get("aldadmin");
		AdminUser au = null;
		if (tag == null) {
			return "nologin";
		} else {
			au = (AdminUser) tag;
			ALDAdminActionSupport aldAction = null;
			Object obj = actionInvocation.getAction();
			if (obj instanceof ALDAdminActionSupport) {
				aldAction = (ALDAdminActionSupport) obj;
			} else {
				throw new RuntimeException("ACTION继承的类非ALDAdminActionSupport");
			}
			
			/**
			 * 获得当前ACTION的名称
			 */
			String className = obj.getClass().getCanonicalName();
			
			
			//检查如果访问的是配置数据表则要判断是否设置了数据源
			String systemNum = au.getExp1();
			if ((className.indexOf("com.adminTool.action") != -1
					 || className.indexOf("com.dataconfig.action") != -1
					 || className.indexOf("com.stats.action") != -1 
					 || className.indexOf("com.log.action") != -1
					)
					 && (systemNum == null  || systemNum.equals(""))) {
				return "nodatasource";
			}
			
			/**
			 * 鉴权
			 */
			AdminPowerPhysicsService app = ServiceCacheFactory.getServiceCache().getService(AdminPowerPhysicsService.class);
			AdminPowerPhysics ap = app.findPowerByActionName(className);
			if (ap != null) {
				int powerId = ap.getPowerId();
				char[] array = au.getPowerString().toCharArray();
				if (array[powerId - 1] != '1') {
					return "nopower";
				}
			} else {
				return "nopysic";
			}		
			aldAction.setAdminUser(au);
			//设置系统编号 线程变量
			if (systemNum != null && !systemNum.equals("")) {
				CustomerContextHolder.setSystemNum(Integer.valueOf(systemNum));
			}
			// 异常处理
			try {
				String result = actionInvocation.invoke();
				return result;
			} catch (Exception e) {
				return "glober_error";
			}
//			System.out.println("result == "+result);
		}
	}
}
