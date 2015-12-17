package com.dataconfig.action;

import com.dataconfig.bo.BDummy;
import com.dataconfig.service.BDummyService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddBDummy extends ALDAdminActionSupport implements ModelDriven<BDummy> {
	
	/** **/
	private static final long serialVersionUID = 6044944280447041144L;
	
	/** **/
	private BDummy dummy = new BDummy();
	
	/** **/
	private String isCommit = "F";
	
	public String execute() {
		if ("F".equals(isCommit)) {
			return INPUT;
		} else {
			BDummyService dummyService = ServiceCacheFactory.getServiceCache().getService(BDummyService.class);
			dummyService.addBDummy(dummy);
			return SUCCESS;
		}
	}
	
	@Override
	public BDummy getModel() {
		// TODO Auto-generated method stub
		return dummy;
	}

	public BDummy getDummy() {
		return dummy;
	}

	public void setDummy(BDummy dummy) {
		this.dummy = dummy;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
}
