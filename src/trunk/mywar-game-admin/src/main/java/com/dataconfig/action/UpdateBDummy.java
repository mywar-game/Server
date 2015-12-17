package com.dataconfig.action;

import com.dataconfig.bo.BDummy;
import com.dataconfig.service.BDummyService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateBDummy extends ALDAdminActionSupport implements ModelDriven<BDummy> {

	/** **/
	private static final long serialVersionUID = 374732829148185691L;

	/** **/
	private BDummy dummy = new BDummy();
	
	/** **/
	private String isCommit = "F";
	
	@Override
	public String execute() {
		BDummyService dummyService = ServiceCacheFactory.getServiceCache().getService(BDummyService.class);
		if ("F".equals(isCommit)) {
			dummy = dummyService.findOneBDummy(dummy.getDummyId());
			return INPUT;
		} else {
			dummyService.updateOneBDummy(dummy);
			return SUCCESS;
		}
	}
	
	@Override
	public BDummy getModel() {
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
