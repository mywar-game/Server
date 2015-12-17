package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.PrisonConstant;
import com.dataconfig.service.PrisonConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class PrisonConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -6746331648290989191L;

	private List<PrisonConstant> prisonConstantList;
	
	public String execute() {
		PrisonConstantService prisonConstantService = ServiceCacheFactory.getServiceCache().getService(PrisonConstantService.class);
		IPage<PrisonConstant> list = prisonConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			prisonConstantList = (List<PrisonConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	/**
	 * 获取 prisonConstantList 
	 */
	public List<PrisonConstant> getPrisonConstantList() {
		return prisonConstantList;
	}

	/**
	 * 设置 prisonConstantList 
	 */
	public void setPrisonConstantList(List<PrisonConstant> prisonConstantList) {
		this.prisonConstantList = prisonConstantList;
	}

}
