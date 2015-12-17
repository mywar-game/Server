package com.dataconfig.action;

import com.dataconfig.bo.MMapArea;
import com.dataconfig.service.MMapAreaService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelMMapArea extends ALDAdminActionSupport implements ModelDriven<MMapArea> {

	/** **/
	private static final long serialVersionUID = 2747784019447441571L;

	/** **/
	private MMapArea mapArea = new MMapArea();
	
	public void executeDel() {
		MMapAreaService mapAreaService = ServiceCacheFactory.getServiceCache().getService(MMapAreaService.class);
		mapAreaService.delMMapArea(mapArea.getAreaId());
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
}
