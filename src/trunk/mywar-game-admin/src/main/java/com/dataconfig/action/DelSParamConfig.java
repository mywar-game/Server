package com.dataconfig.action;

import com.dataconfig.bo.SParamConfig;
import com.dataconfig.service.SParamConfigService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelSParamConfig extends ALDAdminActionSupport implements ModelDriven<SParamConfig> {
	private static final long serialVersionUID = 8139295253400255912L;
	
	private SParamConfig spParamConfig = new SParamConfig();
	

	public SParamConfig getSpParamConfig() {
		return spParamConfig;
	}


	public void setSpParamConfig(SParamConfig spParamConfig) {
		this.spParamConfig = spParamConfig;
	}
	
	public void executeDel() {
		SParamConfigService service = ServiceCacheFactory.getServiceCache().getService(SParamConfigService.class);
		service.delSParamConfig(spParamConfig.getConfigkey());
	}

	@Override
	public SParamConfig getModel() {
		// TODO Auto-generated method stub
		return spParamConfig;
	}
}
