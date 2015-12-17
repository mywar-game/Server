package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.MMultipleActivityConfig;
import com.dataconfig.service.MultipleActivityConfigService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class MultipleActivityConfigList extends ALDAdminPageActionSupport {

	/**  */
	private static final long serialVersionUID = -5152786173374138231L;
	
	private List<MMultipleActivityConfig> multipleActivityConfigList;
	
	public String execute(){
		MultipleActivityConfigService multipleActivityConfigService = ServiceCacheFactory.getServiceCache().getService(MultipleActivityConfigService.class);
		IPage<MMultipleActivityConfig> list = multipleActivityConfigService.findMMultipleActivityConfigPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			multipleActivityConfigList = (List<MMultipleActivityConfig>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setMultipleActivityConfigList(
			List<MMultipleActivityConfig> multipleActivityConfigList) {
		this.multipleActivityConfigList = multipleActivityConfigList;
	}

	public List<MMultipleActivityConfig> getMultipleActivityConfigList() {
		return multipleActivityConfigList;
	}

}
