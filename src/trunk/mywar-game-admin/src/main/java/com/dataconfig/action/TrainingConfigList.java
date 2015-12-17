package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.BTrainingConfig;
import com.dataconfig.service.TrainingConfigService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class TrainingConfigList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -8265214453883770996L;
	
	private List<BTrainingConfig> trainingConfigList;
	
	public String execute() {
		TrainingConfigService trainingConfigService = ServiceCacheFactory.getServiceCache().getService(TrainingConfigService.class);
		IPage<BTrainingConfig> list = trainingConfigService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			trainingConfigList = (List<BTrainingConfig>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setTrainingConfigList(List<BTrainingConfig> trainingConfigList) {
		this.trainingConfigList = trainingConfigList;
	}

	public List<BTrainingConfig> getTrainingConfigList() {
		return trainingConfigList;
	}

}
