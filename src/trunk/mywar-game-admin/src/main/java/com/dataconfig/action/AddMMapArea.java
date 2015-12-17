package com.dataconfig.action;

import com.dataconfig.bo.MMapArea;
import com.dataconfig.service.MMapAreaService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddMMapArea extends ALDAdminActionSupport implements ModelDriven<MMapArea> {

	/** **/
	private static final long serialVersionUID = 3114312162756179444L;

	/** **/
	private MMapArea mapArea = new MMapArea();
	
	/** **/
	private String isCommit = "F";
	
	public String execute() {
		if ("F".equals(isCommit)) {
			return INPUT;
		} else {
			MMapAreaService mapAreaService = ServiceCacheFactory.getServiceCache().getService(MMapAreaService.class);
			mapAreaService.addMMapArea(mapArea);
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
