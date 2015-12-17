package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.GuildMall;
import com.dataconfig.service.GuildMallService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class GuildMallList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = 1L;

	private List<GuildMall> guildMallList;
	
	public String execute() {
		GuildMallService guildMallService = ServiceCacheFactory.getServiceCache().getService(GuildMallService.class);
		IPage<GuildMall> list = guildMallService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			guildMallList = (List<GuildMall>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	/**
	 * 获取 guildMallList 
	 */
	public List<GuildMall> getGuildMallList() {
		return guildMallList;
	}

	/**
	 * 设置 guildMallList 
	 */
	public void setGuildMallList(List<GuildMall> guildMallList) {
		this.guildMallList = guildMallList;
	}

}
