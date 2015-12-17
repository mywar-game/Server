package com.dataconfig.action;

import com.dataconfig.bo.SParamConfig;
import com.dataconfig.service.SParamConfigService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateSParamConfig extends ALDAdminActionSupport implements ModelDriven<SParamConfig> {
	
	private static final long serialVersionUID = -1227699507741839030L;

	private SParamConfig sparamConfig = new SParamConfig();
	
	private String isCommit = "F";
	
	public String execute() {
		SParamConfigService sParamConfigService = ServiceCacheFactory.getServiceCache().getService(SParamConfigService.class);
		if ("F".equals(this.isCommit)) {
			sparamConfig = sParamConfigService.findOneSParamConfig(this.sparamConfig.getConfigkey());
			return INPUT;
		} else {
//			System.out.println(sparamConfig.getConfigkey());
			sParamConfigService.updateOneSParamConfig(sparamConfig);
			return SUCCESS;
		}
	}
	
	public SParamConfig getModel() {
		return this.sparamConfig;
	}
	
	public SParamConfig getSparamConfig() {
		return sparamConfig;
	}

	public void setSparamConfig(SParamConfig sparamConfig) {
		this.sparamConfig = sparamConfig;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}
