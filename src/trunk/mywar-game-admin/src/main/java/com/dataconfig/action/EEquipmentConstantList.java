package com.dataconfig.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.EEquipmentConstant;
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.HHeroConstantService;
import com.dataconfig.service.SSkillConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * @author hzy
 * 2012-7-20
 */
public class EEquipmentConstantList extends ALDAdminPageActionSupport {

	/**  **/
	private static final long serialVersionUID = -2895025529499251529L;

	/**  **/
	private List<EEquipmentConstant> eequipmentConstantList;
	
	/**  **/
	private Map<Integer, String> heroIdNameMap;
	
	/**  **/
	private Map<Integer, String> skillIdNameMap;
	
	/**  **/
	private Integer id;
	
	/**  **/
	private String name = "";
	
	/**  **/
	private Map<Integer, String> equipmentIDNameMap;

	@Override
	public String execute() {
		HHeroConstantService heroConstantService = ServiceCacheFactory.getServiceCache().getService(HHeroConstantService.class);
		heroIdNameMap = heroConstantService.findHeroIdNameMap();
		
		//System.out.println("id ====================== "+id);
		//System.out.println("name ==================== "+name);
		try {
			name = URLDecoder.decode(name, "UTF-8").trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//System.out.println("name ==================== "+name);
		EEquipmentConstantService eEquipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
		IPage<EEquipmentConstant> list = eEquipmentConstantService.findEEquipmentConstantPageList(id, name, super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			eequipmentConstantList = (List<EEquipmentConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		SSkillConstantService skillConstantService = ServiceCacheFactory.getServiceCache().getService(SSkillConstantService.class);
		skillIdNameMap = skillConstantService.findSkillIDNameMap();
		return SUCCESS;
	}


	public String findEquipmentIdNameMapByCondition() throws Exception {
		EEquipmentConstantService equipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
		equipmentIDNameMap = equipmentConstantService.findEquipmentIdNameMapByCondition(id, URLDecoder.decode(name, "UTF-8").trim());
		return "find";
	}
	
	/**
	 * @return the eequipmentConstantList
	 */
	public List<EEquipmentConstant> getEequipmentConstantList() {
		return eequipmentConstantList;
	}

	/**
	 * @param eequipmentConstantList the eequipmentConstantList to set
	 */
	public void setEequipmentConstantList(
			List<EEquipmentConstant> eequipmentConstantList) {
		this.eequipmentConstantList = eequipmentConstantList;
	}

	/**
	 * @return the heroIdNameMap
	 */
	public Map<Integer, String> getHeroIdNameMap() {
		return heroIdNameMap;
	}

	/**
	 * @param heroIdNameMap the heroIdNameMap to set
	 */
	public void setHeroIdNameMap(Map<Integer, String> heroIdNameMap) {
		this.heroIdNameMap = heroIdNameMap;
	}

	/**
	 * @return the skillIdNameMap
	 */
	public Map<Integer, String> getSkillIdNameMap() {
		return skillIdNameMap;
	}

	/**
	 * @param skillIdNameMap the skillIdNameMap to set
	 */
	public void setSkillIdNameMap(Map<Integer, String> skillIdNameMap) {
		this.skillIdNameMap = skillIdNameMap;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	public void setEquipmentIDNameMap(Map<Integer, String> equipmentIDNameMap) {
		this.equipmentIDNameMap = equipmentIDNameMap;
	}


	public Map<Integer, String> getEquipmentIDNameMap() {
		return equipmentIDNameMap;
	}
}
