package com.dataconfig.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.MMonsterConstant;
import com.dataconfig.service.MMonsterConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class MMonsterConstantList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 2655844680042619144L;

	private List<MMonsterConstant> monsterConstantList;
	
	private Integer id;
	
	private Integer level;
	
	private String name = "";
	
	private Map<Integer, String> monsterIdNameMap;

	public String execute() {
//		System.out.println("level ====================== "+level);
//		System.out.println("name ======================= "+name);
		try {
			name = URLDecoder.decode(name, "UTF-8").trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		System.out.println("name ==================== "+name);
		MMonsterConstantService monsterConstantService = ServiceCacheFactory.getServiceCache().getService(MMonsterConstantService.class);
		IPage<MMonsterConstant> page = monsterConstantService.findMMonsterConstantList(id, level, name, super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (page != null) {
			monsterConstantList = (List<MMonsterConstant>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getPageSize());
		}
		return SUCCESS;
	}

	public String findMonsterIdNameMapByCondition() throws Exception {
		MMonsterConstantService monsterConstantService = ServiceCacheFactory.getServiceCache().getService(MMonsterConstantService.class);
		monsterIdNameMap = monsterConstantService.findMonsterIdNameMapByCondition(level, URLDecoder.decode(name, "UTF-8").trim());
		super.setErroDescrip("asdf");
		return "find";
	}
	
	public void setMonsterConstantList(List<MMonsterConstant> monsterConstantList) {
		this.monsterConstantList = monsterConstantList;
	}

	public List<MMonsterConstant> getMonsterConstantList() {
		return monsterConstantList;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getLevel() {
		return level;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setMonsterIdNameMap(Map<Integer, String> monsterIdNameMap) {
		this.monsterIdNameMap = monsterIdNameMap;
	}

	public Map<Integer, String> getMonsterIdNameMap() {
		return monsterIdNameMap;
	}
}
