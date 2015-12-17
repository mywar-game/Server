package com.adminTool.action;

import com.adminTool.service.ValentineHeroService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class AddValentineHero extends ALDAdminActionSupport {
	private static final long serialVersionUID = 6530894885296718235L;
	private String isCommit = "F";
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

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String execute() {
		if (isCommit.equals("F")) {
			return INPUT;
		} else {
			ValentineHeroService service = ServiceCacheFactory
					.getServiceCache().getService(ValentineHeroService.class);
			service.add(hero_id1, hero_id2);
			return SUCCESS;
		}
	}
}
