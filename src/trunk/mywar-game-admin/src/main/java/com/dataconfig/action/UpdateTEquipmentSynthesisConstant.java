package com.dataconfig.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import com.dataconfig.bo.TEquipmentSynthesisConstant;
import com.dataconfig.constant.TTreasureTypeConstant;
import com.dataconfig.service.TEquipmentSynthesisConstantService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateTEquipmentSynthesisConstant  extends ALDAdminActionSupport implements ModelDriven<TEquipmentSynthesisConstant> {


	/** **/
	private static final long serialVersionUID = -583269578014186802L;

	/** **/
	private TEquipmentSynthesisConstant tequipmentSynthesisConstant = new TEquipmentSynthesisConstant();

	/** **/
	private String equipmentName;

	/** **/
	private Map<Integer, String> synthesisMaterialIdNameMap;

	/** **/
	private String isCommit = "F";
	
	public String execute() {
		TEquipmentSynthesisConstantService tequipmentSynthesisConstantService = ServiceCacheFactory.getServiceCache().getService(TEquipmentSynthesisConstantService.class);
		if ("F".equals(isCommit)) {
			try {
				TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
				synthesisMaterialIdNameMap = treasureConstantService.findTypeTreasureIdNameMap(TTreasureTypeConstant.SYNTHESIS_MATERIAL);
				
				String keyWord = URLDecoder.decode(tequipmentSynthesisConstant.getEquipmentName(), "UTF-8").trim();
				equipmentName = keyWord;
				tequipmentSynthesisConstant = tequipmentSynthesisConstantService.findOneTEquipmentSynthesisConstant(tequipmentSynthesisConstant.getEquipmentId());
				tequipmentSynthesisConstant.setEquipmentName(equipmentName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return INPUT;
		} else {
			tequipmentSynthesisConstantService.updateOneTEquipmentSynthesisConstant(tequipmentSynthesisConstant);
			return SUCCESS;
		}
	}

	@Override
	public TEquipmentSynthesisConstant getModel() {
		// TODO Auto-generated method stub
		return tequipmentSynthesisConstant;
	}

	public TEquipmentSynthesisConstant getTequipmentSynthesisConstant() {
		return tequipmentSynthesisConstant;
	}

	public void setTequipmentSynthesisConstant(
			TEquipmentSynthesisConstant tequipmentSynthesisConstant) {
		this.tequipmentSynthesisConstant = tequipmentSynthesisConstant;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public void setSynthesisMaterialIdNameMap(
			Map<Integer, String> synthesisMaterialIdNameMap) {
		this.synthesisMaterialIdNameMap = synthesisMaterialIdNameMap;
	}

	public Map<Integer, String> getSynthesisMaterialIdNameMap() {
		return synthesisMaterialIdNameMap;
	}

}
