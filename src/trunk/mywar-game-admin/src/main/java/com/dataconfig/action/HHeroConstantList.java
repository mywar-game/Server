package com.dataconfig.action;

import java.util.List;
import java.util.Map;

import com.dataconfig.bo.HHeroConstant;
import com.dataconfig.service.HHeroConstantService;
import com.dataconfig.service.SSkillConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class HHeroConstantList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 2741808726763805345L;
	
	private List<HHeroConstant> hheroConstantList;
	
	private Map<Integer, String> skillIDNameMap;
	
	private Integer id;
	
	public String execute() {
		SSkillConstantService skillConstantService = ServiceCacheFactory.getServiceCache().getService(SSkillConstantService.class);
		skillIDNameMap = skillConstantService.findSkillIDNameMap();
		
		HHeroConstantService hHeroConstantService = ServiceCacheFactory.getServiceCache().getService(HHeroConstantService.class);
		IPage<HHeroConstant> pageList = hHeroConstantService.findHHeroConstantPageList(id, super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (pageList != null) {
			hheroConstantList = (List<HHeroConstant>) pageList.getData();
			super.setTotalPage(pageList.getTotalPage());
			super.setTotalSize(pageList.getTotalSize());
		}
		return SUCCESS;
	}
	
	public List<HHeroConstant> getHheroConstantList() {
		return hheroConstantList;
	}
	
	public void setHheroConstantList(List<HHeroConstant> hheroConstantList) {
		this.hheroConstantList = hheroConstantList;
	}

	public void setSkillIDNameMap(Map<Integer, String> skillIDNameMap) {
		this.skillIDNameMap = skillIDNameMap;
	}

	public Map<Integer, String> getSkillIDNameMap() {
		return skillIDNameMap;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
