package com.dataconfig.action;

import com.dataconfig.bo.MMultipleActivityConfig;
import com.dataconfig.service.MultipleActivityConfigService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateMultipleActivityConfig extends ALDAdminActionSupport implements ModelDriven<MMultipleActivityConfig> {

	/**  */
	private static final long serialVersionUID = 1L;
	
	private MMultipleActivityConfig multipleActivityConfig = new MMultipleActivityConfig();
	
	private String isCommit = "F";
	
	public String execute(){
		MultipleActivityConfigService service = ServiceCacheFactory.getServiceCache().getService(MultipleActivityConfigService.class);
		if ("F".equals(isCommit)) {
			multipleActivityConfig = service.findOneMMultipleActivityConfig(multipleActivityConfig.getConfigId());
			return INPUT;
		} else {
			service.updateOneMMultipleActivityConfig(multipleActivityConfig);
			return SUCCESS;
		}
	}

	@Override
	public MMultipleActivityConfig getModel() {
		return multipleActivityConfig;
	}

	public void setMultipleActivityConfig(MMultipleActivityConfig multipleActivityConfig) {
		this.multipleActivityConfig = multipleActivityConfig;
	}

	public MMultipleActivityConfig getMultipleActivityConfig() {
		return multipleActivityConfig;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

}
