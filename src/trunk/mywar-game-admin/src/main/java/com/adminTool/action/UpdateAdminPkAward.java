package com.adminTool.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.AdminPkAward;
import com.adminTool.service.AdminPkAwardService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;


/**
 * 修改PK奖励
 * 
 *
 */
public class UpdateAdminPkAward extends ALDAdminActionSupport implements
		ModelDriven<AdminPkAward> {

	private static final long serialVersionUID = -1110226654585540875L;
	private AdminPkAward adminPkAward = new AdminPkAward();

	private String isCommit = "F";

	public String execute() {
		AdminPkAwardService service = ServiceCacheFactory.getServiceCache()
				.getService(AdminPkAwardService.class);
		if (isCommit.equals("F")) {

			adminPkAward = service.getOneAdminPkAward(adminPkAward.getId());

			return INPUT;
		}


		service.updateAdminPkAward(adminPkAward);
		return SUCCESS;
	}


	public AdminPkAward getAdminPkAward() {
		return adminPkAward;
	}

	public void setAdminMarquee(AdminPkAward adminPkAward) {
		this.adminPkAward = adminPkAward;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	@Override
	public AdminPkAward getModel() {
		// TODO Auto-generated method stub
		return adminPkAward;
	}

}
