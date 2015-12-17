package com.admin.action;

import com.admin.bo.AdminUser;
import com.admin.service.AdminUserService;
import com.admin.util.DTools;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;

public class UpdatePwdDueTime extends ALDAdminActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private AdminUser adminUser;
	
	private String dueTime;
	
	private Integer Id;
	
	private String isCommit = "F";

	public String execute() {
		AdminUserService aus = ServiceCacheFactory.getServiceCache().getService(AdminUserService.class);
		adminUser = aus.findOneAdmin(Id);

		if (adminUser == null) {
			return ERROR;
		}
		if (isCommit.equals("T")) {
			if (!check()) {
				return SUCCESS;
			}
			String newDueTime = DateUtil.dateToString(DateUtil.getDiffDate(adminUser.getDueTime(), Integer.parseInt(dueTime)), DateUtil.FORMAT_ONE);
			aus.updatePwdDueTime(Id, newDueTime);
			return "result";
		}
		return SUCCESS;
	}

	public boolean check() {
		if (DTools.isEmpty(dueTime) || Integer.parseInt(dueTime)==0) {
			super.setErroDescrip(this.getText("updatePwdDueTimeJsp.note"));
			return false;
		}
		return true;
	}
	
	public AdminUser getAdminUser() {
		return adminUser;
	}
	
	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}


	public String getDueTime() {
		return dueTime;
	}

	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}
