package com.adminTool.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Activity;
import com.adminTool.bo.AdminPkAward;
import com.adminTool.constant.ActivityConstant;
import com.adminTool.service.AdminPkAwardService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加系统Pk奖励
 * 
 * @author 
 */
public class AddAdminPkAward extends ALDAdminActionSupport implements
		ModelDriven<AdminPkAward> {

	private static final long serialVersionUID = 2777960771533094581L;

	private AdminPkAward adminPkAward = new AdminPkAward();

	private String isCommit = "F";

	public String execute() {
		
		
		AdminPkAwardService service = ServiceCacheFactory.getServiceCache()
				.getService(AdminPkAwardService.class);
		if (isCommit.equals("F")) {
			AdminPkAward adminPkAwardI = service.finLastAdminPkAward();
			if (adminPkAwardI == null) {
				adminPkAward.setId(1);
			} else {
				adminPkAward.setId(adminPkAwardI.getId() + 1);
			}
			return INPUT;
		} else {
			
			service.addAdminPkAward(adminPkAward);
			return SUCCESS;
		}
	
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

	public AdminPkAward getAdminPkAward() {
		return adminPkAward;
	}

	public void setAdminPkAward(AdminPkAward adminPkAward) {
		this.adminPkAward = adminPkAward;
	}

}
