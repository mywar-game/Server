package com.dataconfig.action;

import java.util.List;
import java.util.Map;

import com.dataconfig.bo.TEquipmentSynthesisConstant;
import com.dataconfig.service.TEquipmentSynthesisConstantService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class TEquipmentSynthesisConstantList  extends ALDAdminPageActionSupport {
	
	/**  **/
	private static final long serialVersionUID = -1892343054539342430L;
	
	/**  **/
	private List<TEquipmentSynthesisConstant> tequipmentSynthesisConstantList;
	
	/**  **/
	private Map<Integer, String> treasureIDNameMap;
	
	public String execute() {
		TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
		treasureIDNameMap = treasureConstantService.findTreasureIdNameMap();
		
		TEquipmentSynthesisConstantService tequipmentSynthesisConstantService = ServiceCacheFactory.getServiceCache().getService(TEquipmentSynthesisConstantService.class);
		IPage<TEquipmentSynthesisConstant> page = tequipmentSynthesisConstantService.findTEquipmentSynthesisConstantPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (page != null) {
			tequipmentSynthesisConstantList = (List<TEquipmentSynthesisConstant>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getPageSize());
		}
		return SUCCESS;
	}

	public List<TEquipmentSynthesisConstant> getTequipmentSynthesisConstantList() {
		return tequipmentSynthesisConstantList;
	}

	public void setTequipmentSynthesisConstantList(
			List<TEquipmentSynthesisConstant> tequipmentSynthesisConstantList) {
		this.tequipmentSynthesisConstantList = tequipmentSynthesisConstantList;
	}

	public void setTreasureIDNameMap(Map<Integer, String> treasureIDNameMap) {
		this.treasureIDNameMap = treasureIDNameMap;
	}

	public Map<Integer, String> getTreasureIDNameMap() {
		return treasureIDNameMap;
	}
	
}
