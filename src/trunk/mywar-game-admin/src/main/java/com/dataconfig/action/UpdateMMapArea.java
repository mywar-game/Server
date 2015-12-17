package com.dataconfig.action;

import com.dataconfig.bo.MMapArea;
import com.dataconfig.service.MMapAreaService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateMMapArea extends ALDAdminActionSupport implements ModelDriven<MMapArea> {

	/** **/
	private static final long serialVersionUID = 921619033566283099L;

	/** **/
	private MMapArea mapArea = new MMapArea();
	
	/** **/
	private String isCommit = "F";
	
	public String execute() {
		MMapAreaService mapAreaService = ServiceCacheFactory.getServiceCache().getService(MMapAreaService.class);
		if ("F".equals(isCommit)) {
			mapArea = mapAreaService.findOneMMapArea(mapArea.getAreaId());
			return INPUT;
		} else {
			mapAreaService.updateOneMMapArea(mapArea);
			return SUCCESS;
		}
	}
	
	@Override
	public MMapArea getModel() {
		// TODO Auto-generated method stub
		return mapArea;
	}
	
	public MMapArea getMapArea() {
		return mapArea;
	}
	
	public void setMapArea(MMapArea mapArea) {
		this.mapArea = mapArea;
	}
	
	public String getIsCommit() {
		return isCommit;
	}
	
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
}
