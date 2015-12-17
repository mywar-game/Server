package com.dataconfig.action;

import com.dataconfig.bo.MMultipleActivityConfig;
import com.dataconfig.service.MultipleActivityConfigService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddMultipleActivityConfig extends ALDAdminActionSupport implements ModelDriven<MMultipleActivityConfig> {

	/**  */
	private static final long serialVersionUID = -5503904463144903921L;

	private MMultipleActivityConfig multipleActivityConfig = new MMultipleActivityConfig();
	
	private String isCommit = "F";
	
	public String execute(){
		MultipleActivityConfigService service = ServiceCacheFactory.getServiceCache().getService(MultipleActivityConfigService.class);
		if ("F".equals(this.isCommit)) {
			multipleActivityConfig.setMultiple(2);
			multipleActivityConfig.setUserLevelLimit(20);
			return INPUT;
		} else {
			service.addMMultipleActivityConfig(multipleActivityConfig);
			return SUCCESS;
		}
	}
	
	@Override
	public MMultipleActivityConfig getModel() {
		return multipleActivityConfig;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setMultipleActivityConfig(MMultipleActivityConfig multipleActivityConfig) {
		this.multipleActivityConfig = multipleActivityConfig;
	}

	public MMultipleActivityConfig getMultipleActivityConfig() {
		return multipleActivityConfig;
	}
}
