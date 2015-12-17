package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.BGeneCostConstant;
import com.dataconfig.service.GeneCostConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class GeneCostConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -6771264489981275027L;
	
	private List<BGeneCostConstant> geneCostConstantList;
	
	public String execute() {
		GeneCostConstantService geneCostConstantService = ServiceCacheFactory.getServiceCache().getService(GeneCostConstantService.class);
		IPage<BGeneCostConstant> list = geneCostConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			geneCostConstantList = (List<BGeneCostConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setGeneCostConstantList(List<BGeneCostConstant> geneCostConstantList) {
		this.geneCostConstantList = geneCostConstantList;
	}

	public List<BGeneCostConstant> getGeneCostConstantList() {
		return geneCostConstantList;
	}

}
