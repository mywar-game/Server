package com.dataconfig.action;

import java.util.List;
import java.util.Map;

import com.dataconfig.bo.SParamConfig;
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.SParamConfigService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class SParamConfigList extends ALDAdminPageActionSupport {
	private static final long serialVersionUID = -1661476638553707443L;
	
	private List<SParamConfig> sparamConfigList;

	private Map<Integer, String> equipmentIDNameMap;
	
	private Map<Integer, String> treasureIDNameMap;
	
	public String execute() {
		SParamConfigService sParamConfigService = ServiceCacheFactory.getServiceCache().getService(SParamConfigService.class);
		IPage<SParamConfig> list = sParamConfigService.findSParamConfigPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			sparamConfigList = (List<SParamConfig>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
			EEquipmentConstantService equipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
			equipmentIDNameMap = equipmentConstantService.findEquipmentIDNameMap();
			
			TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
			treasureIDNameMap = treasureConstantService.findTreasureIdNameMap();
		}
		return SUCCESS;
		
	}
	
	public List<SParamConfig> getSparamConfigList() {
		return sparamConfigList;
	}
	
	public void setSparamConfigList(List<SParamConfig> sparamConfigList) {
		this.sparamConfigList = sparamConfigList;
	}

	public void setEquipmentIDNameMap(Map<Integer, String> equipmentIDNameMap) {
		this.equipmentIDNameMap = equipmentIDNameMap;
	}

	public Map<Integer, String> getEquipmentIDNameMap() {
		return equipmentIDNameMap;
	}

	public void setTreasureIDNameMap(Map<Integer, String> treasureIDNameMap) {
		this.treasureIDNameMap = treasureIDNameMap;
	}

	public Map<Integer, String> getTreasureIDNameMap() {
		return treasureIDNameMap;
	}
}
