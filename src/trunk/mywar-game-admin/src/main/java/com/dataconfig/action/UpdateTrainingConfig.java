package com.dataconfig.action;

import com.dataconfig.bo.BTrainingConfig;
import com.dataconfig.service.TrainingConfigService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateTrainingConfig extends ALDAdminActionSupport implements ModelDriven<BTrainingConfig>{

	/** * */
	private static final long serialVersionUID = -6597067354619134569L;
	
	/** **/
	private BTrainingConfig trainingConfig = new BTrainingConfig();
	
	/** **/
	private String isCommit = "F";
	
	public String execute() {
		TrainingConfigService trainingConfigService = ServiceCacheFactory.getServiceCache().getService(TrainingConfigService.class);
		if ("F".equals(isCommit)) {
			trainingConfig = trainingConfigService.findOne(trainingConfig.getLevel());
			return INPUT;
		} else {
			trainingConfigService.updateOne(trainingConfig);
			return SUCCESS;
		}
	}
	
	@Override
	public BTrainingConfig getModel() {
		return trainingConfig;
	}

	public void setTrainingConfig(BTrainingConfig trainingConfig) {
		this.trainingConfig = trainingConfig;
	}

	public BTrainingConfig getTrainingConfig() {
		return trainingConfig;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

}
