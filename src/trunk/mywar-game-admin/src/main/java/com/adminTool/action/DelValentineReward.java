package com.adminTool.action;

import com.adminTool.service.ValentineRewardService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class DelValentineReward extends ALDAdminActionSupport{
	private static final long serialVersionUID = 6335594885296718235L;
	private Integer id;
	private String isCommit1 = "T";
	public String getIsCommit1() {
		return isCommit1;
	}
	public void setIsCommit1(String isCommit1) {
		this.isCommit1 = isCommit1;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String execute(){
		ValentineRewardService service=ServiceCacheFactory
				.getServiceCache().getService(ValentineRewardService.class);
		service.delete(id);
		return SUCCESS;
	}
}
