package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.SParamConfig;
import com.dataconfig.service.SParamConfigService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddSParamConfig extends ALDAdminActionSupport implements ModelDriven<SParamConfig> {

	private static final long serialVersionUID = -2498276293141599535L;
	
	private SParamConfig sparamConfig = new SParamConfig();
	
	private String isCommit = "F";

	private List<SParamConfig> sparamConfigList;
	
	public String execute() {
		SParamConfigService service = ServiceCacheFactory.getServiceCache().getService(SParamConfigService.class);
		if ("F".equals(this.isCommit)) {
			sparamConfigList = service.findSParamConfigList();
			return INPUT;
		} else {
			service.addSParamConfig(sparamConfig);
			return "ok";
		}
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

	@Override
	public SParamConfig getModel() {
		// TODO Auto-generated method stub
		return sparamConfig;
	}
	
	public List<SParamConfig> getSparamConfigList() {
		return sparamConfigList;
	}
	
	public void setSparamConfigList(List<SParamConfig> sparamConfigList) {
		this.sparamConfigList = sparamConfigList;
	}
}