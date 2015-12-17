package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.HHeroIntimacyConstant;
import com.dataconfig.service.HeroIntimacyConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class HeroIntimacyConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -146494281194269666L;
	
	private List<HHeroIntimacyConstant> heroIntimacyConstantList;
	
	public String execute() {
		HeroIntimacyConstantService heroIntimacyConstantService = ServiceCacheFactory.getServiceCache().getService(HeroIntimacyConstantService.class);
		IPage<HHeroIntimacyConstant> list = heroIntimacyConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			heroIntimacyConstantList = (List<HHeroIntimacyConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setHeroIntimacyConstantList(List<HHeroIntimacyConstant> heroIntimacyConstantList) {
		this.heroIntimacyConstantList = heroIntimacyConstantList;
	}

	public List<HHeroIntimacyConstant> getHeroIntimacyConstantList() {
		return heroIntimacyConstantList;
	}

}
