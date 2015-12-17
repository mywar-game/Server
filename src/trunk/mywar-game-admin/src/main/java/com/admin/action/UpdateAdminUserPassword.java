package com.admin.action;

import com.admin.bo.AdminUser;
import com.admin.service.AdminUserService;
import com.admin.util.DTools;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.ErrorCode;
import com.framework.common.MD5;
import com.framework.common.SystemCode;
import com.framework.servicemanager.ServiceCacheFactory;

public class UpdateAdminUserPassword extends ALDAdminActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private AdminUser adminUser;
	
	private String newPassword;
	
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
			ErrorCode error = new ErrorCode();
			String oldPassword = adminUser.getPassword();
			newPassword = MD5.md5Of32(newPassword);
			aus.updateAdminUserService(Id, oldPassword, newPassword, error);
			if (error.getErrorCode() != SystemCode.SYS_SUCESS) {
				super.setErroDescrip(error.getErrorDisc());
				return SUCCESS;
			} else {
				return "result";
			}
		}
		return SUCCESS;
	}

	public boolean check() {
		if (DTools.isEmpty(newPassword)) {
			super.setErroDescrip(this.getText("updatepasswordJsp.note"));
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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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

	public static void main(String[] args) {
		String newPassword = MD5.md5Of32("ft123321");
		System.out.println(newPassword);
	}
}
