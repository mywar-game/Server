package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.SEffectConstant;
import com.dataconfig.service.SEffectConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class SEffectConstantList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -3286253713260870847L;
	
	private List<SEffectConstant> seffectConstantList;
	
	public String execute() {
		SEffectConstantService sEffectConstantService = ServiceCacheFactory.getServiceCache().getService(SEffectConstantService.class);
		IPage<SEffectConstant> list = sEffectConstantService.findSEffectConstantPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			seffectConstantList = (List<SEffectConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public List<SEffectConstant> getSeffectConstantList() {
		return seffectConstantList;
	}

	public void setSeffectConstantList(List<SEffectConstant> seffectConstantList) {
		this.seffectConstantList = seffectConstantList;
	}
}
