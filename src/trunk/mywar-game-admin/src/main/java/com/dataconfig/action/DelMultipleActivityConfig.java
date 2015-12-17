package com.dataconfig.action;

import com.dataconfig.bo.MMultipleActivityConfig;
import com.dataconfig.service.MultipleActivityConfigService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelMultipleActivityConfig extends ALDAdminActionSupport implements ModelDriven<MMultipleActivityConfig> {

	/**  */
	private static final long serialVersionUID = 1L;
	
	private MMultipleActivityConfig multipleActivityConfig = new MMultipleActivityConfig();
	
	public void executeDel(){
		MultipleActivityConfigService service = ServiceCacheFactory.getServiceCache().getService(MultipleActivityConfigService.class);
		service.delMMultipleActivityConfig(multipleActivityConfig.getConfigId());
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

}
