package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.GGuildTechnologyConstant;
import com.dataconfig.service.GGuildTechnologyConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class GGuildTechnologyList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 4134183703186010424L;
	
	private List<GGuildTechnologyConstant> guildTechnologyConstantList;
	
	public String execute() {
		GGuildTechnologyConstantService guildTechnologyService = ServiceCacheFactory.getServiceCache().getService(GGuildTechnologyConstantService.class);
		IPage<GGuildTechnologyConstant> list = guildTechnologyService.findGGuildTechnologyConstantList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			guildTechnologyConstantList = (List<GGuildTechnologyConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setGuildTechnologyConstantList(
			List<GGuildTechnologyConstant> guildTechnologyConstantList) {
		this.guildTechnologyConstantList = guildTechnologyConstantList;
	}

	public List<GGuildTechnologyConstant> getGuildTechnologyConstantList() {
		return guildTechnologyConstantList;
	}
}
