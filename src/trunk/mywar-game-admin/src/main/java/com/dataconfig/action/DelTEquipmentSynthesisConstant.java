package com.dataconfig.action;

import com.dataconfig.bo.TEquipmentSynthesisConstant;
import com.dataconfig.service.TEquipmentSynthesisConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelTEquipmentSynthesisConstant  extends ALDAdminActionSupport implements ModelDriven<TEquipmentSynthesisConstant> {

	/** **/
	private static final long serialVersionUID = 1L;
	
	/** **/
	private TEquipmentSynthesisConstant tequipmentSynthesisConstant = new TEquipmentSynthesisConstant();
	
	@Override
	public TEquipmentSynthesisConstant getModel() {
		return tequipmentSynthesisConstant;
	}
	
	public void executeDel() {
		TEquipmentSynthesisConstantService tequipmentSynthesisConstantService = ServiceCacheFactory.getServiceCache().getService(TEquipmentSynthesisConstantService.class);
		tequipmentSynthesisConstantService.delTEquipmentSynthesisConstant(tequipmentSynthesisConstant.getEquipmentId());
	}

	public TEquipmentSynthesisConstant getTequipmentSynthesisConstant() {
		return tequipmentSynthesisConstant;
	}

	public void setTequipmentSynthesisConstant(
			TEquipmentSynthesisConstant tequipmentSynthesisConstant) {
		this.tequipmentSynthesisConstant = tequipmentSynthesisConstant;
	}

}
