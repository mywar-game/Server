package com.admin.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.admin.bo.AdminUser;
import com.admin.bo.AdminUserLog;
import com.admin.service.AdminUserService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.ErrorCode;
import com.framework.common.MD5;
import com.framework.common.SystemCode;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ActionContext;

public class AdminLogin extends ALDAdminActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String adminName;
	private String adminPassword;
	private String randString;
	public String getRandString() {
		return randString;
	}

	public void setRandString(String randString) {
		this.randString = randString;
	}

	public String execute() {
		if (adminName == null || adminPassword == null)
			return "error";

		AdminUserService aus = ServiceCacheFactory.getServiceCache()
				.getService(AdminUserService.class);
		HttpSession sessionhttp = ServletActionContext.getRequest()
				.getSession();
		String rand = (String) sessionhttp.getAttribute("rand");
		if (rand == null) {
			super.setErroCodeNum(SystemCode.SYS_FAIL);
			super.setErroDescrip("验证码过期！");
			return "error";
		}
		if (!rand.equals(randString)) {
			super.setErroCodeNum(SystemCode.SYS_FAIL);
			super.setErroDescrip("验证码不正确！");
			return "error";
		}
		ErrorCode error = new ErrorCode();
		adminPassword = MD5.md5Of32(adminPassword);
		AdminUser au = aus.findAdminUser(adminName, adminPassword, error);
		if (error.getErrorCode() != SystemCode.SYS_SUCESS) {
			super.setErroCodeNum(SystemCode.SYS_FAIL);
			super.setErroDescrip(error.getErrorDisc());
			return "error";
		} else {
			ActionContext ctx = ActionContext.getContext();
			Map session = ctx.getSession();
			session.put("aldadmin", au);
			if(au.getId().intValue()!=1 && au.getLoginFailTimes().intValue()!=0){
				aus.updateLock(au.getId(), 0);// 非admin用户登陆成功后如果失败次数不为0则失败次数置0
			}
			// 记录管理员登陆日志
			AdminUserLog log = new AdminUserLog();
			log.setAdminName(au.getAdminName());
			log.setState(1);
			log.setTime(new Date());
			aus.saveLog(log);
			return "error";
		}
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getErroDescrip() {
		return super.getErroDescrip();
	}

	public int getErroCodeNum() {
		return super.getErroCodeNum();
	}
}
