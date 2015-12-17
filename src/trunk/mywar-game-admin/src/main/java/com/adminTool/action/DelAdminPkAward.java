package com.adminTool.action;

import com.adminTool.service.AdminPkAwardService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 删除Pk奖励
 * 
 * @author 
 */
public class DelAdminPkAward extends ALDAdminActionSupport {

	private static final long serialVersionUID = 1348352187246027445L;
	private Integer id;

	public void executeDel() {
		AdminPkAwardService service = ServiceCacheFactory.getServiceCache()
				.getService(AdminPkAwardService.class);
		
		service.delAdminPkAward(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
