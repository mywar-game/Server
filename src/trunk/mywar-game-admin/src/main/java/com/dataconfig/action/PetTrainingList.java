package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.PetTraining;
import com.dataconfig.service.PetTrainingService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class PetTrainingList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = 8676068163147135428L;
	
	private List<PetTraining> petTrainingList;
	
	public String execute() {
		PetTrainingService petTrainingService = ServiceCacheFactory.getServiceCache().getService(PetTrainingService.class);
		IPage<PetTraining> list = petTrainingService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			petTrainingList = (List<PetTraining>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setPetTrainingList(List<PetTraining> petTrainingList) {
		this.petTrainingList = petTrainingList;
	}

	public List<PetTraining> getPetTrainingList() {
		return petTrainingList;
	}

}
