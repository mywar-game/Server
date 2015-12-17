package com.dataconfig.action;

import com.dataconfig.bo.BDummy;
import com.dataconfig.service.BDummyService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelBDummy extends ALDAdminActionSupport implements ModelDriven<BDummy> {

	/** **/
	private static final long serialVersionUID = -2519745233448825633L;

	/** **/
	private BDummy dummy = new BDummy();
	
	public void executeDel() {
		BDummyService dummyService = ServiceCacheFactory.getServiceCache().getService(BDummyService.class);
		dummyService.delBDummy(dummy.getDummyId());
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
}
