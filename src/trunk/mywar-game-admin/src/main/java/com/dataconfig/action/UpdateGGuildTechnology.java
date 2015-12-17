package com.dataconfig.action;

import com.dataconfig.bo.GGuildTechnologyConstant;
import com.dataconfig.service.GGuildTechnologyConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateGGuildTechnology extends ALDAdminActionSupport implements ModelDriven<GGuildTechnologyConstant> {

	private static final long serialVersionUID = -129679858266578888L;

	private GGuildTechnologyConstant guildTechnologyConstant = new GGuildTechnologyConstant();
	
	private String isCommit = "F";
	
	public String execute() {
		GGuildTechnologyConstantService guildTechnologyService = ServiceCacheFactory.getServiceCache().getService(GGuildTechnologyConstantService.class);
		if ("F".equals(isCommit)) {
			guildTechnologyConstant = guildTechnologyService.findOneGGuildTechnologyConstant(guildTechnologyConstant.getGuildTechnologyId());
			return INPUT;
		} else {
			guildTechnologyService.updateOneGGuildTechnologyConstant(guildTechnologyConstant);
			return SUCCESS;
		}
	}
	@Override
	public GGuildTechnologyConstant getModel() {
		// TODO Auto-generated method stub
		return guildTechnologyConstant;
	}
	
	public String getIsCommit() {
		return isCommit;
	}
	
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
	public GGuildTechnologyConstant getGuildTechnologyConstant() {
		return guildTechnologyConstant;
	}
	public void setGuildTechnologyConstant(
			GGuildTechnologyConstant guildTechnologyConstant) {
		this.guildTechnologyConstant = guildTechnologyConstant;
	}
}