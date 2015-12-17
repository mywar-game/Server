package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.PetUnlock;
import com.dataconfig.service.PetUnlockService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class PetUnlockList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -4461970145865722883L;
	
	private List<PetUnlock> petUnlockList;
	
	public String execute() {
		PetUnlockService petUnlockService = ServiceCacheFactory.getServiceCache().getService(PetUnlockService.class);
		IPage<PetUnlock> list = petUnlockService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			petUnlockList = (List<PetUnlock>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setPetUnlockList(List<PetUnlock> petUnlockList) {
		this.petUnlockList = petUnlockList;
	}

	public List<PetUnlock> getPetUnlockList() {
		return petUnlockList;
	}

}
