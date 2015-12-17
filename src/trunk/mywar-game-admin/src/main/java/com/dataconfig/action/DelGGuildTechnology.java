package com.dataconfig.action;

import com.dataconfig.bo.GGuildTechnologyConstant;
import com.dataconfig.service.GGuildTechnologyConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelGGuildTechnology extends ALDAdminActionSupport implements ModelDriven<GGuildTechnologyConstant> {

	private static final long serialVersionUID = 8151498192362020085L;
	
	private GGuildTechnologyConstant guildTechnologyConstant = new GGuildTechnologyConstant();

	public void executeDel() {
		GGuildTechnologyConstantService guildTechnologyService = ServiceCacheFactory.getServiceCache().getService(GGuildTechnologyConstantService.class);
		guildTechnologyService.delGGuildTechnologyConstant(guildTechnologyConstant.getGuildTechnologyId());
	}
	
	@Override
	public GGuildTechnologyConstant getModel() {
		// TODO Auto-generated method stub
		return guildTechnologyConstant;
	}

	public GGuildTechnologyConstant getGuildTechnologyConstant() {
		return guildTechnologyConstant;
	}

	public void setGuildTechnologyConstant(
			GGuildTechnologyConstant guildTechnologyConstant) {
		this.guildTechnologyConstant = guildTechnologyConstant;
	}
}
