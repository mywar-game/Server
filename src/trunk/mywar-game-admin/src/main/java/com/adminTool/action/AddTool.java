package com.adminTool.action;

import java.util.List;
import java.util.Map;

import com.adminTool.constant.ToolTypeConstant;
import com.dataconfig.bo.SystemHero;
import com.dataconfig.bo.SystemTool;
import com.dataconfig.service.SystemHeroService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 添加道具
 * 
 * @author yezp
 */
public class AddTool extends ALDAdminActionSupport {

	private static final long serialVersionUID = -5782715021253980160L;

	private List<SystemTool> toolList;
	private Map<Integer, String> typeNameMap;
	private Map<Integer, String> artifactIdNameMap;
	private Map<Integer, String> equipmentMap;
	private List<SystemHero> heroList;

	public String execute() {
		TTreasureConstantService treasureConstantService = ServiceCacheFactory
				.getServiceCache().getService(TTreasureConstantService.class);
//		SystemArtifactService artifactService = ServiceCacheFactory
//				.getServiceCache().getService(SystemArtifactService.class);
//		EEquipmentConstantService equipmentService = ServiceCacheFactory
//				.getServiceCache().getService(EEquipmentConstantService.class);
		SystemHeroService heroService = ServiceCacheFactory.getServiceCache()
				.getService(SystemHeroService.class);

		toolList = treasureConstantService.getToolList();
		ToolTypeConstant toolType = new ToolTypeConstant();
		typeNameMap = toolType.getToolTypeNameMap();
//		artifactIdNameMap = artifactService.findArtifactIDNameMap();
//		equipmentMap = equipmentService.findEquipmentIDNameMap();
		heroList = heroService.getHeroList();

		return INPUT;
	}

	public List<SystemTool> getToolList() {
		return toolList;
	}

	public void setToolList(List<SystemTool> toolList) {
		this.toolList = toolList;
	}

	public Map<Integer, String> getTypeNameMap() {
		return typeNameMap;
	}

	public void setTypeNameMap(Map<Integer, String> typeNameMap) {
		this.typeNameMap = typeNameMap;
	}

	public Map<Integer, String> getArtifactIdNameMap() {
		return artifactIdNameMap;
	}

	public void setArtifactIdNameMap(Map<Integer, String> artifactIdNameMap) {
		this.artifactIdNameMap = artifactIdNameMap;
	}

	public Map<Integer, String> getEquipmentMap() {
		return equipmentMap;
	}

	public void setEquipmentMap(Map<Integer, String> equipmentMap) {
		this.equipmentMap = equipmentMap;
	}

	public List<SystemHero> getHeroList() {
		return heroList;
	}

	public void setHeroList(List<SystemHero> heroList) {
		this.heroList = heroList;
	}

}
