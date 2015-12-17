package com.dataconfig.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.SSkillConstant;
import com.dataconfig.service.SSkillConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class SSkillConstantList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 4004172839745115297L;
	
	private List<SSkillConstant> sskillConstantList;
	
	private Map<Integer, String> equipmentSkillIdNameMap;
	
	private Integer id;
	
	private String name = "";

	public String execute() {
//		System.out.println("id ====================== "+id);
//		System.out.println("name ==================== "+name);
		try {
			name = URLDecoder.decode(name, "UTF-8").trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		System.out.println("name ==================== "+name);
		
		SSkillConstantService sSkillConstantService = ServiceCacheFactory.getServiceCache().getService(SSkillConstantService.class);
		IPage<SSkillConstant> list = sSkillConstantService.findSSkillConstantPageList(id, name, super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			sskillConstantList = (List<SSkillConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}
	

	public String findTreasureIdNameMapByCondition() throws Exception {
		SSkillConstantService sSkillConstantService = ServiceCacheFactory.getServiceCache().getService(SSkillConstantService.class);
		equipmentSkillIdNameMap = sSkillConstantService.findEquipmentSkillIDNameMapByCondition(id, URLDecoder.decode(name, "UTF-8").trim());
		return "find";
	}
	
	public List<SSkillConstant> getSskillConstantList() {
		return sskillConstantList;
	}

	public void setSskillConstantList(List<SSkillConstant> sskillConstantList) {
		this.sskillConstantList = sskillConstantList;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


	public void setEquipmentSkillIdNameMap(Map<Integer, String> equipmentSkillIdNameMap) {
		this.equipmentSkillIdNameMap = equipmentSkillIdNameMap;
	}


	public Map<Integer, String> getEquipmentSkillIdNameMap() {
		return equipmentSkillIdNameMap;
	}
}
