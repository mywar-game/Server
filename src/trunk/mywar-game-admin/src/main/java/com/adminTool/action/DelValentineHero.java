package com.adminTool.action;

import com.adminTool.service.ValentineHeroService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class DelValentineHero extends ALDAdminActionSupport{
	private static final long serialVersionUID = 6530394885296718235L;
	private Integer hero_id1;
	private Integer hero_id2;
	public Integer getHero_id1() {
		return hero_id1;
	}
	public void setHero_id1(Integer hero_id1) {
		this.hero_id1 = hero_id1;
	}
	public Integer getHero_id2() {
		return hero_id2;
	}
	public void setHero_id2(Integer hero_id2) {
		this.hero_id2 = hero_id2;
	}
	public String execute(){
		ValentineHeroService service = ServiceCacheFactory
				.getServiceCache().getService(ValentineHeroService.class);
		service.delete(hero_id1,hero_id2);
		return SUCCESS;
	}
}
